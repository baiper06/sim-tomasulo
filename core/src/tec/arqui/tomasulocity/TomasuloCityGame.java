package tec.arqui.tomasulocity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class TomasuloCityGame extends Game {
	
	
	private Music dropSound;

	@Override
	public void create(){
		InicioScreen inicio = new InicioScreen();
		inicio.game = this;
		this.setScreen(inicio);
	    dropSound = Gdx.audio.newMusic(Gdx.files.internal("sound.wav"));
	    dropSound.setLooping(true);
	    dropSound.play();
	}
	
	public void changeScreen(){
		EscenarioTomasulo escenario = new EscenarioTomasulo();
		this.setScreen(escenario);
	}
}
