package tec.arqui.tomasulocity.model;

public class TempRegistersBank {

	private TempRegister[]	mTempRegisters;
	private int mHeader;
	
	/*
	 * Singleton
	 */
	private static TempRegistersBank instance = null;
	   
	protected TempRegistersBank() {
		mTempRegisters 	= new TempRegister[Constants.SIZE_TEMP_REGISTERS];
		for ( int i=0; i < Constants.SIZE_TEMP_REGISTERS; i++ ){
			mTempRegisters[i] = new TempRegister();
		}
		mHeader = 0;
	}
	
	public static TempRegistersBank getInstance() {
      if(instance == null) {
         instance = new TempRegistersBank();
      }
      return instance;
	}
	
	public TempRegister[] getRegisters(){
		return mTempRegisters;
	}
	   
	
	
	public int addRegister( TempRegister pReg ){
		int i=mHeader;
		do{
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				mTempRegisters[i] = pReg;
				mHeader = i;
				return i;
			} 
			i++;
			if( i == Constants.SIZE_TEMP_REGISTERS ){
				i = 0;
			}
		}while(i != mHeader);
		return -1;
	}
	
	/*
	 * FREE THE REGS!!!
	 */
	public void freeRegs(int mTag){
		this.mTempRegisters[mTag].setDirty(true);
	}
	
	public TempRegister getTempRegister( PhysicRegister pReg ){
		int i=mHeader;
		do{
			if( mTempRegisters[i] != null && mTempRegisters[i].getPhysicRegister() == pReg && !mTempRegisters[i].isDirty()){
				return mTempRegisters[i];
			} 
			i--;
			if( i == -1 ){
				i = Constants.SIZE_TEMP_REGISTERS-1;
			}
		}while(i != mHeader);

		return null;
	}
	
	public int getTag( TempRegister pReg ){
		for( int i=0; i<Constants.SIZE_TEMP_REGISTERS; i++ ){
			if( mTempRegisters[i] == pReg ){
				return i;
			}
		}
		return -1;
	}
	
	public boolean anyEmptySlots( ){
		for( int i=0; i<Constants.SIZE_TEMP_REGISTERS; i++ ){
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				return true;
			} 
		}
		return false;
	}
}
