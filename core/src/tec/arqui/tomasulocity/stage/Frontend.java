package tec.arqui.tomasulocity.stage;

import java.util.ArrayList;

import tec.arqui.tomasulocity.model.Constants;
import tec.arqui.tomasulocity.model.Instruction;
import tec.arqui.tomasulocity.model.ItemReorderBuffer;
import tec.arqui.tomasulocity.model.ItemReservStation;
import tec.arqui.tomasulocity.model.PhysicRegister;
import tec.arqui.tomasulocity.model.ReorderBuffer;
import tec.arqui.tomasulocity.model.TempRegister;
import tec.arqui.tomasulocity.model.TempRegistersBank;
import tec.arqui.tomasulocity.model.UFAdder;
import tec.arqui.tomasulocity.model.UFMultiplier;
import tec.arqui.tomasulocity.model.UnitFunctional;

public class Frontend {
	
	int mPC;
	private ArrayList<Instruction> 	mListInstructions;
	private ArrayList<Instruction> 	mBlockProgram;
	
	public Frontend(){
		mListInstructions = new ArrayList<Instruction>(Constants.SIZE_PAGE);
	}
	
	public void run(){
		mListInstructions.clear();
		for( int i=0; i<Constants.SIZE_PAGE; i++){
			/*
			 *  decoder
			 */
			Instruction inst = mBlockProgram.get(0);
			mBlockProgram.remove(0);
			mListInstructions.add(inst);
			
			issue(inst);
			
		}
	}
	
	private void issue( Instruction pInst ){

		/*
		 * renaming
		 */
		PhysicRegister PhysicRegTarget = (PhysicRegister) pInst.getTarget();
		PhysicRegister PhysicRegSource = (PhysicRegister) pInst.getSource();
		
		// renaming source
		TempRegister reg = TempRegistersBank.getInstance().getTempRegister( PhysicRegSource );
		TempRegister tempRegSource;
		if( reg  == null || reg.isBusyBit() == false ){
			tempRegSource = new TempRegister();
			tempRegSource.setPhysicRegister( PhysicRegSource );
			tempRegSource.setBusyBit( false );
			tempRegSource.setDirty( false );
			TempRegistersBank.getInstance().addRegister(tempRegSource);
		} else {
			tempRegSource = reg;
		}

		// renaming target
		
		TempRegister tempRegTarget = new TempRegister();
		tempRegTarget.setPhysicRegister( PhysicRegTarget );
		tempRegTarget.setBusyBit( true );
		tempRegTarget.setDirty( false );
		TempRegistersBank.getInstance().addRegister(tempRegTarget);
		
		// set new registers
		pInst.setSource(tempRegSource);
		pInst.setTarget(tempRegTarget);

		
		
		/*
		 * dispatch
		 */
		UnitFunctional fu;
		if( ReorderBuffer.getInstance().blockAvailable() ){
			if( pInst.getOperation() == Constants.Operations.ADD ||  pInst.getOperation() == Constants.Operations.MOVE ){
				fu = UFAdder.getInstance();
			} else {
				fu = UFMultiplier.getInstance();
			}
			int pos = fu.anySlotEmptyInRS( );
			if( pos != -1 ){
				
				ItemReorderBuffer rob = new ItemReorderBuffer();
				rob.setTarget( PhysicRegTarget );
				int tagROB = ReorderBuffer.getInstance().addElement(rob);
				
				ItemReservStation rs = new ItemReservStation();
				rs.setOperation( pInst.getOperation() );
				rs.setDirty(false);
				rs.setTag1( TempRegistersBank.getInstance().getTag(tempRegSource) );
				rs.setTag2( TempRegistersBank.getInstance().getTag(tempRegTarget) );
				rs.setTagROB( tagROB );
				rs.setTarget( TempRegistersBank.getInstance().getTag(tempRegTarget) );
				rs.setValue1( PhysicRegSource.getValue() );
				rs.setValue2( PhysicRegTarget.getValue() );
				fu.addRS(rs, pos );
				
				mPC++;
			}
		}
		
	}
	
	public ArrayList<Instruction> getListInstructions() {
		return mListInstructions;
	}
	
	public void setBlockProgram(ArrayList<Instruction> pBlockProgram){
		this.mBlockProgram = pBlockProgram;
	}
	
	public ArrayList<Instruction> getBlockProgram(){
		return mBlockProgram;
	}
}
