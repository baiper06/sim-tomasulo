package tec.arqui.tomasulocity.model;

public class UFMultiplier extends UnitFunctional {

	private final static int TIME_EXEC = 2;
	public static int SIZE_RS = 3;

	/*
	 * Singleton
	 */
	private static UFMultiplier instance = null;
	   
	protected UFMultiplier(){
		super(SIZE_RS, TIME_EXEC);
//		this.mReservStation = ItemReservStation[SIZE_RS];
//		this.mTimeExec = 2;
//		this.mReady = true;
	}
	
	public static UFMultiplier getInstance() {
      if(instance == null) {
         instance = new UFMultiplier();
      }
      return instance;
	}

	
//	public boolean areYouReady(){
//		return this.mReady;
//	}
	
//	public ItemReservStation action(ItemReservStation instruction){
//		int i = 0;
//		this.mReady = false;
//		
//		switch(instruction.mOperation){
//			case 1:	for(int t = 0; t = mTimeExec; t++){
//						instruction.mValue2 = instruction.mValue1 * 
//											  instruction.mValue2;
//					}
//					
//			case 2:	for(int t = 0; t = mTimeExec; t++){
//						instruction.mValue2 = instruction.mValue1 / 
//											  instruction.mValue2;
//					}
//					
//			case 3:	for(int t = 0; t = mTimeExec; t++){
//						instruction.mValue2 = instruction.mValue1 * 2;
//					}
//		}
//		
//		this.mReady = true;;
//		return instruction;
//	}
}