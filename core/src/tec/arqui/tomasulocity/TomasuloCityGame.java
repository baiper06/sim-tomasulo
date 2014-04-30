package tec.arqui.tomasulocity;

import com.badlogic.gdx.Game;

public class TomasuloCityGame extends Game {
	
	
	@Override
	public void create(){
		EscenarioTomasulo escenario = new EscenarioTomasulo();
		this.setScreen(escenario);
	}
}
