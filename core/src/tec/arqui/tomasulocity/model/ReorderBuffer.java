package tec.arqui.tomasulocity.model;

public class ReorderBuffer {

	private ItemReorderBuffer[] mReorderBuffer;
	private ItemReorderBuffer mHeader;
	private int AvailableBlock;
	private int Order;
	
	public static int SIZE_ROD = 15;
	
	/*
	 * Singleton
	 */
	private static ReorderBuffer instance = null;

	
	public static ReorderBuffer getInstance() {
      if(instance == null) {
         instance = new ReorderBuffer();
      }
      return instance;
	}
	
	
	protected ReorderBuffer() {
	
		this.mReorderBuffer = new ItemReorderBuffer[SIZE_ROD];
		this.mHeader = mReorderBuffer[0];
		this.AvailableBlock = 0;
		this.Order = 0;
	
	}
	/*
	 * Checks for blocks availability
	 */
	public boolean blockAvailable(){
		for(int i = 0; i < SIZE_ROD; i ++){
			if (mReorderBuffer[i] == null){
				AvailableBlock = i;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Add new element
	 */
	public int addElement( ItemReorderBuffer mItem ){
		
		if(!blockAvailable()){
			return -1;
		} else {
			mItem = mReorderBuffer[AvailableBlock];
		}
				
		return AvailableBlock;
	}
	
	/*
	 * Checks for data to dispatch
	 */
	public ItemReorderBuffer update(){
		// TODO: revisar
		if(this.mHeader.getValue() != null){
			if( (Order + 1) < 5){
				mHeader = mReorderBuffer[Order + 1];
			}
		} 
		return mHeader;
	}
	
}
