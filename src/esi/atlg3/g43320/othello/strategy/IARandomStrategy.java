/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.GameException;
import esi.atlg3.g43320.othello.model.GameModel;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents the following strategy : the IA will chose a random
 * move amongs all its possible move.
 *
 * @author s_u_y_s_a
 */
public class IARandomStrategy implements Strategy {

    private final GameModel othello;

    /**
     * It creates an instance of RandomStrategy.
     *
     * @param strategy the strategy from which the new instance is copied.
     */
    public IARandomStrategy(IARandomStrategy strategy) {
        this.othello = strategy.othello;
    }

    /**
     * It creates an instance of RandomStrategy.
     *
     * @param othello the game being played.
     */
    public IARandomStrategy(GameModel othello) {
        this.othello = othello;
    }

    @Override
    public void execute() {
        othello.turnPassed(true);
        if (othello.isTurnPassed()) {
            try {
                othello.pass(othello.getCurrentPlayer().getName());
            } catch (GameException ex) {
                System.out.println("error GameException");
            }
        } else {
            othello.updatePossibleMove(othello.getCurrentColor());
            int nbPossibleMove = othello.getPossibleMove().size();
            Random r = new Random();
            int random = r.nextInt(nbPossibleMove);
            Coordinates playedCoord = othello.getPossibleMove().get(random);
            try {
                othello.play(playedCoord, othello.getCurrentPlayer().getName());
            } catch (GameException ex) {
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.othello);
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
        final IARandomStrategy other = (IARandomStrategy) obj;
        return Objects.equals(this.othello, other.othello);
    }

}
