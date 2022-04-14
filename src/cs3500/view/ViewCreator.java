package cs3500.view;

import cs3500.model.ReadAnimator;

/**
 * Factory of views for the animator.
 */
public class ViewCreator {
  private final ReadAnimator anim;
  private final Appendable ap;

  /**
   * Creates a creator that will use the given animator.
   * @param anim is the animator that the views will use.
   */
  public ViewCreator(ReadAnimator anim, Appendable ap) {
    this.anim = anim;
    this.ap = ap;
  }

  /**
   * Factory method to produce the different view.
   *
   * @param type is a string that represent the type of the view.
   */
  public IView factory(String type) {
    switch (type) {
      case "visual":
        return new VisualView();
      case "text":
        return new TextView(this.anim, this.ap);
      case "svg":
        return new SVGView(this.anim, this.ap);
      default:
        throw new IllegalArgumentException("Invalid type !");
    }
  }
}
