
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.SlickException;

public abstract class PowerUp extends Entity {

    public PowerUp(float x, float y) throws SlickException {
        super(x, y);
    }
    
    abstract void givePowerUp(Player player) throws SlickException;
}
