package Controller.Commandes.NewCommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Commandes.Commandes;
import View.Commandes.NewCommande;

public class ActionNouveau implements ActionListener {
	
	private Commandes commande;
	
	public ActionNouveau(Commandes com){
		this.commande = com;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new ActionValider(commande).valider();
		if(!commande.isShowing()){
			new NewCommande(commande.getBase(), commande.getFenetre());
		}
	}
}
