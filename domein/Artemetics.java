package domein;

public class Artemetics {
    private LogicGates lg;

    public Artemetics() {
        lg = new LogicGates();
    }

    
    public boolean[] halfAdder(boolean a, boolean b) {
        boolean sum = lg.xorGate(a, b);
        boolean overflow = lg.andGate(a, b);
        return new boolean[]{sum, overflow};
    }

    public boolean[] fullAdder(boolean in, boolean a, boolean b) {
        boolean[] ha1 = halfAdder(a, b);
        boolean[] ha2 = halfAdder(in, ha1[0]);
        return new boolean[]{ha2[0], lg.orGate(ha1[1], ha2[1])};
    }

    public Object[] byteAdder(boolean[] byteOne, boolean[] byteTwo) {
        boolean carry = false;
        boolean[] sum = new boolean[8];
        for (int i = 7; i >= 0; i--) {
            boolean[] res = fullAdder(carry, byteOne[i], byteTwo[i]);
            sum[i] = res[0];
            carry = res[1];
        }
        return new Object[]{sum, carry};
    }

    public Object[] byteSuber(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] one = new boolean[8];
        one[7] = true;
        Object[] twoComplement = byteAdder(byteNot(byteTwo), one);
        return byteAdder(byteOne, (boolean[]) twoComplement[0]);
    }

    public boolean[] byteAnd(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) result[i] = lg.andGate(byteOne[i], byteTwo[i]);
        return result;
    }

    public boolean[] byteOr(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) result[i] = lg.orGate(byteOne[i], byteTwo[i]);
        return result;
    }

    public boolean[] byteNot(boolean[] byteOne) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) result[i] = lg.notGate(byteOne[i]);
        return result;
    }

    public Object[] byteXor(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) result[i] = lg.xorGate(byteOne[i], byteTwo[i]);
        return new Object[]{result, false};
    }

    public Object[] rightShift(boolean[] byteIn) {
        boolean overflow = byteIn[7];
        boolean[] shifted = new boolean[8];
        for (int i = 7; i > 0; i--) shifted[i] = byteIn[i - 1];
        shifted[0] = false;
        return new Object[]{shifted, overflow};
    }
}
