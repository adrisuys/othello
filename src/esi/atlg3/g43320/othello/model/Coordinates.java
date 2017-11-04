/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 * This class represents the coordinates of the different cases of the
 * checkerboard.
 *
 * @author s_u_y_s_a
 */
public class Coordinates {

    private int x;
    private int y;

    /**
     * Creates an instance of a Coordinates.
     *
     * @param x the row of the case.
     * @param y the column of the case.
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the row the case is at.
     *
     * @return the row the case is at.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the column the case is at.
     *
     * @return the column the case is at.
     */
    public int getY() {
        return y;
    }

    /**
     * Change the row value of the coordinate.
     *
     * @param x the new row value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Change the column value of the coordinate.
     *
     * @param y the new column value.
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
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
        final Coordinates other = (Coordinates) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

}
