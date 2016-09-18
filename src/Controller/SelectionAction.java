package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import BDD.Base;
import View.Clients.LookClient;
import View.Clients.ModifClient;
import View.Clients.SupprClient;
import View.Commandes.LookCommande;
import View.Commandes.ModifCommande;
import View.Commandes.SelectDevis;
import View.Commandes.SupprCommande;
import View.Devis.LookDevis;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;
import View.Factures.LookFacture;
import View.Factures.ModifFacture;
import View.Factures.NewFacture;
import View.Factures.SupprFacture;
import View.Impression.Clients.EtatsClients;
import View.Impression.Commandes.EtatsCommandes;
import View.Impression.Devis.EtatsDevis;
import View.Impression.Factures.EtatsFactures;
import View.Parameters.Categorie;
import View.Parameters.Salarie;
import View.Pointage.SaisiePointage;
import View.SearchClients.SearchClientList;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevisList;
import View.SearchDevis.SearchDevisPointagePrint;
import View.SearchFactures.SearchFactureList;
import View.SearchParameters.SearchSalarieList;
import View.SearchTerme.SearchTermeList;
import View.Termes.LookTerme;
import View.Termes.ModifTerme;
import View.Termes.NewTerme;
import View.Termes.SupprTerme;
import fr.julien.autocomplete.view.AutoComplete;

public class SelectionAction implements ActionListener, MouseListener {

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
	private EtatsDevis etats;
	private EtatsCommandes etatsc;
	private EtatsClients etatscl;
	private EtatsFactures etatsf;

	public SelectionAction(Object frame, String classe, String fonction) {
		this.f = fonction;
		this.type = classe;
		this.clas = frame;
		
	}

	public void actionPerformed(ActionEvent e) {
		action();
	}
	
	public void action(){
		System.out.println(f);
		initVerif();
		int ligne = tables.getSelectedRow();
		if (ligne != -1) {
			String numero = datas[tables.convertRowIndexToModel(ligne)][0].toString();
			String numero2 = null;
			if(datas[tables.convertRowIndexToModel(ligne)].length >= 3 && datas[tables.convertRowIndexToModel(ligne)][2] != null){
				numero2 = datas[tables.convertRowIndexToModel(ligne)][2].toString();
			}
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
						new ModifTerme(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					} else if (type.equals("Salarie")){
						new Salarie(fenetre, bdd, numero, f);
					} else if (type.equals("Factures")){
						new ModifFacture(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					}
				} else if (f.equals("Suppr")) {
					if (type.equals("Client")) {
						new SupprClient(bdd, numero);
					} else if (type.equals("Devis")) {
						new SupprDevis(bdd, numero, fenetre);
					} else if (type.equals("Commandes")) {
						new SupprCommande(bdd, numero, fenetre);
					} else if (type.equals("Termes")) {
						new SupprTerme(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					} else if (type.equals("Factures")) {
						new SupprFacture(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					} else if (type.equals("Salarie")){
						new Salarie(fenetre, bdd, numero, f);
					}
				} else if (f.equals("Recherche")) {
					if (type.equals("Client")) {
						new LookClient(bdd, numero);
					} else if (type.equals("Devis")) {
						new LookDevis(bdd, numero, fenetre);
					} else if (type.equals("Commandes")) {
						new LookCommande(bdd, numero, fenetre);
					} else if (type.equals("Termes")) {
						 new LookTerme(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					} else if (type.equals("Factures")) {
						 new LookFacture(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString(), numero2);
					}
				} else if (f.equals("SearchClient")) {
					autos.getZoneTexte().setText(numero);
					dialog.dispose();
					new FocusJText(((SearchClientList)clas).getFenetre(), ((SearchClientList)clas).getClasse()).name();
				} else if (f.equals("NewTerme")){ 
					new NewTerme(bdd, fenetre, numero);
				}  else if (f.equals("NewFacture")){ 
					new NewFacture(bdd, fenetre, numero, datas[tables.convertRowIndexToModel(ligne)][1].toString());
				} else if (f.equals("SelectDevis")) {
					if (!select.containt((String) datas[tables.convertRowIndexToModel(ligne)][0])) {
						select.getModel().addRow(datas[tables.convertRowIndexToModel(ligne)]);
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
				 else if (f.equals("EtatsDevis")){
						autos.getZoneTexte().setText(numero);
						dialog.dispose();
						new FocusJText(etats, "EtatsDevis").name();
				}
				 else if (f.equals("EtatsCommandes")){
						autos.getZoneTexte().setText(numero);
						dialog.dispose();
						new FocusJText(etatsc, "EtatsCommandes").name();
				}
				 else if (f.equals("EtatsClients")){
						autos.getZoneTexte().setText(numero);
						dialog.dispose();
						new FocusJText(etatscl, "EtatsClients").name();
				}
				 else if (f.equals("EtatsFactures")){
						autos.getZoneTexte().setText(numero);
						dialog.dispose();
						new FocusJText(etatsf, "EtatsFactures").name();
				}
				 else if (f.equals("EHSP")){
						autos.getZoneTexte().setText(numero);
						dialog.dispose();
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
			SearchDevisPointagePrint search = (SearchDevisPointagePrint)clas;
			dialog = search;
			autos = search.getAuto();
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
			saisie = (SaisiePointage)search.getSaisie();
		}
		
		else if(f.equals("EtatsDevis")){
			if(type == null){
				SearchDevisPointagePrint search = (SearchDevisPointagePrint)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etats = (EtatsDevis)search.getSaisie();
			}
			else if(type.equals("Client")){
				SearchClientList search = (SearchClientList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etats = (EtatsDevis)search.getFenetre();
			}
		}
		else if(f.equals("EtatsCommandes")){
			if(type.equals("Commandes")){
				SearchCommandeList search = (SearchCommandeList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etatsc = (EtatsCommandes)search.getSaisie();
			}
			else if(type.equals("Client")){
				SearchClientList search = (SearchClientList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etatsc = (EtatsCommandes)search.getFenetre();
			}
		}
		
		else if(f.equals("EtatsClients")){
			 if(type.equals("Client")){
				SearchClientList search = (SearchClientList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etatscl = (EtatsClients)search.getFenetre();
			}
		}
		
		else if(f.equals("EtatsFactures")){
			 if(type.equals("Client")){
				SearchClientList search = (SearchClientList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
				etatsf = (EtatsFactures)search.getFenetre();
			}
		}
		else if(f.equals("EHSP")){
			if(type == null){
				SearchDevisPointagePrint search = (SearchDevisPointagePrint)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type == "Salarie"){
				SearchSalarieList search = (SearchSalarieList)clas;
				dialog = search;
				autos = search.getAuto();
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
			else if(type.equals("Categorie")){
				Categorie search = (Categorie)clas;
				dialog = search;
				autos = search.getAuto();
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
				autos = search.getAuto();
			}
		}
		
		else if(f.equals("Pointage Code")){
			SearchSalarieList search = (SearchSalarieList)clas;
			dialog = search;
			autos = search.getAuto();
			datas = search.getData();
			bdd = search.getBdd();
			tables = search.getLayerTable();
			fenetre = search.getFrame();
			saisie = (SaisiePointage)search.getSaisie();
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
			
			else if(type.equals("Factures")){
				SearchFactureList search = (SearchFactureList)clas;
				dialog = search;
				datas = search.getData();
				bdd = search.getBdd();
				tables = search.getLayerTable();
				fenetre = search.getFrame();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 if (e.getClickCount() == 2) {
			 action();
		 }
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
