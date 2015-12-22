package View.Clients;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import BDD.Base;
import Controller.ActionFermer;
import Controller.Client.ModifSupprClient.ActionRechercher;
import Controller.Client.ModifSupprClient.ActionValider;
import Model.Donnees;

public class SupprClient extends Client {

	private static final long serialVersionUID = 1L;

	public SupprClient(Base bdd, String numero) {
		super(bdd, null);
		this.setTitle("STID Gestion 2.0 (Supprimer Client)");
		nouveau.setText("Recherche");
		donnees = new Donnees(base);
		String[] res = donnees.client(numero);
		jNumClient.setText(numero);
		jNumClient.setEditable(false);
		jNumClient.setBackground(new Color(204, 204, 204));
		jName.setText(res[1]);
		jName.setEditable(false);
		jName.setBackground(new Color(204, 204, 204));
		for (int j = 0; j < jAdresses.length; j++) {
			jAdresses[j].setText(res[2 + j]);
			jAdresses[j].setEditable(false);
			jAdresses[j].setBackground(new Color(204, 204, 204));
		}
		for (Entry<String, String[]> entry : valeurTaux.entrySet()) {
			if (((String) entry.getValue()[0]).equals(res[14])) {
				boxTva.setSelectedItem(entry.getKey());
				break;
			}
		}
		boxTva.setEnabled(false);
		jDelais.setText(res[10]);
		jDelais.setEditable(false);
		jDelais.setBackground(new Color(204, 204, 204));
		jNbExemplaire.setText(res[12]);
		jNbExemplaire.setEditable(false);
		jNbExemplaire.setBackground(new Color(204, 204, 204));
		jMail.setText(res[9]);
		jMail.setEditable(false);
		jMail.setBackground(new Color(204, 204, 204));
		jnumTVA.setText(res[15]);
		jnumTVA.setEditable(false);
		jnumTVA.setBackground(new Color(204, 204, 204));
		if (res[11] == "1") {
			br1.setSelected(true);
		} else if (res[11] == "2") {
			br2.setSelected(true);
		}
		br1.setEnabled(false);
		br2.setEnabled(false);
		jJourSuivant.setText(res[13]);
		jJourSuivant.setEditable(false);
		jJourSuivant.setBackground(new Color(204, 204, 204));
		LinkedHashMap<String, Object[]> val = donnees.modesPaiements();
		val = verifPaiement(val, numero);
		modes = new ModePaiement(donnees, null, val);
		vals = donnees.modesPaiements();
		vals = verifPaiement(vals, numero);
		valider.addActionListener(new ActionValider(this));
		valider.setText("Supprimer");
		valider.setBounds(270, 325, 120, 25);
		fermer.setBounds(400, 325, 80, 25);
		nouveau.setBounds(155, 325, 105, 25);
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionRechercher(this, "Suppr"));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private LinkedHashMap<String, Object[]> verifPaiement(LinkedHashMap<String, Object[]> val, String numero) {
		String[] result = donnees.modeClient(numero);
		for (Entry<String, Object[]> entry : val.entrySet()) {
			for (int i = 0; i < result.length; i++) {
				if (result[i].equals((String) entry.getValue()[0])) {
					val.get(entry.getKey())[1] = true;
				}
			}
		}
		return val;
	}
}
