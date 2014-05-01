package tec.arqui.tomasulocity.model;

public abstract class UnitFunctional {
	
	protected ItemReservStation[] mReservStation;
	protected int	mTimeExec;
	protected int mSize;
	
	public UnitFunctional( int pSizeRS, int pTimeExec ){
		mSize = pSizeRS;
		mTimeExec = pTimeExec;
		mReservStation 	= new ItemReservStation[pSizeRS]; 
	}
	
	public int anySlotEmptyInRS( ){
		for( int i=0; i<mSize; i++ ){
			if( mReservStation[i] == null || mReservStation[i].isDirty() ){
				return i;
			} 
		}
		return -1;
	}
	
	public void addRS( ItemReservStation pItem, int pPosition ){
		mReservStation[pPosition] = pItem;
	}
	
	public void exec(){
		
	}
}
