package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import BDD.Base;
import Model.Donnees;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;

public class ActionValiderVerif implements ActionListener {
	private JDialog frame;
	private Base bdd;
	private JFormattedTextField numDevis;
	private String f;
	
	public ActionValiderVerif(JDialog frame, Base base, JFormattedTextField devis, String fonction){
		this.frame = frame;
		this.bdd = base;
		this.numDevis = devis;
		this.f = fonction;
	}
	public void actionPerformed(ActionEvent e) {
		Donnees donnees = new Donnees(bdd);
		if(donnees.existNumDevis(numDevis.getText())){
			frame.dispose();
			try {
				if(f.equals("Modif")){
					new ModifDevis(bdd, numDevis.getText());
				}
				else if(f.equals("Suppr")){
					new SupprDevis(bdd, numDevis.getText());
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Aucun Devis avec ce numèro !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
