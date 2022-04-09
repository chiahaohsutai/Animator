package cs3500.model.Transformation;

public class PositionTransform extends ATransform {
  private final double x;
  private final double y;

  /**
   * Sets the position to which the transform translates the object to. Sets the time
   * interval for it as well.
   *
   * @param start is the start tick of the transformation.
   * @param end   is the end time of the transformation.
   * @param x is the x-coordinate.
   * @param y is the y-coordinate.
   *
   */
  public PositionTransform(int start, int end, double x, double y) {
    super(start, end);
    this.x = x;
    this.y = y;
  }

  @Override
  public TransformType getType() {
    return TransformType.POSITION;
  }

  @Override
  public double[] getData() {
    return new double[] {x, y};
  }
}
