
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
    public void testSetLocationSetsTheBombsLocationToTheCenterOfATile() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        bomb.setLocation();
        
        assertArrayEquals(new int[]{96, 160}, new int[]{(int) (bomb.getX()), (int) (bomb.getY())});
    }
    
    // Test that return to player
    @Test
    public void testReturnToPlayerResetsTheBomb() throws SlickException {
        Player player = new Player(100, 150);
        Bomb bomb = new Bomb(player);
        
        bomb.returnToPlayer();
        
        assertTrue("The bomb should be active after it has been returned to the player", bomb.isActive());
    }
    
    // Test equals
    @Test
    public void testEqualsWithANullObject() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Bomb bomb2 = null;
        
        assertFalse("Equals should return false when comapring to null!", bomb1.equals(bomb2));        
    }
    
    @Test
    public void testEqualsWithAnotherClass() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Player player = new Player(0, 0);
        
        assertFalse("Equals should return false when comapring to a player!", bomb1.equals(player));        
    }
    
    @Test
    public void testEqualsWithAnEqualBomb() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Bomb bomb2 = new Bomb(null);
        bomb2.x = 100;
        bomb2.y = 150;
        
        assertTrue("Equals should return true when comapring two bombs with the same location!", bomb1.equals(bomb2));
    }
    
    @Test
    public void testEqualsWithABombWithDifferentX() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Bomb bomb2 = new Bomb(null);
        bomb1.x = 300;
        bomb1.y = 150;
        
        assertFalse("Equals should return false when comapring two bombs with different X!", bomb1.equals(bomb2));
    }
    
    @Test
    public void testEqualsWithABombWithDifferentY() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Bomb bomb2 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 300;
        
        assertFalse("Equals should return false when comapring two bombs with different Y!", bomb1.equals(bomb2));
    }
    
    @Test
    public void testEqualsWithABombWithDifferentXandY() throws SlickException {
        Bomb bomb1 = new Bomb(null);
        bomb1.x = 100;
        bomb1.y = 150;
        
        Bomb bomb2 = new Bomb(null);
        bomb1.x = 300;
        bomb1.y = 300;
        
        assertFalse("Equals should return false when comapring two bombs with different X and Y!", bomb1.equals(bomb2));
    }
}
