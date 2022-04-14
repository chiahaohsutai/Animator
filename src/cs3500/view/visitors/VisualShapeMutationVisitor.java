package cs3500.view.visitors;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.IShapeMutationVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 *
 */
public class VisualShapeMutationVisitor implements IShapeMutationVisitor {
  private final IShape shapeToTransform;
  private int currentTick;

  /**
   *
   * @param shapeToTransform
   * @param currentTick
   */
  public VisualShapeMutationVisitor(IShape shapeToTransform, int currentTick) {
    this.shapeToTransform = shapeToTransform;
    this.currentTick = currentTick;
  }
  @Override
  public IShape visitAndApplyColorTransform(ColorTransform t) {
    double[] beginColor = t.getOldData();
    double[] endColor = t.getData();

    double redAtTick = getUpdatedState(t, currentTick, beginColor[1], endColor[1]);
    double greenAtTick = getUpdatedState(t, currentTick, beginColor[2], endColor[2]);
    double blueAtTick = getUpdatedState(t, currentTick, beginColor[3], endColor[3]);

    shapeToTransform.setColor((int)redAtTick, (int)greenAtTick, (int)blueAtTick);

    return shapeToTransform;
  }

  @Override
  public IShape visitAndApplyPositionTransform(PositionTransform t) {
    double[] beginPosition = t.getOldData();
    double[] endPosition = t.getData();

    double xAtTick = getUpdatedState(t, currentTick, beginPosition[1], endPosition[1]);
    double yAtTick = getUpdatedState(t, currentTick, beginPosition[2], endPosition[2]);

    shapeToTransform.move(xAtTick, yAtTick);

    return shapeToTransform;
  }

  @Override
  public IShape visitAndApplyScaleTransform(ScaleTransform t) {
    double[] beginSize = t.getOldData();
    double[] endSize = t.getData();

    double widthAtTick = getUpdatedState(t, currentTick, beginSize[1], endSize[1]);
    double heightAtTick = getUpdatedState(t, currentTick, beginSize[2], endSize[2]);

    shapeToTransform.reScale(widthAtTick, heightAtTick);

    return shapeToTransform;
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
    return (beginState * ((t.getEnd() - currentTick) / (double)(t.getEnd() - t.getStart())) +
            (endState * ((currentTick - t.getStart()) / (double)(t.getEnd() - t.getStart()))));
  }
}
