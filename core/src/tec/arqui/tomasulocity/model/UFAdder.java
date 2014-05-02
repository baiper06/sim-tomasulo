package tec.arqui.tomasulocity.model;

public class UFAdder extends UnitFunctional {
	
	private final static int TIME_EXEC = 2;
	private final static int SIZE_RS   = 2;
	
	/*
	 * Singleton
	 */
	private static UFAdder instance = null;
	   
	protected UFAdder(){
		super(SIZE_RS, TIME_EXEC);
	}
	
	public static UFAdder getInstance() {
      if(instance == null) {
         instance = new UFAdder();
      }
      return instance;
	}


	public ItemReservStation action(){
		super.action();
		switch( mItemInExec.getOperation()){
			case ADD:
				mItemInExec.setValue2(mItemInExec.getValue1() +  mItemInExec.getValue2() );
					
			case MOVE:	
				mItemInExec.setValue2(mItemInExec.getValue1());
			default:
				break;
		}
		return mItemInExec;
	}

	

}