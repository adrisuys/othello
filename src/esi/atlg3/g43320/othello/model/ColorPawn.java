/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 * This class represents the color of the pawns (and a fortiori of the player),
 * it can either be black or white.
 *
 * @author s_u_y_s_a
 */
public enum ColorPawn {

    /**
     * The color black
     */
    BLACK(1, "B"),
    /**
     * The color white
     */
    WHITE(2, "W");

    private final int value;
    private final String libelle;

    private ColorPawn(int value, String libelle) {
        this.value = value;
        this.libelle = libelle;
    }

    /**
     * Returns the value of a color
     *
     * @return an int which is 1 for black and 2 for white.
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
