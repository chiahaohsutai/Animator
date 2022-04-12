package cs3500.view;

import javax.swing.*;

import cs3500.model.Animator;
import cs3500.model.ReadAnimator;

/**
 *
 */
public class VisualView extends JFrame implements IView {
  private final VisualViewPanel panel;
  private final ReadAnimator readOnlyModel;

  /**
   *
   */
  public VisualView() {
    super();

    this.readOnlyModel = new Animator();

    this.setSize(readOnlyModel.getCanvasWidth(), readOnlyModel.getCanvasHeight());

    this.panel = new VisualViewPanel(readOnlyModel);
  }

  @Override
  public void render() {

  }
}
