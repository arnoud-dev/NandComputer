package domein;

public class CompareComps {
	private LogicGates lg;
	
	public CompareComps() {
		lg = new LogicGates();
	}
	
	public boolean equal(boolean[] byteOne, boolean[] byteTwo) {
	    boolean e0 = lg.notGate(lg.xorGate(byteOne[0], byteTwo[0]));
	    boolean e1 = lg.notGate(lg.xorGate(byteOne[1], byteTwo[1]));
	    boolean e2 = lg.notGate(lg.xorGate(byteOne[2], byteTwo[2]));
	    boolean e3 = lg.notGate(lg.xorGate(byteOne[3], byteTwo[3]));
	    boolean e4 = lg.notGate(lg.xorGate(byteOne[4], byteTwo[4]));
	    boolean e5 = lg.notGate(lg.xorGate(byteOne[5], byteTwo[5]));
	    boolean e6 = lg.notGate(lg.xorGate(byteOne[6], byteTwo[6]));
	    boolean e7 = lg.notGate(lg.xorGate(byteOne[7], byteTwo[7]));

	    return lg.andGate(
	             lg.andGate(lg.andGate(e0, e1), lg.andGate(e2, e3)),
	             lg.andGate(lg.andGate(e4, e5), lg.andGate(e6, e7))
	           );
	}
	
	public boolean notEqual(boolean[] byteOne, boolean[] byteTwo) {
		return lg.notGate(equal(byteOne, byteTwo));
	}
	
	public boolean greaterThan(boolean[] byteOne, boolean[] byteTwo) {
	    boolean gt7 = lg.andGate(lg.notGate(byteTwo[0]), byteOne[0]);
	    boolean eq7 = lg.notGate(lg.xorGate(byteOne[0], byteTwo[0]));

	    boolean gt6 = lg.andGate(lg.andGate(lg.notGate(byteTwo[1]), byteOne[1]), eq7);
	    boolean eq6 = lg.andGate(lg.notGate(lg.xorGate(byteOne[1], byteTwo[1])), eq7);

	    boolean gt5 = lg.andGate(lg.andGate(lg.notGate(byteTwo[2]), byteOne[2]), eq6);
	    boolean eq5 = lg.andGate(lg.notGate(lg.xorGate(byteOne[2], byteTwo[2])), eq6);

	    boolean gt4 = lg.andGate(lg.andGate(lg.notGate(byteTwo[3]), byteOne[3]), eq5);
	    boolean eq4 = lg.andGate(lg.notGate(lg.xorGate(byteOne[3], byteTwo[3])), eq5);

	    boolean gt3 = lg.andGate(lg.andGate(lg.notGate(byteTwo[4]), byteOne[4]), eq4);
	    boolean eq3 = lg.andGate(lg.notGate(lg.xorGate(byteOne[4], byteTwo[4])), eq4);

	    boolean gt2 = lg.andGate(lg.andGate(lg.notGate(byteTwo[5]), byteOne[5]), eq3);
	    boolean eq2 = lg.andGate(lg.notGate(lg.xorGate(byteOne[5], byteTwo[5])), eq3);

	    boolean gt1 = lg.andGate(lg.andGate(lg.notGate(byteTwo[6]), byteOne[6]), eq2);
	    boolean eq1 = lg.andGate(lg.notGate(lg.xorGate(byteOne[6], byteTwo[6])), eq2);

	    boolean gt0 = lg.andGate(lg.andGate(lg.notGate(byteTwo[7]), byteOne[7]), eq1);

	    return lg.orGate(
	             lg.orGate(lg.orGate(gt7, gt6), lg.orGate(gt5, gt4)),
	             lg.orGate(lg.orGate(gt3, gt2), lg.orGate(gt1, gt0)));
	}
	
	public boolean greaterOrEqualThan(boolean[] byteOne, boolean[] byteTwo) {
		return lg.orGate(equal(byteOne, byteTwo), greaterThan(byteOne, byteTwo));
	}
	
	public boolean lesserThan(boolean[] byteOne, boolean[] byteTwo) {
		return lg.andGate(lesserOrEqualThan(byteOne, byteTwo), notEqual(byteOne, byteTwo));
	}
	
	public boolean lesserOrEqualThan(boolean[] byteOne, boolean[] byteTwo) {
		return lg.notGate(greaterThan(byteOne, byteTwo));
	}
}