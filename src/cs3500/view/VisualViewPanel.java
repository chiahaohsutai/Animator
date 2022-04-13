package cs3500.view;

import java.awt.*;

import javax.swing.*;

import cs3500.model.ReadAnimator;

/**
 *
 */
public class VisualViewPanel extends JPanel {
  private final ReadAnimator readOnlyModel;
  private int currentTick;

  /**
   *
   * @param readOnlyModel
   */
  public VisualViewPanel(ReadAnimator readOnlyModel) {
    super();

    this.readOnlyModel = readOnlyModel;
    this.setBackground(Color.white);
    this.currentTick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

  }
}
