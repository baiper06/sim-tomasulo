package tec.arqui.tomasulocity.model;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class ReorderBuffer {
	
	public int InstrucRatioDispatch = 0;

	private ArrayBlockingQueue<ItemReorderBuffer> mReorderBuffer;
	
	public static int SIZE_ROD = 15;
	public int mNextTag = 1;
	
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
		return mNextTag++;
	}
	
	/*
	 * Sets the final value & frees the temp regs.
	 */
	public void updateValuesFromCDB(){
		ItemReservStation item = CommonDataBus.getInstance().getRegister();
		if (item != null ){
			Iterator<ItemReorderBuffer> itr = mReorderBuffer.iterator();
			while(itr.hasNext()){
				ItemReorderBuffer elem = itr.next();
				if (item.getTagROB() == elem.getTagROB()){
					elem.setValue(item.getValue2());
					TempRegistersBank.getInstance().freeRegs(elem.getTagROB());
				}
				if (item.getTag1() == elem.getTagROB()){
					//elem.setValue(item.getValue2());
					TempRegistersBank.getInstance().freeRegs(elem.getSourceTag());
				}
			}
		}		
	}
	
	
	/*
	 * Checks for data to dispatch
	 */
	public void update(){		
		if(mReorderBuffer.peek() != null && mReorderBuffer.peek().getValue() != null){			
			ItemReorderBuffer item = mReorderBuffer.poll();
			System.out.println("XXXXXXXXXXXXXXXXXXXX "+ item.toString());
			// escribir el valor en le reg fisico
			item.getTarget().setValue( item.getValue() );
			this.InstrucRatioDispatch++;
		}	
	}

	public ArrayBlockingQueue<ItemReorderBuffer> getReorderBuffer() {
		return mReorderBuffer;
	}	
	
	
}