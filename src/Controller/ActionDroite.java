package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Factures.Factures;
import View.Parameters.Salarie;
import View.Termes.Termes;

public class ActionDroite implements ActionListener {

	private Devis devis;
	private String classe, fonction;
	private Commandes commandes;
	private Termes termes;
	private Factures factures;
	private Salarie salarie;
	private Client client;

	public ActionDroite(Devis d, String classe, String fonction) throws ParseException {
		this.devis = d;
		this.classe = classe;
		this.fonction = fonction;
	}

	public ActionDroite(Client d, String classe, String fonction) throws ParseException {
		this.client = d;
		this.classe = classe;
		this.fonction = fonction;
	}

	public ActionDroite(Commandes c, String classe, String fonction) throws ParseException {
		this.commandes = c;
		this.classe = classe;
		this.fonction = fonction;
	}

	public ActionDroite(Termes t, String classe, String fonction) throws ParseException {
		this.termes = t;
		this.classe = classe;
		this.fonction = fonction;
	}

	public ActionDroite(Factures f, String classe, String fonction) throws ParseException {
		this.factures = f;
		this.classe = classe;
		this.fonction = fonction;
	}

	public ActionDroite(Salarie s, String classe, String fonction) throws ParseException {
		this.salarie = s;
		this.classe = classe;
		this.fonction = fonction;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (classe.equals("Devis")) {
			try {
				actionDevis();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (classe.equals("Client")) {
			try {
				actionClient();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (classe.equals("Salarie")) {
			try {
				actionSalarie();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (classe.equals("Commandes")) {
			try {
				actionCommandes();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (classe.equals("Termes")) {
			try {
				actionTermes();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (classe.equals("Factures")) {
			try {
				actionFactures();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void actionDevis() throws ParseException {
		if (devis.getDonnees().exist("devis", "NumDevis", "numDevis = " + devis.getjNumDevis().getText())) {
			if (fonction.equals("Modif")) {
				if (!devis.getNumClient().getText().equals("") && devis.getDonnees().exist("clients", "NumClient",
						"NumClient = " + devis.getNumClient().getText())) {
					String[] re = devis.getValeurDevises().get(devis.getDevises().getSelectedItem());
					String[] valeursInit = devis.getDonnees().fiche(
							"NumDevis, NumClient, DateDevis, LblDevis, Round(MntFour, 2), Round(CoutMo,2), Round(HeureSite, 2), Round(HeureAtelier,2), Round(Prefabrication, 2), Round(MatierePrevu,2), Round(MatiereCommande, 2), CodeDevise",
							"devis", "numDevis = " + devis.getjNumDevis().getText());
					String[] valeursModifie = new String[12];
					valeursModifie[0] = devis.getjNumDevis().getText();
					valeursModifie[1] = devis.getNumClient().getText();
					valeursModifie[2] = new SimpleDateFormat("yyyy-MM-dd").format(devis.getjDate().getDate());
					valeursModifie[3] = devis.getjLibelle().getText();
					valeursModifie[4] = devis.getjFournitures().getText().replaceAll(",", "\\.");
					valeursModifie[5] = devis.getjCout().getText().replaceAll(",", "\\.");
					valeursModifie[6] = devis.getjHeureSite().getText().replaceAll(",", "\\.");
					valeursModifie[7] = devis.getjHeureAtelier().getText().replaceAll(",", "\\.");
					valeursModifie[8] = devis.getjPrefabrication().getText().replaceAll(",", "\\.");
					valeursModifie[9] = devis.getjPrevu().getText().replaceAll(",", "\\.");
					valeursModifie[10] = devis.getjCommande().getText().replaceAll(",", "\\.");
					valeursModifie[11] = re[0];
					if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
						devis.getBase().update("devis",
								"numClient = " + devis.getNumClient().getText() + ", DateDevis = '"
										+ new SimpleDateFormat("yyyy/MM/dd").format(devis.getjDate().getDate())
										+ "', LblDevis = '" + apostrophe(devis.getjLibelle().getText()) + "', MntFour = "
										+ devis.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
										+ devis.getjCout().getText().replaceAll(",", "\\.") + ", HeureSite = "
										+ devis.getjHeureSite().getText().replaceAll(",", "\\.") + ", HeureAtelier = "
										+ devis.getjHeureAtelier().getText().replaceAll(",", "\\.")
										+ ", Prefabrication = "
										+ devis.getjPrefabrication().getText().replaceAll(",", "\\.")
										+ ", MatierePrevu = " + devis.getjPrevu().getText().replaceAll(",", "\\.")
										+ ", MatiereCommande = " + devis.getjCommande().getText().replaceAll(",", "\\.")
										+ ", CodeDevise = " + re[0],
								"numDevis = " + devis.getjNumDevis().getText());
						JOptionPane.showMessageDialog(null, "Devis validé !");

					}

				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			int numero = Integer.parseInt(devis.getjNumDevis().getText()) + 1;
			while(!devis.getDonnees().exist("devis", "numdevis", "numdevis = " + numero)){
				numero += 1;
			}
			String [] res = devis.getDonnees().fiche("*, c.nomclient", "devis as d, clients as c", "d.numclient = c.numclient and d.numDevis = " + numero);
			devis.initModif(res);
			if(!res[0].equals(devis.getDonnees().min("NumDevis", "devis")) && !devis.getGauche().isVisible()){
				devis.getGauche().setVisible(true);
			}
			if(res[0].equals(devis.getDonnees().max("NumDevis", "devis")) && devis.getDroite().isVisible()){
				devis.getDroite().setVisible(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void actionTermes() throws ParseException {
		if (termes.getDonnees().exist("termes", "NumCommande, NumIndice", "numCommande = " + termes.getNumeroCommande() + " AND numIndice = " + termes.getjNumIndice().getText())) {
			if (fonction.equals("Modif")) {
					String[] re = termes.getValeurDevises().get(termes.getDevises().getSelectedItem());
					String[] valeursInit = termes.getDonnees().fiche(
							"t.numCommande, t.numIndice, t.lblTerme, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise",
							"commandes as c, termes as t", "t.numCommande = " + termes.getNumeroCommande() + " and t.numIndice = "
									+ termes.getjNumIndice().getText() + " and t.numcommande = c.numCommande");
					String[] valeursModifie = new String[7];
					valeursModifie[0] = termes.getNumeroCommande();
					valeursModifie[1] = termes.getNumeroIndice();
					valeursModifie[2] = termes.getjLibelle().getText();
					valeursModifie[3] = termes.getjFournitures().getText().replaceAll(",", "\\.");
					valeursModifie[4] = termes.getjCout().getText().replaceAll(",", "\\.");
					valeursModifie[5] = termes.getjPrefabrication().getText().replaceAll(",", "\\.");
					valeursModifie[6] = re[0];
					if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
						if (termes.getNumFacture() == null) {
						termes.getBase().update("termes",
								 "LblTerme = '" + apostrophe(termes.getjLibelle().getText()) + "', MntFour = "
										+ termes.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
										+ termes.getjCout().getText().replaceAll(",", "\\.") + ", Prefabrication = "
										+ termes.getjPrefabrication().getText().replaceAll(",", "\\."),
								"numcommande = " + termes.getNumeroCommande() + " and numindice = " + termes.getNumeroIndice() + " and numfacture is null");
						termes.getBase().update("commandes", "CodeDevise = " + re[0], "numcommande = " + termes.getNumeroCommande());
						JOptionPane.showMessageDialog(null, "Termes validé !");
						}
						else{
							termes.getBase().update("termes",
									 "LblTerme = '" + apostrophe(termes.getjLibelle().getText()) + "', MntFour = "
											+ termes.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
											+ termes.getjCout().getText().replaceAll(",", "\\.") + ", Prefabrication = "
											+ termes.getjPrefabrication().getText().replaceAll(",", "\\."),
									"numcommande = " + termes.getNumeroCommande() + " and numindice = " + termes.getNumeroIndice() + " and numfacture = " + termes.getNumFacture());
							termes.getBase().update("commandes", "CodeDevise = " + re[0], "numcommande = " + termes.getNumeroCommande());
							JOptionPane.showMessageDialog(null, "Termes validé !");
						}

					}
			}
			int numero = Integer.parseInt(termes.getNumeroCommande()) + 1;
			while(!termes.getDonnees().exist("termes", "numcommande", "numcommande = " + numero)){
				numero += 1;
			}
			String [] res = termes.getDonnees().fiche("t.numcommande, t.numindice, t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient, t.numfacture",
				"commandes as c, clients as cl, termes as t", "t.numCommande = " + numero + " and t.numIndice = 1 and t.numcommande = c.numCommande and c.numclient = cl.numclient");
			termes.initModif(res);
			if(!res[0].equals(termes.getDonnees().min("Numcommande", "termes")) && !termes.getGauche().isVisible()){
				termes.getGauche().setVisible(true);
			}
			if(res[0].equals(termes.getDonnees().max("Numcommande", "termes")) && termes.getDroite().isVisible()){
				termes.getDroite().setVisible(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de termes inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void actionFactures() throws ParseException {
		if (factures.getDonnees().exist("factures", "NumFacture", "numfacture = " + factures.getjNumFacture().getText())) {
			if (fonction.equals("Modif")) {
					String[] re = factures.getValeurDevises().get(factures.getDevises().getSelectedItem());
					String[] valeursInit = factures.getDonnees().fiche(
							"f.montanttaxe, f.valeur, f.modepaiement, f.preclettre, f.dateEmission, f.dateEcheance, f.anneeValeur, f.CodeDevise",
							"factures as f", "f.numfacture = " + factures.getjNumFacture().getText());
					String[] valeursModifie = new String[8];
					valeursModifie[0] = factures.getRecupTVA() + "";
					valeursModifie[1] = factures.getjValeur().getText();
					valeursModifie[2] = factures.getModespaiements().get(factures.getBoxModePaiement().getSelectedItem())[0];
					valeursModifie[3] = factures.getjPrecision().getText();
					valeursModifie[4] = new SimpleDateFormat("yyyy-MM-dd").format(factures.getjDateEmission().getDate());
					valeursModifie[5] = new SimpleDateFormat("yyyy-MM-dd").format(factures.getjDateEcheance().getDate());
					valeursModifie[6] = factures.getjAnneeValeur().getText();
					valeursModifie[7] = re[0];
					if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
						factures.getBase().update("factures", "MontantTaxe = " + factures.getRecupTVA()
						+  ", valeur = " + factures.getjValeur().getText()
						+ ", ModePaiement = " + factures.getModespaiements().get(factures.getBoxModePaiement().getSelectedItem())[0]
						+ ", PrecLettre = '" + apostrophe(factures.getjPrecision().getText()) + "', DateEmission = '" + new SimpleDateFormat("yyyy/MM/dd").format(factures.getjDateEmission().getDate()) + "', DateEcheance = '" + new SimpleDateFormat("yyyy/MM/dd").format(factures.getjDateEcheance().getDate()) + "', AnneeValeur = " + factures.getjAnneeValeur().getText() + ", CodeDevise = " + re[0]  
						, "numFacture = " + factures.getjNumFacture().getText());
						JOptionPane.showMessageDialog(null, "Facture modifié !");

					}
			}
			int numero = Integer.parseInt(factures.getjNumFacture().getText()) + 1;
			while(!factures.getDonnees().exist("factures", "numfacture", "numfacture = " + numero)){
				numero += 1;
			}
			String [] res = factures.getDonnees().fiche("f.numfacture, t.numcommande, t.numindice, f.lblEcheance, cl.nomclient, Round(t.MntFour, 2), Round(t.CoutMo, 2), Round(t.Prefabrication, 2), c.CodeDevise, c.CdeComClient, Round(f.tva,2), cl.numClient, f.modepaiement, f.preclettre, f.dateEmission, f.dateecheance, f.anneeValeur, f.valeur",
					"commandes as c, clients as cl, termes as t, factures as f", "t.numfacture = " + numero
					+ " and t.numfacture = f.numfacture and t.numcommande = c.numCommande and c.numclient = cl.numclient ");
			factures.initModif(res);
			if(!res[0].equals(factures.getDonnees().min("Numfacture", "factures")) && !factures.getGauche().isVisible()){
				factures.getGauche().setVisible(true);
			}
			if(res[0].equals(factures.getDonnees().max("Numfacture", "factures")) && factures.getDroite().isVisible()){
				factures.getDroite().setVisible(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de termes inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void actionCommandes() throws ParseException {
		if (commandes.getDonnees().exist("commandes", "Numcommande",
				"numcommande = " + commandes.getjNumCommande().getText())) {
			if(fonction.equals("Modif")){
			if (!commandes.getNumClient().getText().equals("") && commandes.getDonnees().exist("clients", "NumClient",
					"NumClient = " + commandes.getNumClient().getText())) {
				String[] re = commandes.getValeurDevises().get(commandes.getDevises().getSelectedItem());
				String[] valeursInit = commandes.getDonnees().fiche(
						"Numcommande, NumClient, CdeComClient, Lblcommande, Round(MntFour, 2), Round(CoutMo,2), Round(HeureSite, 2), Round(HeureAtelier,2), Round(Prefabrication, 2), Round(MatierePrevu,2), Round(MatiereCommande, 2), CodeDevise",
						"commandes", "numcommande = " + commandes.getjNumCommande().getText());
				String[] valeursModifie = new String[12];
				valeursModifie[0] = commandes.getjNumCommande().getText();
				valeursModifie[1] = commandes.getNumClient().getText();
				valeursModifie[2] = commandes.getjNumCommandeClient().getText();
				valeursModifie[3] = commandes.getjLibelle().getText();
				valeursModifie[4] = commandes.getjFournitures().getText().replaceAll(",", "\\.");
				valeursModifie[5] = commandes.getjCout().getText().replaceAll(",", "\\.");
				valeursModifie[6] = commandes.getjHeureSite().getText().replaceAll(",", "\\.");
				valeursModifie[7] = commandes.getjHeureAtelier().getText().replaceAll(",", "\\.");
				valeursModifie[8] = commandes.getjPrefabrication().getText().replaceAll(",", "\\.");
				valeursModifie[9] = commandes.getjPrevu().getText().replaceAll(",", "\\.");
				valeursModifie[10] = commandes.getjCommande().getText().replaceAll(",", "\\.");
				valeursModifie[11] = re[0];
				if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
					commandes.getBase().update("commandes", "numClient = " + commandes.getNumClient().getText()
							+ ", CdeComClient = '" + apostrophe(commandes.getjNumCommandeClient().getText()) + "', Lblcommande = '"
							+ apostrophe(commandes.getjLibelle().getText()) + "', MntFour = "
							+ commandes.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
							+ commandes.getjCout().getText().replaceAll(",", "\\.") + ", HeureSite = "
							+ commandes.getjHeureSite().getText().replaceAll(",", "\\.") + ", HeureAtelier = "
							+ commandes.getjHeureAtelier().getText().replaceAll(",", "\\.") + ", Prefabrication = "
							+ commandes.getjPrefabrication().getText().replaceAll(",", "\\.") + ", MatierePrevu = "
							+ commandes.getjPrevu().getText().replaceAll(",", "\\.") + ", MatiereCommande = "
							+ commandes.getjCommande().getText().replaceAll(",", "\\.") + ", CodeDevise = " + re[0],
							"numCommande = " + commandes.getjNumCommande().getText());
					comparaisonDevis();
					JOptionPane.showMessageDialog(null, "commandes validé !");
				}
			}
				int numero = Integer.parseInt(commandes.getjNumCommande().getText()) + 1;
				while (!commandes.getDonnees().exist("commandes", "numcommande", "numcommande = " + numero)) {
					numero += 1;
				}
				String[] res = commandes.getDonnees().fiche("d.*, c.nomclient", "commandes as d, clients as c",
						"d.numclient = c.numclient and d.numcommande = " + numero);
				if (!res[0].equals(commandes.getDonnees().min("NumCommande", "commandes"))
						&& !commandes.getGauche().isVisible()) {
					commandes.getGauche().setVisible(true);
				}
				if (res[0].equals(commandes.getDonnees().max("NumCommande", "commandes"))
						&& commandes.getDroite().isVisible()) {
					commandes.getDroite().setVisible(false);
				}
				commandes.initModif(res);

			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void actionSalarie() throws ParseException {
		if (salarie.getDonnees().exist("personne", "NumPersonnel", "numpersonnel = " + salarie.getCode().getText())) {
			if(fonction.equals("Modif")){
			if (!salarie.getNom().getText().equals("") && !salarie.getPrenom().getText().equals("")) {
				String[] re = salarie.getValeur().get(salarie.getjType().getSelectedItem());
				String[] valeursInit = salarie.getDonnees().fiche("*", "personne",
						"numPersonnel = " + salarie.getCode().getText());
				String[] valeursModifie = new String[4];
				valeursModifie[0] = salarie.getCode().getText();
				valeursModifie[1] = salarie.getNom().getText();
				valeursModifie[2] = salarie.getPrenom().getText();
				valeursModifie[3] = re[0];
				if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
					salarie.getBdd().update("personne",
							"Nom = '" + apostrophe(salarie.getNom().getText()) + "', Prenom = '" + apostrophe(salarie.getPrenom().getText())
									+ "', CodeTypePersonne = " + re[0],
							"NumPersonnel = " + salarie.getCode().getText());
					JOptionPane.showMessageDialog(null, "salarie validé !");
				}
			}
				int numero = Integer.parseInt(salarie.getCode().getText()) + 1;
				while (!salarie.getDonnees().exist("personne", "numpersonnel", "numpersonnel = " + numero)) {
					numero += 1;
				}
				String[] res = salarie.getDonnees().fiche("*", "personne", "numpersonnel = " + numero);
				salarie.initModif(res);
				if (!res[0].equals(salarie.getDonnees().min("NumPersonnel", "personne"))
						&& !salarie.getGauche().isVisible()) {
					salarie.getGauche().setVisible(true);
				}
				if (res[0].equals(salarie.getDonnees().max("NumPersonnel", "personne"))
						&& salarie.getDroite().isVisible()) {
					salarie.getDroite().setVisible(false);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Erreur : un champ est vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de salarié inexistant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean ModifieOuIdentique(String[] valeursInit, String[] valeursMaintenant) {
		boolean verifie = true;
		for (int i = 0; i < valeursInit.length; i++) {
			System.out.println(valeursInit[i] + " - " + valeursMaintenant[i]);
			try{
				Double num1 = Double.parseDouble(valeursInit[i]);
				Double num2 = Double.parseDouble(valeursMaintenant[i]);
				int retval = Double.compare(num1, num2);
				if(retval != 0){
					System.out.println("ok2");
					verifie = false;
					break;
				}
			}
			catch(Exception e){
				if (!valeursInit[i].equals(valeursMaintenant[i])) {
					verifie = false;
					break;
				}
			}
		}

		return verifie;
	}

	private void comparaisonDevis() {
		String[] ancienNum = new String[commandes.getDevisdelaCommande().length];
		String[] num = new String[commandes.getListDevis().size()];
		for (int i = 0; i < commandes.getListDevis().size(); i++) {
			commandes.getBase().update("devis", "numcommande = " + commandes.getjNumCommande().getText(),
					"numdevis = " + (String) commandes.getListDevis().get(i)[0]);
			num[i] = (String) commandes.getListDevis().get(i)[0];
		}
		for (int i = 0; i < commandes.getDevisdelaCommande().length; i++) {
			ancienNum[i] = (String) commandes.getDevisdelaCommande()[i][0];
		}
		for (int i = 0; i < ancienNum.length; i++) {
			boolean ok = false;
			for (int j = 0; j < num.length; j++) {
				if (ancienNum[i].equals(num[j])) {
					ok = true;
					break;
				}
			}
			if (!ok) {
				commandes.getBase().update("devis", "numcommande = null", "numdevis = " + ancienNum[i]);
			}
		}
	}

	private void actionClient() throws ParseException {
		if (client.getDonnees().exist("clients", "Numclient", "numclient = " + client.getjNumClient().getText())) {
			String[] valeursInit = client.getDonnees().fiche("*", "clients",
					"numclient = " + client.getjNumClient().getText());
			String[] valeursModifie = new String[17];
			valeursModifie[0] = client.getjName().getText();
			valeursModifie[1] = client.getjNumClient().getText();
			valeursModifie[2] = client.getjAdresses()[0].getText();
			valeursModifie[3] = client.getjAdresses()[1].getText();
			valeursModifie[4] = client.getjAdresses()[2].getText();
			valeursModifie[5] = client.getjAdresses()[3].getText();
			valeursModifie[6] = client.getjAdresses()[4].getText();
			valeursModifie[7] = client.getjAdresses()[5].getText();
			valeursModifie[8] = client.getjAdresses()[6].getText();
			valeursModifie[9] = client.getjMail().getText();
			valeursModifie[10] = client.getjDelais().getText();
			if (client.getBr1().isSelected()) {
				valeursModifie[11] = 1 + "";
			} else if (client.getBr2().isSelected()) {
				valeursModifie[11] = 2 + "";
			}
			valeursModifie[12] = client.getjNbExemplaire().getText();
			valeursModifie[13] = client.getjJourSuivant().getText();
			String[] re = client.getValeurTaux().get(client.getBoxTva().getSelectedItem());
			valeursModifie[14] = re[0];
			valeursModifie[15] = client.getJnumTVA().getText();
			if (client.getCheck().isSelected()) {
				valeursModifie[16] = 1 + "";
			} else {
				valeursModifie[16] = 0 + "";
			}
			if (!ModifieOuIdentique(valeursInit, valeursModifie)) {
				client.getBase().update("clients",
						"Nomclient = '" + apostrophe(client.getjName().getText()) + "', Adresse1 = '" + apostrophe(valeursModifie[2])
								+ "', Adresse2 = '" + apostrophe(valeursModifie[3]) + "', Adresse3 = '" + apostrophe(valeursModifie[4])
								+ "', Adresse4 = '" + apostrophe(valeursModifie[5]) + "', Adresse5 = '" + apostrophe(valeursModifie[6])
								+ "', Adresse6 = '" + apostrophe(valeursModifie[7]) + "', Adresse7 = '" + apostrophe(valeursModifie[8])
								+ "', Mail = '" + client.getjMail().getText() + "', DelaiPaiement = "
								+ valeursModifie[10] + ", DebFinMois = " + valeursModifie[11] + ", NbEx = "
								+ valeursModifie[12] + ", JourDansMois = " + valeursModifie[13] + ", TypeTVA = "
								+ valeursModifie[14] + ", NumTVA = '" + apostrophe(valeursModifie[15]) + "', Actif = "
								+ valeursModifie[16],
						"NumClient = " + client.getjNumClient().getText());
			}
			ArrayList<String> valeur = client.getModes().modesSelect();
			for (Entry<String, Object[]> entry : client.getVal().entrySet()) {
				if (((boolean) entry.getValue()[1]) && !valeur.contains((String) entry.getValue()[0])) {
					client.getBase().delete("paiementclient", "numclient = " + client.getjNumClient().getText()
							+ " && modepaiement = " + (String) (entry.getValue()[0]));
				} else if (!((boolean) entry.getValue()[1]) && valeur.contains((String) entry.getValue()[0])) {
					client.getBase().insert("paiementclient",
							client.getjNumClient().getText() + ", " + (String) (entry.getValue()[0]));
				}
			}
			int numero = Integer.parseInt(client.getjNumClient().getText()) + 1;
			while (!client.getDonnees().exist("personne", "numpersonnel", "numpersonnel = " + numero)) {
				numero += 1;
			}
			String[] res = client.getDonnees().fiche("*", "clients as c", "c.numclient = " + numero);
			if (!res[0].equals(client.getDonnees().min("Numclient", "clients")) && !client.getGauche().isVisible()) {
				client.getGauche().setVisible(true);
			}
			if (res[0].equals(client.getDonnees().max("Numclient", "clients")) && client.getDroite().isVisible()) {
				client.getDroite().setVisible(false);
			}
			client.initModif(res);

		} else {
			JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	 public String apostrophe(String message){
		 String mes = message;
		 if(message != null && message.contains("'")){
				String[] separer = message.split("'");
				mes = separer[0];
				for(int j = 1; j < separer.length; j++){
					mes += "\\'" + separer[j];
				}
				
		 }
		 return mes;
	 }
}
