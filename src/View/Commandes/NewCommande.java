package View.Commandes;

import javax.swing.JFrame;

import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.Commandes.NewCommande.ActionValiderCommande;
import Controller.Commandes.SelectDevis.ActionSelectDevis;
import View.Options.ClickDroit;

public class NewCommande extends Commandes{

	private static final long serialVersionUID = 1L;

	public NewCommande(Base bdd, JFrame frame) {
		super(bdd, frame);
		new ClickDroit(jNumCommande, true, true);
		new ClickDroit(jLibelle, true, true);
		new ClickDroit(numClient.getZoneTexte(), true, true);
		sd = new SelectDevis(bdd, this, null);
		jDevis.addActionListener(new ActionSelectDevis(sd));
		fermer.addActionListener(new ActionFermer(this, frame));
		valider.addActionListener(new ActionValiderCommande(this));
		nouveau.addActionListener(new ActionNouveau(this, "commandes"));
        this.setVisible(true);
	}
	

}
