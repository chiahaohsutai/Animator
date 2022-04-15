package cs3500.view;

/**
 *
 */
public interface IInteractiveFeatures extends IVisual {
  /**
   *
   */
  void startAnimation();

  /**
   *
   */
  void pause();

  /**
   *
   */
  void resume();

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
}
