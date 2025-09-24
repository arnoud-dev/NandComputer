package testen;

import org.junit.jupiter.api.Test;
import domein.CompareComps;
import static org.junit.jupiter.api.Assertions.*;

public class CompareCompsTest {

    private CompareComps comp = new CompareComps();

    boolean[] numToByte(int num) {
        boolean[] outputByte = new boolean[8];
        for (int i = 0; i < 8; i++) {
            outputByte[i] = (num & (1 << (7 - i))) != 0;
        }
        return outputByte;
    }

    int byteToNum(boolean[] byteVal) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            if (byteVal[i]) num += 1 << (7 - i);
        }
        return num;
    }

    @Test
    void testAllEqual() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            boolean[] b = numToByte(i);
            assertTrue(comp.equal(a, b));
            for (int j = 0; j < 256; j++) {
                if (i != j) {
                    b = numToByte(j);
                    assertFalse(comp.equal(a, b));
                }
            }
        }
    }

    @Test
    void testAllNotEqual() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            for (int j = 0; j < 256; j++) {
                boolean[] b = numToByte(j);
                if (i == j) {
                    assertFalse(comp.notEqual(a, b));
                } else {
                    assertTrue(comp.notEqual(a, b));
                }
            }
        }
    }

    @Test
    void testAllGreaterThen() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            for (int j = 0; j < 256; j++) {
                boolean[] b = numToByte(j);
                assertEquals(i > j, comp.greaterThan(a, b));
            }
        }
    }

    @Test
    void testAllGreaterOrEqualThen() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            for (int j = 0; j < 256; j++) {
                boolean[] b = numToByte(j);
                assertEquals(i >= j, comp.greaterOrEqualThan(a, b));
            }
        }
    }

    @Test
    void testAllLesserThen() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            for (int j = 0; j < 256; j++) {
                boolean[] b = numToByte(j);
                assertEquals(i < j, comp.lesserThan(a, b));
            }
        }
    }

    @Test
    void testAllLesserOrEqualThen() {
        for (int i = 0; i < 256; i++) {
            boolean[] a = numToByte(i);
            for (int j = 0; j < 256; j++) {
                boolean[] b = numToByte(j);
                assertEquals(i <= j, comp.lesserOrEqualThan(a, b));
            }
        }
    }
}