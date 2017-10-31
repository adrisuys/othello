/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.dp.Observer;
import esi.atlg3.g43320.othello.model.Board;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.Scanner;

/**
 *
 * @author s_u_y_s_a
 */
public class BoardgameTerminal implements Observer {
    
    private OthelloModel othello;

    public BoardgameTerminal(OthelloModel observable) {
        if (observable == null) {
            throw new IllegalArgumentException("Nothing to observe");
        }
        othello = (OthelloModel) observable;
        othello.registerObserver(this);
    }
    
    public static void displayBoardgame(Board board) {
        int cpt = 0;
        String aString = "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |" + "\n";
        aString = aString + "-------------------------------------"+"\n";
        for (int i = 0; i < board.getROW(); i++) {
            aString = aString + "| " + cpt+ " ";
            for (int j = 0; j < board.getCOL(); j++) {
                if (board.getCheckerboard()[i][j] == 0) {
                    aString = aString + "| . ";
                } else {
                    if(board.getCheckerboard()[i][j]==1){
                        aString = aString + "| B ";
                    } else {
                        aString = aString + "| W ";
                    }
                }
            }
            aString = aString + "|";
            aString = aString + ("\n" + "-------------------------------------" + "\n");
            cpt++;
        }
        System.out.println(aString);
    }
    
    public static void displayRules() {
        System.out.println("Othello");
        System.out.println("Rules : Outflank your opponent by surrounding a vertical, horizontal, or diagonal row of their discs with your discs. \nThen flip the whole row over so that it’s the color of your discs. Take turns making moves and outflanking \n until you can’t make any more moves. At this point, whoever has more discs flipped to their side wins. ");
    }
    
    public static void displayUsage(){
        System.out.println("You can use the following 3 command to play this game");
        System.out.println("show : the checkerboard will be displayed");
        System.out.println("score : the current score will be displayed");
        System.out.println("play i j : a pawn will be put on the case (i,j), i being the row and j the column");
        System.out.println("if such a move is not valid, an error message will appear");
    }

    @Override
    public void updatePlay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateErrorInputCoordinates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateErrorInputCommand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
