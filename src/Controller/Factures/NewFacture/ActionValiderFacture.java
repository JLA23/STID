package Controller.Factures.NewFacture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import View.Impression;
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
		if (!facture.getDonnees().exist("factures", "NumFacture", "NumFacture = " + facture.getjNumFacture().getText())) {
				if (facture.getDonnees().exist("termes", "NumCommande , Numindice", "NumCommande = " + facture.getNumeroCommande() + " and numIndice = " + facture.getNumeroIndice())) {
				if (!facture.getjFournitures().getText().equals("0,00") || !facture.getjPrefabrication().getText().equals("0,00") || !facture.getjCout().getText().equals("0,00")) {
						facture.getBase().insert("factures",
							facture.getjNumFacture().getText() + ", " + facture.getRecupTVA() + ", " + facture.getjValeur().getText() + ", "
									+ facture.getModespaiements().get(facture.getBoxModePaiement().getSelectedItem())[0] + ", '"
									+ facture.getjPrecision().getText() + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEmission().getDate()) + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEcheance().getDate()) + "', '"
									+ facture.getLibelle2().getText() + "', "
									+ facture.getjAnneeValeur().getText() + ", "
									+ facture.getValeurDevises().get(facture.getDevises().getSelectedItem())[0] + ", " + facture.getjTVA().getText().replaceAll(",", "\\."));
					if(!facture.getjFournitures().getText().equals(facture.getValeursTerme()[2].replaceAll("\\.", ",")) || !facture.getjCout().getText().equals(facture.getValeursTerme()[3].replaceAll("\\.", ",")) || !facture.getjPrefabrication().getText().equals(facture.getValeursTerme()[4].replaceAll("\\.", ","))){
						facture.getBase().update("termes", "Prefabrication = " + facture.getjPrefabrication().getText() + ", CoutMO = " + facture.getjCout().getText() + ", MntFour = " + facture.getjFournitures() + ", NumFacture = " + facture.getjNumFacture().getText(), "NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice());
					}
					else {
						facture.getBase().update("termes", "NumFacture = " + facture.getjNumFacture().getText(), "NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice());
					}
					String res [] = facture.getDonnees().fiche("Round(c.prefabrication + c.coutmo + c.mntfour, 2) as com, Round(Sum(t.prefabrication + t.coutmo + t.mntfour), 2) as ter", "commandes as c, termes as t", "t.numcommande = c.numcommande and c.numcommande = " + facture.getNumeroCommande() + " and t.prefabrication >= 0 and t.coutmo >= 0 and t.mntfour >= 0");
					Double valcom = Double.parseDouble(res[0]);
					Double valter = Double.parseDouble(res[1]);
					if(valter >= valcom){
						facture.getBase().update("commandes", "travauxfini = 1", "numcommande = " + facture.getNumeroCommande());
					}
					JOptionPane.showMessageDialog(null, "Facture enregistré !");
					facture.dispose();
					facture.getFenetre().setEnabled(true);
					facture.getFenetre().setVisible(true);
					int option = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer la facture ?", "Impression",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.YES_OPTION) {
						double num = Double.parseDouble(facture.getjTotalTTC().getText().replaceAll(",", "\\."));
						new Impression(facture.getjNumFacture().getText(), facture.getBase(), num, facture.getFenetre());
					}
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