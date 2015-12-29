package Model;

import View.Commandes.Commandes;
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
	
	public void calculerCommande(Commandes commande) {
		double calcule = Double.parseDouble(commande.getjFournitures().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(commande.getjCout().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(commande.getjPrefabrication().getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		commande.getjTotalDevis().setText((calcule + "").replaceAll("\\.", ","));
		calcule = Math.round(((calcule * commande.getValeurDevise()) + 0.004) * Math.pow(10, 2)) / Math.pow(10, 2);
		commande.getjTotalDevisDevise().setText((calcule + "").replaceAll("\\.", ","));
	}

	public void calculerHeures(Commandes commande) {
		double calcule = Double.parseDouble(commande.getjHeureSite().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				+ Double.parseDouble(commande.getjHeureAtelier().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		commande.getjTotalHeure().setText((calcule + "").replaceAll("\\.", ","));
	}

	public void calculerResteCommande(Commandes commande) {
		double calcule = Double.parseDouble(commande.getjPrevu().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule
				- Double.parseDouble(commande.getjCommande().getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10, 2)) / Math.pow(10, 2);
		commande.getjResteCommande().setText((calcule + "").replaceAll("\\.", ","));
	}
	
}
