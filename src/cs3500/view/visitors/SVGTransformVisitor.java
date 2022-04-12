package cs3500.view.visitors;

import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Visitor for a transform. The visitor puts the transformation in SVG format.
 */
public class SVGTransformVisitor implements ITVisitor {
  private static final String template = "<animate attributeType=\"xml\" begin=\"%.3fs\" " +
          "dur=\"%.3fs\" attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\" />";
  private String animate;

  @Override
  public void visitColor(ColorTransform t) {
    /*
    String.format("<animate attributeName=\"fill\" attributeType=\"CSS\" from=\"rgb(%d,%d,%d)\" " +
                    "to=\"rgb(%d,%d,%d)\" begin=\"%.3fs\" dur=\"%.3fs\" fill=\"freeze\" /> \n",
            color[0], color[1], color[2], color[3], color[4], color[5], data[0], timeEnd);

     */
  }

  @Override
  public void visitPosition(PositionTransform t) {

  }

  @Override
  public void visitScale(ScaleTransform t) {

  }
}
