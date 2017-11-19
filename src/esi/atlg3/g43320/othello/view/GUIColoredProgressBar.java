/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author s_u_y_s_a
 */
class GUIColoredProgressBar extends ProgressBar {
        
    public GUIColoredProgressBar(String styleClass) {
        super();
        getStyleClass().add(styleClass);
        setMinWidth(300);
        setProgress(0.50F);
    }
    
    public void updateProgressBar(OthelloModel othello){
        double score1 = othello.getScorePlayer1();
        double score2 = othello.getScorePlayer2();
        double nbPawnsOnBoard = score1 + score2;
        double pcProgress = score1 / nbPawnsOnBoard;
        setProgress(pcProgress);
    }
}