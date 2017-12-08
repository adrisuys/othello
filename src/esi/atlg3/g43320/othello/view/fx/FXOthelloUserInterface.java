/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.GameException;
import esi.atlg3.g43320.othello.model.GameModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class represents the graphical interface of the game Othello.
 *
 * @author s_u_y_s_a
 */
public class FXOthelloUserInterface extends Application {

    private GameModel othello;
    private FXOthelloView view;

    /**
     * Starts and creates the application.
     *
     * @param primaryStage the stage that will be displayed.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMaxHeight(750);
        primaryStage.setMaxWidth(1000);
        othello = new GameModel();
        view = new FXOthelloView(othello);

        BorderPane mainFrame = new BorderPane();
        Image img = new Image(getClass().getResourceAsStream("./img/bckground.jpg"));
        ImageView imgView = new ImageView(img);
        imgView.fitWidthProperty().bind(primaryStage.widthProperty());
        mainFrame.getChildren().add(imgView);

        //CREATION OF FRAMES OF THE DISPLAY (THE GAME SCENE)
        HBox contentGame = new HBox();
        contentGame.getChildren().addAll(view.getLeftSubFrame(), view.getRightSubFrame());

        //CREATIONS OF FRAMES OF THE DISPLAY (THE RULES SCENE)
        GUIRules contentRules = new GUIRules();

        //CREATIONS OF FRAMES OF THE DISPLAY (THE MENU)
        GUIMenu menu = new GUIMenu();

        //.................
        mainFrame.setTop(menu.getMenu());
        mainFrame.setCenter(contentGame);
        Scene scene = new Scene(mainFrame, 1000, 750);
        GUIIntroMsg start = new GUIIntroMsg();
        Scene sceneStart = new Scene(start.getDialog(), 1000, 750);
        
        //WHEN PLAY BUTTON PRESSED ON START SCREEN
        start.getOKButtonType().setOnMouseClicked((MouseEvent e) -> {
            if (start.getTypeGame().getSelectedToggle() == start.getHumVShum()) {
                view.setIsIAPlaying(false);
                view.setOnlyIAPlaying(false);
            } else if (start.getTypeGame().getSelectedToggle() == start.getHumVScomp()) {
                view.setIsIAPlaying(true);
                view.setOnlyIAPlaying(false);
            } else if (start.getTypeGame().getSelectedToggle() == start.getCompVScomp()) {
                view.setIsIAPlaying(false);
                view.setOnlyIAPlaying(true);
            }
            primaryStage.setScene(scene);
            
            try {
                othello.init(start.getName1(), start.getName2(), view.isIsIAPlaying(), view.isOnlyIAPlaying());
            } catch (GameException ex) {
            }
        });
        
        //WHEN QUIT BUTTON PRESSED ON START SCREEN
        start.getQuitButtonType().setOnMouseClicked((MouseEvent ev) -> {
            Platform.exit();
        });

        primaryStage.setScene(sceneStart);
        primaryStage.show();

        

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
        view.getBoardgame().getBoard().getChildren().forEach((node) -> {
            node.setOnMouseClicked((MouseEvent event) -> {
                int i = GridPane.getRowIndex(node);
                int j = GridPane.getColumnIndex(node);
                boolean primaryButtonMouseClicked = false;
                if (event.getButton() == MouseButton.PRIMARY) {
                    primaryButtonMouseClicked = true;
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    primaryButtonMouseClicked = false;
                }
                try {
                    othello.playATurn(primaryButtonMouseClicked, new Coordinates(i, j), view.getResultFrame().getName1(), view.getResultFrame().getName2(), view.isWallChosenOverPass(), view.isIsIAPlaying());
                } catch (GameException e) {
                }
            });
        });

        //WHEN BUTTON ABANDONNER CLICKED
        view.getGiveUp().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.confirm();
            if (view.hasConfirm()) {
                othello.giveUp();
                othello.setINITStatus();
                primaryStage.setScene(sceneStart);
                start.cleanTxtField();
                start.getHumVShum().setSelected(true);
            }
        });

        //WHEN BUTTON RECOMMENCER CLICKED
        view.getRestart().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.confirm();
            if (view.hasConfirm()) {
                othello.setINITStatus();
                primaryStage.setScene(sceneStart);
                start.cleanTxtField();
                start.getHumVShum().setSelected(true);
            }
        });

        //WHEN BUTTON PASSER CLICKED
        view.getPass().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            othello.turnPassed(view.isIsIAPlaying());
            if (othello.isTurnPassed()) {
                try {
                    othello.changePlayer();
                } catch (GameException ex) {
                }
            } else {
                othello.problemPass();
            }
        });

        //WHEN MENU JOUER IS PRESSED
        menu.getGame().getGraphic().setOnMouseClicked((MouseEvent event) -> {
            mainFrame.setCenter(contentGame);
        });

        //WHEN MENU REGLES IS PRESSED
        menu.getRules().getGraphic().setOnMouseClicked((MouseEvent event) -> {
            mainFrame.setCenter(contentRules.getFrame());
        });
        
        //WHEN MENU QUITTER IS PRESSED
        menu.getQuit().getGraphic().setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
