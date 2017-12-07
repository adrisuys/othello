/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This class represent a single case of the boardgame.
 *
 * @author s_u_y_s_a
 */
public class GUISquare extends StackPane {
    
    private final Rectangle square;
    private final Circle pawn;

    /**
     * It creates an instance of a GUISquare.
     */
    public GUISquare() {
        super();
        square = new Rectangle(60.0, 60.0, Color.GREEN);
        square.setStroke(Color.BLACK);
        pawn = new Circle(25.0, Color.TRANSPARENT);
        getChildren().addAll(square, pawn);
    }

    /**
     * Returns the background of a case : a square shape.
     *
     * @return the background of a case : a square shape.
     */
    public Rectangle getSquare() {
        return square;
    }

    /**
     * Returns the circle of each case. Its color depends on the pawns put on
     * the board. If there is no pawn, the circle is transparent.
     *
     * @return the circle of each case. Its color depends on the pawns put on
     * the board. If there is no pawn, the circle is transparent.
     */
    public Circle getPawn() {
        return pawn;
    }

    /**
     * Update the case when the mouse is over it. If the player can play on this
     * case, the background of the case becomes green, if not, red.
     *
     * @param validMove the boolean indicating if the player can put a pawn on
     * the case.
     */
    public void updateSquare(boolean validMove) {
        if (validMove) {
            square.setFill(Color.GREENYELLOW);
        } else {
            square.setFill(Color.LIGHTCORAL);
        }
    }
    
    public void animationPawnTurn(){
        Timeline tl = new Timeline();
        KeyFrame kfFront = new KeyFrame(Duration.millis(100), e->{
            if (pawn.getFill()==Color.BLACK) {
                pawn.setFill(Color.WHITE);
            } else {
                pawn.setFill(Color.BLACK);
            }
        }, new KeyValue(pawn.scaleXProperty(), 0));
        KeyFrame kfBack = new KeyFrame(Duration.millis(100), new KeyValue(pawn.scaleXProperty(),1));
        tl.getKeyFrames().addAll(kfFront,kfBack);
    }
    
    

}
