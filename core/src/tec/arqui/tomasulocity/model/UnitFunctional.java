package tec.arqui.tomasulocity.model;

import java.util.Arrays;

public abstract class UnitFunctional {
	
	protected ItemReservStation[] mReservStation;
	protected int				mTimeExec;
	protected int 				mSize;
	protected ItemReservStation mItemInExec;
	protected int				mTimer;
	protected boolean			mReady;
	protected boolean			mExecuting;

	protected UnitFunctional( int pSizeRS, int pTimeExec ){
		mSize = pSizeRS;
		System.out.println("SizeUF:"+ mSize);
		mTimeExec = pTimeExec;
		mReservStation 	= new ItemReservStation[pSizeRS];
		mTimer = 1;
		mReady = false;
		mExecuting = false;
	}
	
	public int anyEmptySlotsInRS( ){
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
	public void updateRS (){
		ItemReservStation pItem = CommonDataBus.getInstance().getRegister();
		if( pItem != null ){ 
			for(int i = 0; i < this.mSize; i++){
				if(mReservStation[i] != null && pItem.getTarget() == mReservStation[i].getTag1()){
					mReservStation[i].setValue1(pItem.getValue2());
				}
				if(mReservStation[i] != null && pItem.getTarget() == mReservStation[i].getTag2()){
					mReservStation[i].setValue2(pItem.getValue2());
				}
			}
			if (mItemInExec != null && pItem.getTarget() == mItemInExec.getTag1()){
				mItemInExec.setValue1(pItem.getValue2());
			}
			if (mItemInExec != null && pItem.getTarget() == mItemInExec.getTag2()){
				mItemInExec.setValue2(pItem.getValue2());
			}
		}
		
	}

	public ItemReservStation popItemRS( ){
		for( int i=0; i<mSize; i++ ){
			//System.out.println("###############################################");
			for (TempRegister reg : TempRegistersBank.getInstance().getRegisters())
				System.out.println(reg.toString());				
			//System.out.println("-----------------------------------------------");
			//System.out.println("popItemsRS: " + mReservStation[i].toString());
			if( mReservStation[i] != null && !mReservStation[i].isDirty()  ){
				System.out.println("popItemsRS1: " + mReservStation[i]);
				if( !TempRegistersBank.getInstance().getRegister( mReservStation[i].getTag1() ).isBusyBit() &&
					!TempRegistersBank.getInstance().getRegister( mReservStation[i].getTag2() ).isBusyBit()){
					System.out.println("popItemsRS2: " + mReservStation[i]);
					//mReservStation[i].setDirty( true );
					return mReservStation[i];
				}	
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
		this.mTimer = 1;
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


	public boolean isExecuting() {
		return mExecuting;
	}

	public void setExecuting(boolean pExecuting) {
		mExecuting = pExecuting;
	}

	
	@Override
	public String toString() {
		return "UnitFunctional [mReservStation="
				+ Arrays.toString(mReservStation) + ", mTimeExec=" + mTimeExec
				+ ", mSize=" + mSize + ", mItemInExec=" + mItemInExec
				+ ", mTimer=" + mTimer + ", mReady=" + mReady + "]";
	}
	
	


}
