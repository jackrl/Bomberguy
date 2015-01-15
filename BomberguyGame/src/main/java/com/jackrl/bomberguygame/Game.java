package com.jackrl.bomberguygame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Game extends BasicGame {
 
    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Slick2D Basic Game Template";
    static int fpslimit = 60;
    
    //Testing
    private Shape circle1 = null;
    private Shape circle2 = null;
   
    public Game(String title) {
        super(title);
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
       // Testing
        circle1 = new Circle(width/2, height/2, 100);
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Testing
        g.setColor(new Color(0xFFFFFF));
        g.fill(circle1);
        
        g.setColor(new Color(0xFF0000));
        g.draw(circle1);
    
    }
   
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        
        
        
        app.start();
    }
   
}
