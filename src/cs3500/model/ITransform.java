package cs3500.model;

import cs3500.model.Transformation.TransformType;

/**
 * Represents a transformation in an animation. A transformation refers to any type of change that
 * occurs to a geometric shape in an animation i.e. a color change, a position change, a scaling
 * change.
 */
public interface ITransform {

  /**
   * Gets the type of transformation the transformation is.
   *
   * @return the type of transformation for the transformation.
   */
  TransformType getType();

  /**
   * Returns the transformation values. Each transformation represents a change in the
   * values/fields of an object, the method returns the values which the object is changing into.
   *
   * @return the transformation values.
   */
  double[] getData();

  /**
   * Gets the interval in which the transformation is happening. The interval is in the
   * notion of ticks.
   *
   * @return the interval in which the transformation happens.
   */
  int[] getInterval();
}
