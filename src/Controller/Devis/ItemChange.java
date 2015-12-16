package Controller.Devis;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Model.Calcul;
import View.Devis.Devis;

public class ItemChange implements ItemListener {

	protected Devis devis;

	public ItemChange(Devis frame) {
		this.devis = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		devis.setValeurDevise(Double.parseDouble((devis.getValeurDevises().get(devis.getDevises().getSelectedItem().toString()))[2]));
		devis.getDevise().setText(devis.getDevises().getSelectedItem().toString());
		devis.repaint();
		new Calcul().calculerDevis(devis);
	}
}