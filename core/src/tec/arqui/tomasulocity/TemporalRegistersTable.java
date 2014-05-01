package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TemporalRegistersTable extends Table{
	
	public final static int ROW_COUNT = 8;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public TemporalRegistersTable(){
		
		Label title = new Label("Temporals",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label tempRegisterHeader	= new Label("Reg Temp", Styles.getInstance().getGenericTableHeaderStyle());
	    Label physicalRegisterHeader= new Label("Reg Fisico", Styles.getInstance().getGenericTableHeaderStyle());
	    Label busyBitHeader			= new Label("BB", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tempRegisterHeader).space(5);
	    this.add(physicalRegisterHeader).space(5);
	    this.add(busyBitHeader).space(5);
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow(row);
	}
	
	private void addRow(int pRow){
		
		this.row();
		
		Label tempRegisterField		= new Label("L"+String.valueOf(pRow+1), Styles.getInstance().getGenericTableNormalStyle());
		Label physicalRegisterField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label busyBitField			= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(tempRegisterField);
		this.add(physicalRegisterField);
		this.add(busyBitField);
	}
}