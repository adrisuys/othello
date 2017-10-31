/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import java.util.Arrays;

/**
 *
 * @author s_u_y_s_a
 */
public class Board {
    private final int[][] checkerboard;
    private final int ROW = 8;
    private final int COL = 8;

    public Board() {
        this.checkerboard = new int[ROW][COL];
        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++){
                this.checkerboard[i][j]=0;
            }
        }
    }
    
    public Board(Board board){
        this.checkerboard = board.checkerboard;   
    }

    public int[][] getCheckerboard() {
        return Arrays.copyOf(checkerboard, checkerboard.length);
        //return checkerboard;
    }

    public int getROW() {
        return ROW;
    }

    public int getCOL() {
        return COL;
    }
    
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
    
    public boolean isFree (Coordinates aCoordinate) {
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        return checkerboard[aCoordinate.getX()][aCoordinate.getY()] == 0;
    }
    
    public int getPawn(Coordinates aCoordinate) {
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        return checkerboard[aCoordinate.getY()][aCoordinate.getX()];
    }
    
    public int getScore (Color color) {
        int score = 0;
        for (int i=0 ; i<ROW; i++){
            for (int j=0; j<COL;j++){
                if (checkerboard[i][j]==color.getValue()) {
                    score++;
                }
            }
        }
        return score;
    }
}
