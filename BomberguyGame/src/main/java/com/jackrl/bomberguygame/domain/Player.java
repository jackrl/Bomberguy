package com.jackrl.bomberguygame.domain;

import java.util.ArrayDeque;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {

    private ArrayDeque<Bomb> bombs = new ArrayDeque<Bomb>();
    
    public Player(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/playerSprite.png");
        speed = 0.25f;
        
        bombs.add(new Bomb(this));
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

    public Bomb throwBomb() {
        if(!bombs.isEmpty()) {
            bombs.getLast().setLocation();
            return bombs.pop();
        }
        else
            return null;
    }
    
    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }
}
