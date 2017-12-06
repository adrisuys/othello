/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.OthelloModel;
import static esi.atlg3.g43320.othello.view.fx.FXOthelloView.addTextLimiter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * This class represent the message that is displayed at the beginning of each
 * game. It allows the players to enter their names and to choose the type of
 * game they want to play : human vs computer, human vs human or computer vs
 * computer.
 *
 * @author s_u_y_s_a
 */
public class GUIIntroMsg {

    private final VBox dialog;
    private final RadioButton humVShum;
    private final RadioButton humVScomp;
    private final RadioButton compVScomp;
    private final ToggleGroup typeGame;
    private final TextField name1;
    private final TextField name2;
    private final Button quitButtonType;
    private final Button OKButtonType;

    /**
     * Creates an instance of GUIIntroMsg.
     *
     * @param title the name of the windows that is opened.
     */
    public GUIIntroMsg(String title) {
        dialog = new VBox();
        Label titleLab = new Label(title);
        //titleLab.setStyle();
        Label explainLab = new Label("Entrez les noms des joueurs.");
        dialog.getChildren().add(titleLab);
        dialog.getChildren().add(explainLab);
        // Set the button types.
        quitButtonType = new Button("Quitter");
        quitButtonType.setMinWidth(100);
        OKButtonType = new Button("OK");
        OKButtonType.setMinWidth(100);
        HBox btnFrame = new HBox();
        btnFrame.getChildren().addAll(OKButtonType,quitButtonType);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        name1 = new TextField();
        addTextLimiter(name1, 5);
        grid.add(new Label("Nom du premier joueur (max 5 caractères):"), 0, 0);
        grid.add(name1, 1, 0);

        name2 = new TextField();
        addTextLimiter(name2, 5);
        grid.add(new Label("Nom du deuxième joueur (max 5 caractères):"), 0, 1);
        grid.add(name2, 1, 1);

        compVScomp = new RadioButton("Ordinateur vs Ordinateur");
        humVScomp = new RadioButton("Humain vs Ordinateur");
        humVShum = new RadioButton("Humain vs Humain");
        typeGame = new ToggleGroup();
        compVScomp.setToggleGroup(typeGame);
        humVScomp.setToggleGroup(typeGame);
        humVShum.setToggleGroup(typeGame);
        humVShum.setSelected(true);
        grid.add(new Label("Choisissez un type de partie:"), 0, 2);
        grid.add(humVShum, 1, 2);
        grid.add(humVScomp, 2, 2);
        grid.add(compVScomp, 3, 2);

        dialog.getChildren().addAll(grid);
        dialog.getChildren().addAll(btnFrame);
        
        //dialog.setPrefHeight(750);
        //dialog.setPrefWidth(1000);

    }

    /**
     * Return a Dialog box, a window with all the elements the players need to
     * enter their names and to select the type of game.
     *
     * @return
     */
    public VBox getDialog() {
        return dialog;
    }

    /**
     * A RadioButton representing the type of game : human vs human.
     *
     * @return a RadioButton representing the type of game : human vs human.
     */
    public RadioButton getHumVShum() {
        return humVShum;
    }

    /**
     * A RadioButton representing the type of game : human vs computer.
     *
     * @return a RadioButton representing the type of game : human vs computer.
     */
    public RadioButton getHumVScomp() {
        return humVScomp;
    }

    /**
     * A RadioButton representing the type of game : computer vs computer.
     *
     * @return a RadioButton representing the type of game : computer vs
     * computer.
     */
    public RadioButton getCompVScomp() {
        return compVScomp;
    }

    /**
     * A ToggleGroup containing all the RadioButton represening the types of
     * game.
     *
     * @return a ToggleGroup containing all the RadioButton represening the
     * types of game.
     */
    public ToggleGroup getTypeGame() {
        return typeGame;
    }

    public String getName1() {
        return name1.getText();
    }

    public String getName2() {
        return name2.getText();
    }

    public Button getQuitButtonType() {
        return quitButtonType;
    }

    public Button getOKButtonType() {
        return OKButtonType;
    }
    
    
    

}