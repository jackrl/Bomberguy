package com.jackrl.bomberguygame.util;

import com.jackrl.bomberguygame.domain.Player;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Controller {

    private GameContainer container;
    StateBasedGame game;

    public Controller(GameContainer container, StateBasedGame game) {
        this.container = container;
        this.game = game;
    }
    
    public void controlInLevel(Player player, int delta) {
        Input input = container.getInput();
        // Movement controlls 
        if(input.isControllerRight(0) || input.isKeyDown(Input.KEY_RIGHT))
            player.moveRight(delta);
        if(input.isControllerLeft(0) || input.isKeyDown(Input.KEY_LEFT)) 
            player.moveLeft(delta);
        if(input.isControllerDown(0) || input.isKeyDown(Input.KEY_DOWN)) 
            player.moveDown(delta);
        if(input.isControllerUp(0) || input.isKeyDown(Input.KEY_UP)) 
            player.moveUp(delta);
        
        // Throw bomb controll
        if (input.isButtonPressed(0, 0) || input.isKeyPressed(Input.KEY_SPACE)) {
            player.throwBomb();
        }
        
        // Pause controll
        if (input.isButtonPressed(7, 0) || input.isKeyPressed(Input.KEY_P)) {
            // TO-DO: Pause the game
        }
        
        // Closing the game with the contoller
        if(input.isButtonPressed(7, 0) && input.isButtonPressed(6, 0) &&
                input.isButtonPressed(5, 0) && input.isButtonPressed(4, 0))
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
