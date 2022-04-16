import org.junit.Test;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.Rect;
import cs3500.view.IView;
import cs3500.view.SVGView;
import static org.junit.Assert.assertEquals;

/**
 * Class to test the SVG output for different animations and shape types.
 */
public class SVGViewTest {

  @Test
  public void render() {
    IAnimator animator = new Animator();
    animator.setTickRate(2);
    animator.setBounds(100, 100);
    animator.add("rect", new Rect(10, 10, 1, 1, 100, 100, 100),
            0, 20);
    animator.move("rect", 0,2, 20, 10);
    animator.setColor("rect", 1, 3, 130, 10, 200);
    animator.move("rect", 3, 4, 40, 30);
    animator.reScale("rect", 4, 5, 20, 10);

    Appendable output = new StringBuilder();
    IView view = new SVGView(animator, output);
    view.render();
    String expected = "<svg width=\"100\" height=\"100\" xmlns=\"http://www.w3.org/2000/svg\"" +
            " version=\"1.1\">\n" +
            "<rect id=\"rect\" x=\"1.000\" y=\"1.000\" width=\"10.000\" " +
            "height=\"10.000\" fill=\"rgb(100,100,100)\" visibility=\"hidden\">\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" " +
            "to=\"visible\" begin=\"0.000s\" dur=\"10.000s\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.000s\" dur=\"1.000s\" " +
            "attributeName=\"x\" from=\"1.000\" to=\"20.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.000s\" dur=\"1.000s\" " +
            "attributeName=\"y\" from=\"1.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" attributeType=\"CSS\" " +
            "from=\"rgb(100.000,100.000,100.000)\" to=\"rgb(130.000,10.000,200.000)\"" +
            " begin=\"0.500s\" dur=\"1.000s\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1.500s\" dur=\"0.500s\" " +
            "attributeName=\"x\" from=\"20.000\" to=\"40.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1.500s\" dur=\"0.500s\" " +
            "attributeName=\"y\" from=\"10.000\" to=\"30.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"2.000s\" dur=\"0.500s\" " +
            "attributeName=\"width\" from=\"10.000\" to=\"20.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"2.000s\" dur=\"0.500s\" " +
            "attributeName=\"height\" from=\"10.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "</rect>\n" +
            "</svg>";
    assertEquals(expected, output.toString());
  }

  @Test
  public void testTextEllipse() {
    IAnimator animator = new Animator();
    animator.setTickRate(2);
    animator.setBounds(100, 100);
    animator.add("ellipse", new Ellipse(10, 10, 1, 1, 100, 100, 100),
            0, 20);
    animator.move("ellipse", 0,2, 20, 10);
    animator.setColor("ellipse", 1, 3, 130, 10, 200);
    animator.move("ellipse", 3, 4, 40, 30);
    animator.reScale("ellipse", 4, 5, 20, 10);

    Appendable output = new StringBuilder();
    IView view = new SVGView(animator, output);
    view.render();
    String expected = "<svg width=\"100\" height=\"100\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "version=\"1.1\">\n" +
            "<ellipse id=\"ellipse\" cx=\"1.000\" cy=\"1.000\" rx=\"10.000\" ry=\"10.000\" " +
            "fill=\"rgb(100,100,100)\" visibility=\"hidden\">\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.000s\" dur=\"10.000s\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.000s\" dur=\"1.000s\" " +
            "attributeName=\"cx\" from=\"1.000\" to=\"20.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.000s\" dur=\"1.000s\" " +
            "attributeName=\"cy\" from=\"1.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" attributeType=\"CSS\" " +
            "from=\"rgb(100.000,100.000,100.000)\" to=\"rgb(130.000,10.000,200.000)\" " +
            "begin=\"0.500s\" dur=\"1.000s\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1.500s\" dur=\"0.500s\" " +
            "attributeName=\"cx\" from=\"20.000\" to=\"40.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1.500s\" dur=\"0.500s\" " +
            "attributeName=\"cy\" from=\"10.000\" to=\"30.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"2.000s\" dur=\"0.500s\" " +
            "attributeName=\"rx\" from=\"10.000\" to=\"20.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"2.000s\" dur=\"0.500s\" " +
            "attributeName=\"ry\" from=\"10.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "</svg>";
    assertEquals(expected, output.toString());
  }
}