package cs3500.controller;

import cs3500.controller.IBasicController;

/**
 *
 */
public interface IInteractiveFeatures extends IBasicController {
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
