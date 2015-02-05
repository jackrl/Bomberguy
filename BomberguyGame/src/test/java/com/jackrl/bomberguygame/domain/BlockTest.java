package com.jackrl.bomberguygame.domain;

import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

public class BlockTest {
    
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
        Block block = new Block(0, 0);
        assertArrayEquals(new float[]{0,0}, new float[]{block.x, block.y}, 0);
    }
    
    @Test
    public void testContructorWithRandomCoordinates() throws SlickException {
        Random rand = new Random();
        
        float randX = rand.nextFloat();
        float randY = rand.nextFloat();
        Block block = new Block(randX, randY);
        assertArrayEquals(new float[]{randX, randY}, new float[]{block.x, block.y}, 0);
    }
}
