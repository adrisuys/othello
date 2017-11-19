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
    private int scorePlayer1;
    private int scorePlayer2;
    private boolean validPlay;
    private boolean turnPassed;
    private boolean isPossibleCase;
    private int coordX;
    private int coordY;
    private String moveName;
    private String moveAction;
    private String movePos;
    private String moveTaken;
    private boolean isWallValid;
    private int nbPawnsOnBoard;
    
    //booleans to be used to check updates
    private boolean updatePlay;
    private boolean updateScore;
    private boolean updateShow;
    private boolean updateInit;
    private boolean updateTurnPassed;
    private boolean updateEndOfGame;
    private boolean updateCoordinatesError;
    private boolean updateCommandsError;
    private boolean updateMouseOver;
    private boolean updateWall;

    /**
     * Creates an instance of a model of Othello.
     */
    public OthelloModel() {
        observers = new ArrayList<>();
        game = new Game();
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
    public void notifyObservers() {
        observers.forEach((obs) -> {
            obs.update();
        });
    }

    /**
     * This represents a play for the current player. If it is its turn and he
     * can play, he puts a pawn on a case. Nnd notifies the observers.
     *
     * @param aCoordinate the coordinates of the case the player wants to put a
     * pawn on
     */
    public void play(Coordinates aCoordinate, String namePlayer) {
        //validPlay = valid;
        //game = aGame;
        //coord = aCoordinate;
        game.updatePossibleMove(game.getCurrentColor());
        if (!game.getPossibleMove().contains(aCoordinate)) {
            validPlay = false;
        } else {
            validPlay = true;
            game.putPawn(aCoordinate, game.getCurrentColor());
            game.changeColorPawn();
            game.changePlayer();
        }
        scorePlayer1 = game.getBoard().getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getBoard().getScore(ColorPawn.WHITE);
        moveName = namePlayer;
        moveAction = "Place une pièce";
        movePos = aCoordinate.toString();
        moveTaken = Integer.toString(game.getNbPawnsToBeTurned());
        updateInit = false;
        updateScore = false;
        updatePlay = true;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
    }

    /**
     * Gets the score of the 2 players at a given time and notifies the
     * observers.
     *
     * @param aGame the game they are currently playing.
     */
    public void score() {
        //game = aGame;
        scorePlayer1 = game.getBoard().getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getBoard().getScore(ColorPawn.WHITE);
        updateInit = false;
        updateScore = true;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }

    /**
     * Recovers the game the players are currently playing and notifies the
     * observers.
     *
     * @param aGame
     */
    public void show() {
        //game = aGame;
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = true;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
    }
    
    public void init(String name1){
        game = new Game();
        game.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        game.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        scorePlayer1 = game.getBoard().getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getBoard().getScore(ColorPawn.WHITE);
        moveName = name1;
        moveAction = "Nouvelle partie";
        movePos = "/";
        moveTaken = "/";
        updateInit = true;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
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
     * End the game by telling who the winner is.
     *
     * @param aGame the game currently being played.
     */
    public void endOfGame() {
        //game = aGame;
        scorePlayer1 = game.getBoard().getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getBoard().getScore(ColorPawn.WHITE);
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = true;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }

    /**
     * If a player's turn is passed, it changes the current player.
     *
     */
    public void turnPassed() {
        //game = aGame;
        turnPassed = game.isTurnPassed(game.getCurrentPlayer());
        //turnPassed = passed;
        if (turnPassed) {
            game.changePlayer();
        }
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = true;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }
    
    public void turnPassedFX() {
        //game = aGame;
        turnPassed = game.isTurnPassed(game.getCurrentPlayer());
        //turnPassed = passed;
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = true;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }

    public boolean isUpdatePlay() {
        return updatePlay;
    }

    public boolean isUpdateScore() {
        return updateScore;
    }

    public boolean isUpdateShow() {
        return updateShow;
    }

    public boolean isUpdateInit() {
        return updateInit;
    }

    public boolean isUpdateTurnPassed() {
        return updateTurnPassed;
    }

    public boolean isUpdateEndOfGame() {
        return updateEndOfGame;
    }

    public boolean isUpdateCoordinatesError() {
        return updateCoordinatesError;
    }

    public boolean isUpdateCommandsError() {
        return updateCommandsError;
    }

    public void throwCoordinatesError() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = true;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }

    public void throwCommandsError() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = true;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        notifyObservers();
    }

    public boolean isOver() {
        return game.isOver();
    }

    public String getMoveName() {
        return moveName;
    }

    public String getMoveAction() {
        return moveAction;
    }

    public String getMovePos() {
        return movePos;
    }

    public String getMoveTaken() {
        return moveTaken;
    }
    
    public void mouseOverEnter(Coordinates coord){
        game.updatePossibleMove(game.getCurrentColor());
        isPossibleCase = game.getPossibleMove().contains(coord);
        coordX = coord.getX();
        coordY = coord.getY();
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = true;
        updateWall = false;
        notifyObservers();
    }

    public boolean isPossibleCase() {
        return isPossibleCase;
    }
    
    public int getCoordX(){
        return coordX;
    }
    
    public int getCoordY(){
        return coordY;
    }

    public boolean isUpdateMouseOver() {
        return updateMouseOver;
    }
    
    public void wall(Coordinates aCoordinate, String namePlayer){
        isWallValid = game.getBoard().putWall(aCoordinate);
        if (isWallValid) {
            game.changePlayer();
        }
        moveName = namePlayer;
        moveAction = "Place un mur";
        movePos = aCoordinate.toString();
        moveTaken = "/";
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = true;
        notifyObservers();
    }

    public boolean isWallValid() {
        return isWallValid;
    }

    public boolean isUpdateWall() {
        return updateWall;
    }
    
    public void changePlayer(){
        game.changePlayer();
    }

    public int getNbPawnsOnBoard() {
        return game.getBoard().nbCaseOccupied();
    }
    
    
    
    

}
