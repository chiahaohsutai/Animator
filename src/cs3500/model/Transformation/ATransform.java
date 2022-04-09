package cs3500.model.Transformation;

import cs3500.model.ITransform;

/**
 * Represents a transformation for a given object. A transformation happens in a time interval,
 * and it refers to a change in the field of the object.
 */
public abstract class ATransform implements ITransform {
  protected final int start;
  protected final int end;

  /**
   * Sets the time interval for the transformation.
   *
   * @param start is the start tick of the transformation.
   * @param end is the end time of the transformation.
   */
  protected ATransform(int start, int end) {
    checkValidInterval(start, end);
    this.start = start;
    this.end = end;
  }

  @Override
  public int[] getInterval() {
    return new int[] {start, end};
  }

  /**
   * Checks if the time interval is consistent. This means that the time interval is not negative
   * or if the start time is greater than the end time.
   *
   * @param start is the start of the time interval.
   * @param end is the end of the time interval.
   * @throws IllegalArgumentException if the interval is inconsistent.
   */
  private void checkValidInterval(int start, int end) {
    if (start > end || start < 0) {
      throw new IllegalArgumentException("Invalid tick interval.");
    }
  }
}
