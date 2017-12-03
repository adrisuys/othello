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
public enum GameStatus {
    INIT("Initialization"), PLAY("Play");
    
    private final String libelle;
    
    private GameStatus(String libelle){
        this.libelle = libelle;
    }
    
    public String toString(){
        return libelle;
    }
}
