/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * This class represent the following strategy : the IA will checks its possible
 * move. For each one of them, it will calculate the number of pawn taken. At
 * the end, it will chose the move that takes the more opponent's pawns.
 *
 * @author s_u_y_s_a
 */
public class MostGainStrategy extends AbstractStrategyIA {

    private final OthelloModel othello;

    /**
     * Creates an instance of MostGainStrategy.
     *
     * @param othello the game being played.
     */
    public MostGainStrategy(OthelloModel othello) {
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
                othello.play(getBestPossibleMove(othello.getPossibleMove()), "IA");
                othello.checkGameOver(name, true);
            }
        });
    }

    private Coordinates getBestPossibleMove(List<Coordinates> possibleMoves) {
        Coordinates bestMove = null;
        int nbPawnsTaken = 0;
        for (Coordinates c : possibleMoves) {
            if (othello.putPawn(c) == true) {
                if (nbPawnsTaken <= othello.getNbPawnsTaken()) {
                    nbPawnsTaken = othello.getNbPawnsTaken();
                    bestMove = c;
                }
            }
            othello.removePawn(c);
        }
        return bestMove;
    }

}
