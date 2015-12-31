package View.Commandes;

import java.awt.Color;
import java.text.ParseException;
import javax.swing.JFrame;
import BDD.Base;
import Controller.Commandes.FocusClient;
import Controller.Commandes.TestContenu;
import Controller.Commandes.SelectDevis.ActionSelectDevis;

public class ModifCommande extends Commandes{
	
	private static final long serialVersionUID = 1L;

	public ModifCommande(Base bdd, String numd, JFrame frame) throws ParseException{
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Modifier Commande)");
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 510, 100, 25);
		DevisdelaCommande = donnees.listeDevisYesCommande(numd);
		String [] res = donnees.commande(numd);
		jNumCommande.setText(res[0]);
		jNumCommande.setEditable(false);
		jNumCommande.setBackground(new Color(204, 204, 204));
		numClient.getZoneTexte().setText(res[1]);
		jLibelle.setText(res[3]);
		jFournitures.setText(res[4].replaceAll("\\.", ","));
		jCout.setText(res[5].replaceAll("\\.", ","));
		jPrefabrication.setText(res[9].replaceAll("\\.", ","));
		jHeureSite.setText(res[6].replaceAll("\\.", ","));
		jHeureAtelier.setText(res[7].replaceAll("\\.", ","));;
		jPrevu.setText(res[10].replaceAll("\\.", ","));
		jCommande.setText(res[11].replaceAll("\\.", ","));
		sd = new SelectDevis(bdd, this, numd);
		jDevis.addActionListener(new ActionSelectDevis(sd));
		DevisdelaCommande = donnees.listeDevisYesCommande(numd);
		for(int i = 0; i < DevisdelaCommande.length; i++){
			listDevis.add(DevisdelaCommande[i]);
		}
		new TestContenu(this, jFournitures, 1);
		new TestContenu(this, jCout, 1);
		new TestContenu(this, jPrefabrication, 1);
		new TestContenu(this, jHeureSite, 2);
		new TestContenu(this, jHeureAtelier, 2);
		new TestContenu(this, jPrevu, 3);
		new TestContenu(this, jCommande, 3);
		new FocusClient(this).nameClient();
		//valider.addActionListener(new ActionValider(this));
		//fermer.addActionListener(new ActionFermer(this, frame));
		//nouveau.addActionListener(new ActionRechercher(this, frame, "Modif"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	
}

