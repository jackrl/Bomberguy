package com.jackrl.bomberguygame.domain;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the bombs used by the player to destroy blocks and kill
 * enemies
 */
public class Bomb extends Entity {
    
    private Player player;
    
    private boolean isActive;
    private int timer;

    public Bomb(Player player) throws SlickException {
        super(-100, -100);
        this.player = player;
        resetBomb();
        
        sprite = new Image("rsc/sprites/bombSprite.png");
    }
    
    private void resetBomb() {
        isActive = true;
        timer = 3 * 1000;
    }

    /**
     * Method that sets the location of the bomb based on the position of the
     * player that owns it
     */
    public void setLocation() {
        x = ((int)((player.getX() + 16) / 32)) * 32; 
        y = ((int)((player.getY() + 16) / 32)) * 32;
    }
    
    /**
     * Method that updates the timer of the bomb until it goes bellow zero. When
     * the timer goes bellow zero the bomb is deactivated.
     * @param   delta   Time to advance
     */
    public void update(int delta) {
        timer -= delta;
        if (timer <= 0)
            isActive = false;
    }
    
    /**
     * Method that returns if the bomb is active.
     * 
     * @return whether the bomb is active or not 
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method that rests a bomb and then returns it to the player that owns it.
     */
    public void returnToPlayer() {
        resetBomb();
        player.addBomb(this);
    }

    public int getTimer() {
        return timer;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        
        Bomb other = (Bomb) obj;
        
        if (this.x == other.x && this.y == other.y)
            return true;
        
        return false;
    }
}
