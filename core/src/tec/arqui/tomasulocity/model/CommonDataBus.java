package tec.arqui.tomasulocity.model;


public class CommonDataBus {
	
	private ItemReservStation mRS;
	private static CommonDataBus mInstance;
	
	/*
	 * Singleton 
	 */
	private static CommonDataBus instance = null;
	   
	protected CommonDataBus() {
		mRS = new ItemReservStation();
	}
	
	public static CommonDataBus getInstance() {
		if(instance == null) {
		   instance = new CommonDataBus();
		}
		return instance;
	}
	
	public ItemReservStation getRegister() {
		return mRS;
	}

	public void setRegister(ItemReservStation pRegister) {
		this.mRS = pRegister;
	}
	
	public ItemReservStation popInstrutionToFU(){
		ItemReservStation item = null;
		if( UFAdder.getInstance().isReady() ){
			item = UFAdder.getInstance().getItemInExec();
			UFAdder.getInstance().setItemInExec( null );
		} else if( UFMultiplier.getInstance().isReady() ){
			item = UFMultiplier.getInstance().getItemInExec();
			UFMultiplier.getInstance().setItemInExec( null );
		}
		TempRegistersBank.getInstance().getRegister( item.getTarget() ).setBusyBit( false );
		return item;
	}
	


}
