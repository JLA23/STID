package Controller.Client.ModifSupprClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import BDD.Base;
import Model.Donnees;
import View.Clients.ModifClient;
import View.Clients.SupprClient;

public class ActionValiderVerif implements ActionListener {
	private JDialog frame;
	private Base bdd;
	private JFormattedTextField numClient;
	private String f;
	
	public ActionValiderVerif(JDialog frame, Base base, JFormattedTextField devis, String fonction){
		this.frame = frame;
		this.bdd = base;
		this.numClient = devis;
		this.f = fonction;
	}
	public void actionPerformed(ActionEvent e) {
		Donnees donnees = new Donnees(bdd);
		if(donnees.existNumClient(numClient.getText())){
			frame.dispose();
			if(f.equals("Modif")){
				new ModifClient(bdd, numClient.getText());
			}
			else if(f.equals("Suppr")){
				new SupprClient(bdd, numClient.getText());
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Aucun Client avec ce numèro !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
