package View.Commandes;

import java.awt.Color;
import java.text.ParseException;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.FocusJText;
import Controller.TestContenu;
import Controller.ActionRechercher;
import Controller.ValiderSuppr;
import Controller.Commandes.SelectDevis.ActionSelectDevis;
import View.Options.ClickDroit;

public class SupprCommande extends Commandes{
private static final long serialVersionUID = 1L;
	
	public SupprCommande(Base bdd, String numd, JFrame frame) throws ParseException{
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Supprimer Commande)");
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 510, 100, 25);
		new ClickDroit(jNumCommande, true, false);
		new ClickDroit(jLibelle, true, false);
		new ClickDroit(numClient.getZoneTexte(), true, false);
		click.setFonction("Suppr");
		DevisdelaCommande = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "devis as d, clients as c", "d.numclient = c.numclient and d.numcommande = " + numd);
		String [] res = donnees.fiche("*", "commandes", "numCommande = " + numd);
		jNumCommande.setText(res[0]);
		jNumCommande.setEditable(false);
		jNumCommande.setBackground(new Color(204, 204, 204));
		numClient.getZoneTexte().setText(res[1]);
		jNumCommandeClient.setText(res[2]);
		jLibelle.setText(res[3]);
		jFournitures.setText(res[4].replaceAll("\\.", ","));
		jCout.setText(res[5].replaceAll("\\.", ","));
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
		jNumCommande.setEditable(false);
		jNumCommande.setBackground(new Color(204, 204, 204));
		jDevis.setEnabled(false);
		devises.setEnabled(false);
		jLibelle.setEditable(false);
		jLibelle.setBackground(new Color(204, 204, 204));
		jNumCommandeClient.setEditable(false);
		jNumCommandeClient.setBackground(new Color(204, 204, 204));
		check.setEnabled(false);
		search.setVisible(false);
		calcul1.setVisible(false);
		calcul2.setVisible(false);
		calcul3.setVisible(false);
		calcul4.setVisible(false);
		calcul5.setVisible(false);
		calcul6.setVisible(false);
		calcul7.setVisible(false);
		valider.addActionListener(new ValiderSuppr(this, "Commandes"));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionRechercher(this, "Commandes", "Suppr"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
}
