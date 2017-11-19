/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

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
 *
 * @author s_u_y_s_a
 */
public class GUIScoreFrame extends HBox {
    
    private Label name1;
    private Label score1;
    private Label name2;
    private Label score2;
    
    public GUIScoreFrame(){
        GridPane score1Frame = new GridPane();
        GridPane score2Frame = new GridPane();
        score1Frame.setStyle("-fx-background-color: #f08c6b;");
        score2Frame.setStyle("-fx-background-color: #c7f097;");
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
        
        Label nameLab1 = new Label ("NOM");
        score1Frame.add(nameLab1, 0, 0);
        GridPane.setMargin(nameLab1, new Insets(10,10,10,10));
        score1Frame.add(name1, 0, 1);
        GridPane.setMargin(name1, new Insets(10,10,10,10));
        GridPane.setHalignment(name1, HPos.CENTER);
        
        Label colorLab1 = new Label ("PION");
        score1Frame.add(colorLab1, 1, 0);
        GridPane.setMargin(colorLab1, new Insets(10,10,10,10));
        Circle circle1 = new Circle(10, Color.BLACK);
        score1Frame.add(circle1, 1,1);
        GridPane.setMargin(circle1, new Insets(10,10,10,10));
        GridPane.setHalignment(circle1, HPos.CENTER);
        
        Label scoreLab1 = new Label ("SCORE");
        score1Frame.add(scoreLab1,2,0);
        GridPane.setMargin(scoreLab1, new Insets(10,10,10,10));
        score1Frame.add(score1, 2, 1);
        GridPane.setMargin(score1, new Insets(10,10,10,10));
        GridPane.setHalignment(score1, HPos.CENTER);
        
        //PLAYER 2
        
        Label nameLab2 = new Label ("NOM");
        score2Frame.add(nameLab2, 0, 0);
        GridPane.setMargin(nameLab2, new Insets(10,10,10,10));
        score2Frame.add(name2, 0, 1);
        GridPane.setMargin(name2, new Insets(10,10,10,10));
        GridPane.setHalignment(name2, HPos.CENTER);
        
        Label colorLab2 = new Label ("PION");
        score2Frame.add(colorLab2, 1, 0);
        GridPane.setMargin(colorLab2, new Insets(10,10,10,10));
        Circle circle2 = new Circle(10, Color.WHITE);
        circle2.setStroke(Color.BLACK);
        score2Frame.add(circle2, 1,1);
        GridPane.setMargin(circle2, new Insets(10,10,10,10));
        GridPane.setHalignment(circle2, HPos.CENTER);
        
        Label scoreLab2 = new Label ("SCORE");
        score2Frame.add(scoreLab2,2,0);
        GridPane.setMargin(scoreLab2, new Insets(10,10,10,10));
        score2Frame.add(score2, 2, 1);
        GridPane.setMargin(score2, new Insets(10,10,10,10));
        GridPane.setHalignment(score2, HPos.CENTER);
        
        getChildren().addAll(score1Frame, score2Frame);
        HBox.setMargin(score1Frame, new Insets(0,20,0,0));
                
    }
    
    public GUIScoreFrame(OthelloModel othello){
        this();
        score1.setText(Integer.toString(othello.getScorePlayer1()));
        score2.setText(Integer.toString(othello.getScorePlayer2()));
    }

    public String getName1() {
        return name1.getText();
    }

    public String getScore1() {
        return score1.getText();
    }

    public String getName2() {
        return name2.getText();
    }

    public String getScore2() {
        return score2.getText();
    }

    public void setName1(String s) {
        name1.setText(s);
    }

    public void setScore1(String s) {
        score1.setText(s);
    }

    public void setName2(String s) {
        name2.setText(s);
    }

    public void setScore2(String s) {
        score2.setText(s);
    }
    
    public void updateScore(OthelloModel othello){
        setScore1(Integer.toString(othello.getScorePlayer1()));
        setScore2(Integer.toString(othello.getScorePlayer2()));
    }
    
    public void initScore(){
        // name1 et name2 doit recevoir le nom des joueurs
    }
    
    
    
    
}
