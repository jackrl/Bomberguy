
package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * <p>The class that represents the enemies in the game. Touching them will kill
 * the player</p>
 * <p>Probably will make it an abstract class from which inherit the different enemies</p>
 * <p>The collisions has to be thought over. Will be united with the ones of the
 * player if possible</p>
 */
public class Enemy extends Entity{

    // Needed for forst attempt in simple AI
    
    public Enemy(float x, float y, int dx, int dy) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/enemySprite.png");
        maxSpeed = 0.15f;
        
        // Start direction
        this.dx = dx;
        this.dy = dy;
    }
    
    /**
     * Method that takes care of the movement of the enemy.
     * 
     * @param delta     time since last update
     * @param walls     array containing the walls that are in the level
     * @param blocks    array containing the blocks that are in the level
     * @param bombs     array containing the bombs that are in the level
     */
    public void move(int delta, ArrayList<Wall> walls, ArrayList<Block> blocks, ArrayList<Bomb> bombs) {
        this.moveX(delta);
        if (    checkCollisionsWithWallsX(delta, walls) ||
                checkCollisionsWithBlocksX(delta, blocks) ||
                checkCollisionsWithBombsX(delta, bombs)) {
            dy = -dx;
            dx = 0;
        }        
        this.moveY(delta);
        if (    checkCollisionsWithWallsX(delta, walls) ||
                checkCollisionsWithBlocksX(delta, blocks) ||
                checkCollisionsWithBombsX(delta, bombs)) {
            dx = dy;
            dy = 0;
        }    
    }
    
    private void moveX(int delta) {
        x += dx * maxSpeed * delta;
    }
    
    private void moveY(int delta) {
        y += dy * maxSpeed * delta;
    }
    
    // Unite somehow the collisions with the ones of the player
    /******************Walls******************/
    
    private boolean checkCollisionsWithWallsX(int delta, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (this.collidesWith(wall)) {
                this.x -= this.dx * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
    
    private boolean checkCollisionsWithWallsY(int delta, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (this.collidesWith(wall)) {
                this.y -= this.dy * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
    
    /******************Blocks******************/
    
    private boolean checkCollisionsWithBlocksX(int delta, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (this.collidesWith(block)) {
                this.x -= this.dx * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
    
    private boolean checkCollisionsWithBlocksY(int delta, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (this.collidesWith(block)) {
                this.y -= this.dy * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
    
    /******************Bombs******************/
    
    private boolean checkCollisionsWithBombsX(int delta, ArrayList<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            if (this.collidesWith(bomb)) {
                this.x -= this.dx * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
    
    private boolean checkCollisionsWithBombsY(int delta, ArrayList<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            if (this.collidesWith(bomb)) {
                this.y -= this.dy * this.maxSpeed * delta;
                return true;
            }
        }
        return false;
    }
}
