/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * This class represents a menu bar on which there are 2 menus : one is JOUER
 * and when clicked, it displays the game ; the other is REGLES, it displays
 * the rules of Othello.
 *
 * @author s_u_y_s_a
 */
public class GUIMenu {

    private final Menu game;
    private final Menu rules;
    private final MenuBar menu;

    /**
     * Creates an instance of GUIMenu
     */
    public GUIMenu() {
        Label labGame = new Label("Jouer");
        Label labRules = new Label("Règles");
        game = new Menu();
        rules = new Menu();
        game.setGraphic(labGame);
        rules.setGraphic(labRules);
        menu = new MenuBar();
        menu.getMenus().addAll(game, rules);
        menu.setMinWidth(1000);
    }

    /**
     * Returns the menu JOUER.
     * @return the menu JOUER.
     */
    public Menu getGame() {
        return game;
    }
    
    /**
     * Returns the menu REGLES.
     * @return the menu REGLES.
     */
    public Menu getRules() {
        return rules;
    }
    
    /**
     * Returns the whole menu bar.
     * @return the whole menu bar.
     */
    public MenuBar getMenu() {
        return menu;
    }

}
