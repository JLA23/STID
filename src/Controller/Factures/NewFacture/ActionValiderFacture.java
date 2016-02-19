package Controller.Factures.NewFacture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JOptionPane;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

import View.Factures.Factures;

public class ActionValiderFacture implements ActionListener {

	private Factures facture;

	public ActionValiderFacture(Factures factures) {
		this.facture = factures;
	}

	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		if (!facture.getDonnees().exist("Factures", "NumFacture", "NumFacture = " + facture.getjNumFacture().getText())) {
				if (facture.getDonnees().exist("Termes", "NumCommande , Numindice", "NumCommande = " + facture.getNumeroCommande() + " and numIndice = " + facture.getNumeroIndice())) {
				if (!facture.getjFournitures().getText().equals("0,00") || !facture.getjPrefabrication().getText().equals("0,00") || !facture.getjCout().getText().equals("0,00")) {
					System.out.println(
							facture.getjNumFacture().getText() + ", " + facture.getRecupTVA() + ", " + facture.getjValeur().getText() + ", "
									+ facture.getModespaiements().get(facture.getBoxModePaiement().getSelectedItem())[0] + ", '"
									+ facture.getjPrecision().getText() + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEmission().getDate()) + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEcheance().getDate()) + "', '"
									+ facture.getLibelle2().getText() + "', "
									+ facture.getjAnneeValeur().getText() + ", "
									+ facture.getValeurDevises().get(facture.getDevises().getSelectedItem())[0] + ", " + facture.getjTVA().getText().replaceAll(",", "\\."));
					facture.getBase().insert("Factures",
							facture.getjNumFacture().getText() + ", " + facture.getRecupTVA() + ", " + facture.getjValeur().getText() + ", "
									+ facture.getModespaiements().get(facture.getBoxModePaiement().getSelectedItem())[0] + ", '"
									+ facture.getjPrecision().getText() + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEmission().getDate()) + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEcheance().getDate()) + "', '"
									+ facture.getLibelle2().getText() + "', "
									+ facture.getjAnneeValeur().getText() + ", "
									+ facture.getValeurDevises().get(facture.getDevises().getSelectedItem())[0] + ", " + facture.getjTVA().getText().replaceAll(",", "\\."));
					if(!facture.getjFournitures().getText().equals(facture.getValeursTerme()[2].replaceAll("\\.", ",")) || !facture.getjCout().getText().equals(facture.getValeursTerme()[3].replaceAll("\\.", ",")) || !facture.getjPrefabrication().getText().equals(facture.getValeursTerme()[4].replaceAll("\\.", ","))){
						facture.getBase().update("Termes", "Prefabrication = " + facture.getjPrefabrication().getText() + ", CoutMO = " + facture.getjCout().getText() + ", MntFour = " + facture.getjFournitures() + ", NumFacture = " + facture.getjNumFacture().getText(), "NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice());
					}
					else {
						facture.getBase().update("Termes", "NumFacture = " + facture.getjNumFacture().getText(), "NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice());
					}


					double num = 2718.28;
					//NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
					NumberFormat formatter = new RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT);
					String result = formatter.format(num);
					System.out.println(result);
					
					// résultat avec Locale.ENGLISH : two thousand seven hundred and eighteen point two eight
					// résultat avec Locale.GERMAN : zwei tausend sieben hundert achtzehn Komma zwei acht
					// résultat avec Locale.FRANCE : deux-mille-sept-cent-dix-huit virgule deux huit
					JOptionPane.showMessageDialog(null, "Facture enregistré !");
					facture.dispose();
					facture.getFenetre().setEnabled(true);
					facture.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Les montants sont à zéro", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Commande inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Commande / Indice existant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}