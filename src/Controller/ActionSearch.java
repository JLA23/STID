package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.SearchClients.SearchClientList;

public class ActionSearch implements ActionListener {
	protected Object classe;
	protected String type;

	public ActionSearch(Object fr, String typeClasse) {
		this.classe = fr;
		this.type = typeClasse;
	}

	public void actionPerformed(ActionEvent e) {
		if(type.equals("Devis")){
			new SearchClientList(((Devis)classe).getBase(), null, "SearchClient",((Devis)classe).getNumClient());
		}
		else if(type.equals("Commandes")){
			new SearchClientList(((Commandes)classe).getBase(), null, "SearchClient", ((Commandes)classe).getNumClient());
		}
	}
}
