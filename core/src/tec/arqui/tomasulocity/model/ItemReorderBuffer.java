package tec.arqui.tomasulocity.model;

public class ItemReorderBuffer {

	private int 			mTag;
	private PhysicRegister 	mTarget;
	private int 			mValue;
	
	public int getTag() {
		return mTag;
	}
	public void setTag(int pTag) {
		this.mTag = pTag;
	}
	public PhysicRegister getTarget() {
		return mTarget;
	}
	public void setTarget(PhysicRegister pTarget) {
		this.mTarget = pTarget;
	}
	public int getValue() {
		return mValue;
	}
	public void setValue(int pValue) {
		this.mValue = pValue;
	}
	
}
