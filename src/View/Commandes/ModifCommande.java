package View.Commandes;

import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;
import Controller.FocusJText;
import Controller.TestContenu;
import Controller.ActionRechercher;
import Controller.ValiderModif;
import Controller.Commandes.SelectDevis.ActionSelectDevis;
import View.Options.ClickDroit;

public class ModifCommande extends Commandes{
	
	private static final long serialVersionUID = 1L;

	public ModifCommande(Base bdd, String numd, JFrame frame) throws ParseException{
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Modifier Commande)");
		new ClickDroit(jNumCommande, true, false);
		new ClickDroit(jLibelle, true, true);
		new ClickDroit(numClient.getZoneTexte(), true, true);
		click.setFonction("Modif");
		DevisdelaCommande = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "devis as d, clients as c", "d.numclient = c.numclient and d.numcommande = " + numd);
		String [] res = donnees.fiche("*", "commandes", "numCommande = " + numd);
		jNumCommande.setText(res[0]);
		jNumCommande.setEditable(false);
		jNumCommande.setBackground(new Color(204, 204, 204));
		numClient.getZoneTexte().setText(res[1]);
		jNumCommandeClient.setText(res[2]);
		jLibelle.setText(res[3]);
		jFournitures.setText(res[5].replaceAll("\\.", ","));
		jCout.setText(res[4].replaceAll("\\.", ","));
		jPrefabrication.setText(res[9].replaceAll("\\.", ","));
		jHeureSite.setText(res[6].replaceAll("\\.", ","));
		jHeureAtelier.setText(res[7].replaceAll("\\.", ","));;
		jPrevu.setText(res[10].replaceAll("\\.", ","));
		jCommande.setText(res[11].replaceAll("\\.", ","));
		devises.setSelectedIndex(Integer.parseInt(res[12]) - 1);
		sd = new SelectDevis(bdd, this, numd);
		jDevis.addActionListener(new ActionSelectDevis(sd));
		for(int i = 0; i < DevisdelaCommande.length; i++){
			listDevis.add(DevisdelaCommande[i]);
		}
		if(res[8].equals("1")){
			check.setSelected(true);
		}
		valeursCommande = res;
		new TestContenu(this, jFournitures, 1, "Commandes");
		new TestContenu(this, jCout, 1, "Commandes");
		new TestContenu(this, jPrefabrication, 1, "Commandes");
		new TestContenu(this, jHeureSite, 2, "Commandes");
		new TestContenu(this, jHeureAtelier, 2, "Commandes");
		new TestContenu(this, jPrevu, 3, "Commandes");
		new TestContenu(this, jCommande, 3, "Commandes");
		new TestContenu(this, jTotalDevis, 0, "Commandes");
		new TestContenu(this, jTotalHeure, 0, "Commandes");
		new TestContenu(this, jResteCommande, 0, "Commandes");
		new FocusJText(this, "Commandes").name();
		valider.addActionListener(new ValiderModif(this, "Commandes"));
		fermer.addActionListener(new ActionFermer(this, frame));
		//nouveau.addActionListener(new ActionRechercher(this, frame, "Modif", "Commandes"));
		nouveau.setVisible(false);
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 510, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Commandes", "Recherche"));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 510, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Commandes", "Recherche"));
		this.add(droite);
		if(res[0].equals(donnees.min("NumCommande", "commandes"))){
			gauche.setVisible(false);
		}
		if(res[0].equals(donnees.max("NumCommande", "commandes"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 510, 25, 25);
		this.add(feuille);
		 
		feuille.addActionListener(new ActionRechercher(this, "Commandes", "Modif"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	
}

