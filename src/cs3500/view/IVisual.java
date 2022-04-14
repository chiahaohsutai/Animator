package cs3500.view;

import cs3500.controller.IController;

/**
 * Represents a visual view of an animation. Sets the functionality of window.
 */
public interface IVisual extends IView {

  /**
   *
   */
  void makeVisible();

  /**
   *
   */
  void incrementCurrentTick();
}