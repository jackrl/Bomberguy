package com.jackrl.bomberguygame.game.states;

import com.jackrl.bomberguygame.domain.Bomb;
import com.jackrl.bomberguygame.domain.Player;
import com.jackrl.bomberguygame.util.Controller;
import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level1State extends BasicGameState {
    private Player player;
    private Controller controller;
    
    // Keep a list of the bombs that have been thrown
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player = new Player(0, 0);
        controller = new Controller(container, game);  
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //The background color is just for testing purpouses
        g.setBackground(Color.lightGray);
        
        player.render();
        for (Bomb bomb : bombs)
            bomb.render();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        controller.controlInLevel(player, bombs, delta);
        
        // Update the bombs with the time passed and give it back to the player if it isn't active anymore
        for (Iterator<Bomb> iterator = bombs.iterator(); iterator.hasNext();) {
            Bomb bomb = iterator.next();
            bomb.update(delta);
                if (!bomb.isActive()) {
                    bomb.returnToPlayer();
                    iterator.remove();
                }         
        }
    }
    
    @Override
    public int getID() {
        return State.LEVEL_1;
    }
}
