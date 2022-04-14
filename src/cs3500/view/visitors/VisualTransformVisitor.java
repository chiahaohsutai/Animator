package cs3500.view.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 *
 */
public class VisualTransformVisitor implements ITVisitor {
  private Map<Integer, List<IShape>> mostRecentStateOfShapesAtTick;
  private IShape shapeToTransform;
  private int currentTick;

  /**
   *
   * @param shapeToTransform
   */
  public VisualTransformVisitor(Map<Integer, List<IShape>> mostRecentStateOfShapesAtTick,
                                IShape shapeToTransform, int currentTick) {
    this.mostRecentStateOfShapesAtTick = mostRecentStateOfShapesAtTick;
    this.shapeToTransform = shapeToTransform;
    this.currentTick = currentTick;
  }

  @Override
  public void visitColor(ColorTransform t) {
    double[] oldColor = t.getOldData();
    double[] newColor = t.getData();

    double redValueAtTick = getUpdatedState(t, currentTick, oldColor[1], newColor[1]);
    double greenValueAtTick = getUpdatedState(t, currentTick, oldColor[2], newColor[2]);
    double blueValueAtTick = getUpdatedState(t, currentTick, oldColor[3], newColor[3]);

    int indexOfShapeToTransform = getIndexOfShape(shapeToTransform);

    shapeToTransform.setColor((int)redValueAtTick, (int)greenValueAtTick, (int)blueValueAtTick);

    mostRecentStateOfShapesAtTick.get(currentTick).set(indexOfShapeToTransform, shapeToTransform);
  }

  @Override
  public void visitPosition(PositionTransform t) {
    double[] beginPosition = t.getOldData();
    double[] endPosition = t.getData();

    double xValueAtTick = getUpdatedState(t, currentTick, beginPosition[1], endPosition[1]);
    double yValueAtTick = getUpdatedState(t, currentTick, beginPosition[2], endPosition[2]);

    int indexOfShapeToTransform = getIndexOfShape(shapeToTransform);

    shapeToTransform.move((int)xValueAtTick, (int)yValueAtTick);

    mostRecentStateOfShapesAtTick.get(currentTick).set(indexOfShapeToTransform, shapeToTransform);
  }

  @Override
  public void visitScale(ScaleTransform t) {
    double[] beginSize = t.getOldData();
    double[] endSize = t.getData();

    double widthAtTick = getUpdatedState(t, currentTick, beginSize[1], endSize[1]);
    double heightAtTick = getUpdatedState(t, currentTick, beginSize[2], endSize[2]);

    int indexOfShapeToTransform = getIndexOfShape(shapeToTransform);

    shapeToTransform.reScale(widthAtTick, heightAtTick);

    mostRecentStateOfShapesAtTick.get(currentTick).set(indexOfShapeToTransform, shapeToTransform);
  }

  /**
   *
   * @param shapeToTransform
   * @return
   */
  private int getIndexOfShape(IShape shapeToTransform) {
    return mostRecentStateOfShapesAtTick.get(currentTick)
            .indexOf(shapeToTransform);
  }

  /**
   *
   * @param t
   * @param currentTick
   * @param beginState
   * @param endState
   * @return
   */
  private double getUpdatedState(ITransform t, int currentTick, double beginState,
                                 double endState) {
    return beginState * ((t.getEnd() - currentTick) / (double)t.getEnd() - t.getStart()) +
            endState * ((currentTick - t.getStart() / (double)t.getEnd() - t.getStart()));
  }
}
