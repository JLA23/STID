package View.Devis;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionFermer;
import Controller.FocusClient;
import Controller.TestContenu;
import Controller.ActionRechercher;
import Controller.ValiderModif;
import Model.Donnees;

public class ModifDevis extends Devis{
	private static final long serialVersionUID = 1L;
	
	public ModifDevis(Base bdd, String numd, JFrame frame) throws ParseException{
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Modifier Devis)");
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 510, 100, 25);
		donnees = new Donnees(base);
		String [] res = donnees.fiche("*, c.nomclient", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numDevis = " + numd);
		jNumDevis.setText(res[0]);
		jNumDevis.setEditable(false);
		jNumDevis.setBackground(new Color(204, 204, 204));
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
		devises.setSelectedIndex(Integer.parseInt(res[12]) - 1);
		new TestContenu(this, jFournitures, 1, "Devis");
		new TestContenu(this, jCout, 1, "Devis");
		new TestContenu(this, jPrefabrication, 1, "Devis");
		new TestContenu(this, jHeureSite, 2, "Devis");
		new TestContenu(this, jHeureAtelier, 2, "Devis");
		new TestContenu(this, jPrevu, 3, "Devis");
		new TestContenu(this, jCommande, 3, "Devis");
		new FocusClient(this, "Devis").nameClient();
		valider.addActionListener(new ValiderModif(this, "Devis"));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionRechercher(this, frame, "Modif", "Devis"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	
}



