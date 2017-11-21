/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.console;

import esi.atlg3.g43320.othello.dp.Observable;
import esi.atlg3.g43320.othello.dp.Observer;
import esi.atlg3.g43320.othello.model.ColorPawn;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.Game;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.Objects;

/**
 * This class represents the command/terminal interface of the game Othello.
 *
 * @author s_u_y_s_a
 */
public class TerminalOthelloView implements Observer {

    private OthelloModel observable;

    /**
     * Creates an instance of this class and register this object as an observer
     * of the parameters observable.
     *
     * @param observable an Observable that is now observed by this object.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public TerminalOthelloView(Observable observable) {
        if (observable == null) {
            throw new IllegalArgumentException("Nothing to observe");
        }
        this.observable = (OthelloModel) observable;
        this.observable.registerObserver(this);

    }

    private void displayBoardgame(Game game, ColorPawn color) {
        int cpt = 0;
        StringBuilder buf = new StringBuilder();
        buf.append("-------------------------------------");
        buf.append("\n");
        buf.append("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        buf.append("\n");
        buf.append("-------------------------------------");
        buf.append("\n");
        for (int i = 0; i < observable.getBoard().getROW(); i++) {
            buf.append("| ");
            buf.append(cpt);
            buf.append(" ");
            for (int j = 0; j < observable.getBoard().getCOL(); j++) {
                switch (observable.getBoard().getCheckerboard()[i][j]) {
                    case 0:
                        if (observable.getPossibleMove().contains(new Coordinates(i, j))) {
                            buf.append("| ");
                            buf.append("\u001B[31m");
                            buf.append("x ");
                            buf.append("\u001B[0m");
                        } else {
                            buf.append("| . ");
                        }
                        break;
                    case 1:
                        buf.append("| B ");
                        break;
                    default:
                        buf.append("| W ");
                }
            }
            buf.append("|");
            buf.append("\n");
            buf.append("-------------------------------------");
            buf.append("\n");
            cpt++;
        }
        String s = buf.toString();
        System.out.println(s);
    }

    private void displayRules() {
        System.out.println("Rules : Outflank your opponent by surrounding a vertical, horizontal, or diagonal row of their discs with your discs. \nThen flip the whole row over so that it’s the color of your discs. Take turns making moves and outflanking \nuntil you can’t make any more moves. At this point, whoever has more discs flipped to their side wins. ");
        System.out.println("The BLACK player always starts.");
        System.out.println("The red 'x' represents the cases where you can put a pawn.");
    }

    private void displayUsage() {
        System.out.println("You can use the following 3 command to play this game");
        System.out.println("- 'show' : the checkerboard will be displayed");
        System.out.println("- 'score' : the current score will be displayed");
        System.out.println("- 'play i j' : a pawn will be put on the case (i,j), i being the row and j the column");
        System.out.println("if such a move is not valid, an error message will appear");
    }

    private void updatePlay() {
        if (observable.isUpdatePlay()) {
            if (!observable.isValidPlay()) {
                System.out.println("You can't put a pawn on that coordinate! Try again!");
            } else {
                displayBoardgame(observable.getGame(), observable.getCurrentColor());
                System.out.println("It is now the turn of " + displayCurrentPlayer() + ".");
            }
        }

    }

    private void updateScore() {
        if (observable.isUpdateScore()) {
            System.out.println("The score of the player BLACK is " + observable.getScorePlayer1());
            System.out.println("The score of the player WHITE is " + observable.getScorePlayer2());
        }

    }

    private void updateShow() {
        if (observable.isUpdateShow()) {
            displayBoardgame(observable.getGame(), observable.getCurrentColor());
        }

    }

    private void updateInit() {
        if (observable.isUpdateInit()) {
            System.out.println("OTHELLO");
            System.out.println("-------");
            displayRules();
            displayBoardgame(observable.getGame(), observable.getCurrentColor());
            displayUsage();
        }
    }

    private void updateErrorInputCoordinates() {
        if (observable.isUpdateCoordinatesError()) {
            System.out.println("You have not entered proper coordinates for the pawn to be put!");
        }
    }

    private void updateErrorInputCommand() {
        if (observable.isUpdateCommandsError()) {
            System.out.println("You have not entered proper command");
            displayUsage();
        }

    }

    private String displayCurrentPlayer() {
        String s;
        if (observable.getCurrentColor() == ColorPawn.BLACK) {
            s = "player BLACK";
        } else {
            s = "player WHITE";
        }
        return s;
    }

    private void updateEndOfGame() {
        if (observable.isUpdateEndOfGame()) {
            System.out.println("The score of the player BLACK is " + observable.getScorePlayer1());
            System.out.println("The score of the player WHITE is " + observable.getScorePlayer2());
            if (observable.getScorePlayer1() > observable.getScorePlayer2()) {
                System.out.println("The player BLACK is the winner!");
            } else if (observable.getScorePlayer1() == observable.getScorePlayer2()) {
                System.out.println("This is a draw!");
            } else {
                System.out.println("The player WHITE is the winner!");
            }
        }

    }

    private void updateTurnPassed() {
        if (observable.isUpdateTurnPassed()) {
            if (observable.isTurnPassed()) {
                System.out.println("Your turn has been passed, it is now the " + displayCurrentPlayer() + "'s turn!");
                displayBoardgame(observable.getGame(), observable.getCurrentColor());
            }
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.observable);
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
        final TerminalOthelloView other = (TerminalOthelloView) obj;
        return Objects.equals(this.observable, other.observable);
    }

    @Override
    public void update() {
        updateEndOfGame();
        updateInit();
        updatePlay();
        updateScore();
        updateShow();
        updateTurnPassed();
        updateErrorInputCommand();
        updateErrorInputCoordinates();
    }

}
