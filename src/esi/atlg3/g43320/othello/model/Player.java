/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import java.util.Objects;

/**
 * The class represents a player and a player is defined by the color of its
 * pawns.
 *
 * @author s_u_y_s_a
 */
public class Player {

    private Color color;

    /**
     * Creates an instance of a player by giving it a specified color.
     *
     * @param color
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of the player.
     *
     * @return the color of the player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Change the color of the player.
     *
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (getColor() == Color.BLACK) {
            return "Player Black";
        } else {
            return "Player White";
        }
    }

}
