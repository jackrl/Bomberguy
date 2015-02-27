
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the explosion of a bomb in one tile. Players and enemies die when they
 * collide with them.
 */
class Explosion extends Entity {

    /**
     * Constructor of the Explosion class.
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Explosion(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/explosionSprite.png");
        maxSpeed = 0.f;
    }
    
}
