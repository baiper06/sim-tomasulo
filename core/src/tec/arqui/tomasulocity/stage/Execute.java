package tec.arqui.tomasulocity.stage;

import tec.arqui.tomasulocity.model.ItemReservStation;
import tec.arqui.tomasulocity.model.UFAdder;
import tec.arqui.tomasulocity.model.UFMultiplier;
import tec.arqui.tomasulocity.model.UnitFunctional;

public class Execute {

	public void run(){
		exec( UFAdder.getInstance() );
		exec( UFMultiplier.getInstance() );
	}
	
	private  void exec(UnitFunctional pUF){
		if( pUF.getItemInExec() == null ){
			ItemReservStation itemRS = pUF.popItemRS();
			if( itemRS != null ){
				pUF.setItemInExec( itemRS );
			}
		}
		
		if( pUF.getTimer() == pUF.getTimeExec() ){
			//pUF.action();
			pUF.setItemInExec(null);
			// cdb
		} else {
			pUF.incrementTimer();
		}
	}
}
