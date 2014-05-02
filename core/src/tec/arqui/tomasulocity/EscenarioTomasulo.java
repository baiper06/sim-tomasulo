package tec.arqui.tomasulocity;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tec.arqui.tomasulocity.model.CommonDataBus;
import tec.arqui.tomasulocity.model.Constants;
import tec.arqui.tomasulocity.model.Instruction;
import tec.arqui.tomasulocity.model.ItemReorderBuffer;
import tec.arqui.tomasulocity.model.ItemReservStation;
import tec.arqui.tomasulocity.model.PhysicRegister;
import tec.arqui.tomasulocity.model.PhysicRegistersBank;
import tec.arqui.tomasulocity.model.ReorderBuffer;
import tec.arqui.tomasulocity.model.TempRegister;
import tec.arqui.tomasulocity.model.TempRegistersBank;
import tec.arqui.tomasulocity.model.UFAdder;
import tec.arqui.tomasulocity.model.UFMultiplier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EscenarioTomasulo implements Screen, GestureListener {

	public final static int FUNTIONAL_UNITS_COUNT = 2;
	public final static int VIEWPORT_WIDTH = 1024;
	public final static int VIEWPORT_HEIGHT = 640;
	
	private Stage   mStage;
	private InstructionStackTable  mInstructionsStackTable;
	private RenamedInstructionStackTable  mRenamedInstructionsStackTable;
	private PhysicRegistersTable   mPhysicRegistersTable;
	private TemporalRegistersTable mTemporalRegistersTable;
	private ReorderBufferTable 	   mReorderBufferTable;
	private ReservationStationTable mReservationStationA;
	private ReservationStationTable mReservationStationB;
	private CommonDataBusTable     mCommonDataBusTable;
	private TextButton mPlayButton;
	private TextButton mStepButton;
	private ShapeRenderer shapeRenderer;
	private TomasuloControl mTomasuloControl;
	
	
	@Override
	public void show() {
		
		//Temporal
		//Temporal
		
		//Control
		mTomasuloControl = new TomasuloControl();		
		
		//Pintores
		mStage = new Stage(new ScreenViewport());
		InputMultiplexer mux = new InputMultiplexer();
		mux.addProcessor(mStage);
		mux.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(mux);
        shapeRenderer=new ShapeRenderer();
        
        //Recursos
		
	    mInstructionsStackTable = new InstructionStackTable();
	    mInstructionsStackTable.setPosition(150,600);
	    
	    mRenamedInstructionsStackTable = new RenamedInstructionStackTable();
	    mRenamedInstructionsStackTable.setPosition(150,250);
	    
	    mPhysicRegistersTable = new PhysicRegistersTable();
	    mPhysicRegistersTable.setPosition(150,-100);
	    
	    mTemporalRegistersTable = new TemporalRegistersTable();
	    mTemporalRegistersTable.setPosition(125,80);
	    
	    mReorderBufferTable = new ReorderBufferTable();
	    mReorderBufferTable.setPosition(600, -100);
	    
	    mReservationStationA = new ReservationStationTable("RS A");
	    mReservationStationA.setPosition(550,320);
	    
	    mReservationStationB = new ReservationStationTable("RS B");
	    mReservationStationB.setPosition(550,140);
	    
	    mCommonDataBusTable = new CommonDataBusTable();
	    mCommonDataBusTable.setPosition(960, 220);
	    
	    mPlayButton = new TextButton("Play",Styles.getInstance().getGenericTextButtonStyle());
	    mPlayButton.setPosition(500, 500);
	    mStepButton = new TextButton("Step",Styles.getInstance().getGenericTextButtonStyle());
	    mStepButton.setPosition(600, 500);
	    
	    mStepButton.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
	                        
	                mTomasuloControl.setBlockProgram(mInstructionsStackTable.getInstrucciones());
	                mTomasuloControl.step();
	                
	                //Actualizar Tabla de Renamed
	                for (int i = 0; i < mTomasuloControl.front.getListInstructions().size(); i++){
	                	Instruction instruccion = mTomasuloControl.front.getListInstructions().get(i);
	                	mRenamedInstructionsStackTable.mInstrucciones.get(i).setText(
	                			Mappers.MInverseOperations.get(
	                					instruccion.getOperation()
	                			)
	                	);
	                	mRenamedInstructionsStackTable.mSource1.get(i).setText(
	                			((TempRegister)instruccion.getSource()).getName()
	                	);
	                	mRenamedInstructionsStackTable.mSink.get(i).setText(
	                			((TempRegister)instruccion.getTarget()).getName()
	                	);
	                };
	                
	                //Actualizar Tabla de temporales
	                for ( int i = 0; i < TempRegistersBank.getInstance().getRegisters().length ; i++ ){
	                	TempRegister register = TempRegistersBank.getInstance().getRegisters()[i];
	                	System.out.println(register);
	                	System.out.println(mTemporalRegistersTable.mTempRegisters);
	                	mTemporalRegistersTable.mTempRegisters.get(i).setText(register.getName());
	                	if (register.getPhysicRegister() != null)
	                		mTemporalRegistersTable.mPhysRegisters.get(i).setText(register.getPhysicRegister().getName());
	                	mTemporalRegistersTable.mBusyBit.get(i).setText(Mappers.MBoolean.get(register.isBusyBit()));
	                }
	                
	                //Actualizar Reservation StationA
	                for ( int i = 0; i < UFAdder.getInstance().getSize(); i++){
	                	ItemReservStation item = UFAdder.getInstance().getReservStation()[i];

	                	if (item.getTarget() != null){
		                	mReservationStationA.listTags.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTarget()].getName() );
		                	System.out.println("RS A:"+item.getOperation());
		                	mReservationStationA.listOps.get(i).setText(Mappers.MInverseOperations.get(item.getOperation()));
		                	mReservationStationA.listTag1.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTag1()].getName() );
		                	mReservationStationA.listTag2.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTag2()].getName() );
		                	mReservationStationA.listValue1.get(i).setText( String.valueOf(item.getValue1()) );
		                	mReservationStationA.listValue2.get(i).setText( String.valueOf(item.getValue2()) );
		                	mReservationStationA.listDirtyBit.get(i).setText( Mappers.MBoolean.get(item.isDirty()) );
		                	mReservationStationA.listTagROB.get(i).setText( String.valueOf(item.getTagROB()) );
	                	}
	                }
	                
	                //Actualizar Reservation StationB
	                for ( int i = 0; i < UFMultiplier.getInstance().getSize(); i++){
	                	ItemReservStation item = UFMultiplier.getInstance().getReservStation()[i];
	                	if (item.getTarget() != null){
		                	System.out.println("RS B:"+item.getOperation());
		                	mReservationStationB.listTags.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTarget()].getName() );
		                	mReservationStationB.listOps.get(i).setText(Mappers.MInverseOperations.get(item.getOperation()));
		                	mReservationStationB.listTag1.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTag1()].getName() );
		                	mReservationStationB.listTag2.get(i).setText(
		                			TempRegistersBank.getInstance().getRegisters()[item.getTag2()].getName() );
		                	mReservationStationB.listValue1.get(i).setText( String.valueOf(item.getValue1()) );
		                	mReservationStationB.listValue2.get(i).setText( String.valueOf(item.getValue2()) );
		                	mReservationStationB.listDirtyBit.get(i).setText( Mappers.MBoolean.get(item.isDirty()) );
		                	mReservationStationB.listTagROB.get(i).setText( String.valueOf(item.getTagROB()) );
	                	}
	                }
	                
	                //Registros Físicos
	                for ( int i = 0 ; i < Constants.SIZE_REGISTERS ; i++ ){
	                	PhysicRegister register = PhysicRegistersBank.getInstance().getPhysicRegisters()[i];
	                	mPhysicRegistersTable.mRegisters.get(i).setText(register.getName());
	                	mPhysicRegistersTable.mValues.get(i).setText(String.valueOf(register.getValue()));
	                }
	               
	                //CDB 
	                ItemReservStation cdbItem = CommonDataBus.getInstance().getRegister();
	                if (cdbItem != null && cdbItem.getTarget() != null){
		                mCommonDataBusTable.mRegisters.get(0)
		                	.setText(TempRegistersBank.getInstance().getRegisters()[cdbItem.getTarget()].getName());
		                mCommonDataBusTable.mValues.get(0).setText(String.valueOf(cdbItem.getValue2()));
	                }
	               
	                //ROB
	                int index = 0;
	                Iterator<ItemReorderBuffer> it = ReorderBuffer.getInstance().getReorderBuffer().iterator();
	                while(it.hasNext()){
	                	ItemReorderBuffer item = it.next();
	                	mReorderBufferTable.mListTarget.get(index).setText( 
	                			item.getTarget().getName());
	                	mReorderBufferTable.mListValue.get(index).setText(
	                			String.valueOf(item.getTarget().getValue()) );
	                	mReorderBufferTable.mListReady.get(index).setText(
	                			Mappers.MBoolean.get(item.getValue() != null ) );
	                }
	                
					return true;
	        }
	    });
			    
	    
	   this.generateGUIDetails();
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mStage.act(delta);
	    mStage.draw();                
        shapeRenderer.end();
	}
	

	
	public void mover(Vector2 pInicio, Vector2 pDestino){
		
	}

	@Override
	public void resize(int pWidth, int pHeight) {
		// TODO Auto-generated method stub
//		mCamera = new OrthographicCamera();
//		mCamera.setToOrtho(false, pWidth, pHeight);
//		mCamera.update();
		Gdx.graphics.setDisplayMode(pWidth, pHeight, false);
		mStage.getViewport().update(pWidth,pHeight,false);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touchDown(float pX, float pY, int pPointer, int pButton) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float pX, float pY, int pCount, int pButton) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean longPress(float pX, float pY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float pVelocityX, float pVelocityY, int pButton) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean pan(float pX, float pY, float pDeltaX, float pDeltaY) {
		// TODO Auto-generated method stub
		mStage.getViewport().getCamera().translate(-pDeltaX, 
				pDeltaY, 0);
		System.out.println("pan");
		return false;
	}

	@Override
	public boolean panStop(float pX, float pY, int pPointer, int pButton) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float pInitialDistance, float pDistance) {
		// TODO Auto-generated method stub
		((OrthographicCamera)mStage.getViewport().getCamera()).zoom += (pInitialDistance-pDistance)*0.0001;
		return true;
	}

	@Override
	public boolean pinch(Vector2 pInitialPointer1, Vector2 pInitialPointer2,
			Vector2 pPointer1, Vector2 pPointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void generateGUIDetails() { Image arrow1 = new Image(Styles.getInstance().getArrowBottom());
	    Image arrow2 = new Image(Styles.getInstance().getArrowTopRight());
	    Image arrow3 = new Image(Styles.getInstance().getArrowBottomRight());
	    Image arrow4 = new Image(Styles.getInstance().getArrowRight());
	    Image gearsA = new Image(Styles.getInstance().getGears());
	    Image gearsB = new Image(Styles.getInstance().getGears());
	    Image arrow25 = new Image(Styles.getInstance().getArrowBottomRight());
	    Image arrow26 = new Image(Styles.getInstance().getArrowTopRight());
	
	    Image arrow5 = new Image(Styles.getInstance().getArrowTop());
	    Image arrow6 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow7 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow8 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow9 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow10 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow11 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow12 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow13 = new Image(Styles.getInstance().getArrowBottom());
	    Image arrow14 = new Image(Styles.getInstance().getArrowBottom());
	    //Image arrow15 = new Image(Styles.getInstance().getArrowRight());
	    
	    //Flechas del reorder buffer
	
	    Image arrow16 = new Image(Styles.getInstance().getArrowBottom());
	    Image arrow17 = new Image(Styles.getInstance().getArrowBottom());
	    Image arrow18 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow19 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow20 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow21 = new Image(Styles.getInstance().getArrowLeft());
	    Image arrow22 = new Image(Styles.getInstance().getArrowTopLeft());
	    Image arrow23 = new Image(Styles.getInstance().getArrowTopLeft());
	    Image arrow24 = new Image(Styles.getInstance().getArrowLeft());
	    
	//    
	    arrow1.setPosition(150, 320);
	    arrow2.setPosition(350, 250);
	    arrow3.setPosition(350, 175);
	    arrow4.setPosition(300, 215);
	    gearsA.setPosition(700, 250);
	    gearsB.setPosition(700, 80);
	    arrow25.setPosition(850, 275);
	    arrow26.setPosition(850, 125);
	    arrow5.setPosition(930, 320);
	    arrow6.setPosition(900, 400);
	    arrow7.setPosition(800, 400);
	    arrow8.setPosition(700, 400);
	    arrow9.setPosition(600, 400);
	    arrow10.setPosition(500, 400);
	    arrow11.setPosition(400, 400);
	    arrow12.setPosition(300, 400);
	    arrow13.setPosition(300, 340);
	    arrow14.setPosition(300, 270);
	   // arrow15.setPosition(1050, 220);
	    arrow16.setPosition(930, 50);
	    arrow17.setPosition(930, -50);
	    arrow18.setPosition(820, -75);
	    arrow19.setPosition(720, -75);
	    arrow20.setPosition(420, -75);
	    arrow21.setPosition(320, -75);
	    arrow22.setPosition(420, -25);
	    arrow23.setPosition(360, 40);
	    arrow24.setPosition(280, 60);
	    
	    arrow1.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow1.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow2.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow2.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow3.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow3.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow4.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow4.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow5.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow5.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow6.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow6.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow7.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow7.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow8.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow8.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow9.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow9.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow10.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow10.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow11.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow11.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow12.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow12.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow13.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow13.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow14.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow14.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow16.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow16.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow17.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow17.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow18.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow18.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow19.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow19.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow20.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow20.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow21.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow21.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow22.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow22.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow23.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow23.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow24.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow24.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow25.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow25.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	    arrow26.setOrigin(arrow1.getWidth()/2, arrow1.getHeight()/2);
	    arrow26.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(10,0.1f),Actions.rotateBy(-10,0.1f))));
	
	    gearsA.setOrigin(gearsA.getWidth()/2, gearsA.getHeight()/2);
	    gearsA.addAction(Actions.forever(Actions.rotateBy(1)));
	    
	    gearsB.setOrigin(gearsB.getWidth()/2, gearsB.getHeight()/2);
	    gearsB.addAction(Actions.forever(Actions.rotateBy(1)));
	    
	    mStage.addActor(mInstructionsStackTable);
	    mStage.addActor(mRenamedInstructionsStackTable);
	    mStage.addActor(mPhysicRegistersTable);
	    mStage.addActor(mTemporalRegistersTable);
	    mStage.addActor(mReorderBufferTable);
	    mStage.addActor(mReorderBufferTable);
	    mStage.addActor(mReservationStationA);
	    mStage.addActor(mReservationStationB);
	    mStage.addActor(mCommonDataBusTable);
	    mStage.addActor(mPlayButton);
	    mStage.addActor(mStepButton);
	    mStage.addActor(arrow1);
	    mStage.addActor(arrow2);
	    mStage.addActor(arrow3);
	    mStage.addActor(arrow4);
	    mStage.addActor(gearsA);
	    mStage.addActor(gearsB);
	    mStage.addActor(arrow25);
	    mStage.addActor(arrow26);
	    mStage.addActor(arrow5);
	    mStage.addActor(arrow6);
	    mStage.addActor(arrow7);
	    mStage.addActor(arrow8);
	    mStage.addActor(arrow9);
	    mStage.addActor(arrow10);
	    mStage.addActor(arrow11);
	    mStage.addActor(arrow12);
	    mStage.addActor(arrow13);
	    mStage.addActor(arrow14);
	    //mStage.addActor(arrow15);
	    mStage.addActor(arrow16);
	    mStage.addActor(arrow17);
	    mStage.addActor(arrow18);
	    mStage.addActor(arrow19);
	    mStage.addActor(arrow20);
	    mStage.addActor(arrow21);
	    mStage.addActor(arrow22);
	    mStage.addActor(arrow23);
	    mStage.addActor(arrow24);
		
	}
	
}
