/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.Board;
import esi.atlg3.g43320.othello.model.Color;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.Game;
import esi.atlg3.g43320.othello.model.OthelloModel;
import esi.atlg3.g43320.othello.view.BoardgameTerminal;
import java.util.Scanner;

/**
 *
 * @author s_u_y_s_a
 */
public class OthelloFacade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        OthelloModel othello = new OthelloModel();

        othello.init(game);
        boolean unvalid = true;
        boolean validPlay = true;
        boolean turnPassed = true;

        while (!game.isOver()) {
            while (unvalid) {
                unvalid = false;
                Scanner keyboard = new Scanner(System.in);
                String input = keyboard.nextLine();
                String[] inputArray = input.split("\\s+");
                if ("SHOW".equals(inputArray[0].toUpperCase())) {
                    othello.show(game);
                } else if ("SCORE".equals(inputArray[0].toUpperCase())) {
                    othello.score(game);
                } else if ("PLAY".equals(inputArray[0].toUpperCase())) {
                    if (verifyArgsCoordinates(inputArray, game)) {
                        int x = Integer.parseInt(inputArray[1]);
                        int y = Integer.parseInt(inputArray[2]);
                        othello.play(game, new Coordinates(x, y), validPlay, turnPassed);
                        unvalid = !validPlay;
                    } else {
                        othello.notifyObserversErrorInputCoordinates();
                        unvalid = true;
                    }
                } else {
                    othello.notifyObserversErrorInputCommand();
                    unvalid = true;
                }
            }
        }
    }

    public static boolean verifyArgsCoordinates(String[] tabStr, Game game) {
        boolean ok = true;
        try {
            int row = Integer.parseInt(tabStr[1]);
            int col = Integer.parseInt(tabStr[2]);
            if (row < 0 || row >= game.getBoard().getROW()) {
                ok = false;
            }
            if (col < 0 || col >= game.getBoard().getCOL()) {
                ok = false;
            }

        } catch (IllegalArgumentException e) {
            ok = false;
        }
        return ok;
    }

}
