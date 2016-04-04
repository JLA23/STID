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

public class ActionGauche implements ActionListener {
	
	private Devis devis;
	private String classe;
	private Commandes commandes;
	private Termes termes;
	private Factures factures;
	private Salarie salarie;
	
	public ActionGauche(Devis d, String classe) throws ParseException{
		this.devis = d;
		this.classe = classe;
	}
	public ActionGauche(Commandes c, String classe) throws ParseException{
		this.commandes = c;;
		this.classe = classe;
	}
	public ActionGauche(Termes t, String classe) throws ParseException{
		this.termes = t;
		this.classe = classe;
	}
	public ActionGauche(Factures f, String classe) throws ParseException{
		this.factures = f;
		this.classe = classe;
	}
	
	public ActionGauche(Salarie s, String classe) throws ParseException{
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
				int numero = Integer.parseInt(devis.getjNumDevis().getText()) - 1;
				String [] res = devis.getDonnees().fiche("*, c.nomclient", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numDevis = " + numero);
				devis.initModif(res);
				if(res[0].equals("1") && devis.getGauche().isVisible()){
					devis.getGauche().setVisible(false);
				}
				if(!res[0].equals(devis.getDonnees().max("NumDevis", "Devis")) && !devis.getDroite().isVisible()){
					devis.getDroite().setVisible(true);
				}
				
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
				int numero = Integer.parseInt(salarie.getCode().getText()) - 1;
				String [] res = salarie.getDonnees().fiche("*", "personne", "numpersonnel = " + numero);
				salarie.initModif(res);
				if(res[0].equals("1") && salarie.getGauche().isVisible()){
					salarie.getGauche().setVisible(false);
				}
				if(!res[0].equals(salarie.getDonnees().max("NumPersonnel", "personne")) && !salarie.getDroite().isVisible()){
					salarie.getDroite().setVisible(true);
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
			if(!valeursInit[i].equals(valeursMaintenant[i])){
				verifie = false;
				break;
			}
		}
		return verifie;
		
	}

}
