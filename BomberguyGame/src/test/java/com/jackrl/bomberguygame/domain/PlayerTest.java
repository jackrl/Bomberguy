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
    
    // Testing movement
    @Test
    public void testMovementInAllDirtections() throws SlickException {
        Player player = new Player(0, 0);
        
        int verticalDelta1 = 50;
        int verticalDelta2 = 75;
        int horizontalDelta1 = 60;
        int horizontalDelta2 = 34;
        
        player.moveDown(verticalDelta1);
        player.moveUp(verticalDelta2);
        player.moveRight(horizontalDelta1);
        player.moveLeft(horizontalDelta2);
        
        assertArrayEquals(new float[]{(horizontalDelta1 - horizontalDelta2) * player.speed,
                                      (verticalDelta1 - verticalDelta2) * player.speed},
                          new float[]{player.x, player.y}, 0);
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
