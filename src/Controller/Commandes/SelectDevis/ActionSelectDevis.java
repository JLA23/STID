package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Commandes.SelectDevis;

public class ActionSelectDevis implements ActionListener{
	

	protected SelectDevis commande;
	
	public ActionSelectDevis(SelectDevis sd){
		this.commande = sd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		commande.afficher();
		
	}

}
