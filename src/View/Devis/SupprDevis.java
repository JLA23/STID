package View.Devis;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import BDD.Base;
import Controller.Devis.ActionFermer;
import Controller.Devis.FocusClient;
import Controller.Devis.TestContenu;
import Controller.Devis.ModifSupprDevis.ActionRechercher;
import Controller.Devis.ModifSupprDevis.ActionSuppr;
import Model.Donnees;

public class SupprDevis extends Devis{
	private static final long serialVersionUID = 1L;
	
	public SupprDevis(Base bdd, String numd) throws ParseException{
		super(bdd);
		this.setTitle("STID Gestion 2.0 (Supprimer Devis)");
		this.base= bdd;
		valider.setText("Supprimer");
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 510, 100, 25);
		donnees = new Donnees(base);
		String [] res = donnees.devis(numd);
		jNumDevis.setText(res[0]);
		numClient.getZoneTexte().setText(res[1]);
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		jDate.setDate(simpleDateFormat.parse(res[3]));
		jLibelle.setText(res[4]);
		jFournitures.setText(res[5].replaceAll("\\.", ","));
		jCout.setText(res[6].replaceAll("\\.", ","));
		jPrefabrication.setText(res[9].replaceAll("\\.", ","));
		jHeureSite.setText(res[7].replaceAll("\\.", ","));
		jHeureAtelier.setText(res[8].replaceAll("\\.", ","));;
		jPrevu.setText(res[10].replaceAll("\\.", ","));
		jCommande.setText(res[11].replaceAll("\\.", ","));
		newClient.setVisible(false);
		search.setVisible(false);
		new TestContenu(this, jFournitures, 1);
		new TestContenu(this, jCout, 1);
		new TestContenu(this, jPrefabrication, 1);
		new TestContenu(this, jHeureSite, 2);
		new TestContenu(this, jHeureAtelier, 2);
		new TestContenu(this, jPrevu, 3);
		new TestContenu(this, jCommande, 3);
		new FocusClient(this).nameClient();
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
		valider.addActionListener(new ActionSuppr(this));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionRechercher(this, "Suppr"));
		}
	
}