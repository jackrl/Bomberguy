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

public class CollisionsTest {
    private Collisions collisions;
    private Level level;
    
    @Before
    public void setUp() throws LWJGLException, SlickException {
        // Creating an openGL context for the tests to work
        Display.create();
        
        level = new Level(new String[] {"             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             ",
                                        "             "});
        
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Wall(100, 100));
        
        ArrayList<Bomb> bombs = new ArrayList<Bomb>();
        Player player = new Player(200, 200);
        player.throwBomb(bombs);
        
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(300, 300));
        
        
        collisions = new Collisions(walls, bombs, blocks);
    }
    
    @After
    public void tearDown() {
        // Destroying the openGL context as only one can be set at a time
        Display.destroy();
    }

    // Test player collisions with walls.
    @Test
    public void testPlayerCollisionWithWallFromTheLeft() throws SlickException {
        Player player = new Player(90, 100);
        player.dx = 1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{90 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }

    @Test
    public void testPlayerCollisionWithWallFromTheRight() throws SlickException {
        Player player = new Player(110, 100);
        player.dx = -1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{110 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }
    
    @Test
    public void testPlayerCollisionWithWallFromTheTop() throws SlickException {
        Player player = new Player(100, 90);
        player.dy = 1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{90 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }

    @Test
    public void testPlayerCollisionWithWallFromTheBottom() throws SlickException {
        Player player = new Player(100, 110);
        player.dy = -1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{110 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }
    
    // Test player collisions with Bombs.
    @Test
    public void testPlayerCollisionWithBombFromTheLeft() throws SlickException {
        Player player = new Player(190, 200);
        player.dx = 1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{190 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }

    @Test
    public void testPlayerCollisionWithBombFromTheRight() throws SlickException {
        Player player = new Player(210, 200);
        player.dx = -1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{210 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }
    
    @Test
    public void testPlayerCollisionWithBombFromTheTop() throws SlickException {
        Player player = new Player(200, 190);
        player.dy = 1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{190 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }

    @Test
    public void testPlayerCollisionWithBombFromTheBottom() throws SlickException {
        Player player = new Player(200, 210);
        player.dy = -1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{210 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }
    
    // Test player collisions with blocks.
    @Test
    public void testPlayerCollisionWithBlockFromTheLeft() throws SlickException {
        Player player = new Player(290, 300);
        player.dx = 1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{290 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }

    @Test
    public void testPlayerCollisionWithBlockFromTheRight() throws SlickException {
        Player player = new Player(310, 300);
        player.dx = -1;
        
        int delta = 99;
        collisions.checkCollisionsX(player, delta);

        assertArrayEquals(new float[]{310 - player.dx * delta * player.maxSpeed}, new float[]{player.x}, 0);
    }
    
    @Test
    public void testPlayerCollisionWithBlockFromTheTop() throws SlickException {
        Player player = new Player(300, 290);
        player.dy = 1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{290 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }

    @Test
    public void testPlayerCollisionWithBlockFromTheBottom() throws SlickException {
        Player player = new Player(300, 310);
        player.dy = -1;
        
        int delta = 99;
        collisions.checkCollisionsY(player, delta);

        assertArrayEquals(new float[]{310 - player.dy * delta * player.maxSpeed}, new float[]{player.y}, 0);
    }
    
    // Test player collsions with enemies
    @Test
    public void testPlayerNonCollisionWithEnemyDoesntKillThePlayer() throws SlickException {
        Player player = new Player(0, 0);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(100, 100));
        
        collisions.checkCollisionsWithEnemies(player, enemies);

        assertFalse("Player shouldn't be dead!", player.isDead);
    }
    
    @Test
    public void testPlayerCollisionWithEnemyKillsThePlayer() throws SlickException {
        Player player = new Player(0, 0);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(10, 10));
        
        collisions.checkCollisionsWithEnemies(player, enemies);

        assertTrue("Player should be dead after colliding with an enemy!", player.isDead);
    }
    
    // Test player collsions with explosions
    @Test
    public void testPlayerCollisionWithExplosionKillsThePlayer() throws SlickException {
        Player player = new Player(0, 0);
        ArrayList<Bomb> bombs = new ArrayList<Bomb>();
        player.throwBomb(bombs);
        player.x = 10;
        player.y = 10;
        
        bombs.get(0).explode(level);
        
        collisions.checkCollisionsWithExplosions(player, bombs);
        assertTrue("Player should be dead after colliding with an explosion!", player.isDead);
    }
    
    // Test player collisions with powerUps
    @Test
    public void testPlayerCollisionWithBombPowerUpGivesThePlayerAnotherBomb() throws SlickException {
        Player player = new Player(10, 10);
        ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
        powerUps.add(new BombPowerUp(0, 0));
        
        collisions.checkCollisionsWithPowerUps(player, powerUps);
        assertTrue("Player should have two bombs after collecting a bomb power-up!", player.bombs.size() == 2);
    }
    
    @Test
    public void testPlayerCollisionWithExplosionPowerUpGivesThePlayerIncresedRange() throws SlickException {
        Player player = new Player(10, 10);
        ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
        powerUps.add(new ExplosionPowerUp(0, 0));
        
        collisions.checkCollisionsWithPowerUps(player, powerUps);
        assertTrue("Player should have a range of 2 after collecting an explsoion power-up!", player.range == 2);
    }
}
