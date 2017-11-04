/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This class represents the graphical interface of the game Othello.
 *
 * @author s_u_y_s_a
 */
public class BoardgameFX extends Application {

    @Override
    public void start(Stage primaryStage) {

// -------------------MENU-------------------------------------------
        MenuBar menuBar = new MenuBar();
        Menu menuReste = new Menu("Reset");
        Menu menuParam = new Menu("Settings");
        menuBar.getMenus().addAll(menuReste, menuParam);

// -------------------TITLE------------------------------------------        
        GridPane title = new GridPane();
        Label titleLab = new Label("Othello");
        titleLab.setStyle("-fx-font-size: 50px; "
                + "-fx-font-weight: bold; "
                + "-fx-text-fill: #FF0000;");
        title.setAlignment(Pos.CENTER);
        title.add(titleLab, 0, 0);
// -------------------CHECKERBOARD-----------------------------------        
        Pane pane = new Pane();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rec = new Rectangle(60 * j, 60 * i, 60, 60);
                if ((i + j) % 2 == 1) {
                    rec.setFill(Color.BROWN);
                } else {
                    rec.setFill(Color.BURLYWOOD);
                }
                pane.getChildren().add(rec);
            }
        }

        for (int k = 1; k < 17; k = k + 2) {
            for (int l = 1; l < 17; l = l + 2) {
                Circle circle = new Circle(30 * l, 30 * k, 20);
                if (k == 7 && l == 7) {
                    circle.setFill(Color.WHITE);
                } else if (k == 9 && l == 9) {
                    circle.setFill(Color.WHITE);
                } else if (k == 7 && l == 9) {
                    circle.setFill(Color.BLACK);
                } else if (k == 9 && l == 7) {
                    circle.setFill(Color.BLACK);
                } else {
                    circle.setFill(Color.TRANSPARENT);
                }
                pane.getChildren().add(circle);
            }
        }
// ------------------SCORE-------------------------------------

        VBox scoreFrame = new VBox();
        scoreFrame.setAlignment(Pos.CENTER);
        HBox scorePlayerFrame = new HBox();
        scorePlayerFrame.setAlignment(Pos.CENTER);
        Label score = new Label("Score");
        score.setStyle("-fx-font-size : 20 px; "
                + "-fx-text-fill: #FF0000 ; "
                + "-fx-font-weight : bold");
        VBox scorePlayerAFrame = new VBox();
        scorePlayerAFrame.setStyle("-fx-background-color : #e9967a;");
        HBox.setMargin(scorePlayerAFrame, new Insets(15, 5, 5, 5));
        VBox scorePlayerBFrame = new VBox();
        scorePlayerBFrame.setStyle("-fx-background-color : #e9967a;");
        HBox.setMargin(scorePlayerBFrame, new Insets(15, 5, 5, 5));
        Label playerAName = new Label();
        playerAName.setText("Player 1");
        playerAName.setStyle("-fx-font-weight: bold; "
                + "-fx-font-size : 15 px;");
        Label playerBName = new Label();
        playerBName.setText("Player 2");
        playerBName.setStyle("-fx-font-weight: bold; "
                + "-fx-font-size : 15 px;");
        Label playerAScore = new Label();
        playerAScore.setText("0");
        VBox.setMargin(playerAScore, new Insets(10, 0, 0, 0));
        Label playerBScore = new Label();
        playerBScore.setText("0");
        VBox.setMargin(playerBScore, new Insets(10, 0, 0, 0));
        scoreFrame.getChildren().addAll(score, scorePlayerFrame);
        scorePlayerFrame.getChildren().addAll(scorePlayerAFrame, scorePlayerBFrame);
        scorePlayerAFrame.getChildren().addAll(playerAName, playerAScore);
        scorePlayerAFrame.setAlignment(Pos.CENTER);
        scorePlayerBFrame.getChildren().addAll(playerBName, playerBScore);
        scorePlayerBFrame.setAlignment(Pos.CENTER);

//-------------------WINDOWS----------------------------------
        VBox mainFrame = new VBox();
        mainFrame.getChildren().addAll(menuBar, title, pane, scoreFrame);
        VBox.setMargin(pane, new Insets(15, 15, 15, 50));
        mainFrame.setAlignment(Pos.CENTER);

// ------------------SCENE-------------------------------------        
        Scene scene = new Scene(mainFrame, 600, 700);
        primaryStage.setTitle("Othello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
