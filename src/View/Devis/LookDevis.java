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
import Model.Donnees;

public class LookDevis extends Devis {
	private static final long serialVersionUID = 1L;

	public LookDevis(Base bdd, String numd, JFrame frame) throws ParseException {
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Fiche Devis)");
		this.base = bdd;
		valider.setVisible(false);
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 510, 100, 25);
		donnees = new Donnees(base);
		String[] res = donnees.fiche("*, c.nomclient", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numDevis = " + numd);
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
		new FocusClient(this, "Devis").nameClient();
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
		nouveau.addActionListener(new ActionRechercher(this, frame, "Recherche", "Devis"));
		fermer.setBounds(670, 510, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
