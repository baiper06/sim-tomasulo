package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CommonDataBusTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public CommonDataBusTable(){
		
		Label registerHeader	= new Label("Register", Styles.getInstance().getGenericTableHeaderStyle());
	    Label valueHeader 		= new Label("Value", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(registerHeader).space(5);
	    this.add(valueHeader).space(5);
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label registerField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label valueField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(registerField);
		this.add(valueField);
	}
}
