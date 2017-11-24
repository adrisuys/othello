/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.dpObs;

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
     * Notifies the observers.
     */
    public void notifyObservers();

}
