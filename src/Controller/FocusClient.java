package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import View.Commandes.Commandes;
import View.Devis.Devis;

public class FocusClient implements FocusListener {

	private Object devis;
	private String classe;

	public FocusClient(Object d, String typeClasse) {
		this.devis = d;
		this.classe = typeClasse;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(true);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(false);
		}
		nameClient();
	}

	public void nameClient() {
		if (classe.equals("Devis")) {
			if (!((Devis)devis).getNumClient().getText().equals("") && !((Devis)devis).getNumClient().getText().equals("(vide)") && ((Devis)devis)
					.getDonnees().exist("Clients", "NumClient", "NumClient = " + ((Devis)devis).getNumClient().getText())) {
				int i = 0;
				while (!((Devis)devis).getListClient()[i][0].toString().equals(((Devis)devis).getNumClient().getText())
						&& i < ((Devis)devis).getListClient().length) {
					i++;
				}
				if (i < ((Devis)devis).getListClient().length) {
					((Devis)devis).getNameClient().setText("Client : " + ((Devis)devis).getListClient()[i][1]);
					((Devis)devis).getNameClient().setBounds(((Devis)devis).getNameClient().getX(), ((Devis)devis).getNameClient().getY(),
							((Devis)devis).getNameClient().getPreferredSize().width,
							((Devis)devis).getNameClient().getPreferredSize().height);
				} else {
					((Devis)devis).getNameClient().setText("Error");
					((Devis)devis).getNameClient().setBounds(((Devis)devis).getNameClient().getX(), ((Devis)devis).getNameClient().getY(),
							((Devis)devis).getNameClient().getPreferredSize().width,
							((Devis)devis).getNameClient().getPreferredSize().height);
				}
				((Devis)devis).repaint();
			}
		}
		
		else if (classe.equals("Commandes")) {
			if (!((Commandes)devis).getNumClient().getText().equals("") && !((Commandes)devis).getNumClient().getText().equals("(vide)") && ((Commandes)devis)
					.getDonnees().exist("Clients", "NumClient", "NumClient = " + ((Commandes)devis).getNumClient().getText())) {
				int i = 0;
				while (!((Commandes)devis).getListClient()[i][0].toString().equals(((Commandes)devis).getNumClient().getText())
						&& i < ((Commandes)devis).getListClient().length) {
					i++;
				}
				if (i < ((Commandes)devis).getListClient().length) {
					((Commandes)devis).getNameClient().setText("Client : " + ((Commandes)devis).getListClient()[i][1]);
					((Commandes)devis).getNameClient().setBounds(((Commandes)devis).getNameClient().getX(), ((Commandes)devis).getNameClient().getY(),
							((Commandes)devis).getNameClient().getPreferredSize().width,
							((Commandes)devis).getNameClient().getPreferredSize().height);
				} else {
					((Commandes)devis).getNameClient().setText("Error");
					((Commandes)devis).getNameClient().setBounds(((Commandes)devis).getNameClient().getX(), ((Commandes)devis).getNameClient().getY(),
							((Commandes)devis).getNameClient().getPreferredSize().width,
							((Commandes)devis).getNameClient().getPreferredSize().height);
				}
				((Commandes)devis).repaint();
			}
		}

	}

}