# Cell Society Design Plan
### Team 4
### Brandon Weiss, Han Zhang, Changmin Shin


#### Examples

Here is a graphical look at my design:

![This is cool, too bad you can't see it](images/online-shopping-uml-example.png "An initial UI")

made from [a tool that generates UML from existing code](http://staruml.io/).


Here is our amazing UI:

![This is cool, too bad you can't see it](images/29-sketched-ui-wireframe.jpg "An alternate design")

taken from [Brilliant Examples of Sketched UI Wireframes and Mock-Ups](https://onextrapixel.com/40-brilliant-examples-of-sketched-ui-wireframes-and-mock-ups/).


## Overview

The goal of the project is to create a program that can implement as many variations of Cellular Automata as possible, 
and the primary goal is to make it easy to add new kind of rules or variations of Cellular Automata, which is supported
by the Rules, and Cell abstractions. For the primary architecture of the design _____

The program will be based upon a model-view separation. The view will handle construction of the GUI, and 
the model will handle the inputs fed to the view behind the scene, such as the state of cells.


## User Interface

![Image GUI layout](images/GUI_Picture.png).

For Grid, the user will be able to click on individual cells to change the state of the cells.
If the user loads up an empty file or a bad XML file, inside the 
area for Description of Variation it will create a pop-up that will read either "empty file " or "bad XML file". 

If the user saves the current state of the simulation as an XML configuration file, then a pop-up
will appear asking for tags that are missing, which don't come from the program(ex, Author, Name of Configuration, ect). 
If an invalid field is entered for any tag, a pop-up will appear stating the issue. 
After the error pops up, the grid will not run until the grid is cleared. 

![Image Error Pop-up](images/GUI_Error_Pop-up_Picture.png)

While the exact specifics of the error box isn't defined yet, the general appearance will match
the view given in the image above. The discussion we have in our group is whether to display every field
as an input or to have the error message only display fields that are invalid.  

## Configuration File Format

XML file tags

- sim_type 
- config_Name
- author
- description
- width
- init_state
- height
- params

The two example files are listed in the data folder, called Example1.xml and Example2.xml. 

We decided to use mainly XML elements in both data folders, as we didn't see a scenario where a xml attribute would
be preferred over an XML element, due to attributes not being easily mutable, and there wasn't a need
for meta-data, so everything is an element. 

## Design Overview


## Design Details


## Use Cases

- Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
- Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
- Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
- Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
- Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in a data file


## Design Considerations


Design issues: 

The first design issue that we had talk about whether cell should be an abstraction or not:
- Yes: There could be multiple states of cells (ex. Dead, alive, transition, etc.)
- No: If there is only a few number of states than handling it with if statements or case statements should be enough

The second design issue that we had was figuring out whether the Mode and Engine should be two different classes
- Just Mode: The job of updating the game state is inherently intertwined with the rules of the game; it might increase confusion for a reader if we attempt to separate the rules from the action of those rules.
- With Engine: Low possibility of violating SRP since the classes have single roles each for both Mode and Engine. 


## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3
