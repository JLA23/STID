package Model;

import javax.swing.JFormattedTextField;

import View.Factures.Factures;

public class Calcul {
	
	public void calculerMontant(JFormattedTextField jFournitures, JFormattedTextField jCout, JFormattedTextField jPrefabrication, JFormattedTextField jTotalDevis, JFormattedTextField jTotalDevisDevise, Double valeurDevise) {
		double calcule = Double.parseDouble(jFournitures.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jCout.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jPrefabrication.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		jTotalDevis.setText((calcule + "").replaceAll("\\.", ","));
		calcule = Math.round(((calcule * valeurDevise) + 0.004) * Math.pow(10, 2)) / Math.pow(10, 2);
		jTotalDevisDevise.setText((calcule + "").replaceAll("\\.", ","));
	}
	
	public void calculerHeures(JFormattedTextField jHeureSite, JFormattedTextField jHeureAtelier, JFormattedTextField jTotalHeure) {
		double calcule = Double.parseDouble(jHeureSite.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				+ Double.parseDouble(jHeureAtelier.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		jTotalHeure.setText((calcule + "").replaceAll("\\.", ","));
	}
	
	public void calculerResteCommande(JFormattedTextField jPrevu, JFormattedTextField jCommande, JFormattedTextField jResteCommande) {
		double calcule = Double.parseDouble(jPrevu.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				- Double.parseDouble(jCommande.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		jResteCommande.setText((calcule + "").replaceAll("\\.", ","));
	}
	
	public void calculerMontantTTC(JFormattedTextField jFournitures, JFormattedTextField jCout, JFormattedTextField jPrefabrication, JFormattedTextField jTotalDevis, JFormattedTextField jTotalDevisTTC, JFormattedTextField jTotalDevisDevise, Double valeurDevise, Double tva, Factures facture) {
		double calcule = Double.parseDouble(jFournitures.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jCout.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jPrefabrication.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		jTotalDevis.setText((calcule + "").replaceAll("\\.", ","));
		double tvaRecup = (calcule * tva)/100;
		tvaRecup = Math.round((tvaRecup) * Math.pow(10, 2)) / Math.pow(10, 2);
		calcule = Math.round((calcule + tvaRecup)*Math.pow(10, 2))/Math.pow(10, 2);
		jTotalDevisTTC.setText((calcule + "").replaceAll("\\.", ","));
		calcule = Math.round(((calcule * valeurDevise)) * Math.pow(10, 2)) / Math.pow(10, 2);
		jTotalDevisDevise.setText((calcule + "").replaceAll("\\.", ","));
		facture.setRecupTVA(tvaRecup);
	}
	
}
