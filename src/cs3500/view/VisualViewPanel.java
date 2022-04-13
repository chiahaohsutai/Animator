package cs3500.view;

import java.awt.*;

import javax.swing.JPanel;

import cs3500.model.ReadAnimator;

/**
 * Represents a
 */
public class VisualViewPanel extends JPanel {
  private final ReadAnimator readOnlyModel;
  private int currentTick;

  /**
   *
   * @param model
   */
  public VisualViewPanel(ReadAnimator model, int currentTick) {
    super();

    this.readOnlyModel = model;
    this.currentTick = currentTick;

    this.setBackground(Color.white);
    this.currentTick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;



  }
}
