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

    public OthelloModel() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }
    
    @Override
    public void notifyObserversPlay(){
        for (Observer obs : observers){
            obs.updatePlay();
        }
    }
    
    public void play(Game aGame, Coordinates aCoordinate, boolean valid, boolean passed){
        validPlay = valid;
        game = aGame;
        coord = aCoordinate;
        turnPassed = passed;
        if (game.isTurnPassed(game.getCurrentPlayer())){
            turnPassed = true;
            game.changePlayer();
        } else {
            game.updatePossibleMove(game.getCurrentColor());
            if (!game.getPossibleMove().contains(aCoordinate)){
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

    @Override
    public void notifyObserversScore() {
        for (Observer obs : observers){
            obs.updateScore();
        }
    }

    @Override
    public void notifyObserversShow() {
        for (Observer obs : observers){
            obs.updateShow();
        }
    }
    
    public void score (Game aGame){
        game = aGame;
        scorePlayer1 = game.getBoard().getScore(Color.BLACK);
        scorePlayer2 = game.getBoard().getScore(Color.WHITE);
        notifyObserversScore();
    }
    
    public void show (Game aGame){
        game = aGame;
        notifyObserversShow();
    }
    
    public void init (Game aGame) {
        game = aGame;
        game.getBoard().putPawn(new Coordinates(3,3), Color.WHITE);
        game.getBoard().putPawn(new Coordinates(3,4), Color.BLACK);
        game.getBoard().putPawn(new Coordinates(4,3), Color.BLACK);
        game.getBoard().putPawn(new Coordinates(4,4), Color.WHITE);
        notifyObserversInit();
    }

    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public Game getGame() {
        return new Game(game);
    }

    public Coordinates getCoord() {
        return new Coordinates(coord.getX(), coord.getY());
    }
    
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public Board getBoard() {
        return new Board(board);
    }

    @Override
    public void notifyObserversInit() {
        for (Observer obs : observers){
            obs.updateInit();
        }
    }

    @Override
    public void notifyObserversErrorInputCommand() {
        for (Observer obs : observers){
            obs.updateErrorInputCommand();
        }
    }

    @Override
    public void notifyObserversErrorInputCoordinates() {
        for (Observer obs : observers){
            obs.updateErrorInputCoordinates();
        }
    }

    public boolean isValidPlay() {
        return validPlay;
    }

    public boolean isTurnPassed() {
        return turnPassed;
    }
   
    
    

}
