/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class displays the title "Othello" and the rules of the game.
 * @author s_u_y_s_a
 */
public class GUIRules {

    private final VBox frame;

    /**
     * Creates an instance of GUIRules.
     */
    public GUIRules() {
        frame = new VBox();
        Label title = new Label("Othello");
        title.setFont(Font.font("Verdana", 70));
        title.setTextAlignment(TextAlignment.JUSTIFY);
        Text rules = new Text();
        rules.setText("Othello se joue à 2, sur un plateau unicolore de 64 cases (8 sur 8), avec des pions bicolores, noirs d'un côté \n"
                + "et blancs de l'autre. Le but du jeu est d'avoir plus de pions de sa couleur que l'adversaire à la fin de  \n"
                + "la partie, celle-ci s'achevant lorsque aucun des deux joueurs ne peut plus jouer de coup légal, généralement \n"
                + "lorsque les 64 cases sont occupées.\n\n"
                + "Afin de poser un pion de votre couleur sur le plateau, il suffit de cliquer sur la case désirée. Si vous \n"
                + "pouvez jouer sur cette case, elle s'illuminera en vert clair. Si non, elle deviendra rouge. Vous pouvez \n"
                + "également placer des murs afin de bloquer vos adversaires, pour ce faire, il suffit de faire un clic gauche \n"
                + "sur n'importe quelle case de l'othellier.\n\n"
                + "A votre tour, vous pouvez décider de soit jouer un pion, soit de placer un mur. Si vous ne savez pas jouer, \n"
                + "vous aurez le choix entre passer votre tour ou placer un mur sur le plateau. \n \n"
                + "Que le meilleur gagne!");
        rules.setFont(Font.font("Verdana", 15));
        rules.setTextAlignment(TextAlignment.JUSTIFY);
        frame.getChildren().addAll(title,rules);
        VBox.setMargin(title, new Insets(10,10,10,10));
        VBox.setMargin(rules, new Insets(10,10,10,10));
    }

    /**
     * Returns the VBox representing on which the rules are displayed.
     * @return the VBox representing on which the rules are displayed.
     */
    public VBox getFrame() {
        return frame;
    }
    
    

}
