package cs3500.model.Shape;

/**
 * Represents a rectangular parallelogram with a color and a position.
 */
public class Rect extends AShape {

  /**
   * Creates a rectangular shape with a color and a shape.
   *
   * @param w is the width of the rectangle.
   * @param h is the height of the rectangle.
   * @param x is the x-coordinate of the rectangle.
   * @param y is the y-coordinate of the rectangle.
   * @param r is red-component for an RGB color.
   * @param g is the green-component for an RGB color.
   * @param b is the blue-component for an RGB color.
   */
  public Rect(double w, double h, double x, double y, int r, int g, int b) {
    super(w, h, x, y, r, g, b);
  }

  @Override
  public IShape copy() {
    return new Rect(getWidth(), getHeight(), getX(), getY(), getRed(), getGreen(), getBlue());
  }
}
