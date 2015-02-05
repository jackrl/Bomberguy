
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the destructible blocks in the level
 */
public class Block extends Entity{
    
    public Block(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/blockSprite.png");
    }   
}
