/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 * This class present all the direction in which we can check the board.
 * 
 * @author s_u_y_s_a
 */
public enum Directions {
    
    /**
     * The soutwest direction.
     */
    SW (1, -1, "Southwest"),

    /**
     * The southeast direction.
     */
    SE (1, 1, "Southeast"),

    /**
     * The northeast direction.
     */
    NE (-1, 1, "Northeast"),

    /**
     * The northwest direction.
     */
    NW (-1, -1, "Northwest"),

    /**
     * The north direction.
     */
    N (-1, 0, "North"),

    /**
     * The south direction.
     */
    S (1, 0, "South"),

    /**
     * The east direction.
     */
    E (0, 1, "East"),

    /**
     * The west direction.
     */
    W (0, -1, "West");
    
    private final int xShift;
    private final int yShift;
    private final String libelle;
    
    private Directions (int xShift, int yShift, String libelle) {
        this.xShift = xShift;
        this.yShift = yShift;
        this.libelle = libelle;
    }

    /**
     * Return the shift of rows according to the direction.
     * @return the shift of rows according to the direction.
     */
    public int getxShift() {
        return xShift;
    }

    /**
     * Return the shift of columns according to the direction.
     * @return the shift of columns according to the direction.
     */
    public int getyShift() {
        return yShift;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
    
    
}
