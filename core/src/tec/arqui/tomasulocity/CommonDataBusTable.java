package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CommonDataBusTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public CommonDataBusTable(){
		
		Label instructionHeader	= new Label("Instruccion", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceOneHeader 	= new Label("Source1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceTwoHeader	= new Label("Source2", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sinkHeader		= new Label("Sink", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(instructionHeader).space(5);
	    this.add(sourceOneHeader).space(5);
	    this.add(sourceTwoHeader).space(5);
	    this.add(sinkHeader).space(5);	
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label instructionField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label sourceOneField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label sourceTwoField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label sinkField			= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(instructionField);
		this.add(sourceOneField);
		this.add(sourceTwoField);
		this.add(sinkField);
	}
}
