package tec.arqui.tomasulocity.model;

public class TempRegistersBank {

	private TempRegister[]	mTempRegisters;
	private int mSize;
	private int mHeader;
	
	/*
	 * Singleton
	 */
	private static TempRegistersBank instance = null;
	   
	protected TempRegistersBank() {
		mTempRegisters 	= new TempRegister[Constants.SIZE_TEMP_REGISTERS];
		mHeader = 0;
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
	
	public int addRegister( TempRegister pReg ){
		int i=mHeader;
		do{
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				mTempRegisters[i] = pReg;
				mHeader = i;
				return i;
			} 
			i++;
			if( i == mSize ){
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
			if( mTempRegisters[i].getPhysicRegister() == pReg && !mTempRegisters[i].isDirty()){
				return mTempRegisters[i];
			} 
			i--;
			if( i == -1 ){
				i = mSize-1;
			}
		}while(i != mHeader);

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
