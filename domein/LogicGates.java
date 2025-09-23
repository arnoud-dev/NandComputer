package domein;

public class LogicGates {
	
	public boolean nandGate(boolean a, boolean b) {
		return !(a && b);
	}
	
	public boolean notGate(boolean a) {
		return nandGate(a, a);
	}
	
	public boolean andGate(boolean a, boolean b) {
		return notGate(nandGate(a, b));
	}
	
	public boolean orGate(boolean a, boolean b) {
		return nandGate(notGate(a), notGate(b));
	}
	
	public boolean xorGate(boolean a, boolean b) {
		return notGate(orGate(andGate(a, b), notGate(orGate(a, b))));
	}
	
    public boolean byteAndGate(boolean[] byteOne, boolean[] byteTwo) {
    	return andGate(andGate(andGate(
    				   andGate(byteOne[0], byteTwo[0]),
    				   andGate(byteOne[1], byteTwo[1])),
    		   andGate(andGate(byteOne[2], byteTwo[2]),
    				   andGate(byteOne[3], byteTwo[3]))),     	
    		   andGate(andGate(
    				   andGate(byteOne[4], byteTwo[4]),
    				   andGate(byteOne[5], byteTwo[5])),
    		   andGate(andGate(byteOne[6], byteTwo[6]),
    				   andGate(byteOne[7], byteTwo[7]))));
    }
    
    public boolean byteOrGate(boolean[] byteOne, boolean[] byteTwo) {
    	return orGate(orGate(orGate(
    				   orGate(byteOne[0], byteTwo[0]),
    				   orGate(byteOne[1], byteTwo[1])),
    		   orGate(orGate(byteOne[2], byteTwo[2]),
    				   orGate(byteOne[3], byteTwo[3]))),     	
    		   orGate(orGate(
    				   orGate(byteOne[4], byteTwo[4]),
    				   orGate(byteOne[5], byteTwo[5])),
    		   orGate(orGate(byteOne[6], byteTwo[6]),
    				   orGate(byteOne[7], byteTwo[7]))));
    }
}
