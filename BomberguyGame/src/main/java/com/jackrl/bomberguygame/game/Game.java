package com.jackrl.bomberguygame.game;

import com.jackrl.bomberguygame.game.states.Level1State;
import com.jackrl.bomberguygame.game.states.MenuState;
import com.jackrl.bomberguygame.game.states.StartState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "BomberGuy";
    static int fpslimit = 60;
    
    public Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        //this.addState(new StartState());
        //this.addState(new MenuState());
        this.addState(new Level1State());
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
