package View.Termes;

import java.awt.Dimension;
import java.awt.Image;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;

import Controller.ActionRechercher;
import Controller.ValiderModif;
import Model.Calcul;

public class ModifTerme extends Termes {

	private static final long serialVersionUID = 1L;

	public ModifTerme(Base bdd, JFrame frame, String num, String indice, String fact) throws ParseException {
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Modifier Terme)");
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
		numero.setText(numero.getText() + num);
		numeroCommande = num;
		String nbindice = indice;
		numeroIndice = indice;
		System.out.println(num +", " + indice + ", " + fact);
		String[] res = null;
		if(fact == null){
		res = donnees.fiche(
				"t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient, t.numfacture",
				"commandes as c, clients as cl, termes as t", "t.numCommande = " + num + " and t.numIndice = "
						+ nbindice + " and t.numfacture is null and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		}
		else{
			res = donnees.fiche(
					"t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient, t.numfacture",
					"commandes as c, clients as cl, termes as t", "t.numCommande = " + num + " and t.numIndice = "
							+ nbindice + " and t.numfacture = " + fact + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		}
		System.out.println(res[0] + ", " + res[1] + ", " + res[2]);
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
		valider.addActionListener(new ValiderModif(this, "Termes"));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.setVisible(false);
		if(res[7] != null){
			numFacture = res[7];
			jPanel6.setBounds(10, 65, jPanel6.getPreferredSize().width, jPanel6.getPreferredSize().height);
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.setBorder(BorderFactory.createTitledBorder("Facture"));
	        jPanel7.setPreferredSize(new Dimension(190, 230));
	        jPanel7.setBounds(12 + jPanel6.getPreferredSize().width , 65, jPanel7.getPreferredSize().width, jPanel7.getPreferredSize().height);
	        facture = new JLabel("N° Facture : " + res[7]);
	        String [] date = donnees.fiche("DATE_FORMAT(DateEmission, '%d/%m/%Y')", "factures", "numfacture = " + res[7]);
	        dateEmission = new JLabel("Emise le : " + date[0]);
	        facture.setBounds(30, 80, facture.getPreferredSize().width, facture.getPreferredSize().height);
	        dateEmission.setBounds(30, 110, dateEmission.getPreferredSize().width, dateEmission.getPreferredSize().height);
	        jfacture = new JButton("Voir facture");
	        jfacture.addActionListener(new ViewFacture(this, res[7]));
	        jfacture.setBounds(50, 150, jfacture.getPreferredSize().width, jfacture.getPreferredSize().height);
	        jPanel7.add(facture);
	        jPanel7.add(dateEmission);
	        jPanel7.add(jfacture);
	        jPanel2.add(jPanel7);
		}
		
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 410, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Termes", "Modif"));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 410, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Termes", "Modif"));
		this.add(droite);
		if(numeroCommande.equals(donnees.min("NumCommande", "termes"))){
			gauche.setVisible(false);
		}
		if(numeroCommande.equals(donnees.max("NumCommande", "termes"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 410, 25, 25);
		feuille.addActionListener(new ActionRechercher(this, "Termes", "Modif"));
		this.add(feuille);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
