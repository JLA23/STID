package Controller.Client.ModifSupprClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import View.Clients.Client;

public class ActionValider implements ActionListener {

	private Client client;

	public ActionValider(Client frame) {
		this.client = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		ArrayList<String> valeur = client.getModes().modesSelect();
		if (!client.getjNumClient().getText().equals("")
				&& client.getDonnees().existClient(client.getjNumClient().getText())) {
			if (client.getjMail().getText().isEmpty() || mail(client.getjMail().getText())) {
				if (!client.getjName().getText().isEmpty()) {
					if (!client.getjJourSuivant().getText().isEmpty()
							|| Integer.parseInt(client.getjJourSuivant().getText()) < 32) {
						if (!client.getjDelais().getText().isEmpty()
								&& !client.getjNbExemplaire().getText().isEmpty()) {
							if (!valeur.isEmpty()) {
								if (!client.getjAdresses()[0].getText().isEmpty()
										&& !client.getjAdresses()[1].getText().isEmpty()
										&& !client.getjAdresses()[2].getText().isEmpty()) {
									String[] re = client.getValeurTaux().get(client.getBoxTva().getSelectedItem());
									String query = "numClient = " + client.getjNumClient().getText() + ", NomClient = '"
											+ client.getjName().getText() + "', ";
									for (int i = 0; i < client.getjAdresses().length; i++) {
										query += "Adresse" + (i + 1) + " = '" + client.getjAdresses()[i].getText()
												+ "', ";
									}
									query += "Mail = '" + client.getjMail().getText() + "', DelaiPaiement = "
											+ client.getjDelais().getText() + ", DebFinMois = ";
									if (client.getBr1().isSelected()) {
										query += 1 + ", ";
									} else if (client.getBr2().isSelected()) {
										query += 2 + ", ";
									}
									query += "NbEx = " + client.getjNbExemplaire().getText() + ", JourDansMois = "
											+ client.getjJourSuivant().getText() + ", TypeTVA = " + re[0]
											+ ", NumTVA = '" + client.getJnumTVA().getText() + "'";
									System.out.println(client.getBase().update("clients", query,
											"numClient = " + client.getjNumClient().getText()));
									for(Entry<String, Object[]> entry : client.getVal().entrySet()){
										System.out.println(((boolean)entry.getValue()[1])  + " " + valeur.contains((String)entry.getValue()[0]));
										if(((boolean)entry.getValue()[1]) && !valeur.contains((String)entry.getValue()[0])){
											System.out.println(client.getBase().delete("paiementclient", "numclient = " + client.getjNumClient().getText() + " && modepaiement = " + (String)(entry.getValue()[0])));
										}
										else if(!((boolean)entry.getValue()[1]) && valeur.contains((String)entry.getValue()[0])){
											System.out.println(client.getBase().insert("paiementclient", client.getjNumClient().getText() +", " + (String)(entry.getValue()[0])));
										}
									}
									JOptionPane.showMessageDialog(null, "Client mis à jour !");
									client.dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Erreur : Adresse vide ou incomplète",
											"ATTENTION", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Erreur : Aucun mode de paiment selectionné",
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
			JOptionPane.showMessageDialog(null, "Numéro de client existant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean mail(String mail) {
		boolean resultat = false;
		if (mail.contains("@")) {
			System.out.println("Entree 1");
			String[] tmp = mail.split("@");
			System.out.println(tmp[0]);
			System.out.println(tmp[1]);
			if (tmp[1].contains(".")) {
				System.out.println("Entree 2");
				resultat = true;
			}
		}
		System.out.println("fin");
		return resultat;
	}
}
