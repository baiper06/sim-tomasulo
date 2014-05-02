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
			System.out.println(mRS);
			System.out.println(mRS.getTag2());
			System.out.println(TempRegistersBank.getInstance().getRegister( mRS.getTag2() ));
			TempRegistersBank.getInstance().getRegister( mRS.getTag2() ).setBusyBit( false );
			UFAdder.getInstance().setItemInExec( null );
		} else if( UFMultiplier.getInstance().isReady() ){
			mRS = UFMultiplier.getInstance().getItemInExec();
			TempRegistersBank.getInstance().getRegister( mRS.getTarget() ).setBusyBit( false );
			UFMultiplier.getInstance().setItemInExec( null );
		}
		return mRS;
	}
	


}
