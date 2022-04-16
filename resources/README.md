# The Easy Animator

The Easy Animator is a GUI created using the Java Swing library and implements the MVC design
pattern. The application creates animations by applying different transformations such as
moving, scaling, changing colors, etc. to 2-D shapes at different time interval. The application
draws the animation described by the text description and is wholly independent of how the
description was produced.

(This is a rough description of what the Easy Animator should be and do at its completion)

### Changes to the Model

The model was changed in several aspects. Most of the changes were driven by the 
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

Previously in Assignment 5, we submitted an incomplete version of the visual view. This assignment, 
we completed the visual view in its entirety and implemented the interactive view.

Implementation details:

The visual view is essentially a GUI that pops up and plays the animation. It is just a single
JPanel layed onto the JFrame. The animation is played in its entirety and dissapears after its 
completion. The window will stay open until it is manually closed. The JPanel essentially grabs
the existing shapes and their current state at given tick and draws it. As the time progresses, 
it keeps drawing the updated states of existing shapes, creating an animation.

The interactive view is much more interesting. The GUI contains many more components. We reused
the prior implementation of the JPanel in the visual view to display the animation. This panel
is placed in the center of the GUI. In addition to the animation panel, we created two more panels,
one to house the buttons and one to house the labels that provides the client with some information
about what is going on. The button panel holds all the interactive elements. We connected these
buttons to the controller by adding action listener, so when one is clicked, the corresponding 
action is performed. In addition, we added additional action listeners to specific buttons
to change the labels to reflect what is going on in the window.

Both of our views contain a manual timekeeper that is synced with the Timer. These clocks are 
meant for internal use and their sole purpose is to determine the shapes and their states at a 
given time. This is used to provide a visual representation of the shapes. These clocks actually
do not move by themselves. Rather, the time of the program is solely handled by the Timer in the
controller. This is discussed later in the controller section.

The main changes to the view are the use of visitors to construct the required views. The view has
a series of different visitors where each visitor behaves according to the view type. We decided to
do this because, we wanted to take advantage of dynamic dispatch and allow Java figure out what
needs to be done based on the object being visited. Hence, we were able to avoid having to check
which type of shape or transform is being worked on while generating the view. 


## The Controller

For this assignment, we finished the implementation of the controller for the visual view and
implemented the controller for the interactive view.

We have two controllers, each specifically paired with one of the visual views. 

The Controller class that implements the BasicController interface is meant to be used with the 
visual view. This controller does not contain many functionalities, all it does is make the GUI 
appear and play the animation until its ending. The animation will end, but the window will remain 
open until it is manually closed. The Controller has access to the view and houses the Timer of the
program. The Timer object ticks after every delay based on the given tick rate and performs a 
repeated action at every tick. This action is essentially to update the clock in the view and to
repaint the entire frame of the view with the updated states.

The InteractiveController class that implements the IInteractiveFeatures interface
is meant to be used with the interactive view. The InteractiveController has access to the view, model, and 
houses the Timer of the program. The Timer object ticks after every delay based on the given tick
rate and performs a repeated action at every tick. This action is essentially to repaint the entire
frame, update the clock in the view, and handle actions in the GUI as time passes. 
The InteractiveController provides the view with functionality and handles all the button presses
by the client. This includes the play/pause, restart, enable/disable looping, increase speed,
decrease speed, and exit buttons.


