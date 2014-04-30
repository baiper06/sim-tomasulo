package tec.arqui.tomasulocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class Styles {
	
	public final static String FONT_FILE = "Aaargh.ttf";

	private static Styles 	mInstance;
	private BitmapFont		mHeaderFont;
	private BitmapFont 		mNormalFont;
	private LabelStyle   	mGenericTableHeaderStyle;
	private LabelStyle  mGenericTableNormalStyle;
	
	private Styles(){
		//Fuentes
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_FILE));
		mHeaderFont = generator.generateFont(12);
		mNormalFont = generator.generateFont(15);
		generator.dispose();
		
		//Styles
		mGenericTableHeaderStyle = new LabelStyle();
		mGenericTableHeaderStyle.font = mHeaderFont;
		mGenericTableHeaderStyle.fontColor = Color.BLACK;
		
		mGenericTableNormalStyle = new LabelStyle();
		mGenericTableNormalStyle.font = mNormalFont;
		mGenericTableNormalStyle.fontColor = Color.BLACK;
		
	}
	
	public LabelStyle getGenericTableHeaderStyle(){
		return mGenericTableHeaderStyle;
	}
	
	public LabelStyle getGenericTableNormalStyle(){
		return mGenericTableNormalStyle;
	}
	
	public static Styles getInstance(){
		if ( mInstance == null )
			mInstance = new Styles();
		return mInstance;
	}
	
}
