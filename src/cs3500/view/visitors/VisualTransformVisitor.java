package cs3500.view.visitors;

import java.util.List;
import java.util.Map;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
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
    double[] changeTo = t.getData();
    shapeToTransform.setColor((int)changeTo[1], (int)changeTo[2], (int)changeTo[3]);
  }

  @Override
  public void visitPosition(PositionTransform t) {

  }

  @Override
  public void visitScale(ScaleTransform t) {

  }
}
