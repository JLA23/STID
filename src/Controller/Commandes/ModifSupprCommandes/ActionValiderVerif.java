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
import View.Commandes.SupprCommande;

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
		if (!numCommande.getText().isEmpty() && commandeClient.getText().isEmpty()) {
			if (donnees.existNumCommande(numCommande.getText())) {
				frame.dispose();
				try {
					if (f.equals("Modif")) {
						new ModifCommande(bdd, numCommande.getText(), fenetre);
					} else if (f.equals("Suppr")) {
						new SupprCommande(bdd, numCommande.getText(), fenetre);
					} else if (f.equals("Recherche")) {
						// new LookCommande(bdd, numCommande.getText(),
						// fenetre);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (numCommande.getText().isEmpty() && !commandeClient.getText().isEmpty()) {
			if (donnees.existNumCommandeClient(commandeClient.getText())) {
				frame.dispose();
				if (donnees.existPlusieurNumComClient(commandeClient.getText())) {
					new SearchCommandeList(bdd, fenetre, f, commandeClient.getText());
				} else {
					if (f.equals("Modif")) {
						try {
							new ModifCommande(bdd, donnees.searchNumCommandeClient(commandeClient.getText()), fenetre);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (f.equals("Suppr")) {
						// new SupprCommande(bdd, numCommande.getText(),
						// fenetre);
					} else if (f.equals("Recherche")) {
						// new LookCommande(bdd, numCommande.getText(),
						// fenetre);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande client avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (!numCommande.getText().isEmpty() && !numCommande.getText().isEmpty()) {
			if (donnees.existNumCommande(numCommande.getText(), commandeClient.getText())) {
				frame.dispose();
				if (f.equals("Modif")) {
					try {
						new ModifCommande(bdd, numCommande.getText(), fenetre);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (f.equals("Suppr")) {
					// new SupprCommande(bdd, numCommande.getText(),
					// fenetre);
				} else if (f.equals("Recherche")) {
					// new LookCommande(bdd, numCommande.getText(),
					// fenetre);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ces numéros !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (numCommande.getText().isEmpty() && numCommande.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}

	}
}
