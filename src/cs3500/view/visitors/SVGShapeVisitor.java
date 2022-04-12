package cs3500.view.visitors;

import java.util.Arrays;
import java.util.Objects;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Rect;

/**
 * Visits a shape and formats the shape in XML format for an SVG animation.
 * In order to get the resulting information out of the class, use the toString method.
 * The output of the SVG will be '<svg>' ... '</svg>' where the tags are separated by a newline
 * character. The shape being visited should always have a valid name.
 */
public class SVGShapeVisitor implements ISVisitor {
  private String results;

  @Override
  public void visitRect(Rect rect) {
    checkForNulls(rect);
    this.results = String.format("<rect id=\"%s\" x=\"%.3f\" y=\"%.3f\" width=\"%.3f\" " +
            "height=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n</rect>",
            rect.getName(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(),
            rect.getRed(), rect.getBlue(), rect.getGreen());
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    checkForNulls(ellipse);
    this.results = String.format("<ellipse id=\"%s\" cx=\"%.3f\" cy=\"%.3f\" rx=\"%.3f\" " +
                    "ry=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n</ellipse>",
            ellipse.getName(), ellipse.getX(), ellipse.getY(), ellipse.getWidth(),
            ellipse.getHeight(), ellipse.getRed(), ellipse.getGreen(), ellipse.getBlue());
  }

  @Override
  public String toString() {
    return this.results;
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
}
