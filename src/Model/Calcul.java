package Model;

import View.Devis.Devis;

public class Calcul {
	
	public void calculerDevis(Devis devis) {
		double calcule = Double.parseDouble(devis.getjFournitures().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(devis.getjCout().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(devis.getjPrefabrication().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		devis.getjTotalDevis().setText((calcule + "").replaceAll("\\.", ","));
		calcule = Math.round(((calcule * devis.getValeurDevise()) + 0.004) * Math.pow(10, 2)) / Math.pow(10, 2);
		devis.getjTotalDevisDevise().setText((calcule + "").replaceAll("\\.", ","));
	}

	public void calculerHeures(Devis devis) {
		double calcule = Double.parseDouble(devis.getjHeureSite().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				+ Double.parseDouble(devis.getjHeureAtelier().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		devis.getjTotalHeure().setText((calcule + "").replaceAll("\\.", ","));
	}

	public void calculerResteCommande(Devis devis) {
		double calcule = Double.parseDouble(devis.getjPrevu().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				- Double.parseDouble(devis.getjCommande().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		devis.getjResteCommande().setText((calcule + "").replaceAll("\\.", ","));
	}

}
