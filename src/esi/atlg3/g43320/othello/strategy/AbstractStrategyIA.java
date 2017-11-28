/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

/**
 * This class represent a play following some specified strategy. As it is
 * abstract, all the classes that will extend it will precise those strategies.
 *
 * @author s_u_y_s_a
 */
public abstract class AbstractStrategyIA implements StrategyIA {

    /**
     * Plays a turn following a certain strategy.
     *
     * @param name the name of the player.
     */
    @Override
    public void play(String name) {

    }

}
