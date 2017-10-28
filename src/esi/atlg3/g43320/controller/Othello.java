/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.controller;

import esi.atlg3.g43320.model.Board;
import esi.atlg3.g43320.model.Color;
import esi.atlg3.g43320.model.Coordinates;
import esi.atlg3.g43320.view.BoardgameTerminal;

/**
 *
 * @author s_u_y_s_a
 */
public class Othello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.getCheckerboard()[3][2] = Color.WHITE.getValue();
        board.putPawn(new Coordinates(4,6), Color.BLACK);
        BoardgameTerminal.displayBoardgame(board);
    }
    
}
