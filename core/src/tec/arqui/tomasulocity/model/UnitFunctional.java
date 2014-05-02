package tec.arqui.tomasulocity.model;

public abstract class UnitFunctional {
	
	protected ItemReservStation[] mReservStation;
	protected int				mTimeExec;
	protected int 				mSize;
	protected ItemReservStation mItemInExec;
	protected int				mTimer;
	protected boolean			mReady;

	protected UnitFunctional( int pSizeRS, int pTimeExec ){
		mSize = pSizeRS;
		mTimeExec = pTimeExec;
		mReservStation 	= new ItemReservStation[pSizeRS];
		mTimer = 0;
		for ( int i=0; i < mSize; i++ ){
			mReservStation[i] = new ItemReservStation();
		}
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
	
	/*
	 * Actualiza datos de los RS por medio del CDB.
	 */
	public void updateRS (ItemReservStation pItem){
		for(int i = 0; i < this.mSize; i++){
			if (pItem.getTag1() == mReservStation[i].getTag1()){
				mReservStation[i].setValue1(pItem.getValue1());
			}
			if (pItem.getTag2() == mReservStation[i].getTag2()){
				mReservStation[i].setValue2(pItem.getValue2());
			}
		}
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

	public boolean isReady() {
		return mReady;
	}

	public void setReady(boolean mReady) {
		this.mReady = mReady;
	}


	public ItemReservStation action() {
		return null;
	}


}
