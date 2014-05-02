package tec.arqui.tomasulocity.model;

public class ItemReorderBuffer {

	private int 			mTagSource;
	private int 			mTagROB;
	private PhysicRegister 	mTarget;
	private Integer			mValue;	
	
	public void setTagROB(int pTag) {
		this.mTagROB = pTag;
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
		return this.mTagSource;
	}
	
	public int getTagROB(){
		return this.mTagROB;
	}
	
	public int setSourceTag(int pSourceTag){
		return this.mTagSource = pSourceTag;
	}
	
	
	@Override
	public String toString() {
		return "ItemReorderBuffer [mTag00=" + mTagSource + ", mTag01=" + mTagROB
				+ ", mTarget=" + mTarget + ", mValue=" + mValue + "]";
	}
}
