package cs3500.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.ReadAnimator;

/**
 * Represents a visual view of an animation. The visual view is displayed in a GUI on the local
 * machine.
 */
public class VisualView extends JFrame implements IVisual {
  private final VisualViewPanel panel;
  private final ReadAnimator model;
  private int currentTick;

  /**
   * Constructs the window that holds the animation. The animation is played in this window.
   */
  public VisualView(ReadAnimator model) {
    super();
    this.currentTick = 0;

    this.setTitle("The Easy Animator Visual View");
    this.setLayout(new BorderLayout());
    this.model = model;
    panel = new VisualViewPanel(model, currentTick);

    panel.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    this.add(panel);

    // format the window.
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void render() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void incrementCurrentTick() {
    currentTick += 1;
  }
}
