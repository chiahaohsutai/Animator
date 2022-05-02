package cs3500.view.visualview;

import java.awt.Graphics2D;
import java.awt.Color;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;

/**
 * Draws a shape using the Java AWT library. It draws the shape according to the shapes
 * attributes.
 */
public class VisualShapeVisitor implements ISVisitor {
  private final Graphics2D g2;

  /**
   * Instantiates the visitor with a graphics 2D object. The object is used to draw the shape.
   * @param g2 is the graphics object used to draw a shape in a GUI.
   * @throws IllegalArgumentException if the graphics object is null.
   */
  public VisualShapeVisitor(Graphics2D g2) {
    if (g2 == null) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    this.g2 = g2;
  }

  @Override
  public void visitRect(Rect rect) {
    g2.setColor(new Color(rect.getRed(), rect.getGreen(), rect.getBlue()));
    g2.fillRect((int)rect.getX(), (int)rect.getY(),
            (int)rect.getWidth(), (int)rect.getHeight());
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    g2.setColor(new Color(ellipse.getRed(), ellipse.getGreen(), ellipse.getBlue()));
    g2.fillOval((int)ellipse.getX(), (int)ellipse.getY(),
            (int)ellipse.getWidth(), (int)ellipse.getHeight());
  }

  @Override
  public void visitPlus(Plus plus) {
    ////////////////////////////////////////////////////////////////////////////
  }
}
