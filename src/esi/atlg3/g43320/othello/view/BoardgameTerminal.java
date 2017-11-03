/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.dp.Observable;
import esi.atlg3.g43320.othello.dp.Observer;
import esi.atlg3.g43320.othello.model.Board;
import esi.atlg3.g43320.othello.model.Color;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.Game;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.Scanner;

/**
 *
 * @author s_u_y_s_a
 */
public class BoardgameTerminal implements Observer {

    private OthelloModel observable;

    public BoardgameTerminal(Observable observable) {
        if (observable == null) {
            throw new IllegalArgumentException("Nothing to observe");
        }
        this.observable = (OthelloModel) observable;
        this.observable.registerObserver(this);
    }

    public static void displayBoardgame(Game game, Color color) {
        int cpt = 0;
        game.updatePossibleMove(color);
        String aString = "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |" + "\n";
        aString = aString + "-------------------------------------" + "\n";
        for (int i = 0; i < game.getBoard().getROW(); i++) {
            aString = aString + "| " + cpt + " ";
            for (int j = 0; j < game.getBoard().getCOL(); j++) {
                switch (game.getBoard().getCheckerboard()[i][j]) {
                    case 0:
                        if (game.getPossibleMove().contains(new Coordinates(i,j))){
                            aString = aString + "| x ";
                        } else {
                            aString = aString + "| . ";
                        }   break;
                    case 1:
                        aString = aString + "| B ";
                        break;
                    default:
                        aString = aString + "| W ";
                        break;
                }
            }
            aString = aString + "|";
            aString = aString + ("\n" + "-------------------------------------" + "\n");
            cpt++;
        }
        System.out.println(aString);
    }

    public static void displayRules() {
        System.out.println("Rules : Outflank your opponent by surrounding a vertical, horizontal, or diagonal row of their discs with your discs. \nThen flip the whole row over so that it’s the color of your discs. Take turns making moves and outflanking \n until you can’t make any more moves. At this point, whoever has more discs flipped to their side wins. ");
    }

    public static void displayUsage() {
        System.out.println("You can use the following 3 command to play this game");
        System.out.println("show : the checkerboard will be displayed");
        System.out.println("score : the current score will be displayed");
        System.out.println("play i j : a pawn will be put on the case (i,j), i being the row and j the column");
        System.out.println("if such a move is not valid, an error message will appear");
    }

    @Override
    public void updatePlay() {
        if (observable.isTurnPassed() == true) {
            System.out.println("Your turn has been passed, it is now the "+displayCurrentPlayer(observable.getGame())+"'s turn!");
        } else if (observable.isValidPlay() == false){
            System.out.println("You can't put a pawn on that coordinate! Try again!");
        } else {
            displayBoardgame(observable.getGame(), observable.getGame().getCurrentColor());
            System.out.println("It is now the turn of "+ displayCurrentPlayer(observable.getGame()) +".");
        }
        
    }

    @Override
    public void updateScore() {
        System.out.println("The score of the player BLACK is " + observable.getScorePlayer1());
        System.out.println("The score of the player WHITE is " + observable.getScorePlayer2());
    }

    @Override
    public void updateShow() {
        displayBoardgame(observable.getGame(), observable.getGame().getCurrentColor());
    }

    @Override
    public void updateInit() {
        System.out.println("OTHELLO");
        System.out.println("-------");
        displayRules();
        displayBoardgame(observable.getGame(), observable.getGame().getCurrentColor());
        displayUsage();
    }

    @Override
    public void updateErrorInputCoordinates() {
        System.out.println("You have not entered proper coordinates for the pawn to be put!");
    }

    @Override
    public void updateErrorInputCommand() {
        System.out.println("You have not entered proper command");
        displayUsage();
    }
    
    public String displayCurrentPlayer(Game game) {
        String s;
        if(game.getCurrentColor() == Color.BLACK){
            s = "player BLACK";
        } else {
            s = "player WHITE";
        }
        return s;
    }
}
