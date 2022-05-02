package cs3500.view.textualviews.svg;

import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Represent the SVG animation tags for a Plus shape.
 */
public class SVGPlusTransform extends ASVGShapeTransform {

  /**
   * Creates the SVG tag according to the given tick rate.
   *
   * @param tickRate of the animations in ticks per second.
   */
  protected SVGPlusTransform(int tickRate) {
    super(tickRate);
  }

  @Override
  public void visitPosition(PositionTransform t) {

  }

  @Override
  public void visitScale(ScaleTransform t) {

  }
}
