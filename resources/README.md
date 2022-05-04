# Assignment 8: The Easy Animator Extra Credit

## New Features Implemented

- Level 1
  - Add the plus sign shape to an animation
  - Animate the plus sign shape (move, scale, change colors) in an animation
  - Toggle between fill and outline modes in interactive view
- Level 2
  - View discrete frames of an animation (along with existing capabilities) in interactive view
  - Toggle between discrete and continuous playing of animation in interactive view
- Level 3
  - Process slow-motion information from an input file using existing file reader
    - Slow-motion information described as time intervals in which animation is slowed down
    - Specified as "tempo tickrate (slow-motion tempo) from (start of interval) to (end of interval)"
  - Play slow-motion animation in specified ranges and play in "original" speed in other parts of 
  animation in interactive view

## Changes to the Model...

### To implement Level 1 features

When populating the model given a text file, we added the ability for the file 
reader (AnimationFileReader.java) to add the plus shape by adding a case in the main switch 
statement. We followed the same existing logic and format as the other shapes to retrieve the 
information describing a plus shape. In the builder (Builder.java) we added methods to account for 
the new case of adding a plus shape.

We added the ability to add the plus shape by making a new class (Plus.java) that implements the
existing shape interface. The fields of a plus shape are the same as the existing shapes. 
Therefore, the plus shape extends the existing abstract class for shapes. We also added
a method to the visitor interface to handle the plus shape (our design is centered around the 
visitor design pattern to delegate behavior and functionality based on the shape).

We added the outline and fill modes by creating a flag in the model. This flag tracks whether the 
shapes should be fill or unfilled. This flag can then be passed onto the view where the view tells
the JPanel whether to draw filled or unfilled shapes. 

### To implement Level 2 features

We added the ability to display the discrete frames by getting all the existing frames from the 
model. We obtained the frames by creating a method that iterates through the 
existing transformations and gets all the start and end ticks. In the method, the ticks are stored
in a list and any repeated values are removed. Moreover, the list gets sorted by increasing order
of tick value. We sort the list in order to facilitate its traversal in the controller.

### To implement Level 3 features

For this level, we decided to edit the AnimatorFileReader in order to capture slo-mo or tempo
information. The implemented this capability by adding a switch case which reads slo-mo 
information. This is achieved by using the same logic/format as the existing switch cases. This
means that we also added a new method in the TweenModelBuilder which adds slo-mo or tempo to the
model. The TweenModelBuilder implementation (Builder.java) then passes the parse information onto
the model.

In the model we defined two new methods in order to capture the slo-mo feature. The first method
accepts the slo-mo information and the second outputs the information. The model stores the slo-mo
information by using a private class called TempoInfo. More specifically, the model stores the 
slo-mo intervals in a list of TempoInfo. The purpose of the TempoInfo class is to store the tempo
intervals and the tick rate for the interval. We decided to design these class in order to 
facilitate the use of the slo-mo information.

## Changes to the View...

### To implement Level 1 features

To accommodate the plus shape in our SVG view, we simply added a new visitor
(SVGPlusTransform.java), which handles creating SVG animate tags for the plus sign. This class
implements the visitor interface for transforms, hence, using this visitor we are able
to execute the moving, scaling, and changing colors of the plus shape. Since we used the
visitor pattern, the class (SVGView.java) that creates the entire SVG had no modifications.

Our Text view required no changes because the existing implementation prints the ID of the
shape along with the shape's animations. It does not need to know what type of shape, therefore,
it works with any shape.

To accommodate the plus shape in our visual views (interactive and visual), we implemented a new 
method in the VisualShapeVisitor class which draws the plus shape. Our visual views delegate 
the task of drawing a shape to the VisualShapeVisitor class; hence, this allowed us to add the 
plus shape by making changes to the interface and visitor class. Moreover, it allowed us to 
keep the existing JPanel (VisualViewPanel.java) and visual views unchanged. The shape itself was
drawn using Java's polygon class and a helper method which calculates the coordinate points of the
polygon.

We added the fill/outline modes to the view by passing a flag to the visitor that draws the 
shapes (VisualShapeVisitor.java). If the flag is true, the shapes get filled and if the flag is
false the shape are not filled. Moreover, we added a button in the JFrame that turns the mode on
off and a JLabel that displays the state (fill mode on or off). 

### To implement Level 2 features

For this level we added a button that responds to the user and changes between discrete and 
non-discrete playing. The button calls a method in the controller, which then sets the frames
that should be played by the view. Additionally, the button will update the JLabel that displays 
whether the mode is on or off.

### To implement Level 3 features

There were no changes to the view implementations for this level.

## Changes to the Controller....

### To implement Level 1 features

We added a method that responds to the fill/outline button in the JFrame. This method mutates the 
model and sets the fill/outline flag. If the flag is false, it changed to true and vice-versa. 

### To implement Level 2 features

We added a flag in the controller that responds to the JFrame button for discrete playing. This
flag is turn on and off (switched between true and false) through the use of a method in the
controller. This flag then determines the methods that are executed by the Timer class. If the flag
is true (discrete playing is on), then the Timer object will tell the view to only display the 
frames in which a motion (move, scale, color change) is either starting or ending. If the mode is
turned off the controller tells the view to play normally starting from where the discrete mode 
left off. The controller gets these "frames" (frames where a motion start or ends) by calling
a method in the model which returns the ticks at which a motion starts or ends. 

### To implement Level 3 features

We added this feature getting the slo-mo intervals from the model and responding to the intervals
in the Timer object we used to move the animation. When the Timer tries to move the animation 
through an ActionEvent, we first check whether the current tick is the start of a slo-mo interval.
If the current tick is part of the interval, we set the Timer's delay to a slower tempo 
(which can be specified by the user in the txt file for the animation) and we keep this tempo until
we get to the end of the interval. Then the Timer's delay is set to the original tick rate 
(the tick rate before the slo-mo frames happened). The animation will keep checking for these 
intervals until the end of the animation.