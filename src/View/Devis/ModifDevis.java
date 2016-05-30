package View.Devis;

import java.awt.Image;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;
import Controller.ActionRechercher;
import Controller.ValiderModif;
import Model.Donnees;

public class ModifDevis extends Devis{
	private static final long serialVersionUID = 1L;
	
	public ModifDevis(Base bdd, String numd, JFrame frame) throws ParseException{
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Modifier Devis)");
		nouveau.setVisible(false);
		donnees = new Donnees(base);
		String [] res = donnees.fiche("*, c.nomclient", "devis as d, clients as c", "d.numclient = c.numclient and d.numDevis = " + numd);
		initModif(res);
		valider.addActionListener(new ValiderModif(this, "Devis"));
		fermer.addActionListener(new ActionFermer(this, frame));
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 510, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Devis", "Modif"));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 510, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Devis", "Modif"));
		this.add(droite);
		if(res[0].equals(donnees.min("NumDevis", "devis"))){
			gauche.setVisible(false);
		}
		if(res[0].equals(donnees.max("NumDevis", "devis"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 510, 25, 25);
		feuille.addActionListener(new ActionRechercher(this, "Devis", "Modif"));
		this.add(feuille);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	
}



