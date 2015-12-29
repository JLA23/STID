package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

import BDD.Base;
import View.Devis.SearchDevis;

public class RetourAction implements ActionListener {
	
private JDialog fenetre;
private Base bdd;
private String f;
private JFrame window;

	public RetourAction(JDialog frame, Base base, JFrame fe, String fonction){
		this.fenetre = frame;
		this.bdd = base;
		this.f = fonction;
		this.window = fe;
	}
	public void actionPerformed(ActionEvent arg0) {
		fenetre.dispose();
		new SearchDevis(bdd, window, true, f);
	}
}
