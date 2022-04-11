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
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.model.transformation.TransformType;

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
  }

  @Override
  public void add(String name, IShape shape, int start, int end) {
    checkForNulls(name, shape);
    checkValidInterval(start, end);
    if (shapes.containsValue(shape) || shapes.containsKey(name)) {
      throw new IllegalArgumentException("The given shape or name already exists.");
    }
    shapes.put(name, shape);

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
    checkForNulls(name);
    checkNameExistence(name);
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
    checkNameExistence(name);
    checkValidInterval(start, end);
    checkIntervalOverlap(positionTransformations.get(name), start);
    positionTransformations.get(name).add(new PositionTransform(start, end,
            xCoordinate, yCoordinate));
  }

  @Override
  public void reScale(String name, int start, int end, double width, double height) {
    checkNameExistence(name);
    checkValidInterval(start, end);
    checkDimensions(width, height);
    checkIntervalOverlap(scaleTransformations.get(name), start);
    scaleTransformations.get(name).add(new ScaleTransform(start, end, width, height));
  }

  @Override
  public void setColor(String name, int start, int end, int r, int g, int b) {
    checkNameExistence(name);
    checkValidInterval(start, end);
    checkRGB(r, g, b);
    checkIntervalOverlap(colorTransformations.get(name), start);
    colorTransformations.get(name).add(new ColorTransform(start, end, r, g, b));
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
    Map<String, List<ITransform>> result = new HashMap<>();
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
  private void checkRGB(int r, int g, int b) {
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
}
