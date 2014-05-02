package tec.arqui.tomasulocity.stage;

import tec.arqui.tomasulocity.model.CommonDataBus;
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
		// Ingresar elemento en la UF
		if( pUF.getItemInExec() == null ){
			ItemReservStation itemRS = pUF.popItemRS();
			if( itemRS != null ){
				pUF.setItemInExec( itemRS );
				pUF.resetTimer();
			}
		//  Despachar elemento
		} else if( pUF.getTimer() == pUF.getTimeExec() ){
			pUF.action();
			//cdb
		//  Ejecutando...
		} else {
			pUF.incrementTimer();
		}
	}
	
	private void toNotify(){
		if(UFAdder.getInstance.isReady()){
			CommonDataBus.getInstance().toNotify(exec(UFAdder.getInstance.action()));
		}
		if(UFMultiplier.getInstance.isReady()){
			CommonDataBus.getInstance().toNotify(exec(UFMultiplier.getInstance.action()));
		}
	}
}
