package tec.arqui.tomasulocity.model;


public class PreviousState {
	
	private static PreviousState mInstance;
	
	private ItemReservStation mPrevRsA;
	private ItemReservStation mPrevRsB;
	private ItemReservStation mActRsA;
	private ItemReservStation mActRsB;
	//private ReorderBuffer     mPrevROB;
	//private ReorderBuffer     mActROB;
	
	private PreviousState(){
		
	}
	
	public static PreviousState getInstance(){
		return mInstance;
	}

	public ItemReservStation getPrevRsA() {
		return mPrevRsA;
	}

	public void setPrevRsA(ItemReservStation pPrevRsA) {
		mPrevRsA = pPrevRsA;
	}

	public ItemReservStation getPrevRsB() {
		return mPrevRsB;
	}

	public void setPrevRsB(ItemReservStation pPrevRsB) {
		mPrevRsB = pPrevRsB;
	}

	public ItemReservStation getActRsA() {
		return mActRsA;
	}

	public void setActRsA(ItemReservStation pActRsA) {
		mActRsA = pActRsA;
	}

	public ItemReservStation getActRsB() {
		return mActRsB;
	}

	public void setActRsB(ItemReservStation pActRsB) {
		mActRsB = pActRsB;
	}
	
	
	
}
