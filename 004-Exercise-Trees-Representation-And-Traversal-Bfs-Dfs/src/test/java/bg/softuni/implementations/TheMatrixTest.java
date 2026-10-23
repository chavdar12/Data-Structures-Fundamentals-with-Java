package bg.softuni.implementations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheMatrixTest {

    @Test
    public void testZeroTestOne() {
        char[][] matrix = {
                {'a', 'a', 'a'},
                {'a', 'a', 'a'},
                {'a', 'b', 'a'},
                {'a', 'b', 'a'},
                {'a', 'b', 'a'}
        };
        char fillChar = 'x';
        int startRow = 0;
        int startCol = 0;

        TheMatrix theMatrix = new TheMatrix(matrix, fillChar, startRow, startCol);

        theMatrix.solve();

        String str = theMatrix.toOutputString();
        assertEquals(
                "xxx" + System.lineSeparator() +
                        "xxx" + System.lineSeparator() +
                        "xbx" + System.lineSeparator() +
                        "xbx" + System.lineSeparator() +
                        "xbx", str);
    }

    @Test
    public void testZeroTestTwo() {
        char[][] matrix = {
                {'a', 'a', 'a',},
                {'a', 'a', 'a',},
                {'a', 'b', 'a',},
                {'a', 'b', 'a',},
                {'a', 'b', 'a',}

        };
        char fillChar = 'x';
        int startRow = 2;
        int startCol = 1;

        TheMatrix theMatrix = new TheMatrix(matrix, fillChar, startRow, startCol);

        theMatrix.solve();

        String str = theMatrix.toOutputString();
        assertEquals(
                "aaa" + System.lineSeparator() +
                        "aaa" + System.lineSeparator() +
                        "axa" + System.lineSeparator() +
                        "axa" + System.lineSeparator() +
                        "axa", str);
    }

    @Test
    public void testZeroTestThree() {
        char[][] matrix = {
                {'o', 'o', '1', '1', 'o', 'o'},
                {'o', '1', 'o', 'o', '1', 'o'},
                {'1', 'o', 'o', 'o', 'o', '1'},
                {'o', '1', 'o', 'o', '1', 'o'},
                {'o', 'o', '1', '1', 'o', 'o'}
        };
        char fillChar = '3';
        int startRow = 2;
        int startCol = 1;

        TheMatrix theMatrix = new TheMatrix(matrix, fillChar, startRow, startCol);

        theMatrix.solve();

        String str = theMatrix.toOutputString();
        assertEquals(
                "oo11oo" + System.lineSeparator() +
                        "o1331o" + System.lineSeparator() +
                        "133331" + System.lineSeparator() +
                        "o1331o" + System.lineSeparator() +
                        "oo11oo", str);
    }

    @Test
    public void testZeroTestFour() {
        char[][] matrix = {
                {'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', '1', 'o', 'o'},
                {'o', 'o', '1', 'o', '1', '1'},
                {'o', '1', '1', 'w', '1', 'o'},
                {'1', 'o', 'o', 'o', 'o', 'o'}
        };
        char fillChar = 'z';
        int startRow = 4;
        int startCol = 1;

        TheMatrix theMatrix = new TheMatrix(matrix, fillChar, startRow, startCol);

        theMatrix.solve();

        String str = theMatrix.toOutputString();
        assertEquals(
                "oooooo" + System.lineSeparator() +
                        "ooo1oo" + System.lineSeparator() +
                        "oo1o11" + System.lineSeparator() +
                        "o11w1z" + System.lineSeparator() +
                        "1zzzzz", str);
    }

    @Test
    public void testZeroTestFive() {
        char[][] matrix = {
                {'o', '1', 'o', 'o', '1', 'o'},
                {'o', '1', 'o', 'o', '1', 'o'},
                {'o', '1', '1', '1', '1', 'o'},
                {'o', '1', 'o', 'w', '1', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o'}
        };
        char fillChar = 'z';
        int startRow = 4;
        int startCol = 0;

        TheMatrix theMatrix = new TheMatrix(matrix, fillChar, startRow, startCol);

        theMatrix.solve();

        String str = theMatrix.toOutputString();
        assertEquals(
                "z1oo1z" + System.lineSeparator() +
                        "z1oo1z" + System.lineSeparator() +
                        "z1111z" + System.lineSeparator() +
                        "z1zw1z" + System.lineSeparator() +
                        "zzzzzz", str);
    }
}