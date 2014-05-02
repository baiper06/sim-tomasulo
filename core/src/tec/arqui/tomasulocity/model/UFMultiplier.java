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

	public ItemReservStation action(ItemReservStation instruction){
		switch(instruction.getOperation()){
			case MULT:
				instruction.setValue2(instruction.getValue1() *  instruction.getValue2() );
					
			case DIV:	
				instruction.setValue2(instruction.getValue1() /  instruction.getValue2() );
				
			case SHIFT_L:	
				instruction.setValue2(instruction.getValue1() * 2);
				
			case SHIFT_R:	
				instruction.setValue2(instruction.getValue1() / 2);
				
			default:
				break;
		}
		return instruction;
	}
}