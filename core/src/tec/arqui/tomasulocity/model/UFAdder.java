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


	public ItemReservStation action(ItemReservStation instruction){
		
		switch(instruction.getOperation()){
			case ADD:
				instruction.setValue2(instruction.getValue1() +  instruction.getValue2() );
					
			case MOVE:	
				instruction.setValue2(instruction.getValue1());
			default:
				break;
		}
		
		return instruction;
	}

	

}