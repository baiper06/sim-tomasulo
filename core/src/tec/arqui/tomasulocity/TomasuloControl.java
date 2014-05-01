package tec.arqui.tomasulocity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import tec.arqui.tomasulocity.model.CommonDataBus;
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


public class TomasuloControl {
	
	private ArrayList<Instruction> 		mPage;
	private CommonDataBus				mCDB;
	private PhysicRegister[] 			mPhysicRegisters;
	private TempRegistersBank 			mTempRegisters;
	private ReorderBuffer	 			mReorderBuffer;
	private UFAdder 		mAdder;
	private UFMultiplier 	mMultiplier;
	
	ArrayList<Instruction> execList;
	ArrayList<Instruction> wbList;
	
	static int clock;
	int mPC;
	

	
	public TomasuloControl(){
		mPhysicRegisters 	= new PhysicRegister[Constants.SIZE_REGISTERS]; 
		mTempRegisters 		= new TempRegistersBank(Constants.SIZE_TEMP_REGISTERS);
		mAdder 				= new UFAdder();
		mMultiplier 		= new UFMultiplier();
		mPage 				= new ArrayList<Instruction>();
		execList = new ArrayList<Instruction>();	
		wbList = new ArrayList<Instruction>();
		clock = 0;
		mPC = 0;

		// cargar instrucciones
		
	}
	
	public void step(){
		clock ++;
		if (clock <= Constants.SIZE_PAGE){  //  <-- revisar
			for( int i=0; i<Constants.SIZE_PAGE; i++){
				issue();
			}
		}
			
		execute();
		rob();
	}
	
	public void issue(){
		
		/*
		 *  decoder
		 */
		Instruction inst = mPage.get(mPC);
		
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
	
	public void execute(){
		for (InstructionItem inst: execList){
			switch (inst.op) {
			case Global.ADDD:
				if (register.getStation(inst.src1) == -1 && 
					register.getStation(inst.src2) == -1){
						
					inst.time--;
					if (inst.time == 0){
						inst.result = register.read(inst.src1) + 
									  register.read(inst.src2);
						wbList.add(inst);
					}
				} 
			case Global.SUBD:
			case Global.MULD:
			case Global.DIVD:
				if (register.getStation(inst.src1) == -1 && 
					register.getStation(inst.src2) == -1){
						
					inst.time--;
				} 
				
				
				break;

			default:
				break;
			}
			if (inst.time == 0){
				
			}
		}
	}
	
	public void rob(){
		
	}
	
	/*
	public static void main(String []args){
		Tomasulo t = new Tomasulo();
		while (clock < 100){
			t.step();
		}
	}
	*/

}
