package com.jackrl.bomberguygame.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * <p>The class that represents the player in the game.</p>
 */
public class Player extends Entity {

    private ArrayDeque<Bomb> bombs = new ArrayDeque<Bomb>();
    private Bomb thrownBomb = null;
    private boolean standingOnBomb = false;
    
    public Player(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/playerSprite.png");
        maxSpeed = 0.15f;
        
        bombs.add(new Bomb(this));
        // Test: Testing multiple bombs (Remove from final version)
        //bombs.add(new Bomb(this));        
    }
    
    /**
     * Method that sets the player to move to the right.
     */
    public void moveRight() {
        dx++;
    }

    /**
     * Method that sets the player to move to the left.
     */
    public void moveLeft() {
        dx--;
    }

    /**
     * Method that sets the player to move to the down.
     */
    public void moveDown() {
        dy++;
    }

    /**
     * Method that sets the player to move to the up.
     */
    public void moveUp() {
        dy--;
    }
    
    /**
     * Method that moves the player in the x-axis.
     * 
     * @param delta     time since last update
     */
    public void moveX(int delta) {
        x += dx * maxSpeed * delta;
    }
    
    /**
     * Method that moves the player in the y-axis.
     * 
     * @param delta     time since last update
     */
    public void moveY(int delta) {
        y += dy * maxSpeed * delta;
    }
    
    /**
     * Method that rests the movement parameters for the next update.
     */
    public void resetDxAndDy() {
        dx = 0;
        dy = 0;
    }

    /**
     * Method that throws a bomb if the player has any left.
     * 
     * @param levelBombs    The array where the thrown bomb will be put for the
     * level to render it
     */
    public void throwBomb(ArrayList<Bomb> levelBombs) {
        if (!standingOnBomb && !bombs.isEmpty()) {
            thrownBomb = bombs.pop(); 
            thrownBomb.setLocation();
            standingOnBomb = true;
            levelBombs.add(thrownBomb); 
        }
    }
    
    /**
     * Method that adds a bomb to the player.
     * 
     * @param bomb  bomb to be added to the player
     */
    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    /**
     * Helper method that gives the last thrown bomb while the player is on top of it.
     * 
     * @return  bomb on top of which the player is standing 
     */
    public Bomb getThrownBomb() {
        return thrownBomb;
    }

    /**
     * Helper method that tells whether the player is on top of a bomb
     * 
     * @return  whether the player is standing on a bomb or not 
     */
    public boolean isStandingOnBomb() {
        return standingOnBomb;
    }
    
    /**
     * Method that resets the bomb after the player is not on top of it anymore
     */
    public void notSandingOnBomb() {
        standingOnBomb = false;
        thrownBomb = null;
    }
}
