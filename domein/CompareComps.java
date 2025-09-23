package domein;

import java.util.Iterator;

public class CompareComps {
	private LogicGates lg;
	
	public CompareComps() {
		lg = new LogicGates();
	}
	
	public boolean equal(boolean[] byteOne, boolean[] byteTwo) {
		return lg.notGate(lg.xorGate(byteOne[0], byteOne[0]));
	}
	
	public boolean notEqual(boolean[] byteOne, boolean[] byteTwo) {
		return lg.notGate(equal(byteOne, byteTwo));
	}
	
	public boolean biggerThen(boolean[] byteOne, boolean[] byteTwo) {
		return true;
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
        CompareComps cc = new CompareComps();
        boolean[] results = new boolean[256 * 256];

        int index = 0;

        for (int b = 0; b < 256; b++) {
            for (int a = 0; a < 256; a++) {
                results[index] = (a == b);
                index++;
            }
        }

        boolean test = true;
        for (int b = 0; b < 256; b++) {
            for (int a = 0; a < 256; a++) {
                if (!cc.equal(cc.numToByte(a), cc.numToByte(b))) {
                    test = false;
                    break;
                }
            }
            if (!test) break;
        }

        if (test) {
            System.out.println("The test succeeded");
        } else {
            System.out.println("The test failed");
        }
    }
}















