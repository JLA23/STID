package Controller.Client.NewClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Devis.Devis;

public class ActionValiderClient implements ActionListener {

	private Client client;
	private Devis dev;

	public ActionValiderClient(Client d) {
		this.client = d;
	}
	
	public ActionValiderClient(Client d, Devis devis){
		this.client = d;
		this.dev = devis;
	}

	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		ArrayList<String> valeur = client.getModes().modesSelect();
		if (!client.getDonnees().exist("clients", "NumClient", "NumClient = " + client.getjNumClient().getText())) {
			if (client.getjMail().getText().isEmpty() || mail(client.getjMail().getText())) {
				if (!client.getjName().getText().isEmpty()) {
					if (!client.getjJourSuivant().getText().isEmpty() || Integer.parseInt(client.getjJourSuivant().getText()) < 32) {
						if (!client.getjDelais().getText().isEmpty()
								&& !client.getjNbExemplaire().getText().isEmpty()) {
							if (!valeur.isEmpty()) {
								if (!client.getjAdresses()[0].getText().isEmpty()
										&& !client.getjAdresses()[1].getText().isEmpty()
										&& !client.getjAdresses()[2].getText().isEmpty()) {
									String[] re = client.getValeurTaux().get(client.getBoxTva().getSelectedItem());
									String query = client.getjNumClient().getText() + ", '" + apostrophe(client.getjName().getText())
											+ "', '";
									for (int i = 0; i < client.getjAdresses().length; i++) {
										query += apostrophe(client.getjAdresses()[i].getText()) + "', '";
									}
									query += client.getjMail().getText() + "', " + client.getjDelais().getText() + ", ";
									if (client.getBr1().isSelected()) {
										query += 1 + ", ";
									} else if (client.getBr2().isSelected()) {
										query += 2 + ", ";
									}
									query += client.getjNbExemplaire().getText() + ", "
											+ client.getjJourSuivant().getText() + ", " + re[0] + ", '"
											+ client.getJnumTVA().getText() + "', 1";
									client.getBase().insert("clients", query);
									client.getVal();
									
									for(int i = 0; i < valeur.size(); i ++){
										client.getBase().insert("paiementclient", client.getjNumClient().getText() +", " + valeur.get(i));
									}
									JOptionPane.showMessageDialog(null, "client enregistr� !");
									client.dispose();
									if(dev != null){
										dev.getNumClient().getZoneTexte().setText(client.getjNumClient().getText());
									}
								} else {
									JOptionPane.showMessageDialog(null, "Erreur : Adresse vide ou incompl�te",
											"ATTENTION", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Erreur : Aucun mode de paiment selectionn�",
										"ATTENTION", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Erreur : Nombre Exemplaire et/ou Delais vide ou incorrecte", "ATTENTION",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Erreur : Jour du mois suivant vide ou incorrecte", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Erreur : Nom du client vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : E-mail incorrecte", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de client existant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean mail(String mail) {
		boolean resultat = false;
		if (mail.contains("@")) {
			String[] tmp = mail.split("@");
			if (tmp[1].contains(".")) {
				resultat = true;
			}
		}
		return resultat;
	}
	
	 public String apostrophe(String message){
		 String mes = message;
		 if(message != null && message.contains("'")){
				String[] separer = message.split("'");
				mes = separer[0];
				for(int j = 1; j < separer.length; j++){
					mes += "\\'" + separer[j];
				}
				
		 }
		 return mes;
	 }
}