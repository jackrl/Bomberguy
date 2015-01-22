package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bomb {
    
    private float x;
    private float y;
    private Player player;
    private Image sprite;
    
    private boolean isActive;
    private int timer;

    Bomb(Player player) throws SlickException {
        this.player = player;
        resetBomb();
        
        sprite = new Image("rsc/sprites/BombSprite.png");
    }
    
    private void resetBomb() {
        isActive = true;
        timer = 3 * 1000;
    }

    public void setLocation() {
        x = player.getX();
        y = player.getY();
    }
    
    public void update(int delta) {
        timer -= delta;
        if (timer <= 0)
            isActive = false;
    }
    
    public void render() {
        sprite.draw(x, y);
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void returnToPlayer() {
        resetBomb();
        player.addBomb(this);
    }

    public int getTimer() {
        return timer;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
