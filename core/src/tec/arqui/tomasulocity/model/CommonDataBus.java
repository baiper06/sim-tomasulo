package tec.arqui.tomasulocity.model;


public class CommonDataBus {
	
	private ItemReservStation mRS;
	//private static CommonDataBus mInstance;
	
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
			UFAdder.getInstance().popItemRS().setDirty( true );
			mRS = UFAdder.getInstance().getItemInExec();
			TempRegistersBank.getInstance().getRegister( mRS.getTarget() ).setBusyBit( false );
			UFAdder.getInstance().setItemInExec( null );
		} else if( UFMultiplier.getInstance().isReady() ){
			UFMultiplier.getInstance().popItemRS().setDirty( true );
			mRS = UFMultiplier.getInstance().getItemInExec();
			TempRegistersBank.getInstance().getRegister( mRS.getTarget() ).setBusyBit( false );
			UFMultiplier.getInstance().setItemInExec( null );
		}
		return mRS;
	}
	


}
