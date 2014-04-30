package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TemporalRegistersTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public TemporalRegistersTable(){
		
		Label tempRegisterHeader	= new Label("Reg Temp", Styles.getInstance().getGenericTableHeaderStyle());
	    Label physicalRegisterHeader= new Label("Reg Fisico", Styles.getInstance().getGenericTableHeaderStyle());
	    Label busyBitHeader			= new Label("BB", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tempRegisterHeader).space(5);
	    this.add(physicalRegisterHeader).space(5);
	    this.add(busyBitHeader).space(5);
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label tempRegisterField		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label physicalRegisterField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label busyBitField			= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(tempRegisterField);
		this.add(physicalRegisterField);
		this.add(busyBitField);
	}
}