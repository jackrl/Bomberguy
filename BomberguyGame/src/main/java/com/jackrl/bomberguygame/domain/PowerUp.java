
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.SlickException;

/**
 * The PowerUp class is the abstract class that represents the power-ups that the player
 * can collect in the game.
 */
public abstract class PowerUp extends Entity {

    /**
     * Constructor of the PowerUp class.
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public PowerUp(float x, float y) throws SlickException {
        super(x, y);
    }
    
    /**
     * Gives the effect of this power-up to the player.
     * 
     * @param player
     * @throws SlickException 
     */
    abstract public void givePowerUp(Player player) throws SlickException;
}
