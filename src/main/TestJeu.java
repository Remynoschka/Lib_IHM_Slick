package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import test.EcranTest1;

public class TestJeu extends StateBasedGame {
	public static final TestJeu			INSTANCE		= new TestJeu();
	public static UnicodeFont		FONT;
	public static UnicodeFont		LOW_FONT;
	public static final ColorEffect	COLOR_EFFECT	= new ColorEffect();

	private TestJeu() {
		super("MyLib_IHM");
	}

	@Override
	public void initStatesList(GameContainer fenetre) throws SlickException {
		addState(new EcranTest1());
	}

	/**
	 * Initialise les polices de caracteres
	 * 
	 * @throws SlickException
	 */
	@SuppressWarnings("unchecked")
	private void initFonts() throws SlickException {
		FONT = new UnicodeFont("./data/fonts/times.ttf", 20, false, false);
		FONT.addAsciiGlyphs();
		FONT.addGlyphs(400, 600);
		FONT.getEffects().add(COLOR_EFFECT);
		FONT.loadGlyphs();
		// Police petite
		LOW_FONT = new UnicodeFont("./data/fonts/times.ttf", 15, false, false);
		LOW_FONT.addAsciiGlyphs();
		LOW_FONT.addGlyphs(400, 600);
		LOW_FONT.getEffects().add(TestJeu.COLOR_EFFECT);
		LOW_FONT.loadGlyphs();
	}

}
