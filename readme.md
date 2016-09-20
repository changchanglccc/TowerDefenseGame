# Map creation and editing
- [x]  User-driven interactive creation of a map as a grid of user-defined dimension with grid elements such as scenery, path, entry point and exit point. Saving/loading/editing/verification of an edited map.

# Game play
- [x] Game starts by user selection of a previously user-saved map, then loads the map. 
- [x] Wave-based play: First (pre-wave phase) the player can place new towers, upgrade towers, sell towers, and signify that critters are allowed in on the map, when all critters in a wave have been killed or reached the end point, a new wave starts.
- [x] End of game, e.g. when a certain number of critters reach the exit point of the map, or the critters steal all the player’s coins, or the player succeeds in killing a certain number of waves. 
- [x] Implementation of currency, cost to buy/sell a tower, and reward for killing critters.
- [x] Critter waves are created with a level of difficulty increasing at every wave. Difficulty must involve increasing critter speed and toughness.
- [x] Implementation of at least three different kinds of towers that are characterized by special damage effects. The mandatory special damage effects are: 
	- [x] splash (inflicts damage to critters around the targeted critter), 
	- [x] burning (inflicts damage over time after a critter has been hit), 
	- [x] freezing (slows down the critter for some time).
- [ ] The towers can target the critters using the following mandatory strategies: 
	- [x] nearest to the tower, 
	- [x] nearest to the end point, 
	- [x] weakest critter, 
	- [x] strongest critter. 
	- It must be possible to set a different targeting strategies for individual towers.
- [x] Tower inspection window that: 
	- [x] dynamically shows its current characteristics, 
	- [x] allows to sell the tower, 
	- [x] increase the level of the tower, 
	- [x] select the tower’s targeting strategy and 
	- [x] view the individual tower’s log (see below).
- [x] Critter observer that allows to dynamically observe the current hit points of any critter on the map.

# Game management 10
- [x] Game log that records all events happening in the game, including placement/upgrade/selling of towers, critter wave creation, etc. The log must allow for the viewing of the whole log in sequential order (i.e. ordered in time) or certain portions of the log related to a certain aspect of the game, also ordered according to time, e.g.
	- [x]  Individual tower log: time-ordered log of all events related to a specific tower
	- [x]  Collective tower log: time-ordered log of all events related to all towers (i.e. time inter-meshing of the previous)
	- [x]  Wave log: all activities that happened in any specific wave of the game (select a wave and list sorted by time the events happened in this wave, from pre-wave tower edition phase to end of the wave).
	- [x]  Global game log: all events that happened throughout the entire game up to now, sorted by time.
- [x] Map log that records in the map file the time of original creation of the map, when it was edited, when it was played and what was the result of the game every time it was played. When a map is being played, the list of five highest scores is presented before the game starts, as well as when the game ends.
- [ ] Save/Load a game in progress: As a game is being played, allow the user to save the game in progress to a file,and allow the user to load the game in exactly the same state as saved.

# Programming process
- [ ] Architectural design—short 3-4 pages document including an architectural design diagram, and a short but complete and clear description of the architectural design. The architectural design shown in the document must be demonstrated to be corresponding to how the actual code in organized.
- [ ] Design patterns—proper use of at least 4 different design patterns, e.g. observer for map rendering or tower inspection, strategy for tower targeting strategies, singleton for game controller, factory for critter wave creation, decorator for levelling up towers.
- [ ] Software versioning repository—well-populated history wit
