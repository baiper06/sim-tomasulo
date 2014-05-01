package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class RenamedInstructionStackTable extends Table{
		
	public final static int ROW_COUNT = 3;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public RenamedInstructionStackTable(){
		
		Label title = new Label("Renamed",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label instructionHeader	= new Label("Instruccion", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceOneHeader 	= new Label("Source1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceTwoHeader	= new Label("Source2", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sinkHeader		= new Label("Sink", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(instructionHeader).space(2);
	    this.add(sourceOneHeader).space(2);
	    this.add(sourceTwoHeader).space(2);
	    this.add(sinkHeader).space(2);
	    
	   for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();

	}
	
	private void addRow(){
		
		this.row();
		
		Label instructionField	= new Label("ADD", Styles.getInstance().getGenericTableNormalStyle());
		Label sourceOneField	= new Label("L1", Styles.getInstance().getGenericTableNormalStyle());
		Label sourceTwoField	= new Label("L2", Styles.getInstance().getGenericTableNormalStyle());
		Label sinkField			= new Label("L3", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(instructionField);
		this.add(sourceOneField);
		this.add(sourceTwoField);
		this.add(sinkField);
	}
		
	

}
