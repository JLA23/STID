package Controller.Termes.NewTerme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Termes.Termes;

public class ActionValiderTerme implements ActionListener {

	private Termes terme;

	public ActionValiderTerme(Termes termes) {
		this.terme = termes;
	}

	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		if (!terme.getDonnees().exist("termes", "NumCommande , Numindice", "NumCommande = " + terme.getNumeroCommande() + " and numIndice = " + terme.getjNumIndice().getText())) {
				if (terme.getDonnees().exist("commandes", "NumCommande", "NumCommande = " + terme.getNumeroCommande())) {
				if (!terme.getjFournitures().getText().equals("0,00") || !terme.getjPrefabrication().getText().equals("0,00") || !terme.getjCout().getText().equals("0,00")) {
					terme.getBase().insert("termes",
							terme.getNumeroCommande() + ", " + terme.getjNumIndice().getText() + ", null, '"
									+ apostrophe(terme.getjLibelle().getText()) + "', "
									+ terme.getjPrefabrication().getText().replaceAll(",", "\\.") + ", "
									+ terme.getjCout().getText().replaceAll(",", "\\.") + ", "
									+ terme.getjFournitures().getText().replaceAll(",", "\\."));
					JOptionPane.showMessageDialog(null, "Terme enregistré !");
					terme.dispose();
					terme.getFenetre().setEnabled(true);
					terme.getFenetre().setVisible(true);
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