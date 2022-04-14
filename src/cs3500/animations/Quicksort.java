package cs3500.animations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;

/**
 * Animates the quicksort algorithm that sorts a list. The animation will use a list of size
 * 6 and the "things" being sorted will be shapes. The shapes will be sorted according to the
 * sum of the RGB color. Larger RGB sum means it goes last.
 */
public class Quicksort implements Animation {
  private Random r;
  private final List<String> animation;

  /**
   * Instantiates the quicksort algorithm. Makes a list of 6 shapes, which are place in a line.
   * The shapes RGB combination is picked at random.
   */
  public Quicksort() {
    this.r = new Random();
    this.animation = new ArrayList<>();
    // set the canvas size.
    animation.add("canvas 260 120");
  }

  @Override
  public void export(String location) {
    try {
      FileWriter myWriter = new FileWriter(location);
      myWriter.write(String.join("\n", animation));
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the file.");
    }
  }

  @Override
  public void create() {
    List<IShape> temp = new ArrayList<>();
    List<IShape> unSorted = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      IShape c = new Ellipse(10, 10, 30 + 40 * i, 60,
              r.nextInt(256), r.nextInt(256),  r.nextInt(256));
      unSorted.add(c);
    }
    temp.addAll(unSorted);
    quickSort(unSorted, temp, 0, unSorted.size() - 1);
  }

  /**
   * Sorts the given list using the quicksort algorithm. Sorts according to the sum of the
   * RGB values of the shape.
   *
   * @param source is list that will be sorted using quick sort.
   * @param temp is the list that will be mutated as we traverse the unsorted list.
   * @param lowIdx is the smallest index in the partition.
   * @param highIdx is the highest index in a partition.
   */
  private void quickSort(List<IShape> source, List<IShape> temp, int lowIdx, int highIdx) {
    if (lowIdx >= highIdx) {
      return;
    }
    IShape pivot = source.get(lowIdx);
    int pivotIdx = getPivotIdx(source, temp, lowIdx, highIdx, pivot);
    quickSort(source, temp, lowIdx, pivotIdx);
    quickSort(source, temp,pivotIdx + 1, highIdx);
  }

  /**
   * Gets the index where the pivot should be placed. The index separates the lists into
   * two partitions, where all the elements left of the index are smaller than the pivot and
   * all the elements right of the index are greater than the pivot..
   *
   * @param source is the original list that is being sorted.
   * @param temp is the list that is being sorted as we traverse the original list.
   * @param lowIdx is the lowest index in the partition.
   * @param highIdx is the highest index in the partition.
   * @param pivot is the shape that we are trying to find the index for.
   * @return the index for the pivot.
   */
  private int getPivotIdx(List<IShape> source, List<IShape> temp, int lowIdx,
                          int highIdx, IShape pivot) {
    int currLow = lowIdx;
    int currHigh = highIdx;
    for (int i = lowIdx + 1; i < highIdx; i++) {

    }
    return 0;
  }
}
