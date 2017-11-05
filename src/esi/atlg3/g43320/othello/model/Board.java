/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import java.util.Arrays;

/**
 * Thic class represents the board on which the game is played.
 *
 * @author s_u_y_s_a
 */
public class Board {

    private final int[][] checkerboard;
    private final static int ROW = 8;
    private final static int COL = 8;

    /**
     * Creates an instance of a Board.
     */
    public Board() {
        this.checkerboard = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.checkerboard[i][j] = 0;
            }
        }
    }

    /**
     * Creates an instance of a Board from another Board.
     *
     * @param board an another Board whose checkerboard is copied in the new
     * one.
     */
    public Board(Board board) {
        if (board == null){
            throw new IllegalArgumentException("the board can't be null");
        }
        this.checkerboard = board.checkerboard;
    }

    /**
     * Returns the checkerboard of the Board, an array of int.
     *
     * @return the checkerboard of the Board, an array of int.
     */
    public int[][] getCheckerboard() {
        return Arrays.copyOf(checkerboard, checkerboard.length);
        //return checkerboard;
    }

    /**
     * Returns the number of rows the board has.
     *
     * @return the number of rows the board has.
     */
    public int getROW() {
        return ROW;
    }

    /**
     * Returns the number of columns the board has.
     *
     * @return the number of columns the board has.
     */
    public int getCOL() {
        return COL;
    }

    /**
     * Puts a pawn of a specified color on the board on a specified case.
     *
     * @param aCoordinate the coordinates of the case the pawn has to be put on.
     * @param color the color of the pawn.
     */
    public void putPawn(Coordinates aCoordinate, Color color) {
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!isFree(aCoordinate)) {
            throw new IllegalArgumentException("There is already a pawn on that case of the board");
        }
        int aPawn = color.getValue();
        checkerboard[aCoordinate.getX()][aCoordinate.getY()] = aPawn;
    }

    /**
     * Determines if a case is empty or not.
     *
     * @param aCoordinate the coordinates of the case we have to check.
     * @return a boolean indicating true if the case is empty, false otherwise.
     */
    public boolean isFree(Coordinates aCoordinate) {
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        return checkerboard[aCoordinate.getX()][aCoordinate.getY()] == 0;
    }

    /**
     * Returns an int representing what is on a specified case of the board.
     *
     * @param aCoordinate the coordinates of the case we have to look at.
     * @return 1 if there is a black pawn on the case, 2 if there is a white
     * pawn and 0 if the case is empty.
     */
    public int getPawn(Coordinates aCoordinate) {
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        return checkerboard[aCoordinate.getY()][aCoordinate.getX()];
    }

    /**
     * Computes the score of the player that has the specified color of pawn.
     *
     * @param color the color of the pawns of a player.
     * @return an int representing the number of pawns of a specified color that
     * are on the board at a given time.
     */
    public int getScore(Color color) {
        int score = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (checkerboard[i][j] == color.getValue()) {
                    score++;
                }
            }
        }
        return score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Arrays.deepHashCode(this.checkerboard);
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
        final Board other = (Board) obj;
        return Arrays.deepEquals(this.checkerboard, other.checkerboard);
    }
    
    
}
