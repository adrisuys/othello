/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author s_u_y_s_a
 */
public class Game {

    private List<Player> players;
    private Board board;
    private Player currentPlayer;
    private List<List<Coordinates>> pawnsToBeTurned;
    private List<Coordinates> pawnsAtE;
    private List<Coordinates> pawnsAtS;
    private List<Coordinates> pawnsAtW;
    private List<Coordinates> pawnsAtN;
    private List<Coordinates> pawnsAtNE;
    private List<Coordinates> pawnsAtSE;
    private List<Coordinates> pawnsAtNW;
    private List<Coordinates> pawnsAtSW;
    private List<Coordinates> possibleMove;

    public Game() {
        players = new ArrayList<>();
        players.add(new Player(Color.BLACK));
        players.add(new Player(Color.WHITE));

        board = new Board();

        currentPlayer = players.get(0);
        pawnsToBeTurned = new ArrayList<>();
        pawnsAtE = new ArrayList<>();
        pawnsAtN = new ArrayList<>();
        pawnsAtW = new ArrayList<>();
        pawnsAtS = new ArrayList<>();
        pawnsAtSW = new ArrayList<>();
        pawnsAtNE = new ArrayList<>();
        pawnsAtNW = new ArrayList<>();
        pawnsAtSE = new ArrayList<>();
        possibleMove = new ArrayList<>();
    }
    
    public Game(Game game){
        this.players = game.players;
        this.board = game.board;
        this.currentPlayer = game.currentPlayer;
        this.pawnsToBeTurned = game.pawnsToBeTurned;
        this.pawnsAtE = game.pawnsAtE;
        this.pawnsAtN = game.pawnsAtN;
        this.pawnsAtW = game.pawnsAtW;
        this.pawnsAtS = game.pawnsAtS;
        this.pawnsAtSE = game.pawnsAtSE;
        this.pawnsAtSW = game.pawnsAtSW;
        this.pawnsAtNE = game.pawnsAtNE;
        this.pawnsAtNW = game.pawnsAtNW;
        this.possibleMove = game.possibleMove;
    }
    
    

    public void changePlayer() {
        if (currentPlayer.equals(players.get(0))) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.get(0);
        }
    }

    public Board getBoard() {
        return new Board(board);
    }

    public Color getCurrentColor() {
        return currentPlayer.getColor();
    }

    public Player getCurrentPlayer() {
        return new Player(getCurrentColor());
    }

    public boolean isTurnPassed(Player player) {
        return (nbPossibleMove(player.getColor()) == 0);
    }

    public boolean putPawn(Coordinates aCoordinate, Color color) {
        boolean ok;
        try {
            if (isMoveValid(aCoordinate, color)) {
                board.putPawn(aCoordinate, color);
                ok = true;
            } else {
                ok = false;
            }
        } catch (IllegalArgumentException e) {
            ok = false;
        }
        return ok;

    }
    
    public void updatePossibleMove(Color color){
        possibleMove.clear();
        for (int i = 0; i < board.getROW(); i++) {
            for (int j = 0; j < board.getCOL(); j++) {
                if (isMoveValid(new Coordinates(i, j), color)) {
                    possibleMove.add(new Coordinates(i, j));
                }
            }
        }
    }

    public int nbPossibleMove(Color color) {
        updatePossibleMove(color);
        return possibleMove.size();
    }

    public boolean isMoveValid(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        try {
            if (isMoveValidEast(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidWest(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNorth(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSouth(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSE(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidSW(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNE(aCoordinate, color)) {
                valid = true;
            }
            if (isMoveValidNW(aCoordinate, color)) {
                valid = true;
            }
        } catch (IllegalArgumentException e) {
            valid = false;
        }
        return valid;
    }

    public boolean isMoveValidEast(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int y = aCoordinate.getY() + 1;
        int x = aCoordinate.getX();
        while (y < board.getCOL() && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtE.add(new Coordinates(x, y));
            y++;
        }
        if (y < board.getCOL() && y > aCoordinate.getY() + 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtE.clear();
        }
        pawnsToBeTurned.add(pawnsAtE);
        return valid;
    }

    public boolean isMoveValidWest(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int y = aCoordinate.getY() - 1;
        int x = aCoordinate.getX();
        while (y >= 0 && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtW.add(new Coordinates(x, y));
            y--;
        }
        if (y >= 0 && y < aCoordinate.getY() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtW.clear();
        }
        pawnsToBeTurned.add(pawnsAtW);
        return valid;
    }

    public boolean isMoveValidNorth(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY();
        while (x >= 0 && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtN.add(new Coordinates(x, y));
            x--;
        }
        if (x >= 0 && x < aCoordinate.getX() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtN.clear();
        }
        pawnsToBeTurned.add(pawnsAtN);
        return valid;
    }

    public boolean isMoveValidSouth(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY();
        while (x < board.getROW() && board.getCheckerboard()[x][y] != 0 && board.getCheckerboard()[x][y] != color.getValue()) {
            pawnsAtS.add(new Coordinates(x, y));
            x++;
        }
        if (x < board.getROW() && x > aCoordinate.getX() + 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtS.clear();
        }
        pawnsToBeTurned.add(pawnsAtS);
        return valid;
    }

    public boolean isMoveValidSE(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY() + 1;
        while ((x < board.getROW() && y < board.getCOL())
                && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            pawnsAtSE.add(new Coordinates(x, y));
            x++;
            y++;
        }
        if ((x < board.getROW() && y < board.getCOL())
                && (x > aCoordinate.getX() + 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtSE.clear();
        }
        pawnsToBeTurned.add(pawnsAtSE);
        return valid;
    }

    public boolean isMoveValidSW(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() + 1;
        int y = aCoordinate.getY() - 1;
        while ((x < board.getROW() && y >= 0)
                && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            x++;
            y--;
            pawnsAtSW.add(new Coordinates(x, y));
        }
        if (x < board.getROW() && y >= 0 && (x > aCoordinate.getX() + 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtSW.clear();
        }
        pawnsToBeTurned.add(pawnsAtSW);
        return valid;
    }

    public boolean isMoveValidNE(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY() + 1;
        while ((x >= 0 && y < board.getCOL()
                && board.getCheckerboard()[x][y] != 0
                && (board.getCheckerboard()[x][y] != color.getValue()))) {
            x--;
            y++;
            pawnsAtNE.add(new Coordinates(x, y));
        }
        if (x >= 0 && y < board.getCOL() && (x < aCoordinate.getX() - 1)
                && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtNE.clear();
        }
        pawnsToBeTurned.add(pawnsAtNE);
        return valid;
    }

    public boolean isMoveValidNW(Coordinates aCoordinate, Color color) {
        boolean valid = false;
        if (aCoordinate == null) {
            throw new IllegalArgumentException("The coordinate is not valid!");
        }
        if (!board.isFree(aCoordinate)){
            throw new IllegalArgumentException("The coordinate is not free");
        }
        int x = aCoordinate.getX() - 1;
        int y = aCoordinate.getY() - 1;
        while ((x >= 0 && y >= 0) && (board.getCheckerboard()[x][y] != 0)
                && (board.getCheckerboard()[x][y] != color.getValue())) {
            x--;
            y--;
            pawnsAtNW.add(new Coordinates(x, y));
        }
        if (x >= 0 && y >= 0 && x < aCoordinate.getX() - 1 && board.getCheckerboard()[x][y] == color.getValue()) {
            valid = true;
        } else {
            pawnsAtNW.clear();
        }
        pawnsToBeTurned.add(pawnsAtNW);
        return valid;
    }

    public void changeColorPawn(Board board) {
        for (int i = 0; i < pawnsToBeTurned.size(); i++) {
            for (int j = 0; j < pawnsToBeTurned.get(i).size(); j++) {
                board.getCheckerboard()[pawnsToBeTurned.get(i).get(j).getX()][pawnsToBeTurned.get(i).get(j).getY()] = getCurrentColor().getValue();
            }
        }
    }

    public List<List<Coordinates>> getPawnsToBeTurned() {
        return Collections.unmodifiableList(pawnsToBeTurned);
        //return pawnsToBeTurned;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
        //return players;
    }

    public List<Coordinates> getPossibleMove() {
        return Collections.unmodifiableList(possibleMove);
        //return possibleMove;
    }
    
    public boolean isOver() {
//        boolean isOver;
//        if (isTurnPassed(currentPlayer)){
//            changePlayer();
//            isOver = isTurnPassed(currentPlayer);
//        } else {
//            isOver = false;
//        }
//        changePlayer();
//        return isOver;
        return isTurnPassed(players.get(0)) && isTurnPassed(players.get(1));
    }
    
    

}
