/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represent a moves which is descriped by 
 *   - the name of the player
 *   - the type of action he played (put a pawn, put a wall, pass)
 *   - the position of the case on which he played (if pawn or wall put)
 *   - the number of pawn he has taken when he played (if pawn put)
 * @author s_u_y_s_a
 */
public class Move {
    
    private final String name;
    private final String action;
    private final String pos;
    private final String taken;
 
    /**
     * Creates an instance of a Move.
     * @param name the name of the player
     * @param action the type of action the player played
     * @param pos the position on which the player put a pawn or a wall
     * @param taken the numnber of pawn he has taken when putting a pawn.
     */
    public Move(String name, String action, String pos, String taken) {
        this.name = name;
        this.action = action;
        this.pos = pos;
        this.taken = taken;
        
    }

    /**
     * Get the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of action he played.
     * @return the type of action he played.
     */
    public String getAction() {
        return action;
    }

    /**
     * Get the position at which he played.
     * @return the position at which he played.
     */
    public String getPos() {
        return pos;
    }

    /**
     * Get the number of pawn he has taken.
     * @return the number of pawn he has taken.
     */
    public String getTaken() {
        return taken;
    }
    
    
        
}
