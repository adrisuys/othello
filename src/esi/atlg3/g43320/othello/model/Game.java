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
class Game {

    private final List<Player> players;
    private final Board board;
    private Player currentPlayer;
    private final List<List<Coordinates>> pawnsToBeTurned;
    private final List<Coordinates> possibleMove;
    private final List<Coordinates> pawnsToBeTurnedInterm;

    /**
     * Creates an instance of a game of Othello.
     */
    public Game() {
        players = new ArrayList<>();
        players.add(new Player(ColorPawn.BLACK));
        players.add(new Player(ColorPawn.WHITE));

        board = new Board();
        
        currentPlayer = players.get(0);
        pawnsToBeTurned = new ArrayList<>();
        possibleMove = new ArrayList<>();
        pawnsToBeTurnedInterm = new ArrayList<>();
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
        this.possibleMove = game.possibleMove;
        this.pawnsToBeTurnedInterm = game.pawnsToBeTurnedInterm;
    }

    /**
     * Change the current player.
     */
    void changePlayer() {
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
    Board getBoard() {
        return new Board(board);
    }

    /**
     * Returns the color of the current player.
     *
     * @return the color of the current player.
     */
    ColorPawn getCurrentColor() {
        return currentPlayer.getColor();
    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    Player getCurrentPlayer() {
        return new Player(getCurrentColor());
    }

    /**
     * Determines if a the turn of a specified player is turned or not.
     *
     * @param player the player whose turn we have to verify if it is passed or
     * not.
     * @return a boolean true if the turn is passed, false otherwise.
     */
    boolean isTurnPassed(Player player) {
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
    boolean putPawn(Coordinates aCoordinate, ColorPawn color) {
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
    void updatePossibleMove(ColorPawn color) {
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
    int nbPossibleMove(ColorPawn color) {
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
    boolean isMoveValid(Coordinates aCoordinate, ColorPawn color) {
        boolean valid ;
        try {
            valid = isMoveValidAllDirections(aCoordinate, color);
        } catch (IllegalArgumentException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Change the color of certain pawns put on the board.
     *
     * @param board the board on which the pawns are put.
     */
    void changeColorPawn() {
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
    List<List<Coordinates>> getPawnsToBeTurned() {
        return Collections.unmodifiableList(pawnsToBeTurned);
    }

    /**
     * Returns the list of the players containing the 2 players.
     *
     * @return the list of the players.
     */
    List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Returns the list of all the possible moves.
     *
     * @return the list of the coordinates of all the case a pawn can be put on.
     */
    List<Coordinates> getPossibleMove() {
        return Collections.unmodifiableList(possibleMove);
    }

    /**
     * Determines if the game is over.
     *
     * @return true if none of the players can't play anymore, false otherwise.
     */
    boolean isOver() {
        return isTurnPassed(players.get(0)) && isTurnPassed(players.get(1));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.players);
        hash = 59 * hash + Objects.hashCode(this.board);
        hash = 59 * hash + Objects.hashCode(this.currentPlayer);
        hash = 59 * hash + Objects.hashCode(this.pawnsToBeTurned);
        hash = 59 * hash + Objects.hashCode(this.possibleMove);
        hash = 59 * hash + Objects.hashCode(this.pawnsToBeTurnedInterm);
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
        if (!Objects.equals(this.possibleMove, other.possibleMove)) {
            return false;
        }
        return Objects.equals(this.pawnsToBeTurnedInterm, other.pawnsToBeTurnedInterm);
    }

    

    boolean isMoveValidAllDirections(Coordinates c, ColorPawn col) {
        pawnsToBeTurned.clear();
        boolean valid = false;
        if (c == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(c)) {
            throw new IllegalArgumentException("The coordinate is not free");
        }
        for (Directions dir : Directions.values()) {
            pawnsToBeTurnedInterm.clear();
            int x = c.getX() + dir.getxShift();
            int y = c.getY() + dir.getyShift();
            while (isInsideBoard(new Coordinates(x,y)) && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != 3 && (board.getCheckerboard()[x][y] != col.getValue())) {
                pawnsToBeTurnedInterm.add(new Coordinates(x, y));
                x = x + dir.getxShift();
                y = y + dir.getyShift();
            }
            Coordinates coordLimit = new Coordinates(x, y);
            if (isInsideBoard(coordLimit) && isGreaterThanCoordinates(coordLimit, c, dir) && board.getCheckerboard()[x][y] == col.getValue()) {
                valid = true;
            } else {
                pawnsToBeTurnedInterm.clear();
            }
            pawnsToBeTurned.add(deepCopyList(pawnsToBeTurnedInterm));
        }
        return valid;
    }

    boolean isInsideBoard(Coordinates c) {
        return c.getX() >= 0 && c.getX() < board.getROW() && c.getY() >= 0 && c.getY() < board.getCOL();
    }

    boolean isGreaterThanCoordinates(Coordinates c1, Coordinates c2, Directions d) {
        boolean ok = false;
        switch (d) {
            case S: case SE: case SW:
                ok = c1.getX() > c2.getX() + 1;
                break;
            case N : case NE : case NW:
                ok = c1.getX() < c2.getX() - 1;
                break;
            case E:
                ok = c1.getY() > c2.getY() + 1;
                break;
            case W:
                ok = c1.getY() < c2.getY() - 1;
                break;
        }
        return ok;
    }
    
    List<Coordinates> deepCopyList (List<Coordinates> l){
        List <Coordinates> l2 = new ArrayList<>();
        l.stream().map((c) -> new Coordinates(c.getX(),c.getY())).forEachOrdered((c2) -> {
            l2.add(c2);
        });
        return l2;
    }
    
    int getNbPawnsToBeTurned(){
        int cpt = 0;
        for (int i = 0; i < pawnsToBeTurned.size(); i++) {
            for (Coordinates get : pawnsToBeTurned.get(i)) {
                if (get != null){
                    cpt++;
                }
            }
        }
        return cpt;
    }
    
    int getScore(ColorPawn color){
        return board.getScore(color);
    }
    
    boolean putWall(Coordinates c){
        return board.putWall(c);
    }
    
    int nbCaseOccupied(){
        return board.nbCaseOccupied();
    }
}
