
package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.SlickException;

/**
 * Class that serves as a helper with the collisions between the player
 * and the rest of the environment
 */
public class Collisions {

    private ArrayList<Wall> walls;
    private ArrayList<Bomb> bombs;
    private ArrayList<Block> blocks;

    /**
     * Constructor of the Collisions class.
     * 
     * @param walls
     * @param bombs
     * @param blocks 
     */
    public Collisions(ArrayList<Wall> walls, ArrayList<Bomb> bombs, ArrayList<Block> blocks) {
        this.walls = walls;
        this.bombs = bombs;
        this.blocks = blocks;
    }
    
    /**
     * Check the collisions of the player with the walls, blocks and bombs in the x-axis.
     * the method prevents the player from going through these entities.
     * 
     * @param player
     * @param delta 
     */
    public void checkCollisionsX(Player player, int delta) {
        checkCollisionsWithWallsX(player, delta);
        checkCollisionsWithBlocksX(player, delta);
        checkCollisionsWithBombsX(player, delta);
    }
    
    /**
     * Check the collisions of the player with the walls, blocks and bombs in the y-axis.
     * the method prevents the player from going through these entities.
     * 
     * @param player
     * @param delta 
     */
    public void checkCollisionsY(Player player, int delta) {
        checkCollisionsWithWallsY(player, delta);
        checkCollisionsWithBlocksY(player, delta);
        checkCollisionsWithBombsY(player, delta);
    }
    
    /**
     * Check if the player has collided with enemies. If it has collided with an enemy,
     * the player will die.
     * 
     * @param player
     * @param enemies 
     */
    public void checkCollisionsWithEnemies(Player player, ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies)
            if (player.collidesWith(enemy))
                player.die();
    }
    
    /**
     * Check if the player has collided with the explosion of a bomb. If it has collided with an explosion,
     * the player will die.
     * 
     * @param player
     * @param bombs 
     */
    public void checkCollisionsWithExplosions(Player player, ArrayList<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            if (bomb.hasExploded()) {
                for (Explosion explosion : bomb.explosions) {
                    if (player.collidesWith(explosion))
                        player.die();
                }
            }
        }
    }
    
    /**
     * Check if the player has collided with any power-ups. If it has collided with one,
     * the effect of the power-up will be applied to the player.
     * 
     * @param player
     * @param powerUps
     * @throws SlickException 
     */
    public void checkCollisionsWithPowerUps(Player player, ArrayList<PowerUp> powerUps) throws SlickException {
        for (Iterator<PowerUp> iter = powerUps.iterator(); iter.hasNext();) {
            PowerUp powerUp = iter.next();
            
            if (player.collidesWith(powerUp)) {
                powerUp.givePowerUp(player);
                iter.remove();
            }
        }
    }
    
    private void checkCollisionsWithWallsX(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.x -= player.dx * player.maxSpeed * delta; 
            }
        }
    }
    
    private void checkCollisionsWithWallsY(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.y -= player.dy * player.maxSpeed * delta;
            }
        }
    }
    
    private void checkCollisionsWithBlocksX(Player player, int delta) {
        for (Block block : blocks) {
            if (player.collidesWith(block)) {
                player.x -= player.dx * player.maxSpeed * delta; 
            }
        }
    }
    
    private void checkCollisionsWithBlocksY(Player player, int delta) {
        for (Block block : blocks) {
            if (player.collidesWith(block)) {
                player.y -= player.dy * player.maxSpeed * delta;
            }
        }
    }
    
    private void checkCollisionsWithBombsX(Player player, int delta) {
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if (!player.isStandingOnBomb() || !bomb.equals(player.getThrownBomb()))
                    player.x -= player.dx * player.maxSpeed * delta;
            }
        }
        
        if (noBombCollisions) {
            player.notSandingOnBomb();
        }
    }
    
    private void checkCollisionsWithBombsY(Player player, int delta) {
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if (!player.isStandingOnBomb() || !bomb.equals(player.getThrownBomb()))
                    player.y -= player.dy * player.maxSpeed * delta;
            }
        }
        
        if (noBombCollisions) {
            player.notSandingOnBomb();
        }
    }
}
