/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.strategy.HumanStrategy;
import esi.atlg3.g43320.othello.strategy.Strategy;
import java.util.Objects;

/**
 * The class represents a player and a player is defined by the color of its
 * pawns.
 *
 * @author s_u_y_s_a
 */
public class Player {

    private ColorPawn color;
    private Strategy strategy;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.color == other.color;
    }

    @Override
    public String toString() {
        if (getColor() == ColorPawn.BLACK) {
            return "Player Black";
        } else {
            return "Player White";
        }
    }
    
    /**
     * Indicates if the player is an IA.
     * @return true if the player is an IA.
     */
    public boolean isAutomatic(){
        return strategy.isIA();
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
    
    
    
    
    
    

}
