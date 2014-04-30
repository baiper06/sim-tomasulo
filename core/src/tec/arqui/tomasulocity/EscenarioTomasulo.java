package tec.arqui.tomasulocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EscenarioTomasulo implements Screen {

	public final static int FUNTIONAL_UNITS_COUNT = 2;
	public final static int VIEWPORT_WIDTH = 1024;
	public final static int VIEWPORT_HEIGHT = 640;
	
	private Stage   mStage;
	private InstructionStackTable  mInstructionsStackTable;
	private InstructionStackTable  mRenamedInstructionsStackTable;
	private PhysicRegistersTable   mPhysicRegistersTable;
	private TemporalRegistersTable mTemporalRegistersTable;
	private ReorderBufferTable 	   mReorderBufferTable;
	private ReservationStationTable mReservationStationA;
	private ReservationStationTable mReservationStationB;
	private CommonDataBusTable     mCommonDataBusTable;

	
	@Override
	public void show() {
		
		//Pintores
		mStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(mStage);
        
        //Recursos
		
	    mInstructionsStackTable = new InstructionStackTable();
	    mInstructionsStackTable.setFillParent(true);
	    mInstructionsStackTable.setPosition(-900,120);
	    
	    mRenamedInstructionsStackTable = new InstructionStackTable();
	    mRenamedInstructionsStackTable.setFillParent(true);
	    mRenamedInstructionsStackTable.setPosition(-900,-90);
	    
	    mPhysicRegistersTable = new PhysicRegistersTable();
	    mPhysicRegistersTable.setFillParent(true);
	    mPhysicRegistersTable.setPosition(-600,-360);
	    
	    mTemporalRegistersTable = new TemporalRegistersTable();
	    mTemporalRegistersTable.setFillParent(true);
	    mTemporalRegistersTable.setPosition(-200,-360);
	    
	    mReorderBufferTable = new ReorderBufferTable();
	    mReorderBufferTable.setFillParent(true);
	    mReorderBufferTable.setPosition(120, -120);
	    
	    mReservationStationA = new ReservationStationTable();
	    mReservationStationA.setFillParent(true);
	    mReservationStationA.setPosition(-500,0);
	    
	    mReservationStationB = new ReservationStationTable();
	    mReservationStationB.setFillParent(true);
	    mReservationStationB.setPosition(-500,-160);
	    
	    mCommonDataBusTable = new CommonDataBusTable();
	    mCommonDataBusTable.setFillParent(true);
	    mCommonDataBusTable.setPosition(-50, -120);
	    
	    mStage.addActor(mInstructionsStackTable);
	    mStage.addActor(mRenamedInstructionsStackTable);
	    mStage.addActor(mPhysicRegistersTable);
	    mStage.addActor(mTemporalRegistersTable);
	    mStage.addActor(mReorderBufferTable);
	    mStage.addActor(mReorderBufferTable);
	    mStage.addActor(mReservationStationA);
	    mStage.addActor(mReservationStationB);
	    mStage.addActor(mCommonDataBusTable);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mStage.act(delta);
        mStage.draw();
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
	
}
