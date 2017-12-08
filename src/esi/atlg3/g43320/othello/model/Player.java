/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.strategy.HumanStrategy;
import esi.atlg3.g43320.othello.strategy.Strategy;

/**
 * The class represents a player and a player is defined by the color of its
 * pawns.
 *
 * @author s_u_y_s_a
 */
public class Player {

    private ColorPawn color;
    private Strategy strategy;
    private String name;
    private boolean isIA;

    /**
     * Creates an instance of a player by giving it a specified color.
     *
     * @param color
     */
    public Player(ColorPawn color) {
        if (color == null) {
            throw new IllegalArgumentException("the color or strategy can't be null");
        }
        this.color = color;
        this.strategy = new HumanStrategy();
        this.name = "";
        this.isIA = false;
    }

    /**
     * Returns the color of the player.
     *
     * @return the color of the player.
     */
    public ColorPawn getColor() {
        return color;
    }

    /**
     * Change the color of the player.
     *
     * @param color the new color.
     */
    public void setColor(ColorPawn color) {
        this.color = color;
    }
    
    /**
     * Makes the player plays according to its own strategy.
     */
    public void runStrategy(){
        strategy.execute();
    }

    /**
     * Returns the strategy of the player.
     * @return the strategy of the player.
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Changes the strategy of the player.
     * @param strategy the new strategy.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Change the name of the player.
     * @param name the new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the boolean indicating if the player is an IA or not.
     * @return true if the player is an IA, false otherwise.
     */
    public boolean isIsIA() {
        return isIA;
    }

    /**
     * Changes the value of the boolean isIA.
     * @param isIA the new value (true or false).
     */
    public void setIsIA(boolean isIA) {
        this.isIA = isIA;
    }
    
    
    
    

}
