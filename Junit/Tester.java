import org.junit.Test;

import static org.junit.Assert.*;

public class Tester {

    @Test //Horizontal Win
    public void H_testWin() {
        Main tester = new Main();
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
        Main tester = new Main();
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
        Main tester = new Main();
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
        Main tester = new Main();
        assertFalse("False Win", tester.connect4(
                new int[][]{{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 2, 1, 0},}, 1));
    }

    @Test //Test to place a token at an already full column
    public void FullColumn_test() {
        Main tester = new Main();
        assertEquals("Column full!", tester.placeToken("B",1,
                new int[][]{{0, 1, 0, 0, 0, 0, 0},
                            {0, 2, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0, 1, 0},
                            {0, 1, 0, 0, 2, 1, 0},
                            {0, 1, 0, 1, 1, 2, 0},
                            {0, 2, 1, 2, 2, 1, 0}}));
    }

    @Test //Test a regular move
    public void NormalMove_test() {
        Main tester = new Main();
        assertEquals("", tester.placeToken("D",2,
                new int[][]{{0, 1, 0, 0, 0, 0, 0},
                            {0, 2, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0, 1, 0},
                            {0, 1, 0, 0, 2, 1, 0},
                            {0, 1, 0, 1, 1, 2, 0},
                            {0, 2, 1, 2, 2, 1, 0}}));
    }

    @Test //Test invalid input
    public void InvalidInput_test() {
        Main tester = new Main();
        assertEquals("Invalid Move!", tester.placeToken("H",1,
                new int[][]{{0, 1, 0, 0, 0, 0, 0},
                            {0, 2, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0, 1, 0},
                            {0, 1, 0, 0, 2, 1, 0},
                            {0, 1, 0, 1, 1, 2, 0},
                            {0, 2, 1, 2, 2, 1, 0}}));
    }
}