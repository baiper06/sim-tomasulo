package tec.arqui.tomasulocity.model;

import tec.arqui.tomasulocity.model.Constants.Operations;

public class Instruction {

	private Operations mOperation;
	private IRegister mSource;
	private IRegister mTarget;
	
	public Operations getOperation() {
		return mOperation;
	}
	public void setOperation(Operations pOperation) {
		this.mOperation = pOperation;
	}
	public IRegister getSource() {
		return mSource;
	}
	public void setSource(IRegister pSource) {
		this.mSource = pSource;
	}
	public IRegister getTarget() {
		return mTarget;
	}
	public void setTarget(IRegister pTarget) {
		this.mTarget = pTarget;
	}
	
}
