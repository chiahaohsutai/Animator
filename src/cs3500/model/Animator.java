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
import java.util.stream.Stream;

import cs3500.model.Shape.IShape;

/**
 * Represents an animator that can animate simple geometric shapes. Tracks the state of each shape
 * in the model. Allows for shape to be added, deleted and transformed.
 */
public class Animator implements IAnimator {
  private int width;
  private int height;
  private final int tickRate;
  private final Map<String, IShape> shapes;
  private final Map<Integer, List<String>> creationTimes;
  private final Map<Integer, List<String>> obituaryTimes;
  private final Map<String, Deque<ITransform>> transformations;

  public Animator(int tickRate) {
    this.width = 100;
    this.height = 100;
    this.tickRate = tickRate;
    this.shapes = new LinkedHashMap<>();
    this.creationTimes = new TreeMap<>(Comparator.comparingInt(key -> key));
    this.obituaryTimes = new TreeMap<>(Comparator.comparingInt(key -> key));
    this.transformations = new HashMap<>();
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
    for (Map<Integer, List<String>> m : Arrays.asList(creationTimes, obituaryTimes)) {
      initializeMap(m, start);
    }
    creationTimes.get(start).add(name);
    obituaryTimes.get(end).add(name);

    // create a space for transformations.
    transformations.put(name, new LinkedList<>());
  }

  @Override
  public void remove(String name) {
    checkForNulls(name);
    checkNameExistence(name);
    shapes.remove(name);
    transformations.remove(name);
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
    checkForNulls(name);
    checkNameExistence(name);

  }

  @Override
  public void reScale(String name, int start, int end, double width, double height) {

  }

  @Override
  public void setColor(String name, int start, int end, int r, int g, int b) {

  }

  @Override
  public void deleteTransform(String name) {
    checkNameExistence(name);
    if (transformations.get(name).isEmpty()) {
      throw new IllegalArgumentException("The shape has no transformations.");
    }
    transformations.get(name).removeLast();
  }

  @Override
  public void setBounds(int width, int height) {
    checkDimensions(width, height);
    this.height = height;
    this.width = width;
  }

  @Override
  public IShape get(String name) {
    checkForNulls(name);
    checkNameExistence(name);
    return shapes.get(name).copy();
  }

  @Override
  public int getTickRate() {
    return this.tickRate;
  }

  /**
   * Checks if the given name corresponds to a shape in the animator.
   *
   * @param name of the shape in the animator.
   * @throws IllegalArgumentException if the name does not exist.
   */
  private void checkNameExistence(String name) {
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
}
