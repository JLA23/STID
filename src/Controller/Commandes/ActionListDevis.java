package Controller.Commandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BDD.Base;
import View.Commandes.SearchDevisList;
import View.Commandes.SelectDevis;

public class ActionListDevis implements ActionListener{
	
	protected Base base;
	protected SelectDevis select;
	protected String num;
	
	public ActionListDevis(Base bdd, SelectDevis sd, String numCommande){
		this.base = bdd;
		this.select= sd;
		this.num = numCommande;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SearchDevisList(base, null, select, num);
		
	}
	

}
