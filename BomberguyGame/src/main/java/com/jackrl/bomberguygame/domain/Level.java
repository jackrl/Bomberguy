
package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Level {
    
    protected Image floorSprite;
    protected ArrayList<Wall> walls = new ArrayList<Wall>();
    
    // Keep a list of the bombs that have been thrown
    protected ArrayList<Bomb> bombs = new ArrayList<Bomb>();

    public Level() throws SlickException {
        floorSprite = new Image("rsc/sprites/floorSprite.png");
        
        for (int x = 0; x <= 12; x++) 
            for (int y = 0; y <= 12; y++)
                if ((x % 2 == 0 && y % 2 == 0) || x == 0 || x == 12 || y == 0 || y == 12)
                    walls.add(new Wall(x * 32, y * 32));
    }
    
    public void updateBombs(int delta) {
        // Update the bombs with the time passed and give it back to the player if it isn't active anymore
        for (Iterator<Bomb> iterator = bombs.iterator(); iterator.hasNext();) {
            Bomb bomb = iterator.next();
            bomb.update(delta);
                if (!bomb.isActive()) {
                    bomb.returnToPlayer();
                    iterator.remove();
                }         
        }
    }
    
    public void render() {
        // Render the floor
        for (int x = 1; x < 12; x++) 
            for (int y = 1; y < 12; y++) 
                if (x % 2 != 0 || y % 2 != 0)
                    floorSprite.draw(x*32, y*32);
            
        // Render the walls
        for (Wall wall : walls)     
            wall.render();
        
        // Render bombs
        for (Bomb bomb : bombs)
            bomb.render();
    }

    public void checkCollisionsX(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.x -= player.dx * player.maxSpeed * delta; 
            }
        }
        
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if(player.isStandingOnBomb() && bomb.equals(player.getThrownBomb())) {
                    continue;
                } else {
                    player.x -= player.dx * player.maxSpeed * delta;
                }
            }
        }
        
        if (noBombCollisions) {
            player.notSandingonBomb();
        }
    }

    public void checkCollisionsY(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.y -= player.dy * player.maxSpeed * delta;
            }
        }
        
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if(player.isStandingOnBomb() && bomb.equals(player.getThrownBomb())) {
                    continue;
                } else {
                    player.y -= player.dy * player.maxSpeed * delta;
                }
            }
        }
        
        if (noBombCollisions) {
            player.notSandingonBomb();
        }
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
}