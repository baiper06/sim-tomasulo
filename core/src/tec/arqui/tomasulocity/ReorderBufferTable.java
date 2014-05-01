package tec.arqui.tomasulocity;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ReorderBufferTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public ReorderBufferTable(){
		
		Label title = new Label("ROB",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label tagHeader		= new Label("Tag", Styles.getInstance().getGenericTableHeaderStyle());
	    Label targetHeader 	= new Label("Target", Styles.getInstance().getGenericTableHeaderStyle());
	    Label valueHeader	= new Label("Value", Styles.getInstance().getGenericTableHeaderStyle());
	    Label readyHeader	= new Label("Ready", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tagHeader).space(5);
	    this.add(targetHeader).space(5);
	    this.add(valueHeader).space(5);
	    this.add(readyHeader).space(5);	
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label tagField		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label targetField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label valueField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label readyField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		this.add(tagField);
		this.add(targetField);
		this.add(valueField);
		this.add(readyField);
	}
}