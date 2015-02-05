
package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
/**
 * Provisional Class that serves as a helper with the collisions between the player
 * and the rest of the environment
 */
public class Collisions {

    private ArrayList<Wall> walls;
    private ArrayList<Bomb> bombs;
    private ArrayList<Block> blocks;
    
    public Collisions(ArrayList<Wall> walls, ArrayList<Bomb> bombs) {
        this.walls = walls;
        this.bombs = bombs;
    }

    Collisions(ArrayList<Wall> walls, ArrayList<Bomb> bombs, ArrayList<Block> blocks) {
        this.walls = walls;
        this.bombs = bombs;
        this.blocks = blocks;
    }
    
    public void checkCollisionsX(Player player, int delta) {
        checkCollisionsWithWallsX(player, delta);
        checkCollisionsWithBlocksX(player, delta);
        checkCollisionsWithBombsX(player, delta);
    }
    
    public void checkCollisionsY(Player player, int delta) {
        checkCollisionsWithWallsY(player, delta);
        checkCollisionsWithBlocksY(player, delta);
        checkCollisionsWithBombsY(player, delta);
    }
    
    /******************Walls******************/
    
    private void checkCollisionsWithWallsX(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.x -= player.dx * player.maxSpeed * delta; 
            }
        }
    }
    
    private void checkCollisionsWithWallsY(Player player, int delta) {
        for (Wall wall : walls) {
            if (player.collidesWith(wall)) {
                player.y -= player.dy * player.maxSpeed * delta;
            }
        }
    }
    
    /******************Blocks******************/
    
    private void checkCollisionsWithBlocksX(Player player, int delta) {
        for (Block block : blocks) {
            if (player.collidesWith(block)) {
                player.x -= player.dx * player.maxSpeed * delta; 
            }
        }
    }
    
    private void checkCollisionsWithBlocksY(Player player, int delta) {
        for (Block block : blocks) {
            if (player.collidesWith(block)) {
                player.y -= player.dy * player.maxSpeed * delta;
            }
        }
    }
    
    /******************Bombs******************/
    
    private void checkCollisionsWithBombsX(Player player, int delta) {
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if(!player.isStandingOnBomb() || !bomb.equals(player.getThrownBomb()))
                    player.x -= player.dx * player.maxSpeed * delta;
            }
        }
        
        if (noBombCollisions) {
            player.notSandingOnBomb();
        }
    }
    
    private void checkCollisionsWithBombsY(Player player, int delta) {
        boolean noBombCollisions = true;
        for (Bomb bomb : bombs) {
            if (player.collidesWith(bomb)) {
                noBombCollisions = false;
                if(!player.isStandingOnBomb() || !bomb.equals(player.getThrownBomb()))
                    player.y -= player.dy * player.maxSpeed * delta;
            }
        }
        
        if (noBombCollisions) {
            player.notSandingOnBomb();
        }
    }
}
