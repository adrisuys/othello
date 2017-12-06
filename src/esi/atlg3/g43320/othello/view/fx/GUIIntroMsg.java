/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.OthelloModel;
import static esi.atlg3.g43320.othello.view.fx.FXOthelloView.addTextLimiter;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
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

    private Dialog<Pair<String, String>> dialog;
    private RadioButton humVShum;
    private RadioButton humVScomp;
    private RadioButton compVScomp;
    private ToggleGroup typeGame;

    /**
     * Creates an instance of GUIIntroMsg.
     *
     * @param othello the game being played.
     * @param title the name of the windows that is opened.
     */
    public GUIIntroMsg(OthelloModel othello, String title) {
        dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Entrez les noms des joueurs.");
        // Set the button types.
        ButtonType OKButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OKButtonType);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name1 = new TextField();
        addTextLimiter(name1, 5);
        grid.add(new Label("Nom du premier joueur (max 5 caractères):"), 0, 0);
        grid.add(name1, 1, 0);

        TextField name2 = new TextField();
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

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OKButtonType) {
                return new Pair<>(name1.getText(), name2.getText());
            }
            return null;
        });
    }

    /**
     * Return a Dialog box, a window with all the elements the players need to
     * enter their names and to select the type of game.
     *
     * @return
     */
    public Dialog<Pair<String, String>> getDialog() {
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

}
