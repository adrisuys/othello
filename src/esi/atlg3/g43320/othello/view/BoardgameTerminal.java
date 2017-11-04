/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.dp.Observable;
import esi.atlg3.g43320.othello.dp.Observer;
import esi.atlg3.g43320.othello.model.Color;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.Game;
import esi.atlg3.g43320.othello.model.OthelloModel;

/**
 * This class represents the command/terminal interface of the game Othello.
 * @author s_u_y_s_a
 */
public class BoardgameTerminal implements Observer {

    private OthelloModel observable;

    /**
     * Creates an instance of this class and register this object as an observer of the parameters observable.
     * @param observable an Observable that is now observed by this object.
     */
    public BoardgameTerminal(Observable observable) {
        if (observable == null) {
            throw new IllegalArgumentException("Nothing to observe");
        }
        this.observable = (OthelloModel) observable;
        this.observable.registerObserver(this);
    }

    /**
     * Display the board. The B represents a black pawn ; the W, a white pawn ; the x, a possible move.
     * @param game the game the players are currently playing.
     * @param color the color of the current player.
     */
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

    /**
     * Display the rules of the game Othello.
     */
    public static void displayRules() {
        System.out.println("Rules : Outflank your opponent by surrounding a vertical, horizontal, or diagonal row of their discs with your discs. \nThen flip the whole row over so that it’s the color of your discs. Take turns making moves and outflanking \n until you can’t make any more moves. At this point, whoever has more discs flipped to their side wins. ");
    }

    /**
     * Display the usage : "how do you play Othello" Guide.
     */
    public static void displayUsage() {
        System.out.println("You can use the following 3 command to play this game");
        System.out.println("show : the checkerboard will be displayed");
        System.out.println("score : the current score will be displayed");
        System.out.println("play i j : a pawn will be put on the case (i,j), i being the row and j the column");
        System.out.println("if such a move is not valid, an error message will appear");
    }

    /**
     * When notified of a play, this object updates itself : if all went to good, it display the updated board, else it displays a error message.
     */
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

    /**
     * When notified that a players wants to see the score, this object updates itself : it displays the score of the 2 players.
     */
    @Override
    public void updateScore() {
        System.out.println("The score of the player BLACK is " + observable.getScorePlayer1());
        System.out.println("The score of the player WHITE is " + observable.getScorePlayer2());
    }

    /**
     * When notified that a players wants to see the board, this object updates iteself : it displays the board.
     */
    @Override
    public void updateShow() {
        displayBoardgame(observable.getGame(), observable.getGame().getCurrentColor());
    }

    /**
     * When notified that the game has been initialized, this object updates itself : it display the name, the rules, the usage and the board of the game.
     */
    @Override
    public void updateInit() {
        System.out.println("OTHELLO");
        System.out.println("-------");
        displayRules();
        displayBoardgame(observable.getGame(), observable.getGame().getCurrentColor());
        displayUsage();
    }

    /**
     * When notified that a pawn can't be put on a given coordinates, this object updates itself : it displays an error message.
     */
    @Override
    public void updateErrorInputCoordinates() {
        System.out.println("You have not entered proper coordinates for the pawn to be put!");
    }

    /**
     * When notified that the player has entered a wrong command, this object
     * updates itself : it displays an error message and reminds of the usage.
     */
    @Override
    public void updateErrorInputCommand() {
        System.out.println("You have not entered proper command");
        displayUsage();
    }
    
    /**
     * Displays the current player.
     * @param game the game the players are currently playing.
     * @return a String "player BLACK" for the player that has the black pawn, "player WHITE" for the other one.
     */
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
