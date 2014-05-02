package tec.arqui.tomasulocity;

import java.util.HashMap;

import tec.arqui.tomasulocity.model.Constants.Operations;

public class Mappers {
	
	public final static HashMap<String,Operations> MOperations = new HashMap<String, Operations>();
	public final static HashMap<Operations,String> MInverseOperations = new HashMap<Operations, String>();
	public final static HashMap<Boolean, String> MBoolean = new HashMap<Boolean, String>();

	
	static{		
		MOperations.put(" ADD ", Operations.ADD);
		MOperations.put(" MUL ", Operations.MULT);
		MOperations.put(" DIV ", Operations.DIV);
		MOperations.put(" MOVE ", Operations.MOVE);
		MOperations.put(" SL ", Operations.SHIFT_L);
		MOperations.put(" SR ", Operations.SHIFT_R);

		MInverseOperations.put(Operations.ADD," ADD ");
		MInverseOperations.put(Operations.MULT," MUL ");
		MInverseOperations.put(Operations.DIV," DIV ");
		MInverseOperations.put(Operations.MOVE," MOVE ");
		MInverseOperations.put(Operations.SHIFT_L," SL ");
		MInverseOperations.put(Operations.SHIFT_R," SR ");
		
		MBoolean.put(true, "1");
		MBoolean.put(false, "0");
		
	}

}
