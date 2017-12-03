/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 *
 * @author s_u_y_s_a
 */
public class GameException extends Exception {
    
    /**
     * Creates a new instance of GameException without detail
     * message.
     */
    public GameException() {
    }

    /**
     * Constructs an instance of GameException with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameException(String msg) {
        super(msg);
    }
    
}
