/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the whole game of Othello.
 *
 * @author s_u_y_s_a
 */
public class Game {

    private final List<Player> players;
    private final Board board;
    private Player currentPlayer;
    private final List<List<Coordinates>> pawnsToBeTurned;
    private final List<Coordinates> pawnsAtE;
    private final List<Coordinates> pawnsAtS;
    private final List<Coordinates> pawnsAtW;
    private final List<Coordinates> pawnsAtN;
    private final List<Coordinates> pawnsAtNE;
    private final List<Coordinates> pawnsAtSE;
    private final List<Coordinates> pawnsAtNW;
    private final List<Coordinates> pawnsAtSW;
    private final List<Coordinates> possibleMove;

    /**
     * Creates an instance of a game of Othello.
     */
    public Game() {
        players = new ArrayList<>();
        players.add(new Player(Color.BLACK));
        players.add(new Player(Color.WHITE));

        board = new Board();

        currentPlayer = players.get(0);
        pawnsToBeTurned = new ArrayList<>();
        pawnsAtE = new ArrayList<>();
        pawnsAtN = new ArrayList<>();
        pawnsAtW = new ArrayList<>();
        pawnsAtS = new ArrayList<>();
        pawnsAtSW = new ArrayList<>();
        pawnsAtNE = new ArrayList<>();
        pawnsAtNW = new ArrayList<>();
        pawnsAtSE = new ArrayList<>();
        possibleMove = new ArrayList<>();
    }

    /**
     * Creates an instance of the game Othello from another one.
     *
     * @param game another game that gives its attributes to the new one.
     */
    public Game(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game can't be null");
        }
        this.players = game.players;
        this.board = game.board;
        this.currentPlayer = game.currentPlayer;
        this.pawnsToBeTurned = game.pawnsToBeTurned;
        this.pawnsAtE = game.pawnsAtE;
        this.pawnsAtN = game.pawnsAtN;
        this.pawnsAtW = game.pawnsAtW;
        this.pawnsAtS = game.pawnsAtS;
        this.pawnsAtSE = game.pawnsAtSE;
        this.pawnsAtSW = game.pawnsAtSW;
        this.pawnsAtNE = game.pawnsAtNE;
        this.pawnsAtNW = game.pawnsAtNW;
        this.possibleMove = game.possibleMove;
    }

    /**
     * Change the current player.
     */
    public void changePlayer() {
        if (currentPlayer.equals(players.get(0))) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.get(0);
        }
    }

    /**
     * Returns the board of the game.
     *
     * @return the board of the game.
     */
    public Board getBoard() {
        return new Board(board);
    }

    /**
     * Returns the color of the current player.
     *
     * @return the color of the current player.
     */
    public Color getCurrentColor() {
        return currentPlayer.getColor();
    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return new Player(getCurrentColor());
    }

    /**
     * Determines if a the turn of a specified player is turned or not.
     *
     * @param player the player whose turn we have to verify if it is passed or
     * not.
     * @return a boolean true if the turn is passed, false otherwise.
     */
    public boolean isTurnPassed(Player player) {
        return (nbPossibleMove(player.getColor()) == 0);
    }

    /**
     * Put a pawn of a specified color on a specified case (by the given
     * coordinates).
     *
     * @param aCoordinate the coordinates of the case we have to put the pawn
     * on.
     * @param color the color of the pawn that has to be put on the board.
     * @return true if the pawn has been correctly put, false otherwise.
     */
    public boolean putPawn(Coordinates aCoordinate, Color color) {
        boolean ok;
        try {
            if (isMoveValid(aCoordinate, color)) {
                board.putPawn(aCoordinate, color);
                ok = true;
            } else {
                ok = false;
            }
        } catch (IllegalArgumentException e) {
            ok = false;
        }
        return ok;

    }

    /**
     * update the list of all the possible move for the player of the specified
     * color at a given time.
     *
     * @param color the color of the player.
     */
    public void updatePossibleMove(Color color) {
        possibleMove.clear();
        for (int i = 0; i < board.getROW(); i++) {
            for (int j = 0; j < board.getCOL(); j++) {
                if (isMoveValid(new Coordinates(i, j), color)) {
                    possibleMove.add(new Coordinates(i, j));
                }
            }
        }
    }

    /**
     * Returns the number of possible move the player of the specified color
     * has.
     *
     * @param color the color of the player.
     * @return an int representing the number of possible move the player of the
     * specified color has.
     */
    public int nbPossibleMove(Color color) {
        updatePossibleMove(color);
        return possibleMove.size();
    }

    /**
     * Checks if a move (a pawn to be put on a coordinates) is valid or not
     * according to the rules of the game Othello.
     *
     * @param aCoordinate the coordinates of the case we want the pawn to be put
     * on.
     * @param color the color of the pawn (of the player).
     * @return a boolean true if the move is valid, false otherwise.
     */
    public boolean isMoveValid(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        try {
            if (isMoveValidEast(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidWest(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNorth(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSouth(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSE(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSW(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNE(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNW(aCoordinate, color)) {
                valid = true;
            }
        } catch (IllegalArgumentException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Checks if the move is valid on a horizontal line to the right of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the eastern direction,
     * false otherwise.
     */
    public boolean isMoveValidEast(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int y = aCoordinate.getY() + 1;
        int x = aCoordinate.getX();
        while (y < board.getCOL() && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtE.add(new Coordinates(x, y));
            y++;
        }
        if (y < board.getCOL() && y > aCoordinate.getY() + 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtE.clear();
        }
        pawnsToBeTurned.add(pawnsAtE);
        return valid;
    }

    /**
     * Checks if the move is valid on a horizontal line to the left of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the western direction,
     * false otherwise.
     */
    public boolean isMoveValidWest(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int y = aCoordinate.getY() - 1;
        int x = aCoordinate.getX();
        while (y >= 0 && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtW.add(new Coordinates(x, y));
            y--;
        }
        if (y >= 0 && y < aCoordinate.getY() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtW.clear();
        }
        pawnsToBeTurned.add(pawnsAtW);
        return valid;
    }

    /**
     * Checks if the move is valid on a vertical line to the top of a specified
     * case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the northern direction,
     * false otherwise.
     */
    public boolean isMoveValidNorth(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY();
        while (x >= 0 && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtN.add(new Coordinates(x, y));
            x--;
        }
        if (x >= 0 && x < aCoordinate.getX() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtN.clear();
        }
        pawnsToBeTurned.add(pawnsAtN);
        return valid;
    }

    /**
     * Checks if the move is valid on a vertical line to the bottom of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the southern direction,
     * false otherwise.
     */
    public boolean isMoveValidSouth(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY();
        while (x < board.getROW() && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtS.add(new Coordinates(x, y));
            x++;
        }
        if (x < board.getROW() && x > aCoordinate.getX() + 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtS.clear();
        }
        pawnsToBeTurned.add(pawnsAtS);
        return valid;
    }

    /**
     * Checks if the move is valid on a diagonal line to the bottom-right of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the south-eastern
     * direction, false otherwise.
     */
    public boolean isMoveValidSE(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY() + 1;
        while ((x < board.getROW() && y < board.getCOL())
                && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            pawnsAtSE.add(new Coordinates(x, y));
            x++;
            y++;
        }
        if ((x < board.getROW() && y < board.getCOL())
                && (x > aCoordinate.getX() + 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtSE.clear();
        }
        pawnsToBeTurned.add(pawnsAtSE);
        return valid;
    }

    /**
     * Checks if the move is valid on a diagonal line to the bottom-left of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the south-western
     * direction, false otherwise.
     */
    public boolean isMoveValidSW(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY() - 1;
        while ((x < board.getROW() && y >= 0)
                && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            pawnsAtSW.add(new Coordinates(x, y));
            x++;
            y--;
        }
        if (x < board.getROW() && y >= 0 && (x > aCoordinate.getX() + 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtSW.clear();
        }
        pawnsToBeTurned.add(pawnsAtSW);
        return valid;
    }

    /**
     * Checks if the move is valid on a diagonal line to the top-right of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the north-eastern
     * direction, false otherwise.
     */
    public boolean isMoveValidNE(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY() + 1;
        while ((x >= 0 && y < board.getCOL()
                && board.getCheckerboard()[x][y] != 0
                && (board.getCheckerboard()[x][y] != color.getValue()))) {
            pawnsAtNE.add(new Coordinates(x, y));
            x--;
            y++;
        }
        if (x >= 0 && y < board.getCOL() && (x < aCoordinate.getX() - 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtNE.clear();
        }
        pawnsToBeTurned.add(pawnsAtNE);
        return valid;
    }

    /**
     * Checks if the move is valid on a diagonal line to the top-left of a
     * specified case.
     *
     * @param aCoordinate the coordinates of the specified case.
     * @param color the color of the pawn that has to be put.
     * @return a boolean true if the move is valid on the north-western
     * direction, false otherwise.
     */
    public boolean isMoveValidNW(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY() - 1;
        while ((x >= 0 && y >= 0) && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            pawnsAtNW.add(new Coordinates(x, y));
            x--;
            y--;
        }
        if (x >= 0 && y >= 0 && x < aCoordinate.getX() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtNW.clear();
        }
        pawnsToBeTurned.add(pawnsAtNW);
        return valid;
    }

    /**
     * Change the color of certain pawns put on the board.
     *
     * @param board the board on which the pawns are put.
     */
    public void changeColorPawn(Board board) {
        for (int i = 0; i < pawnsToBeTurned.size(); i++) {
            for (int j = 0; j < pawnsToBeTurned.get(i).size(); j++) {
                board.getCheckerboard()[pawnsToBeTurned.get(i).get(j).getX()][pawnsToBeTurned.get(i).get(j).getY()] = getCurrentColor().getValue();
            }
        }
    }

    /**
     * Returns the list of the pawns that have to switch color.
     *
     * @return the list of the coordinates of the case that have a pawn that can
     * switch colors.
     */
    public List<List<Coordinates>> getPawnsToBeTurned() {
        return Collections.unmodifiableList(pawnsToBeTurned);
    }

    /**
     * Returns the list of the players containing the 2 players.
     *
     * @return the list of the players.
     */
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Returns the list of all the possible moves.
     *
     * @return the list of the coordinates of all the case a pawn can be put on.
     */
    public List<Coordinates> getPossibleMove() {
        return Collections.unmodifiableList(possibleMove);
    }

    /**
     * Determines if the game is over.
     *
     * @return true if none of the players can't play anymore, false otherwise.
     */
    public boolean isOver() {
        return isTurnPassed(players.get(0)) && isTurnPassed(players.get(1));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.players);
        hash = 37 * hash + Objects.hashCode(this.board);
        hash = 37 * hash + Objects.hashCode(this.currentPlayer);
        hash = 37 * hash + Objects.hashCode(this.pawnsToBeTurned);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtE);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtS);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtW);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtN);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtNE);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtSE);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtNW);
        hash = 37 * hash + Objects.hashCode(this.pawnsAtSW);
        hash = 37 * hash + Objects.hashCode(this.possibleMove);
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
        final Game other = (Game) obj;
        if (!Objects.equals(this.players, other.players)) {
            return false;
        }
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        if (!Objects.equals(this.currentPlayer, other.currentPlayer)) {
            return false;
        }
        if (!Objects.equals(this.pawnsToBeTurned, other.pawnsToBeTurned)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtE, other.pawnsAtE)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtS, other.pawnsAtS)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtW, other.pawnsAtW)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtN, other.pawnsAtN)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtNE, other.pawnsAtNE)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtSE, other.pawnsAtSE)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtNW, other.pawnsAtNW)) {
            return false;
        }
        if (!Objects.equals(this.pawnsAtSW, other.pawnsAtSW)) {
            return false;
        }
        return Objects.equals(this.possibleMove, other.possibleMove);
    }

}
