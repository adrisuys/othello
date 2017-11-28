/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

/**
 * This interface represent a play being played following a certain strategy.
 * Those strategies are specified in the class implementing the interface.
 *
 * @author s_u_y_s_a
 */
public interface StrategyIA {

    /**
     * Plays following a certain strategy.
     *
     * @param name
     */
    public void play(String name);
}
