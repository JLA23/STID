package Controller.Devis.NewDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Clients.NewClient;
import View.Devis.Devis;

public class ActionCreateClient implements ActionListener{
	
	protected Devis dev;
	
	public ActionCreateClient(Devis devis){
		this.dev = devis;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new NewClient(dev.getBase(), dev);
		
	}

}
