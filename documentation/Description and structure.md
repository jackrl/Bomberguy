# Description and Structure of the Topic

**Topic:** The intention of this project is to make a game similar to the classic bomberman games. The player will be able to go through a couple of levels, that have enemies and destructible blocks that can be destroyed with the help of bombs. The player will begin with the capability of throwing just one bomb at a time. The amount of bombs and their range can be increased by collecting power-ups that may drop when the player destructs a destructible block.

The player will be able to advance to the next level every time he has eliminated every enemy in a the current level. 

**Users:** Player/players
		
**Features of the player/players:**
* The player can move in the 4 main directions (up, down, left and right)
* The player can throw bombs
* The player can collect power-ups
* The player can pause the game

**Structure:**
The program consists of a Slick2D StateBaseGame with the different states that represent the levels(LevelXState). Every LevelState contains the player (This is due to the possibilities of upgrade that would need the player to persist from one level to another). The Level object that the LevelState creates contains the rest: walls, blocks, bombs, explosions, power-ups and enemies. All this and the player inherit from Entity that is the basic unit for all the drawable and collidable objects in the game. The helper class Controller take care of the input.
