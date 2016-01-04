package Controller.Commandes.ModifSupprCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import View.Commandes.Commandes;
import View.Commandes.SearchCommande;

public class ActionRechercher implements ActionListener {

	private Commandes commandes;
	private String f;
	private JFrame fenetre;

	public ActionRechercher(Commandes d, JFrame frame, String fonction) {
		this.commandes = d;
		this.f = fonction;
		this.fenetre = frame;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (f.equals("Modif")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications ?",
					"Quitter devis", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValider(commandes).valider();
			}
			if (option != JOptionPane.CANCEL_OPTION) {
				commandes.dispose();
				new SearchCommande(commandes.getBase(), fenetre, f);
			}
		} else if (f.equals("Suppr")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le devis ?", "Quitter devis",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionSuppr(commandes).suppr();
			}
			if (option != JOptionPane.CANCEL_OPTION) {
				commandes.dispose();
				new SearchCommande(commandes.getBase(), fenetre, f);
			}
		}

		else if (f.equals("Recherche")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le devis ?", "Quitter devis",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				commandes.dispose();
				new SearchCommande(commandes.getBase(), fenetre, f);
			}
		}
	}
}
