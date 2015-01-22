
package com.jackrl.bomberguygame.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;


public class BombTest {
        
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
    
    // Contructor test
    @Test
    public void testContructorDoesntFailAndSetsTheBombActive() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        assertTrue("The bomb should be active upon creation!", bomb.isActive());
    }
    
    // Testing updating the bomb
    @Test
    public void testUpdateAdvancesTheTimer() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        int timerStart = bomb.getTimer();
        
        int delta1 = 500;
        int delta2 = 133;
        
        bomb.update(delta1);
        bomb.update(delta2);
        
        assertEquals(timerStart - (delta1 + delta2), bomb.getTimer());
        assertTrue("The bomb should still be active", bomb.isActive());
    }
    
    @Test
    public void testUpdateDeactivatesBombWhenTimerGetsToZero() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        int timerStart = bomb.getTimer();
        
        bomb.update(timerStart);
        
        assertFalse("The bomb should be deactivated when the timer reaches 0!", bomb.isActive());
    }
    
    @Test
    public void testUpdateDeactivatesBombWhenTimerGoesBellowZero() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        int timerStart = bomb.getTimer();
        
        bomb.update(timerStart + 500);
        
        assertFalse("The bomb should be deactivated when the timer is less than 0!", bomb.isActive());
    }
    
    // Test setting the location of the bomb
    @Test
    public void testSetLocationCopiesThePlayersLocationToTheBomb() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        bomb.setLocation();
        
        assertArrayEquals(new float[]{player.getX(), player.getY()}, new float[]{bomb.getX(), bomb.getY()}, 0);
    }
}
