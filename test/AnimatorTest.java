import org.junit.Test;

import java.util.Objects;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.Shape.IShape;
import cs3500.model.Shape.Rect;

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

  }
}
