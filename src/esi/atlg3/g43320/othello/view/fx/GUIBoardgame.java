/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.GameModel;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

/**
 * This class represents the graphical interface of the boardgame.
 *
 * @author s_u_y_s_a
 */
public class GUIBoardgame {

    private final GridPane board;
    private final static int ROW = 8;
    private final static int COL = 8;

    /**
     * Creates an instance of GUIBoardgame.
     */
    public GUIBoardgame() {
        board = new GridPane();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                GUISquare square = new GUISquare();
                GridPane.setRowIndex(square, i);
                GridPane.setColumnIndex(square, j);
                board.getChildren().add(square);
            }
        }
    }
    
    /**
     * Creates an instance of GUIBoardgame that takes the attributes of another GUIBoardgame.
     * @param boardgame the GUIBoardgame the new instance takes its value from.
     */
    public GUIBoardgame(GUIBoardgame boardgame){
        this.board = boardgame.board;
    }

    /**
     * Display the boardgame at a given time.
     *
     * @param othello the model of the game.
     */
    public void updateBoardgame(GameModel othello) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Coordinates coord = new Coordinates(i, j);
                switch (othello.getPawn(coord)) {
                    case 0:
                        getSquareAtCoordinates(i, j).getPawn().setFill(Color.TRANSPARENT);
                        break;
                    case 1:
                        getSquareAtCoordinates(i, j).getPawn().setFill(Color.BLACK);
                        getSquareAtCoordinates(i, j).setDisable(true);
                        break;
                    case 2:
                        getSquareAtCoordinates(i, j).getPawn().setFill(Color.WHITE);
                        getSquareAtCoordinates(i, j).setDisable(true);
                        break;
                    default:
                        Image image = new Image(getClass().getResourceAsStream("./img/wall.png"));
                        getSquareAtCoordinates(i, j).getPawn().setFill(new ImagePattern(image));
                        getSquareAtCoordinates(i, j).setDisable(true);
                        break;
                }
            }
        }
    }

    /**
     * Get the Node at a certain coordinate in the GridPane (the specific case
     * of the board)
     *
     * @param row the row of the coordinate
     * @param col the column of the coordinate
     * @return the Node at that coordinate if it exists.
     */
    public GUISquare getSquareAtCoordinates(int row, int col) {
        GUISquare aSquare = null;
        for (Node sq : board.getChildren()) {
            if (GridPane.getRowIndex(sq) == row && GridPane.getColumnIndex(sq) == col) {
                aSquare = (GUISquare) sq;
                break;
            }
        }
        return aSquare;
    }

    /**
     * Returns the GridPane representing the boardgame.
     *
     * @return the GridPane representing the boardgame.
     */
    public GridPane getBoard() {
        return board;
    }

    /**
     * Allows all the cases of the board to be responsive to the different
     * events.
     */
    public void setDisableOnFalse() {
        board.getChildren().forEach((n) -> {
            n.setDisable(false);
        });
    }
    
    /**
     * Allows all the cases of the board to be responsive to the different
     * events.
     */
    public void setDisableOnTrue() {
        board.getChildren().forEach((n) -> {
            n.setDisable(true);
        });
    }
}
