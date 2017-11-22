/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.scene.control.ProgressBar;

/**
 * This class represent the progress bar of the score.
 * @author s_u_y_s_a
 */
public class GUIColoredProgressBar {
    
    private final ProgressBar bar;
    /**
     * Creates an instance of a GUIColoredProgressBar
     * @param styleClass the color of the bar, stocked in a css sheet.
     */
    public GUIColoredProgressBar(String styleClass) {
        bar = new ProgressBar();
        bar.getStyleClass().add(styleClass);
        bar.setMinWidth(300);
        bar.setProgress(0.50F);
    }
    
    /**
     * Update the progress bar by changing its percentage of completion.
     * @param othello the model of the game being played.
     */
    public void updateProgressBar(OthelloModel othello){
        double score1 = othello.getScorePlayer1();
        double score2 = othello.getScorePlayer2();
        double nbPawnsOnBoard = score1 + score2;
        double pcProgress = score1 / nbPawnsOnBoard;
        bar.setProgress(pcProgress);
    }
    
    /**
     * Returns the ProgressBar representing the bar of progress.
     * @return the ProgressBar representing the bar of progress.
     */
    public ProgressBar getBar() {
        return bar;
    }
    
    
}