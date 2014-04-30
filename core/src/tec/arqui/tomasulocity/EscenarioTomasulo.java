package tec.arqui.tomasulocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class EscenarioTomasulo implements Screen {

	private SpriteBatch batch;
	private Skin mSkin;	
	
	public final static String TEXTURA_BANCO 		= "bank";
	public final static String TEXTURA_FABRICA 		= "fabrica";
	public final static String TEXTURA_CASA 		= "house";
	public final static String TEXTURA_BIBLIOTECA 	= "library";
	public final static String TEXTURA_HOTEL 		= "office";
	public final static String TEXTURA_ALIEN 		= "alien";
	public final static String TEXTURA_AUTO 		= "car";
	public final static int    SCREEN_WIDTH 		= 1024;
	public final static int    SCREEN_HEIGHT 		= 600;
	public final static int    CANTIDAD_CASAS 		= 5;
	public final static int    CANTIDAD_HOTELES 	= 5;
	public final static int    CANTIDAD_BANCOS 		= 5;
	public final static int    ESCALA_CUADRICULA 	= 50;
	public final static Vector2 CASA_POSICION_INICIO 	= new Vector2(1,2).scl(ESCALA_CUADRICULA);
	public final static Vector2 CASA_POSICION_OFFSET 	= new Vector2(0,1).scl(ESCALA_CUADRICULA);
	public final static Vector2 HOTEL_POSICION_INICIO 	= new Vector2(6,1).scl(ESCALA_CUADRICULA);
	public final static Vector2 HOTEL_POSICION_OFFSET 	= new Vector2(0,1).scl(ESCALA_CUADRICULA);
	public final static Vector2 BANCO_POSICION 			= new Vector2(10,1).scl(ESCALA_CUADRICULA);
	public final static Vector2 BIBLIOTECA_POSICION 	= new Vector2(10,2).scl(ESCALA_CUADRICULA);
	public final static Vector2 FABRICA_POSICION 		= new Vector2(10,2).scl(ESCALA_CUADRICULA);
	public final static Vector2 POSICION_INICIAL_AUTO 	= new Vector2(0,0).scl(ESCALA_CUADRICULA);
	
	private Actor   mActor;
	private Stage   mStage;
	private OrthographicCamera mCamera;
	
	private class AutoActor extends Actor{
        @Override
        public void draw(Batch batch, float alpha){
        	super.draw(batch, alpha);
            batch.draw(mSkin.getRegion(TEXTURA_AUTO),this.getX(),this.getY());
        }
	}
	
	@Override
	public void show() {
		//Fuentes
		
		//Pintores
		mStage = new Stage();
		batch = new SpriteBatch();
        Gdx.input.setInputProcessor(mStage);
        
        //Recursos
		TextureAtlas atlas = new TextureAtlas("edificios.pack");
		mSkin = new Skin(atlas);
		
		//Auto
		mActor = new AutoActor();
		mActor.setPosition(POSICION_INICIAL_AUTO.x, POSICION_INICIAL_AUTO.y);
		mActor.addAction(Actions.moveTo(100, 100, 5));
		mStage.addActor(mActor);  

	    InstructionStackTable table = new InstructionStackTable();
	    table.setFillParent(true);
	    mStage.addActor(table);	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//this.renderObjetos();
		batch.end();
        mStage.act(delta);
        mStage.draw();
	}
	
	private void renderObjetos(){
		for (int i=0 ; i < CANTIDAD_CASAS ; i++)
			batch.draw(mSkin.getRegion(TEXTURA_CASA),
					CASA_POSICION_INICIO.x + CASA_POSICION_OFFSET.x*i
					,CASA_POSICION_INICIO.y + CASA_POSICION_OFFSET.y*i);
		for (int i=0 ; i < CANTIDAD_HOTELES ; i++)
			batch.draw(mSkin.getRegion(TEXTURA_HOTEL),
					HOTEL_POSICION_INICIO.x + HOTEL_POSICION_OFFSET.x*i
					,HOTEL_POSICION_INICIO.y + HOTEL_POSICION_OFFSET.y*i);
		for (int i=0 ; i < CANTIDAD_HOTELES ; i++)
			batch.draw(mSkin.getRegion(TEXTURA_BANCO),
					BANCO_POSICION.x + BANCO_POSICION.x*i
					,BANCO_POSICION.y + BANCO_POSICION.y*i);
		//batch.draw(mSkin.getRegion(TEXTURA_AUTO),mPosicionAuto.x,mPosicionAuto.y,30,30);
	}
	
	public void mover(Vector2 pInicio, Vector2 pDestino){
		
	}

	@Override
	public void resize(int pWidth, int pHeight) {
		// TODO Auto-generated method stub
//		mCamera = new OrthographicCamera();
//		mCamera.setToOrtho(false, pWidth, pHeight);
//		mCamera.update();
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
