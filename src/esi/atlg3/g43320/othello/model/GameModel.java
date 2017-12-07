/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import esi.atlg3.g43320.othello.dpObs.Observable;
import esi.atlg3.g43320.othello.dpObs.Observer;
import esi.atlg3.g43320.othello.strategy.IARandomStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the model of the Game, it implements Observable.
 *
 * @author s_u_y_s_a
 */
public class GameModel implements Observable {

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
    private boolean isIA;

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
    private boolean updateChangePlayer;

    /**
     * Creates an instance of a model of Othello.
     */
    public GameModel() {
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
     * @param namePlayer the name of the player that has to play.
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    public void play(Coordinates aCoordinate, String namePlayer) throws GameException {
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
        game.updatePossibleMove(game.getCurrentColor());
        notifyObservers();
    }

    /**
     * Initializes the game.
     *
     * @param name2 the name of the first player.
     * @param name3 the name of the second player.
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    public void init(String name2, String name3) throws GameException {
        if (game.getStatus() != GameStatus.INIT) {
            throw new GameException("Status error : game has to be initialize now");
        }
        game = new Game();
        game.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        game.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        game.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        game.setStatus(GameStatus.PLAY);
        scorePlayer1 = game.getScore(ColorPawn.BLACK);
        scorePlayer2 = game.getScore(ColorPawn.WHITE);
        game.getPlayers().get(0).setName(name2);
        game.getPlayers().get(1).setName(name3);
        moveName = name2;
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
        updateChangePlayer = false;
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
        updateChangePlayer = true;
        notifyObservers();
    }

    /**
     * If a player's turn is passed, it changes the current player (FX version).
     *
     * @param aBool indicating if the IA is playing against a player or not.
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
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
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    private void wall(Coordinates aCoordinate, String namePlayer) throws GameException {
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
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
        updateChangePlayer = false;
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

    /**
     * Play a full turn of 1 player if the game is on mode 'Player vs Player'.
     * If the game is on mode 'Player vs IA', it plays the turn of the IA
     * directly after the players has played.
     *
     * @param primaryKeyPressed indicates if the left button of the mouse has
     * been pressed.
     * @param coord the coordinates of the case on which the player wants to
     * play.
     * @param name1 the name of the first player.
     * @param name2 the name of the second player.
     * @param wallChosenOverPass a boolean indicating that the player has chosen
     * to play a wall when he had to pass his turn.
     * @param iaChosen a boolean indicating if the IA is playing.
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    public void playATurn(boolean primaryKeyPressed, Coordinates coord,
            String name1, String name2, boolean wallChosenOverPass, boolean iaChosen) throws GameException {
        if (iaChosen) {
            game.getPlayers().get(1).setStrategy(new IARandomStrategy(this));
        }
        turnPassedFX(iaChosen);
        if (isTurnPassed()) {
            if (!wallChosenOverPass) {
                pass(getCurrentPlayer().getName());
                checkGameOver();
                game.getCurrentPlayer().runStrategy();
                if (iaChosen){checkGameOver();}
            } else {
                wall(coord, getCurrentPlayer().getName());
                checkGameOver();
                game.getCurrentPlayer().runStrategy();
                if (iaChosen){checkGameOver();}
            }
        } else {
            if (primaryKeyPressed) {
                play (coord, getCurrentPlayer().getName());
                checkGameOver();
                game.getCurrentPlayer().runStrategy();
                if (iaChosen){checkGameOver();}
            } else {
                wall(coord, getCurrentPlayer().getName());
                checkGameOver();
                game.getCurrentPlayer().runStrategy();
                if (iaChosen){checkGameOver();}
            }
        }
    }

    /**
     * Checks if the game is over. If true, it ends the game and reinitialize a
     * new game by asking the type of game (against a player or an IA) and the
     * name of the players.
     *
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    public void checkGameOver() throws GameException {
        if (isOver()) {
            endOfGame();
        }
    }

    /**
     * Asks the players to enter their names. If the IA is playing, its name
     * will always be "IA".
     *
     */
    public void askName() {
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
        updateChangePlayer = false;
        game.setStatus(GameStatus.INIT);
        notifyObservers();
    }

    /**
     * Returns a boolean indicating the the game must be updated following the
     * choosing of the names of the players.
     *
     * @return a boolean indicating the the game must be updated following the
     * choosing of the names of the players.
     */
    public boolean isUpdateAskName() {
        return updateAskName;
    }

    /**
     * Indicating the frame of history of moves has to be updated following the
     * decision of a player to pass.
     *
     * @param name the name of the player that passes his turn.
     */
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
        updateChangePlayer = false;
        game.changePlayer();
        notifyObservers();
    }

    /**
     * Returns a boolean indicating that the game must be updated following the
     * passing of a player.
     *
     * @return a boolean indicating that the game must be updated following the
     * passing of a player.
     */
    public boolean isUpdatePass() {
        return updatePass;
    }

    /**
     * Gets the pawn at a certain case specified by the coordinate.
     *
     * @param c the coordinate of the case.
     * @return the pawn on that coordinate.
     */
    public int getPawn(Coordinates c) {
        return game.getBoard().getPawn(c);
    }

    /**
     * Get the number of row of the boardgame.
     *
     * @return the number of row of the boardgame.
     */
    public int getROW() {
        return game.getBoard().getROW();
    }

    /**
     * Gets the number of columns of the boardgame.
     *
     * @return the number of columns of the boardgame.
     */
    public int getCOL() {
        return game.getBoard().getCOL();
    }

    /**
     * Returns the array of int representing the boardgame.
     *
     * @return the array of int representing the boardgame.
     */
    public int[][] getCheckerboard() {
        return game.getBoard().getCheckerboard();
    }

    /**
     * Returns a boolean indicating if an IA is playing.
     *
     * @return a boolean indicating if an IA is playing.
     */
    public boolean isIsIA() {
        return isIA;
    }

    /**
     * Updates the list of the possible move for the player of the specified
     * color.
     *
     * @param color the color of the player.
     */
    public void updatePossibleMove(ColorPawn color) {
        game.updatePossibleMove(color);
    }

    /**
     * Returns a boolean indicating that the game must be updated following the
     * change of player.
     *
     * @return a boolean indicating that the game must be updated following the
     * change of player.
     */
    public boolean isUpdateChangePlayer() {
        return updateChangePlayer;
    }

    /**
     * Remove a pawn at a certain coordinate.
     *
     * @param aCoordinate the coordinate of the case from which the pawn must be
     * removed.
     */
    public void removePawn(Coordinates aCoordinate) {
        game.removePawn(aCoordinate);
    }

    /**
     * Put a pawn on a specified case.
     *
     * @param c the coordinates of the case.
     * @return true if the pawn could be put properly, false otherwise.
     * @throws esi.atlg3.g43320.othello.model.GameException if the game status
     * is not the right one.
     */
    public boolean putPawn(Coordinates c) throws GameException {
        return game.putPawn(c, ColorPawn.WHITE);
    }

    /**
     * Returns the number of pawn that a player can take from the other one by
     * playing.
     *
     * @return the number of pawn that a player can take from the other one by
     * playing.
     */
    public int getNbPawnsTaken() {
        return game.getNbPawnsToBeTurned();
    }

    /**
     * Get the list of all the pawn to be turned after one player has played.
     *
     * @return the list of all the pawn to be turned after one player has
     * played.
     */
    public List<List<Coordinates>> getPawnsToBeTurned() {
        return Collections.unmodifiableList(game.getPawnsToBeTurned());
    }

    /**
     * Change the color of the pawn won by a player after its move.
     */
    public void changeColorPawn() {
        game.changeColorPawn();
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
        updateChangePlayer = false;
        notifyObservers();
    }

    /**
     * Plays a full game if the two players are both IA.
     *
     * @throws GameException (see method checkGameOver(String name)).
     */
    public void playOnlyComputers() throws GameException {
        game.getPlayers().get(0).setStrategy(new IARandomStrategy(this));
        game.getPlayers().get(1).setStrategy(new IARandomStrategy(this));
        while (!isOver()) {
            game.getCurrentPlayer().runStrategy();
        }
        checkGameOver();

    }

    /**
     * Returns the current player of the game.
     *
     * @return the current player of the game.
     */
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }
    
    /**
     * Set the status of the game to INIT.
     */
    public void setINITStatus(){
        game.setStatus(GameStatus.INIT);
    }
    
    /**
     * Returns the list of players.
     * @return the list of players.
     */
    public List<Player> getPlayers(){
        return Collections.unmodifiableList(game.getPlayers());
    }
}
