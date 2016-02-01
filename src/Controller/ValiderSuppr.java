package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;

public class ValiderSuppr implements ActionListener {

	private Object classe;
	private String type;

	public ValiderSuppr(Object frame, String typeClasse) {
		this.classe = frame;
		this.type = typeClasse;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		suppr();
	}

	public void suppr() {
		if (type.equals("Devis")) {
			supprDevis();
		} else if (type.equals("Client")) {
			supprClient();
		} else if (type.equals("Commandes")) {
			supprCommandes();
		}
	}

	private void supprDevis() {
		Devis devis = (Devis) classe;
		if (devis.getDonnees().exist("Devis", "NumDevis", "NumDevis" + devis.getjNumDevis().getText())) {
			if (!devis.getNumClient().getText().equals("") && devis.getDonnees().exist("Clients", "NumClient",
					"NumClient = " + devis.getNumClient().getText())) {
				if (devis.getDonnees().lier("numCommande", "Devis", "numDevis = " + devis.getjNumDevis().getText())) {
					devis.getBase().delete("devis", "numDevis = " + devis.getjNumDevis().getText());
					JOptionPane.showMessageDialog(null, "Devis supprim� !");
					devis.dispose();
					devis.getFenetre().setEnabled(true);
					devis.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Une commande est li�e au devis", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void supprClient() {
		Client client = (Client) classe;
		if (client.getDonnees().exist("Clients", "NumClient", "NumClient = " + client.getjNumClient().getText())) {
			if (!client.getDonnees().lier("numClient", "Devis", "numClient = " + client.getjNumClient().getText())) {
				client.getBase().delete("client", "numclient = " + client.getjNumClient().getText());
				JOptionPane.showMessageDialog(null, "Client supprim� !");
				client.dispose();
			} else {
				JOptionPane.showMessageDialog(null,
						"Impossible de supprimer le Client.\nUn devis est li�e � ce client.", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Client inexistant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void supprCommandes() {
		Commandes commande = (Commandes) classe;
		if (commande.getDonnees().exist("Commandes", "NumCommande",
				"NumCommande = " + commande.getjNumCommande().getText())) {
			if (!commande.getNumClient().getText().equals("") && commande.getDonnees().exist("Clients", "NumClient",
					"NumClient = " + commande.getNumClient().getText())) {
				if (!commande.getDonnees().lier("numCommande", "Termes", "numCommande = " + commande.getjNumCommande().getText())) {
					for (int i = 0; i < commande.getListDevis().size(); i++) {
						commande.getBase().update("Devis", "numcommande = null",
								"numdevis = " + (String) commande.getListDevis().get(i)[0]);
					}
					commande.getBase().delete("commandes", "numCommande = " + commande.getjNumCommande().getText());
					JOptionPane.showMessageDialog(null, "Devis supprim� !");
					commande.dispose();
					commande.getFenetre().setEnabled(true);
					commande.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Une commande est li�e au devis", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
}
