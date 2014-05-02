package tec.arqui.tomasulocity.model;

public class ItemReorderBuffer {

	private int 			mTag00;
	private int 			mTag01;
	private PhysicRegister 	mTarget;
	private int 			mValue;	
	
	public void setTargetTag(int pTag) {
		this.mTag01 = pTag;
	}
	public PhysicRegister getTarget() {
		return mTarget;
	}
	public void setTarget(PhysicRegister pTarget) {
		this.mTarget = pTarget;
	}
	public Integer getValue() {
		return mValue;
	}
	public void setValue(int pValue) {
		this.mValue = pValue;
	}
	
	public int getSourceTag(){
		return this.mTag00;
	}
	
	public int getTargetTag(){
		return this.mTag01;
	}
}
