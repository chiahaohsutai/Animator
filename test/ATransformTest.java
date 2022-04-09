import org.junit.Test;

import cs3500.model.Transformation.ColorTransform;
import cs3500.model.Transformation.ITransform;
import cs3500.model.Transformation.PositionTransform;
import cs3500.model.Transformation.ScaleTransform;
import cs3500.model.Transformation.TransformType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class ATransformTest {

  @Test
  public void testGetTypePosition() {
    ITransform t = new PositionTransform(0, 1, 10, 10);
    assertEquals(TransformType.POSITION, t.getType());
  }

  @Test
  public void testGetTypeColor() {
    ITransform t = new ColorTransform(0, 1, 10, 10, 10);
    assertEquals(TransformType.COLOR, t.getType());
  }

  @Test
  public void testGetTypeScale() {
    ITransform t = new ScaleTransform(0, 1, 10, 10);
    assertEquals(TransformType.SCALE, t.getType());
  }

  @Test
  public void testGetDataPosition() {
    ITransform t = new PositionTransform(0, 1, 10, 10);
    double[] result = new double[] {10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetDataColor() {
    ITransform t = new ColorTransform(0, 1, 10, 10, 10);
    double[] result = new double[] {10, 10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetDataScale() {
    ITransform t = new ScaleTransform(0, 1, 10, 10);
    double[] result = new double[] {10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetIntervalPosition() {
    ITransform t = new PositionTransform(0, 1, 10, 10);
    int[] result = new int[] {0, 1};
    assertArrayEquals(result, t.getInterval());
  }

  @Test
  public void testGetIntervalColor() {
    ITransform t = new ColorTransform(0, 1, 10, 10, 10);
    int[] result = new int[] {0, 1};
    assertArrayEquals(result, t.getInterval());
  }

  @Test
  public void testGetIntervalScale() {
    ITransform t = new ScaleTransform(0, 1, 10, 10);
    int[] result = new int[] {0, 1};
    assertArrayEquals(result, t.getInterval());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeColor() {
    ITransform t = new ColorTransform(-1, 1, 1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimePosition() {
    ITransform t = new PositionTransform(-1, 1, 1,1 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeScale() {
    ITransform t = new ScaleTransform(-1, 1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndScale() {
    ITransform t = new ScaleTransform(1, -1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndColor() {
    ITransform t = new ColorTransform(1, -1, 1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndPosition() {
    ITransform t = new PositionTransform(1, -1, 1,1 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthScale() {
    ITransform t = new ScaleTransform(1, 1, -1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHeightScale() {
    ITransform t = new ScaleTransform(1, 1, 1,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorR() {
    ITransform t = new ColorTransform(1, 1, -1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorG() {
    ITransform t = new ColorTransform(1, 1, 1,-1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorB() {
    ITransform t = new ColorTransform(1, 1, 1,1 ,-1);
  }
}