package com.jackrl.bomberguygame.domain;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

public class PlayerTest {
    
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
    public void testContructorWithCoordinatesx0y0() throws SlickException {
        Player player = new Player(0, 0);
        assertArrayEquals(new float[]{0, 0}, new float[]{player.x, player.y}, 0);
        
    }
    
    @Test
    public void testContructorWithRandomCoordinates() throws SlickException {
        Random rand = new Random();
        
        float randX = rand.nextFloat();
        float randY = rand.nextFloat();
        Player player = new Player(randX, randY);
        assertArrayEquals(new float[]{randX, randY}, new float[]{player.x, player.y}, 0);
    }
    
    // Test moves
    @Test
    public void testMoveRight() throws SlickException {
        Player player = new Player(0, 0);
        player.moveRight();

        assertArrayEquals(new int[]{1}, new int[]{player.dx});
    }
    
    @Test
    public void testMoveLeft() throws SlickException {
        Player player = new Player(0, 0);
        player.moveLeft();

        assertArrayEquals(new int[]{-1}, new int[]{player.dx});
    }
    
    @Test
    public void testMoveUp() throws SlickException {
        Player player = new Player(0, 0);
        player.moveUp();

        assertArrayEquals(new int[]{-1}, new int[]{player.dy});
    }
    
    @Test
    public void testMoveDown() throws SlickException {
        Player player = new Player(0, 0);
        player.moveDown();

        assertArrayEquals(new int[]{1}, new int[]{player.dy});
    }
    
    @Test
    public void testMoveXWithAPreviousMoveRight() throws SlickException {
        Player player = new Player(0, 0);
        player.moveRight();
        player.moveX(1000);

        assertArrayEquals(new float[]{1000 * player.maxSpeed}, new float[]{player.x}, 0);
    }
    
    @Test
    public void testMoveYWithAPreviousMoveDown() throws SlickException {
        Player player = new Player(0, 0);
        player.moveDown();
        player.moveY(1000);

        assertArrayEquals(new float[]{1000 * player.maxSpeed}, new float[]{player.y}, 0);
    }
    
    // Test resetting dx and dy
    @Test
    public void testResetDxAndDy() throws SlickException {
        Player player = new Player(0, 0);
        player.moveRight();
        player.moveRight();
        player.resetDxAndDy();

        assertArrayEquals(new int[]{0, 0}, new int[]{player.dx, player.dy});
    }
    
    // Testing bomb throwing
    @Test
    public void testThrowBombAllowsThePlayerToThroughBomb() throws SlickException {
        Player player = new Player(0, 0);
        ArrayList<Bomb> levelBombs = new ArrayList<Bomb>();
        
        player.throwBomb(levelBombs);
        assertArrayEquals(new int[]{1}, new int[]{levelBombs.size()});
    }
    
    @Test
    public void testThrowBombDoesntAllowThePlayerToThroughASecondBomb() throws SlickException {
        Player player = new Player(0, 0);
        ArrayList<Bomb> levelBombs = new ArrayList<Bomb>();
        
        player.throwBomb(levelBombs);
        player.throwBomb(levelBombs);
        assertArrayEquals(new int[]{1}, new int[]{levelBombs.size()});
    }
    
    // Testing the addition of more bombs and throwing them
    @Test
    public void testAddBombByBeingAbleToThroughTwoBombsConsecutivelyAfterMovingAwayFromTheFirstBomb() throws SlickException {
        Player player = new Player(0, 0);
        player.addBomb(new Bomb(player));
        ArrayList<Bomb> levelBombs = new ArrayList<Bomb>();
        
        player.throwBomb(levelBombs);
        
        player.notSandingOnBomb();
        
        player.throwBomb(levelBombs);
        assertArrayEquals(new int[]{2}, new int[]{levelBombs.size()});
    }
    
    @Test
    public void testThrowBombByNotBeingAbleToThroughTwoBombsConsecutivelyIfThePlayerIsStandingOnTheFirstBomb() throws SlickException {
        Player player = new Player(0, 0);
        player.addBomb(new Bomb(player));
        ArrayList<Bomb> levelBombs = new ArrayList<Bomb>();

        player.throwBomb(levelBombs);
        player.throwBomb(levelBombs);
        assertArrayEquals(new int[]{1}, new int[]{levelBombs.size()});
    }
    
    // Test getThrownBomb and isStandingOnBomb
    @Test
    public void testGetThrownBombReturnsNullBeforeABobmbHasBeenThrown() throws SlickException {
        Player player = new Player(0, 0);
        assertNull("getThrownBomb() should return null at the beginning", player.getThrownBomb());
    }
    
    @Test
    public void testGetThrownBombReturnsABombAfterABobmbHasBeenThrown() throws SlickException {
        Player player = new Player(0, 0);
        player.throwBomb(new ArrayList<Bomb>());
        assertNotNull("getThrownBomb() should not return null after throwing a bomb", player.getThrownBomb());
    }
    
    @Test
    public void testIsStandingOnBombShouldReturnFalseBeforeThrowingABomb() throws SlickException {
        Player player = new Player(0, 0);
        assertFalse("isStandingOnBomb() should return false at the beginning", player.isStandingOnBomb());
    }
    
    @Test
    public void testIsStandingOnBombShouldReturnTrueAfterABobmbHasBeenThrown() throws SlickException {
        Player player = new Player(0, 0);
        player.throwBomb(new ArrayList<Bomb>());
        assertTrue("isStandingOnBomb() should return true after throwing a bomb", player.isStandingOnBomb());
    }
    
    // Test Death
    @Test
    public void testPlayerIsAliveWhenreated() throws SlickException {
        Player player = new Player(0, 0);
        assertFalse("Player should be alive upon creation!", player.isDead());
    }
    
    @Test
    public void testDieKillsThePlayer() throws SlickException {
        Player player = new Player(0, 0);
        player.die();
        assertTrue("Player should be dead after it is killed!", player.isDead());
    }
    
    // Test Entities collision method
    @Test
    public void testPlayerNonCollisionWithWall() throws SlickException {
        Player player = new Player(0, 0);
        Wall wall = new Wall(100, 100);
        assertFalse("The player shouldn't have collided with the wall!", player.collidesWith(wall));
    }
    
    @Test
    public void testPlayerCollisionWithWall() throws SlickException {
        Player player = new Player(0, 0);
        Wall wall = new Wall(16, 16);
        assertTrue("The player should have collided with the wall!", player.collidesWith(wall));
    }
}
