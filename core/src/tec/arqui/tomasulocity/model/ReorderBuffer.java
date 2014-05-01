package tec.arqui.tomasulocity.model;

public class ReorderBuffer {

	private ItemReorderBuffer[] mReorderBuffer;
	private ItemReorderBuffer	mHeader;
	private int AvailableBlock;
	private Stack<int> Order;

	
	public static int SIZE_ROD = 5;
	
	public ReorderBuffer {
	
	this.mReorderBuffer = new ItemReorderBuffer[SIZE_ROD];
	this.mHeader = ItemReorderBuffer[0];
	this.AvailableBlock = 0;
	this.Order = 0;
	
	}
	/*
	 * Checks for blocks availability
	 */
	private boolean blockAvailable(){
		for(int i = 0; i < SIZE_ROD; i ++){
			if (ItemReorderBuffer[i] == null){
				AvailableBlock = i;
				return true;
			}
		} else {
			return false;
		}
	}
	
	/*
	 * Add new element
	 */
	public int addElement( ItemReorderBuffer mItem ){
		
		if(!blockAvailable()){
			//Nose
		} else {
			mItem = ItemReorderBuffer[AvailableBlock];
		}
				
		return AvailableBlock;
	}
	
	/*
	 * Checks for data to dispatch
	 */
	public update(){
		
		for (int i = 0; i < SIZE_ROD; i++){
			
			if(mHeader.getValue != null){
				//despachar
			}
		}
	}
	
}
