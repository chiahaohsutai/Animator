package cs3500.view.visualview;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import cs3500.controller.ReadTimeKeeper;
import cs3500.model.ReadAnimator;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;

/**
 * Represents a panel in which the animation is being played. It grabs the state of the existing
 * shapes at a given tick and draws them.
 */
public class VisualViewPanel extends JPanel {
  private final ReadAnimator model;
  private final ReadTimeKeeper clock;

  /**
   * Draws the current state of the animation according to the current tick.
   * @param model is the animator model with all the states of the animation.
   */
  public VisualViewPanel(ReadAnimator model, ReadTimeKeeper clock) {
    super();
    this.model = model;
    this.clock = clock;
    this.setBackground(Color.white);
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    List<IShape> shapesToDraw = model.calculateStatesAtTick(clock.getTime());
    ISVisitor visualShapeVisitor = new VisualShapeVisitor(g2);
    for (IShape singleShape : shapesToDraw) {
      singleShape.visitor(visualShapeVisitor);
    }
  }
}
