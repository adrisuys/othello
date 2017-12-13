/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.dpObs.Observable;
import esi.atlg3.g43320.othello.dpObs.Observer;
import esi.atlg3.g43320.othello.model.GameException;
import esi.atlg3.g43320.othello.model.GameModel;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * This class creates the view of the application. It observes the model of the
 * game.
 *
 * @author s_u_y_s_a
 */
public class FXOthelloView implements Observer {

    private final GameModel othello;
    private GUIBoardgame boardgame;
    private GUIColoredProgressBar bar;
    private GUIMovesHistory historyOfMoves;
    private GUIProgressCake progressCake;
    private GUIScoreFrame resultFrame;
    private VBox leftSubFrame;
    private VBox rightSubFrame;
    private boolean wallChosenOverPass;
    private boolean isIAPlaying;
    private boolean onlyIAPlaying;
    private Button giveUp;
    private Button pass;
    private Button restart;
    private boolean confirm;

    /**
     * Creates an instance of this class and register this object as an observer
     * of the parameters observable.
     *
     * @param observable an Observable that is now observed by this object.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public FXOthelloView(Observable observable) {
        if (observable == null) {
            throw new IllegalArgumentException("Nothing to observe");
        }
        othello = (GameModel) observable;
        othello.registerObserver(this);

        leftSubFrame = new VBox();
        rightSubFrame = new VBox();

        //CREATION OF THE BOARDGAME
        boardgame = new GUIBoardgame();
        boardgame.getBoard().setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");

        //CREATION OF LIST OF ACTION
        historyOfMoves = new GUIMovesHistory();
        historyOfMoves.getHistory().setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");

        //CREATION OF SCORE FRAME
        resultFrame = new GUIScoreFrame(othello);
        resultFrame.setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");
        resultFrame.setPadding(new Insets(5, 2, 5, 2));

        //CREATION AND DECORATION OF FRAMES OF THE DISPLAY
        HBox progressScoreBarFrame = new HBox();
        progressScoreBarFrame.setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");
        progressScoreBarFrame.setPadding(new Insets(5, 2, 5, 2));
        HBox progressGameFrame = new HBox();
        progressGameFrame.setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");
        progressGameFrame.setPadding(new Insets(5, 2, 5, 2));
        HBox btnFrame = new HBox();
        btnFrame.setStyle("-fx-background-color:#a7682b; -fx-border-color:#723102; -fx-border-width:2;");
        btnFrame.setPadding(new Insets(5, 2, 5, 2));
        HBox.setMargin(leftSubFrame, new Insets(10, 10, 10, 10));
        HBox.setMargin(rightSubFrame, new Insets(20, 10, 10, 10));
        leftSubFrame.getChildren().addAll(boardgame.getBoard(), progressScoreBarFrame, progressGameFrame, btnFrame);
        rightSubFrame.getChildren().addAll(resultFrame, historyOfMoves.getHistory());
        VBox.setMargin(resultFrame, new Insets(0, 0, 10, 0));

        //CREATION OF PROGRESS BAR (SCORE)
        VBox.setMargin(boardgame.getBoard(), new Insets(0, 0, 10, 0));
        bar = new GUIColoredProgressBar("black-bar");
        Label progressBarLabel = new Label("Noir/Blanc ");
        progressScoreBarFrame.getChildren().addAll(progressBarLabel, bar.getBar());
        progressScoreBarFrame.getStylesheets().add(getClass().getResource("./style/stylesheet.css").toExternalForm());

        //CREATION OF PROGRESS BAR (IS THE GAME CLOSE TO BE OVER)
        progressCake = new GUIProgressCake();
        Label progressCakeLabel = new Label("Partie complétée à ");
        progressGameFrame.getChildren().addAll(progressCakeLabel, progressCake.getProgressCake());
        VBox.setMargin(progressScoreBarFrame, new Insets(0, 0, 10, 0));

        //CREATION OF BUTTONS FRAME
        giveUp = new Button("ABANDON");
        pass = new Button("PASSE");
        restart = new Button("NOUVELLE PARTIE");
        btnFrame.getChildren().addAll(giveUp, pass, restart);
        VBox.setMargin(btnFrame, new Insets(10, 0, 0, 0));
        btnFrame.setAlignment(Pos.CENTER);
        HBox.setMargin(giveUp, new Insets(0, 10, 0, 0));
        HBox.setMargin(pass, new Insets(0, 10, 0, 0));

    }

    @Override
    public void update() {
        updateInit();
        updateMouseOver();
        updatePlay();
        updateTurnPassed();
        updateWall();
        updateEndOfGame();
        updateConfirm();
        updateGiveUp();
        updateProblemPass();
        updatePass();
        updateChangePlayer();
    }

    private void updateInit() {
        if (othello.isUpdateInit()) {
            historyOfMoves.reinitMovesHistory();
            boardgame.updateBoardgame(othello);
            boardgame.setDisableOnFalse();
            historyOfMoves.updateMovesHistory(othello);
            progressCake.updateProgressCake(othello);
            resultFrame.updateScore(othello);
            bar.updateProgressBar(othello);
            resultFrame.setNameP1(othello.getPlayers().get(0).getName());
            resultFrame.setNameP2(othello.getPlayers().get(1).getName());
            giveUp.setDisable(false);
            pass.setDisable(false);
        }
    }

    private void updatePlay() {
        if (othello.isUpdatePlay()) {
            if (othello.isValidPlay()) {
                boardgame.updateBoardgame(othello);
                historyOfMoves.updateMovesHistory(othello);
                progressCake.updateProgressCake(othello);
                resultFrame.updateScore(othello);
                bar.updateProgressBar(othello);
            }
        }
    }

    private void updateMouseOver() {
        if (othello.isUpdateMouseOver()) {
            boardgame.getSquareAtCoordinates(othello.getCoordX(), othello.getCoordY()).updateSquare(othello.isPossibleCase());
        }
    }

    private void updateTurnPassed() {
        if (othello.isUpdateTurnPassed()) {
            if (!othello.isIsIA()) {
                if (othello.isTurnPassed()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Tu dois passer ton tour!");
                    alert.setContentText("Néanmois, tu as la possibilité de placer un mur. Que veux-tu faire?");

                    ButtonType btnWall = new ButtonType("Je place un mur!");
                    ButtonType btnPass = new ButtonType("Je passe mon tour...", ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(btnWall, btnPass);

                    Optional<ButtonType> result = alert.showAndWait();
                    wallChosenOverPass = (result.get() == btnWall);
                }
            }
        }
    }

    private void updateWall() {
        if (othello.isUpdateWall()) {
            if (othello.isWallValid()) {
                boardgame.updateBoardgame(othello);
                progressCake.updateProgressCake(othello);
                historyOfMoves.updateMovesHistory(othello);
            }
        }
    }

    private void updateEndOfGame() {
        if (othello.isUpdateEndOfGame()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("La partie est finie!");
            int score1 = othello.getScorePlayer1();
            int score2 = othello.getScorePlayer2();
            if (score1 > score2) {
                alert.setContentText(othello.getPlayers().get(0).getName() + " a gagné! (" + score1 + "-" + score2 + ")");
            } else if (score1 == score2) {
                alert.setContentText("Match nul! (" + score1 + "-" + score2 + ")");
            } else {
                alert.setContentText(othello.getPlayers().get(1).getName() + " a gagné! (" + score1 + "-" + score2 + ")");
            }
            alert.showAndWait();
            boardgame.setDisableOnTrue();
            giveUp.setDisable(true);
            pass.setDisable(true);
        }
    }

    private void updateConfirm() {
        if (othello.isUpdateConfirm()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Etes-vous sûr?");
            alert.setContentText("");

            Optional<ButtonType> result = alert.showAndWait();
            confirm = result.get() == ButtonType.OK;
        }
    }

    private void updateGiveUp() {
        if (othello.isUpdateGiveUp()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Tu as abandonné! Perdu... ");

            alert.showAndWait();
        }
    }

    private void updateProblemPass() {
        if (othello.isUpdateProblemPass()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Tu ne peux pas passer ton tour, par contre tu peux mettre un mur!");

            alert.showAndWait();
        }
    }

    /**
     * Returns the graphical interface of the boardgame.
     *
     * @return the graphical interface of the boardgame.
     */
    GUIBoardgame getBoardgame() {
        return new GUIBoardgame(boardgame);
    }

    /**
     * Returns the graphical interface of the progress bar of the score.
     *
     * @return the graphical interface of the progress bar of the score.
     */
    GUIColoredProgressBar getBar() {
        return new GUIColoredProgressBar(bar);
    }

    /**
     * Returns the graphical interface of the history of moves.
     *
     * @return the graphical interface of the history of moves.
     */
    GUIMovesHistory getHistoryOfMoves() {
        return new GUIMovesHistory(historyOfMoves);
    }

    /**
     * Returns the graphical interface of the progress cake of the game.
     *
     * @return the graphical interface of the progress cake of the game.
     */
    GUIProgressCake getProgressCake() {
        return new GUIProgressCake(progressCake);
    }

    /**
     * Returns the graphical interface of the score.
     *
     * @return the graphical interface of the score.
     */
    GUIScoreFrame getResultFrame() {
        return new GUIScoreFrame(resultFrame);
    }

    /**
     * Returns the left part of the window with all its components.
     *
     * @return the left part of the window with all its components.
     */
    VBox getLeftSubFrame() {
        return leftSubFrame;
    }

    /**
     * Returns the right part of the window with all its components.
     *
     * @return the right part of the window with all its components.
     */
    VBox getRightSubFrame() {
        return rightSubFrame;
    }

    /**
     * Returns a boolean indicating if a player has chosen to put a wall instead
     * of passing its turn.
     *
     * @return a boolean indicating if a player has chosen to put a wall instead
     * of passing its turn.
     */
    boolean isWallChosenOverPass() {
        return wallChosenOverPass;
    }

    /**
     * Returns the button GIVE UP.
     *
     * @return the button GIVE UP.
     */
    Button getGiveUp() {
        return giveUp;
    }

    /**
     * Returns the button PASS.
     *
     * @return the button PASS.
     */
    Button getPass() {
        return pass;
    }

    /**
     * Returns the button RESTART.
     *
     * @return the button RESTART.
     */
    Button getRestart() {
        return restart;
    }

    /**
     * Returns a boolean indication if a player has confirmed an action.
     *
     * @return a boolean indication if a player has confirmed an action.
     */
    boolean hasConfirm() {
        return confirm;
    }

    private void updatePass() {
        if (othello.isUpdatePass()) {
            historyOfMoves.updateMovesHistory(othello);
        }
    }

    /**
     * Returns a boolean indicating if the IA is playing.
     *
     * @return a boolean indicating if the IA is playing.
     */
    boolean isIsIAPlaying() {
        return isIAPlaying;
    }

    /**
     * Returns a boolean indicating if 2 IAs are playing.
     *
     * @return a boolean indicating if 2 IAs are playing.
     */
    boolean isOnlyIAPlaying() {
        return onlyIAPlaying;
    }

    private void updateChangePlayer() {
        if (othello.isUpdateChangePlayer()) {
            resultFrame.updateChangePlayer(othello);
            enableHumanPlay();
            if (!othello.isOver()) {
                PauseTransition p = new PauseTransition(Duration.millis(1000));
                p.setOnFinished((ActionEvent e) -> {
                    Platform.runLater(() -> {
                        othello.getCurrentPlayer().runStrategy();
                        try {
                            othello.checkGameOver();
                        } catch (GameException ex) {

                        }
                    });
                });
                p.play();
            }
        }
    }

        /**
         * Set the boolean isIAPlaying with the new value (true or false). It
         * indicates that the game is in the mode Human vs Computer.
         *
         * @param isIAPlaying a boolean indicating if one IA is playing.
         */
        void setIsIAPlaying
        (boolean isIAPlaying
        
            ) {
        this.isIAPlaying = isIAPlaying;
        }

        /**
         * Set the boolean isIAPlaying with the new value (true or false). It
         * indicates that the game is in the mode Computer vs Computer
         *
         * @param onlyIAPlaying a boolean indicating if two IAs are playing.
         */
        void setOnlyIAPlaying
        (boolean onlyIAPlaying
        
            ) {
        this.onlyIAPlaying = onlyIAPlaying;
        }
    
    

    private void enableHumanPlay() {
        if (othello.getCurrentPlayer().isIsIA()) {
            boardgame.setDisableOnTrue();
        } else {
            boardgame.setDisableOnFalse();
        }
    }

}
