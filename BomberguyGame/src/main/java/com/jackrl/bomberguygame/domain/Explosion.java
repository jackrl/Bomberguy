
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

class Explosion extends Entity {

    public Explosion(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/explosionSprite.png");
        maxSpeed = 0.f;
    }
    
}
