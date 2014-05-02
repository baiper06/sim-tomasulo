package tec.arqui.tomasulocity.model;

public class PhysicRegistersBank {

	private PhysicRegister[]	mPhysicRegisters;
	
	/*
	 * Singleton
	 */
	private static PhysicRegistersBank instance = null;
	   
	protected PhysicRegistersBank() {
		mPhysicRegisters 	= new PhysicRegister[Constants.SIZE_REGISTERS]; 
		for ( int i=0; i < Constants.SIZE_REGISTERS; i++ ){
			mPhysicRegisters[i] = new PhysicRegister(0);
		}
	}
	
	public static PhysicRegistersBank getInstance() {
      if(instance == null) {
         instance = new PhysicRegistersBank();
      }
      return instance;
	}
<<<<<<< HEAD
=======
	
	public PhysicRegister[] getPhysicRegisters(){
		return mPhysicRegisters;
	}
	 
	public int getTag( PhysicRegister pReg ){
		for( int i=0; i<mPhysicRegisters.length; i++ ){
			if( mPhysicRegisters[i] == pReg ){
				return i;
			}
		}
		return -1;
	}
>>>>>>> a9be1c5b782add2168dc2174583f2886d4d07f4f
}
