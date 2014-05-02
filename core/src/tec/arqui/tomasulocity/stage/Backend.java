package tec.arqui.tomasulocity.stage;

import tec.arqui.tomasulocity.model.ReorderBuffer;

public class Backend {

	public void run(){
		ReorderBuffer.getInstance().updateValuesFromCDB();
		ReorderBuffer.getInstance().update();
	}
}
