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
        g.drawString("BOMBERGUY", 165, 160);
        g.drawString("Press Space to start!", 115, 200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (controller.controlInStartAndEndScreen())
            game.enterState(State.LEVEL_1);
    }
    
    @Override
    public int getID() {
        return State.START;
    }
}
