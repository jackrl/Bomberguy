
package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the destructible blocks in the level
 */
public class Block extends Entity {
    
    /**
     * Constructor that sets the position of the block. It also assigns it its sprite.
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Block(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/blockSprite.png");
    }   
}
