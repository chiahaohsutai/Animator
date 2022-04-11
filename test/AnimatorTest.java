import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Rect;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.TransformType;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AnimatorTest {

  IAnimator animator;

  private void animator() {
    animator = new Animator();
  }

  @Test
  public void testAdd() {
    animator();
    IShape rect = new Rect(10, 10, 1, 1, 2, 2, 2);
    assertNull(animator.getShape("rect"));
    animator.add("rect", rect, 0, 1);
    assertTrue(Objects.nonNull(animator.getShape("rect")));
  }

  @Test
  public void testRemove() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    IShape rect2 = new Rect(10, 10, 1, 1, 10, 10, 10);
    animator.add("rect1", rect1, 0, 1);
    animator.add("rect2", rect2, 0, 1);
    assertTrue(Objects.nonNull(animator.getShape("rect1")));
    assertTrue(Objects.nonNull(animator.getShape("rect2")));
    animator.remove("rect2");
    assertTrue(Objects.nonNull(animator.getShape("rect1")));
    assertNull(animator.getShape("rect2"));
  }

  @Test
  public void testMove() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.move("rect1", 0, 1, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.POSITION, transforms.getType());
    assertArrayEquals(new double[] {10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void reScaleTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.reScale("rect1", 0, 1, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.SCALE, transforms.getType());
    assertArrayEquals(new double[] {10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void setColorTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.setColor("rect1", 0, 1, 10, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.COLOR, transforms.getType());
    assertArrayEquals(new double[] {10, 10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void deleteTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    animator.setColor("rect1", 0, 1, 10, 10, 10);
    Map<String, List<ITransform>> state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.COLOR, transforms.getType());
    assertArrayEquals(new double[] {10, 10, 10}, transforms.getData(), 0.00001);
    animator.deleteTransform("rect1", TransformType.COLOR);
    state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
  }

  @Test
  public void setBoundsTest() {
    animator();
    assertEquals(0, animator.getCanvasHeight());
    assertEquals(0, animator.getCanvasWidth());
    animator.setBounds(100, 100);
    assertEquals(100, animator.getCanvasHeight());
    assertEquals(100, animator.getCanvasWidth());
  }

  @Test
  public void getCanvasHeight() {
    animator();
    assertEquals(0, animator.getCanvasHeight());
  }

  @Test
  public void getCanvasWidth() {
    animator();
    assertEquals(0, animator.getCanvasWidth());
  }

  @Test
  public void setTickRate() {
    animator();
    assertEquals(1, animator.getTickRate());
    animator.setTickRate(100);
    assertEquals(100, animator.getTickRate());
  }

  @Test
  public void getTickRate() {
    animator();
    assertEquals(1, animator.getTickRate());
  }

  @Test
  public void getState() {
    animator();
    assertNull(animator.getState());
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.move("rect1", 3, 10, 10, 10);
    animator.setColor("rect1", 2, 5, 10, 10, 10);
    animator.reScale("rect1", 7, 10, 10, 19);
    List<ITransform> list = animator.getState().get("rect1");
    assertEquals(TransformType.COLOR, list.get(0).getType());
    assertEquals(TransformType.POSITION, list.get(1).getType());
    assertEquals(TransformType.SCALE, list.get(2).getType());
    assertEquals(3, list.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.move("rect1", 3, 10, 10, 10);
    animator.move("rect1", 3, 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void scalingIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.reScale("rect1", 3, 10, 10, 10);
    animator.reScale("rect1", 3, 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.setColor("rect1", 3, 10, 10, 10, 10);
    animator.setColor("rect1", 3, 10, 10, 10, 10);
  }
}
