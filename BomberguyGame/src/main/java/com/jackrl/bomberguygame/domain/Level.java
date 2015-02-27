
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

    /**
     * Constructor of the Level class. The level class builds the level from the
     * String array given to it.
     * 
     * @param mapRows
     * @throws SlickException 
     */
    public Level(String[] mapRows) throws SlickException {
        floorSprite = new Image("rsc/sprites/floorSprite.png");
        
        for (int y = 0; y < mapRows.length; y++) {
            String row = mapRows[y];
            for (int x = 0; x < row.length(); x++) {
                switch (row.charAt(x)) {
                    case '#':
                        walls.add(new Wall(x * 32, y * 32));
                        break;
                    case '@':
                        blocks.add(new Block(x * 32, y * 32));
                        break;
                    case '!':
                        enemies.add(new Enemy(x * 32, y * 32));
                        break;
                }
            }
        }
        
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
        for (int x = 0; x < 13; x++) 
            for (int y = 0; y < 13; y++) 
                floorSprite.draw(x * 32, y * 32);
            
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
    
    /**
     * Checks the collisions of the player with enemies.
     * 
     * @param player 
     */
    public void checkCollisionsWithEnemies(Player player) {
        collisions.checkCollisionsWithEnemies(player, enemies);
    }
    
    /**
     * Checks the collisions of the player with explosions.
     * 
     * @param player 
     */
    public void checkCollisionsWithExplosions(Player player) {
        collisions.checkCollisionsWithExplosions(player, bombs);
    }
    
    /**
     * Checks the collisions of the player with power-ups.
     * 
     * @param player 
     */
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