package Controller;

import javax.swing.JFormattedTextField;

import Model.Calcul;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Factures.Factures;
import View.Termes.Termes;

public class TestContenu {

	public TestContenu(Object d, JFormattedTextField jtext, int methode, String typeClasse) {
		if (jtext.getText().toString().isEmpty() || jtext.getText().toString().equals("")) {
			jtext.setText("0,00");
		} else if(jtext.getText().matches(".*[a-zA-Z].*")){
			jtext.setText("0,00");
		}
		else if(!jtext.getText().matches(".*[a-zA-Z].*") && jtext.getText().contains(".")){
			jtext.setText(jtext.getText().replaceAll("\\.", ","));
		}
		else if (!jtext.getText().contains(",") && !jtext.getText().contains(".")) {
			jtext.setText(jtext.getText() + ",00");
		}
		else if (jtext.getText().contains(",") && jtext.getText().split(",").length == 1){
				jtext.setText(jtext.getText() + "00");
		}
		 else if (jtext.getText().contains(",") && jtext.getText().split(",")[1].length() == 1){
			jtext.setText(jtext.getText() + "0");
		}
		else {
			jtext.setText((Math
					.round(((Double.parseDouble(jtext.getText().replaceAll(",", "\\.")))) * Math.pow(10, 2))
					/ Math.pow(10, 2) + "").replaceAll("\\.", ","));
		}
		if (typeClasse != null && typeClasse.equals("Devis")) {
			if (methode == 1) {
				new Calcul().calculerMontant(((Devis) d).getjFournitures(), ((Devis) d).getjCout(),
						((Devis) d).getjPrefabrication(), ((Devis) d).getjTotalDevis(),
						((Devis) d).getjTotalDevisDevise(), ((Devis) d).getValeurDevise());
			} else if (methode == 2) {
				new Calcul().calculerHeures(((Devis) d).getjHeureSite(), ((Devis) d).getjHeureAtelier(),
						((Devis) d).getjTotalHeure());
			} else if (methode == 3) {
				new Calcul().calculerResteCommande(((Devis) d).getjPrevu(), ((Devis) d).getjCommande(),
						((Devis) d).getjResteCommande());
			}
		}
		else if (typeClasse != null && typeClasse.equals("Commandes")) {
			if (methode == 1) {
				new Calcul().calculerMontant(((Commandes) d).getjFournitures(), ((Commandes) d).getjCout(),
						((Commandes) d).getjPrefabrication(), ((Commandes) d).getjTotalDevis(),
						((Commandes) d).getjTotalDevisDevise(), ((Commandes) d).getValeurDevise());
			} else if (methode == 2) {
				new Calcul().calculerHeures(((Commandes) d).getjHeureSite(), ((Commandes) d).getjHeureAtelier(),
						((Commandes) d).getjTotalHeure());
			} else if (methode == 3) {
				new Calcul().calculerResteCommande(((Commandes) d).getjPrevu(), ((Commandes) d).getjCommande(),
						((Commandes) d).getjResteCommande());
			}
		}
		
		else if (typeClasse != null && typeClasse.equals("Termes")) {
			if (methode == 1) {
				new Calcul().calculerMontant(((Termes) d).getjFournitures(), ((Termes) d).getjCout(),
						((Termes) d).getjPrefabrication(), ((Termes) d).getjTotalDevis(),
						((Termes) d).getjTotalDevisDevise(), ((Termes) d).getValeurDevise());
			}
		}
		else if (typeClasse != null && typeClasse.equals("Factures")) {
			if (methode == 1) {
				new Calcul().calculerMontantTTC(((Factures) d).getjFournitures(), ((Factures) d).getjCout(),
						((Factures) d).getjPrefabrication(), ((Factures) d).getjTotalHT(),((Factures) d).getjTotalTTC(),
						((Factures) d).getjTotalDevise(), ((Factures) d).getValeurDevise(), Double.parseDouble(((Factures)d).getjTVA().getText().replaceAll(",", "\\.")), ((Factures)d));
			}
		}
	}
}
