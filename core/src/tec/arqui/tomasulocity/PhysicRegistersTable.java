package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PhysicRegistersTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	public ArrayList<Label> mRegisters;
	public ArrayList<Label> mValues;
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public PhysicRegistersTable(){
		
		mRegisters = new ArrayList<Label>();
		mValues = new ArrayList<Label>();
		
		Label title = new Label("Registers",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label registersHeader	= new Label("Register", Styles.getInstance().getGenericTableHeaderStyle());
	    Label valuesHeader 	= new Label("Value", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(registersHeader).space(5);
	    this.add(valuesHeader).space(5);
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label registersField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label valuesOneField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		
		this.mRegisters.add(registersField);
		this.mValues.add(valuesOneField);
		
		this.add(registersField);
		this.add(valuesOneField);
	}
}