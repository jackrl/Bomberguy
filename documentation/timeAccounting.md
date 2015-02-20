#Time Accounting
**14.01.2015, *5h*:**
* Initial setup (GitHub, NetBeans project...)
* Setting up external libraries: Slick2D, lwjgl
* Test that the libraries are working with a Slick2D template.

**15.01.2015, *3h*:**
* Changed the name of the project and made some little tweaks
* Wrote the topic description
* Registered to the course's labtool

**16.01.2015, *4h*:**
* Made Slick2D to really work through maven

**17.01.2015, *4h*:**
* Familiarize with Slick2D's basic functionality
* Make the basic game structure

**18.01.2015, *4h*:**
* Made the basic functionality of the first domain classes: Entity and Player
* Built the basic control scheme for the player in a level

**23.01.2015, *7h*:**
* Created the Bomb class
* Added the ability to throw bombs to the player. The bombs disappear after 3 seconds
* Made some junit tests for Player and Bomb.
* Wasn't able to get Pitest to work. I suppose that it has problems to get the native libraries. Junit had also problems but got it working.

**24.01.2015, *2h*:**
* Made pitests work

**29.01.2015, *8h*:**
* Created the Level class to take care of the entities, their rendering and collision detection
* Added the Wall class
* Made the basic map into Level
* Moved some of the entities that were previously in the LevelState into Level
* Researched about collisions
* Made some changes to the Entity, Player and Bomb classes to make them more suitable for collisions
* Was able to make collisions work between the player and the walls
* Made the collisions between the player and bombs work. Some tweaking was needed to avoid problems when the player is on top of the bomb after throwing it
* Made some new tests, although a great part of the testing must be done visually because of the nature of the program
* Run the pitest and cobertura reports

**05.02.2015,*8h*:**
* Polished the process of throwing bombs. Now the method takes into account if the player is on top of a bomb he has just thrown so it doesn't stack multiple bombs in the same tile.
* Made some modifications to the player test to take into account the changes above. Added also a new test for it.
* Separated the collisions logic to its own class. Made some changes into the player-bomb collisions to make it more simple.
* Added the Block class that represents the destructible blocks of the game
* Added the Enemy class with simple movement AI. This will probably be changed to an abstract class from which the rest of the enemies will be inhereted.
* Added tests and fixed some that needed to be modified
* Wrote javadoc comments
* Added instructions on how to get the project working on windows

**12.02.2015,*7h*:**
* Bombs explode now and destroyed blocks. All the different blocking mechanisms have been taking into account, e.g. explosions don't go through walls and the explosion of a bomb triggers the explosion of any bomb the bomb's explosion reaches to.
* Added the Explosion class.
* Added sprites for the explosion and the enemy.
* Updated the tests that were affected by changes made to make the explosions possible.
* Added a few new tests.
* Updated the documentation.

**20.02.2015, *2h*:**
* Added death to player when it collides with enemies.
* Implemented the pause and exit features into the Controller
* Fixed last week's sequence diagrams