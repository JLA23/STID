package View.Clients;

import BDD.Base;
import Controller.ActionFermer;
import Controller.Client.NewClient.ActionNouveau;
import Controller.Client.NewClient.ActionValider;
import View.Devis.Devis;

public class NewClient extends Client{
	
	private static final long serialVersionUID = 1L;

	public NewClient(Base bdd){
		super(bdd, null);
		valider.addActionListener(new ActionValider(this));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionNouveau(this));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public NewClient(Base bdd, Devis devis){
		super(bdd, null);
		valider.addActionListener(new ActionValider(this, devis));
		fermer.addActionListener(new ActionFermer(this));
		//nouveau.addActionListener(new ActionNouveau(this));
		nouveau.setVisible(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
