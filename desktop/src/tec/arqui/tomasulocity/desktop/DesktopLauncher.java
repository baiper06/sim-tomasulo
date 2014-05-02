package tec.arqui.tomasulocity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tec.arqui.tomasulocity.TomasuloCityGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=640;
		config.width=1024;
		new LwjglApplication(new TomasuloCityGame(), config);
	}
}
