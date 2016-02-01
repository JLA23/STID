package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Controller.Client.NewClient.ActionValiderClient;
import Controller.Devis.NewDevis.ActionValiderDevis;
import Controller.Termes.NewTerme.ActionValiderTerme;
import Controller.Commandes.NewCommande.ActionValiderCommande;
import View.Clients.Client;
import View.Clients.NewClient;
import View.Commandes.Commandes;
import View.Commandes.NewCommande;
import View.Devis.Devis;
import View.Devis.NewDevis;
import View.SearchCommandes.SearchCommande;
import View.Termes.Termes;

public class ActionNouveau implements ActionListener {

	private Object frame;
	private String typeClasse;

	public ActionNouveau(Object classe, String type) {
		this.frame = classe;
		this.typeClasse = type;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (typeClasse.equals("Client")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le client ?", "Quitter " + typeClasse,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValiderClient(((Client)frame)).valider();
				if (!((Client)frame).isShowing()) {
					new NewClient(((Client)frame).getBase());
				}
			}
			else if (option == JOptionPane.NO_OPTION) {
				((Client)frame).dispose();
				if (!((Client)frame).isShowing()) {
					new NewClient(((Client)frame).getBase());
				}
			}
		}
		else if (typeClasse.equals("Devis")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le devis ?", "Quitter " + typeClasse,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValiderDevis(((Devis)frame)).valider();
				if (!((Devis)frame).isShowing()) {
					new NewDevis(((Devis)frame).getBase(), ((Devis)frame).getFenetre());
				}
			}
			else if (option == JOptionPane.NO_OPTION) {
				((Devis)frame).dispose();
				((Devis)frame).getFenetre().setEnabled(true);
				((Devis)frame).getFenetre().setVisible(true);
				if (!((Devis)frame).isShowing()) {
					new NewDevis(((Devis)frame).getBase(),((Devis)frame).getFenetre());
				}
			}
		}
		else if (typeClasse.equals("Commandes")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer la commande ?", "Quitter " + typeClasse,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValiderCommande(((Commandes)frame)).valider();
				if (!((Commandes)frame).isShowing()) {
					new NewCommande(((Commandes)frame).getBase(), ((Commandes)frame).getFenetre());
				}
			}
			else if (option == JOptionPane.NO_OPTION) {
				((Commandes)frame).dispose();
				((Commandes)frame).getFenetre().setEnabled(true);
				((Commandes)frame).getFenetre().setVisible(true);
				if (!((Commandes)frame).isShowing()) {
					new NewCommande(((Commandes)frame).getBase(), ((Commandes)frame).getFenetre());
				}
			}
		}
		else if (typeClasse.equals("Termes")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le terme ?", "Quitter " + typeClasse,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValiderTerme(((Termes)frame)).valider();
				if (!((Termes)frame).isShowing()) {
					new SearchCommande(((Termes)frame).getBase(),((Termes)frame).getFenetre(), "NewTerme");
				}
			}
			else if (option == JOptionPane.NO_OPTION) {
				((Termes)frame).dispose();
				((Termes)frame).getFenetre().setEnabled(true);
				((Termes)frame).getFenetre().setVisible(true);
				if (!((Termes)frame).isShowing()) {
					new SearchCommande(((Termes)frame).getBase(), ((Termes)frame).getFenetre(), "NewTerme");
				}
			}
		}
	}
}
