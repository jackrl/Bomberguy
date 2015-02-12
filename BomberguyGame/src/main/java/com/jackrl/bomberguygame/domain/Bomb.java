package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class that represents the bombs used by the player to destroy blocks and kill
 * enemies
 */
public class Bomb extends Entity {
    
    private Player player;
    
    private boolean isActive;
    private boolean hasExploded;
    private int timer;
    private int explosionTime;
    
    protected ArrayList<Explosion> explosions;
    
    private int range;

    public Bomb(Player player) throws SlickException {
        super(-100, -100);
        this.player = player;
        this.range = player.range;
        resetBomb();
        
        sprite = new Image("rsc/sprites/bombSprite.png");
    }
    
    private void resetBomb() {
        isActive = true;
        hasExploded = false;
        timer = 3 * 1000;
        explosionTime = 1 * 1000;
        explosions = new ArrayList<Explosion>();
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
     * @param   level   The level object containing all the other entities
     */
    public void update(int delta, Level level) throws SlickException {
        timer -= delta;
        if (hasExploded) {
            explosionTime -= delta;
            if (explosionTime <= 0)
                isActive = false;
        } else if (timer <= 0) 
            this.explode(level);
        
    }
    
    protected void explode(Level level) throws SlickException {
        hasExploded = true;
        
        explosions.add(new Explosion(this.x, this.y));
        
        ArrayList<Integer[]> posXCoords = new ArrayList<Integer[]>();
        ArrayList<Integer[]> negXCoords = new ArrayList<Integer[]>();
        ArrayList<Integer[]> posYCoords = new ArrayList<Integer[]>();
        ArrayList<Integer[]> negYCoords = new ArrayList<Integer[]>();
        for (int i = 1; i <= range; i++) {
            posXCoords.add(new Integer[]{(int) this.x + i * 32, (int) this.y});
            negXCoords.add(new Integer[]{(int) this.x - i * 32, (int) this.y});
            posYCoords.add(new Integer[]{(int) this.x, (int) this.y + i * 32});
            negYCoords.add(new Integer[]{(int) this.x, (int) this.y - i * 32});
        }
        
        checkExplosionsWithWalls(posXCoords, negXCoords, posYCoords, negYCoords, level.walls);
        checkExplosionsWithBlocks(posXCoords, negXCoords, posYCoords, negYCoords, level.blocks);
        checkExplosionsWithBombs(posXCoords, negXCoords, posYCoords, negYCoords, level);
        
        for (Integer[] coord : posXCoords)
            explosions.add(new Explosion(coord[0], coord[1]));
        for (Integer[] coord : negXCoords)
            explosions.add(new Explosion(coord[0], coord[1]));
        for (Integer[] coord : posYCoords)
            explosions.add(new Explosion(coord[0], coord[1]));
        for (Integer[] coord : negYCoords)
            explosions.add(new Explosion(coord[0], coord[1]));
    }
    
    private void checkExplosionsWithWalls(ArrayList<Integer[]> posXCoords, ArrayList<Integer[]> negXCoords,
                                          ArrayList<Integer[]> posYCoords, ArrayList<Integer[]> negYCoords,
                                          ArrayList<Wall> walls) {
        checkExplosionsWithWallsHelper(posXCoords, walls);
        checkExplosionsWithWallsHelper(negXCoords, walls);
        checkExplosionsWithWallsHelper(posYCoords, walls);
        checkExplosionsWithWallsHelper(negYCoords, walls);
    }
    
    private void checkExplosionsWithWallsHelper(ArrayList<Integer[]> coords, ArrayList<Wall> walls) {
        boolean removeRest = false;
        for (Iterator<Integer[]> iter = coords.iterator(); iter.hasNext();) {
            Integer[] coord = iter.next();
            
            if (removeRest) {
                iter.remove();
                continue;
            }
            
            for (Wall wall : walls) {
                if(wall.x == coord[0] && wall.y == coord[1]) {
                    iter.remove();
                    removeRest = true;
                }
            }
        }
    }
    
    // TODO Randomize power-up drop
    private void checkExplosionsWithBlocks(ArrayList<Integer[]> posXCoords, ArrayList<Integer[]> negXCoords,
                                           ArrayList<Integer[]> posYCoords, ArrayList<Integer[]> negYCoords,
                                           ArrayList<Block> blocks) {
        checkExplosionsWithBlocksHelper(posXCoords, blocks);
        checkExplosionsWithBlocksHelper(negXCoords, blocks);
        checkExplosionsWithBlocksHelper(posYCoords, blocks);
        checkExplosionsWithBlocksHelper(negYCoords, blocks);
    }
    
    private void checkExplosionsWithBlocksHelper(ArrayList<Integer[]> coords, ArrayList<Block> blocks) {
        boolean removeRest = false;
        for (Iterator<Integer[]> iter = coords.iterator(); iter.hasNext();) {
            Integer[] coord = iter.next();
            
            if (removeRest) {
                iter.remove();
                continue;
            }
            
            for (Iterator<Block> blockIter = blocks.iterator(); blockIter.hasNext();) {
                Block block = blockIter.next();
                
                if(block.x == coord[0] && block.y == coord[1]) {
                    blockIter.remove();
                    removeRest = true;
                    break;
                }
            }
        }
    }
    
    private void checkExplosionsWithBombs(ArrayList<Integer[]> posXCoords, ArrayList<Integer[]> negXCoords,
                                          ArrayList<Integer[]> posYCoords, ArrayList<Integer[]> negYCoords,
                                          Level level) throws SlickException {
        checkExplosionsWithBombsHelper(posXCoords, level.bombs, level);
        checkExplosionsWithBombsHelper(negXCoords, level.bombs, level);
        checkExplosionsWithBombsHelper(posYCoords, level.bombs, level);
        checkExplosionsWithBombsHelper(negYCoords, level.bombs, level);
    }
    
    private void checkExplosionsWithBombsHelper(ArrayList<Integer[]> coords, ArrayList<Bomb> bombs, Level level) throws SlickException {
        boolean removeRest = false;
        for (Iterator<Integer[]> iter = coords.iterator(); iter.hasNext();) {
            Integer[] coord = iter.next();
            
            if (removeRest) {
                iter.remove();
                continue;
            }
            
            for (Bomb bomb : bombs) {
                if(bomb.x == coord[0] && bomb.y == coord[1]) {
                    iter.remove();
                    if(!bomb.hasExploded) {
                        bomb.explode(level);
                    }
                    removeRest = true;
                    break;
                }
            }
        }
    }

    @Override
    public void render() {
        if (!hasExploded) {
            super.render();
        } else {
            for (Explosion explosion : explosions) 
                explosion.render();
        }
    }

    
    /**
     * Method that returns if the bomb is active.
     * 
     * @return whether the bomb is active or not 
     */
    public boolean isActive() {
        return isActive;
    }
    
    public boolean hasExploded() {
        return hasExploded;
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

    public int getExplosionTime() {
        return explosionTime;
    }

    public void setRange(int range) {
        this.range = range;
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
