package com.jackrl.bomberguygame.game.states;

import com.jackrl.bomberguygame.util.Controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * <p>A Slick2D state that represents the start screen.</p>
 */
public class StartState extends BasicGameState {
    private Controller controller;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        controller = new Controller(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Press Space or Start!", 200, 200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        controller.controlInStartScreen();
    }
    
    @Override
    public int getID() {
        return State.START;
    }
}
