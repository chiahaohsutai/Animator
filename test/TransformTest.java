import org.junit.Test;

import cs3500.model.Transformation.ColorTransform;
import cs3500.model.Transformation.ITransform;
import cs3500.model.Transformation.PositionTransform;
import cs3500.model.Transformation.ScaleTransform;
import cs3500.model.Transformation.TransformType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TransformTest {

  // dummy constructor.
  ITransform constructor;

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
  public void testGetStartPosition() {
    ITransform t = new PositionTransform(0, 1, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetStartColor() {
    ITransform t = new ColorTransform(0, 1, 10, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetStartScale() {
    ITransform t = new ScaleTransform(0, 1, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetEndPosition() {
    ITransform t = new PositionTransform(0, 1, 10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test
  public void testGetEndColor() {
    ITransform t = new ColorTransform(0, 1, 10, 10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test
  public void testGetEndScale() {
    ITransform t = new ScaleTransform(0, 1, 10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeColor() {
    constructor = new ColorTransform(-1, 1, 1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimePosition() {
    constructor = new PositionTransform(-1, 1, 1,1 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeScale() {
    constructor = new ScaleTransform(-1, 1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndScale() {
    constructor = new ScaleTransform(1, -1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndColor() {
    constructor = new ColorTransform(1, -1, 1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndPosition() {
    constructor = new PositionTransform(1, -1, 1,1 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthScale() {
    constructor = new ScaleTransform(1, 1, -1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHeightScale() {
    constructor = new ScaleTransform(1, 1, 1,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorR() {
    constructor = new ColorTransform(1, 1, -1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorG() {
    constructor = new ColorTransform(1, 1, 1,-1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorB() {
    constructor = new ColorTransform(1, 1, 1,1 ,-1);
  }

  @Test
  public void makeCopyColor() {
    ITransform t1 = new ColorTransform(0, 1, 10, 10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }

  @Test
  public void makeCopyScale() {
    ITransform t1 = new ScaleTransform(0, 1, 10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }

  @Test
  public void makeCopyMove() {
    ITransform t1 = new PositionTransform(0, 1, 10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }
}