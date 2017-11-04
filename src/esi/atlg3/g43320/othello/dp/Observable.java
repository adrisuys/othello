/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.dp;

/**
 * The interface Observable contains all the methods classes have to have if
 * they want to implement this interface.
 *
 * @author s_u_y_s_a
 */
public interface Observable {

    /**
     * Adds an observer to its list. The observer can now observe this object.
     *
     * @param obs the new observer
     */
    public void registerObserver(Observer obs);

    /**
     * Removes an observer of its list, this observer has no access anymore.
     *
     * @param obs the observer to be removed.
     */
    public void removeObserver(Observer obs);

    /**
     * Notifies the observers when a player plays.
     */
    public void notifyObserversPlay();

    /**
     * Notifies the observers when a player asks to see the score.
     */
    public void notifyObserversScore();

    /**
     * Notifies the observers when a player asks to see the board at a given
     * time.
     */
    public void notifyObserversShow();

    /**
     * Notifies the observers when the game is being initialized.
     */
    public void notifyObserversInit();

    /**
     * Notifies the observers when the player typed a wrong command (not amongs
     * the following : "score", "show", "play x y").
     */
    public void notifyObserversErrorInputCommand();

    /**
     * Notifies the observers when the player wants to put a pawn on a non-valid
     * case of the board.
     */
    public void notifyObserversErrorInputCoordinates();

}
