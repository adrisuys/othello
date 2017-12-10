/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents a GridPane containing the name, the color and the score
 * of a player.
 *
 * @author s_u_y_s_a
 */
public class GUIScoreFrame1Player extends GridPane {

    private final Label name;
    private final Label score;

    /**
     * Creates an instance of GUIScoreFrame1Player.
     * @param color the color of the pawn of the player.
     * @param nameStr the name of the player.
     * @param colorBckGrd the color of the background of the GridPane.
     */
    public GUIScoreFrame1Player(Color color, String nameStr, String colorBckGrd) {
        name = new Label(nameStr);
        score = new Label();
        
        //NAME
        Label nameLab = new Label("NOM");
        add(nameLab, 0, 0);
        GridPane.setMargin(nameLab, new Insets(10, 10, 10, 10));
        add(name, 0, 1);
        GridPane.setMargin(name, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(name, HPos.CENTER);
        
        //COLOR
        Label colorLab = new Label("PION");
        add(colorLab, 1, 0);
        GridPane.setMargin(colorLab, new Insets(10, 10, 10, 10));
        Circle circle = new Circle(10, color);
        circle.setStroke(Color.BLACK);
        add(circle, 1, 1);
        GridPane.setMargin(circle, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(circle, HPos.CENTER);
        
        //SCORE
        Label scoreLab = new Label("SCORE");
        add(scoreLab, 2, 0);
        GridPane.setMargin(scoreLab, new Insets(10, 10, 10, 10));
        add(score, 2, 1);
        GridPane.setMargin(score, new Insets(10, 10, 10, 10));
        GridPane.setHalignment(score, HPos.CENTER);
        
        //SETTING UP THE GRIDPANE
        setGridLinesVisible(true);
        setMinWidth(150);
        setAlignment(Pos.CENTER);
        setHgap(10);
        setStyle(colorBckGrd);
    }

    /**
     * Changes the name of the player.
     * @param name the new name.
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    /**
     * Changes the score of the player.
     * @param score the new score.
     */
    public void setScore(String score) {
        this.score.setText(score);
    }
    
    
}
