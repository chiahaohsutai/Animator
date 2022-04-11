package cs3500.view;

/**
 * This interface represents the functionalities of the view component
 * of the simple animator. The client can choose from three possible
 * forms to display animation: a text view that shows a textual description of
 * the animation, an SVG view that is a textual representation compliant with the popular
 * SVG file format, and a visual view that displays the animation in a GUI locally.
 * This interface includes the methods required to generate the different views.
 */
public interface IView {

  /**
   * Renders the animation according to the view implementation.
   *
   * @throws IllegalStateException if the animation fails to be output into the desired
   *     output location.
   */
  void render();
}
