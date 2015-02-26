
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ExplosionPowerUp extends PowerUp {

    public ExplosionPowerUp(float x, float y) throws SlickException {
        super(x, y);
        
        sprite = new Image("rsc/sprites/explosionPowerUpSprite.png");
    }

    @Override
    void givePowerUp(Player player) throws SlickException {
        player.range++;
    }
}
