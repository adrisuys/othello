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
    
    SW (1, -1),
    SE (1, 1),
    NE (-1, 1),
    NW (-1, -1),
    N (-1, 0),
    S (1, 0),
    E (0, 1),
    W (0, -1);
    
    private final int xShift;
    private final int yShift;
    
    private Directions (int xShift, int yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
    }

    public int getxShift() {
        return xShift;
    }

    public int getyShift() {
        return yShift;
    }
    
    
}
