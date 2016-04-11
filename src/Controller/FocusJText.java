package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Pointage.SaisiePointage;

public class FocusJText implements FocusListener {

	private Object devis;
	private String classe;

	public FocusJText(Object d, String typeClasse) {
		this.devis = d;
		this.classe = typeClasse;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(true);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(false);
		} else if (classe.equals("Pointage")) {
			if (((SaisiePointage) devis).getjNumDevis().getFenetreRecherche().isVisible()) {
				((SaisiePointage) devis).getjNumDevis().getFenetreRecherche().setVisible(false);
			} else if (((SaisiePointage) devis).getjCode().getFenetreRecherche().isVisible()) {
				((SaisiePointage) devis).getjCode().getFenetreRecherche().setVisible(false);
			}
		}
		name();
	}

	public void name() {
		if (classe.equals("Devis")) {
			if (!((Devis)devis).getNumClient().getText().equals("") && !((Devis)devis).getNumClient().getText().equals("(vide)") && ((Devis)devis)
					.getDonnees().exist("clients", "NumClient", "NumClient = " + ((Devis)devis).getNumClient().getText())) {
				int i = 0;
				while (!((Devis)devis).getListClient()[i][0].toString().equals(((Devis)devis).getNumClient().getText())
						&& i < ((Devis)devis).getListClient().length) {
					i++;
				}
				if (i < ((Devis)devis).getListClient().length) {
					((Devis)devis).getNameClient().setText("Client : " + ((Devis)devis).getListClient()[i][1]);
					((Devis)devis).getNameClient().setBounds(((Devis)devis).getNameClient().getX(), ((Devis)devis).getNameClient().getY(),
							((Devis)devis).getNameClient().getPreferredSize().width,
							((Devis)devis).getNameClient().getPreferredSize().height);
				} else {
					((Devis)devis).getNameClient().setText("Error");
					((Devis)devis).getNameClient().setBounds(((Devis)devis).getNameClient().getX(), ((Devis)devis).getNameClient().getY(),
							((Devis)devis).getNameClient().getPreferredSize().width,
							((Devis)devis).getNameClient().getPreferredSize().height);
				}
				((Devis)devis).repaint();
			}
		}
		
		else if (classe.equals("Commandes")) {
			if (!((Commandes)devis).getNumClient().getText().equals("") && !((Commandes)devis).getNumClient().getText().equals("(vide)") && ((Commandes)devis)
					.getDonnees().exist("clients", "NumClient", "NumClient = " + ((Commandes)devis).getNumClient().getText())) {
				int i = 0;
				while (!((Commandes)devis).getListClient()[i][0].toString().equals(((Commandes)devis).getNumClient().getText())
						&& i < ((Commandes)devis).getListClient().length) {
					i++;
				}
				if (i < ((Commandes)devis).getListClient().length) {
					((Commandes)devis).getNameClient().setText("Client : " + ((Commandes)devis).getListClient()[i][1]);
					((Commandes)devis).getNameClient().setBounds(((Commandes)devis).getNameClient().getX(), ((Commandes)devis).getNameClient().getY(),
							((Commandes)devis).getNameClient().getPreferredSize().width,
							((Commandes)devis).getNameClient().getPreferredSize().height);
				} else {
					((Commandes)devis).getNameClient().setText("Error");
					((Commandes)devis).getNameClient().setBounds(((Commandes)devis).getNameClient().getX(), ((Commandes)devis).getNameClient().getY(),
							((Commandes)devis).getNameClient().getPreferredSize().width,
							((Commandes)devis).getNameClient().getPreferredSize().height);
				}
				((Commandes)devis).repaint();
			}
		}
		
		
		else if (classe.equals("Pointage")) {
			if (!((SaisiePointage)devis).getjNumDevis().getZoneTexte().getText().equals("") && !((SaisiePointage)devis).getjNumDevis().getZoneTexte().getText().equals("(vide)") && ((SaisiePointage)devis)
					.getDonnees().exist("devis", "NumDevis", "NumDevis = " + ((SaisiePointage)devis).getjNumDevis().getZoneTexte().getText()) && !((SaisiePointage)devis).getjCode().getZoneTexte().getText().equals("") && !((SaisiePointage)devis).getjCode().getZoneTexte().getText().equals("(vide)") && ((SaisiePointage)devis)
					.getDonnees().exist("personne", "NumPersonnel", "NumPersonnel = " + ((SaisiePointage)devis).getjCode().getZoneTexte().getText())) {
				int i = 0;
				while (!((SaisiePointage)devis).getListDevis()[i][0].toString().equals(((SaisiePointage)devis).getjNumDevis().getText())
						&& i < ((SaisiePointage)devis).getListDevis().length) {
					i++;
				}
				/*if (i <= ((SaisiePointage)devis).getListDevis().length) {
					((SaisiePointage)devis).getNameClient().setText("Client : " + ((SaisiePointage)devis).getListClient()[i][1]);
					((SaisiePointage)devis).getNameClient().setBounds(((SaisiePointage)devis).getNameClient().getX(), ((SaisiePointage)devis).getNameClient().getY(),
							((SaisiePointage)devis).getNameClient().getPreferredSize().width,
							((SaisiePointage)devis).getNameClient().getPreferredSize().height);
				} else {
					((SaisiePointage)devis).getNameClient().setText("Error");
					((SaisiePointage)devis).getNameClient().setBounds(((SaisiePointage)devis).getNameClient().getX(), ((SaisiePointage)devis).getNameClient().getY(),
							((SaisiePointage)devis).getNameClient().getPreferredSize().width,
							((SaisiePointage)devis).getNameClient().getPreferredSize().height);
				}*/
				if(((SaisiePointage)devis).getDonnees().exist("pointage", "NumPersonnel, NumDevis, NumSem, Annee", "NumPersonnel = " + ((SaisiePointage)devis).getjCode().getText() + " AND NumDevis = " + ((SaisiePointage)devis).getjNumDevis().getText() + " AND NumSem = " + new SimpleDateFormat("ww").format(((SaisiePointage)devis).getjDate().getDate()) + " AND Annee = " + new SimpleDateFormat("yyyy").format(((SaisiePointage)devis).getjDate().getDate()))){
					String [] res = ((SaisiePointage)devis).getDonnees().fiche("Round(HrPassNormal, 2), Round(HrPassSup,2), Round(HrPassSup50,2), Round(HrPassSup25,2)", "pointage", "NumPersonnel = " + ((SaisiePointage)devis).getjCode().getText() + " AND NumDevis = " + ((SaisiePointage)devis).getjNumDevis().getText() + " AND NumSem = " + new SimpleDateFormat("ww").format(((SaisiePointage)devis).getjDate().getDate()) + " AND Annee = " + new SimpleDateFormat("yyyy").format(((SaisiePointage)devis).getjDate().getDate()));
					((SaisiePointage)devis).getjHN().setText(res[0].replaceAll("\\.", ","));
					((SaisiePointage)devis).getjHSC125().setText(res[1].replaceAll("\\.", ","));
					((SaisiePointage)devis).getjHSC15().setText(res[2].replaceAll("\\.", ","));
					((SaisiePointage)devis).getjHSC2().setText(res[3].replaceAll("\\.", ","));
				}
				((SaisiePointage)devis).repaint();
			}
		}

	}

}