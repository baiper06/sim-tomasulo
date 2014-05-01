//package tec.arqui.tomasulocity;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import tec.arqui.tomasulocity.model.CommonDataBus;
//import tec.arqui.tomasulocity.model.Instruction;
//import tec.arqui.tomasulocity.model.PhysicRegister;
//import tec.arqui.tomasulocity.model.ReorderBuffer;
//import tec.arqui.tomasulocity.model.TempRegister;
//import tec.arqui.tomasulocity.model.UFAdder;
//import tec.arqui.tomasulocity.model.UFMultiplier;
//
//
//public class TomasuloControl {
//	
//	
//
//	
//	private ArrayList<Instruction> 		mPage;
//	private CommonDataBus				mCDB;
//	private PhysicRegister[] 			mPhysicRegisters;
//	private TempRegister[]	 			mTempRegisters;
//	private ReorderBuffer	 			mReorderBuffer;
//	private UFAdder 		mAdder;
//	private UFMultiplier 	mMultiplier;
//	
//	Instruction nextToExec = null;
//	ArrayList<Instruction> execList;
//	ArrayList<Instruction> wbList;
//	
//	static int clock;
//	int pc;
//	
//	public static int SIZE_REGISTERS = 16;
//	public static int SIZE_PAGE = 3;
//	
//	public TomasuloControl(){
//		mPhysicRegisters 	= new PhysicRegister[SIZE_REGISTERS]; 
//		mAdder 				= new UFAdder();
//		mMultiplier 		= new UFMultiplier();
//		mPage 				= new ArrayList<Instruction>();
//		execList = new ArrayList<Instruction>();	
//		wbList = new ArrayList<Instruction>();
//		clock = 0;
//		pc = 0;
//
//		// cargar instrucciones
//		
//	}
//	
//	public void step(){
//		clock ++;
//		if (clock <= SIZE_PAGE){
//			issue();
//		}
//			
//		execute();
//		rob();
//		
//		if (nextToExec != null){
//			execList.add(nextToExec);
//		}
//	}
//	
//	public void issue(){
//		
//		// decoder
//		Instruction inst = mPage.get(pc);
//		nextToExec = null;
//		
//		if (op.equals("ADDD") || op.equals("SUBD")){
//
//			inst.time = 2;
//			
//			int station = schedule(Global.A);
//			inst.result = station;
//			
//			register.setStation(des, station);
//			rs.setBusy(Global.getID(station));
//			
//			int src1Station = register.getStation(src1);
//			int src2Station = register.getStation(src2);
//			if (src1Station == -1) {
//				rs.setData1(src1, register.read(src1));
//			} else {
//				rs.setStation1(src1, src1Station);
//			}
//			if (src2Station == -1) {
//				rs.setData1(src2, register.read(src2));
//			} else {
//				rs.setStation1(src2, src2Station);
//			}
//			
//			pc ++;
//			
//		} else if (op.equals("MULD") || op.equals("DIVD")){
//			inst.op = op.equals("MULD")? Global.MULD: Global.DIVD;
//			inst.time = op.equals("MULD")? 10: 40;
//			des = Global.getInt(s[1]);
//			src1 = Global.getInt(s[2]);
//			src2 = Global.getInt(s[3]);
//			inst.src1 = src1;
//			inst.src2 = src2;
//			inst.des = des;
//			
//			int station = schedule(Global.M);
//			
//			if (station == -1){
//				return;
//			}		
//			inst.result = station;
//			
//			register.setStation(des, station);
//			rs.setBusy(Global.getID(station));
//			
//			int src1Station = register.getStation(src1);
//			int src2Station = register.getStation(src2);
//			if (src1Station == -1) {
//				rs.setData1(src1, register.read(src1));
//			} else {
//				rs.setStation1(src1, src1Station);
//			}
//			if (src2Station == -1) {
//				rs.setData1(src2, register.read(src2));
//			} else {
//				rs.setStation1(src2, src2Station);
//			}
//			
//			pc++;
//				
//		} 
//		
//		nextToExec = inst;
//		
//		inst.issue = clock;
//		System.out.println(clock +": " +inst.name);
//		
//		
//	}
//	
//	public void execute(){
//		for (InstructionItem inst: execList){
//			switch (inst.op) {
//			case Global.ADDD:
//				if (register.getStation(inst.src1) == -1 && 
//					register.getStation(inst.src2) == -1){
//						
//					inst.time--;
//					if (inst.time == 0){
//						inst.result = register.read(inst.src1) + 
//									  register.read(inst.src2);
//						wbList.add(inst);
//					}
//				} 
//			case Global.SUBD:
//			case Global.MULD:
//			case Global.DIVD:
//				if (register.getStation(inst.src1) == -1 && 
//					register.getStation(inst.src2) == -1){
//						
//					inst.time--;
//				} 
//				
//				
//				break;
//
//			default:
//				break;
//			}
//			if (inst.time == 0){
//				
//			}
//		}
//	}
//	
//	public void rob(){
//		
//	}
//	
//	public int schedule(int type){
//		int i = 0;
//		switch (type){			
//		case Global.A:
//			for (i=Global._A1; i<=Global._A3; i++){
//				if ( !rs.isBusy(i)){
//					return i+14;
//				}
//			}
//			break;
//			
//		case Global.M:
//			for (i=Global._M1; i<=Global._M2; i++){
//				if ( !rs.isBusy(i)){
//					return i+14;
//				}
//			}
//			break;
//		}
//		
//		return -1;	
//	}
//	/*
//	public static void main(String []args){
//		Tomasulo t = new Tomasulo();
//		while (clock < 100){
//			t.step();
//		}
//	}
//	*/
//
//}
