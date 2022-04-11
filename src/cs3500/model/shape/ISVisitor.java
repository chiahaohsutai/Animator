package cs3500.model.shape;

/**
 * Visitor interface to add functionality to the shapes.
 */
public interface ISVisitor {

  /**
   * Visits the React Class and executes functionality according to the visitor.
   *
   * @param rect is the rectangle that will be visited.
   * @throws IllegalArgumentException if the shape is null.
   */
  void visitRect(Rect rect);

  /**
   * Visits the Ellipse Class and executes functionality according to the visitor.
   *
   * @param ellipse is the ellipse being visited.
   * @throws IllegalArgumentException if the shape is null.
   */
  void visitEllipse(Ellipse ellipse);
}
