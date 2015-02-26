
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BombPowerUp extends PowerUp {

    public BombPowerUp(float x, float y) throws SlickException {
        super(x, y);
        
        sprite = new Image("rsc/sprites/bombPowerUpSprite.png");
    }

    @Override
    void givePowerUp(Player player) throws SlickException {
        player.addBomb(new Bomb(player));
    }
}
