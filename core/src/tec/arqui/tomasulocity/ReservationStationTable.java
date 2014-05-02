package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ReservationStationTable extends Table{

	public final static int ROW_COUNT = 3;
	
	public ArrayList<Label> listTags;
	public ArrayList<Label> listOps;
	public ArrayList<Label> listTag1;
	public ArrayList<Label> listTag2;
	public ArrayList<Label> listValue1;
	public ArrayList<Label> listValue2;
	public ArrayList<Label> listDirtyBit;
	public ArrayList<Label> listTagROB;
	
	/**
	 * Tabla Gráfica para desplegar el stack inicial de instrucciones
	 */
	public ReservationStationTable(String pName){
		
		listTags = new ArrayList<Label>();
		listOps = new ArrayList<Label>();
		listTag1 = new ArrayList<Label>();
		listTag2 = new ArrayList<Label>();
		listValue1 = new ArrayList<Label>();
		listValue2 = new ArrayList<Label>();
		listDirtyBit = new ArrayList<Label>();
		listTagROB = new ArrayList<Label>();
		
		Label title = new Label(pName,Styles.getInstance().getGenericTableNormalStyle());
	    this.add(title).space(2);
	    this.row();
		
		Label tagHeader			= new Label("Tag", Styles.getInstance().getGenericTableHeaderStyle());
	    Label opHeader		 	= new Label("Op", Styles.getInstance().getGenericTableHeaderStyle());
	    Label tag1Header		= new Label("Tag1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label tag2Header		= new Label("Tag2", Styles.getInstance().getGenericTableHeaderStyle());
	    Label value1Header		= new Label("Value1", Styles.getInstance().getGenericTableHeaderStyle());
	    Label value2Header 		= new Label("Value2", Styles.getInstance().getGenericTableHeaderStyle());
	    Label busybitHeader		= new Label("BB", Styles.getInstance().getGenericTableHeaderStyle());
	    Label tagROBHeader		= new Label("TagRB", Styles.getInstance().getGenericTableHeaderStyle());
	    
	    this.add(tagHeader).space(2);
	    this.add(opHeader).space(2);
	    this.add(tag1Header).space(2);
	    this.add(tag2Header).space(2);
	    this.add(value1Header).space(2);
	    this.add(value2Header).space(2);
	    this.add(busybitHeader).space(2);
	    this.add(tagROBHeader).space(2);	
	    
	    for ( int row=0; row < ROW_COUNT ; row++ )
	    	this.addRow();
	}
	
	private void addRow(){
		
		this.row();
		
		Label tagField		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label opField		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label tag1Field		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label tag2Field		= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label value1Field	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label value2Field	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label busybitField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		Label tagROBField	= new Label(".", Styles.getInstance().getGenericTableNormalStyle());
		
		listTags.add(tagField);
		listOps.add(opField);
		listTag1.add(tag1Field);
		listTag2.add(tag2Field);
		listValue1.add(value1Field);
		listValue2.add(value2Field);
		listDirtyBit.add(busybitField);
		listTagROB.add(tagROBField);
		
		this.add(tagField);
		this.add(opField);
		this.add(tag1Field);
		this.add(tag2Field);
		this.add(value1Field);
		this.add(value2Field);
		this.add(busybitField);
		this.add(tagROBField);		
	}
	
}
