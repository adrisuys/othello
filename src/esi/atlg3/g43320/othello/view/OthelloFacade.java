/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.Game;
import esi.atlg3.g43320.othello.model.OthelloModel;
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
        BoardgameTerminal view = new BoardgameTerminal(othello);

        othello.init(game);

        while (!game.isOver()) {
            playATurn(othello, game);
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

    public static void playATurn(OthelloModel othello, Game game) {
        boolean unvalid = true;
        boolean validPlay = false;
        boolean turnPassed = false;
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        while (unvalid) {
            unvalid = false;
            String[] inputArray = input.split("\\s+");
            if (null == inputArray[0].toUpperCase()) {
                othello.notifyObserversErrorInputCommand();
                unvalid = true;
            } else switch (inputArray[0].toUpperCase()) {
                case "SHOW":
                    othello.show(game);
                    break;
                case "SCORE":
                    othello.score(game);
                    break;
                case "PLAY":
                    if (verifyArgsCoordinates(inputArray, game)) {
                        int x = Integer.parseInt(inputArray[1]);
                        int y = Integer.parseInt(inputArray[2]);
                        othello.play(game, new Coordinates(x, y), validPlay, turnPassed);
                        unvalid = !validPlay;
                    } else {
                        othello.notifyObserversErrorInputCoordinates();
                        unvalid = true;
                    }   break;
                default:
                    othello.notifyObserversErrorInputCommand();
                    unvalid = true;
                    break;
            }
            if (unvalid){
                input = keyboard.nextLine();
            }
        }
    }

}
