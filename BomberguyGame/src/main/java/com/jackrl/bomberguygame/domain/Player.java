package com.jackrl.bomberguygame.domain;

import java.util.ArrayDeque;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Player extends Entity {

    private ArrayDeque<Bomb> bombs = new ArrayDeque<Bomb>();
    private Bomb thrownBomb = null;
    private boolean standingOnBomb = false;
    
    public Player(float x, float y) throws SlickException {
        super(x, y);
        sprite = new Image("rsc/sprites/playerSprite.png");
        maxSpeed = 0.15f;
        
        bombs.add(new Bomb(this));
    }
    
    public void moveRight() {
        dx++;
    }

    public void moveLeft() {
        dx--;
    }

    public void moveDown() {
        dy++;
    }

    public void moveUp() {
        dy--;
    }
    
    public void moveX(int delta) {
        x += dx * maxSpeed * delta;
    }
    
    public void moveY(int delta) {
        y += dy * maxSpeed * delta;
    }
    
    public void resetDxAndDy() {
        dx = 0;
        dy = 0;
    }

    public Bomb throwBomb() {
        if(!bombs.isEmpty()) {
            bombs.getLast().setLocation();
            thrownBomb = bombs.pop(); 
            standingOnBomb = true;
            return thrownBomb;
        }
        else
            return null;
    }
    
    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public Bomb getThrownBomb() {
        return thrownBomb;
    }

    public boolean isStandingOnBomb() {
        return standingOnBomb;
    }
    
    public void notSandingonBomb() {
        standingOnBomb = false;
    }
}
