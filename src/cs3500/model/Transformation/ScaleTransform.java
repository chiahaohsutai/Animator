package cs3500.model.Transformation;

/**
 * Represents a transformation that scales the given object to a new scale.
 */
public class ScaleTransform extends ATransform {
  private final double width;
  private final double height;

  /**
   * Sets the values for the scale of the object.
   *
   * @param start is the start tick of the transformation.
   * @param end   is the end time of the transformation.
   * @param width is the width of the new scale.
   * @param height is the height of the new scale.
   */
  public ScaleTransform(int start, int end, double width, double height) {
    super(start, end);
    checkDimensions(width, height);
    this.width = width;
    this.height = height;
  }

  @Override
  public TransformType getType() {
    return TransformType.SCALE;
  }

  @Override
  public double[] getData() {
    return new double[] {width, height};
  }

  @Override
  public ITransform copy() {
    return new ScaleTransform(start, end, width, height);
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
