package tec.arqui.tomasulocity;

import java.util.ArrayList;

import tec.arqui.tomasulocity.model.Constants;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TemporalRegistersTable extends Table{
	
	
	public ArrayList<Label> mTempRegisters;
	public ArrayList<Label> mPhysRegisters;
	public ArrayList<Label> mBusyBit;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public TemporalRegistersTable(){
		
		mTempRegisters = new ArrayList<Label>();
		mPhysRegisters = new ArrayList<Label>();
		mBusyBit = new ArrayList<Label>();
		
		Label title = new Label("Temporals",Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label tempRegisterHeader	= new Label("Reg Temp", Styles.getInstance().getGenericTableHeaderStyle());
	    Label physicalRegisterHeader= new Label("Reg Fisico", Styles.getInstance().getGenericTableHeaderStyle());
	    Label busyBitHeader			= new Label("BB", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tempRegisterHeader).space(5);
	    this.add(physicalRegisterHeader).space(5);
	    this.add(busyBitHeader).space(5);
	    
	    for ( int row=0; row < Constants.SIZE_TEMP_REGISTERS ; row++ )
	    	this.addRow(row);
	}
	
	private void addRow(int pRow){
		
		this.row();
		
		Label tempRegisterField		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label physicalRegisterField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label busyBitField			= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		mTempRegisters.add(tempRegisterField);
		mPhysRegisters.add(physicalRegisterField);
		mBusyBit.add(busyBitField);
		
		this.add(tempRegisterField);
		this.add(physicalRegisterField);
		this.add(busyBitField);
	}
}