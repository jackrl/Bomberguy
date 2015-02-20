
package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * <p>The class that represents a level in the game. It contains all the entities and takes care of updating and rendering them.</p>
 */
public class Level {

    protected Image floorSprite;
    protected ArrayList<Wall> walls = new ArrayList<Wall>();
    protected ArrayList<Block> blocks = new ArrayList<Block>();
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    // Keep a list of the bombs that have been thrown
    protected ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    
    // Create a collision helper
    private Collisions collisions;

    public Level() throws SlickException {
        floorSprite = new Image("rsc/sprites/floorSprite.png");
        
        for (int x = 0; x <= 12; x++) 
            for (int y = 0; y <= 12; y++)
                if ((x % 2 == 0 && y % 2 == 0) || x == 0 || x == 12 || y == 0 || y == 12)
                    walls.add(new Wall(x * 32, y * 32));
        
        // Some manually placed blocks
        // TODO: Randomize this
        for (int x = 3; x <= 5; x++) 
            for (int y = 3; y <= 7; y++)
                if (x % 2 != 0 || y % 2 != 0)
                    blocks.add(new Block(x * 32, y * 32));
        
        enemies.add(new Enemy(11 * 32, 11 * 32, 0, 1));
        enemies.add(new Enemy(10 * 32, 11 * 32, -1, 0));
        
        collisions = new Collisions(walls, bombs, blocks);
    }
    
    /**
     * Method that updates all the bombs in the level.
     * 
     * @param delta     time since last update
     */
    public void updateBombs(int delta) throws SlickException {
        // Update the bombs with the time passed and give it back to the player if it isn't active anymore
        for (Iterator<Bomb> iterator = bombs.iterator(); iterator.hasNext();) {
            Bomb bomb = iterator.next();
            bomb.update(delta, this);
                if (!bomb.isActive()) {
                    bomb.returnToPlayer();
                    iterator.remove();
                }         
        }
    }
    
    /**
     * Method that updates all the enemies in the level.
     * 
     * @param delta     time since last update
     */
    public void updateEnemies(int delta) {
        for (Enemy enemy : enemies) 
            enemy.move(delta, walls, blocks, bombs);
    }
    
    /**
     * Method that renders all the sprites of the different entities.
     */
    public void render() {
        // Render the floor
        for (int x = 1; x < 12; x++) 
            for (int y = 1; y < 12; y++) 
                if (x % 2 != 0 || y % 2 != 0)
                    floorSprite.draw(x*32, y*32);
            
        // Render the walls
        for (Wall wall : walls)     
            wall.render();
        
        // Render the blocks
        for (Block block : blocks) {
            block.render();
        }
        
        // Render bombs
        for (Bomb bomb : bombs)
            bomb.render();
        
        //Render enemies
        for (Enemy enemy : enemies) {
            enemy.render();
        }
    }

    /**
     * Method checks the collisions between the player and other entities in the x-axis.
     * 
     * @param player    player for which the collisions are to be checked
     * @param delta     time since last update
     */
    public void checkCollisionsX(Player player, int delta) {
        collisions.checkCollisionsX(player, delta);
    }

    /**
     * Method checks the collisions between the player and other entities in the y-axis.
     * 
     * @param player    player for which the collisions are to be checked
     * @param delta     time since last update
     */
    public void checkCollisionsY(Player player, int delta) {
        collisions.checkCollisionsY(player, delta);
    }
    
    public void checkCollisionsWithEnemies(Player player) {
        collisions.checkCollisionsWithEnemies(player, enemies);
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
}