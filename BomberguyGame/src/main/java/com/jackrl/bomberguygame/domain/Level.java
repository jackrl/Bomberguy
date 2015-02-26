
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
    protected ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    
    // Keep a list of the bombs that have been thrown
    protected ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    
    // Create a collision helper
    private Collisions collisions;
    private boolean hasEnded = false;

    public Level() throws SlickException {
        floorSprite = new Image("rsc/sprites/floorSprite.png");
        
        for (int x = 0; x <= 12; x++) 
            for (int y = 0; y <= 12; y++)
                if ((x % 2 == 0 && y % 2 == 0) || x == 0 || x == 12 || y == 0 || y == 12)
                    walls.add(new Wall(x * 32, y * 32));
        
        // Some manually placed blocks
        // TODO: Build two levels
        for (int x = 3; x <= 5; x++) 
            for (int y = 3; y <= 7; y++)
                if (x % 2 != 0 || y % 2 != 0)
                    blocks.add(new Block(x * 32, y * 32));
        
        // Spawn two enemies        
        enemies.add(new Enemy(11 * 32, 11 * 32, 0, 1));
        enemies.add(new Enemy(10 * 32, 11 * 32, -1, 0));
        
        // Testing poweups
        //powerUps.add(new BombPowerUp(8 * 32, 1 * 32));
        //powerUps.add(new ExplosionPowerUp(6 * 32, 1 * 32));
        
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
        for (Iterator<Enemy> iter = enemies.iterator(); iter.hasNext();) {
            Enemy enemy = iter.next();
            
            enemy.move(delta, walls, blocks, bombs);
            if (enemy.isDead())
                iter.remove();
        }
        
        if (enemies.isEmpty())
            hasEnded = true;
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
        
        // Render powerUps
        for (PowerUp powerUp : powerUps)
            powerUp.render();
        
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
    
    public void checkCollisionsWithExplosions(Player player) {
        collisions.checkCollisionsWithExplosions(player, bombs);
    }
    
    public void checkCollisionsWithPowerUps(Player player) throws SlickException {
        collisions.checkCollisionsWithPowerUps(player, powerUps);
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public boolean hasEnded() {
        return hasEnded;
    }
}