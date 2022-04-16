package cs3500.controller;

import cs3500.view.IInteractiveVisual;

/**
 * Represent a controller for an interactive GUI for an animation. The controller has the
 * ability to stop, reset, start, increase/decrease tick rate, pause and resume the animation.
 * It also allows for looping to be disabled or enabled.
 */
public interface IInteractiveFeatures extends IBasicController {

  /**
   * Sets the view for the controller.
   *
   * @param view is the view for the controller.
   */
  void setView(IInteractiveVisual view);

  /**
   *
   */
  void pauseResume();

  /**
   *
   */
  void restart();

  /**
   *
   */
  void looping();

  /**
   *
   */
  void increaseSpeed();

  /**
   *
   */
  void decreaseSpeed();

  /**
   *
   */
  void exitProgram();
}
