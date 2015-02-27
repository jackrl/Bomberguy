package com.jackrl.bomberguygame.game;

import com.jackrl.bomberguygame.game.states.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;

/**
 * <p>A Slick2D state based game that sets up the basic settings and starts the game</p>
 */
public class Game extends StateBasedGame {

    static int width = 416;
    static int height = 416;
   
    static boolean fullscreen = false;
    static boolean showFPS = false;
    static String title = "BomberGuy";
    static int fpslimit = 60;
    
    public Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new StartState());
        this.addState(new Level1State());
        this.addState(new Level2State());
        this.addState(new EndState());
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
