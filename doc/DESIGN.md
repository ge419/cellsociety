# Cell Society Design Final
### Team 04
### Han Zhang, Changmin Shin, Brandon Weiss


## Team Roles and Responsibilities

* Han
   - Primary: GUI
   - Secondary: Interfaces, Controller, GameLoopManager
* Changmin
   *    Primary: Config
   * Secondary: Engine, GameLoopManager, Cells, Interfaces

* Brandon
   * Primary: Engine, Cells
   * Secondary: Grid, GameLoopManager



## Design goals

#### What Features are Easy to Add
Any new types of Graphs, Buttons and new Types of Grids/Graphs are all easy to add. New types
of simulations are also easy to add due to the interface and SimEngine abstraction, as well as Cell types. If Config needs to be set up, there is a ConfigInterface to make it easy to decide which methods it needs to implement for integration to work.

## High-level Design

#### Core Classes
The four core classes are the Config, SimEngine, Grid, and GUIContainer. The Config is a standalone class,
instantiated without any dependencies. SimEngine intakes values from the Config, and the GUIContainer takes in interfaces of both to interact with the two classes using lambda functions attached to event handlers. The Main object being modified throughout is the Grid class, which allows us to keep track of the Cells. SimEngine modifies the Grid, and GUIContainer simply has components that copy it. The GUIContainer represents the View, and the SimEngine and Config represent the Model. We also have a Controller Package with SimulationManager to help us manage the changes between the View and Model. For the most part, the View communicates to the Model what it needs.
## Assumptions that Affect the Design
An Assumption that we made was that the shapes were going to be square. This affected the engine and the way it found neighbors.
#### Features Affected by Assumptions
The SimEngine's corners and neighbors were affected

## Significant differences from Original Plan
The timeline was shifted way back, and many View classes became deleted in favor of "container" classes that also contained objects such as HBoxs to help with formatting. Also, some features were not implemented, such as simulations wator and schelling, which aren't fully functional due to intergration, and clicking on grids to modify cell state, which was planned, but never fully functional. Also no percolation was implemented. The method to update the Grid also has changed. Instead of feeding the View a cell one by one, the VisualGrid was simply updated by copying the contents of the Grid every time, to ensure no accidents happened.

## New Features HowTo

#### Easy to Add Features
Features that are easy to add include more Graphs, Grids, and additional buttons to modify the front end. For example, if a New ButtonContainer is added for
more interaction with other buttons to change the grid, A button Container class would be made, and any nececssary ineractions between Config and Engine will be used the Config and EngineInterface. Lambda expressions connecting the View to the model will interfact with the EngineInterface, unless it modifies the GameLoop itself, in which it will setup the Simulation Interface. Styling will be added into the CSS file called GUIContainer.css. If they control the game flow, they interact wit the Simulation Controller. For Grids, just implement new types of grids based on VisualGrid. For Graphs, the same follows, using the VisualGraphInterface as a basis for any new Grphas, then adding a ButtonContainer to allow the pop-up to occur. New engines can implement SimEngine. 

