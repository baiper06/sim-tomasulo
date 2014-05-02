package tec.arqui.tomasulocity.stage;

import tec.arqui.tomasulocity.model.CommonDataBus;
import tec.arqui.tomasulocity.model.ItemReservStation;
import tec.arqui.tomasulocity.model.UFAdder;
import tec.arqui.tomasulocity.model.UFMultiplier;
import tec.arqui.tomasulocity.model.UnitFunctional;

public class Execute {

	public void run(){
		
		UFAdder.getInstance().updateRS();
		UFMultiplier.getInstance().updateRS();
		
		CommonDataBus.getInstance().popInstrutionToFU();

		exec( UFAdder.getInstance() );
		exec( UFMultiplier.getInstance() );
		
		/*
		System.out.println( "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Execute.run>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for ( ItemReservStation itemRS : UFAdder.getInstance().getReservStation() ){
			if (itemRS != null)
				System.out.println(itemRS.toString());
			else
				System.out.println("null");
		}
		System.out.println( "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Execute.run.end>>>>>>>>>>>>>>>>>>>>>>>>>>");
		*/
	}
	
	
	private  void exec(UnitFunctional pUF){
		// Ingresar elemento en la UF
		if( pUF.getItemInExec() == null ){
			ItemReservStation itemRS = pUF.popItemRS();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX++++XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  " + itemRS);
			if( itemRS != null ){
				System.out.println( "00000000000000000000000000000000000000000000000000000000000000000000000000000000");
				System.out.println("Executing...."+ itemRS.toString());
				pUF.setItemInExec( itemRS );
				pUF.resetTimer();
				pUF.setExecuting(true);
			}
			pUF.setReady( false );
		//  Despachar elemento
		} else if( pUF.getTimer() == pUF.getTimeExec()-1 ){
			pUF.action();
			pUF.setReady( true );
			pUF.setExecuting(false);
		//  Ejecutando...
		} else {
			pUF.incrementTimer();
		}
	}
}
