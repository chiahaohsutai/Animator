import org.junit.Test;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Rect;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.view.visitors.SVGAnimateVisitor;
import cs3500.view.visitors.SVGShapeVisitor;
import cs3500.view.visitors.SVGTransformVisitor;
import cs3500.view.visitors.TextVisitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Class to test the functionality of the different visitors for the views.
 */
public class VisitorsTest {

  ////// SVGAnimatorVisitor //////

  @Test
  public void visitSVGAnimVisit() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ISVisitor visitor = new SVGAnimateVisitor();
    assertNull(visitor.toString());
    rect.visitor(visitor);
    assertEquals("x y width height", visitor.toString());
    ellipse.visitor(visitor);
    assertEquals("cx cy rx ry", visitor.toString());
  }

  ////// SVGShapeVisitor //////

  @Test
  public void visitSVGShapeVisitor() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ISVisitor visitor = new SVGShapeVisitor();
    assertNull(visitor.toString());
    rect.visitor(visitor);
    assertEquals("<rect id=\"null\" x=\"1.000\" y=\"20.000\" width=\"10.000\" " +
            "height=\"10.000\" fill=\"rgb(10,10,10)\" visibility=\"hidden\">\n" +
            "</rect>", visitor.toString());
    ellipse.visitor(visitor);
    assertEquals("<ellipse id=\"null\" cx=\"1.000\" cy=\"30.000\" rx=\"10.000\" " +
            "ry=\"10.000\" fill=\"rgb(12,12,30)\" visibility=\"hidden\">\n" +
            "</ellipse>", visitor.toString());
  }

  ////// SVGTransformVisitor //////

  @Test
  public void visitSVGTransformVisitorPos() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ITVisitor visitRect = new SVGTransformVisitor(10, rect);
    ITVisitor visitEllipse = new SVGTransformVisitor(10, ellipse);
    ITransform colorT = new ColorTransform(10, 11, 1, 1, 1, 20,
            30, 50);
    ITransform scaleT = new ScaleTransform(10, 10, 10, 4,
            30, 5);
    ITransform posT = new PositionTransform(10, 10, 10, 30, -10, 5);

    assertNull(visitEllipse.toString());
    assertNull(visitRect.toString());
    posT.visitor(visitEllipse);
    assertEquals("<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
            "attributeName=\"cx\" from=\"10.000\" to=\"-10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
            "attributeName=\"cy\" from=\"30.000\" to=\"5.000\"" +
            " fill=\"freeze\"/>", visitEllipse.toString());
    posT.visitor(visitRect);
    assertEquals("<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\"" +
                    " attributeName=\"x\" from=\"10.000\" to=\"-10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
                    "attributeName=\"y\" from=\"30.000\" to=\"5.000\" fill=\"freeze\"/>",
            visitRect.toString());
  }

  @Test
  public void visitSVGTransformVisitorColor() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ITVisitor visitRect = new SVGTransformVisitor(10, rect);
    ITVisitor visitEllipse = new SVGTransformVisitor(10, ellipse);
    ITransform colorT = new ColorTransform(10, 11, 1, 1, 1, 20,
            30, 50);
    ITransform scaleT = new ScaleTransform(10, 10, 10, 4,
            30, 5);

    assertNull(visitEllipse.toString());
    assertNull(visitRect.toString());
    colorT.visitor(visitEllipse);
    assertEquals("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
            "from=\"rgb(1.000,1.000,1.000)\" to=\"rgb(20.000,30.000,50.000)\" begin=\"1.000s\" " +
            "dur=\"0.100s\" fill=\"freeze\"/>", visitEllipse.toString());
    colorT.visitor(visitRect);
    assertEquals("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
            "from=\"rgb(1.000,1.000,1.000)\" to=\"rgb(20.000,30.000,50.000)\" " +
            "begin=\"1.000s\" dur=\"0.100s\" fill=\"freeze\"/>", visitRect.toString());
  }

  @Test
  public void visitSVGTransformVisitScale() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ITVisitor visitRect = new SVGTransformVisitor(10, rect);
    ITVisitor visitEllipse = new SVGTransformVisitor(10, ellipse);
    ITransform scaleT = new ScaleTransform(10, 10, 10, 4,
            30, 5);
    assertNull(visitEllipse.toString());
    assertNull(visitRect.toString());
    scaleT.visitor(visitEllipse);
    assertEquals("<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
                    "attributeName=\"rx\" from=\"10.000\" to=\"30.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
                    "attributeName=\"ry\" from=\"4.000\" to=\"5.000\" fill=\"freeze\"/>",
            visitEllipse.toString());
    scaleT.visitor(visitRect);
    assertEquals("<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
                    "attributeName=\"width\" from=\"10.000\" to=\"30.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"1.000s\" dur=\"0.000s\" " +
                    "attributeName=\"height\" from=\"4.000\" to=\"5.000\" fill=\"freeze\"/>",
            visitRect.toString());
  }

  ////// TextViewVisitor //////

  @Test
  public void textViewVisitor() {
    ITransform scale = new ScaleTransform(10, 12, 10, 10,
            15, 20);
    ITransform pos = new PositionTransform(10, 30, 40, 10, 30, 60);
    ITransform color = new ColorTransform(10, 30, 1, 3, 5,
            12, 13, 15);
    ITVisitor text = new TextVisitor();

    assertNull(text.toString());
    scale.visitor(text);
    assertEquals("Re-scaled from (10.000,10.000) to (15.000,20.000).", text.toString());
    pos.visitor(text);
    assertEquals("Moves from (40.000,10.000) to (30.000,60.000).", text.toString());
    color.visitor(text);
    assertEquals("Color changes from rgb=[1.000,3.000,5.000] to " +
            "rgb=[12.000,13.000,15.000].", text.toString());
  }
}
