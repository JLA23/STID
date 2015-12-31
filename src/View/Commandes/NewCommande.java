package View.Commandes;

import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionFermer;
import Controller.Commandes.NewCommande.ActionNouveau;
import Controller.Commandes.NewCommande.ActionValider;
import Controller.Commandes.SelectDevis.ActionSelectDevis;

public class NewCommande extends Commandes{

	private static final long serialVersionUID = 1L;

	public NewCommande(Base bdd, JFrame frame) {
		super(bdd, frame);
		sd = new SelectDevis(bdd, this, null);
		jDevis.addActionListener(new ActionSelectDevis(sd));
		fermer.addActionListener(new ActionFermer(this, frame));
		valider.addActionListener(new ActionValider(this));
		nouveau.addActionListener(new ActionNouveau(this));
        this.setVisible(true);
	}
	

}
