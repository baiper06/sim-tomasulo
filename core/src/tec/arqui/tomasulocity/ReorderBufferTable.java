package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ReorderBufferTable extends Table{
	
	public final static int ROW_COUNT = 15;
	public ArrayList<Label> mListTagROB;
	public ArrayList<Label> mListTarget;
	public ArrayList<Label> mListValue;
	public ArrayList<Label> mListSource;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public ReorderBufferTable(){
		
		mListTagROB = new ArrayList<Label>();
		mListTarget = new ArrayList<Label>();
		mListValue = new ArrayList<Label>();
		mListSource = new ArrayList<Label>();
		
		Label title = new Label("ROB",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label tagHeader		= new Label("Tag", Styles.getInstance().getGenericTableHeaderStyle());
	    Label targetHeader 	= new Label("Target", Styles.getInstance().getGenericTableHeaderStyle());
	    Label valueHeader	= new Label("Value", Styles.getInstance().getGenericTableHeaderStyle());
	    Label sourceHeader	= new Label("Source", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tagHeader).space(5);
	    this.add(sourceHeader).space(5);
	    this.add(targetHeader).space(5);
	    this.add(valueHeader).space(5);	
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow(row);
	}
	
	private void addRow(int pRow){
		
		this.row();
		
		Label tagField		= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label targetField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label valueField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label sourceField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		
		mListTagROB.add(tagField);
		mListTarget.add(targetField);
		mListValue.add(valueField);
		mListSource.add(sourceField);
		
		this.add(tagField);
		this.add(sourceField);
		this.add(targetField);
		this.add(valueField);
	}
}