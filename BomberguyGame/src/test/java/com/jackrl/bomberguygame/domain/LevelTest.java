package com.jackrl.bomberguygame.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

public class LevelTest {
    
    @Before
    public void setUp() throws LWJGLException {
        // Creating an openGL context for the tests to work
        Display.create();
    }
    
    @After
    public void tearDown() {
        // Destroying the openGL context as only one can be set at a time
        Display.destroy();
    }

    // Contructor tests
    @Test
    public void testContructorWorksWithoutErrorsAndTheWallsArrayListIsntEmpty() throws SlickException {
        Level level = new Level();
        
        assertFalse("The walls ArrayList should contain the walls of the level!", level.walls.isEmpty());
    }
    
    // Test that rendering doesn't fail
    @Test
    public void testRenderDoesntThrowAnException() throws SlickException {
        Level level = new Level();
        level.bombs.add(new Bomb(null));
        
        try {
            level.render();
        } catch (Exception e) {
            assertFalse("Rendering failed. There is probably something wrong with the images!", true);
        }   
    }
    
    // Test updateBombs
    @Test
    public void testUpdateBombUpdatesTheBombsTimer() throws SlickException {
        Level level = new Level();
        
        Player player = new Player(32, 32);
        Bomb bomb = new Bomb(player);
        level.bombs.add(bomb);
        player.throwBomb(level.bombs);
        
        int start = bomb.getTimer();
        
        int delta = 1000;
        level.updateBombs(delta);
        
        assertArrayEquals(new int[] {start - delta}, new int[] {bomb.getTimer()});
    }
    
    @Test
    public void testUpdateBombReturnsAnExplodedBombToThePlayer() throws SlickException {
        Level level = new Level();
        
        Player player = new Player(32, 32);
        Bomb bomb = new Bomb(player);
        level.bombs.add(bomb);
        player.throwBomb(level.bombs);
        
        int start = bomb.getTimer();
        
        int delta = 10000;
        level.updateBombs(delta);
        
        assertArrayEquals(new int[] {0}, new int[] {level.getBombs().size()});
    }
}
