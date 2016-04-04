package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Pointage.SaisiePointage;
import View.SearchClients.SearchClientList;
import View.SearchDevis.SearchDevisPointage;
import View.SearchParameters.SearchSalarieList;

public class ActionSearch implements ActionListener {
	protected Object classe;
	protected String type, fonction;

	public ActionSearch(Object fr, String typeClasse) {
		this.classe = fr;
		this.type = typeClasse;
		this.fonction = null;
	}
	public ActionSearch(Object fr, String typeClasse, String fonction) {
		this.classe = fr;
		this.type = typeClasse;
		this.fonction = fonction;
	}

	public void actionPerformed(ActionEvent e) {
		if(type.equals("Devis")){
			new SearchClientList(((Devis)classe).getBase(), null, "SearchClient",((Devis)classe).getNumClient());
		}
		else if(type.equals("Commandes")){
			new SearchClientList(((Commandes)classe).getBase(), null, "SearchClient", ((Commandes)classe).getNumClient());
		}
		else if(type.equals("Pointage")){
			if(fonction.equals("NumDevis")){
				new SearchDevisPointage(((SaisiePointage)classe).getBase(), ((SaisiePointage)classe), ((SaisiePointage)classe).getjNumDevis());
			}
			else if(fonction.equals("NumCode")){
				new SearchSalarieList(((SaisiePointage)classe).getBase(), ((SaisiePointage)classe), "Pointage Code", ((SaisiePointage)classe).getjCode());
			}
		}
	}
}
