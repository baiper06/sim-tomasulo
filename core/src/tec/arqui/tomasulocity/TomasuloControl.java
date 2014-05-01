package tec.arqui.tomasulocity;


import java.util.ArrayList;
import java.util.Queue;

import tec.arqui.tomasulocity.model.Instruction;
import tec.arqui.tomasulocity.stage.Backend;
import tec.arqui.tomasulocity.stage.Execute;
import tec.arqui.tomasulocity.stage.Frontend;


public class TomasuloControl {

	Frontend front;
	Execute exec;
	Backend back;
	Queue<Instruction> 		mBlockProgram;
	
	static int clock;
	int mPC;
	

	public TomasuloControl(){
		front = new Frontend();
		exec  = new Execute();
		back  = new Backend();
		
		clock 	= 0;
		mPC 	= 0;

	}
	
	public void step(){	
		if( clock == 0 ){
			front.setListInstructions(mBlockProgram);
		}
		front.run();
		exec.run();
		back.run();
		
		clock ++;
	}

	
	public Queue<Instruction> getBlockProgram() {
		return mBlockProgram;
	}

	public static int getClock() {
		return clock;
	}

	public void setmBlockProgram(Queue<Instruction> pBlockProgram) {
		this.mBlockProgram = pBlockProgram;
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
