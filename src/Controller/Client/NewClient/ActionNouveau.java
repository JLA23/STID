package Controller.Client.NewClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Clients.Client;
import View.Clients.NewClient;
import View.Devis.Devis;
import View.Devis.NewDevis;

public class ActionNouveau implements ActionListener {
	
	private Client devis;
	
	public ActionNouveau(Client newClient){
		this.devis = newClient;
	}
	
	public void actionPerformed(ActionEvent arg0) {
	
	}
}
