package View.Clients;

import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;
import Controller.ActionRechercher;
import Controller.ValiderModif;
import Model.Donnees;

public class ModifClient extends Client{

	private static final long serialVersionUID = 1L;

	public ModifClient(Base bdd, String numero) throws ParseException {
		super(bdd, null);
		this.setTitle("STID Gestion 2.0 (Modifier Client)");
		nouveau.setText("Recherche");
		donnees = new Donnees(base);
		String [] res = donnees.fiche("*", "clients", "numClient = " + numero);
		jNumClient.setText(numero);
		jNumClient.setEditable(false);
		jNumClient.setBackground(new Color(204, 204, 204));
		jName.setText(res[1]);
		for (int j = 0; j < jAdresses.length; j++) {
			jAdresses[j].setText(res[2 + j]);
		}
		for (Entry<String, String[]> entry : valeurTaux.entrySet()) {
			if(((String)entry.getValue()[0]).equals(res[14])){
				boxTva.setSelectedItem(entry.getKey());
				break;
			}
		}
		jDelais.setText(res[10]);
		jNbExemplaire.setText(res[12]);
		jMail.setText(res[9]);
		jnumTVA.setText(res[15]);
		if(res[11].equals("1")){
			br1.setSelected(true);
		}
		else if(res[11].equals("2")){
			br2.setSelected(true);
		}
		if(res[16].equals("1")){
			check.setSelected(true);
		}
		jJourSuivant.setText(res[13]);
		LinkedHashMap<String, Object[]> val = donnees.modesPaiements();
		val = verifPaiement(val, numero);
		modes = new ModePaiement(donnees, null, val);
		vals = donnees.modesPaiements();
		vals = verifPaiement(vals, numero);
		valider.addActionListener(new ValiderModif(this, "Client"));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.setVisible(false);
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 325, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Client", "Modif"));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 325, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Client", "Modif"));
		this.add(droite);
		if(res[0].equals(donnees.min("NumClient", "clients"))){
			gauche.setVisible(false);
		}
		if(res[0].equals(donnees.max("NumClient", "clients"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 325, 25, 25);
		this.add(feuille);		
		feuille.addActionListener(new ActionRechercher(this, "Client", "Modif"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private LinkedHashMap<String, Object[]> verifPaiement(LinkedHashMap<String, Object[]> val, String numero){
		String [] result = donnees.modeClient(numero);
		for (Entry<String, Object[]> entry : val.entrySet()) {
			for(int i = 0; i < result.length; i++){
				if(result[i].equals((String)entry.getValue()[0])){
					val.get(entry.getKey())[1] = true;
				}
			}
		}
		return val;
	}

}
