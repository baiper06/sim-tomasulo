package tec.arqui.tomasulocity.model;

import tec.arqui.tomasulocity.model.Constants.Operations;

public class ItemReservStation {
	
	private int mTarget;
	private Operations mOperation;
	private int mTag1;
	private int mTag2;
	private int mValue1;
	private int mValue2;
	private boolean	mDirty;
	private int mTagROB; //del reorder buffer
	
	public int getTarget() {
		return mTarget;
	}
	public Operations getOperation() {
		return mOperation;
	}
	public int getTag1() {
		return mTag1;
	}
	public int getTag2() {
		return mTag2;
	}
	public int getValue1() {
		return mValue1;
	}
	public int getValue2() {
		return mValue2;
	}
	public boolean isDirty() {
		return mDirty;
	}
	public int getTagROB() {
		return mTagROB;
	}
	public void setTarget(int pTarget) {
		this.mTarget = pTarget;
	}
	public void setOperation(Operations pOperation) {
		this.mOperation = pOperation;
	}
	public void setTag1(int pTag1) {
		this.mTag1 = pTag1;
	}
	public void setTag2(int pTag2) {
		this.mTag2 = pTag2;
	}
	public void setValue1(int pValue1) {
		this.mValue1 = pValue1;
	}
	public void setValue2(int pValue2) {
		this.mValue2 = pValue2;
	}
	public void setDirty(boolean pDirty) {
		this.mDirty = pDirty;
	}
	public void setTagROB(int pTagROB) {
		this.mTagROB = pTagROB;
	}
	
}
