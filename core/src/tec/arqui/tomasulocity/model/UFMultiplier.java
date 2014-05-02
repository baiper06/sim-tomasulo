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
	}
	
	public static UFMultiplier getInstance() {
      if(instance == null) {
         instance = new UFMultiplier();
      }
      return instance;
	}

	public ItemReservStation action(){
		super.action();
		switch(mItemInExec.getOperation()){
			case MULT:
				mItemInExec.setValue2(mItemInExec.getValue1() *  mItemInExec.getValue2() );
					
			case DIV:	
				mItemInExec.setValue2(mItemInExec.getValue1() /  mItemInExec.getValue2() );
				
			case SHIFT_L:	
				mItemInExec.setValue2(mItemInExec.getValue1() * 2);
				
			case SHIFT_R:	
				mItemInExec.setValue2(mItemInExec.getValue1() / 2);
				
			default:
				break;
		}
		return mItemInExec;
	}
}