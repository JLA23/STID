package Controller.Commandes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Model.Calcul;
import View.Commandes.Commandes;

public class ItemChange implements ItemListener {

	protected Commandes commande;

	public ItemChange(Commandes frame) {
		this.commande = frame;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		commande.setValeurDevise(Double.parseDouble((commande.getValeurDevises().get(commande.getDevises().getSelectedItem().toString()))[2]));
		commande.getDevise().setText(commande.getDevises().getSelectedItem().toString());
		commande.repaint();
		new Calcul().calculerCommande(commande);
	}
}