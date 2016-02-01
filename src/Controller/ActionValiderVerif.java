package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;

import BDD.Base;
import Model.Donnees;
import View.Clients.ModifClient;
import View.Clients.SupprClient;
import View.Commandes.LookCommande;
import View.Commandes.ModifCommande;
import View.Commandes.SupprCommande;
import View.Devis.LookDevis;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;
import View.SearchClients.SearchClient;
import View.SearchCommandes.SearchCommande;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevis;
import View.SearchTerme.SearchTerme;
import View.SearchTerme.SearchTermeList;
import View.Termes.ModifTerme;
import View.Termes.NewTerme;

public class ActionValiderVerif implements ActionListener {
	private Object fr;
	private String type;

	public ActionValiderVerif(Object frame, String classe) {
		this.fr = frame;
		this.type = classe;
	}

	public void actionPerformed(ActionEvent e) {
		if (type.equals("Client")) {
			verifClient();
		}
		else if(type.equals("Devis")){
			verifDevis();
		}
		else if(type.equals("Commandes")){
			verifCommandes();
		}
		else if(type.equals("Termes")){
			verifTermes();
		}

	}
	
	private void verifClient(){
		Base bdd = ((SearchClient) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (donnees.exist("Clients", "NumClient", "NumClient = " + ((SearchClient) fr).getNumClient().getText())) {
			((SearchClient) fr).dispose();
			if (((SearchClient) fr).getF().equals("Modif")) {
				new ModifClient(bdd, ((SearchClient) fr).getNumClient().getText());
			} 
			else if (((SearchClient) fr).getF().equals("Suppr")) {
				if (!donnees.lier("numClient", "Devis", "numClient = " + ((SearchClient) fr).getNumClient().getText())) {
					new SupprClient(bdd, ((SearchClient) fr).getNumClient().getText());
				} else {
					JOptionPane.showMessageDialog(null, "Le client ne peut être supprimé", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucun Client avec ce numèro !", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void verifDevis(){
		Base bdd = ((SearchDevis)fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if(donnees.exist("Devis", "NumDevis", "numDevis = " + ((SearchDevis)fr).getNumDevis().getText())){
			((SearchDevis)fr).dispose();
			try {
				if(((SearchDevis)fr).getF().equals("Modif")){
					new ModifDevis(bdd, ((SearchDevis)fr).getNumDevis().getText(), ((SearchDevis)fr).getFr());
				}
				else if(((SearchDevis)fr).getF().equals("Suppr")){
					new SupprDevis(bdd, ((SearchDevis)fr).getNumDevis().getText(), ((SearchDevis)fr).getFr());
				}
				else if(((SearchDevis)fr).getF().equals("Recherche")){
					new LookDevis(bdd,((SearchDevis)fr).getNumDevis().getText(), ((SearchDevis)fr).getFr());
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Aucun Devis avec ce numéro !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void verifCommandes(){
		Base bdd = ((SearchCommande)fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (!((SearchCommande)fr).getNumCom().getText().isEmpty() && ((SearchCommande)fr).getNumComClient().getText().isEmpty()) {
			if(donnees.exist("Commandes", "NumCommande", "NumCommande = " + ((SearchCommande)fr).getNumCom().getText())) {
				((SearchCommande)fr).dispose();
				try {
					if (((SearchCommande)fr).getF().equals("Modif")) {
						new ModifCommande(bdd, ((SearchCommande)fr).getNumCom().getText(), ((SearchCommande)fr).getFr());
					} else if (((SearchCommande)fr).getF().equals("Suppr")) {
						new SupprCommande(bdd, ((SearchCommande)fr).getNumCom().getText(), ((SearchCommande)fr).getFr());
					} else if (((SearchCommande)fr).getF().equals("Recherche")) {
						new LookCommande(bdd, ((SearchCommande)fr).getNumCom().getText(),((SearchCommande)fr).getFr());
					} else if (((SearchCommande)fr).getF().equals("NewTerme")) {
						new NewTerme(bdd, ((SearchCommande)fr).getFr(),((SearchCommande)fr).getNumCom().getText());
					}					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchCommande)fr).getNumCom().getText().isEmpty() && !((SearchCommande)fr).getNumComClient().getText().isEmpty()) {
			if (donnees.exist("Commandes", "NumCommande", "CdeComClient = " + ((SearchCommande)fr).getNumComClient().getText())) {
				((SearchCommande)fr).dispose();
				if (donnees.existPlusieur("numCommande", "Commandes", "CdeComClient = '" + ((SearchCommande)fr).getNumComClient().getText() + "'")) {
					new SearchCommandeList(bdd, ((SearchCommande)fr).getFr(), ((SearchCommande)fr).getF(), ((SearchCommande)fr).getNumComClient().getText());
				} else {
					try{
						if (((SearchCommande)fr).getF().equals("Modif")) {
							new ModifCommande(bdd, donnees.searchNumCommandeClient(((SearchCommande)fr).getNumComClient().getText()), ((SearchCommande)fr).getFr());
						} else if (((SearchCommande)fr).getF().equals("Suppr")) {
						new SupprCommande(bdd, donnees.searchNumCommandeClient(((SearchCommande)fr).getNumComClient().getText()), ((SearchCommande)fr).getFr());
						} else if (((SearchCommande)fr).getF().equals("Recherche")) {
							new LookCommande(bdd, donnees.searchNumCommandeClient(((SearchCommande)fr).getNumComClient().getText()), ((SearchCommande)fr).getFr());
						}
					}catch(ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande client avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (!((SearchCommande)fr).getNumCom().getText().isEmpty() && !((SearchCommande)fr).getNumComClient().getText().isEmpty()) {
			if (donnees.exist("Commandes", "NumCommande", "NumCommane = " + ((SearchCommande)fr).getNumCom().getText() + " AND CdeComClient = " + ((SearchCommande)fr).getNumComClient().getText())) {
				((SearchCommande)fr).dispose();
				
					try {
						if (((SearchCommande)fr).getF().equals("Modif")) {
							new ModifCommande(bdd, ((SearchCommande)fr).getNumCom().getText(), ((SearchCommande)fr).getFr());
						} else if (((SearchCommande)fr).getF().equals("Suppr")) {
							new SupprCommande(bdd, ((SearchCommande)fr).getNumCom().getText(), ((SearchCommande)fr).getFr());
						} else if (((SearchCommande)fr).getF().equals("Recherche")) {
							new LookCommande(bdd, ((SearchCommande)fr).getNumCom().getText(), ((SearchCommande)fr).getFr());
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ces numéros !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchCommande)fr).getNumCom().getText().isEmpty() && ((SearchCommande)fr).getNumComClient().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void verifTermes(){
		Base bdd = ((SearchTerme)fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (!((SearchTerme)fr).getNumCom().getText().isEmpty() && ((SearchTerme)fr).getNumIndice().getText().isEmpty()) {
			if(donnees.exist("Termes", "NumCommande", "NumCommande = " + ((SearchTerme)fr).getNumCom().getText())) {
				((SearchTerme)fr).dispose();
				if (donnees.existPlusieur("NumIndice", "Termes", "NumCommande = " + ((SearchTerme)fr).getNumCom().getText())) {
					new SearchTermeList(bdd, ((SearchTerme)fr).getFr(), ((SearchTerme)fr).getF(), ((SearchTerme)fr).getNumCom().getText());
				}
				else{
				try {
					if (((SearchTerme)fr).getF().equals("Modif")) {
						new ModifTerme(bdd, ((SearchTerme)fr).getFr(),((SearchTerme)fr).getNumCom().getText(), ((SearchTerme)fr).getNumIndice().getText());
					} else if (((SearchTerme)fr).getF().equals("Suppr")) {
						new SupprCommande(bdd, ((SearchTerme)fr).getNumCom().getText(), ((SearchTerme)fr).getFr());
					} else if (((SearchTerme)fr).getF().equals("Recherche")) {
						new LookCommande(bdd, ((SearchTerme)fr).getNumCom().getText(),((SearchTerme)fr).getFr());
					} else if (((SearchTerme)fr).getF().equals("NewTerme")) {
						new NewTerme(bdd, ((SearchTerme)fr).getFr(),((SearchTerme)fr).getNumCom().getText());
					}					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchTerme)fr).getNumCom().getText().isEmpty() && !((SearchTerme)fr).getNumIndice().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut un numéro de Commande !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
		} else if (!((SearchTerme)fr).getNumCom().getText().isEmpty() && !((SearchTerme)fr).getNumIndice().getText().isEmpty()) {
			if (donnees.exist("Termes", "NumCommande, NumIndice", "NumCommande = " + ((SearchTerme)fr).getNumCom().getText() + " AND numIndice = " + ((SearchTerme)fr).getNumIndice().getText())) {
				((SearchTerme)fr).dispose();
				
					try {
						if (((SearchTerme)fr).getF().equals("Modif")) {
							new ModifTerme(bdd, ((SearchTerme)fr).getFr(),((SearchTerme)fr).getNumCom().getText(), ((SearchTerme)fr).getNumIndice().getText());
						} else if (((SearchTerme)fr).getF().equals("Suppr")) {
							new SupprCommande(bdd, ((SearchTerme)fr).getNumCom().getText(), ((SearchTerme)fr).getFr());
						} else if (((SearchTerme)fr).getF().equals("Recherche")) {
							new LookCommande(bdd, ((SearchTerme)fr).getNumCom().getText(), ((SearchTerme)fr).getFr());
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ces numéros !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchTerme)fr).getNumCom().getText().isEmpty() && ((SearchTerme)fr).getNumComClient().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
