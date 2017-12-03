/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.console;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.GameException;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the facade that dialogues with the player and that asks the other
 * class of the game to act according to what the player wants.
 *
 * @author s_u_y_s_a
 */
public class TerminalOthelloUserInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OthelloModel othello = new OthelloModel();
        TerminalOthelloView view = new TerminalOthelloView(othello);

        try {
            othello.init("");
        } catch (GameException ex) {
            Logger.getLogger(TerminalOthelloUserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (!othello.isOver()) {
            othello.turnPassed();
            if (!othello.isTurnPassed()) {
                playATurn(othello);
            }
        }

        othello.endOfGame();
    }

    private static boolean verifyArgsCoordinates(String[] tabStr, OthelloModel othello) {
        boolean ok = true;
        try {
            int row = Integer.parseInt(tabStr[1]);
            int col = Integer.parseInt(tabStr[2]);
            if (row < 0 || row >= othello.getROW()) {
                ok = false;
            }
            if (col < 0 || col >= othello.getCOL()) {
                ok = false;
            }

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            ok = false;
        }
        return ok;
    }

    private static void playATurn(OthelloModel othello) {
        boolean unvalid = true;
        Scanner keyboard = new Scanner(System.in, "UTF-8");
        String input = keyboard.nextLine();
        while (unvalid) {
            unvalid = false;
            String[] inputArray = input.split("\\s+");
            if (null == inputArray[0]) {
                othello.throwCommandsError();
                unvalid = true;
            } else {
                switch (inputArray[0].toUpperCase(Locale.ENGLISH)) {
                    case "SHOW":
                        othello.show();
                        break;
                    case "SCORE":
                        othello.score();
                        break;
                    case "PLAY":
                        if (verifyArgsCoordinates(inputArray, othello)) {
                            int x = Integer.parseInt(inputArray[1]);
                            int y = Integer.parseInt(inputArray[2]);
                            try {
                                othello.play(new Coordinates(x, y), "");
                            } catch (GameException ex) {
                                Logger.getLogger(TerminalOthelloUserInterface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            unvalid = !othello.isValidPlay();
                        } else {
                            othello.throwCoordinatesError();
                            unvalid = true;
                        }
                        break;
                    default:
                        othello.throwCommandsError();
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
