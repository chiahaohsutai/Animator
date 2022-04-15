import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import cs3500.animations.SortingAnimations;
import cs3500.animations.Quicksort;
import cs3500.io.AnimationFileReader;
import cs3500.io.Builder;
import cs3500.model.IAnimator;
import cs3500.model.shape.IShape;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test that the algorithm is working and creating the appropriate animation.
 */
public class SortingAnimationsTest {

  @Test
  public void testAnimQuickSort() {
    SortingAnimations quick = new Quicksort();
    quick.setSeed(2);
    quick.create();
    List<IShape> s1 = quick.getUnSortedList();
    List<Integer> greens = s1.stream().map(IShape::getGreen).collect(Collectors.toList());
    boolean condition = greens.get(0) < greens.get(1) && greens.get(1) < greens.get(2) &&
            greens.get(2) < greens.get(3) && greens.get(3) < greens.get(4) &&
            greens.get(4) < greens.get(5) && greens.get(5) < greens.get(5);
    assertFalse(condition);
    List<IShape> s2 = quick.getSortedList();
    for (int i = 0; i < s2.size() - 1; i++) {
      assertTrue(s2.get(i).getGreen() < s2.get(i + 1).getGreen());
    }
  }

  @Test
  public void testAnimQuickSortAnimation() {
    SortingAnimations quick = new Quicksort();
    quick.setSeed(2);
    assertEquals("", quick.toString());
    quick.create();
    String expected = "canvas 260 120\n" +
            "oval name circle0 center-x 30.000 center-y 60.0 x-radius 10.0 y-radius " +
            "10.0 color 0.000000 0.733333 0.000000 from 0 to 103\n" +
            "oval name circle1 center-x 70.000 center-y 60.0 x-radius 10.0 y-radius " +
            "10.0 color 0.000000 0.294118 0.000000 from 0 to 103\n" +
            "oval name circle2 center-x 110.000 center-y 60.0 x-radius 10.0 y-radius " +
            "10.0 color 0.000000 0.901961 0.000000 from 0 to 103\n" +
            "oval name circle3 center-x 150.000 center-y 60.0 x-radius 10.0 y-radius " +
            "10.0 color 0.000000 0.003922 0.000000 from 0 to 103\n" +
            "oval name circle4 center-x 190.000 center-y 60.0 x-radius 10.0 y-radius" +
            " 10.0 color 0.000000 0.498039 0.000000 from 0 to 103\n" +
            "oval name circle5 center-x 230.000 center-y 60.0 x-radius 10.0 y-radius " +
            "10.0 color 0.000000 0.854902 0.000000 from 0 to 103\n" +
            "move name circle2 moveto 110.000 60.000 110.000 80.000 from 5 to 7\n" +
            "move name circle2 moveto 110.000 80.000 190.000 80.000 from 7 to 9\n" +
            "move name circle2 moveto 190.000 80.000 190.000 60.000 from 9 to 11\n" +
            "move name circle2 moveto 190.000 60.000 190.000 80.000 from 75 to 77\n" +
            "move name circle2 moveto 190.000 80.000 230.000 80.000 from 77 to 79\n" +
            "move name circle2 moveto 230.000 80.000 230.000 60.000 from 79 to 81\n" +
            "move name circle3 moveto 150.000 60.000 150.000 40.000 from 26 to 28\n" +
            "move name circle3 moveto 150.000 40.000 150.000 40.000 from 28 to 30\n" +
            "move name circle3 moveto 150.000 40.000 150.000 60.000 from 30 to 32\n" +
            "move name circle3 moveto 30.000 60.000 30.000 80.000 from 33 to 35\n" +
            "move name circle3 moveto 30.000 80.000 30.000 80.000 from 35 to 37\n" +
            "move name circle3 moveto 30.000 80.000 30.000 60.000 from 37 to 39\n" +
            "move name circle3 moveto 30.000 60.000 30.000 40.000 from 40 to 42\n" +
            "move name circle3 moveto 30.000 40.000 30.000 40.000 from 42 to 44\n" +
            "move name circle3 moveto 30.000 40.000 30.000 60.000 from 44 to 46\n" +
            "move name circle0 moveto 30.000 60.000 30.000 80.000 from 19 to 21\n" +
            "move name circle0 moveto 30.000 80.000 150.000 80.000 from 21 to 23\n" +
            "move name circle0 moveto 150.000 80.000 150.000 60.000 from 23 to 25\n" +
            "move name circle1 moveto 70.000 60.000 70.000 80.000 from 47 to 49\n" +
            "move name circle1 moveto 70.000 80.000 70.000 80.000 from 49 to 51\n" +
            "move name circle1 moveto 70.000 80.000 70.000 60.000 from 51 to 53\n" +
            "move name circle1 moveto 70.000 60.000 70.000 40.000 from 54 to 56\n" +
            "move name circle1 moveto 70.000 40.000 70.000 40.000 from 56 to 58\n" +
            "move name circle1 moveto 70.000 40.000 70.000 60.000 from 58 to 60\n" +
            "move name circle4 moveto 190.000 60.000 190.000 40.000 from 12 to 14\n" +
            "move name circle4 moveto 190.000 40.000 190.000 40.000 from 14 to 16\n" +
            "move name circle4 moveto 190.000 40.000 190.000 60.000 from 16 to 18\n" +
            "move name circle4 moveto 110.000 60.000 110.000 80.000 from 61 to 63\n" +
            "move name circle4 moveto 110.000 80.000 110.000 80.000 from 63 to 65\n" +
            "move name circle4 moveto 110.000 80.000 110.000 60.000 from 65 to 67\n" +
            "move name circle4 moveto 110.000 60.000 110.000 40.000 from 68 to 70\n" +
            "move name circle4 moveto 110.000 40.000 110.000 40.000 from 70 to 72\n" +
            "move name circle4 moveto 110.000 40.000 110.000 60.000 from 72 to 74\n" +
            "move name circle5 moveto 230.000 60.000 230.000 40.000 from 82 to 84\n" +
            "move name circle5 moveto 230.000 40.000 230.000 40.000 from 84 to 86\n" +
            "move name circle5 moveto 230.000 40.000 230.000 60.000 from 86 to 88\n" +
            "move name circle5 moveto 190.000 60.000 190.000 80.000 from 89 to 91\n" +
            "move name circle5 moveto 190.000 80.000 190.000 80.000 from 91 to 93\n" +
            "move name circle5 moveto 190.000 80.000 190.000 60.000 from 93 to 95\n" +
            "move name circle5 moveto 190.000 60.000 190.000 40.000 from 96 to 98\n" +
            "move name circle5 moveto 190.000 40.000 190.000 40.000 from 98 to 100\n" +
            "move name circle5 moveto 190.000 40.000 190.000 60.000 from 100 to 102";
    assertEquals(expected, quick.toString());
  }

  @Test
  public void testCompatibleQuickSort() {
    AnimationFileReader reader = new AnimationFileReader();
    SortingAnimations quicksort = new Quicksort();
    quicksort.setSeed(2);
    quicksort.create();
    quicksort.export("quicksort.txt");
    try {
      IAnimator a = reader.readFile("quicksort.txt", new Builder());
      String[] names1 = quicksort.getSortedList().stream().map(IShape::getName).sorted()
              .toArray(String[]::new);
      String[] names2 = a.getShapes().stream().map(IShape::getName).sorted().toArray(String[]::new);
      assertArrayEquals(names1, names2);
    } catch (FileNotFoundException | IllegalStateException | InputMismatchException e) {
      e.printStackTrace();
    }
  }


}
