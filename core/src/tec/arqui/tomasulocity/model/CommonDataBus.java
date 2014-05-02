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
		mRS = null;
		if( UFAdder.getInstance().isReady() ){
			mRS = UFAdder.getInstance().getItemInExec();
			UFAdder.getInstance().setItemInExec( null );
		} else if( UFMultiplier.getInstance().isReady() ){
			mRS = UFMultiplier.getInstance().getItemInExec();
			UFMultiplier.getInstance().setItemInExec( null );
		}
		TempRegistersBank.getInstance().getRegister( item.getTarget() ).setBusyBit( false );
		return mRS;
	}
	


}
