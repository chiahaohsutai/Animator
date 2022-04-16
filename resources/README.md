# The Easy Animator

The Easy Animator is a GUI created using the Java Swing library and implements the MVC design
pattern. The application creates animations by applying different transformations such as
moving, scaling, changing colors, etc. to 2-D shapes at different time interval. The application
draws the animation described by the text description and is wholly independent of how the
description was produced.

(This is a rough description of what the Easy Animator should be and do at its completion)

### Changes to the Model

The model was changed in several changed in several aspects. Most of the changes were driven by the 
difficulty of transmitting information from the model to the view and controller. The biggest issue
was that stored the states of the shapes as strings, which lead to the model string manipulations
and string slicing for a lot of operations. Hence, we decided to introduce an object called the 
ITransform that would replace these strings. The ITransform represents a change of state where the
change of state could be a change in position, change in color or change in scale. This object
allowed use to store the information in a cleaner more accessible form, which allowed the model
to pass information to the controller and view more easily. We also added some more methods that
would allow the view to have access to the shapes at a current tick. This helped to render the 
visual view and determine if a shape exists at the given tick. We also decided to change the
signature of several methods. Before, many method signatures would have Java Objects or classes
that we created as parameters, which meant that our implementations had strong coupling between 
certain classes. Hence, we try to reduce this by changing all the signatures to use primitive types
as parameters. 

##### The IShape and the ITransform

Another addition was the use of the visitor pattern for accessing and getting functionality out of
the IShapes and the ITransforms. We decided to use the visitor pattern in order to avoid having
to type check or use enums when trying to determine the behavior of the model. Moreover, it allows
animator to stay more flexible. We can implement a series of different visitors, where each visitor
something different.

### Changes to the View

The main changes to the view are the use of visitors to construct the required views. The view has
a series of different visitors where ach visitor behaves according to the view type. We decided to
do this because, we wanted to take advantage of dynamic dispatch and allow java figure out what
needs to be done based on the object being visited. Hence, we were able to avoid having to check
which type of shape or transform is being worked on while generating the view.

## The Controller


