package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import BDD.Base;
import View.SearchClients.SearchClientList;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevisList;
import View.SearchFactures.SearchFactureList;
import View.SearchParameters.SearchSalarieList;
import View.SearchTerme.SearchTermeList;

public class ActionList implements ActionListener {
	private JDialog frame;
	private JFrame windo;
	private Base bdd;
	private String fonction;
	private String type;
	
	public ActionList(JDialog dialog, Base base, JFrame fenetre, String f, String typeClasse){
		this.frame = dialog;
		this.bdd = base;
		this.fonction = f;
		this.windo = fenetre;
		this.type = typeClasse;
	}
	public void actionPerformed(ActionEvent e) {
		if(frame != null){
			frame.dispose();
		}
		if(type.equals("Devis")){
			new SearchDevisList(bdd, windo, null, null, fonction);
		}
		else if(type.equals("Client")){
			new SearchClientList(bdd, null, fonction, null);
		}
		else if(type.equals("Commandes")){
			String n = null;
			new SearchCommandeList(bdd, windo, fonction, n);	
		}
		else if(type.equals("Termes")){
			if(fonction.equals("NewTerme")){
				String n = null;
				new SearchCommandeList(bdd, windo, fonction, n);	
			}
			else{
				new SearchTermeList(bdd, windo, fonction, null, null);
			}
		}
		else if(type.equals("Factures")){
			if(fonction.equals("NewFacture")){
				new SearchTermeList(bdd, windo, fonction, null, null);	
			}
			else{
				new SearchFactureList(bdd, windo, fonction, null, null, null);
			}
		}
		else if(type.equals("Salarie")){
			new SearchSalarieList(bdd, windo, fonction);
		}
	}
}
