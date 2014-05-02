package tec.arqui.tomasulocity.model;

public class TempRegistersBank {

	private TempRegister[]	mTempRegisters;
	/*
	 * Singleton
	 */
	private static TempRegistersBank instance = null;
	   
	protected TempRegistersBank() {
		mTempRegisters 	= new TempRegister[Constants.SIZE_TEMP_REGISTERS];
		for ( int i=0; i < Constants.SIZE_TEMP_REGISTERS; i++ ){
			mTempRegisters[i] = new TempRegister();
		}
	}
	
	public static TempRegistersBank getInstance() {
      if(instance == null) {
         instance = new TempRegistersBank();
      }
      return instance;
	}

	
	public void addRegister( TempRegister pReg ){
		for( int i=0; i<mTempRegisters.length; i++ ){
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				mTempRegisters[i] = pReg;
				break;
			} 
		}
	}
	
	public TempRegister getTempRegister( PhysicRegister pReg ){
		for( int i=0; i<mTempRegisters.length; i++ ){
			if( mTempRegisters[i].getPhysicRegister() == pReg ){
				return mTempRegisters[i];
			}
		}
		return null;
	}
	
	public int getTag( TempRegister pReg ){
		for( int i=0; i<mTempRegisters.length; i++ ){
			if( mTempRegisters[i] == pReg ){
				return i;
			}
		}
		return -1;
	}
	
	public TempRegister[] getRegisters(){
		return mTempRegisters;
	}
}
