package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ReorderBufferTable extends Table{
	
	public final static int ROW_COUNT = 15;
	public ArrayList<Label> mListTarget;
	public ArrayList<Label> mListValue;
	public ArrayList<Label> mListReady;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public ReorderBufferTable(){
		
		mListTarget = new ArrayList<Label>();
		mListValue = new ArrayList<Label>();
		mListReady = new ArrayList<Label>();
		
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
	    	this.addRow(row);
	}
	
	private void addRow(int pRow){
		
		this.row();
		
		Label tagField		= new Label("P"+String.valueOf(pRow+1), Styles.getInstance().getGenericTableNormalStyle());
		Label targetField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label valueField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label readyField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		
		mListTarget.add(targetField);
		mListValue.add(valueField);
		mListReady.add(readyField);
		
		this.add(tagField);
		this.add(targetField);
		this.add(valueField);
		this.add(readyField);
	}
}