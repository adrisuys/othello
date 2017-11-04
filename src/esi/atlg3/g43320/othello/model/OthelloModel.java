/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.dp.Observable;
import esi.atlg3.g43320.othello.dp.Observer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the model of the Game, it implements Observable.
 *
 * @author s_u_y_s_a
 */
public class OthelloModel implements Observable {

    private final List<Observer> observers;
    private Game game;
    private Coordinates coord;
    private String errorMessage;
    private int scorePlayer1;
    private int scorePlayer2;
    private Board board;
    private boolean validPlay;
    private boolean turnPassed;

    /**
     * Creates an instance of a model of Othello.
     */
    public OthelloModel() {
        observers = new ArrayList<>();
    }

    /**
     * Adds an observer to its list. The observer can now observe this object.
     *
     * @param obs the new observer
     */
    @Override
    public void registerObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    /**
     * Removes an observer of its list, this observer has no access anymore.
     *
     * @param obs the observer to be removed.
     */
    @Override
    public void removeObserver(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    /**
     * Notifies the observers when a player plays.
     */
    @Override
    public void notifyObserversPlay() {
        for (Observer obs : observers) {
            obs.updatePlay();
        }
    }

    /**
     * This represents a play for the current player. If it is its turn and he
     * can play, he puts a pawn on a case. Nnd notifies the observers.
     *
     * @param aGame the game.
     * @param aCoordinate the coordinates of the case the player wants to put a
     * pawn on
     * @param valid a boolean indicating if the play is valid (if he can play on
     * that particular case)
     * @param passed a boolean indicating if the player's turn is passed.
     */
    public void play(Game aGame, Coordinates aCoordinate, boolean valid, boolean passed) {
        validPlay = valid;
        game = aGame;
        coord = aCoordinate;
        turnPassed = passed;
        if (game.isTurnPassed(game.getCurrentPlayer())) {
            turnPassed = true;
            game.changePlayer();
        } else {
            game.updatePossibleMove(game.getCurrentColor());
            if (!game.getPossibleMove().contains(aCoordinate)) {
                validPlay = false;
            } else {
                validPlay = true;
                game.putPawn(aCoordinate, game.getCurrentColor());
                game.changeColorPawn(game.getBoard());
                game.changePlayer();
            }
        }
//        if (!game.isTurnPassed(game.getCurrentPlayer())){
//            validPlay = game.putPawn(coord);
//            turnPassed = false;
//        } else {
//            turnPassed = true;
//            game.changePlayer();
//        }
//        if (validPlay){
//            game.changeColorPawn(game.getBoard());
//            game.changePlayer();
//        }
        notifyObserversPlay();
    }

    /**
     * Notifies the observers when a player asks to see the score.
     */
    @Override
    public void notifyObserversScore() {
        for (Observer obs : observers) {
            obs.updateScore();
        }
    }

    /**
     * Notifies the observers when a player asks to see the board at a given
     * time.
     */
    @Override
    public void notifyObserversShow() {
        for (Observer obs : observers) {
            obs.updateShow();
        }
    }

    /**
     * Gets the score of the 2 players at a given time and notifies the
     * observers.
     *
     * @param aGame the game they are currently playing.
     */
    public void score(Game aGame) {
        game = aGame;
        scorePlayer1 = game.getBoard().getScore(Color.BLACK);
        scorePlayer2 = game.getBoard().getScore(Color.WHITE);
        notifyObserversScore();
    }

    /**
     * Recovers the game the players are currently playing and notifies the
     * observers.
     *
     * @param aGame
     */
    public void show(Game aGame) {
        game = aGame;
        notifyObserversShow();
    }

    /**
     * Initialize the game by putting 2 white pawns and 2 black ones in the
     * middle of the board.
     *
     * @param aGame the game the players are currently playing.
     */
    public void init(Game aGame) {
        game = aGame;
        game.getBoard().putPawn(new Coordinates(3, 3), Color.WHITE);
        game.getBoard().putPawn(new Coordinates(3, 4), Color.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 3), Color.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 4), Color.WHITE);
        notifyObserversInit();
    }

    /**
     * Returns the list of all the observers of this object.
     *
     * @return the list of all the observers of this object.
     */
    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    /**
     * Returns the game.
     *
     * @return the game.
     */
    public Game getGame() {
        return new Game(game);
    }

    /**
     * Returns the coordinates of the case on which the player wants to put a
     * pawn.
     *
     * @return the coordinates of the case on which the player wants to put a
     * pawn.
     */
    public Coordinates getCoord() {
        return new Coordinates(coord.getX(), coord.getY());
    }

    /**
     * Returns the score of the player that has the black pawns.
     *
     * @return the score of the player that has the black pawns.
     */
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    /**
     * Returns the score of the player that has the white pawns.
     *
     * @return the score of the player that has the white pawns.
     */
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    /**
     * Returns the board.
     *
     * @return the board.
     */
    public Board getBoard() {
        return new Board(board);
    }

    /**
     * Notifies the observers when the game is being initialized.
     */
    @Override
    public void notifyObserversInit() {
        for (Observer obs : observers) {
            obs.updateInit();
        }
    }

    /**
     * Notifies the observers when the player typed a wrong command (not amongs
     * the following : "score", "show", "play x y").
     */
    @Override
    public void notifyObserversErrorInputCommand() {
        for (Observer obs : observers) {
            obs.updateErrorInputCommand();
        }
    }

    /**
     * Notifies the observers when the player wants to put a pawn on a non-valid
     * case of the board.
     */
    @Override
    public void notifyObserversErrorInputCoordinates() {
        for (Observer obs : observers) {
            obs.updateErrorInputCoordinates();
        }
    }

    /**
     * Returns a boolean indicating if the play happened correctly (if the
     * player has been able to put a pawn on the board).
     *
     * @return a boolean indicating if the play happened correctly (if the
     * player has been able to put a pawn on the board).
     */
    public boolean isValidPlay() {
        return validPlay;
    }

    /**
     * Returns a boolean indicating if the player's turn has been passed.
     *
     * @return a boolean indicating if the player's turn has been passed.s
     */
    public boolean isTurnPassed() {
        return turnPassed;
    }

}
