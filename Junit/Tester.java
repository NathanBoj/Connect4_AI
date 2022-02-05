import org.junit.Test;

import static org.junit.Assert.*;

public class Tester {

    @Test
    public void testWin() {
        Main tester = new Main();
        assertEquals(true, tester.connect4(new int[][]{{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1},}, 1));
    }
}