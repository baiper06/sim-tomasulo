package tec.arqui.tomasulocity.model;

public class PhysicRegistersBank {

	private PhysicRegister[]	mPhysicRegisters;
	private int mSize;
	
	/*
	 * Singleton
	 */
	private static PhysicRegistersBank instance = null;
	   
	protected PhysicRegistersBank() {
		mPhysicRegisters 	= new PhysicRegister[Constants.SIZE_REGISTERS]; 
	}
	
	public static PhysicRegistersBank getInstance() {
      if(instance == null) {
         instance = new PhysicRegistersBank();
      }
      return instance;
	}
	 
}
