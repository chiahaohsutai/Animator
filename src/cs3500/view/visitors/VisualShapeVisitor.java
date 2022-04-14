package cs3500.view.visitors;

import java.awt.*;

import javax.swing.*;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Rect;

public class VisualShapeVisitor implements ISVisitor {
  private final Graphics2D g2;

  public VisualShapeVisitor(Graphics2D g2) {
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

  }
}
