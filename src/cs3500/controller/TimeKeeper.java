package cs3500.controller;

/**
 *
 */
public interface TimeKeeper extends ReadTimeKeeper {
  /**
   *
   */
  void increaseTime();

  /**
   *
   */
  void decreaseTime();

  /**
   *
   */
  void reset();
}
