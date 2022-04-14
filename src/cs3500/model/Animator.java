package cs3500.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.model.transformation.TransformType;
import cs3500.view.visitors.VisualTransformVisitor;

/**
 * Represents an animator that can animate simple geometric shapes. Tracks the state of each shape
 * in the model. Allows for shape to be added, deleted and transformed.
 */
public class Animator implements IAnimator {
  private int width;
  private int height;
  private int tickRate;
  private final Map<String, IShape> shapes;
  private final Map<Integer, List<String>> creationTimes;
  private final Map<Integer, List<String>> obituaryTimes;
  private final Map<String, Deque<ITransform>> colorTransformations;
  private final Map<String, Deque<ITransform>> scaleTransformations;
  private final Map<String, Deque<ITransform>> positionTransformations;
  private Map<Integer, List<IShape>> mostRecentStateOfShapesAtTick;

  /**
   * Creates an instance of an animator with a tick rate of 1 and a canvas dimension of 0x0.
   */
  public Animator() {
    this.width = 0;
    this.height = 0;
    this.tickRate = 1;
    this.shapes = new LinkedHashMap<>();
    this.creationTimes = new TreeMap<>(Comparator.comparingInt(key -> key));
    this.obituaryTimes = new TreeMap<>(Comparator.comparingInt(key -> key));
    this.colorTransformations = new HashMap<>();
    this.scaleTransformations = new HashMap<>();
    this.positionTransformations = new HashMap<>();
    this.mostRecentStateOfShapesAtTick = new HashMap<>();
  }

  @Override
  public void add(String name, IShape shape, int start, int end) {
    checkForNulls(name, shape);
    checkValidInterval(start, end);
    if (shapes.containsValue(shape) || shapes.containsKey(name)) {
      throw new IllegalArgumentException("The given shape or name already exists.");
    }
    shapes.put(name, shape);
    shape.setName(name);
    // initialize a list if the key doesn't exist in the map.
    initializeMap(obituaryTimes, end);
    initializeMap(creationTimes, start);
    creationTimes.get(start).add(name);
    obituaryTimes.get(end).add(name);

    // create a space for transformations.
    colorTransformations.put(name, new LinkedList<>());
    scaleTransformations.put(name, new LinkedList<>());
    positionTransformations.put(name, new LinkedList<>());
  }

  @Override
  public void remove(String name) {
    checkNameExistence(name);
    shapes.get(name).setName(null);
    shapes.remove(name);
    colorTransformations.remove(name);
    positionTransformations.remove(name);
    scaleTransformations.remove(name);
    // remove from the rest of data structures.
    for (Map<Integer, List<String>> m : Arrays.asList(creationTimes, obituaryTimes)) {
      for (List<String> names : m.values()) {
        int index = names.indexOf(name);
        if (index != -1) {
          names.remove(index);
          break;
        }
      }
    }
  }

  @Override
  public void move(String name, int start, int end, double xCoordinate, double yCoordinate) {
    checkParamTransform(start, end, name, positionTransformations);
    ITransform t = positionTransformations.get(name).peekLast();
    if (Objects.isNull(t)) {
      IShape s = shapes.get(name);
      positionTransformations.get(name).add(new PositionTransform(start, end,
              s.getX(), s.getY(), xCoordinate, yCoordinate));
      return;
    }
    double[] data = t.getData();
    positionTransformations.get(name).add(new PositionTransform(start, end, data[0], data[1],
            xCoordinate, yCoordinate));
  }

  @Override
  public void reScale(String name, int start, int end, double width, double height) {
    checkParamTransform(start, end, name, scaleTransformations);
    checkDimensions(width, height);
    ITransform t = scaleTransformations.get(name).peekLast();
    if (Objects.isNull(t)) {
      IShape s = shapes.get(name);
      scaleTransformations.get(name).add(new ScaleTransform(start, end, s.getWidth(),
              s.getHeight(), width, height));
      return;
    }
    double[] data = t.getData();
    scaleTransformations.get(name).add(new ScaleTransform(start, end, data[0], data[1],
            width, height));
  }

  @Override
  public void setColor(String name, int start, int end, int r, int g, int b) {
    checkParamTransform(start, end, name, colorTransformations);
    checkRGB(r, g, b);
    ITransform t = colorTransformations.get(name).peekLast();
    if (Objects.isNull(t)) {
      IShape s = shapes.get(name);
      colorTransformations.get(name).add(new ColorTransform(start, end, s.getRed(),
              s.getGreen(), s.getBlue(), r, g, b));
      return;
    }
    double[] c = t.getData();
    colorTransformations.get(name).add(new ColorTransform(start, end, (int)c[0],
            (int)c[1], (int)c[2], r, g, b));
  }

  @Override
  public void deleteTransform(String name, TransformType type) {
    checkNameExistence(name);
    List<Deque<ITransform>> transforms = getTransforms(name);
    for (Deque<ITransform> d : transforms) {
      if (!d.isEmpty() && d.getFirst().getType() == type) {
        d.removeLast();
      }
    }
  }

  @Override
  public void setBounds(int width, int height) {
    checkDimensions(width, height);
    this.height = height;
    this.width = width;
  }

  @Override
  public int getCanvasWidth() {
    return width;
  }

  @Override
  public int getCanvasHeight() {
    return height;
  }

  @Override
  public void setTickRate(int tick) {
    this.tickRate = tick;
  }

  @Override
  public void calculateStates(int tick) {

    Map<String, List<ITransform>> transformsAtTick = getTransformsAtTick(tick);

    for (Map.Entry<String, List<ITransform>> singleShapeEntry : transformsAtTick.entrySet()) {
      IShape shapeToTransform = this.getShape(singleShapeEntry.getKey());

      ITVisitor visualTransformVisitor = new VisualTransformVisitor(mostRecentStateOfShapesAtTick,
              shapeToTransform, tick);

      for (ITransform singleShapeTransformations : singleShapeEntry.getValue()) {
        singleShapeTransformations.visitor(visualTransformVisitor);
      }

    }


  }

  @Override
  public int getStart(String name) {
    checkNameExistence(name);
    return getTick(name, creationTimes);
  }

  @Override
  public int getEnd(String name) {
    checkNameExistence(name);
    return getTick(name, obituaryTimes);
  }

  @Override
  public IShape getShape(String name) {
    checkForNulls(name);
    if (!shapes.containsKey(name)) {
      return null;
    }
    return shapes.get(name).copy();
  }

  @Override
  public int getTickRate() {
    return this.tickRate;
  }

  @Override
  public Map<String, List<ITransform>> getState() {
    Map<String, List<ITransform>> result = new LinkedHashMap<>();
    for (String name : shapes.keySet()) {
      List<ITransform> sortedList = new ArrayList<>();

      // get the deque for the given name
      List<Deque<ITransform>> transforms = getTransforms(name);
      // filters the list (remove empty lists)
      Stream<Deque<ITransform>> list = transforms.stream().filter(l -> !l.isEmpty());
      // convert the stream into a list
      List<Deque<ITransform>> nonEmpty = list.collect(Collectors.toList());

      nonEmpty.forEach(sortedList::addAll);
      sortedList.sort(Comparator.comparingInt(ITransform::getStart));
      result.put(name, sortedList);
    }
    if (result.values().isEmpty()) {
      return null;
    }
    return result;
  }

  @Override
  public List<IShape> getShapes() {
    List<IShape> shapesList = new ArrayList<>();
    for (IShape shape : shapes.values()) {
      shapesList.add(shape.copy());
    }
    return shapesList;
  }

  @Override
  public List<IShape> getShapesAtTick(int tick) {
    /*
    return shapes.values().stream()
            .map((IShape s) -> s.copy())
            .filter((IShape s) -> getStart(s.getName()) >= tick && getEnd(s.getName()) < tick)
            .collect(Collectors.toList());
     */

    List<IShape> listOfShapes = new ArrayList<>();

    for (IShape singleShape : this.getShapes()) {
      if (getStart(singleShape.getName()) >= tick &&
              getEnd(singleShape.getName()) < tick) {
        listOfShapes.add(singleShape.copy());
      }
    }

    return listOfShapes;
  }

  /**
   *
   * @param tick
   * @return
   */
  private Map<String, List<ITransform>> getTransformsAtTick(int tick) {
    Map<String, List<ITransform>> statesByShape = this.getState();

    for (Map.Entry<String, List<ITransform>> singleShapeEntry : statesByShape.entrySet()) {
      statesByShape.put(singleShapeEntry.getKey(),
              singleShapeEntry.getValue().stream()
                      .filter((ITransform t) -> t.getStart() >= tick && t.getEnd() < tick)
                      .map((ITransform t) -> t.copy())
                      .collect(Collectors.toList()));
    }

    return statesByShape;
  }

  /**
   * Checks if the given name corresponds to a shape in the animator.
   *
   * @param name of the shape in the animator.
   * @throws IllegalArgumentException if the name does not exist.
   */
  private void checkNameExistence(String name) {
    checkForNulls(name);
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("The given name does not exist.");
    }
  }

  /**
   * Check is any of the given values is null.
   *
   * @param o is an array of parameters which will be checked for null.
   * @throws IllegalArgumentException if any value is null in the array.
   */
  private void checkForNulls(Object... o) {
    if (Arrays.stream(o).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Cannot have null values.");
    }
  }

  /**
   * Checks if the time interval is consistent. This means that the time interval is not negative
   * or if the start time is greater than the end time.
   *
   * @param start is the start of the time interval.
   * @param end is the end of the time interval.
   * @throws IllegalArgumentException if the interval is inconsistent.
   */
  private void checkValidInterval(int start, int end) {
    if (start > end || start < 0) {
      throw new IllegalArgumentException("Invalid tick interval.");
    }
  }

  /**
   * Initializes an empty list for a map which has lists for its values.
   *
   * @param map is the map that will be initialized.
   * @param key is the key at which the list will be created.
   * @param <T> is the type for the key.
   * @param <V> is the type for the list (the value of the map).
   */
  private <T, V> void initializeMap(Map<T, List<V>> map, T key) {
    List<V> list = new ArrayList<>();
    if (Objects.isNull(map.get(key))) {
      map.put(key, list);
    }
  }

  /**
   * Checks that the RGB values are in the range [0, 255].
   * @param r is the red component of the RGB.
   * @param g is the green component of the RGB.
   * @param b is the blue component of the RGB.
   * @throws IllegalArgumentException if any value of the RGB is not in the range [0, 255].
   */
  private void checkRGB(double r, double g, double b) {
    boolean validateColors = Stream.of(r, g, b).allMatch((val -> val >= 0 && val <= 255));
    if (!validateColors) {
      throw new IllegalArgumentException("A parameter is not in the range [0, 255]");
    }
  }

  /**
   * Checks that the dimensions are valid.
   *
   * @param width is the dimension that a shape take in the x-direction.
   * @param height is the dimension that a shape take in the y-direction.
   * @throws IllegalArgumentException if the width or height <= 0.
   */
  private void checkDimensions(double width, double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be <= 0");
    }
  }

  /**
   * Checks the end time for the last transformed.
   *
   * @param list is the list of transforms.
   * @param start is the start of the interval.
   * @throws IllegalArgumentException is the transform leads to an overlap.
   */
  private void checkIntervalOverlap(Deque<ITransform> list, int start) {
    if (!list.isEmpty()) {
      int end = list.getLast().getEnd();
      if (start < end) {
        throw new IllegalArgumentException("Invalid start. Causes overlap");
      }
    }
  }

  /**
   * Gets all the transformation for the given name.
   *
   * @param name is the shape of the shape.
   * @return a list with all the position, scale, color transformation in the written order.
   */
  private List<Deque<ITransform>> getTransforms(String name) {
    return Stream.of(positionTransformations, scaleTransformations,
            colorTransformations).map((l) -> (l.get(name))).collect(Collectors.toList());
  }

  /**
   * Gets the tick at which the shape is being created or destroyed.
   *
   * @param name is the name of the shape.
   * @param ticks map of ticks and shapes.
   * @return the tick at which the name appears.
   */
  private int getTick(String name, Map<Integer, List<String>> ticks) {
    checkNameExistence(name);
    for (Map.Entry<Integer, List<String>> names : ticks.entrySet()) {
      if (!names.getValue().contains(name)) {
        continue;
      }
      return names.getKey();
    }
    throw new IllegalArgumentException("The name is not in the animator");
  }

  /**
   * Checks that the shape exists in the for the given time interval.
   *
   * @param start is first tick.
   * @param end is last tick
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if the shape does not exist in the given tick.
   */
  private void checkTransformationInterval(int start, int end, String name) {
    if (start < this.getStart(name) || end > this.getEnd(name)) {
      throw new IllegalArgumentException("The shape does not exists at some tick in the interval.");
    }
  }

  /**
   * Checks that all the parameters for a transform are valid.
   * @param start the start tick.
   * @param end the end tick.
   * @param name the name of the shape.
   * @param transforms is the map of transformations and shape name.
   * @throws IllegalArgumentException if time interval is inconsistent.
   * @throws IllegalArgumentException if the transform leads to an overlap.
   * @throws IllegalArgumentException if the name is not in the animator.
   * @throws IllegalArgumentException if the transforms happens outside the shapes' lifespan.
   */
  private void checkParamTransform(int start, int end, String name, Map<String,
          Deque<ITransform>> transforms) {
    checkNameExistence(name);
    checkValidInterval(start, end);
    checkTransformationInterval(start, end, name);
    checkIntervalOverlap(transforms.get(name), start);
  }
}
