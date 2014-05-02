package tec.arqui.tomasulocity.model;

public abstract class UnitFunctional {
	
	protected ItemReservStation[] mReservStation;
	protected int				mTimeExec;
	protected int 				mSize;
	protected ItemReservStation mItemInExec;
	protected int				mTimer;


	public UnitFunctional( int pSizeRS, int pTimeExec ){
		mSize = pSizeRS;
		mTimeExec = pTimeExec;
		mReservStation 	= new ItemReservStation[pSizeRS];
		mTimer = 0;
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

	public ItemReservStation popItemRS( ){
		for( int i=0; i<mSize; i++ ){
			if( mReservStation[i] != null || ! mReservStation[i].isDirty() ){
				mReservStation[i].setDirty( true );
				return mReservStation[i];
			} 
		}
		return null;
	}

	public ItemReservStation[] getReservStation() {
		return mReservStation;
	}

	public int getTimeExec() {
		return mTimeExec;
	}

	public int getSize() {
		return mSize;
	}
	
	
	public int getTimer() {
		return mTimer;
	}
	
	public void incrementTimer() {
		mTimer++;
	}

	public void resetTimer() {
		this.mTimer = 0;
	}
	
	public ItemReservStation getItemInExec() {
		return mItemInExec;
	}

	public void setItemInExec(ItemReservStation mItemInExec) {
		this.mItemInExec = mItemInExec;
	}


}
