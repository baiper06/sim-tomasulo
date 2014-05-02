package tec.arqui.tomasulocity.model;

import tec.arqui.tomasulocity.model.Constants.Operations;

public class ItemReservStation {
	
	public final static int DATA_COUNT = 8;
	private Integer mTarget;
	private Operations mOperation;
	private Integer mTag1;
	private Integer mTag2;
	private Integer mValue1;
	private Integer mValue2;
	private boolean	mDirty;
	private Integer mTagROB; //del reorder buffer
	
	public ItemReservStation(){
		mDirty = true;
	}
	
	public Integer getTarget() {
		return mTarget;
	}
	public Operations getOperation() {
		return mOperation;
	}
	public Integer getTag1() {
		return mTag1;
	}
	public Integer getTag2() {
		return mTag2;
	}
	public Integer getValue1() {
		return mValue1;
	}
	public Integer getValue2() {
		return mValue2;
	}
	public boolean isDirty() {
		return mDirty;
	}
	public Integer getTagROB() {
		return mTagROB;
	}
	public void setTarget(Integer pTarget) {
		this.mTarget = pTarget;
	}
	public void setOperation(Operations pOperation) {
		this.mOperation = pOperation;
	}
	public void setTag1(Integer pTag1) {
		this.mTag1 = pTag1;
	}
	public void setTag2(Integer pTag2) {
		this.mTag2 = pTag2;
	}
	public void setValue1(Integer pValue1) {
		this.mValue1 = pValue1;
	}
	public void setValue2(Integer pValue2) {
		this.mValue2 = pValue2;
	}
	public void setDirty(boolean pDirty) {
		this.mDirty = pDirty;
	}
	public void setTagROB(Integer pTagROB) {
		this.mTagROB = pTagROB;
	}
	
	public boolean[] compare(ItemReservStation objectA, ItemReservStation objectB){
		boolean[] semejanza = new boolean[DATA_COUNT];
		semejanza[0] = objectA.getTarget() == objectB.getTarget();
		semejanza[1] = objectA.getOperation() == objectB.getOperation();
		semejanza[2] = objectA.getTag1() == objectB.getTag1();
		semejanza[3] = objectA.getTag2() == objectB.getTag2();
		semejanza[4] = objectA.getValue1() == objectB.getValue1();
		semejanza[5] = objectA.getValue1() == objectB.getValue2();
		semejanza[6] = objectA.isDirty() == objectB.isDirty();
		semejanza[7] = objectA.getTagROB() == objectB.getTagROB();
		return semejanza;
	}

	@Override
	public String toString() {
		return "ItemReservStation [mTarget=" + mTarget + ", mOperation="
				+ mOperation + ", mTag1=" + mTag1 + ", mTag2=" + mTag2
				+ ", mValue1=" + mValue1 + ", mValue2=" + mValue2 + ", mDirty="
				+ mDirty + ", mTagROB=" + mTagROB + "]";
	}
	
	
	
}
