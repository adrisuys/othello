/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.dp;

/**
 * The interface Observer contains all the methods classes have to have if they
 * want to implement this interface.
 *
 * @author s_u_y_s_a
 */
public interface Observer {

    /**
     * When notified of a play, this object updates itself.
     */
    public void updatePlay();

    /**
     * When notified that a players wants to see the score, this object updates
     * itself.
     */
    public void updateScore();

    /**
     * When notified that a players wants to see the board, this object updates
     * iteself.
     */
    public void updateShow();

    /**
     * When notified that the game has been initialized, this object updates
     * itself.
     */
    public void updateInit();

    /**
     * When notified that a pawn can't be put on a given coordinates, this
     * object updates itself.
     */
    public void updateErrorInputCoordinates();

    /**
     * When notified that the player has entered a wrong command, this object
     * updates itself.
     */
    public void updateErrorInputCommand();

}
