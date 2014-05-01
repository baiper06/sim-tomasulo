package tec.arqui.tomasulocity.model;

public class TempRegistersBank {

	private TempRegister[]	mTempRegisters;
	private int mSize;
	
	/*
	 * Singleton
	 */
	private static TempRegistersBank instance = null;
	   
	protected TempRegistersBank() {
		mTempRegisters 	= new TempRegister[Constants.SIZE_TEMP_REGISTERS];
	}
	
	public static TempRegistersBank getInstance() {
      if(instance == null) {
         instance = new TempRegistersBank();
      }
      return instance;
	}
	   
	
	public TempRegistersBank( int pSize ){
		mSize = pSize;
		mTempRegisters 	= new TempRegister[pSize]; 
	}
	
	public void addRegister( TempRegister pReg ){
		for( int i=0; i<mSize; i++ ){
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				mTempRegisters[i] = pReg;
				break;
			} 
		}
	}
	
	public TempRegister getPhysicReg( PhysicRegister pReg ){
		for( int i=0; i<mSize; i++ ){
			if( mTempRegisters[i].getPhysicRegister() == pReg ){
				return mTempRegisters[i];
			}
		}
		return null;
	}
	
	public int getTag( TempRegister pReg ){
		for( int i=0; i<mSize; i++ ){
			if( mTempRegisters[i] == pReg ){
				return i;
			}
		}
		return -1;
	}
}
