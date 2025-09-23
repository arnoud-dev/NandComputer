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

    public boolean[] byteAdder(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] adder1 = fullAdder(false, byteOne[8], byteTwo[8]);
        boolean[] adder2 = fullAdder(adder1[1], byteOne[7], byteTwo[7]);
        boolean[] adder3 = fullAdder(adder2[1], byteOne[6], byteTwo[6]);
        boolean[] adder4 = fullAdder(adder3[1], byteOne[5], byteTwo[5]);
        boolean[] adder5 = fullAdder(adder4[1], byteOne[4], byteTwo[4]);
        boolean[] adder6 = fullAdder(adder5[1], byteOne[3], byteTwo[3]);
        boolean[] adder7 = fullAdder(adder6[1], byteOne[2], byteTwo[2]);
        boolean[] adder8 = fullAdder(adder7[1], byteOne[1], byteTwo[1]);

        return new boolean[]{
        	adder8[1],
            adder8[0],
            adder7[0],
            adder6[0],
            adder5[0],
            adder4[0],
            adder3[0],
            adder2[0],
            adder1[0]
        };
    }

    public boolean[] byteSuber(boolean[] byteOne, boolean[] byteTwo) {
        boolean[] one = {false,false,false,false,false,false,false,false,true};
        boolean[] twoComplement = byteAdder(byteNot(byteTwo), one);

        return byteAdder(byteOne, twoComplement);
    }

    public boolean[] byteAnd(boolean[] byteOne, boolean[] byteTwo) {
        return new boolean[]{
            false,
            lg.andGate(byteOne[0], byteTwo[0]),
            lg.andGate(byteOne[1], byteTwo[1]),
            lg.andGate(byteOne[2], byteTwo[2]),
            lg.andGate(byteOne[3], byteTwo[3]),
            lg.andGate(byteOne[4], byteTwo[4]),
            lg.andGate(byteOne[5], byteTwo[5]),
            lg.andGate(byteOne[6], byteTwo[6]),
            lg.andGate(byteOne[7], byteTwo[7])
        };
    }

    public boolean[] byteOr(boolean[] byteOne, boolean[] byteTwo) {
        return new boolean[]{
            false,
            lg.orGate(byteOne[1], byteTwo[1]),
            lg.orGate(byteOne[2], byteTwo[2]),
            lg.orGate(byteOne[3], byteTwo[3]),
            lg.orGate(byteOne[4], byteTwo[4]),
            lg.orGate(byteOne[5], byteTwo[5]),
            lg.orGate(byteOne[6], byteTwo[6]),
            lg.orGate(byteOne[7], byteTwo[7]),
            lg.orGate(byteOne[8], byteTwo[8])
        };
    }

    public boolean[] byteNot(boolean[] byteOne) {
        return new boolean[]{
            false,
            lg.notGate(byteOne[1]),
            lg.notGate(byteOne[2]),
            lg.notGate(byteOne[3]),
            lg.notGate(byteOne[4]),
            lg.notGate(byteOne[5]),
            lg.notGate(byteOne[6]),
            lg.notGate(byteOne[7]),
            lg.notGate(byteOne[8])
        };
    }
    //Temp methodes
    public boolean[] numToByte(int num) {
        boolean[] outputByte = new boolean[9];
        outputByte[0] = num > 255;
        boolean b7 = num >= 128; num = b7 ? num - 128 : num;
        boolean b6 = num >= 64;  num = b6 ? num - 64 : num;
        boolean b5 = num >= 32;  num = b5 ? num - 32 : num;
        boolean b4 = num >= 16;  num = b4 ? num - 16 : num;
        boolean b3 = num >= 8;   num = b3 ? num - 8 : num;
        boolean b2 = num >= 4;   num = b2 ? num - 4 : num;
        boolean b1 = num >= 2;   num = b1 ? num - 2 : num;
        boolean b0 = num >= 1;   num = b0 ? num - 1 : num;

        outputByte[1] = b7;
        outputByte[2] = b6;
        outputByte[3] = b5;
        outputByte[4] = b4;
        outputByte[5] = b3;
        outputByte[6] = b2;
        outputByte[7] = b1;
        outputByte[8] = b0;

        return outputByte;
    }

    public int byteToNum(boolean[] inputByte) {
        int num = 0;
        if (inputByte[1]) num += 128;
        if (inputByte[2]) num += 64;
        if (inputByte[3]) num += 32;
        if (inputByte[4]) num += 16;
        if (inputByte[5]) num += 8;
        if (inputByte[6]) num += 4;
        if (inputByte[7]) num += 2;
        if (inputByte[8]) num += 1;
        if (inputByte[0]) System.out.println("overflow");
        return num;
    }
    
    public int byteToNumNeg(boolean[] inputByte) {
        int num = 0;
        if (inputByte[1]) num -= 128;
        if (inputByte[2]) num += 64;
        if (inputByte[3]) num += 32;
        if (inputByte[4]) num += 16;
        if (inputByte[5]) num += 8;
        if (inputByte[6]) num += 4;
        if (inputByte[7]) num += 2;
        if (inputByte[8]) num += 1;
        if (inputByte[0]) System.out.println("overflow");
        return num;
    }

    public static void main(String[] args) {
        Artemetics demo = new Artemetics();
        boolean[] result = demo.byteSuber(demo.numToByte(35), demo.numToByte(20));
        System.out.println(demo.byteToNumNeg(result));
    }
}
