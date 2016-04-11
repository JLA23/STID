package View.Termes;

import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.Termes.NewTerme.ActionValiderTerme;
import Model.Calcul;

public class NewTerme extends Termes{

	private static final long serialVersionUID = 1L;

	public NewTerme(Base bdd, JFrame frame, String num) {
		super(bdd, frame);
		numero.setText(numero.getText() + num);
		numeroCommande = num;
		int nbCommande = donnees.newNum("termes","NumIndice", "NumCommande = " + num);
		numeroIndice = num + "";
		String [] res = null;
		if(nbCommande > 1){
			res = donnees.fiche("c.lblcommande, cl.nomclient, Round(c.MntFour - Sum(t.MntFour), 2), Round(c.CoutMo - Sum(t.CoutMo), 2), Round(c.Prefabrication - Sum(t.Prefabrication),2), c.CodeDevise", "commandes as c, clients as cl, termes as t", "c.numCommande = " + num + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		}
		else{
			res = donnees.fiche("c.lblcommande, cl.nomclient, c.MntFour, c.CoutMo, c.Prefabrication, c.CodeDevise", "commandes as c, clients as cl", "c.numCommande = " + num + " and c.numclient = cl.numclient");
		}
		
		jNumIndice.setText(nbCommande + "");
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
        
        valider.addActionListener(new ActionValiderTerme(this));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionNouveau(this, "Termes"));
        this.setVisible(true);
	}
}
