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
			
			// si aun no quedan instrucciones en el programa y hay registros temp
			if( mBlockProgram.size() != 0 						&&
				TempRegistersBank.getInstance().anyEmptySlots() &&	
				ReorderBuffer.getInstance().blockAvailable()  ){
				
					Instruction inst = mBlockProgram.get(0);
					UnitFunctional fu;
					if( inst.getOperation() == Constants.Operations.ADD ||  inst.getOperation() == Constants.Operations.MOVE ){
						fu = UFAdder.getInstance();
					} else {
						fu = UFMultiplier.getInstance();
					}
					
					// si hay espacio en el RS
					if( fu.anyEmptySlotsInRS() != -1 ){
						mBlockProgram.remove(0);
						mListInstructions.add(inst);
						issue(inst);
					} else {
						break;
					}
					
			} else {
				break;
			}
		}
	}
	
	private void issue( Instruction pInst ){

		/*
		 * renaming
		 */
		PhysicRegister PhysicRegTarget = (PhysicRegister) pInst.getTarget();
		PhysicRegister PhysicRegSource = (PhysicRegister) pInst.getSource();
		
		// renaming source 1
		TempRegister reg1 = TempRegistersBank.getInstance().getTempRegister( PhysicRegSource );
		TempRegister tempRegSource1;
		if( reg1  == null || reg1.isBusyBit() == false ){
			tempRegSource1 = new TempRegister();
			tempRegSource1.setPhysicRegister( PhysicRegSource );
			tempRegSource1.setBusyBit( false );
			tempRegSource1.setDirty( false );
			TempRegistersBank.getInstance().addRegister(tempRegSource1);
		} else {
			tempRegSource1 = reg1;
		}
		
		// renaming source 2
		TempRegister reg2 = TempRegistersBank.getInstance().getTempRegister( PhysicRegSource );
		TempRegister tempRegSource2;
		if( reg2  == null || reg2.isBusyBit() == false ){
			tempRegSource2 = new TempRegister();
			tempRegSource2.setPhysicRegister( PhysicRegSource );
			tempRegSource2.setBusyBit( false );
			tempRegSource2.setDirty( false );
			TempRegistersBank.getInstance().addRegister(tempRegSource2);
		} else {
			tempRegSource2 = reg2;
		}

		// renaming target
		TempRegister tempRegTarget = new TempRegister();
		tempRegTarget.setPhysicRegister( PhysicRegTarget );
		tempRegTarget.setBusyBit( true );
		tempRegTarget.setDirty( false );
		TempRegistersBank.getInstance().addRegister(tempRegTarget);
		
		// set new registers
		pInst.setSource(tempRegSource1);
		pInst.setTarget(tempRegTarget);

		
		/*
		 * dispatch
		 */
		UnitFunctional fu;
		if( pInst.getOperation() == Constants.Operations.ADD ||  pInst.getOperation() == Constants.Operations.MOVE ){
			fu = UFAdder.getInstance();
		} else {
			fu = UFMultiplier.getInstance();
		}
				
		ItemReorderBuffer rob = new ItemReorderBuffer();
		rob.setSourceTag(TempRegistersBank.getInstance().getTag(tempRegSource1));
		rob.setTarget( PhysicRegTarget );
		int tagROB = ReorderBuffer.getInstance().addElement(rob);
		rob.setTagROB(tagROB);
		
		ItemReservStation rs = new ItemReservStation();
		rs.setOperation( pInst.getOperation() );
		rs.setDirty(false);
		rs.setTag1( TempRegistersBank.getInstance().getTag(tempRegSource1) );
		rs.setTag2( TempRegistersBank.getInstance().getTag(tempRegSource2) );
		rs.setTagROB( tagROB );
		rs.setTarget( TempRegistersBank.getInstance().getTag(tempRegTarget) );
		rs.setValue1( PhysicRegSource.getValue() );
		rs.setValue2( PhysicRegTarget.getValue() );
		fu.addRS(rs, fu.anyEmptySlotsInRS() );	
		
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
