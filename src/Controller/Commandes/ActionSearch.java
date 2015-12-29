package Controller.Commandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Commandes.Commandes;
import View.Devis.SearchClient;

public class ActionSearch implements ActionListener {
	protected Commandes devis;

	public ActionSearch(Commandes fr) {
		this.devis = fr;
	}

	public void actionPerformed(ActionEvent e) {
		new SearchClient(devis.getBase(), devis.getNumClient(), null, true).searchClientNum(devis.getListClient());
	}
}
