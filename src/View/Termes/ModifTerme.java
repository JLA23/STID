package View.Termes;

import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.ValiderModif;
import Model.Calcul;

public class ModifTerme extends Termes {

private static final long serialVersionUID = 1L;

public ModifTerme(Base bdd, JFrame frame, String num, String indice) {
	super(bdd, frame);
	numero.setText(numero.getText() + num);
	numeroCommande = num;
	String nbindice = indice;
	numeroIndice = indice;
	String [] res = null;
	res = donnees.fiche("t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient", "Commandes as c, Clients as cl, Termes as t", "t.numCommande = " + num + " and t.numIndice = " + nbindice + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
	jNumIndice.setText(nbindice);
	jLibelle.setText(res[0]);
	nameClient.setText(nameClient.getText() + res[1]);
	nameClient.setBounds(nameClient.getX(), nameClient.getY(), nameClient.getPreferredSize().width,	nameClient.getPreferredSize().height);
	jFournitures.setText(res[2].replaceAll("\\.", ","));
	jCout.setText(res[3].replaceAll("\\.", ","));
	jPrefabrication.setText(res[4].replaceAll("\\.", ","));
	devises.setSelectedIndex(Integer.parseInt(res[5]) - 1);
    valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
    new Calcul().calculerMontant(jFournitures, jCout, jPrefabrication, jTotalDevis, jTotalDevisDevise, valeurDevise);
    jNumIndice.requestFocus();
    
    valider.addActionListener(new ValiderModif(this, "Termes"));
	fermer.addActionListener(new ActionFermer(this, frame));
	nouveau.addActionListener(new ActionNouveau(this, "Termes"));
    this.setVisible(true);
}
}
