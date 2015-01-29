package com.jackrl.bomberguygame.domain;

import java.util.Random;
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
}
