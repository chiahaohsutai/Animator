package cs3500.view.visitors;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Rect;

/**
 * Visits a shape and formats the shape in XML format for an SVG animation.
 * In order to get the resulting information out of the class, use the toString method.
 * The output of the SVG will be '<svg>' ... '</svg>' where the tags are separated by a space.
 */
public class ShapeVisitor implements ISVisitor {
  private String results;
  private String id;

  /**
   * Creates a shape visitor. The shape visitor show be identified by an id.
   * @param id is the identification name for the shape being converted to svg.
   */
  public ShapeVisitor(String id) {
    this.id = id;
  }

  @Override
  public void visitRect(Rect rect) {
    this.results = String.format("<rect id=\"%s\" x=\"%.3f\" y=\"%.3f\" width=\"%.3f\" " +
            "height=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\"> </svg>",
            id, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), rect.getRed(),
            rect.getBlue(), rect.getGreen());
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    if (ellipse.getWidth() == ellipse.getHeight()) {
      this.results = String.format("<circle id=\"%s\" cx=\"%.3f\" cy=\"%.3f\" r=\"%.3f\" " +
              "fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" > \n",
              type, id, x, y, w, r, g, b);
    }
  }

  @Override
  public String toString() {
    return this.results;
  }
}
