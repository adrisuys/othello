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
 *
 * @author s_u_y_s_a
 */
public class GUIMenu {
    
    private final Menu game;
    private final Menu rules;
    private final MenuBar menu;

    public GUIMenu() {
        Label labGame = new Label("Jouer");
        Label labRules = new Label("Règles");
        game = new Menu();
        rules = new Menu();
        game.setGraphic(labGame);
        rules.setGraphic(labRules);
        menu = new MenuBar();
        menu.getMenus().addAll(game,rules);
        menu.setMinWidth(1000);
    }

    public Menu getGame() {
        return game;
    }

    public Menu getRules() {
        return rules;
    }
    
    public MenuBar getMenu(){
        return menu;
    }
    
    
    
}
