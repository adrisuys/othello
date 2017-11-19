/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author s_u_y_s_a
 */
public class Move {
    
    private final SimpleStringProperty name;
    private final SimpleStringProperty action;
    private final SimpleStringProperty pos;
    private final SimpleStringProperty taken;
 
    public Move(String name, String action, String pos, String taken) {
        this.name = new SimpleStringProperty(name);
        this.action = new SimpleStringProperty(action);
        this.pos = new SimpleStringProperty(pos);
        this.taken = new SimpleStringProperty(taken);
        
    }
 
    public String getName() {
        return name.get();
    }
    public void setName(String s) {
        name.set(s);
    }
    
    public String getAction() {
        return action.get();
    }
    public void setAction(String s) {
        action.set(s);
    }
    
    public String getPos() {
        return pos.get();
    }
    public void setPos(String s) {
        pos.set(s);
    }
    
    public String getTaken() {
        return taken.get();
    }
    public void setTaken(String s) {
        taken.set(s);
    }
        
}
