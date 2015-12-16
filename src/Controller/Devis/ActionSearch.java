package Controller.Devis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Clients.SearchClient;
import View.Devis.Devis;

public class ActionSearch implements ActionListener {
	protected Devis devis;

	public ActionSearch(Devis fr) {
		this.devis = fr;
	}

	public void actionPerformed(ActionEvent e) {
		new SearchClient(devis.getBase(), devis.getNumClient(), devis, true).searchClientNum(devis.getListClient());
	}
}
