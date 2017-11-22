/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.scene.control.ProgressIndicator;

/**
 * This class represent a progress cake of the game ("Where are we at in the game?")
 * @author s_u_y_s_a
 */
public class GUIProgressCake{
    
    private ProgressIndicator progressCake;
    /**
     * Creates an instance of a GUIProgressCake.
     */
    public GUIProgressCake(){
        progressCake = new ProgressIndicator();
        progressCake.setProgress(0.0F);
    }
    
    /**
     * Updates the progressCake after each turn.
     * @param othello 
     */
    public void updateProgressCake(OthelloModel othello){
        double nbPawnsAlreadyPut = othello.getNbPawnsOnBoard();
        progressCake.setProgress(nbPawnsAlreadyPut/64);
    }

    /**
     * Returns the ProgressIndicator representing the cake of progress of the game.
     * @return the ProgressIndicator representing the cake of progress of the game.
     */
    public ProgressIndicator getProgressCake() {
        return progressCake;
    }
}
