package cs3500.model.Transformation;

import java.awt.Color;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Represents a color transformation.
 */
public class ColorTransform extends ATransform {
  private final Color color;

  /**
   * Instantiates a color transformation. The parameters are in RGB.
   *
   * @param start is the tick in which the transformation starts.
   * @param end is the tick in which the transformation ends.
   * @param r is the red component in RGB.
   * @param g is the green component in RGB.
   * @param b is the blue component in RGB.
   * @throws IllegalArgumentException if any parameter is not in the range [0, 255].
   */
  public ColorTransform(int start, int end, int r, int g, int b) {
    super(start, end);
    checkRGB(r, g, b);
    this.color = new Color(r, g, b);
  }

  @Override
  public TransformType getType() {
    return TransformType.COLOR;
  }

  @Override
  public double[] getData() {
    double[] values = convert(color.getRed(), color.getGreen(), color.getRed());
    return new double[] {values[0], values[1], values[2]};
  }

  @Override
  public ITransform copy() {
    return new ColorTransform(start, end, color.getRed(), color.getGreen(), color.getBlue());
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
   * Converts the integers to an array of doubles.
   *
   * @return an array of doubles.
   */
  private double[] convert(int... values) {
    DoubleStream collection = Arrays.stream(values).mapToDouble((v) -> (double)v);
    return collection.toArray();
  }
}
