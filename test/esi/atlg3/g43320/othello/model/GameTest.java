/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s_u_y_s_a
 */
public class GameTest {

    /**
     * Creates an instance of the class GameTest.
     */
    public GameTest() {
    }

    /**
     * Test of isTurnPassed method, of class Game.
     */
    @Test
    public void testIsTurnPassed() {
        System.out.println("isTurnPassed1");
        Game instance = new Game();
        Player player = instance.getCurrentPlayer();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = false;
        boolean result = instance.isTurnPassed(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of isTurnPassed method, of class Game.
     */
    @Test
    public void testIsTurnPassed2() {
        System.out.println("isTurnPassed2");
        Game instance = new Game();
        Player player = instance.getCurrentPlayer();
        instance.getBoard().putPawn(new Coordinates(0, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 4), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(0, 5), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(4, 1), ColorPawn.BLACK);
        boolean expResult = true;
        boolean result = instance.isTurnPassed(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver() {
        System.out.println("isOver");
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 4), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(0, 5), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(4, 1), ColorPawn.BLACK);
        boolean expResult = false;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver2() {
        System.out.println("isOver2");
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 4), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(0, 5), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(4, 1), ColorPawn.WHITE);
        boolean expResult = true;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of putPawn method, of class Game.
     */
    @Test
    public void testPutPawn() {
        System.out.println("putPawn1");
        Coordinates aCoordinate = new Coordinates(3, 2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = true;
        boolean result;
        try {
            result = instance.putPawn(aCoordinate, ColorPawn.BLACK);
        } catch (GameException ex) {
            result = false;
        }
        System.out.println(instance.getBoard().getCheckerboard()[3][2]);
        assertEquals(expResult, result);
    }

    /**
     * Test of putPawn method, of class Game.
     */
    @Test
    public void testPutPawn2() {
        System.out.println("putPawn2");
        Coordinates aCoordinate = new Coordinates(2, 4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = false;
        boolean result;
        try {
            result = instance.putPawn(aCoordinate, ColorPawn.BLACK);
        } catch (GameException ex) {
            result = false;
        }
        System.out.println(instance.getBoard().getCheckerboard()[2][4]);
        assertEquals(expResult, result);
    }

    /**
     * Test of nbPossibleMove method, of class Game.
     */
    @Test
    public void testNbPossibleMove() {
        System.out.println("nbPossibleMove1");
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        int expResult = 4;
        int result = instance.nbPossibleMove(ColorPawn.BLACK);
        assertEquals(expResult, result);
    }

    /**
     * Test of nbPossibleMove method, of class Game.
     */
    @Test
    public void testNbPossibleMove2() {
        System.out.println("nbPossibleMove2");
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 1), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 4), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(0, 5), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(4, 1), ColorPawn.BLACK);
        int expResult = 0;
        int result = instance.nbPossibleMove(ColorPawn.BLACK);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValid method, of class Game.
     */
    @Test
    public void testIsMoveValid() {
        System.out.println("isMoveValid1");
        Coordinates aCoordinate = new Coordinates(3, 2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = true;
        boolean result = instance.isMoveValid(aCoordinate, ColorPawn.BLACK);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValid method, of class Game.
     */
    @Test
    public void testIsMoveValid2() {
        System.out.println("isMoveValid2");
        Coordinates aCoordinate = new Coordinates(3, 5);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValid(aCoordinate, ColorPawn.BLACK);
        assertEquals(expResult, result);
    }

    /**
     * Verfies if the right coordinates are put on the list pawnsToBeTurned.
     */
    @Test
    public void testListCoordChange() {
        System.out.println("Test List de coord à changer");
        Coordinates aCoordinate = new Coordinates(3, 5);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0, 2), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(1, 4), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(1, 5), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(2, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(2, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(2, 5), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(3, 3), ColorPawn.WHITE);
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 3), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = true;
        boolean result = instance.isMoveValid(aCoordinate, ColorPawn.WHITE);
        instance.getPawnsToBeTurned().forEach((c) -> {
            c.forEach((co) -> {
                System.out.println(co);
            });
        });
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidTest method, of class Game.
     */
    @Test
    public void testisMoveValidTest() {
        System.out.println("isMoveValidTest");
        Coordinates aCoordinate = new Coordinates(2, 4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = true;
        boolean result = instance.isMoveValidAllDirections(aCoordinate, ColorPawn.WHITE);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidTest method, of class Game.
     */
    @Test
    public void testisMoveValidTest2() {
        System.out.println("isMoveValidTest2");
        Coordinates aCoordinate = new Coordinates(1, 4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3, 4), ColorPawn.BLACK);
        instance.getBoard().putPawn(new Coordinates(4, 4), ColorPawn.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidAllDirections(aCoordinate, ColorPawn.WHITE);
        assertEquals(expResult, result);
    }

}
