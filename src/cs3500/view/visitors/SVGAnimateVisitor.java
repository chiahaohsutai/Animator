package cs3500.view.visitors;

import java.util.Arrays;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Rect;

/**
 * To resent the different animation tags attributes for each shape. The string goes in order
 * (posX, posY, width, height).
 */
public class SVGAnimateVisitor implements ISVisitor {
  private String tags;

  @Override
  public void visitRect(Rect rect) {
    tags = String.join(" ", Arrays.asList("x", "y", "width", "height"));
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    tags = String.join(" ", Arrays.asList("cx", "cy", "rx", "ry"));
  }

  @Override
  public String toString() {
    return this.tags;
  }
}
