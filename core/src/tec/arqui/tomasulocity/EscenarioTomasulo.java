package tec.arqui.tomasulocity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private Image arrow1;
	private ArrayList<Image> arrows;
	
	private enum SIDES{
		TOP, BOTTOM, LEFT, RIGHT
	}

	
	@Override
	public void show() {
		
		//Pintores
		mStage = new Stage(new ScreenViewport());
		InputMultiplexer mux = new InputMultiplexer();
		mux.addProcessor(mStage);
		mux.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(mux);
        shapeRenderer=new ShapeRenderer();
        
        //Recursos
		
	    mInstructionsStackTable = new InstructionStackTable();
	    mInstructionsStackTable.setPosition(150,500);
	    
	    mRenamedInstructionsStackTable = new RenamedInstructionStackTable();
	    mRenamedInstructionsStackTable.setPosition(150,250);
	    
	    mPhysicRegistersTable = new PhysicRegistersTable();
	    mPhysicRegistersTable.setPosition(150,-50);
	    
	    mTemporalRegistersTable = new TemporalRegistersTable();
	    mTemporalRegistersTable.setPosition(125,125);
	    
	    mReorderBufferTable = new ReorderBufferTable();
	    mReorderBufferTable.setPosition(600, -50);
	    
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
	    
	    
	    Image arrow1 = new Image(Styles.getInstance().getArrowBottom());
	    Image arrow2 = new Image(Styles.getInstance().getArrowTopRight());
	    Image arrow3 = new Image(Styles.getInstance().getArrowBottomRight());
	    Image arrow4 = new Image(Styles.getInstance().getArrowRight());
	    Image image4 = new Image(Styles.getInstance().getGears());
	    Image image5 = new Image(Styles.getInstance().getGears());
	    Image image6 = new Image(Styles.getInstance().getArrowBottomRight());
	    Image image7 = new Image(Styles.getInstance().getArrowTopRight());

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
	    image4.setPosition(700, 250);
	    image5.setPosition(700, 80);
	    image6.setPosition(850, 275);
	    image7.setPosition(850, 125);
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
	    mStage.addActor(image4);
	    mStage.addActor(image5);
	    mStage.addActor(image6);
	    mStage.addActor(image7);
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

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mStage.act(delta);
        mStage.draw();
        
        
                
        shapeRenderer.end();
	}
	
	private Vector2 getSemiPos(Actor pActor,SIDES pActorSide){
		Vector2 side;
		switch (pActorSide) {
		case TOP:
			side = new Vector2( pActor.getX()+ pActor.getWidth()/2, 
								 pActor.getY() +  pActor.getHeight());
			break;
		case BOTTOM:
			side = new Vector2( pActor.getX()+ pActor.getWidth()/2, 
					 pActor.getY() );
			break;
		case LEFT:
			side = new Vector2( pActor.getOriginX(), 
					 pActor.getOriginY() + pActor.getHeight()/2 );
			break;
		default://RIGHT
			side = new Vector2( pActor.getRight(), 
					 pActor.getOriginY() + pActor.getHeight()/2 );
			break;
		}
		return side;
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
		System.out.println("tap");
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
	
	
	
}
