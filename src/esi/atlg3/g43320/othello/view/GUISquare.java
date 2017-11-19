/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author s_u_y_s_a
 */
public class GUISquare extends StackPane {
    
    private final Rectangle square;
    private final Circle pawn;

    public GUISquare() {
        square = new Rectangle(60.0, 60.0, Color.GREEN);
        square.setStroke(Color.BLACK);
        pawn = new Circle(25.0, Color.TRANSPARENT);
        getChildren().addAll(square, pawn);
    }

    public Rectangle getSquare() {
        return square;
    }

    public Circle getPawn() {
        return pawn;
    }

    public void updateSquare(boolean validMove){
        if (validMove){
            square.setFill(Color.GREENYELLOW);
        } else {
            square.setFill(Color.LIGHTCORAL);
        }
    }

    
}
