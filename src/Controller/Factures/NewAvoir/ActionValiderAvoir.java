package Controller.Factures.NewAvoir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import View.Impression;
import View.Factures.Factures;

public class ActionValiderAvoir implements ActionListener {

	private Factures facture;

	public ActionValiderAvoir(Factures factures) {
		this.facture = factures;
	}

	public void actionPerformed(ActionEvent arg0) {
		valider();
		JOptionPane.showMessageDialog(null, "Facture enregistr� !");
		facture.dispose();
		facture.getFenetre().setEnabled(true);
		facture.getFenetre().setVisible(true);
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer la facture ?", "Impression",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			double num = Double.parseDouble(facture.getjTotalTTC().getText().replaceAll(",", "\\."));
		
			new Impression(facture.getjNumFacture().getText(), facture.getBase(), num, facture.getFenetre());
		}
	}

	public void valider() {
		if (!facture.getDonnees().exist("factures", "NumFacture", "NumFacture = " + facture.getjNumFacture().getText())) {
				if (facture.getDonnees().exist("termes", "NumCommande , Numindice", "NumCommande = " + facture.getNumeroCommande() + " and numIndice = " + facture.getNumeroIndice())) {
				if (!facture.getjFournitures().getText().equals("0,00") || !facture.getjPrefabrication().getText().equals("0,00") || !facture.getjCout().getText().equals("0,00")) {
					facture.getBase().insert("termes",
							facture.getNumeroCommande() + ", " + facture.getNumeroIndice() + ", null, '"
									+ facture.getLibelle2().getText() + "', "
									+ facture.getjPrefabrication().getText().replaceAll(",", "\\.") + ", "
									+ facture.getjCout().getText().replaceAll(",", "\\.") + ", "
									+ facture.getjFournitures().getText().replaceAll(",", "\\."));
						facture.getBase().insert("factures",
							facture.getjNumFacture().getText() + ", " + facture.getRecupTVA() + ", " + facture.getjValeur().getText() + ", "
									+ facture.getModespaiements().get(facture.getBoxModePaiement().getSelectedItem())[0] + ", '"
									+ facture.getjPrecision().getText() + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEmission().getDate()) + "', '"
									+ new SimpleDateFormat("yyyy/MM/dd").format(facture.getjDateEcheance().getDate()) + "', '"
									+ facture.getLibelle2().getText() + "', "
									+ facture.getjAnneeValeur().getText() + ", "
									+ facture.getValeurDevises().get(facture.getDevises().getSelectedItem())[0] + ", " + facture.getjTVA().getText().replaceAll(",", "\\."));
						facture.getBase().update("termes", "NumFacture = " + facture.getjNumFacture().getText(), "NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice() + " AND NumFacture is null");
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Les montants sont � z�ro", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Commande inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Commande / Indice existant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}