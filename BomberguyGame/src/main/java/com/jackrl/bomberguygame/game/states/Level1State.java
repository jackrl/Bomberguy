package com.jackrl.bomberguygame.game.states;

import com.jackrl.bomberguygame.domain.Level;
import com.jackrl.bomberguygame.domain.Player;
import com.jackrl.bomberguygame.util.Controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * <p>A Slick2D state that represents the first level.</p>
 */
public class Level1State extends BasicGameState {
    private Player player;
    private Controller controller;
    private Level level;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        reset(container, game);
    }
    
    private void reset(GameContainer container, StateBasedGame game) throws SlickException {
        level = new Level(new String[] {"#############",
                                        "#  @ @ @ @  #",
                                        "# # # # # # #",
                                        "#@ @ @ @ @ @#",
                                        "# # # # # # #",
                                        "#@ @ @ @ @ @#",
                                        "# # # # # # #",
                                        "#@ @ @ @ @ @#",
                                        "# # # # # # #",
                                        "#@ @ @ @ @ @#",
                                        "# # # # # # #",
                                        "#  @ @ @ @ !#",
                                        "#############"});
        player = new Player(32, 32);
        controller = new Controller(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        level.render();

        player.render();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        level.updateBombs(delta);
        level.updateEnemies(delta);
        
        // Player controll
        controller.controlInLevel(player, level.getBombs());
        
        // Move and check collisions
        player.moveX(delta);
        level.checkCollisionsX(player, delta);
        player.moveY(delta);
        level.checkCollisionsY(player, delta);

        // Reset dx and dy for next update
        player.resetDxAndDy();
        
        // Check deadly collisions
        level.checkCollisionsWithEnemies(player);      
        level.checkCollisionsWithExplosions(player);
        
        // Check collsions with powerUps
        level.checkCollisionsWithPowerUps(player);
        
        // Reset level if palyer dies
        if (player.isDead())
            reset(container, game);
        
        // Go to next level or to final screen if there are no enemies left
        if (level.hasEnded()) {
            reset(container, game);
            game.enterState(State.LEVEL_2);
        }
    }
    
    @Override
    public int getID() {
        return State.LEVEL_1;
    }
}
