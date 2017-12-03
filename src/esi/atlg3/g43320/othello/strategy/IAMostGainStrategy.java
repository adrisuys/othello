/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.strategy;

import esi.atlg3.g43320.othello.model.Coordinates;
import esi.atlg3.g43320.othello.model.GameException;
import esi.atlg3.g43320.othello.model.OthelloModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * This class represent the following strategy : the IA will checks its possible
 * move. For each one of them, it will calculate the number of pawn taken. At
 * the end, it will chose the move that takes the more opponent's pawns.
 *
 * @author s_u_y_s_a
 */
public class IAMostGainStrategy implements Strategy {

    private final OthelloModel othello;

    /**
     * Creates an instance of MostGainStrategy.
     *
     * @param othello the game being played.
     */
    public IAMostGainStrategy(OthelloModel othello) {
        this.othello = othello;
    }

    @Override
    public void play(String name) {
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event -> {
            othello.turnPassedFX(true);
            if (othello.isTurnPassed()) {
                try {
                    othello.pass("IA");
                    othello.changePlayer();
                    othello.checkGameOver(name, true);
                } catch (GameException ex) {
                }
            } else {
                try {
                    othello.play(getBestPossibleMove(othello.getPossibleMove()), "IA");
                    othello.checkGameOver(name, true);
                } catch (GameException ex) {
                }
            }
        });
    }

    private Coordinates getBestPossibleMove(List<Coordinates> possibleMoves) {
        Coordinates bestMove = null;
        int nbPawnsTaken = 0;
        for (Coordinates c : possibleMoves) {
            try {
                if (othello.putPawn(c) == true) {
                    if (nbPawnsTaken <= othello.getNbPawnsTaken()) {
                        nbPawnsTaken = othello.getNbPawnsTaken();
                        bestMove = c;
                    }
                }
            } catch (GameException ex) {
            }
            othello.removePawn(c);
        }
        return bestMove;
    }

    @Override
    public boolean isIA() {
        return true;
    }

}
