package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {

    public Player(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/playerSprite.png");
        speed = 0.25f;
    }
    
    public void moveRight(int delta) {
        x += speed * delta;
    }

    public void moveLeft(int delta) {
        x -= speed * delta;
    }

    public void moveDown(int delta) {
        y += speed * delta;
    }

    public void moveUp(int delta) {
        y -= speed * delta;
    }

    public void throwBomb() {
        // TO-DO: Throw bomb
        System.out.println("Throw bomb");
    }
}
