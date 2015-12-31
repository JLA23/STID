package Controller.Commandes.ModifSupprCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import BDD.Base;
import Model.Donnees;
import View.Commandes.ModifCommande;
import View.Commandes.SearchCommandeList;

public class ActionValiderVerif implements ActionListener {
	private JDialog frame;
	private JFrame fenetre;
	private Base bdd;
	private JFormattedTextField numCommande;
	private String f;
	private JTextField commandeClient;

	public ActionValiderVerif(JDialog frame, Base base, JFormattedTextField commande, JTextField comClient, JFrame fen,
			String fonction) {
		this.frame = frame;
		this.fenetre = fen;
		this.bdd = base;
		this.numCommande = commande;
		this.f = fonction;
		this.commandeClient = comClient;
	}

	public void actionPerformed(ActionEvent e) {
		Donnees donnees = new Donnees(bdd);
		if (donnees.existNumCommande(numCommande.getText())) {
			frame.dispose();
			if (donnees.existPlusieurNumComClient(commandeClient.getText())) {
				new SearchCommandeList(bdd, fenetre, f, commandeClient.getText());
			} else {
				if (f.equals("Modif")) {
					try {
						new ModifCommande(bdd, numCommande.getText(), fenetre);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (f.equals("Suppr")) {
					//new SupprCommande(bdd, numCommande.getText(), fenetre);
				} else if (f.equals("Recherche")) {
					// new LookCommande(bdd, numCommande.getText(),
					// fenetre);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucun commande avec ce numèro !", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
