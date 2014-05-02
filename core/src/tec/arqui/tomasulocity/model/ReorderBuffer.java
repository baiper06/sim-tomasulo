package tec.arqui.tomasulocity.model;

import java.util.Iterator;
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
	public int addElement( ItemReorderBuffer mItem ){
		mReorderBuffer.add(mItem);
		return mReorderBuffer.size();
	}
	
	/*
	 * Sets the final value 
	 */
	public void updateROB(ItemReservStation pItem){
		Iterator<ItemReorderBuffer> itr = mReorderBuffer.iterator();
		while(itr.hasNext()){
			ItemReorderBuffer elem = itr.next();
			if (pItem.getTagROB() == elem.getTag()){
				elem.setValue(pItem.getValue2());
			}
		}
	}
	
	/*
	 * Set value to header
	 */
	public void setValue (int pValue){
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