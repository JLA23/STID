package Controller.Client.ModifSupprClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Clients.Client;

public class ActionSuppr implements ActionListener {

	private Client client;

	public ActionSuppr(Client frame) {
		this.client = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		suppr();
	}

	public void suppr() {
		if (client.getDonnees().existNumClient(client.getjNumClient().getText())) {
			client.getBase().delete("client", "numclient = " + client.getjNumClient().getText());
			JOptionPane.showMessageDialog(null, "Client supprimé !");
			client.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
