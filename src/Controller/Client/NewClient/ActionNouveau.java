package Controller.Client.NewClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Clients.Client;
import View.Clients.NewClient;

public class ActionNouveau implements ActionListener {
	
	private Client client;
	
	public ActionNouveau(Client newClient){
		this.client = newClient;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new ActionValider(client).valider();
		if(!client.isShowing()){
			new NewClient(client.getBase());
		}
	}
}
