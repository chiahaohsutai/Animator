package cs3500.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import cs3500.model.Animator;
import cs3500.model.ReadAnimator;

/**
 * Represents a graphical view of an animation.
 */
public class VisualView extends JFrame implements IVisual {
  private final VisualViewPanel panel;
  private final ReadAnimator model;

  /**
   * Represents the window that holds the animation. The animation is played in this window.
   */
  public VisualView() {
    super();
    this.setTitle("The Easy Animator Visual View");
    this.setLayout(new BorderLayout());
    model = new Animator();
    panel = new VisualViewPanel(model);

    panel.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    this.add(panel, BorderLayout.CENTER);

    // format the window.
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void render() {
    panel.repaint();
  }


}
