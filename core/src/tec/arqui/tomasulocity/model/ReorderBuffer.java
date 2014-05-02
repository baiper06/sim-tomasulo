package tec.arqui.tomasulocity.model;

import java.util.concurrent.ArrayBlockingQueue;

public class ReorderBuffer {

	private ArrayBlockingQueue<ItemReorderBuffer> mReorderBuffer;
	
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
		this.mReorderBuffer = new ArrayBlockingQueue<ItemReorderBuffer> 
								  (SIZE_ROD);
		
	}
	
	/*
	 * Checks for blocks availability
	 */
	public boolean blockAvailable(){
		if(this.mReorderBuffer.remainingCapacity() > 0){
			return true;
		} 
		return false;
	}
	
	/*
	 * Add new element
	 */
	public void addElement( ItemReorderBuffer mItem ){
		mReorderBuffer.add(mItem);
	}
	/*
	 * Set value to header
	 */
	public void setValue(int pValue){
		mReorderBuffer.peek().setValue(pValue);
	}
	
	/*
	 * Checks for data to dispatch
	 */
	public void update(){		
		if(mReorderBuffer.peek().getValue() != null){			
			ItemReorderBuffer item = mReorderBuffer.poll();
			item.getTarget().setValue( item.getValue() );
		}	
	}	
}