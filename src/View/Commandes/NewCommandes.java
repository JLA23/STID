package View.Commandes;

import javax.swing.JFrame;

import BDD.Base;

public class NewCommandes extends Commandes{

	private static final long serialVersionUID = 1L;

	public NewCommandes(Base bdd, JFrame frame) {
		super(bdd, frame);
        this.setVisible(true);
	}
	

}
