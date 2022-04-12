package cs3500.model.transformation;

/**
 * Visitor which adds extra functionality to classes thought the use a visitor.
 * The visitor aims to visit the shape in an animation.
 */
public interface ITVisitor {
  void visitColor(ColorTransform t);
  void visitPosition(PositionTransform t);
  void visitScale(ScaleTransform t);
}
