package com.jackrl.bomberguygame.domain;

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
        assertArrayEquals(new float[]{0,0}, new float[]{player.x, player.y}, 0);
        
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

        assertNotNull("The player should be able to throw a bomb after it has been created!", player.throwBomb());
    }
    
    @Test
    public void testThrowBombDoesntAllowThePlayerToThroughASecondBomb() throws SlickException {
        Player player = new Player(0, 0);
        
        player.throwBomb();
        assertNull("The player shouldn't be able to throw another bomb before the previous one has exploded!", player.throwBomb());
    }
    
    // Testing the addition of more bombs
    @Test
    public void testAddBombByBeingAbleToThroughTwoBombsConsecutively() throws SlickException {
        Player player = new Player(0, 0);
        player.addBomb(new Bomb(player));
        
        player.throwBomb();
        assertNotNull("The player should be able to throw a second bomb after the first one as it was added to the player!", player.throwBomb());
        
        assertNull("The player shouldn't be able to throw a third bomb!", player.throwBomb());
    }
    
        
}
