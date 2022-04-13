package cs3500.view;

import java.awt.*;

import javax.swing.*;

import cs3500.model.Animator;
import cs3500.model.ReadAnimator;

/**
 *
 */
public class VisualView extends JFrame implements IView {
  private final VisualViewPanel panel;
  private final ReadAnimator readOnlyModel;
  private final int currentTick;

  /**
   *
   */
  public VisualView() {
    super();

    this.readOnlyModel = new Animator();

    this.currentTick = 0;

    // set title of frame
    this.setTitle("The Easy Animator Visual View");

    // set layout of frame
    this.setLayout(new BorderLayout());

    // create view panel where animation is played
    this.panel = new VisualViewPanel(readOnlyModel);
    this.panel.setPreferredSize(new Dimension(readOnlyModel.getCanvasWidth(),
            readOnlyModel.getCanvasHeight()));
    this.add(panel, BorderLayout.NORTH);

    // formatting the frame
    this.pack();

    // closing the frame
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // make frame visible
    this.setVisible(true);
  }

  @Override
  public void render() {
    panel.repaint();
  }
}
