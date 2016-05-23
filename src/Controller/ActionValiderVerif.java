package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;

import BDD.Base;
import Model.Donnees;
import View.Clients.LookClient;
import View.Clients.ModifClient;
import View.Clients.SupprClient;
import View.Commandes.LookCommande;
import View.Commandes.ModifCommande;
import View.Commandes.SupprCommande;
import View.Devis.LookDevis;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;
import View.Factures.ModifFacture;
import View.Factures.NewFacture;
import View.Factures.SupprFacture;
import View.Parameters.Salarie;
import View.SearchClients.SearchClient;
import View.SearchParameters.SearchSalarie;
import View.SearchCommandes.SearchCommande;
import View.SearchCommandes.SearchCommandeList;
import View.SearchDevis.SearchDevis;
import View.SearchFactures.SearchFacture;
import View.SearchFactures.SearchFactureList;
import View.SearchTerme.SearchTerme;
import View.SearchTerme.SearchTermeList;
import View.Termes.LookTerme;
import View.Termes.ModifTerme;
import View.Termes.NewTerme;
import View.Termes.SupprTerme;

public class ActionValiderVerif implements ActionListener {
	private Object fr;
	private String type;

	public ActionValiderVerif(Object frame, String classe) {
		this.fr = frame;
		this.type = classe;
	}

	public void actionPerformed(ActionEvent e) {
		verif();
	}

	public void verif() {
		if (type.equals("Client")) {
			try {
				verifClient();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals("Devis")) {
			verifDevis();
		} else if (type.equals("Commandes")) {
			verifCommandes();
		} else if (type.equals("Termes")) {
			try {
				verifTermes();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else if (type.equals("Factures")) {
			try {
				verifFacture();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals("Salarie")) {
			try {
				verifSalarie();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void verifClient() throws ParseException {
		Base bdd = ((SearchClient) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (donnees.exist("clients", "NumClient", "NumClient = " + ((SearchClient) fr).getNumClient().getText())) {
			((SearchClient) fr).dispose();
			if (((SearchClient) fr).getF().equals("Modif")) {
				new ModifClient(bdd, ((SearchClient) fr).getNumClient().getText());
			} else if (((SearchClient) fr).getF().equals("Suppr")) {
				if (!donnees.lier("numClient", "devis",
						"numClient = " + ((SearchClient) fr).getNumClient().getText())) {
					new SupprClient(bdd, ((SearchClient) fr).getNumClient().getText());
				} else {
					JOptionPane.showMessageDialog(null, "Le client ne peut être supprimé", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (((SearchClient) fr).getF().equals("Recherche")) {
				new LookClient(bdd, ((SearchClient) fr).getNumClient().getText());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucun Client avec ce numèro !", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verifSalarie() throws ParseException {
		Base bdd = ((SearchSalarie) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (donnees.exist("personne", "NumPersonnel",
				"NumPersonnel = " + ((SearchSalarie) fr).getNumPersonnel().getText())) {
			((SearchSalarie) fr).dispose();
			new Salarie(((SearchSalarie) fr).getFr(), bdd, ((SearchSalarie) fr).getNumPersonnel().getText(),
					((SearchSalarie) fr).getF());
		} else {
			JOptionPane.showMessageDialog(null, "Aucun Salarié avec ce numèro !", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verifDevis() {
		Base bdd = ((SearchDevis) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (!((SearchDevis) fr).getNumDevis().getText().equals("")
				&& !((SearchDevis) fr).getNumDevis().getText().isEmpty()) {
			if (donnees.exist("devis", "NumDevis", "numDevis = " + ((SearchDevis) fr).getNumDevis().getText())) {
				((SearchDevis) fr).dispose();
				try {
					if (((SearchDevis) fr).getF().equals("Modif")) {
						new ModifDevis(bdd, ((SearchDevis) fr).getNumDevis().getText(), ((SearchDevis) fr).getFr());
					} else if (((SearchDevis) fr).getF().equals("Suppr")) {
						new SupprDevis(bdd, ((SearchDevis) fr).getNumDevis().getText(), ((SearchDevis) fr).getFr());
					} else if (((SearchDevis) fr).getF().equals("Recherche")) {
						new LookDevis(bdd, ((SearchDevis) fr).getNumDevis().getText(), ((SearchDevis) fr).getFr());
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucun Devis avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		 else {
				JOptionPane.showMessageDialog(null, "La case est vide !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
	}

	private void verifCommandes() {
		Base bdd = ((SearchCommande) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (!((SearchCommande) fr).getNumCom().getText().isEmpty()
				&& ((SearchCommande) fr).getNumComClient().getText().isEmpty()) {
			if (donnees.exist("commandes", "NumCommande",
					"NumCommande = " + ((SearchCommande) fr).getNumCom().getText())) {
				((SearchCommande) fr).dispose();
				try {
					if (((SearchCommande) fr).getF().equals("Modif")) {
						new ModifCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("Suppr")) {
						new SupprCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("Recherche")) {
						new LookCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("NewTerme")) {
						new NewTerme(bdd, ((SearchCommande) fr).getFr(), ((SearchCommande) fr).getNumCom().getText());
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchCommande) fr).getNumCom().getText().isEmpty()
				&& !((SearchCommande) fr).getNumComClient().getText().isEmpty()) {
			if (donnees.exist("commandes", "NumCommande",
					"CdeComClient = " + ((SearchCommande) fr).getNumComClient().getText())) {
				((SearchCommande) fr).dispose();
				if (donnees.existPlusieur("numCommande", "commandes",
						"CdeComClient = '" + ((SearchCommande) fr).getNumComClient().getText() + "'")) {
					new SearchCommandeList(bdd, ((SearchCommande) fr).getFr(), ((SearchCommande) fr).getF(),
							((SearchCommande) fr).getNumComClient().getText());
				} else {
					try {
						if (((SearchCommande) fr).getF().equals("Modif")) {
							new ModifCommande(bdd,
									donnees.searchNumCommandeClient(((SearchCommande) fr).getNumComClient().getText()),
									((SearchCommande) fr).getFr());
						} else if (((SearchCommande) fr).getF().equals("Suppr")) {
							new SupprCommande(bdd,
									donnees.searchNumCommandeClient(((SearchCommande) fr).getNumComClient().getText()),
									((SearchCommande) fr).getFr());
						} else if (((SearchCommande) fr).getF().equals("Recherche")) {
							new LookCommande(bdd,
									donnees.searchNumCommandeClient(((SearchCommande) fr).getNumComClient().getText()),
									((SearchCommande) fr).getFr());
						} else if (((SearchCommande) fr).getF().equals("NewTerme")) {
							new NewTerme(bdd, ((SearchCommande) fr).getFr(),
									donnees.searchNumCommandeClient(((SearchCommande) fr).getNumComClient().getText()));
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande client avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (!((SearchCommande) fr).getNumCom().getText().isEmpty()
				&& !((SearchCommande) fr).getNumComClient().getText().isEmpty()) {
			if (donnees.exist("commandes", "NumCommande", "NumCommande = " + ((SearchCommande) fr).getNumCom().getText()
					+ " AND CdeComClient = " + ((SearchCommande) fr).getNumComClient().getText())) {
				((SearchCommande) fr).dispose();

				try {
					if (((SearchCommande) fr).getF().equals("Modif")) {
						new ModifCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("Suppr")) {
						new SupprCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("Recherche")) {
						new LookCommande(bdd, ((SearchCommande) fr).getNumCom().getText(),
								((SearchCommande) fr).getFr());
					} else if (((SearchCommande) fr).getF().equals("NewTerme")) {
						new NewTerme(bdd, ((SearchCommande) fr).getFr(), ((SearchCommande) fr).getNumCom().getText());
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ces numéros !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchCommande) fr).getNumCom().getText().isEmpty()
				&& ((SearchCommande) fr).getNumComClient().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verifTermes() throws ParseException {
		Base bdd = ((SearchTerme) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		if (!((SearchTerme) fr).getNumCom().getText().isEmpty()
				&& ((SearchTerme) fr).getNumIndice().getText().isEmpty()) {
			if (donnees.exist("termes", "NumCommande", "NumCommande = " + ((SearchTerme) fr).getNumCom().getText())) {
				((SearchTerme) fr).dispose();
				boolean exist = false;
				if (((SearchTerme) fr).getF().equals("NewFacture")) {
					exist = donnees.existPlusieur("NumIndice", "termes",
							"Numfacture is null and NumCommande = " + ((SearchTerme) fr).getNumCom().getText());
				} else {
					exist = donnees.existPlusieur("NumIndice", "termes",
							"NumCommande = " + ((SearchTerme) fr).getNumCom().getText());
				}
				if (exist) {
					new SearchTermeList(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getF(),
							((SearchTerme) fr).getNumCom().getText(), null);
				} else {
					if (((SearchTerme) fr).getF().equals("Modif")) {
						new ModifTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(), "1", null);
					} else if (((SearchTerme) fr).getF().equals("Suppr")) {
						new SupprTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(), "1");
					} else if (((SearchTerme) fr).getF().equals("Recherche")) {
						new LookTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(), "1");
					} else if (((SearchTerme) fr).getF().equals("NewFacture")) {
						String num = donnees.searchTerme(((SearchTerme) fr).getNumCom().getText());
						if (!num.isEmpty()) {
							new NewFacture(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(),
									num);
						} else {
							JOptionPane.showMessageDialog(null, "Tout les termes de cette commande ont été facturés !",
									"ATTENTION", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchTerme) fr).getNumCom().getText().isEmpty()
				&& !((SearchTerme) fr).getNumIndice().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Il faut un numéro de Commande !", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		} else if (!((SearchTerme) fr).getNumCom().getText().isEmpty()
				&& !((SearchTerme) fr).getNumIndice().getText().isEmpty()) {
			if (donnees.exist("termes", "NumCommande, NumIndice",
					"NumCommande = " + ((SearchTerme) fr).getNumCom().getText() + " AND numIndice = "
							+ ((SearchTerme) fr).getNumIndice().getText())) {
				((SearchTerme) fr).dispose();
				boolean exist = donnees.existPlusieur("NumFacture", "termes",
						"NumCommande = " + ((SearchTerme) fr).getNumCom().getText() + " AND NumIndice = " + ((SearchTerme) fr).getNumIndice().getText());
				if(!exist){
					if (((SearchTerme) fr).getF().equals("Modif")) {
						new ModifTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(),
								((SearchTerme) fr).getNumIndice().getText(), null);
					} else if (((SearchTerme) fr).getF().equals("Suppr")) {
						new SupprTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(),
								((SearchTerme) fr).getNumIndice().getText());
					} else if (((SearchTerme) fr).getF().equals("Recherche")) {
						new LookTerme(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(),
								((SearchTerme) fr).getNumIndice().getText());
					} else if (((SearchTerme) fr).getF().equals("NewFacture")) {
						new NewFacture(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getNumCom().getText(),
								((SearchTerme) fr).getNumIndice().getText());
					}
				}
				else {
					new SearchTermeList(bdd, ((SearchTerme) fr).getFr(), ((SearchTerme) fr).getF(),
							((SearchTerme) fr).getNumCom().getText(), ((SearchTerme) fr).getNumIndice().getText());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aucune commande avec ces numéros !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (((SearchTerme) fr).getNumCom().getText().isEmpty()
				&& ((SearchTerme) fr).getNumIndice().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void verifFacture() throws ParseException {
		Base bdd = ((SearchFacture) fr).getBdd();
		Donnees donnees = new Donnees(bdd);
		int utile = ((SearchFacture) fr).getJtp().getSelectedIndex();
		if (utile == 0) {
			// Si Num Commande n'est pas vide et num indice vide et num facture
			// vide
			if (!((SearchFacture) fr).getNumCom().getText().isEmpty()
					&& ((SearchFacture) fr).getNumIndice().getText().isEmpty()) {
				if (donnees.exist("termes", "NumCommande",
						"NumCommande = " + ((SearchFacture) fr).getNumCom().getText())) {
					((SearchFacture) fr).dispose();
					boolean exist = false;
					exist = donnees.existPlusieur("NumIndice", "termes",
							"Numfacture is not null and NumCommande = " + ((SearchFacture) fr).getNumCom().getText());
					if (exist) {
						new SearchFactureList(bdd, ((SearchFacture) fr).getFr(), ((SearchFacture) fr).getF(),
								((SearchFacture) fr).getNumCom().getText(), null, null);
					} else {
						String[] res = donnees.fiche("NumCommande, NumIndice, NumFacture", "termes",
								"NumIndice = 1 AND NumCommande = " + ((SearchFacture) fr).getNumCom().getText());
						if (((SearchFacture) fr).getF().equals("Modif")) {
							new ModifFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
						}
						else if (((SearchFacture) fr).getF().equals("Suppr")) {
							new SupprFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			// Si Num Commande vide et num indice n'est pas vide
			else if (((SearchFacture) fr).getNumCom().getText().isEmpty()
					&& !((SearchFacture) fr).getNumIndice().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut un numéro de Commande !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
			// Si Num commande n'est pas vide et num indice n'est pas vide et
			// num facture vide
			else if (!((SearchFacture) fr).getNumCom().getText().isEmpty()
					&& !((SearchFacture) fr).getNumIndice().getText().isEmpty()) {
				if (donnees.exist("termes", "NumCommande, NumIndice",
						"NumCommande = " + ((SearchFacture) fr).getNumCom().getText() + " AND NumIndice = "
								+ ((SearchFacture) fr).getNumIndice().getText())) {
					((SearchFacture) fr).dispose();
					boolean exist = false;
					exist = donnees.existPlusieur("NumFacture", "termes",
							"Numfacture is not null and NumCommande = " + ((SearchFacture) fr).getNumCom().getText() + " and NumIndice = " + ((SearchFacture) fr).getNumIndice().getText());
					if (exist) {
						new SearchFactureList(bdd, ((SearchFacture) fr).getFr(), ((SearchFacture) fr).getF(),
								((SearchFacture) fr).getNumCom().getText(), ((SearchFacture) fr).getNumIndice().getText(), null);
					}
					else{
					String[] res = donnees.fiche("NumCommande, NumIndice, NumFacture", "termes",
							"NumIndice = " + ((SearchFacture) fr).getNumIndice().getText() + " AND NumCommande = "
									+ ((SearchFacture) fr).getNumCom().getText());
					if (((SearchFacture) fr).getF().equals("Modif")) {
						new ModifFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
					}
					else if (((SearchFacture) fr).getF().equals("Suppr")) {
						new SupprFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
					}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Aucune commande avec ce numéro !", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (((SearchFacture) fr).getNumCom().getText().isEmpty()
					&& ((SearchFacture) fr).getNumIndice().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Les champs sont vides !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (utile == 1) {
			// Num Facture
			if (!((SearchFacture) fr).getNumFacture().getText().isEmpty()) {
				if (donnees.exist("termes", "NumCommande, NumIndice, NumFacture",
						"NumFacture = " + ((SearchFacture) fr).getNumFacture().getText())) {
					((SearchFacture) fr).dispose();
					String[] res = donnees.fiche("NumCommande, NumIndice, NumFacture", "termes",
							"NumFacture = " + ((SearchFacture) fr).getNumFacture().getText());
					if (((SearchFacture) fr).getF().equals("Modif")) {
						new ModifFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
					}
					else if (((SearchFacture) fr).getF().equals("Suppr")) {
						new SupprFacture(bdd, ((SearchFacture) fr).getFr(), res[0], res[1], res[2]);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Aucune facture avec ce numéro !", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Le champs est vide !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
