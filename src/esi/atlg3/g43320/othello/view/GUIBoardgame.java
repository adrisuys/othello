/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author s_u_y_s_a
 */
public class GUIBoardgame extends GridPane{
    
    private final static int ROW = 8;
    private final static int COL = 8;
    //private final GridPane board;

    public GUIBoardgame() {
        //board = new GridPane();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                GUISquare square = new GUISquare();
                GridPane.setRowIndex(square, i);
                GridPane.setColumnIndex(square, j);
                getChildren().add(square);
            }
        }
    }

    public void updateBoardgame(OthelloModel othello) {
        for (int i=0; i<ROW; i++) {
            for (int j=0; j<COL; j++) {
                Coordinates coord = new Coordinates(i,j);
                switch (othello.getGame().getBoard().getPawn(coord)) {
                    case 0:
                        getSquareAtCoordinates(i,j).getPawn().setFill(Color.TRANSPARENT);
                        break;
                    case 1:
                        getSquareAtCoordinates(i,j).getPawn().setFill(Color.BLACK);
                        break;
                    case 2:
                        getSquareAtCoordinates(i,j).getPawn().setFill(Color.WHITE);
                        break;
                    default:
                        getSquareAtCoordinates(i,j).setStyle("-fx-background-image: url(img/wall.png)");
                        getSquareAtCoordinates(i,j).getPawn().setFill(Color.TRANSPARENT);
                        break;
                }
            }
        }
    }

    public GUISquare getSquareAtCoordinates(int row, int col){
        GUISquare aSquare = null;
        for (Node sq : getChildren()) {
            if (getRowIndex(sq)==row && getColumnIndex(sq)==col){
                aSquare = (GUISquare) sq;
                break;
            }
        }
        return aSquare;
    }
    
    

}
