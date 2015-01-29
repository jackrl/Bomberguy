
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends Entity {

    public Wall(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/wallSprite.png");
    }    
}