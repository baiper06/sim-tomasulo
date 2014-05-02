package tec.arqui.tomasulocity.model;


public class TempRegister implements IRegister {

	private PhysicRegister 	mPhysicRegister;
	private boolean 		mBusyBit;
	private boolean			mDirty;

	public TempRegister(){
		mBusyBit = false;
		mDirty = true;
	}
	
	public PhysicRegister getPhysicRegister() {
		return mPhysicRegister;
	}
	public void setPhysicRegister(PhysicRegister iRegister) {
		this.mPhysicRegister = iRegister;
	}
	public boolean isBusyBit() {
		return mBusyBit;
	}
	public void setBusyBit(boolean pBusyBit) {
		this.mBusyBit = pBusyBit;
	}
	public boolean isDirty() {
		return mDirty;
	}
	public void setDirty(boolean pDirty) {
		this.mDirty = pDirty;
<<<<<<< HEAD
	}	
=======
	}
	
	public String getName(){
		int tag = TempRegistersBank.getInstance().getTag(this);
		System.out.println("TempTag:"+String.valueOf(tag));
		if (tag != -1 ){
			return "L"+String.valueOf(tag+1);
		}
		return "None";
		
	}
>>>>>>> a9be1c5b782add2168dc2174583f2886d4d07f4f
}
