package View.Factures;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.Factures.NewFacture.ActionValiderFacture;
import Model.Calcul;
import View.Options.ClickDroit;

public class NewFacture extends Factures{
	
	private static final long serialVersionUID = 1L;

	public NewFacture(Base bdd, JFrame frame, String num, String indice){
		super(bdd, frame);
		numero.setText(numero.getText() + num + " / " + indice);
		numeroCommande = num;
		numeroIndice = indice;
		int nbFacture = donnees.newNum("Factures","NumFacture", null);
		jNumFacture.setText(nbFacture + "");
		String [] res = null;
		res = donnees.fiche("t.lblterme, cl.nomclient, Round(t.MntFour, 2), Round(t.CoutMo, 2), Round(t.Prefabrication, 2), c.CodeDevise, c.CdeComClient, Round(c.tva,2), cl.numClient", "Commandes as c, Clients as cl, Termes as t", "c.numCommande = " + num + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		libelle2.setText(res[0]);
		client.setText(res[1]);
		numCommandeClient.setText(res[6]);
		jFournitures.setText(res[2].replaceAll("\\.", ","));
		jCout.setText(res[3].replaceAll("\\.", ","));
		jPrefabrication.setText(res[4].replaceAll("\\.", ","));
		devises.setSelectedIndex(Integer.parseInt(res[5]) - 1);
		devise.setText(devises.getSelectedItem().toString());
        valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
        String [] tva = donnees.fiche("Round(t.tauxchange, 2)", "taux as t, Clients as c", "c.NumClient = " + res[8] + " and c.TypeTVA = t.id");
        double valeurTVA = 0;
        if(!tva[0].equals(res[7])){
        	int option = JOptionPane.showConfirmDialog(null, new JLabel("<html><center>La TVA de la commande est différente<br/>Ancienne TVA : " + res[7] + " %<br/> Nouvelle TVA : " + tva[0] + " %<br/>Voulez garder l'ancienne TVA ?</center></html>", JLabel.CENTER), "ATTENTION", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        	if (option == JOptionPane.YES_OPTION) {
        		valeurTVA = Double.parseDouble(res[7].replaceAll(",", "\\."));
			}
			else if (option == JOptionPane.NO_OPTION) {
				valeurTVA = Double.parseDouble(tva[0].replaceAll(",", "\\."));
			}
        }
        else{
        	valeurTVA = Double.parseDouble(res[7].replaceAll(",", "\\."));
        }
        valeursTerme = res;
        jTVA.setText((valeurTVA + "").replaceAll("\\.", ","));
        new Calcul().calculerMontantTTC(jFournitures, jCout, jPrefabrication, jTotalHT, jTotalTTC, jTotalDevise, valeurDevise, valeurTVA, this);
        System.out.println(recupTVA);
        InsertModesPaiements(res[8]);
        new ClickDroit(jNumFacture, true, true);
        new ClickDroit(jFournitures, true, true);
        new ClickDroit(jCout, true, true);
        new ClickDroit(jPrefabrication, true, true);
        new ClickDroit(jTotalHT, true, false);
        new ClickDroit(jTotalTTC, true, false);
        new ClickDroit(jTotalDevise, true, false);
        new ClickDroit(jPrecision, true, true);
        new ClickDroit(jAnneeValeur, true, true);
        new ClickDroit(jTVA, true, true);
        valider.addActionListener(new ActionValiderFacture(this));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionNouveau(this, "Factures"));
        this.setVisible(true);
	}
}
