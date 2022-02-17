import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tester {

    Main tester = new Main();

    @Test //Horizontal Win
    public void H_testWin() {
        assertTrue("Horizontal Win",tester.connect4(
                new int[][]{{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 1, 1, 1, 1},}, 1));
    }

    @Test //Vertical Win, works with >4 tokens
    public void V_testWin() {
        assertTrue("Vertical Win", tester.connect4(
                new int[][]{{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 2, 0, 0, 0, 0},
                            {0, 0, 2, 0, 0, 0, 0},
                            {0, 0, 2, 0, 0, 0, 0},
                            {0, 0, 2, 0, 0, 0, 0},
                            {0, 0, 2, 0, 0, 0, 0},}, 2));
    }

    @Test //Diagonal Win
    public void D_testWin() {
        assertTrue("Diagonal Win", tester.connect4(
                new int[][]{{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 1, 0},
                            {0, 0, 0, 0, 1, 0, 0},
                            {0, 0, 0, 1, 0, 0, 0},
                            {0, 0, 1, 0, 0, 0, 0},}, 1));
    }

    @Test //False Win
    public void test_NoWin() {
        assertFalse("False Win", tester.connect4(
                new int[][]{{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 2, 1, 0},}, 1));
    }

    @Test //Test valid input
    public void test_input() {
        assertEquals(2, tester.clean_input("C"));
    }

    @Test //Test invalid input
    public void test_F_input() {
        assertEquals(999, tester.clean_input("K"));
    }

    @Test //Test to place a token at an already full column
    public void FullColumn_test() {
        assertEquals("Column full!", tester.placeToken(1,1,
                new int[][]{{0, 1, 0, 0, 0, 0, 0},
                            {0, 2, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0, 1, 0},
                            {0, 1, 0, 0, 2, 1, 0},
                            {0, 1, 0, 1, 1, 2, 0},
                            {0, 2, 1, 2, 2, 1, 0}}));
    }

    @Test //Test a regular move
    public void NormalMove_test() {

        int[][] board1 = { {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},};

        Main tester = new Main();
        assertEquals("", tester.placeToken(3, 2, board1));

        //Verify that it actually placed the token
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("A   B   C   D   E   F   G");
    }

    @Test //Test invalid input message
    public void InvalidInput_test() {
        assertEquals("Invalid Move!", tester.placeToken(999,1,
                new int[][]{{0, 1, 0, 0, 0, 0, 0},
                            {0, 2, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0, 1, 0},
                            {0, 1, 0, 0, 2, 1, 0},
                            {0, 1, 0, 1, 1, 2, 0},
                            {0, 2, 1, 2, 2, 1, 0}}));
    }

    //(array index is off by 1)

    @Test //Test the computer algorithm's normal moves
    public void algorithm_test1() {
        assertEquals(7, tester.PC_bot( new int[][]{{0, 0, 0, 0, 0, 0, 0},
                                                            {0, 0, 0, 0, 0, 0, 0},
                                                            {0, 0, 0, 0, 0, 0, 0},
                                                            {0, 0, 0, 0, 0, 0, 0},
                                                            {0, 0, 0, 0, 0, 0, 1},
                                                            {2, 2, 0, 0, 0, 1, 1}}, 5,7));
        //Here every three turns the pc places the token above the players last move, otherwise place in columns incrementally
    }

    @Test //Test the computer algorithm
    public void algorithm_test2() {
        assertEquals(2, tester.PC_bot( new int[][]{{0, 1, 0, 0, 0, 0, 0},
                                                            {0, 2, 0, 0, 0, 0, 0},
                                                            {0, 1, 0, 0, 0, 1, 0},
                                                            {0, 1, 0, 0, 2, 1, 0},
                                                            {0, 1, 0, 1, 1, 2, 0},
                                                            {0, 2, 1, 2, 2, 1, 0}}, 13,3));
        //Here player 1 is close to winning at row 2 column 3, but the algorithm detects this and instead places its token there
    }
}
