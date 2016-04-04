package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Factures.Factures;
import View.Parameters.Salarie;
import View.Termes.Termes;

public class ActionDroite implements ActionListener {
	
	private Devis devis;
	private String classe;
	private Commandes commandes;
	private Termes termes;
	private Factures factures;
	private Salarie salarie;
	
	public ActionDroite(Devis d, String classe) throws ParseException{
		this.devis = d;
		this.classe = classe;
	}
	public ActionDroite(Commandes c, String classe) throws ParseException{
		this.commandes = c;;
		this.classe = classe;
	}
	public ActionDroite(Termes t, String classe) throws ParseException{
		this.termes = t;
		this.classe = classe;
	}
	public ActionDroite(Factures f, String classe) throws ParseException{
		this.factures = f;
		this.classe = classe;
	}
	
	public ActionDroite(Salarie s, String classe) throws ParseException{
		this.salarie = s;
		this.classe = classe;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(classe.equals("Devis")){
			try {
				actionDevis();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(classe.equals("Salarie")){
			try {
				actionSalarie();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(classe.equals("Commandes")){
			try {
				actionSalarie();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void actionDevis() throws ParseException{
		if (devis.getDonnees().exist("Devis", "NumDevis", "numDevis = " + devis.getjNumDevis().getText())) {
			if (!devis.getNumClient().getText().equals("") && devis.getDonnees().exist("Clients", "NumClient",
					"NumClient = " + devis.getNumClient().getText())) {
				String[] re = devis.getValeurDevises().get(devis.getDevises().getSelectedItem());
				String[] valeursInit = devis.getDonnees().fiche("NumDevis, NumClient, DateDevis, LblDevis, Round(MntFour, 2), Round(CoutMo,2), Round(HeureSite, 2), Round(HeureAtelier,2), Round(Prefabrication, 2), Round(MatierePrevu,2), Round(MatiereCommande, 2), CodeDevise", "Devis", "numDevis = " + devis.getjNumDevis().getText());
				String[] valeursModifie = new String [12] ;
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
				if(!ModifieOuIdentique(valeursInit, valeursModifie)){
					devis.getBase().update("Devis",
						"numClient = " + devis.getNumClient().getText() + ", DateDevis = '"
								+ new SimpleDateFormat("yyyy/MM/dd").format(devis.getjDate().getDate())
								+ "', LblDevis = '" + devis.getjLibelle().getText() + "', MntFour = "
								+ devis.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
								+ devis.getjCout().getText().replaceAll(",", "\\.") + ", HeureSite = "
								+ devis.getjHeureSite().getText().replaceAll(",", "\\.") + ", HeureAtelier = "
								+ devis.getjHeureAtelier().getText().replaceAll(",", "\\.") + ", Prefabrication = "
								+ devis.getjPrefabrication().getText().replaceAll(",", "\\.") + ", MatierePrevu = "
								+ devis.getjPrevu().getText().replaceAll(",", "\\.") + ", MatiereCommande = "
								+ devis.getjCommande().getText().replaceAll(",", "\\.") + ", CodeDevise = " + re[0],
						"numDevis = " + devis.getjNumDevis().getText());
					JOptionPane.showMessageDialog(null, "Devis validé !");
					//devis.dispose();
					//devis.getFenetre().setEnabled(true);
					//devis.getFenetre().setVisible(true);
				}
				int numero = Integer.parseInt(devis.getjNumDevis().getText()) + 1;
				String [] res = devis.getDonnees().fiche("*, c.nomclient", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numDevis = " + numero);
				devis.initModif(res);
				if(!res[0].equals("1") && !devis.getGauche().isVisible()){
					devis.getGauche().setVisible(true);
				}
				if(res[0].equals(devis.getDonnees().max("NumDevis", "Devis")) && devis.getDroite().isVisible()){
					devis.getDroite().setVisible(false);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void actionCommandes() throws ParseException{
		if (commandes.getDonnees().exist("commandes", "Numcommande", "numcommande = " + commandes.getjNumCommande().getText())) {
			if (!commandes.getNumClient().getText().equals("") && commandes.getDonnees().exist("Clients", "NumClient",
					"NumClient = " + commandes.getNumClient().getText())) {
				String[] re = commandes.getValeurDevises().get(commandes.getDevises().getSelectedItem());
				String[] valeursInit = commandes.getDonnees().fiche("Numcommande, NumClient, CdeComClient, Lblcommande, Round(MntFour, 2), Round(CoutMo,2), Round(HeureSite, 2), Round(HeureAtelier,2), Round(Prefabrication, 2), Round(MatierePrevu,2), Round(MatiereCommande, 2), CodeDevise", "commandes", "numcommande = " + commandes.getjNumCommande().getText());
				String[] valeursModifie = new String [12] ;
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
				if(!ModifieOuIdentique(valeursInit, valeursModifie)){
					commandes.getBase().update("commandes",
						"numClient = " + commandes.getNumClient().getText() + ", CdeComClient = " + commandes.getjNumCommandeClient()
								+ ", Lblcommande = '" + commandes.getjLibelle().getText() + "', MntFour = "
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
				int numero = Integer.parseInt(commandes.getjNumCommande().getText()) + 1;
				String [] res = commandes.getDonnees().fiche("*, c.nomclient", "commandes as d, Clients as c", "d.numclient = c.numclient and d.numcommandes = " + numero);
				commandes.initModif(res);
				
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void actionSalarie() throws ParseException{
		if (salarie.getDonnees().exist("Personne", "NumPersonnel", "numpersonnel = " + salarie.getCode().getText())) {
			if (!salarie.getNom().getText().equals("") && !salarie.getPrenom().getText().equals("")) {
				String[] re = salarie.getValeur().get(salarie.getjType().getSelectedItem());
				String[] valeursInit = salarie.getDonnees().fiche("*", "personne", "numPersonnel = " + salarie.getCode().getText());
				String[] valeursModifie = new String [4] ;
				valeursModifie[0] = salarie.getCode().getText();
				valeursModifie[1] = salarie.getNom().getText();
				valeursModifie[2] = salarie.getPrenom().getText();
				valeursModifie[3] = re[0];
				if(!ModifieOuIdentique(valeursInit, valeursModifie)){
					salarie.getBdd().update("personne",
						"Nom = '" + salarie.getNom().getText() + "', Prenom = '" + salarie.getPrenom().getText() + "', CodeTypePersonne = "	+ re[0], "NumPersonnel = " + salarie.getCode().getText());
					JOptionPane.showMessageDialog(null, "salarie validé !");
					//devis.dispose();
					//devis.getFenetre().setEnabled(true);
					//devis.getFenetre().setVisible(true);
				}
				int numero = Integer.parseInt(salarie.getCode().getText()) + 1;
				String [] res = salarie.getDonnees().fiche("*", "personne", "numpersonnel = " + numero);
				salarie.initModif(res);
				if(!res[0].equals("1") && !salarie.getGauche().isVisible()){
					salarie.getGauche().setVisible(true);
				}
				if(res[0].equals(salarie.getDonnees().max("NumPersonnel", "personne")) && salarie.getDroite().isVisible()){
					salarie.getDroite().setVisible(false);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : un champ est vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de salarié inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private boolean ModifieOuIdentique(String[] valeursInit, String [] valeursMaintenant){
		boolean verifie = true;
		for(int i = 0; i < valeursInit.length; i ++){
			//System.out.println(i + " : " + valeursInit[i] + " - " + valeursMaintenant[i]);
			if(!valeursInit[i].equals(valeursMaintenant[i])){
				verifie = false;
				break;
			}
		}
		//System.out.println(verifie);
		return verifie;
		
	}
	
	private void comparaisonDevis(){
		String [] ancienNum = new String [commandes.getDevisdelaCommande().length];
		String [] num = new String[commandes.getListDevis().size()];
		for (int i = 0; i < commandes.getListDevis().size(); i++) {
			commandes.getBase().update("Devis", "numcommande = " + commandes.getjNumCommande().getText(), "numdevis = " + (String)commandes.getListDevis().get(i)[0]);
			num[i] = (String)commandes.getListDevis().get(i)[0];
		}
		for (int i = 0; i < commandes.getDevisdelaCommande().length; i++) {
			ancienNum[i] = (String)commandes.getDevisdelaCommande()[i][0];
		}
		for(int i = 0; i < ancienNum.length; i++){
			boolean ok = false;
			for(int j = 0; j < num.length; j++){
				if(ancienNum[i].equals(num[j])){
					ok = true;
					break;
				}
			}
			if(!ok){
				commandes.getBase().update("Devis", "numcommande = null", "numdevis = " + ancienNum[i]);
			}
		}
	}
}
