package tec.arqui.tomasulocity.model;

public class Instruction {

	private int mOperation;
	private IRegister mSource;
	private IRegister mTarget;
	
	public int getOperation() {
		return mOperation;
	}
	public void setOperation(int pOperation) {
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
