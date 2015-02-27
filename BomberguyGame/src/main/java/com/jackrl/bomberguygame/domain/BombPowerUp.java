
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the power-up that adds the ability to through one bomb to the player. 
 */
public class BombPowerUp extends PowerUp {

    /**
     * Constructor of the BombPowerUp class. 
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public BombPowerUp(float x, float y) throws SlickException {
        super(x, y);
        
        sprite = new Image("rsc/sprites/bombPowerUpSprite.png");
    }

    @Override
    public void givePowerUp(Player player) throws SlickException {
        player.addBomb(new Bomb(player));
    }
}
