/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

import esi.atlg3.g43320.othello.model.ColorPawn;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * This class represents the following strategy : the IA will chose a random
 * move amongs all its possible move.
 *
 * @author s_u_y_s_a
 */
public class RandomStrategy extends AbstractStrategyIA {

    private final OthelloModel othello;

    /**
     * It creates an instance of RandomStrategy.
     *
     * @param othello the game being played.
     */
    public RandomStrategy(OthelloModel othello) {
        this.othello = othello;
    }

    @Override
    public void play(String name) {
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event -> {
            othello.turnPassedFX(true);
            if (othello.isTurnPassed()) {
                othello.pass("IA");
                othello.changePlayer();
                othello.checkGameOver(name, true);
            } else {
                othello.updatePossibleMove(ColorPawn.WHITE);
                int nbPossibleMove = othello.getPossibleMove().size();
                int random = (int) (Math.random() * nbPossibleMove);
                Coordinates playedCoord = othello.getPossibleMove().get(random);
                othello.play(playedCoord, "IA");
                othello.checkGameOver(name, true);
            }
        });
        pause.play();
    }

}
