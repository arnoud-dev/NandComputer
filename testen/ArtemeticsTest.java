package testen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import domein.Artemetics;

public class ArtemeticsTest {

    private Artemetics ar = new Artemetics();

    private boolean[] numToByte(int num) {
        boolean[] outputByte = new boolean[8];
        for (int i = 0; i < 8; i++) {
            outputByte[i] = (num & (1 << (7 - i))) != 0;
        }
        return outputByte;
    }

    private int byteToNum(boolean[] inputByte) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            if (inputByte[i]) num += 1 << (7 - i);
        }
        return num;
    }

    private int byteToNumNeg(boolean[] inputByte) {
        int num = byteToNum(inputByte);
        if (inputByte[0]) num -= 256;
        return num;
    }

    @Test
    void testAllAdditions() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                boolean[] a = numToByte(i);
                boolean[] b = numToByte(j);
                Object[] res = ar.byteAdder(a, b);
                boolean[] sum = (boolean[]) res[0];
                boolean overflow = (boolean) res[1];
                int expected = (i + j) & 0xFF;
                boolean expectedOverflow = (i + j) > 255;
                assertEquals(expected, byteToNum(sum));
                assertEquals(expectedOverflow, overflow);
            }
        }
    }

    @Test
    void testAllSubtractions() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                boolean[] a = numToByte(i);
                boolean[] b = numToByte(j);
                Object[] res = ar.byteSuber(a, b);
                boolean[] diff = (boolean[]) res[0];
                int expected = (i - j + 256) % 256;
                assertEquals(expected, byteToNum(diff));
            }
        }
    }

    @Test
    void testAllXor() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                boolean[] a = numToByte(i);
                boolean[] b = numToByte(j);
                Object[] res = ar.byteXor(a, b);
                boolean[] xor = (boolean[]) res[0];
                assertEquals(i ^ j, byteToNum(xor));
                assertFalse((boolean) res[1]);
            }
        }
    }

    @Test
    void testAllAnd() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                boolean[] a = numToByte(i);
                boolean[] b = numToByte(j);
                boolean[] and = ar.byteAnd(a, b);
                assertEquals(i & j, byteToNum(and));
            }
        }
    }

    @Test
    void testAllOr() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                boolean[] a = numToByte(i);
                boolean[] b = numToByte(j);
                boolean[] or = ar.byteOr(a, b);
                assertEquals(i | j, byteToNum(or));
            }
        }
    }

    @Test
    void testAllNot() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            boolean[] not = ar.byteNot(a);
            assertEquals(i ^ 0xFF, byteToNum(not));
        }
    }

    @Test
    void testAllRightShifts() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            Object[] res = ar.rightShift(a);
            boolean[] shifted = (boolean[]) res[0];
            boolean overflow = (boolean) res[1];
            int expectedShift = i >> 1;
            boolean expectedOverflow = (i & 1) != 0;
            assertEquals(expectedShift, byteToNum(shifted));
            assertEquals(expectedOverflow, overflow);
        }
    }

    @Test
    void testAllSignedNumbers() {
        for (int i = 0; i < 256; i++) {
            boolean[] byteVal = numToByte(i);
            int signed = byteToNumNeg(byteVal);
            if (i < 128) {
                assertEquals(i, signed);
            } else {
                assertEquals(i - 256, signed);
            }
        }
    }
}
