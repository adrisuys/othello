/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s_u_y_s_a
 */
public class GameTest {
    
    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of isMoveValidEast method, of class Game.
     */
    @Test
    public void testIsMoveValidEast1() {
        System.out.println("isMoveValidEast1");
        Coordinates aCoordinate = new Coordinates(1,2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(1,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(1,4), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(1,5), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidEast(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidEast method, of class Game.
     */
    @Test
    public void testIsMoveValidEast2() {
        System.out.println("isMoveValidEast2");
        Coordinates aCoordinate = new Coordinates(3,5);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3,6), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,7), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidEast(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidEast method, of class Game.
     */
    @Test
    public void testIsMoveValidEast3() {
        System.out.println("isMoveValidEast3");
        Coordinates aCoordinate = new Coordinates(7,4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(7,5), Color.BLACK);
        boolean expResult = false;
        boolean result = instance.isMoveValidEast(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidEast method, of class Game.
     */
    @Test
    public void testIsMoveValidEast() {
        System.out.println("isMoveValidEast4");
        Coordinates aCoordinate = new Coordinates(5,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(5,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(5,3), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidEast(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeColorPawn method, of class Game.
     */
    /*@Test
    public void testChangeColorPawn() {
        System.out.println("changeColorPawn");
        Board board = null;
        Color color = null;
        Game instance = new Game();
        instance.changeColorPawn(board, color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePlayer method, of class Game.
     */
    /*@Test
    public void testChangePlayer() {
        System.out.println("changePlayer");
        Game instance = new Game();
        instance.changePlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class Game.
     */
    /*@Test
    public void testGetBoard() {
        System.out.println("getBoard");
        Game instance = new Game();
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentColor method, of class Game.
     */
    /*@Test
    public void testGetCurrentColor() {
        System.out.println("getCurrentColor");
        Game instance = new Game();
        Color expResult = null;
        Color result = instance.getCurrentColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
   /* @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMoveValidWest method, of class Game.
     */
    @Test
    public void testIsMoveValidWest() {
        System.out.println("isMoveValidWest1");
        Coordinates aCoordinate = new Coordinates(0,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0,0), Color.BLACK);
        boolean expResult = false;
        boolean result = instance.isMoveValidWest(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidWest method, of class Game.
     */
    @Test
    public void testIsMoveValidWest2() {
        System.out.println("isMoveValidWest2");
        Coordinates aCoordinate = new Coordinates(2,4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(2,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,1), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidWest(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidWest method, of class Game.
     */
    @Test
    public void testIsMoveValidWest3() {
        System.out.println("isMoveValidWest3");
        Coordinates aCoordinate = new Coordinates(4,2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(4,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(4,0), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidWest(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidWest method, of class Game.
     */
    @Test
    public void testIsMoveValidWest4() {
        System.out.println("isMoveValidWest4");
        Coordinates aCoordinate = new Coordinates(6,2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(6,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(6,0), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidWest(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isTurnPassed method, of class Game.
     */
    /*@Test
    public void testIsTurnPassed() {
        System.out.println("isTurnPassed");
        Player player = null;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isTurnPassed(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOver method, of class Game.
     */
    /*@Test
    public void testIsOver() {
        System.out.println("isOver");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putPawn method, of class Game.
     */
    /*@Test
    public void testPutPawn() {
        System.out.println("putPawn");
        Coordinates aCoordinate = null;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.putPawn(aCoordinate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nbPossibleMove method, of class Game.
     */
    /*@Test
    public void testNbPossibleMove() {
        System.out.println("nbPossibleMove");
        Game instance = new Game();
        int expResult = 0;
        int result = instance.nbPossibleMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMoveValid method, of class Game.
     */
    /*@Test
    public void testIsMoveValid() {
        System.out.println("isMoveValid");
        Coordinates aCoordinate = null;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValid(aCoordinate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMoveValidNorth method, of class Game.
     */
    @Test
    public void testIsMoveValidNorth() {
        System.out.println("isMoveValidNorth1");
        Coordinates aCoordinate = new Coordinates(4,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(3,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,1), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidNorth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNorth method, of class Game.
     */
    @Test
    public void testIsMoveValidNorth2() {
        System.out.println("isMoveValidNorth2");
        Coordinates aCoordinate = new Coordinates(1,3);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0,3), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidNorth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNorth method, of class Game.
     */
    @Test
    public void testIsMoveValidNorth3() {
        System.out.println("isMoveValidNorth3");
        Coordinates aCoordinate = new Coordinates(1,5);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0,5), Color.BLACK);
        boolean expResult = false;
        boolean result = instance.isMoveValidNorth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNorth method, of class Game.
     */
    @Test
    public void testIsMoveValidNorth4() {
        System.out.println("isMoveValidNorth4");
        Coordinates aCoordinate = new Coordinates(4,4);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidNorth(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidSouth method, of class Game.
     */
    @Test
    public void testIsMoveValidSouth() {
        System.out.println("isMoveValidSouth1");
        Coordinates aCoordinate = new Coordinates(1,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(2,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,1), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidSouth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSouth method, of class Game.
     */
    @Test
    public void testIsMoveValidSouth2() {
        System.out.println("isMoveValidSouth2");
        Coordinates aCoordinate = new Coordinates(5,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(6,1), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidSouth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSouth method, of class Game.
     */
    @Test
    public void testIsMoveValidSouth3() {
        System.out.println("isMoveValidSouth3");
        Coordinates aCoordinate = new Coordinates(0,3);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(1,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(4,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(5,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(6,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(7,3), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidSouth(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSouth method, of class Game.
     */
    @Test
    public void testIsMoveValidSouth4() {
        System.out.println("isMoveValidSouth4");
        Coordinates aCoordinate = new Coordinates(4,5);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(5,5), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(6,5), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(7,5), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidSouth(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidSE method, of class Game.
     */
    @Test
    public void testIsMoveValidSE() {
        System.out.println("isMoveValidSE1");
        Coordinates aCoordinate = new Coordinates(1,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(2,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,3), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidSE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSE method, of class Game.
     */
    @Test
    public void testIsMoveValidSE2() {
        System.out.println("isMoveValidSE2");
        Coordinates aCoordinate = new Coordinates(4,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(5,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(6,3), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidSE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSE method, of class Game.
     */
    @Test
    public void testIsMoveValidSE3() {
        System.out.println("isMoveValidSE3");
        Coordinates aCoordinate = new Coordinates(2,7);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidSE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSE method, of class Game.
     */
    @Test
    public void testIsMoveValidSE4() {
        System.out.println("isMoveValidSE4");
        Coordinates aCoordinate = new Coordinates(7,7);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidSE(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidSW method, of class Game.
     */
    @Test
    public void testIsMoveValidSW() {
        System.out.println("isMoveValidSW1");
        Coordinates aCoordinate = new Coordinates (0,0);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidSW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSW method, of class Game.
     */
    @Test
    public void testIsMoveValidSW2() {
        System.out.println("isMoveValidSW2");
        Coordinates aCoordinate = new Coordinates (1,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(2,0), Color.BLACK);
        boolean expResult = false;
        boolean result = instance.isMoveValidSW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSW method, of class Game.
     */
    @Test
    public void testIsMoveValidSW3() {
        System.out.println("isMoveValidSW3");
        Coordinates aCoordinate = new Coordinates (0,7);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(1,6), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,5), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,4), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(4,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(5,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(6,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(7,0), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidSW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidSW method, of class Game.
     */
    @Test
    public void testIsMoveValidSW4() {
        System.out.println("isMoveValidSW4");
        Coordinates aCoordinate = new Coordinates (5,7);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(6,6), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidSW(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidNE method, of class Game.
     */
    @Test
    public void testIsMoveValidNE() {
        System.out.println("isMoveValidNE1");
        Coordinates aCoordinate = new Coordinates(0,7);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidNE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNE method, of class Game.
     */
    @Test
    public void testIsMoveValidNE2() {
        System.out.println("isMoveValidNE2");
        Coordinates aCoordinate = new Coordinates(1,0);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0,1), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidNE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNE method, of class Game.
     */
    @Test
    public void testIsMoveValidNE3() {
        System.out.println("isMoveValidNE3");
        Coordinates aCoordinate = new Coordinates(5,0);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(4,1), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,2), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidNE(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNE method, of class Game.
     */
    @Test
    public void testIsMoveValidNE4() {
        System.out.println("isMoveValidNE4");
        Coordinates aCoordinate = new Coordinates(7,4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(6,5), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(5,6), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidNE(aCoordinate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMoveValidNW method, of class Game.
     */
    @Test
    public void testIsMoveValidNW() {
        System.out.println("isMoveValidNW1");
        Coordinates aCoordinate = new Coordinates(1,1);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(0,0), Color.BLACK);
        boolean expResult = false;
        boolean result = instance.isMoveValidNW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNW method, of class Game.
     */
    @Test
    public void testIsMoveValidNW2() {
        System.out.println("isMoveValidNW2");
        Coordinates aCoordinate = new Coordinates(5,4);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(4,3), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(3,2), Color.WHITE);
        instance.getBoard().putPawn(new Coordinates(2,1), Color.BLACK);
        boolean expResult = true;
        boolean result = instance.isMoveValidNW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNW method, of class Game.
     */
    @Test
    public void testIsMoveValidNW3() {
        System.out.println("isMoveValidNW3");
        Coordinates aCoordinate = new Coordinates(0,7);
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isMoveValidNW(aCoordinate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isMoveValidNW method, of class Game.
     */
    @Test
    public void testIsMoveValidNW4() {
        System.out.println("isMoveValidNW4");
        Coordinates aCoordinate = new Coordinates(6,2);
        Game instance = new Game();
        instance.getBoard().putPawn(new Coordinates(5,1), Color.WHITE);
        boolean expResult = false;
        boolean result = instance.isMoveValidNW(aCoordinate);
        assertEquals(expResult, result);
    }
    
}
