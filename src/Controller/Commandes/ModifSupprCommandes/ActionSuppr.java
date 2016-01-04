package Controller.Commandes.ModifSupprCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Commandes.Commandes;

public class ActionSuppr implements ActionListener {
	
	private Commandes commande;

	public ActionSuppr(Commandes frame){
		this.commande = frame;
	}
@Override
public void actionPerformed(ActionEvent arg0) {
	suppr();
}

public void suppr(){
	if(commande.getDonnees().existNumDevis(commande.getjNumCommande().getText())){
		if(!commande.getNumClient().getText().equals("") && commande.getDonnees().existClient(commande.getNumClient().getText())){
			if(!commande.getDonnees().lieeTerme(commande.getjNumCommande().getText())){
				for (int i = 0; i < commande.getListDevis().size(); i++) {
					commande.getBase().update("Devis", "numcommande = null", "numdevis = " + (String)commande.getListDevis().get(i)[0]);
				}
				commande.getBase().delete("commandes", "numCommande = "+ commande.getjNumCommande().getText());
				JOptionPane.showMessageDialog(null, "Devis supprimé !");
				commande.dispose();
				commande.getFenetre().setEnabled(true);
				commande.getFenetre().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Une commande est liée au devis", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	else{
		JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
	}	
}
}
