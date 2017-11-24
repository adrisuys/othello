/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;
import esi.atlg3.g43320.othello.model.ColorPawn;
import java.util.Optional;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * This class represents the graphical interface of the game Othello.
 *
 * @author s_u_y_s_a
 */
public class FXOthelloUserInterface extends Application {

    private OthelloModel othello;
    private FXOthelloView view;

    /**
     * Starts and creates the application.
     * @param primaryStage the stage that will be displayed.
     */
    @Override
    public void start(Stage primaryStage) {
        othello = new OthelloModel();
        view = new FXOthelloView(othello);

        //CREATION OF FRAMES OF THE DISPLAY
        HBox mainFrame = new HBox();
        mainFrame.setStyle("-fx-background : #f6ebba");
        mainFrame.getChildren().addAll(view.getLeftSubFrame(), view.getRightSubFrame());

        Scene scene = new Scene(mainFrame, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.show();

        //INITIALIZING THE GAME
        othello.askTypeGame();
        othello.askName(view.isIsIAPlaying());
        othello.init(view.getResultFrame().getName1());

        //WHEN MOUSE HOVER CASE
        view.getBoardgame().getBoard().getChildren().stream().map((node) -> {
            node.setOnMouseEntered((MouseEvent event) -> {
                int x = GridPane.getRowIndex(node);
                int y = GridPane.getColumnIndex(node);
                Coordinates coord = new Coordinates(x, y);
                othello.mouseOverEnter(coord);
            });
            return node;
        }).forEachOrdered((node) -> {
            node.setOnMouseExited((MouseEvent event2) -> {
                GUISquare sq = (GUISquare) node;
                sq.getSquare().setFill(Color.GREEN);
            });
        });
        
        //WHEN MOUSE PRESSED
        for (Node node : view.getBoardgame().getBoard().getChildren()){
            node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    int i = GridPane.getRowIndex(node);
                    int j = GridPane.getColumnIndex(node);
                    boolean primaryButtonMouseClicked = false;
                    if (event.getButton()==MouseButton.PRIMARY){
                        primaryButtonMouseClicked = true;
                    } else if (event.getButton()==MouseButton.SECONDARY){
                        primaryButtonMouseClicked = false;
                    }
                    othello.playATurn(primaryButtonMouseClicked, new Coordinates(i,j), view.getResultFrame().getName1(), view.getResultFrame().getName2(), view.isWallChosenOverPass(), view.isIsIAPlaying());
                }
            
            });
        }

        //WHEN BUTTON ABANDONNER CLICKED
        view.getGiveUp().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.confirm();
            if (view.hasConfirm()){
                othello.giveUp();
                othello.askTypeGame();
                othello.askName(view.isIsIAPlaying());
                //askName(view.getResultFrame());
                othello.init(view.getResultFrame().getName1());
            }
        });
        
        //WHEN BUTTON RECOMMENCER CLICKED
        view.getRestart().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.confirm();
            if (view.hasConfirm()){
                othello.askTypeGame();
                othello.askName(view.isIsIAPlaying());
                //askName(view.getResultFrame());
                othello.init(view.getResultFrame().getName1());
            }
        });
        
        //WHEN BUTTON PASSER CLICKED
        view.getPass().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.turnPassedFX(view.isIsIAPlaying());
            if (othello.isTurnPassed()){
                othello.changePlayer();
            } else {
                othello.problemPass();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

//    private static void checkGameOver(OthelloModel othello, GUIScoreFrame score) {
//        if (othello.isOver()) {
//            othello.endOfGame();
//            askName(score);
//            othello.init(score.getName1());
//        }
//    }

//    private static void askName(GUIScoreFrame score) {
//        // Create the custom dialog.
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("");
//        dialog.setHeaderText("Enter the names of the player");
//        // Set the button types.
//        ButtonType OKButtonType = new ButtonType("OK", ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(OKButtonType);
//        // Create the username and password labels and fields.
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 150, 10, 10));
//
//        TextField name1 = new TextField();
//        TextField name2 = new TextField();
//
//        grid.add(new Label("Name of the player 1 (max 5 lettres):"), 0, 0);
//        grid.add(name1, 1, 0);
//        grid.add(new Label("Name of the player 2 (max 5 lettres):"), 0, 1);
//        grid.add(name2, 1, 1);
//
//        dialog.getDialogPane().setContent(grid);
//
//        // Convert the result to a username-password-pair when the login button is clicked.
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == OKButtonType) {
//                return new Pair<>(name1.getText(), name2.getText());
//            }
//            return null;
//        });
//
//        Optional<Pair<String, String>> result = dialog.showAndWait();
//
//        result.ifPresent(name1name2 -> {
//            score.setName1(name1name2.getKey());
//            score.setName2(name1name2.getValue());
//        });
//    }

}
