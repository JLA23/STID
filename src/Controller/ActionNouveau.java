package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import View.Pointage.SaisiePointage;
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
		else if (typeClasse.equals("Pointage")){
			SaisiePointage pointage = ((SaisiePointage)frame);
			if(!pointage.getjNumDevis().getZoneTexte().getText().equals("") && !pointage.getjNumDevis().getZoneTexte().equals("(vide)") && pointage.getDonnees().exist("devis", "NumDevis", "NumDevis = " + pointage.getjNumDevis().getZoneTexte().getText())){
				if (!pointage.getjCode().getZoneTexte().getText().equals("") && !pointage.getjCode().getZoneTexte().equals("(vide)") && pointage.getDonnees().exist("personne", "NumPersonnel", "NumPersonnel = " + pointage.getjCode().getZoneTexte().getText())){
					if(!pointage.getjHN().getText().equals("0,00")){
						if(!pointage.getjHN().getText().contains("-") && !pointage.getjHSC125().getText().contains("-") && !pointage.getjHSC15().getText().contains("-")&& !pointage.getjHSC2().getText().contains("-")){
							if(pointage.getDonnees().exist("pointage", "NumPersonnel, NumDevis, NumSem, Annee", "NumPersonnel = " + pointage.getjCode().getText() + " AND NumDevis = " + pointage.getjNumDevis().getText() + " AND NumSem = " + new SimpleDateFormat("ww").format(pointage.getjDate().getDate()) + " AND Annee = " + new SimpleDateFormat("yyyy").format(pointage.getjDate().getDate()))){
								pointage.getBase().update("pointage", "HrPassNormal = " + pointage.getjHN().getText().replaceAll(",", "\\.") + ", HrPassSup = " + pointage.getjHSC125().getText().replaceAll(",", "\\.") + ", HrPassSup50 = " + pointage.getjHSC15().getText().replaceAll(",", "\\.") + ", HrPassSup25 = " + pointage.getjHSC2().getText().replaceAll(",", "\\."), "NumPersonnel = " + pointage.getjCode().getText() + " AND NumDevis = " + pointage.getjNumDevis().getText() + " AND NumSem = " + new SimpleDateFormat("ww").format(pointage.getjDate().getDate()) + " AND Annee = " + new SimpleDateFormat("yyyy").format(pointage.getjDate().getDate()));
							}
							else{
								pointage.getBase().insert("pointage", pointage.getjCode().getZoneTexte().getText() + ", " + pointage.getjNumDevis().getZoneTexte().getText() + ", " + new SimpleDateFormat("ww").format(pointage.getjDate().getDate()) + ", " + new SimpleDateFormat("yyyy").format(pointage.getjDate().getDate()) +  ", " + pointage.getjHN().getText().replaceAll(",", "\\.") + ", " + pointage.getjHSC125().getText().replaceAll(",", "\\.") + ", " + pointage.getjHSC15().getText().replaceAll(",", "\\.") + ", " + pointage.getjHSC2().getText().replaceAll(",", "\\."));
							}
							pointage.getjNumDevis().getZoneTexte().setText("");
							pointage.getjCode().getZoneTexte().setText("");
							pointage.getjHN().setText("0,00");
							pointage.getjHSC125().setText("0,00");
							pointage.getjHSC15().setText("0,00");
							pointage.getjHSC2().setText("0,00");
							pointage.getjDate().setDate(new Date());
							pointage.repaint();
						}
						else{
							JOptionPane.showMessageDialog(null, "Erreur : Pas de valeurs négatif possible", "ATTENTION",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Erreur : Heures normales sont à 0", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Erreur : Aucun Salarié Selectionné ou existant", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Aucun Devis Selectionné ou existant", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
