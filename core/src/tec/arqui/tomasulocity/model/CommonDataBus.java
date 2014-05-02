package tec.arqui.tomasulocity.model;

public class CommonDataBus {
	
	private TempRegister mRegister;
	
	
	/*
	 * Singleton
	 */
	private static CommonDataBus instance = null;
	   
	protected CommonDataBus() {
		
	}
	
	public static CommonDataBus getInstance() {
		if(instance == null) {
		   instance = new CommonDataBus();
		}
		return instance;
	}
	
	public TempRegister getRegister() {
		return mRegister;
	}

	public void setRegister(TempRegister pRegister) {
		this.mRegister = pRegister;
	}
	
	

}
