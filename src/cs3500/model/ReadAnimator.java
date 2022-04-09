package cs3500.model;

import cs3500.model.Shape.IShape;

/**
 * This interface contains all the observable operations of the simple animator. The simple animator
 * creates a simple animation for the motion of simple shapes. Measures the passing of time
 * using the notion of ticks. None of the motions will overlap and adjacent motions must agree on
 * their common endpoint.
 */
public interface ReadAnimator {

  /**
   * Gets the shape with the given id.
   *
   * @param name is the name/id of the shape you want to get.
   * @throws IllegalArgumentException if the id/name doesn't exist.
   * @throws IllegalArgumentException if the given id/name is null.
   */
  IShape getShape(String name);

  /**
   * Returns the tick rate of the model. The tick rate at which the animation plays. The animation
   * will return 1 by default unless the user sets a custom tick rate. 
   *
   * @return the tick rate of the animation.
   */
  int getTickRate();
}
