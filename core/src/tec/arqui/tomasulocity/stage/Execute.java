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
		
		ItemReservStation item = CommonDataBus.getInstance().popInstrutionToFU();
		if (item != null){
			UFAdder.getInstance().updateRS(item);
			UFMultiplier.getInstance().updateRS(item);
		}
	}
	
	
	private  void exec(UnitFunctional pUF){
		// Ingresar elemento en la UF
		if( pUF.getItemInExec() == null ){
			ItemReservStation itemRS = pUF.popItemRS();
			if( itemRS != null ){
				System.out.println("Executing...."+ itemRS.toString());
				pUF.setItemInExec( itemRS );
				pUF.setReady( false );
				pUF.resetTimer();
			}
		//  Despachar elemento
		} else if( pUF.getTimer() == pUF.getTimeExec()-1 ){
			pUF.action();
			pUF.setReady( true );
		//  Ejecutando...
		} else {
			pUF.incrementTimer();
		}
	}
}
