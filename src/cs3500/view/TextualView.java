package cs3500.view;

import java.util.Objects;

import cs3500.model.ReadAnimator;

/**
 * Represents views that are textual or consists of strings.
 */
public abstract class TextualView implements IView {
  protected final ReadAnimator animator;
  protected final Appendable output;

  /**
   * Instantiates a text view for the animation.
   *
   * @param animator is the model for the animator.
   * @param output is the location to switch the view is output to.
   * @throws IllegalArgumentException if any parameters are null.
   */
  protected TextualView(ReadAnimator animator, Appendable output) {
    if (Objects.isNull(animator) || Objects.isNull(output)) {
      throw new IllegalArgumentException("The parameters cannot be null");
    }
    this.animator = animator;
    this.output = output;
  }

  @Override
  public abstract void render();
}
