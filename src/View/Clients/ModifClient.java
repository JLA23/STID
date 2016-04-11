package View.Clients;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionRechercher;
import Controller.ValiderModif;
import Model.Donnees;

public class ModifClient extends Client{

	private static final long serialVersionUID = 1L;

	public ModifClient(Base bdd, String numero) {
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
		if(res[11] == "1"){
			br1.setSelected(true);
		}
		else if(res[11] == "2"){
			br2.setSelected(true);
		}
		jJourSuivant.setText(res[13]);
		LinkedHashMap<String, Object[]> val = donnees.modesPaiements();
		val = verifPaiement(val, numero);
		modes = new ModePaiement(donnees, null, val);
		vals = donnees.modesPaiements();
		vals = verifPaiement(vals, numero);
		valider.addActionListener(new ValiderModif(this, "Client"));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionRechercher(this, null, "Modif", "Client"));
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
