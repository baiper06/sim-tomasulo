package tec.arqui.tomasulocity.model;

public class TempRegistersBank {

	private TempRegister[]	mTempRegisters;
	private int mSize;
	
	public TempRegistersBank( int pSize ){
		mSize = pSize;
		mTempRegisters 	= new TempRegister[pSize]; 
	}
	
	public void add( TempRegister pReg ){
		for( int i=0; i<mSize; i++ ){
			if( mTempRegisters[i] == null || mTempRegisters[i].isDirty() ){
				mTempRegisters[i] = pReg;
				break;
			} 
		}
	}
	
	public TempRegister get( PhysicRegister pReg ){
		for( int i=0; i<mSize; i++ ){
			if( mTempRegisters[i].getPhysicRegister() == pReg ){
				return mTempRegisters[i];
			}
		}
		return null;
	}
}
