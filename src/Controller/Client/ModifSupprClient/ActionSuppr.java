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
			if(!client.getDonnees().lieeDevis(client.getjNumClient().getText())){
				client.getBase().delete("client", "numclient = " + client.getjNumClient().getText());
				JOptionPane.showMessageDialog(null, "Client supprim� !");
				client.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Impossible de supprimer le Client.\nUn devis est li�e � ce client.", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Client inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
