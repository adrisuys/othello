/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.dp.Observable;
import esi.atlg3.g43320.othello.dp.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is the model of the Game, it implements Observable.
 *
 * @author s_u_y_s_a
 */
public class OthelloModel implements Observable {

    private final List<Observer> observers;
    private Game game;
    private Coordinates coord;
    private int scorePlayer1;
    private int scorePlayer2;
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
        if (obs == null) {
            throw new IllegalArgumentException("obs can't be null");
        }
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
        if (obs == null) {
            throw new IllegalArgumentException("obs can't be null");
        }
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    /**
     * Notifies the observers when a player plays.
     */
    @Override
    public void notifyObserversPlay() {
        observers.forEach((obs) -> {
            obs.updatePlay();
        });
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
        game.updatePossibleMove(game.getCurrentColor());
        if (!game.getPossibleMove().contains(coord)) {
            validPlay = false;
        } else {
            validPlay = true;
            game.putPawn(coord, game.getCurrentColor());
            game.changeColorPawn(game.getBoard());
            game.changePlayer();
        }
        notifyObserversPlay();
    }

    /**
     * Notifies the observers when a player asks to see the score.
     */
    @Override
    public void notifyObserversScore() {
        observers.forEach((obs) -> {
            obs.updateScore();
        });
    }

    /**
     * Notifies the observers when a player asks to see the board at a given
     * time.
     */
    @Override
    public void notifyObserversShow() {
        observers.forEach((obs) -> {
            obs.updateShow();
        });
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
     * Returns the game.
     *
     * @return the game.
     */
    public Game getGame() {
        return new Game(game);
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
     * Notifies the observers when the game is being initialized.
     */
    @Override
    public void notifyObserversInit() {
        observers.forEach((obs) -> {
            obs.updateInit();
        });
    }

    /**
     * Notifies the observers when the player typed a wrong command (not amongs
     * the following : "score", "show", "play x y").
     */
    @Override
    public void notifyObserversErrorInputCommand() {
        observers.forEach((obs) -> {
            obs.updateErrorInputCommand();
        });
    }

    /**
     * Notifies the observers when the player wants to put a pawn on a non-valid
     * case of the board.
     */
    @Override
    public void notifyObserversErrorInputCoordinates() {
        observers.forEach((obs) -> {
            obs.updateErrorInputCoordinates();
        });
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

    /**
     * Notifies the observers when the game is over.
     */
    @Override
    public void notifyEndOfGame() {
        observers.forEach((obs) -> {
            obs.updateEndOfGame();
        });
    }

    /**
     * End the game by telling who the winner is.
     *
     * @param aGame the game currently being played.
     */
    public void endOfGame(Game aGame) {
        game = aGame;
        score(game);
        notifyEndOfGame();
    }

    /**
     * If a player's turn is passed, it changes the current player.
     *
     * @param aGame the game the players are currently playing.
     * @param passed a boolean indicating if a player's turn is passed.
     */
    public void turnPassed(Game aGame, boolean passed) {
        game = aGame;
        turnPassed = passed;
        if (turnPassed) {
            game.changePlayer();
        }
        notifyTurnPassed();
    }

    @Override
    public void notifyTurnPassed() {
        observers.forEach((obs) -> {
            obs.updateTurnPassed();
        });
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.observers);
        hash = 47 * hash + Objects.hashCode(this.game);
        hash = 47 * hash + Objects.hashCode(this.coord);
        hash = 47 * hash + this.scorePlayer1;
        hash = 47 * hash + this.scorePlayer2;
        hash = 47 * hash + (this.validPlay ? 1 : 0);
        hash = 47 * hash + (this.turnPassed ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OthelloModel other = (OthelloModel) obj;
        if (this.scorePlayer1 != other.scorePlayer1) {
            return false;
        }
        if (this.scorePlayer2 != other.scorePlayer2) {
            return false;
        }
        if (this.validPlay != other.validPlay) {
            return false;
        }
        if (this.turnPassed != other.turnPassed) {
            return false;
        }
        if (!Objects.equals(this.observers, other.observers)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return Objects.equals(this.coord, other.coord);
    }

}
