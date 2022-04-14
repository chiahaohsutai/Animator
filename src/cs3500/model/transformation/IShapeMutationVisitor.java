package cs3500.model.transformation;

import cs3500.model.shape.IShape;

/**
 *
 */
public interface IShapeMutationVisitor {
  /**
   *
   * @param t
   * @return
   */
  IShape visitColor(ColorTransform t);

  /**
   *
   * @param t
   * @return
   */
  IShape visitPosition(PositionTransform t);

  /**
   *
   * @param t
   * @return
   */
  IShape visitScale(ScaleTransform t);
}
