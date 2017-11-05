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
public class BoardTest {
    
    /**
     * Creates an instance of the class BoardTest.
     */
    public BoardTest() {
    }

    /**
     * Test of putPawn method, of class Board.
     */
    @Test
    public void testPutPawn() {
        System.out.println("putPawn");
        Coordinates aCoordinate = new Coordinates(1,1);
        Board instance = new Board();
        instance.putPawn(aCoordinate, Color.BLACK);
        System.out.println(instance.getCheckerboard()[1][1]);
        assertEquals(1, instance.getCheckerboard()[1][1]);
    }

    /**
     * Test of isFree method, of class Board.
     */
    @Test
    public void testIsFree1() {
        System.out.println("isFree1");
        Coordinates aCoordinate = new Coordinates(1,1);
        Board instance = new Board();
        boolean expResult = true;
        boolean result = instance.isFree(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isFree method, of class Board.
     */
    @Test
    public void testIsFree2() {
        System.out.println("isFree2");
        Coordinates aCoordinate = new Coordinates(1,1);
        Board instance = new Board();
        instance.putPawn(aCoordinate, Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isFree(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Board.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Color color = Color.BLACK;
        Board instance = new Board();
        instance.putPawn(new Coordinates(1,1), Color.BLACK);
        instance.putPawn(new Coordinates(1,5), Color.WHITE);
        instance.putPawn(new Coordinates(4,1), Color.BLACK);
        int expResult = 2;
        int result = instance.getScore(color);
        assertEquals(expResult, result);
    }
    
}
