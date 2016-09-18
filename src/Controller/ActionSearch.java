package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Impression.Clients.EtatsClients;
import View.Impression.Commandes.EtatsCommandes;
import View.Impression.Devis.EtatsDevis;
import View.Impression.Factures.EtatsFactures;
import View.Impression.Pointage.EtatsHeureSpePointage;
import View.Parameters.Categorie;
import View.Pointage.SaisiePointage;
import View.SearchClients.SearchClientList;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevisPointagePrint;
import View.SearchParameters.SearchSalarieList;
import fr.julien.autocomplete.view.AutoComplete;

public class ActionSearch implements ActionListener {
	protected Object classe;
	protected String type, fonction;
	protected AutoComplete complete;

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
	public ActionSearch(Object fr, String typeClasse, String fonction, AutoComplete complete) {
		this.classe = fr;
		this.type = typeClasse;
		this.fonction = fonction;
		this.complete = complete;
	}

	public void actionPerformed(ActionEvent e) {
		if(type.equals("Devis")){
			new SearchClientList(((Devis)classe).getBase(), classe, "SearchClient", "Devis", ((Devis)classe).getNumClient());
		}
		else if(type.equals("Commandes")){
			new SearchClientList(((Commandes)classe).getBase(), classe, "SearchClient", "Commandes", ((Commandes)classe).getNumClient());
			
		}
		else if(type.equals("Pointage")){
			if(fonction.equals("NumDevis")){
				new SearchDevisPointagePrint(((SaisiePointage)classe).getBase(), ((SaisiePointage)classe), type, ((SaisiePointage)classe).getjNumDevis());
			}
			else if(fonction.equals("NumCode")){
				new SearchSalarieList(((SaisiePointage)classe).getBase(), ((SaisiePointage)classe), "Pointage Code", ((SaisiePointage)classe).getjCode());
			}
		}
		else if(type.equals("EtatsDevis")){
			if(fonction.equals("NumDevis")){
				new SearchDevisPointagePrint(((EtatsDevis)classe).getBdd(), ((EtatsDevis)classe), type, complete);
			}
			else if(fonction.equals("NumClient")){
				new SearchClientList(((EtatsDevis)classe).getBdd(), ((EtatsDevis)classe), type, complete);
			}
		}
		else if(type.equals("EHSP")){
			if(fonction.equals("NumPersonnel")){
				new SearchSalarieList(((EtatsHeureSpePointage)classe).getBdd(), ((EtatsHeureSpePointage)classe), type, complete);
			}
			else if(fonction.equals("NumCategorie")){
				new Categorie(((EtatsHeureSpePointage)classe).getBdd(), ((EtatsHeureSpePointage)classe), type, complete);
			}
			else if(fonction.equals("NumDevis")){
				new SearchDevisPointagePrint(((EtatsHeureSpePointage)classe).getBdd(), ((EtatsHeureSpePointage)classe), type, complete);
			}
			else if(fonction.equals("NumCommande")){
				new SearchCommandeList(((EtatsHeureSpePointage)classe).getBdd(), ((EtatsHeureSpePointage)classe), type, complete);
			}
		}
		else if(type.equals("EtatsCommandes")){
			if(fonction.equals("NumCommandes")){
				new SearchCommandeList(((EtatsCommandes)classe).getBdd(), ((EtatsCommandes)classe), type, complete);
			}
			else if(fonction.equals("NumClient")){
				new SearchClientList(((EtatsCommandes)classe).getBdd(), ((EtatsCommandes)classe), type, complete);
			}
		}
		
		else if(type.equals("EtatsClients")){
			if(fonction.equals("NumClient")){
				new SearchClientList(((EtatsClients)classe).getBdd(), ((EtatsClients)classe), type, complete);
			}
		}
		else if(type.equals("EtatsFactures")){
			if(fonction.equals("NumClient")){
				new SearchClientList(((EtatsFactures)classe).getBdd(), ((EtatsFactures)classe), type, complete);
			}
		}
		
	}
}
