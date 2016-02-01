package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Model.Calcul;
import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Termes.Termes;

public class ItemChange implements ItemListener {

	protected Object classe;
	protected String type;

	public ItemChange(Object frame, String typeClasse) {
		this.classe = frame;
		this.type = typeClasse;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (type.equals("Devis")) {
			((Devis)classe).setValeurDevise(Double.parseDouble((((Devis)classe).getValeurDevises().get(((Devis)classe).getDevises().getSelectedItem().toString()))[2]));
			((Devis)classe).getDevise().setText(((Devis)classe).getDevises().getSelectedItem().toString());
			((Devis)classe).repaint();
			new Calcul().calculerMontant(((Devis)classe).getjFournitures(), ((Devis)classe).getjCout(), ((Devis)classe).getjPrefabrication(),
					((Devis)classe).getjTotalDevis(), ((Devis)classe).getjTotalDevisDevise(), ((Devis)classe).getValeurDevise());
		}
		else if(type.equals("Client")){
			((Client)classe).getTaux().setText("Taux TVA : "+ ((Client)classe).getValeurTaux().get(((Client)classe).getBoxTva().getSelectedItem())[1] + " %");
		}
		
		else if(type.equals("Commandes")){
			((Commandes)classe).setValeurDevise(Double.parseDouble((((Commandes)classe).getValeurDevises().get(((Commandes)classe).getDevises().getSelectedItem().toString()))[2]));
			((Commandes)classe).getDevise().setText(((Commandes)classe).getDevises().getSelectedItem().toString());
			((Commandes)classe).repaint();
			new Calcul().calculerMontant(((Commandes)classe).getjFournitures(), ((Commandes)classe).getjCout(), ((Commandes)classe).getjPrefabrication(), ((Commandes)classe).getjTotalDevis(), ((Commandes)classe).getjTotalDevisDevise(), ((Commandes)classe).getValeurDevise());
		}
		else if(type.equals("Termes")){
			((Termes)classe).setValeurDevise(Double.parseDouble((((Termes)classe).getValeurDevises().get(((Termes)classe).getDevises().getSelectedItem().toString()))[2]));
			((Termes)classe).getDevise().setText(((Termes)classe).getDevises().getSelectedItem().toString());
			((Termes)classe).repaint();
			new Calcul().calculerMontant(((Termes)classe).getjFournitures(), ((Termes)classe).getjCout(), ((Termes)classe).getjPrefabrication(), ((Termes)classe).getjTotalDevis(), ((Termes)classe).getjTotalDevisDevise(), ((Termes)classe).getValeurDevise());
		}
	}
}