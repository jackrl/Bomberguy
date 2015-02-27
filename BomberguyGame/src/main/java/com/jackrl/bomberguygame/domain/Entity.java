package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * <p>An abstract class for all the entities that are rendered in the game.</p>
 */
public abstract class Entity {
    
    protected float x;
    protected float y;
    protected float maxSpeed = 0.f;
    protected int dx;
    protected int dy;
    
    protected Image sprite;
    
    private Rectangle boundingShape;
    private Rectangle collidingShape;

    /**
     * Constructor of the Entity class.
     * 
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Entity(float x, float y) throws SlickException {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        // A place holder in case that you forget to place the real one
        sprite = new Image("rsc/sprites/placeHolderSprite.png");
        
        this.boundingShape = new Rectangle(x, y, sprite.getWidth() - 2, sprite.getHeight() - 2);
        this.collidingShape = new Rectangle(-100, -100, 0, 0);
    }

    // Might be used when coding the "AI" for aggressive enemies
    public int getX() {
        return (int) x;
    }

    // Might be used when coding the "AI" for aggressive enemies
    public int getY() {
        return (int) y;
    }
    
    /**
     * Method that renders the sprite of an entity.
     */
    public void render() {
        sprite.draw((int) x, (int) y);
    }
    
    /**
     * Method that checks the collisions between Entities
     * 
     * @param  other
     * @return Whether this Entity has collided with the other Entity.
     */
    public boolean collidesWith(Entity other) {
        boundingShape.setBounds((int) x, (int) y, sprite.getWidth() - 3, sprite.getHeight() - 3);
        
        collidingShape.setBounds((int) other.x, (int) other.y, other.sprite.getWidth() - 3, other.sprite.getHeight() - 3);
        
        return boundingShape.intersects(collidingShape);
    }
}
