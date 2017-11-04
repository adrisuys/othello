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
 * This is the facade that dialogues with the player and that asks the other
 * class of the game to act according to what the player wants.
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

    /**
     * Verify if the player has typed a valid coordinates : 2 int (x, y) ,
     * between 0 and 7 (the size of the board). Here we do not take into account
     * if he can put a pawn or not on the case specified by the coordinates.
     *
     * @param tabStr an array of string which represents the command typed by
     * the user.
     * @param game the game the players are currently playing.
     * @return a boolean true if the coordinates typed by the player are valid,
     * false otherwise.
     */
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

    /**
     * It represents a play in which a single player plays a single time. He has
     * the choice to either see the scores, see the board or put a pawn on a
     * case.
     *
     * @param othello the model that take care of the implementation of the
     * game.
     * @param game the game that is being implemented.
     */
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
            } else {
                switch (inputArray[0].toUpperCase()) {
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
                        }
                        break;
                    default:
                        othello.notifyObserversErrorInputCommand();
                        unvalid = true;
                        break;
                }
            }
            if (unvalid) {
                input = keyboard.nextLine();
            }
        }
    }

}
