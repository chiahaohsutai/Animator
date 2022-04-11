package cs3500.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.model.ReadAnimator;
import cs3500.model.transformation.ITransform;

/**
 * Renders a view in SVG style compliant text.
 */
public class SVGView extends TextualView {

  /**
   * Instantiates a text view for the animation.
   *
   * @param animator is the model for the animator.
   * @param output   is the location to switch the view is output to.
   * @throws IllegalArgumentException if any parameters are null.
   */
  public SVGView(ReadAnimator animator, Appendable output) {
    super(animator, output);
  }

  @Override
  public void render() {
    List<String> svg = new ArrayList<>();
    Map<String, List<ITransform>> states = animator.getState();

    if (Objects.isNull(states)) {
      svg.add(setCanvas(animator.getCanvasWidth(), animator.getCanvasHeight()));

      svg.add("</svg>");
      //render(String.join());
    }

    for (String key : states.keySet()) {
      List<ITransform> transforms = states.get(key);
      //Deque<String> animations = renderAnimationTags();
    }
  }


  /**
   * Initializes the svg tag with the canvas height and width.
   *
   * @return the SVG declaration tag.
   */
  private String setCanvas(int width, int height) {
    return String.format("<svg width=\"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "version=\"1.1\"> \n", width, height);
  }

  /**
   * Adds the SVG tags to the output location.
   *
   * @param list is the svg tags in a list format.
   */
  private void render(List<String> list) {
    try { output.append(String.join("\n", list)); }
    catch (IOException e) {
      throw new IllegalStateException("Failed to render the svg.");
    }
  }
}
