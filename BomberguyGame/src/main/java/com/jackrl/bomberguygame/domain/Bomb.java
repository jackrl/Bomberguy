package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bomb extends Entity {
    
    private Player player;
    
    private boolean isActive;
    private int timer;

    Bomb(Player player) throws SlickException {
        super(-100, -100);
        this.player = player;
        resetBomb();
        
        sprite = new Image("rsc/sprites/BombSprite.png");
    }
    
    private void resetBomb() {
        isActive = true;
        timer = 3 * 1000;
    }

    // TODO: Clean this
    public void setLocation() {
        x = ((int)((player.getX() + 16) / 32)) * 32; 
        y = ((int)((player.getY() + 16) / 32)) * 32;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        
        Bomb other = (Bomb) obj;
        
        if (this.x == other.x && this.y == other.y)
            return true;
        
        return false;
    }
}
