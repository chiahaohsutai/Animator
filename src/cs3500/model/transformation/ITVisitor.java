package cs3500.model.transformation;

/**
 * Visitor which adds extra functionality to classes thought the use a visitor.
 * The visitor aims to visit the shape in an animation.
 */
public interface ITVisitor {
  /**
   *
   * @param t
   */
  void visitColor(ColorTransform t);

  /**
   *
   * @param t
   */
  void visitPosition(PositionTransform t);

  /**
   *
   * @param t
   */
  void visitScale(ScaleTransform t);
}
