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
public enum Directions {
    
    SW (1, -1, "Southwest"),
    SE (1, 1, "Southeast"),
    NE (-1, 1, "Northeast"),
    NW (-1, -1, "Northwest"),
    N (-1, 0, "North"),
    S (1, 0, "South"),
    E (0, 1, "East"),
    W (0, -1, "West");
    
    private final int xShift;
    private final int yShift;
    private final String libelle;
    
    private Directions (int xShift, int yShift, String libelle) {
        this.xShift = xShift;
        this.yShift = yShift;
        this.libelle = libelle;
    }

    public int getxShift() {
        return xShift;
    }

    public int getyShift() {
        return yShift;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
    
    
}
