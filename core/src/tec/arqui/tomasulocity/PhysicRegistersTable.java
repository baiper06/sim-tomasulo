package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class PhysicRegistersTable extends Table{
	
	public final static int ROW_COUNT = 5;
	
	public ArrayList<Label> mRegisters;
	public ArrayList<Label> mValues;
	public ArrayList<TextField> mInitialValues;
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public PhysicRegistersTable(){
		
		mRegisters = new ArrayList<Label>();
		mValues = new ArrayList<Label>();
		mInitialValues = new ArrayList<TextField>();
		
		Label title = new Label("Registers",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label registersHeader = new Label("Register", Styles.getInstance().getGenericTableHeaderStyle());
	    Label valuesHeader = new Label("Value", Styles.getInstance().getGenericTableHeaderStyle());
	    Label initialValueHeader = new Label("Initial", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(registersHeader).space(5);
	    this.add(valuesHeader).space(5);
	    this.add(initialValueHeader).space(5);
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label registersField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		Label valuesOneField	= new Label("-", Styles.getInstance().getGenericTableNormalStyle());
		TextField initialValueField = new TextField("0",Styles.getInstance().getGenericTextFieldStyle());
		initialValueField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
		
		this.mRegisters.add(registersField);
		this.mValues.add(valuesOneField);
		this.mInitialValues.add(initialValueField);
		
		this.add(registersField);
		this.add(valuesOneField);
		this.add(initialValueField).width(50).height(20).padLeft(10).padRight(10);
	}
}