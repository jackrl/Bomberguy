package com.jackrl.bomberguygame.game.states;

import com.jackrl.bomberguygame.domain.Player;
import com.jackrl.bomberguygame.util.Controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level1State extends BasicGameState {
    private Player player;
    private Controller controller;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player = new Player(0, 0);
        controller = new Controller(container, game);  
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        player.render();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        controller.controlInLevel(player, delta);
    }
    
    @Override
    public int getID() {
        return State.LEVEL_1;
    }
}
