package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import BDD.Base;
import Model.Donnees;
import View.Devis.LookDevis;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;

public class ActionValiderVerif implements ActionListener {
	private JDialog frame;
	private JFrame fenetre;
	private Base bdd;
	private JFormattedTextField numDevis;
	private String f;
	
	public ActionValiderVerif(JDialog frame, Base base, JFormattedTextField devis, JFrame fen, String fonction){
		this.frame = frame;
		this.fenetre = fen;
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
					new ModifDevis(bdd, numDevis.getText(), fenetre);
				}
				else if(f.equals("Suppr")){
					new SupprDevis(bdd, numDevis.getText(), fenetre);
				}
				else if(f.equals("Recherche")){
					new LookDevis(bdd, numDevis.getText(), fenetre);
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
