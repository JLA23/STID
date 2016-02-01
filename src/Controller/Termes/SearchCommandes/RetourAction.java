package Controller.Termes.SearchCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import BDD.Base;
import View.SearchCommandes.SearchCommande;;

public class RetourAction implements ActionListener {
	
private JDialog fenetre;
private Base bdd;
private JFrame window;

	public RetourAction(JDialog frame, Base base, JFrame fe){
		this.fenetre = frame;
		this.bdd = base;
		this.window = fe;
	}
	public void actionPerformed(ActionEvent arg0) {
		fenetre.dispose();
		new SearchCommande(bdd, window, "NewTermes");
	}
}
