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

To accommodate the plus shape in our SVG view, we simply added a new visitor 
(SVGPlusTransform.java), which handles creating SVG animate tags for the plus sign. This class
implements the visitor interface for transforms, hence, using this visitor we are able 
to execute the moving, scaling, and changing colors of the plus shape. Since we used the
visitor pattern, the class (SVGView.java) that creates the entire SVG had no modifications.

Our Text view required no changes because the existing implementation prints the ID of the
shape along with the shape's animations. It does not need to know what type of shape, therefore,
it works with any shape.

To accommodate the plus shape in our visual views, 


### To implement Level 2 features

### To implement Level 3 features

## Changes to the View...

### To implement Level 1 features

### To implement Level 2 features

### To implement Level 3 features

## Changes to the Controller....

### To implement Level 1 features

### To implement Level 2 features

### To implement Level 3 features
