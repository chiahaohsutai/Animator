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
  IShape visitAndApplyColorTransform(ColorTransform t);

  /**
   *
   * @param t
   * @return
   */
  IShape visitAndApplyPositionTransform(PositionTransform t);

  /**
   *
   * @param t
   * @return
   */
  IShape visitAndApplyScaleTransform(ScaleTransform t);
}
