# Refactoring Lab Discussion
#### Han Zhang, Changmin Shin, Brandon Weiss
#### Team 4


## Principles

### Current Abstractions

#### Abstraction #1: Simulation
* Open/Close

A core algorithm that gets closed is the CountNeighborsAlgorithm. It doesn't matter what kind of simulation
it is, this method shouldn't change, so we keep it closed from other classes. 

* Liskov Substitution

Because this class is abstract, it helps us use CountNeighborsAlgorithm without worrying
about the type of simulation, SimulationEngine can reliably call upon CountNeighborsAlgorithm. 


#### Abstraction #2: Visual Grid
* Open/Close

The core algorithm that gets closed is the updateGrid method, which we don't want to be modifiable 
by other classes, so that there is only one way to update the Grid visually, reducing chances of bugs.

* Liskov Substitution

Designed to be substitutable by keeping most of the method calls that are used when we feed a grid type into
some method call all the same. This needs some updating and work, but hopefully there will no longer
be method calls for methods only in RectangleVisualGrid. 


#### Abstraction #3
* Open/Close

* Liskov Substitution


### New Abstractions

#### Abstraction #1: Grid Structure
* Open/Close


* Liskov Substitution

Want GUI container to be able to place where it needs to be, without worrying about type, and able to call methods for the engine. 

#### Abstraction #2: Simulation Engine
* Open/Close

Both methods called Blank start and randomize start should be closed, because they don't depend
on sim type, but are still the same. 

* Liskov Substitution

Doesn't matter what kind of Simulation Engine it is, the GUIContainer should be able to read individual values
from the Simulation Engine and display the data properly. 

#### Abstraction #3: Cell
* Open/Close

Want to keep Cell close because currently the concrete implementation allows for invalid paramaters to be 
entered for values such as cell state for the current simulation type, and needs to be open so some simulations
such as WatorCell which require additional methods. 

* Liskov Substitution

There are commonality between cells that the engine should not care about what type of cell it is in 
order to call reliably, such as getX or getY position. 


## Issues in Current Code

### SimulationEngine
* Design issues

Doesn't call grid from config, currently has a concrete implementation of grid inside the code as List<List<Cell>>. 

* Design issue

A bunch of if statements, needs abstraction to solve this issue. 

### Main 
* Design issues

Currently, does too much, violates SRP, because it not only runs the game loop, it keeps track on 
info for the game statue such as FrameNumber, speed, and if the game is paused. 

* Design issue
The code inside main that handles the logic for communicating between SimulationEngine and GUIContainer is not the 
best, as currently it's in the form of Command Control. but so many if statements becomes very unsalable soon. The way to refactor 
is by creating an Interface for the SimulationEngine class. 

### GUIContainer
* Design issues

To many dependancies on other classes that aren't needed due to making each class a instance variable, so 
I'll see if I can cut down on that. 

* Design issue

Some functionality inside the GUIContainer should be moved to the individual classes, such as FileSaver and FileUploader. 


## Refactoring Plan

* What are the code's biggest issues?
Long method, duplicate declaration of Strings that should be in css, duplication of methods in config, 
use java interfaces, and magic numbers hard coded into GUIContainer, and exception handlers should
preserve original exception. 
* Which issues are easy to fix and which are hard?
Duplicate declaration of Strings, hardcoded and magic numbers, and referencing java interfaces instead of implementations. 
* What are good ways to implement the changes "in place"?
Comment out old code, write directly under, so you can still view the old code, and make small commits. 


## Refactoring Work

* Issue chosen: Fix and Alternatives
Magic numbers: Read the strings from a properties file and make sure that these numbers are easily modifiable, or move them to css if appropriate. 

* Issue chosen: Fix and Alternatives
Use java interfaces: Change all calls of concrete implementations into interfaces such as List or Map. 

* Issue chosen: Fix and Alternatives
Duplication: Extract duplication code inside config and make helper methods, and integration
colors into css folder to prevent duplication inside RectangleGird. 