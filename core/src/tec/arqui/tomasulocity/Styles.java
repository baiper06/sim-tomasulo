package tec.arqui.tomasulocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Styles {
	
	public final static String FONT_FILE = "Aaargh.ttf";

	private static Styles 	mInstance;
	private BitmapFont		mHeaderFont;
	private BitmapFont 		mNormalFont;
	private Drawable 		mDrawableRect;
	private LabelStyle   	mGenericTableHeaderStyle;
	private LabelStyle  mGenericTableNormalStyle;
	private ListStyle   mGenericListStyle;
	private SelectBoxStyle mGenericSelectBoxStyle;

	private ScrollPaneStyle mGenericScrollPaneStyle;

	private TextButtonStyle mGenericTextButtonStyle;
	
	private TextFieldStyle mGenericTextFieldStyle;

	private Drawable mDrawableRectDark;
	
	private Drawable mDrawableArrowRight;

	private Drawable mDrawableArrowLeft;

	private Drawable mDrawableArrowTop;

	private Drawable mDrawableArrowBottom;

	private Drawable mDrawableArrowBottomRight;

	private Drawable mDrawableArrowTopRight;
	
	private Drawable mGears;

	private Drawable mDrawableArrowTopLeft;

	private Skin mSkin;
	
	private Styles(){
		//Fuentes
		mHeaderFont = new BitmapFont(Gdx.files.internal("Aaargh-15.fnt"));
		mNormalFont = new BitmapFont(Gdx.files.internal("Aaargh-12.fnt"));
		
		TextureAtlas atlas = new TextureAtlas("icons.pack");
		mSkin = new Skin(atlas);
		mDrawableRect = new Image(mSkin, "rectangle").getDrawable();
		mDrawableRectDark = new Image(mSkin,"rectangle-dark").getDrawable();
		//Styles
		mGenericTableHeaderStyle = new LabelStyle();
		mGenericTableHeaderStyle.font = mHeaderFont;
		mGenericTableHeaderStyle.fontColor = Color.BLACK;
		
		mGenericTableNormalStyle = new LabelStyle();
		mGenericTableNormalStyle.font = mNormalFont;
		mGenericTableNormalStyle.fontColor = Color.BLACK;
		
		mGenericListStyle = new ListStyle();
		mGenericListStyle.font = mNormalFont;
		mGenericListStyle.fontColorSelected = Color.BLACK;
		mGenericListStyle.fontColorUnselected = Color.GRAY;
		mGenericListStyle.background = mDrawableRect;
		mGenericListStyle.selection = mDrawableRect;
		
		mGenericScrollPaneStyle = new ScrollPaneStyle();
		mGenericScrollPaneStyle.background = mDrawableRect;
		
		mGenericSelectBoxStyle = new SelectBoxStyle();
		mGenericSelectBoxStyle.font = mNormalFont;
		mGenericSelectBoxStyle.fontColor = Color.BLACK;
		mGenericSelectBoxStyle.disabledFontColor = Color.GRAY;
		mGenericSelectBoxStyle.background = mDrawableRect;
		mGenericSelectBoxStyle.backgroundDisabled = mDrawableRect;
		mGenericSelectBoxStyle.listStyle = mGenericListStyle;
		mGenericSelectBoxStyle.scrollStyle = mGenericScrollPaneStyle;
		
		
		mGenericTextButtonStyle = new TextButtonStyle();
		mGenericTextButtonStyle.up = mDrawableRect;
		mGenericTextButtonStyle.down = mDrawableRectDark;
		mGenericTextButtonStyle.font = mNormalFont;
		mGenericTextButtonStyle.fontColor = Color.BLACK;
		
		mGenericTextFieldStyle = new TextFieldStyle();
		mGenericTextFieldStyle.font = mNormalFont;
		mGenericTextFieldStyle.fontColor = Color.BLACK;
		mGenericTextFieldStyle.background = mDrawableRect;
		
		mDrawableArrowRight  = new Image(mSkin, "right").getDrawable();
		mDrawableArrowLeft   = new Image(mSkin, "left").getDrawable();
		mDrawableArrowTop    = new Image(mSkin, "top").getDrawable();
		mDrawableArrowBottom = new Image(mSkin, "bottom").getDrawable();
		mDrawableArrowBottomRight = new Image(mSkin, "bottom-right").getDrawable();
		mDrawableArrowTopRight = new Image(mSkin, "top-right").getDrawable();
		mGears = new Image(mSkin,"gears3").getDrawable();
		mDrawableArrowTopLeft = new Image(mSkin,"top-left").getDrawable();
	}
	
	public LabelStyle getGenericTableHeaderStyle(){
		return mGenericTableHeaderStyle;
	}
	
	public LabelStyle getGenericTableNormalStyle(){
		return mGenericTableNormalStyle;
	}
	
	public ListStyle getGenericListStyle(){
		return mGenericListStyle;
	}
	
	public SelectBoxStyle getGenericSelectBoxStyle(){
		return mGenericSelectBoxStyle;
	}
	
	public TextButtonStyle getGenericTextButtonStyle(){
		return mGenericTextButtonStyle;
	}
	
	public TextFieldStyle getGenericTextFieldStyle(){
		return mGenericTextFieldStyle;
	}
	
	public Drawable getDrawable(){
		return mDrawableRect;
	}
	
	public Drawable getArrowRight(){
		return mDrawableArrowRight;
	}
	public Drawable getArrowLeft(){
		return mDrawableArrowLeft;
	}
	public Drawable getArrowTop(){
		return mDrawableArrowTop;
	}
	public Drawable getArrowBottom(){
		return mDrawableArrowBottom;
	}
	
	public Drawable getArrowBottomRight(){
		return mDrawableArrowBottomRight;
	}
	
	public Drawable getArrowTopRight(){
		return mDrawableArrowTopRight;
	}
	
	public Drawable getGears(){
		return mGears;
	}
	
	public Drawable getArrowTopLeft(){
		return mDrawableArrowTopLeft;
	}
	
	public Skin getSkin(){
		return mSkin;
	}
	
	
	public static Styles getInstance(){
		if ( mInstance == null )
			mInstance = new Styles();
		return mInstance;
	}
	
}
