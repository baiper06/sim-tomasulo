package tec.arqui.tomasulocity.stage;

import java.util.ArrayList;

import tec.arqui.tomasulocity.model.Constants;
import tec.arqui.tomasulocity.model.Instruction;
import tec.arqui.tomasulocity.model.ItemReorderBuffer;
import tec.arqui.tomasulocity.model.ItemReservStation;
import tec.arqui.tomasulocity.model.PhysicRegister;
import tec.arqui.tomasulocity.model.TempRegister;
import tec.arqui.tomasulocity.model.UnitFunctional;

public class Frontend {
	
	int mPC;
	private ArrayList<Instruction> 		mListInstructions;
	
	public void issue(){
		
		/*
		 *  decoder
		 */
		Instruction inst = mListInstructions.get(mPC);
		
		/*
		 * renaming
		 */
		PhysicRegister PhysicRegTarget = (PhysicRegister) inst.getTarget();
		PhysicRegister PhysicRegSource = (PhysicRegister) inst.getSource();
		// renaming target
		TempRegister tempRegTarget = new TempRegister();
		tempRegTarget.setPhysicRegister( PhysicRegTarget );
		tempRegTarget.setBusyBit( true );
		tempRegTarget.setDirty( false );
		mTempRegisters.addRegister(tempRegTarget);
		
		// renaming source
		TempRegister reg = mTempRegisters.getPhysicReg( PhysicRegSource );
		TempRegister tempRegSource;
		if( reg  == null || reg.isBusyBit() == false ){
			tempRegSource = new TempRegister();
			tempRegSource.setPhysicRegister( PhysicRegSource );
			tempRegSource.setBusyBit( false );
			tempRegSource.setDirty( false );
			mTempRegisters.addRegister(tempRegSource);
		} else {
			tempRegSource = reg;
		}
		
		// set new registers
		inst.setSource(tempRegTarget);
		inst.setTarget(tempRegSource);

		
		
		/*
		 * dispatch
		 */
		UnitFunctional fu;
		if( mReorderBuffer.blockAvailable() ){
			if( inst.getOperation() == Constants.Operations.ADD ||  inst.getOperation() == Constants.Operations.MOVE ){
				fu = mAdder;
			} else {
				fu = mMultiplier;
			}
			int pos = fu.anySlotEmptyInRS( );
			if( pos != -1 ){
				
				ItemReorderBuffer rob = new ItemReorderBuffer();
				rob.setTarget( PhysicRegTarget );
				int tagROB = mReorderBuffer.addElement(rob);
				
				ItemReservStation rs = new ItemReservStation();
				rs.setOperation( inst.getOperation() );
				rs.setDirty(false);
				rs.setTag1( mTempRegisters.getTag(tempRegSource) );
				rs.setTag2( mTempRegisters.getTag(tempRegTarget) );
				rs.setTagROB( tagROB );
				rs.setTarget( mTempRegisters.getTag(tempRegTarget) );
				rs.setValue1( PhysicRegSource.getValue() );
				rs.setValue2( PhysicRegTarget.getValue() );
				fu.addRS(rs, pos );
				
				mPC++;
			}
		}
		
		execList.add(inst);
		
	}
}
