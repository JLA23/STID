package View.Commandes;

import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.Commandes.NewCommande.ActionValiderCommande;
import Controller.Commandes.SelectDevis.ActionSelectDevis;

public class NewCommande extends Commandes{

	private static final long serialVersionUID = 1L;

	public NewCommande(Base bdd, JFrame frame) {
		super(bdd, frame);
		sd = new SelectDevis(bdd, this, null);
		jDevis.addActionListener(new ActionSelectDevis(sd));
		fermer.addActionListener(new ActionFermer(this, frame));
		valider.addActionListener(new ActionValiderCommande(this));
		nouveau.addActionListener(new ActionNouveau(this, "Commandes"));
        this.setVisible(true);
	}
	

}
