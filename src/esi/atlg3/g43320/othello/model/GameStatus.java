/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

/**
 * This class represents all the different status the game can have.
 * @author s_u_y_s_a
 */
enum GameStatus {

    /**
     * The game status INITIALIZATION.
     */
    INIT("Initialization"),

    /**
     * The game status PLAY.
     */
    PLAY("Play");
    
    private final String libelle;
    
    private GameStatus(String libelle){
        this.libelle = libelle;
    }
    
    @Override
    public String toString(){
        return libelle;
    }
}
