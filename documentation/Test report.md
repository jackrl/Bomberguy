#Test report

Much of the testing was done visually. The first thing that was built, was the basic graphical interface to be able to see every change. Every time a change was made the game was run to see the effect of the changes. Things like rendering and sudden unexpected movement would have been very difficult to check in any other way.

Most of the domain classes have been tested wit unit tests, but that isn't enough. It wasn't easy either as I had to figure out that a OpenGL Display must be created every time you want to test anything involving Slick2D. This also affects the tests runtime. For example running all the pitests takes a few minutes with windows flashing on and off for the whole time. 

###Bugs and improvements that could be made
There are many places where the game could be improved. I haven't found any breaking bugs, but the following things would need to be considered in the future:
* The collisions between the player and the walls and blocks could be improved to help the player turn and fit between to walls without the need of pressing two keys at a time if the player is close enough to the aperture.
* The Enemy class could be made an abstract class to make different kind of enemies.