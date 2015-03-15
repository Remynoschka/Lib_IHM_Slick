package slickIHM;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 * Classe generique pour les textfields Une partie du code de cette classe est
 * issue du code original de slick2D
 * 
 * @author Remynoschka
 * @author kevin
 * 
 */
public abstract class ExtendedTextField extends AbstractComponent implements
		IHMComponent {

	/**
	 * Le composant est desactive
	 */
	protected boolean			disabled					= false;

	/**
	 * Le composant est visible
	 */
	protected boolean			visible						= true;

	/**
	 * L'Ecran contenant ce textfield
	 */
	protected Ecran				ecran;

	/**
	 * Le conteneur de ce textfield
	 */
	protected IHMConteneur		conteneur;

	/**
	 * La couleur du curseur
	 */
	protected Color				cursorColor					= Color.black;

	/**
	 * Le nombre de render effectue (sert pour le clignotement du curseur)
	 */
	private int					nbRefresh					= 0;
	// surbrillance
	/**
	 * Point de depart de la mise en surbrillance
	 */
	protected int				surbrillanceStart			= 0;
	/**
	 * Point d'arrive de la mise en surbrillance
	 */
	protected int				surbrillanceEnd				= 0;
	/**
	 * Couleur de la surbrillance
	 */
	protected Color				surbrillanceColor			= Color.white;

	/**
	 * Indique s'il y a surbrillance
	 */
	protected boolean			surbrillance				= false;

	// Issues de l'ancien TextField
	/** The key repeat interval */
	private static final int	INITIAL_KEY_REPEAT_INTERVAL	= 400;
	/** The key repeat interval */
	private static final int	KEY_REPEAT_INTERVAL			= 50;
	/** The width of the field */
	private int					width;
	/** The height of the field */
	private int					height;
	/** The location in the X coordinate */
	protected int				x;
	/** The location in the Y coordinate */
	protected int				y;
	/** The maximum number of characters allowed to be input */
	private int					maxCharacter				= 10000;
	/** The value stored in the text field */
	private String				value						= "";
	/** The font used to render text in the field */
	private Font				font;
	/** The border color - null if no border */
	private Color				borderColor					= Color.white;
	/** The text color */
	private Color				textColor					= Color.black;
	/** The background color - null if no background */
	private Color				backgroundColor				= new Color(200,
																	200, 200);
	/** The current cursor position */
	private int					cursorPos;
	/** True if the cursor should be visible */
	private boolean				visibleCursor				= true;
	/** The last key pressed */
	private int					lastKey						= -1;
	/** The last character pressed */
	private char				lastChar					= 0;
	/** The time since last key repeat */
	private long				repeatTimer;
	/** The text before the paste in */
	private String				oldText;
	/** The cursor position before the paste */
	private int					oldCursorPos;
	/** True if events should be consumed by the field */
	private boolean				consume						= true;

	/**
	 * 
	 * @param fenetre
	 * @param font
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ecran
	 * @param listener
	 */
	public ExtendedTextField(GUIContext fenetre, Font font, int x, int y,
			int width, int height, Ecran ecran, ComponentListener listener) {
		super(fenetre);
		this.x = x;
		this.y = y;
		this.font = font;
		this.width = width;
		this.height = height;
		this.ecran = ecran;
		this.font = font;
		addListener(listener);
	}

	/**
	 * 
	 * @param fenetre
	 * @param font
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ecran
	 */
	public ExtendedTextField(GUIContext fenetre, Font font, int x, int y,
			int width, int height, Ecran ecran) {
		super(fenetre);
		this.x = x;
		this.y = y;
		this.font = font;
		this.width = width;
		this.height = height;
		this.ecran = ecran;
		this.font = font;
	}

	/**
	 * Indicate if the input events should be consumed by this field
	 * 
	 * @param consume
	 *            True if events should be consumed by this field
	 */
	public void setConsumeEvents(boolean consume) {
		this.consume = consume;
	}

	/**
	 * Deactivate the key input handling for this field
	 */
	public void deactivate() {
		setFocus(false);
	}

	/**
	 * Moves the component.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the position in the X coordinate
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the position in the Y coordinate
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the width of the component
	 * 
	 * @return The width of the component
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of the component
	 * 
	 * @return The height of the component
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the background color. Set to null to disable the background
	 * 
	 * @param color
	 *            The color to use for the background
	 */
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	 * Set the border color. Set to null to disable the border
	 * 
	 * @param color
	 *            The color to use for the border
	 */
	public void setBorderColor(Color color) {
		borderColor = color;
	}

	/**
	 * Set the text color.
	 * 
	 * @param color
	 *            The color to use for the text
	 */
	public void setTextColor(Color color) {
		textColor = color;
	}

	/**
	 * Defini si le composant est visible ou non
	 * 
	 * @param val
	 *            : true = visible
	 */
	public void setVisible(boolean val) {
		visible = val;
	}

	/**
	 * Active le composant
	 */
	public void enable() {
		disabled = false;
	}

	public void disable() {
		disabled = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isEnabled() {
		return !disabled;
	}

	public Ecran getEcran() {
		return ecran;
	}

	public void setConteneur(IHMConteneur conteneur) {
		this.conteneur = conteneur;
		if (conteneur != null)
			this.setLocation(conteneur.getX() + this.getX(), conteneur.getY()
					+ this.getY());
	}

	public IHMConteneur getConteneur() {
		return conteneur;
	}

	/**
	 * @see org.newdawn.slick.gui.AbstractComponent#render(org.newdawn.slick.gui.GUIContext,
	 *      org.newdawn.slick.Graphics)
	 */
	public void render(GUIContext fenetre, Graphics g) {
		if (visible) {
			doRender(fenetre, g);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#doRender(org.newdawn.slick.gui.GUIContext,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		if (lastKey != -1) {
			if (input.isKeyDown(lastKey)) {
				if (repeatTimer < System.currentTimeMillis()) {
					repeatTimer = System.currentTimeMillis()
							+ KEY_REPEAT_INTERVAL;
					keyPressed(lastKey, lastChar);
				}
			} else {
				lastKey = -1;
			}
		}
		Rectangle oldClip = g.getClip();
		g.setWorldClip(x, y, width, height);
		// Someone could have set a color for me to blend...
		Color clr = g.getColor();
		if (backgroundColor != null) {
			g.setColor(backgroundColor);
			g.fillRect(x, y, width, height);
		}
		Font temp = g.getFont();
		int cpos = font.getWidth(value.substring(0, cursorPos));
		int start = font.getWidth(value.substring(0, surbrillanceStart));
		int end = font.getWidth(value.substring(0, surbrillanceEnd));
		int tx = 0;
		if (cpos > width) {
			tx = width - cpos - font.getWidth("|");
		}
		if (surbrillance) {
			g.setColor(surbrillanceColor);
			drawSurbrillance(g, start, end);
		}
		g.setColor(textColor);
		drawTexte(g, tx);
		drawCursor(g, cpos);
		drawBorder(g, clr, tx);
		g.setColor(clr);
		g.setFont(temp);
		g.clearWorldClip();
		g.setClip(oldClip);

		nbRefresh++;
		if (nbRefresh >= 3000) {
			nbRefresh = 0;
		}
	}

	/**
	 * Dessine la zone de surbrillance
	 * 
	 * @param g
	 * @param start
	 *            : le point de depart de la surbrillance
	 * @param end
	 *            : le point de fin de la surbrillance
	 */
	protected void drawSurbrillance(Graphics g, int start, int end) {
		g.fillRect((float) (x + start + 1), (float) (y + 3),
				(float) (end - start) + 1, (float) (font.getHeight("|")));
	}

	/**
	 * Dessine le texte
	 * 
	 * @param g
	 * @param tx
	 */
	protected void drawTexte(Graphics g, int tx) {
		g.translate(tx + 2, 0);
		g.setFont(font);
		g.drawString(value, x + 1, y + 1);
	}

	/**
	 * Dessine le curseur
	 * 
	 * @param g
	 * @param cpos
	 *            : position du curseur
	 */
	protected void drawCursor(Graphics g, int cpos) {
		if (hasFocus() && visibleCursor) {
			if (nbRefresh > 1500) {
				Color col = g.getColor();
				g.setColor(cursorColor);
				g.drawLine((float) (x + cpos), y + 3, (float) (x + cpos), y
						+ font.getHeight("|") + 1);
				g.setColor(col);
			}
		}
	}

	/**
	 * Dessine la bordure du TextField
	 * 
	 * @param g
	 * @param clr
	 *            : la couleur de la bordure
	 * @param tx
	 */
	protected void drawBorder(Graphics g, Color clr, int tx) {
		g.translate(-tx - 2, 0);
		if (borderColor != null) {
			g.setColor(borderColor.multiply(clr));
			g.drawRect(x, y, width, height);
		}
	}

	/**
	 * Get the value in the text field
	 * 
	 * @return The value in the text field
	 */
	public String getText() {
		return value;
	}

	/**
	 * Set the value to be displayed in the text field
	 * 
	 * @param value
	 *            The value to be displayed in the text field
	 */
	public void setText(String value) {
		this.value = value;
		if (cursorPos > value.length()) {
			cursorPos = value.length();
		}
	}

	/**
	 * Set the position of the cursor
	 * 
	 * @param pos
	 *            The new position of the cursor
	 */
	public void setCursorPos(int pos) {
		cursorPos = pos;
		if (cursorPos > value.length()) {
			cursorPos = value.length();
		}
	}

	/**
	 * Permet d'obtenir la position du curseur
	 * 
	 * @return la position du curseur
	 */
	public int getCursorPos() {
		return cursorPos;
	}

	/**
	 * Indicate whether the mouse cursor should be visible or not
	 * 
	 * @param visibleCursor
	 *            True if the mouse cursor should be visible
	 */
	public void setCursorVisible(boolean visibleCursor) {
		this.visibleCursor = visibleCursor;
	}

	/**
	 * Set the length of the allowed input
	 * 
	 * @param length
	 *            The length of the allowed input
	 */
	public void setMaxLength(int length) {
		maxCharacter = length;
		if (value.length() > maxCharacter) {
			value = value.substring(0, maxCharacter);
		}
	}

	/**
	 * Do the paste into the field, overrideable for custom behaviour
	 * 
	 * @param text
	 *            The text to be pasted in
	 */
	protected void doPaste(String text) {
		recordOldPosition();
		for (int i = 0; i < text.length(); i++) {
			keyPressed(-1, text.charAt(i));
		}
	}

	/**
	 * Record the old position and content
	 */
	protected void recordOldPosition() {
		oldText = getText();
		oldCursorPos = cursorPos;
	}

	/**
	 * Do the undo of the paste, overrideable for custom behaviour
	 * 
	 * @param oldCursorPos
	 *            before the paste
	 * @param oldText
	 *            The text before the last paste
	 */
	protected void doUndo(int oldCursorPos, String oldText) {
		if (oldText != null) {
			setText(oldText);
			setCursorPos(oldCursorPos);
		}
	}

	/**
	 * @see org.newdawn.slick.gui.AbstractComponent#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (hasFocus())
					actionKeyPressed(key, c);
			}
		}
	}

	/**
	 * @see org.newdawn.slick.gui.AbstractComponent#setFocus(boolean)
	 */
	public void setFocus(boolean focus) {
		lastKey = -1;
		super.setFocus(focus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseClicked(int, int, int, int)
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseClicked(button, x, y, clickCount);
			}
		}
	}

	/**
	 * Action effectuee quand un clic de souris a lieu
	 * 
	 * @param button
	 * @param x
	 * @param y
	 * @param clickCount
	 * @see org.newdawn.slick.util.InputAdapter#mouseClicked(int button, int x,
	 *      int y, int clickCount)
	 */
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseDragged(int, int, int, int)
	 */
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseDragged(oldx, 0, newx, newy);
			}
		}
	}

	/**
	 * Action effectuee quand un drag de souris a lieu
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 * @see org.newdawn.slick.util.InputAdapter#mouseDragged(int oldx, int oldy,
	 *      int newx, int newy)
	 */
	public void actionMouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int, int, int, int)
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseMoved(oldx, oldy, newx, newy);
			}
		}
	}

	/**
	 * Action effectuee quand un mouvement de souris a lieu
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int oldx, int oldy,
	 *      int newx, int newy)
	 */
	public void actionMouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mousePressed(int, int, int)
	 */
	@Override
	public void mousePressed(int button, int mx, int my) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionMousePressed(button, mx, my);
			}
		}
	}

	/**
	 * Action effectuee quand un bouton de la souris est appuye
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mousePressed(int button, int mx,
	 *      int my
	 */
	public void actionMousePressed(int button, int mx, int my) {
		super.mousePressed(button, mx, my);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.gui.MouseOverArea#mouseReleased(int, int, int)
	 */
	@Override
	public void mouseReleased(int button, int mx, int my) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionMouseReleased(button, mx, my);
			}
		}
	}

	/**
	 * Action effectuee quand un bouton de la souris est relache
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int button, int
	 *      mx, int my
	 */
	public void actionMouseReleased(int button, int mx, int my) {
		super.mouseReleased(button, mx, my);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	@Override
	public void mouseWheelMoved(int change) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionMouseWheelMoved(change);
			}
		}
	}

	/**
	 * Action effectuee quand la molette de la souris change d'etat
	 * 
	 * @param change
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	public void actionMouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
	}

	/**
	 * Action effectuee quand une touche est pressee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
	 */
	public void actionKeyPressed(int key, char c) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (hasFocus())
					if (key != -1) {
						// System.out.println(cursorPos);
						if ((key == Input.KEY_V)
								&& ((input.isKeyDown(Input.KEY_LCONTROL)) || (input
										.isKeyDown(Input.KEY_RCONTROL)))) {
							String text = Sys.getClipboard();
							if (text != null) {
								int droite = cursorPos;
								int gauche = cursorPos;
								// remplace le texte si surbrillance
								if (surbrillance) {
									// on definit les bords g/d de la zone en
									// surbrillance
									if (surbrillanceStart >= surbrillanceEnd) {
										droite = surbrillanceStart;
										gauche = surbrillanceEnd;
									} else {
										gauche = surbrillanceStart;
										droite = surbrillanceEnd;
									}
									// en enleve ce qui va etre remplace
									value = value.substring(0, gauche)
											+ value.substring(droite);

									surbrillance = false;
									surbrillanceStart = surbrillanceEnd = cursorPos;
									cursorPos = gauche;
								}
								doPaste(text);
								// cursorPos = gauche + text.length();
							}
							return;
						}
						if ((key == Input.KEY_Z)
								&& ((input.isKeyDown(Input.KEY_LCONTROL)) || (input
										.isKeyDown(Input.KEY_RCONTROL)))) {
							if (oldText != null) {
								doUndo(oldCursorPos, oldText);
								surbrillanceStart = surbrillanceEnd = cursorPos;
								surbrillance = false;
							}
							return;
						}
						if ((key == Input.KEY_A)
								&& ((input.isKeyDown(Input.KEY_LCONTROL)) || (input
										.isKeyDown(Input.KEY_RCONTROL)))) {
							surbrillanceStart = 0;
							surbrillanceEnd = value.length();
							surbrillance = true;
							cursorPos = value.length();
						}
						// alt and control keys don't come through here
						if (input.isKeyDown(Input.KEY_LCONTROL)
								|| input.isKeyDown(Input.KEY_RCONTROL)) {
							return;
						}
						if (input.isKeyDown(Input.KEY_LALT)
								|| input.isKeyDown(Input.KEY_RALT)) {
							return;
						}
					}
				if (lastKey != key) {
					lastKey = key;
					repeatTimer = System.currentTimeMillis()
							+ INITIAL_KEY_REPEAT_INTERVAL;
				} else {
					repeatTimer = System.currentTimeMillis()
							+ KEY_REPEAT_INTERVAL;
				}
				lastChar = c;
				if (key == Input.KEY_LEFT) {
					if (cursorPos > 0) {
						cursorPos--;
						if (input.isKeyDown(Input.KEY_LSHIFT)
								|| input.isKeyDown(Input.KEY_RSHIFT)) {
							surbrillance = true;
							if (surbrillanceStart > 0)
								surbrillanceEnd--;
						} else {
							surbrillanceStart = surbrillanceEnd = cursorPos;
							surbrillance = false;
						}
					}
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
				} else if (key == Input.KEY_RIGHT) {
					if (cursorPos < value.length()) {
						cursorPos++;
						if (input.isKeyDown(Input.KEY_LSHIFT)
								|| input.isKeyDown(Input.KEY_RSHIFT)) {
							surbrillance = true;
							if (surbrillanceEnd < value.length())
								surbrillanceEnd++;
						} else {
							surbrillanceStart = surbrillanceEnd = cursorPos;
							surbrillance = false;
						}
					}
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
				} else if (key == Input.KEY_BACK) {
					if ((cursorPos > 0) && (value.length() > 0)) {
						if (cursorPos < value.length()) {
							int droite = cursorPos;
							int gauche = cursorPos;
							// remplace le texte si surbrillance
							if (surbrillance) {
								// on definit les bords g/d de la zone en
								// surbrillance
								if (surbrillanceStart >= surbrillanceEnd) {
									droite = surbrillanceStart;
									gauche = surbrillanceEnd;
								} else {
									gauche = surbrillanceStart;
									droite = surbrillanceEnd;
								}
								// en enleve ce qui va etre remplace
								value = value.substring(0, gauche)
										+ value.substring(droite);

								surbrillance = false;
								cursorPos = gauche;
							} else {
								value = value.substring(0, cursorPos - 1)
										+ value.substring(cursorPos);
								cursorPos--;
							}
						} else {
							int droite = cursorPos;
							int gauche = cursorPos;
							// remplace le texte si surbrillance
							if (surbrillance) {
								// on definit les bords g/d de la zone en
								// surbrillance
								if (surbrillanceStart >= surbrillanceEnd) {
									droite = surbrillanceStart;
									gauche = surbrillanceEnd;
								} else {
									gauche = surbrillanceStart;
									droite = surbrillanceEnd;
								}
								// en enleve ce qui va etre remplace
								value = value.substring(0, gauche)
										+ value.substring(droite);

								surbrillance = false;
								cursorPos = gauche;
							} else {
								value = value.substring(0, cursorPos - 1);
								cursorPos--;
							}
						}
					}
					surbrillance = false;
					surbrillanceStart = surbrillanceEnd = cursorPos;
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
				} else if (key == Input.KEY_DELETE) {
					if (value.length() >= cursorPos) {
						// delete le texte si surbrillance
						int droite = cursorPos;
						int gauche = cursorPos;
						if (surbrillance) {
							// on definit les bords g/d de la zone en
							// surbrillance
							if (surbrillanceStart >= surbrillanceEnd) {
								droite = surbrillanceStart;
								gauche = surbrillanceEnd;
							} else {
								gauche = surbrillanceStart;
								droite = surbrillanceEnd;
							}
							// en enleve ce qui va etre remplace
							value = value.substring(0, gauche)
									+ value.substring(droite);

							surbrillance = false;
							cursorPos = gauche;
						} else {
							if (value.length() > cursorPos) {
								value = value.substring(0, cursorPos)
										+ value.substring(cursorPos + 1);
							}
						}
					}
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
					surbrillance = false;
					surbrillanceStart = surbrillanceEnd = cursorPos;
				} else if ((c < 127) && (c > 31)
						&& (value.length() < maxCharacter)) {
					if (cursorPos < value.length()) {
						value = value.substring(0, cursorPos) + c
								+ value.substring(cursorPos);
					} else {
						value = value.substring(0, cursorPos) + c;
					}
					int droite = cursorPos;
					int gauche = cursorPos;
					// remplace le texte si surbrillance
					if (surbrillance) {
						// on definit les bords g/d de la zone en
						// surbrillance
						if (surbrillanceStart >= surbrillanceEnd) {
							droite = surbrillanceStart;
							gauche = surbrillanceEnd;
						} else {
							gauche = surbrillanceStart;
							droite = surbrillanceEnd;
						}
						// en enleve ce qui va etre remplace
						value = value.substring(0, gauche) + c
								+ value.substring(droite);

						surbrillance = false;
						cursorPos = gauche;
					}
					cursorPos++;
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
					surbrillanceStart = surbrillanceEnd = cursorPos;
				} else if (key == Input.KEY_RETURN) {
					notifyListeners();
					// Nobody more will be notified
					if (consume) {
						container.getInput().consumeEvent();
					}
					surbrillanceStart = surbrillanceEnd = cursorPos;
					surbrillance = false;
				} else if (key == Input.KEY_HOME) {
					if (input.isKeyDown(Input.KEY_LSHIFT)
							|| input.isKeyDown(Input.KEY_RSHIFT)) {
						surbrillanceStart = 0;
						surbrillanceEnd = cursorPos;
						surbrillance = true;
					} else {
						surbrillanceStart = surbrillanceEnd = cursorPos;
						surbrillance = false;
					}
					this.setCursorPos(0);
				} else if (key == Input.KEY_END) {
					if (input.isKeyDown(Input.KEY_LSHIFT)
							|| input.isKeyDown(Input.KEY_RSHIFT)) {
						surbrillanceStart = cursorPos;
						surbrillanceEnd = this.getText().length();
						surbrillance = true;
						this.setCursorPos(this.getText().length());
					} else {
						this.setCursorPos(this.getText().length());
						surbrillanceStart = surbrillanceEnd = cursorPos;
						surbrillance = false;
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	@Override
	public void keyReleased(int key, char c) {
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (hasFocus())
					actionKeyReleased(key, c);
			}
		}
	}

	/**
	 * Action effectuee quand une touche est relachee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	public void actionKeyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	/**
	 * Test si la souris survole l'IHMComponent
	 * 
	 * @param mouseX
	 *            : position X de la souris
	 * @param mouseY
	 *            : position Y de la souris
	 * @return true si la souris est dans le composant
	 */
	public boolean mouseInside(int mouseX, int mouseY) {
		return (mouseX > this.getX() && mouseX < this.getX() + getWidth()
				&& mouseY > this.getY() && mouseY < this.getY()
				+ this.getHeight());
	}

	/**
	 * Renvoie la couleur du curseur
	 * 
	 * @return la couleur du curseur
	 */
	public Color getCursorColor() {
		return cursorColor;
	}

	/**
	 * Definit la couleur du curseur
	 * 
	 * @param cursorColor
	 *            : couleur
	 */
	public void setCursorColor(Color cursorColor) {
		this.cursorColor = cursorColor;
	}

}
