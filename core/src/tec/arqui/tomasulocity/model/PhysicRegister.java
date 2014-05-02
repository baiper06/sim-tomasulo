package tec.arqui.tomasulocity.model;

public class PhysicRegister implements IRegister {
	
	public PhysicRegister(int pValue){
		mValue = pValue;
	}
	
	private int mValue;

	public int getValue() {
		return mValue;
	}

	public void setValue(int pValue) {
		this.mValue = pValue;
	}
	
	
	public String getName(){
		int tag = PhysicRegistersBank.getInstance().getTag(this);
		if ( tag != -1){
			return "R"+String.valueOf(tag+1);
		}
		return  null;
	}
}
