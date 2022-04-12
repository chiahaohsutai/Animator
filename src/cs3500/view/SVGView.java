package cs3500.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.model.ReadAnimator;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;
import cs3500.view.visitors.SVGShapeVisitor;

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
    svg.add(setCanvas(animator.getCanvasWidth(), animator.getCanvasHeight()));

    // if no transformations happened then just draw the shape at their initial location.
    if (Objects.isNull(states)) {
      ISVisitor visitor = new SVGShapeVisitor();

      // goes through all the shapes and adds them to the canvas.
      for (IShape s : animator.getShapes()) {
        animator.getShape(s.getName()).visitor(visitor);
        String[] shape = visitor.toString().split("\n");
        svg.addAll(Arrays.asList(shape[0], setVisibility(s.getName()), shape[1]));
      }
      // end the svg tag.
      svg.add("</svg>");
      render(svg);
      return;
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
   * Sets the visibility of a shape in an animation.
   *
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if the name is not in the animation or the name is null.
   * @return an SVG tag which sets the visibility attribute of a shape.
   */
  private String setVisibility(String name) {
    double start = (double)animator.getStart(name) / animator.getTickRate();
    double end = (double)animator.getEnd(name) / animator.getTickRate();
    double duration = end - start;
    return String.format("<set attributeName=\"visibility\" " +
            "attributeType=\"CSS\" to=\"visible\" begin=\"%.3fs\" dur=\"%.3fs\" " +
            "fill=\"freeze\"/>", start, duration);
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
