
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represent the power-up that adds range to the bombs of a player.
 */
public class ExplosionPowerUp extends PowerUp {

    /**
     * Constructor of the ExplosionPowerUp class. 
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public ExplosionPowerUp(float x, float y) throws SlickException {
        super(x, y);
        
        sprite = new Image("rsc/sprites/explosionPowerUpSprite.png");
    }

    @Override
    public void givePowerUp(Player player) throws SlickException {
        player.range++;
    }
}
