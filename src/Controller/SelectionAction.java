package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import BDD.Base;
import View.Clients.ModifClient;
import View.Clients.SupprClient;
import View.Commandes.LookCommande;
import View.Commandes.ModifCommande;
import View.Commandes.SelectDevis;
import View.Commandes.SupprCommande;
import View.Devis.LookDevis;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;
import View.Factures.NewFacture;
import View.Parameters.Salarie;
import View.Pointage.SaisiePointage;
import View.SearchClients.SearchClientList;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevisList;
import View.SearchDevis.SearchDevisPointage;
import View.SearchParameters.SearchSalarieList;
import View.SearchTerme.SearchTermeList;
import View.Termes.LookTerme;
import View.Termes.ModifTerme;
import View.Termes.NewTerme;
import View.Termes.SupprTerme;
import fr.julien.autocomplete.view.AutoComplete;

public class SelectionAction implements ActionListener {

	private Object clas;
	private String f;
	private String type;
	private Base bdd;
	private JTable tables;
	private Object[][] datas;
	private JFrame fenetre;
	private JDialog dialog;
	private AutoComplete autos;
	private SelectDevis select;
	private SaisiePointage saisie;

	public SelectionAction(Object frame, String classe, String fonction) {
		this.f = fonction;
		this.type = classe;
		this.clas = frame;
	}

	public void actionPerformed(ActionEvent e) {
		initVerif();
		int ligne = tables.getSelectedRow();
		if (ligne != -1) {
			String numero = datas[tables.convertRowIndexToModel(ligne)][0].toString();
			System.out.println(numero);
			dialog.dispose();
			try {
				if (f.equals("Modif")) {
					if (type.equals("Client")) {
						new ModifClient(bdd, numero);
					} else if (type.equals("Devis")) {
						new ModifDevis(bdd, numero, fenetre);
					} else if (type.equals("Commandes")) {
						new ModifCommande(bdd, numero, fenetre);
					} else if (type.equals("Termes")) {
						new ModifTerme(bdd, fenetre, numero, datas[ligne][1].toString());
					} else if (type.equals("Salarie")){
						new Salarie(fenetre, bdd, numero, f);
					}
				} else if (f.equals("Suppr")) {
					if (type.equals("Client")) {
						new SupprClient(bdd, numero);
					} else if (type.equals("Devis")) {
						new SupprDevis(bdd, numero, fenetre);
					} else if (type.equals("Commandes")) {
						new SupprCommande(bdd, numero, fenetre);
					} else if (type.equals("Termes")) {
						new SupprTerme(bdd, fenetre, numero, datas[ligne][1].toString());
					} else if (type.equals("Salarie")){
						new Salarie(fenetre, bdd, numero, f);
					}
				} else if (f.equals("Recherche")) {
					if (type.equals("Client")) {
						// new LookClient(bdd, numero);
					} else if (type.equals("Devis")) {
						new LookDevis(bdd, numero, fenetre);
					} else if (type.equals("Commandes")) {
						new LookCommande(bdd, numero, fenetre);
					} else if (type.equals("Termes")) {
						 new LookTerme(bdd, fenetre, numero, datas[ligne][1].toString());
					}
				} else if (f.equals("SearchClient")) {
					autos.getZoneTexte().setText(numero);
					dialog.dispose();
				} else if (f.equals("NewTerme")){ 
					new NewTerme(bdd, fenetre, numero);
				}  else if (f.equals("NewFacture")){ 
					new NewFacture(bdd, fenetre, numero, datas[ligne][1].toString());
				} else if (f.equals("SelectDevis")) {
					if (!select.containt((String) datas[ligne][0])) {
						select.getModel().addRow(datas[ligne]);
						dialog.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Vous l'avez déjà ajoutè", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);
					}
				} else if (f.equals("Pointage") || f.equals("Pointage Code")){
					autos.getZoneTexte().setText(numero);
					dialog.dispose();
					new FocusJText(saisie, "Pointage").name();
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void initVerif(){
		if(f.equals("SearchClient")){
			SearchClientList search = (SearchClientList)clas;
			dialog = search;
			datas = search.getData();
			autos = search.getAuto();
			tables = search.getLayerTable();
		}
		else if(f.equals("SelectDevis")){
			SearchDevisList search = (SearchDevisList)clas;
			dialog = search;
			datas = search.getData();
			select = search.getSd();
			tables = search.getLayerTable();
		}
		else if(f.equals("NewTerme")){
			SearchCommandeList search = (SearchCommandeList)clas;
			dialog = search;
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
		}
		else if(f.equals("NewFacture")){
			SearchTermeList search = (SearchTermeList)clas;
			dialog = search;
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
		}
		else if(f.equals("Pointage")){
			SearchDevisPointage search = (SearchDevisPointage)clas;
			dialog = search;
			autos = search.getAuto();
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
			saisie = search.getSaisie();
		}
		
		else if(f.equals("Pointage Code")){
			SearchSalarieList search = (SearchSalarieList)clas;
			dialog = search;
			autos = search.getAuto();
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
			saisie = search.getSaisie();
		}
		
		else if(f.equals("Modif") || f.equals("Suppr") || f.equals("Recherche")){
			if(type.equals("Devis")){
				SearchDevisList search = (SearchDevisList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type.equals("Client")){
				SearchClientList search = (SearchClientList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type.equals("Commandes")){
				SearchCommandeList search = (SearchCommandeList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type.equals("Termes")){
				SearchTermeList search = (SearchTermeList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type.equals("Salarie")){
				SearchSalarieList search = (SearchSalarieList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			
		}
	}
}
