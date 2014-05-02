package tec.arqui.tomasulocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InicioScreen implements Screen{
	
	Sprite sprite;
    private SpriteBatch spriteBatch;
    public TomasuloCityGame game;

	@Override
	public void render(float pDelta) {
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		sprite.draw(spriteBatch);
		spriteBatch.end();
		if(Gdx.input.isTouched()) {
	           // Remove the task so we don't call changeScreen twice: 
			game.changeScreen();
	    }
	}

	@Override
	public void resize(int pWidth, int pHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		spriteBatch = new SpriteBatch();
		sprite =  new Sprite(Styles.getInstance().getSkin().getRegion("FreeTheRegs"));

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
