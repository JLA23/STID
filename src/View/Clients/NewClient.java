package View.Clients;

import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.VerifNum;
import Controller.Client.NewClient.ActionValiderClient;
import View.Devis.Devis;
import View.Options.ClickDroit;

public class NewClient extends Client{
	
	private static final long serialVersionUID = 1L;

	public NewClient(Base bdd){
		super(bdd, null);
		new ClickDroit(jNumClient, true, true);
		jNumClient.addFocusListener(new VerifNum(jNumClient, donnees, "clients"));
		valider.addActionListener(new ActionValiderClient(this));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionNouveau(this, "Client"));
		actif.setVisible(false);
		check.setVisible(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public NewClient(Base bdd, Devis devis){
		super(bdd, null);
		new ClickDroit(jNumClient, true, true);
		jNumClient.addFocusListener(new VerifNum(jNumClient, donnees, "clients"));
		valider.addActionListener(new ActionValiderClient(this, devis));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.setVisible(false);
		actif.setVisible(false);
		check.setVisible(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
