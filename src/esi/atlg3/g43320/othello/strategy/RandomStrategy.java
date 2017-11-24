/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

import esi.atlg3.g43320.othello.model.ColorPawn;
import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;

/**
 *
 * @author s_u_y_s_a
 */
public class RandomStrategy extends AbstractStrategyIA {

    private final OthelloModel othello;

    public RandomStrategy(OthelloModel othello) {
        this.othello = othello;
    }

    @Override
    public void play(String name) {
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
            //othello.changePlayer();
            othello.checkGameOver(name, true);
        }
    }

}
