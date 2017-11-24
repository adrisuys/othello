/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.dpObs.Observable;
import esi.atlg3.g43320.othello.dpObs.Observer;
import esi.atlg3.g43320.othello.strategy.RandomStrategy;
import esi.atlg3.g43320.othello.strategy.StrategyIA;
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
    private boolean isIAChosen;
    private boolean isIA;
    private final StrategyIA strategy;

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
    private boolean updateConfirm;
    private boolean updateGiveUp;
    private boolean updateProblemPass;
    private boolean updateAskName;
    private boolean updatePass;
    private boolean updateTypeGame;
    private boolean updateChangePlayer;

    /**
     * Creates an instance of a model of Othello.
     */
    public OthelloModel() {
        observers = new ArrayList<>();
        game = new Game();
        strategy = new RandomStrategy(this);
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
     * @param namePlayer the name of the player that has to play.
     */
    public void play(Coordinates aCoordinate, String namePlayer) {
        game.updatePossibleMove(game.getCurrentColor());
        if (!game.getPossibleMove().contains(aCoordinate)) {
            validPlay = false;
        } else {
            validPlay = true;
            game.putPawn(aCoordinate, game.getCurrentColor());
            game.changeColorPawn();
            game.changePlayer();
        }
        scorePlayer1 = game.getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getScore(ColorPawn.WHITE);
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
    }

    /**
     * Gets the score of the 2 players at a given time and notifies the
     * observers.
     *
     */
    public void score() {
        scorePlayer1 = game.getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getScore(ColorPawn.WHITE);
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Recovers the game the players are currently playing and notifies the
     * observers.
     *
     */
    public void show() {
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
    }

    /**
     * Initializes the game.
     *
     * @param name1 the name of the first player (always the black one).
     */
    public void init(String name1) {
        game = new Game();
        game.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        game.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        scorePlayer1 = game.getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getScore(ColorPawn.WHITE);
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
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
     */
    public void endOfGame() {
        scorePlayer1 = game.getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getScore(ColorPawn.WHITE);
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * If a player's turn is passed, it changes the current player (terminal
     * version).
     *
     */
    public void turnPassed() {
        turnPassed = game.isTurnPassed(game.getCurrentPlayer());
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * If a player's turn is passed, it changes the current player (FX version).
     *
     */
    public void turnPassedFX(boolean aBool) {
        isIA = aBool;
        turnPassed = game.isTurnPassed(game.getCurrentPlayer());
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean indicating that the display of the game must be updated
     * after a player has played.
     *
     * @return a boolean indicating that the display of the game must be updated
     * after a player has played.
     */
    public boolean isUpdatePlay() {
        return updatePlay;
    }

    /**
     * Returns a boolean indicating that the score must be updated.
     *
     * @return a boolean indicating that the score must be updated.
     */
    public boolean isUpdateScore() {
        return updateScore;
    }

    /**
     * Returns a boolean indicating that the board must be updated.
     *
     * @return a boolean indicating that the board must be updated.
     */
    public boolean isUpdateShow() {
        return updateShow;
    }

    /**
     * Returns a boolean indicating that the display of the game must be updated
     * after an initialization.
     *
     * @return a boolean indicating that the display of the game must be updated
     * after an initialization.
     */
    public boolean isUpdateInit() {
        return updateInit;
    }

    /**
     * Returns a boolean indicating that the game must be updated after a player
     * has passed its turn.
     *
     * @return a boolean indicating that the game must be updated after a player
     * has passed its turn.
     */
    public boolean isUpdateTurnPassed() {
        return updateTurnPassed;
    }

    /**
     * Returns a boolean indicating that the game must be updated it has ended.
     *
     * @return a boolean indicating that the game must be updated it has ended.
     */
    public boolean isUpdateEndOfGame() {
        return updateEndOfGame;
    }

    /**
     * Returns a boolean indicating that the game must be updated after an error
     * occured when a player choose coordinates for its pawn to be put.
     *
     * @return a boolean indicating that the game must be updated after an error
     * occured when a player choose coordinates for its pawn to be put.
     */
    public boolean isUpdateCoordinatesError() {
        return updateCoordinatesError;
    }

    /**
     * Returns a boolean indicating that the game must be updated after an error
     * occured when a player entered a command.
     *
     * @return a boolean indicating that the game must be updated after an error
     * occured when a player entered a command.
     */
    public boolean isUpdateCommandsError() {
        return updateCommandsError;
    }

    /**
     * Throws an error if an error occured when the player entered a wrong
     * coordinates.
     */
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Throws an error if an error occured when the player entered a wrong
     * command line.
     */
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Return a boolean indicating if the game is over.
     *
     * @return a boolean indicating if the game is over.
     */
    public boolean isOver() {
        return game.isOver();
    }

    /**
     * Get the name of the current player to be put in the table of history of
     * moves.
     *
     * @return the name of the current player
     */
    public String getMoveName() {
        return moveName;
    }

    /**
     * Get the action of the current player to be put in the table of history of
     * moves.
     *
     * @return the action the current player just did.
     */
    public String getMoveAction() {
        return moveAction;
    }

    /**
     * Get the position at which the current player played to be put in the
     * table of history of moves.
     *
     * @return the position at which the current player played.
     */
    public String getMovePos() {
        return movePos;
    }

    /**
     * Get the number of pawn the player has taken by playing to be put in the
     * table of history of moves.
     *
     * @return the number of pawn the player has taken by playing.
     */
    public String getMoveTaken() {
        return moveTaken;
    }

    /**
     * Indicates that the mouse has entered a case of the board.
     *
     * @param coord the coordinates of the case the mouse has entered in.
     */
    public void mouseOverEnter(Coordinates coord) {
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean inidicating if it is possible to play a pawn in a case.
     *
     * @return a boolean inidicating if it is possible to play a pawn in a case.
     */
    public boolean isPossibleCase() {
        return isPossibleCase;
    }

    /**
     * Returns the absissa of a coordinate.
     *
     * @return the absissa of a coordinate.
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Returns the ordinate of a coordinate.
     *
     * @return the ordinate of a coordinate.
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Returns a boolean indicating if the mouse is over a case.
     *
     * @return a boolean indicating if the mouse is over a case.
     */
    public boolean isUpdateMouseOver() {
        return updateMouseOver;
    }

    /**
     * Puts a wall on a specified case.
     *
     * @param aCoordinate the coordinate of the case the wall is to be put.
     * @param namePlayer the name of the current player.
     */
    public void wall(Coordinates aCoordinate, String namePlayer) {
        isWallValid = game.putWall(aCoordinate);
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
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean indicating if a wall has been put correctly.
     *
     * @return a boolean indicating if a wall has been put correctly.
     */
    public boolean isWallValid() {
        return isWallValid;
    }

    /**
     * Returns a boolean indicating if the game has to be updated following the
     * setting of a wall.
     *
     * @return a boolean indicating if the game has to be updated following the
     * setting of a wall.
     */
    public boolean isUpdateWall() {
        return updateWall;
    }

    /**
     * Change the current player.
     */
    public void changePlayer() {
        game.changePlayer();
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
    }

    /**
     * Returns the number of occupied case of the board.
     *
     * @return the number of occupied case of the board.
     */
    public int getNbPawnsOnBoard() {
        return game.nbCaseOccupied();
    }

    /**
     * Confirms an action.
     */
    public void confirm() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = true;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean indicating if the game has to be updated following the
     * confirmation of an action.
     *
     * @return a boolean indicating if the game has to be updated following the
     * confirmation of an action.
     */
    public boolean isUpdateConfirm() {
        return updateConfirm;
    }

    /**
     * States that the player has given up.
     */
    public void giveUp() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = true;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean indicating if the game has to be updated following the
     * abandonment of a player.
     *
     * @return a boolean indicating if the game has to be updated following the
     * abandonment of a player.
     */
    public boolean isUpdateGiveUp() {
        return updateGiveUp;
    }

    /**
     * States that a player can't pass its turn even if he wants to.
     */
    public void problemPass() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = true;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * Returns a boolean indicating if the game has to be updated following the
     * will of a player to pass.
     *
     * @return a boolean indicating if the game has to be updated following the
     * will of a player to pass.
     */
    public boolean isUpdateProblemPass() {
        return updateProblemPass;
    }

    /**
     * Returns the board of the game.
     *
     * @return the board of the game.
     */
    public Board getBoard() {
        return new Board(game.getBoard());
    }

    /**
     * Returns the list of all the possible moves.
     *
     * @return the list of all the possible moves.
     */
    public List<Coordinates> getPossibleMove() {
        return Collections.unmodifiableList(game.getPossibleMove());
    }

    /**
     * Returns the color of the current player.
     *
     * @return the color of the current player.
     */
    public ColorPawn getCurrentColor() {
        return game.getCurrentPlayer().getColor();
    }

    public void playATurn(boolean primaryKeyPressed, Coordinates coord,
            String name1, String name2, boolean wallChosenOverPass, boolean iaChosen) {
        turnPassedFX(iaChosen);
        if (isTurnPassed()) {
            if (!wallChosenOverPass) {
                if (getCurrentColor() == ColorPawn.BLACK) {
                    pass(name1);
                } else {
                    pass(name2);
                }
                changePlayer();
                checkGameOver(name1, iaChosen);
                if (iaChosen){
                    strategy.play(name1);
                }
            } else {
                if (getCurrentColor() == ColorPawn.BLACK) {
                    wall(coord, name1);
                } else {
                    wall(coord, name2);
                }
                checkGameOver(name1, iaChosen);
                if (iaChosen){
                    strategy.play(name1);
                }
            }
        } else {
            if (primaryKeyPressed) {
                if (getGame().getCurrentColor() == ColorPawn.BLACK) {
                    play(coord, name1);
                } else {
                    play(coord, name2);
                }
                checkGameOver(name1, iaChosen);
                if (iaChosen){
                    strategy.play(name1);
                }
            } else {
                if (getGame().getCurrentColor() == ColorPawn.BLACK) {
                    wall(coord, name1);
                } else {
                    wall(coord, name2);
                }
                checkGameOver(name1, iaChosen);
                if (iaChosen){
                    strategy.play(name1);
                }
            }
        }

    }

    public void checkGameOver(String name1, boolean bool) {
        if (isOver()) {
            endOfGame();
            askTypeGame();
            askName(bool);
            init(name1);
        }
    }

    public void askName(boolean aBool) {
        isIAChosen = aBool;
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = true;
        updatePass = false;
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    public boolean isIsIAChosen() {
        return isIAChosen;
    }

    public boolean isUpdateAskName() {
        return updateAskName;
    }

    public void askTypeGame() {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = false;
        updateTypeGame = true;
        updateChangePlayer = true;
        notifyObservers();
    }

    public boolean isUpdateTypeGame() {
        return updateTypeGame;
    }

    public void pass(String name) {
        updateInit = false;
        updateScore = false;
        updatePlay = false;
        updateShow = false;
        updateTurnPassed = false;
        updateEndOfGame = false;
        updateCommandsError = false;
        updateCoordinatesError = false;
        updateMouseOver = false;
        updateWall = false;
        updateConfirm = false;
        updateGiveUp = false;
        updateProblemPass = false;
        updateAskName = false;
        updatePass = true;
        moveName = name;
        moveAction = "Passe son tour";
        movePos = "/";
        moveTaken = "/";
        updateTypeGame = false;
        updateChangePlayer = true;
        notifyObservers();
    }

    public boolean isUpdatePass() {
        return updatePass;
    }

    public int getPawn(Coordinates c) {
        return game.getBoard().getPawn(c);
    }

    public int getROW() {
        return game.getBoard().getROW();
    }

    public int getCOL() {
        return game.getBoard().getCOL();
    }

    public int[][] getCheckerboard() {
        return game.getBoard().getCheckerboard();
    }

    public boolean isIsIA() {
        return isIA;
    }
    
    public void updatePossibleMove(ColorPawn color){
        game.updatePossibleMove(color);
    }

    public boolean isUpdateChangePlayer() {
        return updateChangePlayer;
    }
    
    

}
