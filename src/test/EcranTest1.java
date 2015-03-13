package test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

import slickIHM.Action;
import slickIHM.Bouton;
import slickIHM.Checkbox;
import slickIHM.Ecran;
import slickIHM.Fenetre;
import slickIHM.GroupeBoutons;
import slickIHM.Panel;
import slickIHM.Popup;
import slickIHM.ToggleBouton;

/**
 * Ecran 1 de test
 * 
 * @author Remynoschka
 * 
 */
public class EcranTest1 extends Ecran {
	private Popup	p;

	@Override
	public void init(GameContainer fenetre, StateBasedGame jeu)
			throws SlickException {
		// On cree un panel
		Panel myPanel = new Panel((Fenetre) fenetre, fenetre.getWidth() - 300,
				0, 300, fenetre.getHeight(), this) {
			@Override
			public void doRender(GUIContext fenetre, Graphics g) {
				g.setColor(Color.red);
				g.drawRect(getX(), getY(), getWidth(), getHeight());
				super.doRender(fenetre, g);
			}
		};
		// on y ajoute un bouton
		Bouton b1 = new Bouton((Fenetre) fenetre, 0, 0, "Bouton", new Action() {

			@Override
			public void actionPerformed() {
				System.out.println("Le bouton 1 a été activé");
			}
		}, this, Input.KEY_A);
		b1.setNormalImage(new Image("./data/normal.png"));
		b1.setMouseOverImage(new Image("./data/over.png"));
		b1.setMouseDownImage(new Image("./data/click.png"));
		myPanel.addComponent(b1);
		// on cree une popup qui s'affichera
		p = new Popup((Fenetre) fenetre, 300, 300, 300, 300, this) {
			@Override
			public void doRender(GUIContext fenetre, Graphics g) {

				super.doRender(fenetre, g);
				g.setColor(Color.cyan);
				g.drawRect(getX(), getY(), getWidth(), getHeight());
				g.drawString("Popup ici", this.getX(), this.getY());
			}
		};
		// bouton pour fermer la popup
		Bouton closePopup = new Bouton((Fenetre) fenetre, p.getWidth()
				- Bouton.getButtonWidth(), p.getHeight()
				- Bouton.getButtonHeight(), "close", new Action() {
			@Override
			public void actionPerformed() {
				p.close();
				System.out.println("Close Popup Button");
			}

		}, this);
		closePopup.setNormalImage(new Image("./data/normal.png"));
		closePopup.setMouseOverImage(new Image("./data/over.png"));
		closePopup.setMouseDownImage(new Image("./data/click.png"));
		p.addComponent(closePopup);
		addComponent(p);
		// On fait un grand bouton qui fera apparaitre la popup
		Bouton.setDimensions(300, 200);
		Bouton b2 = new Bouton((Fenetre) fenetre, 0, 100, "Bouton\ngrand",
				new Action() {

					@Override
					public void actionPerformed() {
						System.out.println("Gros bouton");
						p.show(EcranTest1.this);
					}
				}, this, Input.KEY_A);
		b2.setNormalImage(new Image("./data/normal.png"));
		b2.setMouseOverImage(new Image("./data/over.png"));
		b2.setMouseDownImage(new Image("./data/click.png"));
		myPanel.addComponent(b2);

		// 2e Panel avec boutons groupables
		Panel p2 = new Panel((Fenetre) fenetre, 50, 0, 300,
				fenetre.getHeight(), this) {
			@Override
			public void doRender(GUIContext fenetre, Graphics g) {
				g.setColor(Color.green);
				g.drawRect(getX(), getY(), getWidth(), getHeight());
				super.doRender(fenetre, g);
			}
		};

		GroupeBoutons groupe = new GroupeBoutons();
		ToggleBouton tg1 = new ToggleBouton((Fenetre) fenetre, 0, 0, this,
				"TG1");
		tg1.setImageUp(new Image("./data/normal.png"));
		tg1.setImageUpOver(new Image("./data/over.png"));
		tg1.setImageDown(new Image("./data/click.png"));
		ToggleBouton tg2 = new ToggleBouton((Fenetre) fenetre, 50, 100, this,
				"TG2");
		tg2.setImageUp(new Image("./data/normal.png"));
		tg2.setImageUpOver(new Image("./data/over.png"));
		tg2.setImageDown(new Image("./data/click.png"));
		groupe.add(tg1);
		groupe.add(tg2);

		p2.addComponent(tg1);
		p2.addComponent(tg2);

		// on ajoute le panel a l'ecran
		this.addComponent(myPanel);
		this.addComponent(p2);

		// quelques checkbox
		Checkbox c1 = new Checkbox((Fenetre) fenetre, 200, 200, this, "c1",
				null, Checkbox.TXT_POS_DOWN);
		c1.setNormalImg(new Image("./data/normal.png"));
		c1.setOverImg(new Image("./data/over.png"));
		c1.setCheckedImg(new Image("./data/click.png"));
		c1.setClickedImg(new Image("./data/click.png"));
		c1.setCheckedOverImg(new Image("./data/click.png"));
		c1.setCheckedClickedImg(new Image("./data/click.png"));
		Checkbox c2 = new Checkbox((Fenetre) fenetre, 400, 200, this, "c2",
				null, Checkbox.TXT_POS_UP);
		c2.setNormalImg(new Image("./data/normal.png"));
		c2.setOverImg(new Image("./data/over.png"));
		c2.setCheckedImg(new Image("./data/click.png"));
		c2.setClickedImg(new Image("./data/click.png"));
		c2.setCheckedOverImg(new Image("./data/click.png"));
		c2.setCheckedClickedImg(new Image("./data/click.png"));
		Checkbox c3 = new Checkbox((Fenetre) fenetre, 600, 200, this, "c3",
				null, Checkbox.TXT_POS_LEFT);
		c3.setNormalImg(new Image("./data/normal.png"));
		c3.setOverImg(new Image("./data/over.png"));
		c3.setCheckedImg(new Image("./data/click.png"));
		c3.setClickedImg(new Image("./data/click.png"));
		c3.setCheckedOverImg(new Image("./data/click.png"));
		c3.setCheckedClickedImg(new Image("./data/click.png"));
		Checkbox c4 = new Checkbox((Fenetre) fenetre, 800, 200, this, "c4",
				null, Checkbox.TXT_POS_RIGHT);
		c4.setNormalImg(new Image("./data/normal.png"));
		c4.setOverImg(new Image("./data/over.png"));
		c4.setCheckedImg(new Image("./data/click.png"));
		c4.setClickedImg(new Image("./data/click.png"));
		c4.setCheckedOverImg(new Image("./data/click.png"));
		c4.setCheckedClickedImg(new Image("./data/click.png"));

		this.addComponent(c1);
		this.addComponent(c2);
		this.addComponent(c3);
		this.addComponent(c4);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

	}

	@Override
	public void enterBehaviour() {
		System.out.println("J'entre dans l'ecran 1");
	}

	@Override
	public void leaveBehaviour() {
		System.out.println("Je quitte l'ecran 1");
	}

	@Override
	public int getID() {
		return 1;
	}

}
