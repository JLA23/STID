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
		if (!commande.getDonnees().exist("commandes", "NumCommande", "NumCommande = " + commande.getjNumCommande().getText())) {
			if (!commande.getNumClient().getText().equals("")
					&& commande.getDonnees().exist("clients", "NumClient", "NumClient = " + commande.getNumClient().getText())) {
				if (!commande.getListDevis().isEmpty()) {
					String[] re = commande.getValeurDevises().get(commande.getDevises().getSelectedItem());
					commande.getBase().insert("commandes",
							commande.getjNumCommande().getText() + ", " + commande.getNumClient().getText() + ", '"
									+ apostrophe(commande.getjNumCommandeClient().getText()) + "', '"
									+ apostrophe(commande.getjLibelle().getText()) + "', "
									+ commande.getjFournitures().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjCout().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjHeureSite().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjHeureAtelier().getText().replaceAll(",", "\\.") + ", "
									+ commande.getCheck().isSelected() + ", "
									+ commande.getjPrefabrication().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjPrevu().getText().replaceAll(",", "\\.") + ", "
									+ commande.getjCommande().getText().replaceAll(",", "\\.") + ", " + re[0] + ", 0,(Select tauxchange from taux where id in (Select typetva as id from clients where numclient = " + commande.getNumClient().getText() + "))" );
					for (int i = 0; i < commande.getListDevis().size(); i++) {
						commande.getBase().update("devis", "numcommande = " + commande.getjNumCommande().getText(), "numdevis = " + (String)commande.getListDevis().get(i)[0]);
					}
					commande.getBase().insert("termes",commande.getjNumCommande().getText() + ", 1, null, '" + apostrophe(commande.getjLibelle().getText()) + "', " + commande.getjPrefabrication().getText().replaceAll(",", "\\.") + ", " +
							 commande.getjCout().getText().replaceAll(",", "\\.") + ", " + commande.getjFournitures().getText().replaceAll(",", "\\."));
					JOptionPane.showMessageDialog(null, "Commande enregistré !");
					commande.dispose();
					commande.getFenetre().setEnabled(true);
					commande.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Aucun Devis Selectionné", "ATTENTION",
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
	
	 public String apostrophe(String message){
		 String mes = message;
		 if(message != null && message.contains("'")){
				String[] separer = message.split("'");
				mes = separer[0];
				for(int j = 1; j < separer.length; j++){
					mes += "\\'" + separer[j];
				}
				
		 }
		 return mes;
	 }
}