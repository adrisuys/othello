/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
     */
    public GUIIntroMsg() {
        dialog = new VBox();
        String imgOthello = getClass().getResource("img/othello.jpg").toExternalForm();
        dialog.setStyle("-fx-background-image: url('" + imgOthello + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");
        dialog.setPadding(new Insets(250, 10, 0, 10));

        VBox text = new VBox();
        text.setStyle("-fx-background-color: #99e699;");
        text.setMaxWidth(980);

        Label explainLab = new Label("Entrez les noms des joueurs.");
        VBox.setMargin(explainLab, new Insets(5, 0, 0, 10));
        text.getChildren().addAll(explainLab);

        quitButtonType = createsButton("Quitter");
        OKButtonType = createsButton("OK");
        
        HBox btnFrame = new HBox();
        btnFrame.getChildren().addAll(OKButtonType, quitButtonType);
        VBox.setMargin(btnFrame, new Insets(100, 0, 0, 270));
        HBox.setMargin(OKButtonType, new Insets(0, 50, 0, 0));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        name1 = createsTF(5,"Nom du joueur 1");
        grid.add(new Label("Nom du premier joueur (max 5 caractères):"), 0, 0);
        grid.add(name1, 1, 0);

        name2 = createsTF(5,"Nom du joueur 2");
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

        text.getChildren().addAll(grid);
        dialog.getChildren().addAll(text, btnFrame);
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

    /**
     * Returns the name of the first player as enterd by the player (max 5
     * characters).
     *
     * @return the name of the first player as enterd by the player (max 5
     * characters).
     */
    public String getName1() {
        return name1.getText();
    }

    /**
     * Returns the name of the second player as enterd by the player (max 5
     * characters).
     *
     * @return the name of the second player as enterd by the player (max 5
     * characters).
     */
    public String getName2() {
        return name2.getText();
    }
    
    /**
     * Returns the button QUIT of the scene.
     * @return the button QUIT of the scene.
     */
    public Button getQuitButtonType() {
        return quitButtonType;
    }
    
    /**
     * Returns the button OK of the scene.
     * @return the button OK of the scene.
     */
    public Button getOKButtonType() {
        return OKButtonType;
    }
    
    /**
     * Clean the two textfield of the scene.
     */
    public void cleanTxtField() {
        name1.clear();
        name2.clear();
    }
    
    /**
     * Limits the number of character a player can enter in a TextField.
     *
     * @param tf the TextField.
     * @param maxLength the maximum number of character a player can enter in
     * the TextField.
     */
    private void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    
    private Button createsButton(String str){
        Button btn = new Button(str);
        btn.setMinHeight(100);
        btn.setMinWidth(200);
        btn.setStyle("-fx-border-color: green; -fx-border-width: 3;");
        return btn;
    }
    
    private TextField createsTF(int maxNbChar, String promptTxt){
        TextField tf = new TextField();
        tf.setPromptText(promptTxt);
        addTextLimiter(tf,maxNbChar);
        return tf;
    }

}
