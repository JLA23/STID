package Controller.Commandes.NewCommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Commandes.Commandes;

public class ActionValiderCommande implements ActionListener {

	private Commandes commande;

	public ActionValiderCommande(Commandes commandes) {
		this.commande = commandes;
	}

	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		if (!commande.getDonnees().exist("Commandes", "NumCommande", "NumCommande = " + commande.getjNumCommande().getText())) {
			if (!commande.getNumClient().getText().equals("")
					&& commande.getDonnees().exist("Clients", "NumClient", "NumClient = " + commande.getNumClient().getText())) {
				if (!commande.getListDevis().isEmpty()) {
					String[] re = commande.getValeurDevises().get(commande.getDevises().getSelectedItem());
					commande.getBase().insert("Commandes",
							commande.getjNumCommande().getText() + ", " + commande.getNumClient().getText() + ", '"
									+ commande.getjNumCommandeClient().getText() + "', '"
									+ commande.getjLibelle().getText() + "', "
									+ commande.getjFournitures().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjCout().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjHeureSite().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjHeureAtelier().getText().replaceAll(",", "\\.") + ", "
									+ commande.getCheck().isSelected() + ", "
									+ commande.getjPrefabrication().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjPrevu().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjCommande().getText().replaceAll(",", "\\.") + ", " + re[0] + ", 0,(Select tauxchange from taux where id in (Select typetva as id from clients where numclient = " + commande.getNumClient().getText() + "))" );
					for (int i = 0; i < commande.getListDevis().size(); i++) {
						commande.getBase().update("Devis", "numcommande = " + commande.getjNumCommande().getText(), "numdevis = " + (String)commande.getListDevis().get(i)[0]);
					}
					JOptionPane.showMessageDialog(null, "Commande enregistré !");
					commande.dispose();
					commande.getFenetre().setEnabled(true);
					commande.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Aucun Devis Selectioné", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Commande existant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}