# cell society
## 4
## Han Zhang, Changmin Shin, Brandon Weiss


This project implements a cellular automata simulator.

### Timeline

 * Start Date: 1/30/2023

 * Finish Date: 2/13/2023

 * Hours Spent: 90



### Attributions

 * Resources used for learning (including AI assistance)
 
 * Resources used directly (including AI assistance)


### Running the Program

 * Main class: Main.java

 * Data files needed: example simulation files in data/Preloaded_Files folder. Also Init.xml in the data folder.

 * Interesting data files: FireEx4.xml, LifeEx3.xml

 * Key/Mouse inputs: Use the mouse to click on the buttons in the UI.



### Notes/Assumptions

 * Assumptions or Simplifications:

 Starts off with a blank Game of Life board. When saving a configuration, undefined parameters get set to 0.

 * Known Bugs: 
 
 Schelling's model of Segregation and the WaTor simulations do not work.

 * Features implemented:

 Randomized start for a simulation. Uploading and saving XML files for configurations. Can pick preloaded XML conifguration files from a drop down menu. Can change the simulation speed with a slider. Can start and stop the simulation, have the simulation take a single step, and clear the board with clicks of buttons in the UI.

 * Features unimplemented: 
 
 No percolation simulation. Unable to have the user click the cells in the grid to change the initial setup.

 * Noteworthy Features:

Runs Conway's Game of Life and the Spreading Fire simulations.


### Assignment Impressions

This was more complicated than expected. Our team certainly had bigger ambitions than could be completed and tested in the alotted time. Some of the design choices feel forced upon us by the instructor with no real reasoning: for example, having a game loop manager separate from Main.java, even though all Main.java does is call the game loop. 

We ran into trouble with integrating the separate parts together while also trying to manage good design principles. This also forced abstractions of the simulation engines and cells which overly complicated dependencies and messed up the logic for the Schelling model. The delay caused by our inability to integrate properly and manage the configuration files meant there was no time to test the logic of the actual simulations until right before the assignment was due.
