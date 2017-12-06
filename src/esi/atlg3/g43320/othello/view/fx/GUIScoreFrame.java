/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.ColorPawn;
import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represent the score frame that has 2 box in it. One for each
 * player. Those box contains the name, the color of the pawn and the score of
 * the player.
 *
 * @author s_u_y_s_a
 */
public class GUIScoreFrame extends HBox {

    private Label name1;
    private Label score1;
    private Label name2;
    private Label score2;
    private GridPane score1Frame;
    private GridPane score2Frame;

    /**
     * Creates an instance of a GUIScoreFrame.
     */
    public GUIScoreFrame() {
        score1Frame = new GridPane();
        score2Frame = new GridPane();
        score1Frame.setStyle("-fx-background-color: #c7f097;");
        score2Frame.setStyle("-fx-background-color: #f08c6b;");
        score1Frame.setGridLinesVisible(true);
        score2Frame.setGridLinesVisible(true);

        score1Frame.setMinWidth(150);
        score2Frame.setMinWidth(150);
        score1Frame.setAlignment(Pos.CENTER);
        score1Frame.setHgap(10);
        score2Frame.setAlignment(Pos.CENTER);
        score2Frame.setHgap(10);

        name1 = new Label("AAA");
        name2 = new Label("BBB");
        score1 = new Label();
        score2 = new Label();

        //PLAYER 1
        Label nameLab1 = new Label("NOM");
        score1Frame.add(nameLab1, 0, 0);
        GridPane.setMargin(nameLab1, new Insets(10, 10, 10, 10));
        score1Frame.add(name1, 0, 1);
        GridPane.setMargin(name1, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(name1, HPos.CENTER);

        Label colorLab1 = new Label("PION");
        score1Frame.add(colorLab1, 1, 0);
        GridPane.setMargin(colorLab1, new Insets(10, 10, 10, 10));
        Circle circle1 = new Circle(10, Color.BLACK);
        score1Frame.add(circle1, 1, 1);
        GridPane.setMargin(circle1, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(circle1, HPos.CENTER);

        Label scoreLab1 = new Label("SCORE");
        score1Frame.add(scoreLab1, 2, 0);
        GridPane.setMargin(scoreLab1, new Insets(10, 10, 10, 10));
        score1Frame.add(score1, 2, 1);
        GridPane.setMargin(score1, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(score1, HPos.CENTER);

        //PLAYER 2
        Label nameLab2 = new Label("NOM");
        score2Frame.add(nameLab2, 0, 0);
        GridPane.setMargin(nameLab2, new Insets(10, 10, 10, 10));
        score2Frame.add(name2, 0, 1);
        GridPane.setMargin(name2, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(name2, HPos.CENTER);

        Label colorLab2 = new Label("PION");
        score2Frame.add(colorLab2, 1, 0);
        GridPane.setMargin(colorLab2, new Insets(10, 10, 10, 10));
        Circle circle2 = new Circle(10, Color.WHITE);
        circle2.setStroke(Color.BLACK);
        score2Frame.add(circle2, 1, 1);
        GridPane.setMargin(circle2, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(circle2, HPos.CENTER);

        Label scoreLab2 = new Label("SCORE");
        score2Frame.add(scoreLab2, 2, 0);
        GridPane.setMargin(scoreLab2, new Insets(10, 10, 10, 10));
        score2Frame.add(score2, 2, 1);
        GridPane.setMargin(score2, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(score2, HPos.CENTER);

        getChildren().addAll(score1Frame, score2Frame);
        HBox.setMargin(score1Frame, new Insets(0, 20, 0, 0));

    }

    /**
     * Creates an instance of a GUIScoreFrame by updating the score with the
     * current one.
     *
     * @param othello the game that is being played.
     */
    public GUIScoreFrame(OthelloModel othello) {
        this();
        score1.setText(Integer.toString(othello.getScorePlayer1()));
        score2.setText(Integer.toString(othello.getScorePlayer2()));
    }

    /**
     * Creates an instance of GUIScoreFrame which takes its value from another
     * one.
     *
     * @param frame the GUIScoreFrame the new instance takes its values from.
     */
    public GUIScoreFrame(GUIScoreFrame frame) {
        this.name1 = frame.name1;
        this.name2 = frame.name2;
        this.score1 = frame.score1;
        this.score2 = frame.score2;
        this.score1Frame = frame.score1Frame;
        this.score2Frame = frame.score2Frame;
    }

    /**
     * Returns the name of the player black.
     *
     * @return the name of the player black.
     */
    public String getName1() {
        return name1.getText();
    }

    /**
     * Returns the score of the player black.
     *
     * @return the score of the player black.
     */
    public String getScore1() {
        return score1.getText();
    }

    /**
     * Returns the name of the player white.
     *
     * @return the name of the player white.
     */
    public String getName2() {
        return name2.getText();
    }

    /**
     * Returns the score of the player white.
     *
     * @return the score of the player white.
     */
    public String getScore2() {
        return score2.getText();
    }

    /**
     * Change the name of the black player.
     *
     * @param s its new name.
     */
    public void setName1(String s) {
        name1.setText(s);
    }

    /**
     * Change the score of the black player.
     *
     * @param s its new score.
     */
    public void setScore1(String s) {
        score1.setText(s);
    }

    /**
     * Change the name of the white player.
     *
     * @param s its new name.
     */
    public void setName2(String s) {
        name2.setText(s);
    }

    /**
     * Change the score of the white player.
     *
     * @param s its new score.
     */
    public void setScore2(String s) {
        score2.setText(s);
    }

    /**
     * Update the score of the player after each turn.
     *
     * @param othello the game being played.
     */
    public void updateScore(OthelloModel othello) {
        setScore1(Integer.toString(othello.getScorePlayer1()));
        setScore2(Integer.toString(othello.getScorePlayer2()));
    }

    /**
     * Indicates who the current player is by coloring the player's frame in
     * green. The other player's frame is red when it is not his turn.
     *
     * @param othello the game being played.
     */
    public void updateChangePlayer(OthelloModel othello) {
        if (othello.getCurrentColor() == ColorPawn.BLACK) {
            score1Frame.setStyle("-fx-background-color: #c7f097;-fx-border-width : 5; -fx-border-color: #FFD700;");
            score2Frame.setStyle("-fx-background-color: #f08c6b;-fx-border-width : 5; -fx-border-color: #a7682b;");
        } else {
            score1Frame.setStyle("-fx-background-color: #f08c6b;-fx-border-width : 5; -fx-border-color: #a7682b;");
            score2Frame.setStyle("-fx-background-color: #c7f097;-fx-border-width : 5; -fx-border-color: #FFD700;");
        }
    }

}
