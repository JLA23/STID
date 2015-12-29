package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import BDD.Base;
import View.Devis.SearchDevisList;

public class ActionList implements ActionListener {
	private JDialog frame;
	private JFrame windo;
	private Base bdd;
	private String fonction;
	
	public ActionList(JDialog dialog, Base base, JFrame fenetre, String f){
		this.frame = dialog;
		this.bdd = base;
		this.fonction = f;
		this.windo = fenetre;
	}
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new SearchDevisList(bdd, windo, true, fonction);	
	}
}
