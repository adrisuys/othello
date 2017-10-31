/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.dp;

/**
 * The interface Observable contains all the methods classes have to have if they want to implement this interface.
 * @author s_u_y_s_a
 */
public interface Observable {
    
    public void registerObserver(Observer obs);

    public void removeObserver(Observer obs);

    public void notifyObserversPlay();
    
    public void notifyObserversScore();
    
    public void notifyObserversShow();
    
    public void notifyObserversInit();
    
    public void notifyObserversErrorInputCommand();
    
    public void notifyObserversErrorInputCoordinates();
    
}
