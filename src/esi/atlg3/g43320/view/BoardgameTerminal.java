/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.view;

import esi.atlg3.g43320.model.Board;

/**
 *
 * @author s_u_y_s_a
 */
public class BoardgameTerminal {

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
}
