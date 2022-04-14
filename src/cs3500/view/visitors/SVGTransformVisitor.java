package cs3500.view.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Visitor for a transform. The visitor puts the transformation in SVG format.
 */
public class SVGTransformVisitor implements ITVisitor {
  private static final String template = "<animate attributeType=\"xml\" begin=\"%.3fs\" " +
          "dur=\"%.3fs\" attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>";
  private String animate;
  private final int tickRate;
  private final IShape shape;

  /**
   * Instantiates the visitor with the tick rate of the animation.
   *
   * @param tickRate is the tick rate of the animation.
   */
  public SVGTransformVisitor(int tickRate, IShape shape) {
    this.tickRate = tickRate;
    this.shape = shape;
  }

  @Override
  public void visitColor(ColorTransform t) {
    double duration = getDuration(t.getStart(), t.getEnd());
    double[] oldC = t.getOldData();
    double[] c = t.getData();
    animate = String.format("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
            "from=\"rgb(%.3f,%.3f,%.3f)\" to=\"rgb(%.3f,%.3f,%.3f)\" begin=\"%.3fs\" " +
                    "dur=\"%.3fs\" fill=\"freeze\"/>", oldC[0], oldC[1], oldC[2], c[0], c[1], c[2],
            (double) t.getStart() / tickRate, duration);
  }

  @Override
  public void visitPosition(PositionTransform t) {
    String[] attributes = getAttributes();
    attributes = new String[] {attributes[0], attributes[1]};
    makeTags(t, attributes);
  }

  @Override
  public void visitScale(ScaleTransform t) {
    String[] attributes = Arrays.stream(getAttributes()).skip(2).toArray(String[]::new);
    makeTags(t, attributes);
  }

  @Override
  public String toString() {
    return this.animate;
  }

  /**
   * Makes the animation tag for the given transformation.
   *
   * @param t is the transform to be animated in an SVG tag.
   * @param attributes is the attributes for the animation tag.
   */
  private void makeTags(ITransform t, String[] attributes) {
    double start = (double) t.getStart() / tickRate;
    double duration = getDuration(t.getStart(), t.getEnd());
    double[] old = t.getOldData();
    double[] curr = t.getData();
    List<String> list = new ArrayList<>();
    for (int i = 0; i < attributes.length; i++) {
      list.add(String.format(template, start, duration, attributes[i], old[i], curr[i]));
    }
    animate = String.join("\n", list);
  }

  /**
   * Gets the duration of the time tick interval.
   *
   * @param start is the starting tick.
   * @param end is the ending tick of the interval.
   * @return the duration of the interval in seconds.
   */
  private double getDuration(int start, int end) {
    return ((double) end / tickRate) - ((double) start / tickRate);
  }

  /**
   * Gets the attributes for an animation for the given shape. The format in the array is always
   * (xPos, yPos, dimension1, dimension2).
   *
   * @return the attributes for the transformation for the given shape.
   */
  private String[] getAttributes() {
    ISVisitor visitor = new SVGAnimateVisitor();
    shape.visitor(visitor);
    return visitor.toString().split(" ");
  }
}
