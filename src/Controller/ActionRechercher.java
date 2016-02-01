package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.ValiderSuppr;
import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.SearchClients.SearchClient;
import View.SearchCommandes.SearchCommande;
import View.SearchDevis.SearchDevis;

public class ActionRechercher implements ActionListener {

	private Object classe;
	private String f;
	private String type;
	private JFrame fenetre;

	public ActionRechercher(Object d, JFrame frame, String fonction, String typeClasse) {
		this.classe = d;
		this.f = fonction;
		this.type = typeClasse;
		this.fenetre = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (f.equals("Modif")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications ?",
					"Quitter " + type, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ValiderModif(classe, type).valider();
			}
			if (option != JOptionPane.CANCEL_OPTION) {
				if (type.equals("Devis")) {
					((Devis) classe).dispose();
					new SearchClient(((Devis) classe).getBase(), fenetre, f);
				} else if (type.equals("Client")) {
					((Client) classe).dispose();
					new SearchClient(((Client) classe).getBase(), null, f);
				} else if (type.equals("Commandes")) {
					((Commandes) classe).dispose();
					new SearchClient(((Commandes) classe).getBase(), fenetre, f);
				}
			}
		} else if (f.equals("Suppr")) {
			if (type.equals("Devis")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le devis ?", "Quitter devis",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					new ValiderSuppr(classe, type).suppr();
				}
				if (option != JOptionPane.CANCEL_OPTION) {
					((Devis) classe).dispose();
					new SearchClient(((Devis) classe).getBase(), null, f);
				}
			} else if (type.equals("Client")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le client ?", "Quitter client",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					new ValiderSuppr(classe, type).suppr();
				}
				if (option != JOptionPane.CANCEL_OPTION) {
					((Client) classe).dispose();
					new SearchClient(((Client) classe).getBase(), null, f);
				}
			} else if (type.equals("Commandes")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer la commande ?",
						"Quitter Commandes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					new ValiderSuppr(classe, type).suppr();
				}
				if (option != JOptionPane.CANCEL_OPTION) {
					((Commandes) classe).dispose();
					new SearchClient(((Commandes) classe).getBase(), null, f);
				}
			}

		}
		else if (f.equals("Recherche")) {
			if (type.equals("Devis")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le devis ?", "Quitter devis",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					((Devis) classe).dispose();
					new SearchDevis(((Devis) classe).getBase(), fenetre, f);
				}
			} else if (type.equals("Client")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter la fiche client ?", "Quitter client",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					((Client)classe).dispose();
					new SearchClient(((Client) classe).getBase(), null, f);
				}
			} else if (type.equals("Commandes")) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter la commande ?", "Quitter commande",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					((Commandes)classe).dispose();
					new SearchCommande(((Commandes) classe).getBase(), fenetre, f);
				}
			}
		}
	}
}
