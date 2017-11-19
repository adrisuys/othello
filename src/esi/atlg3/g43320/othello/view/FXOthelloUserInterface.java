/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;
import esi.atlg3.g43320.othello.model.ColorPawn;
import java.util.Optional;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
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

    @Override
    public void start(Stage primaryStage) {
        othello = new OthelloModel();
        view = new FXOthelloView(othello);

        //CREATION OF FRAMES OF THE DISPLAY
        HBox mainFrame = new HBox();
        mainFrame.setStyle("-fx-background : #f6ebba");
        mainFrame.getChildren().addAll(view.getLeftSubFrame(), view.getRightSubFrame());

        //ASKING THE PLAYER'S NAME
        //INITIALIZING THE GAME
        othello.init(view.getResultFrame().getName1());

        //WHEN MOUSE HOVER CASE
        for (Node node : view.getBoardgame().getChildren()) {
            node.setOnMouseEntered((MouseEvent event) -> {
                int x = GUIBoardgame.getRowIndex(node);
                int y = GUIBoardgame.getColumnIndex(node);
                Coordinates coord = new Coordinates(x, y);
                othello.mouseOverEnter(coord);
            });
            node.setOnMouseExited((MouseEvent event2) -> {
                GUISquare sq = (GUISquare) node;
                sq.getSquare().setFill(Color.GREEN);
            });
        }

        //WHEN MOUSE PRESSED (LEFT CLICK)
        view.getBoardgame().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : view.getBoardgame().getChildren()) {
                    if (node instanceof GUISquare) {
                        if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                            int i = GridPane.getRowIndex(node);
                            int j = GridPane.getColumnIndex(node);
                            Coordinates coord = new Coordinates(i, j);
                            othello.turnPassedFX();
                            if (!othello.isTurnPassed()) {
                                if (othello.getGame().getCurrentColor() == ColorPawn.BLACK) {
                                    othello.play(coord, view.getResultFrame().getName1());
                                } else {
                                    othello.play(coord, view.getResultFrame().getName2());
                                }
                            } else {
                                if (view.isWallChosenOverPass()) {
                                    if (e.getButton() == MouseButton.SECONDARY) {
                                        if (othello.getGame().getCurrentColor() == ColorPawn.BLACK) {
                                            othello.wall(coord, view.getResultFrame().getName1());
                                        } else {
                                            othello.wall(coord, view.getResultFrame().getName2());
                                        }
                                    }
                                } else {
                                    othello.changePlayer();
                                }
                            }
                        }
                    }
                }
            }
        });

        // 2. IF THE PLAYER CAN'T PLAY, PASS TURN, DO YOU WANT TO PUT A WALL? (SEE 7)
        // 3. WHEN ABANDON IS PRESSED, "R U SURE?", IF YES, REINIT, YOU LOOSE
        // 4. WHEN PASSED IS PRESSED, VERIFY IF HE CAN PASS
        // 5. WHEN RECOMMENCER IS PRESSED, "R U SURE?", IF YES, REINIT
        // 6. WHEN A SQUARE IS PRESSED (LEFT CLICK), IF OK, PLAY, IF NOT NOTHING
        // 7. WHEN A SQUARE IS PRESSED (RIGHT CLICK), PUT WALL
        // 8. MOUSE OVER = IF ISMOVEPOSSIBLE, GREEN, ELSE RED
        Scene scene = new Scene(mainFrame, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void checkGameOver(OthelloModel othello, GUIScoreFrame score) {
        if (othello.isOver()) {
            //othello.endOfGame();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("The game is over!");
            int score1 = Integer.parseInt(score.getScore1());
            int score2 = Integer.parseInt(score.getScore2());
            if (score1 > score2) {
                alert.setContentText(score.getName1() + " has won! (" + score1 + "-" + score2 + ")");
            } else if (score1 == score2) {
                alert.setContentText("It is a draw! (" + score1 + "-" + score2 + ")");
            } else {
                alert.setContentText(score.getName2() + " has won! (" + score1 + "-" + score2 + ")");
            }
            ButtonType buttonTypeOK = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOK);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOK) {
                //askName(score);
                othello.init(score.getName1());
            }
        }
    }

    public static Dialog<Pair<String, String>> askName(GUIScoreFrame score) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("");
        dialog.setHeaderText("Enter the names of the player");
        // Set the button types.
        ButtonType OKButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name1 = new TextField();
        TextField name2 = new TextField();

        grid.add(new Label("Name of the player 1:"), 0, 0);
        grid.add(name1, 1, 0);
        grid.add(new Label("Name of the player 2:"), 0, 1);
        grid.add(name2, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OKButtonType) {
                return new Pair<>(name1.getText(), name2.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(name1name2 -> {
            score.setName1(name1name2.getKey());
            score.setName2(name1name2.getValue());
        });

        return dialog;
    }

}
