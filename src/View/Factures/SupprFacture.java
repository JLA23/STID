package View.Factures;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.ValiderModif;
import Controller.ValiderSuppr;
import Model.Calcul;
import View.Options.ClickDroit;

public class SupprFacture extends Factures {

	private static final long serialVersionUID = 1L;

	public SupprFacture(Base bdd, JFrame frame, String num, String indice, String nbFacture) throws ParseException {
		super(bdd, frame);
		numero.setText(numero.getText() + num + " / " + indice);
		numeroCommande = num;
		numeroIndice = indice;
		jNumFacture.setText(nbFacture);
		String[] res = null;
		res = donnees.fiche(
				"f.lblEcheance, cl.nomclient, Round(t.MntFour, 2), Round(t.CoutMo, 2), Round(t.Prefabrication, 2), c.CodeDevise, c.CdeComClient, Round(f.tva,2), cl.numClient, f.modepaiement, f.preclettre, f.dateEmission, f.dateecheance, f.anneeValeur, f.valeur",
				"commandes as c, clients as cl, termes as t, factures as f", "t.numfacture = " + nbFacture
						+ " and t.numfacture = f.numfacture and t.numcommande = c.numCommande and c.numclient = cl.numclient ");
		libelle2.setText(res[0]);
		client.setText(res[1]);
		numCommandeClient.setText(res[6]);
		jFournitures.setText(res[2].replaceAll("\\.", ","));
		jCout.setText(res[3].replaceAll("\\.", ","));
		jPrefabrication.setText(res[4].replaceAll("\\.", ","));
		devises.setSelectedIndex(Integer.parseInt(res[5]) - 1);
		devise.setText(devises.getSelectedItem().toString());
		valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
		double valeurTVA = Double.parseDouble(res[7].replaceAll(",", "\\."));
		valeursTerme = res;
		jTVA.setText((valeurTVA + "").replaceAll("\\.", ","));
		new Calcul().calculerMontantTTC(jFournitures, jCout, jPrefabrication, jTotalHT, jTotalTTC, jTotalDevise,
				valeurDevise, valeurTVA, this);
		InsertModesPaiements(res[8]);
		SelectModePaiement(res[9]);
		jPrecision.setText(res[10]);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		jDateEmission.setDate(simpleDateFormat.parse(res[11]));
		jDateEcheance.setDate(simpleDateFormat.parse(res[12]));
		jAnneeValeur.setText(res[13]);
		jValeur.setText(res[14]);
		valider.addActionListener(new ValiderModif(this, "Factures"));
		fermer.addActionListener(new ActionFermer(this, frame));

		nouveau.setVisible(false);
		jNumFacture.setEditable(false);
		jFournitures.setEditable(false);
		jCout.setEditable(false);
		jPrefabrication.setEditable(false);
		jTVA.setEditable(false);
		jPrecision.setEditable(false);
		jValeur.setEditable(false);
		jAnneeValeur.setEditable(false);
		jDateEmission.setEnabled(false);
		jDateEcheance.setEnabled(false);
		devises.setEditable(false);
		boxModePaiement.setEditable(false);
		calcul1.setVisible(false);
		calcul2.setVisible(false);
		calcul3.setVisible(false);
		jNumFacture.setBackground(new Color(204, 204, 204));
		jFournitures.setBackground(new Color(204, 204, 204));
		jCout.setBackground(new Color(204, 204, 204));
		jPrefabrication.setBackground(new Color(204, 204, 204));
		jTVA.setBackground(new Color(204, 204, 204));
		jPrecision.setBackground(new Color(204, 204, 204));
		jValeur.setBackground(new Color(204, 204, 204));
		jAnneeValeur.setBackground(new Color(204, 204, 204));
        new ClickDroit(jNumFacture, true, false);
        new ClickDroit(jFournitures, true, false);
        new ClickDroit(jCout, true, false);
        new ClickDroit(jPrefabrication, true, false);
        new ClickDroit(jTotalHT, true, false);
        new ClickDroit(jTotalTTC, true, false);
        new ClickDroit(jTotalDevise, true, false);
        new ClickDroit(jPrecision, true, true);
        new ClickDroit(jAnneeValeur, true, true);
        new ClickDroit(jTVA, true, false);
        for( ActionListener al : valider.getActionListeners() ) {
	    	valider.removeActionListener( al );
	    }
        valider.addActionListener(new ValiderSuppr(this, "Factures"));
		this.setVisible(true);
	}

	private void SelectModePaiement(String modes) {
		String cle = null;
		String[] valeurs = null;
		for (Entry<String, String[]> entry : modespaiements.entrySet()) {
			if (entry.getValue()[0].equals(modes)) {
				valeurs = entry.getValue();
				cle = entry.getKey();
				break;
			}
		}
		if (cle != null && valeurs != null) {
			for (int i = 0; i < boxModePaiement.getItemCount(); i++) {
				if (boxModePaiement.getItemAt(i).equals(cle)) {
					boxModePaiement.setSelectedIndex(i);
					break;
				}
			}
		}
	}	
}
