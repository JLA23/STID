package View.Devis;

import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;
import Controller.ActionRechercher;
import Controller.FocusJText;
import Controller.TestContenu;
import Model.Donnees;
import View.Options.ClickDroit;

public class LookDevis extends Devis {
	private static final long serialVersionUID = 1L;

	public LookDevis(Base bdd, String numd, JFrame frame) throws ParseException {
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Fiche Devis)");
		this.base = bdd;
		new ClickDroit(jNumDevis, true, false);
		new ClickDroit(jLibelle, true, false);
		new ClickDroit(numClient.getZoneTexte(), true, false);
		click.setFonction("Look");
		valider.setVisible(false);
		nouveau.setVisible(false);
		//nouveau.setBounds(20, 510, 100, 25);
		donnees = new Donnees(base);
		String[] res = donnees.fiche("*, c.nomclient", "devis as d, clients as c", "d.numclient = c.numclient and d.numDevis = " + numd);
		jNumDevis.setText(res[0]);
		numClient.getZoneTexte().setText(res[1]);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		jDate.setDate(simpleDateFormat.parse(res[3]));
		jLibelle.setText(res[4]);
		jFournitures.setText(res[5].replaceAll("\\.", ","));
		jCout.setText(res[6].replaceAll("\\.", ","));
		jPrefabrication.setText(res[9].replaceAll("\\.", ","));
		jHeureSite.setText(res[7].replaceAll("\\.", ","));
		jHeureAtelier.setText(res[8].replaceAll("\\.", ","));
		jPrevu.setText(res[10].replaceAll("\\.", ","));
		jCommande.setText(res[11].replaceAll("\\.", ","));
		newClient.setVisible(false);
		search.setVisible(false);
		new TestContenu(this, jFournitures, 1, "Devis");
		new TestContenu(this, jCout, 1, "Devis");
		new TestContenu(this, jPrefabrication, 1, "Devis");
		new TestContenu(this, jHeureSite, 2, "Devis");
		new TestContenu(this, jHeureAtelier, 2, "Devis");
		new TestContenu(this, jPrevu, 3, "Devis");
		new TestContenu(this, jCommande, 3, "Devis");
		new TestContenu(this, jTotalDevis, 0, "Devis");
		new TestContenu(this, jTotalHeure, 0, "Devis");
		new TestContenu(this, jResteCommande, 0, "Devis");
		new FocusJText(this, "Devis").name();
		jFournitures.setEditable(false);
		jFournitures.setBackground(new Color(204, 204, 204));
		jCout.setEditable(false);
		jCout.setBackground(new Color(204, 204, 204));
		jPrefabrication.setEditable(false);
		jPrefabrication.setBackground(new Color(204, 204, 204));
		jHeureSite.setEditable(false);
		jHeureSite.setBackground(new Color(204, 204, 204));
		jHeureAtelier.setEditable(false);
		jHeureAtelier.setBackground(new Color(204, 204, 204));
		jPrevu.setEditable(false);
		jPrevu.setBackground(new Color(204, 204, 204));
		jCommande.setEditable(false);
		jCommande.setBackground(new Color(204, 204, 204));
		numClient.getZoneTexte().setEditable(false);
		numClient.getZoneTexte().setBackground(new Color(204, 204, 204));
		jNumDevis.setEditable(false);
		jNumDevis.setBackground(new Color(204, 204, 204));
		jDate.setEnabled(false);
		devises.setEnabled(false);
		jLibelle.setEditable(false);
		jLibelle.setBackground(new Color(204, 204, 204));
		calcul1.setVisible(false);
		calcul2.setVisible(false);
		calcul3.setVisible(false);
		calcul4.setVisible(false);
		calcul5.setVisible(false);
		calcul6.setVisible(false);
		calcul7.setVisible(false);
		fermer.addActionListener(new ActionFermer(this, frame));
		fermer.setBounds(670, 510, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 510, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Devis", "Recherche"));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 510, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Devis", "Recherche"));
		this.add(droite);
		if(res[0].equals("1")){
			gauche.setVisible(false);
		}
		if(res[0].equals(donnees.max("NumDevis", "devis"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 510, 25, 25);
		feuille.addActionListener(new ActionRechercher(this, "Devis", "Recherche"));
		this.add(feuille);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
