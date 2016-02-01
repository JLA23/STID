package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.SearchClients.SearchClient;
import View.SearchCommandes.SearchCommande;
import View.SearchDevis.SearchDevis;
import View.SearchList.SearchList;

public class RetourAction implements ActionListener {
	
private SearchList fenetre;
private String f, type;

	public RetourAction(SearchList frame, String classe, String fonction){
		this.fenetre = frame;
		this.f = fonction;
		this.type = classe;
	}
	public void actionPerformed(ActionEvent arg0) {
		fenetre.dispose();
		if(type.equals("Devis")){
			new SearchDevis(fenetre.getBdd(), fenetre.getFrame(), f);
		}
		else if(type.equals("Client")){
			new SearchClient(fenetre.getBdd(), fenetre.getFrame(), f);
		}
		else if(type.equals("Commandes")){
			new SearchCommande(fenetre.getBdd(), fenetre.getFrame(), f);
		}
		else if(type.equals("Termes")){
			
		}
	}

}
