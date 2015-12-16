package Controller.Client;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import View.Clients.Client;

public class ItemChange implements ItemListener {

	protected Client client;

	public ItemChange(Client frame) {
		this.client = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		client.getTaux().setText("Taux TVA : "+ client.getValeurTaux().get(client.getBoxTva().getSelectedItem())[1] + " %");
	}
}