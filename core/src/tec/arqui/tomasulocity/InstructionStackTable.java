package tec.arqui.tomasulocity;

import java.util.ArrayList;

import tec.arqui.tomasulocity.model.Constants;
import tec.arqui.tomasulocity.model.Instruction;
import tec.arqui.tomasulocity.model.PhysicRegistersBank;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class InstructionStackTable extends Table{
	
	public final static int ROW_COUNT = 12;
	
	
	private ArrayList<SelectBox<String>> mSelectBoxesInstrucciones;
	private ArrayList<SelectBox<String>> mSelectBoxesSource1;
	private ArrayList<SelectBox<String>> mSelectBoxesTarget;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public InstructionStackTable(){
		
		mSelectBoxesInstrucciones = new ArrayList<SelectBox<String>>();
		mSelectBoxesSource1 = new ArrayList<SelectBox<String>>();
		mSelectBoxesTarget = new ArrayList<SelectBox<String>>();
		

		
		Label title = new Label("Instructions",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title);
	    this.row();
		
		Label instructionHeader	= new Label("Instruction", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceOneHeader 	= new Label("Source1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sinkHeader		= new Label("Sink", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(instructionHeader);
	    this.add(sourceOneHeader);
	    this.add(sinkHeader);
	    
	   for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	   
	}
	
	private void addRow(){
		
		this.row();
		
		
		Array<String> instructionsItems = new Array<String>();
		instructionsItems.add(" ADD ");
		instructionsItems.add(" MUL ");
		instructionsItems.add(" DIV ");
		instructionsItems.add(" MOVE ");
		instructionsItems.add(" SL ");
		instructionsItems.add(" SR ");
		
		Array<String> registersItems = new Array<String>();
		registersItems.add(" R1 ");
		registersItems.add(" R2 ");
		registersItems.add(" R3 ");
		registersItems.add(" R4 ");
		registersItems.add(" R5 ");

		SelectBox<String> mInstructions = new SelectBox<String>(Styles.getInstance().getGenericSelectBoxStyle());
		mInstructions.setItems(instructionsItems);
		
		SelectBox<String> mSourceOne = new SelectBox<String>(Styles.getInstance().getGenericSelectBoxStyle());
		mSourceOne.setItems(registersItems);
		
		SelectBox<String> mSourceTwo = new SelectBox<String>(Styles.getInstance().getGenericSelectBoxStyle());
		mSourceTwo.setItems(registersItems);
		
		SelectBox<String> mSink = new SelectBox<String>(Styles.getInstance().getGenericSelectBoxStyle());
		mSink.setItems(registersItems);
		
		this.mSelectBoxesInstrucciones.add(mInstructions);
		this.mSelectBoxesSource1.add(mSourceOne);

		this.mSelectBoxesTarget.add(mSink);
		this.add(mInstructions);
		this.add(mSourceOne);
		this.add(mSourceTwo);
		this.add(mSink);
	}
	
	
	public ArrayList<Instruction> getInstrucciones(){
		ArrayList<Instruction> colaInstrucciones = new ArrayList<Instruction>(Constants.SIZE_PROGRAM);
        for ( int i=0 ; i < Constants.SIZE_PROGRAM ; i++ ){
        	
        	Instruction instruction = new Instruction();
        	instruction.setOperation( Mappers.MOperations.get( mSelectBoxesInstrucciones.get(i).getSelected() ) );
        	instruction.setSource( PhysicRegistersBank.getInstance().getPhysicRegisters()[
        	                          mSelectBoxesSource1.get(i).getSelectedIndex()
        	                       ] );
        	instruction.setTarget( PhysicRegistersBank.getInstance().getPhysicRegisters()[
        	                          mSelectBoxesTarget.get(i).getSelectedIndex()
        	                       ] );        	
        	colaInstrucciones.add(instruction);
        	  	
        	
        }
		return colaInstrucciones;
	}
	

}
