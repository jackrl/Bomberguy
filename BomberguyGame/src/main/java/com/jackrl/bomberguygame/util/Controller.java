package com.jackrl.bomberguygame.util;

import com.jackrl.bomberguygame.domain.Bomb;
import com.jackrl.bomberguygame.domain.Player;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 * <p>The utility class that takes care of taking the input of the player.</p>
 */
public class Controller {

    private GameContainer container;
    private StateBasedGame game;

    /**
     * Constructor of the Controller class.
     * 
     * @param container
     * @param game 
     */
    public Controller(GameContainer container, StateBasedGame game) {
        this.container = container;
        this.game = game;
    }
    
    /**
     * Method that controls the input while in the start and end screens.
     */
    public boolean controlInStartAndEndScreen() {
        Input input = container.getInput();

        return input.isButtonPressed(7, 0) || input.isKeyPressed(Input.KEY_SPACE);
    }
    
    /**
     * Method that controls the input while in the level.
     * 
     * @param player    player to be controlled
     * @param bombs     array of bombs used by the level to store the thrown bombs
     */
    public void controlInLevel(Player player, ArrayList<Bomb> bombs) {
        Input input = container.getInput();
        
        // Movement controlls 
        if (input.isControllerRight(0) || input.isKeyDown(Input.KEY_RIGHT))
            player.moveRight();
        if (input.isControllerLeft(0) || input.isKeyDown(Input.KEY_LEFT)) 
            player.moveLeft();
        if (input.isControllerDown(0) || input.isKeyDown(Input.KEY_DOWN)) 
            player.moveDown();
        if (input.isControllerUp(0) || input.isKeyDown(Input.KEY_UP)) 
            player.moveUp();        
        
        // Throw bomb controll
        if (input.isButtonPressed(1, 0) || input.isKeyPressed(Input.KEY_SPACE)) {
            player.throwBomb(bombs);
        }
        
        // Pause controll
        if (input.isButtonPressed(7, 0) || input.isKeyPressed(Input.KEY_P)) {
            if (container.isPaused())
                container.setPaused(false);
            else
                container.pause();
        }
        
        // Closing the game with the contoller
        if (input.isButtonPressed(7, 0) && input.isButtonPressed(6, 0) &&
            input.isButtonPressed(5, 0) && input.isButtonPressed(4, 0) ||
            input.isKeyPressed(Input.KEY_ESCAPE))
            container.exit();
    }
}

/*
            SNES Controller mappings
            A -> 0
            B -> 1
            X -> 2
            Y -> 3
            LB -> 4
            RB -> 5
            SELECT -> 6
            START -> 7
*/
