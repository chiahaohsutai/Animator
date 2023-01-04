## Animator

### Project Description:

The project focuses on the use of the MVC (Model, View Controller) pattern to design the framework of the application. The model focuses
on the data structure behind the storage and retrieval for each movement and object in the animation, while the controller and view focus on providing the user with a visual
view of the animation and likewise control over the state of the animation. The visual view is achieved using a GUI, which also contains a set of controls. These
controls allow the user to pause, speed up, slow down, reset or restart the animation at any point. The project also utilizes a visitor pattern, which allowed the team
to maintain flexibility and extensibility. 

Please find the code in the src folder. 

### Run the project?

To run the project, run the Main.java file and where the command line argument should be of the following type: <p>
-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

- name-of-animation-file: text file containing the animation description.
- view: type of view to output (text, visual, SVG).
- out: location where the application will write the animation to (txt file).
- speed: is the speed of the animation (needs to be positive).

(There are some sample text files that can be used to run the application, you may find the files in the resources folder)
![image](https://user-images.githubusercontent.com/89400862/210667377-3040c8bd-d65d-4ee4-a150-734d9128ac9e.png)
