/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.dpObs.Observable;
import esi.atlg3.g43320.othello.dpObs.Observer;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * This class creates the view of the application. It observes the model of the
 * game.
 *
 * @author s_u_y_s_a
 */
public class FXOthelloView implements Observer {

    private OthelloModel othello;
    private GUIBoardgame boardgame;
    private GUIColoredProgressBar bar;
    private GUIMovesHistory historyOfMoves;
    private GUIProgressCake progressCake;
    private GUIScoreFrame resultFrame;
    private VBox leftSubFrame;
    private VBox rightSubFrame;
    private boolean wallChosenOverPass;
    private boolean isIAPlaying;
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
        othello = (OthelloModel) observable;
        othello.registerObserver(this);

        leftSubFrame = new VBox();
        rightSubFrame = new VBox();

        //CREATION OF THE BOARDGAME
        boardgame = new GUIBoardgame();

        //CREATION OF LIST OF ACTION
        historyOfMoves = new GUIMovesHistory();

        //CREATION OF SCORE FRAME
        resultFrame = new GUIScoreFrame(othello);

        //CREATION AND DECORATION OF FRAMES OF THE DISPLAY
        HBox progressScoreBarFrame = new HBox();
        HBox progressGameFrame = new HBox();
        HBox btnFrame = new HBox();
        HBox.setMargin(leftSubFrame, new Insets(20, 10, 20, 20));
        HBox.setMargin(rightSubFrame, new Insets(20, 20, 20, 10));
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
        restart = new Button("RECOMMENCER");
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
        updateAskName();
        updatePass();
        updateTypeGame();
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
            } else {
                if (othello.isTurnPassed()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("L'ordinateur a passé son tour !");
                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
                    delay.setOnFinished(e -> alert.hide());
                    alert.showAndWait();
                    delay.play();
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
            alert.setHeaderText("The game is over!");
            int score1 = othello.getScorePlayer1();
            int score2 = othello.getScorePlayer2();
            if (score1 > score2) {
                alert.setContentText(resultFrame.getName1() + " has won! (" + score1 + "-" + score2 + ")");
            } else if (score1 == score2) {
                alert.setContentText("It is a draw! (" + score1 + "-" + score2 + ")");
            } else {
                alert.setContentText(resultFrame.getName2() + " has won! (" + score1 + "-" + score2 + ")");
            }
            alert.showAndWait();
        }
    }

    private void updateConfirm() {
        if (othello.isUpdateConfirm()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Are you sure you want to do that?");
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
            alert.setContentText("You have given up! You have lost! ");

            alert.showAndWait();
        }
    }

    private void updateProblemPass() {
        if (othello.isUpdateProblemPass()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("You can't pass, you still can put a pawn on the board !");

            alert.showAndWait();
        }
    }

    /**
     * Returns the graphical interface of the boardgame.
     *
     * @return the graphical interface of the boardgame.
     */
    public GUIBoardgame getBoardgame() {
        return boardgame;
    }

    /**
     * Returns the graphical interface of the progress bar of the score.
     *
     * @return the graphical interface of the progress bar of the score.
     */
    public GUIColoredProgressBar getBar() {
        return bar;
    }

    /**
     * Returns the graphical interface of the history of moves.
     *
     * @return the graphical interface of the history of moves.
     */
    public GUIMovesHistory getHistoryOfMoves() {
        return historyOfMoves;
    }

    /**
     * Returns the graphical interface of the progress cake of the game.
     *
     * @return the graphical interface of the progress cake of the game.
     */
    public GUIProgressCake getProgressCake() {
        return progressCake;
    }

    /**
     * Returns the graphical interface of the score.
     *
     * @return the graphical interface of the score.
     */
    public GUIScoreFrame getResultFrame() {
        return resultFrame;
    }

    /**
     * Returns the left part of the window with all its components.
     *
     * @return the left part of the window with all its components.
     */
    public VBox getLeftSubFrame() {
        return leftSubFrame;
    }

    /**
     * Returns the right part of the window with all its components.
     *
     * @return the right part of the window with all its components.
     */
    public VBox getRightSubFrame() {
        return rightSubFrame;
    }

    /**
     * Returns a boolean indicating if a player has chosen to put a wall instead
     * of passing its turn.
     *
     * @return a boolean indicating if a player has chosen to put a wall instead
     * of passing its turn.
     */
    public boolean isWallChosenOverPass() {
        return wallChosenOverPass;
    }

    /**
     * Returns the button GIVE UP.
     *
     * @return the button GIVE UP.
     */
    public Button getGiveUp() {
        return giveUp;
    }

    /**
     * Returns the button PASS.
     *
     * @return the button PASS.
     */
    public Button getPass() {
        return pass;
    }

    /**
     * Returns the button RESTART.
     *
     * @return the button RESTART.
     */
    public Button getRestart() {
        return restart;
    }

    /**
     * Returns a boolean indication if a player has confirmed an action.
     *
     * @return a boolean indication if a player has confirmed an action.
     */
    public boolean hasConfirm() {
        return confirm;
    }

    private void updateAskName() {
        // Create the custom dialog.
        if (othello.isUpdateAskName()) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("");
            dialog.setHeaderText("Enter the names of the player");
            // Set the button types.
            ButtonType OKButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(OKButtonType);
            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField name1 = new TextField();
            addTextLimiter(name1,5);
            grid.add(new Label("Name of the player 1 (max 5 lettres):"), 0, 0);
            grid.add(name1, 1, 0);

            TextField name2 = new TextField();
            addTextLimiter(name2,5);
            if (othello.isIsIAChosen()) {
                name2.setText("IA");
                name2.setEditable(false);
            }
            grid.add(new Label("Name of the player 2 (max 5 lettres):"), 0, 1);
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
                resultFrame.setName1(name1name2.getKey());
                resultFrame.setName2(name1name2.getValue());
            });
        }
    }

    private void updatePass() {
        if (othello.isUpdatePass()) {
            historyOfMoves.updateMovesHistory(othello);
        }
    }

    private void updateTypeGame() {
        if (othello.isUpdateTypeGame()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Quel sera ton adversaire?");
            alert.setContentText("Veux-tu jouer avec un ami ou contre l'ordinateur?");

            ButtonType btn1 = new ButtonType("Mon ami");
            ButtonType btn2 = new ButtonType("L'ordi!", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btn1, btn2);

            Optional<ButtonType> result = alert.showAndWait();
            isIAPlaying = (result.get() == btn2);
        }
    }

    public boolean isIsIAPlaying() {
        return isIAPlaying;
    }
    
    public void updateChangePlayer(){
        if (othello.isUpdateChangePlayer()){
            resultFrame.updateChangePlayer(othello);
        }
    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
    tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
        if (tf.getText().length() > maxLength) {
            String s = tf.getText().substring(0, maxLength);
            tf.setText(s);
        }
    });
}

}
