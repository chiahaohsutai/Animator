package cs3500.controller;

/**
 *
 */
public class Clock implements TimeKeeper {
  private int currentTick;

  /**
   *
   */
  public Clock() {
    this.currentTick = 0;
  }

  @Override
  public int getTime() {
    return currentTick;
  }

  @Override
  public void increaseTime() {
    this.currentTick += 1;
  }

  @Override
  public void decreaseTime() {
    this.currentTick -= 1;
  }

  @Override
  public void reset() {
    this.currentTick = 0;
  }
}
