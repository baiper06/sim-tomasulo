package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class InstructionStackTable extends Table{
	
	public final static int ROW_COUNT = 12;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public InstructionStackTable(){
		
		Label title = new Label("Instructions",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title);
	    this.row();
		
		Label instructionHeader	= new Label("Instruction", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceOneHeader 	= new Label("Source1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceTwoHeader	= new Label("Source2", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sinkHeader		= new Label("Sink", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(instructionHeader);
	    this.add(sourceOneHeader);
	    this.add(sourceTwoHeader);
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
		
		this.add(mInstructions);
		this.add(mSourceOne);
		this.add(mSourceTwo);
		this.add(mSink);
	}
	
}
