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

	public void execute(){
		if( mAdder.areYouReady() ){
			mAdder.action();
			mAdder.
		}

		mAdder.
		mMultiplier.action();
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
