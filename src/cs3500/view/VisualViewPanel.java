package cs3500.view;

import java.awt.*;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import cs3500.model.ReadAnimator;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;
import cs3500.view.visitors.VisualShapeVisitor;

/**
 * Represents a
 */
public class VisualViewPanel extends JPanel {
  private final ReadAnimator model;
  private int currentTick;

  /**
   *
   * @param model
   */
  public VisualViewPanel(ReadAnimator model, int currentTick) {
    super();

    this.model = model;
    this.currentTick = currentTick;

    this.setBackground(Color.white);
    this.currentTick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    List<IShape> shapesToDraw = model.getShapesAtTick(currentTick);

    //List<ITransform> transformsToDraw = model.getTransformsAtTick(currentTick);

    ISVisitor visualShapeVisitor = new VisualShapeVisitor(g2);

    for (IShape singleShape : shapesToDraw) {
      singleShape.visitor(visualShapeVisitor);
    }
  }
}
