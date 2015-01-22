package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Entity {
    
    protected float x;
    protected float y;
    protected float speed = 0.f;
    protected Image sprite;

    public Entity(float x, float y) throws SlickException {
        this.x = x;
        this.y = y;
        // A place holder in case that you forget to place the real one
        sprite = new Image("rsc/sprites/placeHolderSprite.png");
    }

    // Might be used when coding the "AI" for aggressive enemies
    public float getX() {
        return x;
    }

    // Might be used when coding the "AI" for aggressive enemies
    public float getY() {
        return y;
    }
    
    public void render() {
        sprite.draw(x, y);
    }
    
    
}
