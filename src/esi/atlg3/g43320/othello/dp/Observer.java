/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.dp;

/**
 *The interface Observer contains all the methods classes have to have if they want to implement this interface.
 * @author s_u_y_s_a
 */
public interface Observer {
    
    public void updatePlay();
    
    public void updateScore();
    
    public void updateShow();
    
    public void updateInit();
    
    public void updateErrorInputCoordinates();
    
    public void updateErrorInputCommand();
    
}
