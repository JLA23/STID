package View.Termes;

import java.awt.Color;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionRechercher;
import Controller.ValiderSuppr;
import Model.Calcul;
import Model.Donnees;

public class SupprTerme extends Termes {

	private static final long serialVersionUID = 1L;

	public SupprTerme(Base bdd, JFrame frame, String num, String indice) {
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Supprimer Terme)");
		this.base= bdd;
		valider.setText("Supprimer");
		valider.setBounds(460, 395, 100, 25);
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 410, 100, 25);
		donnees = new Donnees(base);
		numero.setText(numero.getText() + num);
		numeroCommande = num;
		String nbindice = indice;
		numeroIndice = indice;
		String[] res = donnees.fiche(
				"t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient",
				"commandes as c, clients as cl, termes as t", "t.numCommande = " + num + " and t.numIndice = "
						+ nbindice + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		jNumIndice.setText(nbindice);
		jLibelle.setText(res[0]);
		nameClient.setText(nameClient.getText() + res[1]);
		nameClient.setBounds(nameClient.getX(), nameClient.getY(), nameClient.getPreferredSize().width,
				nameClient.getPreferredSize().height);
		jFournitures.setText(res[2].replaceAll("\\.", ","));
		jCout.setText(res[3].replaceAll("\\.", ","));
		jPrefabrication.setText(res[4].replaceAll("\\.", ","));
		devises.setSelectedIndex(Integer.parseInt(res[5]) - 1);
		valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
		new Calcul().calculerMontant(jFournitures, jCout, jPrefabrication, jTotalDevis, jTotalDevisDevise,
				valeurDevise);
		jNumIndice.requestFocus();
		jFournitures.setEditable(false);
		jFournitures.setBackground(new Color(204, 204, 204));
		jCout.setEditable(false);
		jCout.setBackground(new Color(204, 204, 204));
		jPrefabrication.setEditable(false);
		jPrefabrication.setBackground(new Color(204, 204, 204));
		
		devises.setEnabled(false);
		jLibelle.setEditable(false);
		calcul1.setVisible(false);
		calcul2.setVisible(false);
		calcul3.setVisible(false);
		jNumIndice.setEditable(false);
		
		jLibelle.setBackground(new Color(204, 204, 204));
		jNumIndice.setBackground(new Color(204, 204, 204));
		valider.addActionListener(new ValiderSuppr(this, "Termes"));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionRechercher(this, frame, "Suppr", "Termes"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
}
