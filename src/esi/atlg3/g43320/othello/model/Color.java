/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 *
 * @author s_u_y_s_a
 */
public enum Color {
    BLACK (1),
    WHITE (2);
    
    private int value;

    private Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    

    @Override
    public String toString() {
        if (this == Color.BLACK){
            return "B";
        } else {
            return "W";
        }
    }
    
    
    
    
}
