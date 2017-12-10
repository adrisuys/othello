/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.ColorPawn;
import esi.atlg3.g43320.othello.model.GameModel;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * This class represent the score frame that has 2 box in it. One for each
 * player. Those box contains the name, the color of the pawn and the score of
 * the player.
 *
 * @author s_u_y_s_a
 */
public class GUIScoreFrame extends HBox {

    private GUIScoreFrame1Player score1Frame;
    private GUIScoreFrame1Player score2Frame;

    /**
     * Creates an instance of a GUIScoreFrame.
     */
    public GUIScoreFrame() {
        score1Frame = new GUIScoreFrame1Player(Color.BLACK, "AAA", "-fx-background-color: #c7f097;");
        score2Frame = new GUIScoreFrame1Player(Color.WHITE, "BBB", "-fx-background-color: #f08c6b;");
        getChildren().addAll(score1Frame, score2Frame);
        HBox.setMargin(score1Frame, new Insets(0, 20, 0, 0));

    }

    /**
     * Creates an instance of a GUIScoreFrame by updating the score with the
     * current one.
     *
     * @param othello the game that is being played.
     */
    public GUIScoreFrame(GameModel othello) {
        this();
        score1Frame.setScore(Integer.toString(othello.getScorePlayer1()));
        score2Frame.setScore(Integer.toString(othello.getScorePlayer2()));
    }

    /**
     * Creates an instance of GUIScoreFrame which takes its value from another
     * one.
     *
     * @param frame the GUIScoreFrame the new instance takes its values from.
     */
    public GUIScoreFrame(GUIScoreFrame frame) {
        this.score1Frame = frame.score1Frame;
        this.score2Frame = frame.score2Frame;
    }

    /**
     * Update the score of the player after each turn.
     *
     * @param othello the game being played.
     */
    public void updateScore(GameModel othello) {
        score1Frame.setScore(Integer.toString(othello.getScorePlayer1()));
        score2Frame.setScore(Integer.toString(othello.getScorePlayer2()));
    }

    /**
     * Indicates who the current player is by coloring the player's frame in
     * green. The other player's frame is red when it is not his turn.
     *
     * @param othello the game being played.
     */
    public void updateChangePlayer(GameModel othello) {
        if (othello.getCurrentColor() == ColorPawn.BLACK) {
            score1Frame.setStyle("-fx-background-color: #c7f097;-fx-border-width : 5; -fx-border-color: #FFD700;");
            score2Frame.setStyle("-fx-background-color: #f08c6b;-fx-border-width : 5; -fx-border-color: #a7682b;");
        } else {
            score1Frame.setStyle("-fx-background-color: #f08c6b;-fx-border-width : 5; -fx-border-color: #a7682b;");
            score2Frame.setStyle("-fx-background-color: #c7f097;-fx-border-width : 5; -fx-border-color: #FFD700;");
        }
    }

    /**
     * Changes the name of the first player.
     * @param name the new name of the player.
     */
    public void setNameP1(String name){
        score1Frame.setName(name);
    }
    
    /**
     * Changes the name of the second player.
     * @param name the new name of the player.
     */
    public void setNameP2(String name){
        score2Frame.setName(name);
    }

}
