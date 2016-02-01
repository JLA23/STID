package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Termes.Termes;

public class ValiderModif implements ActionListener {

	private Object classe;
	private String type;

	public ValiderModif(Object frame, String typeClasse) {
		this.classe = frame;
		this.type = typeClasse;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}
	
	public void valider(){
		if (type.equals("Devis")) {
			validerDevis();
		} else if (type.equals("Client")) {
			validerClient();
		} else if (type.equals("Commandes")) {
			validerCommandes();
		} else if (type.equals("Termes")){
			validerTermes();
		}
		
	}

	private void validerDevis() {
		Devis devis = (Devis) classe;
		if (devis.getDonnees().exist("Devis", "NumDevis", "numDevis = " + devis.getjNumDevis().getText())) {
			if (!devis.getNumClient().getText().equals("") && devis.getDonnees().exist("Clients", "NumClient",
					"NumClient = " + devis.getNumClient().getText())) {
				String[] re = devis.getValeurDevises().get(devis.getDevises().getSelectedItem());
				System.out.println(re.length);
				devis.getBase().update("Devis",
						"numClient = " + devis.getNumClient().getText() + ", DateDevis = '"
								+ new SimpleDateFormat("yyyy/MM/dd").format(devis.getjDate().getDate())
								+ "', LblDevis = '" + devis.getjLibelle().getText() + "', MntFour = "
								+ devis.getjFournitures().getText().replaceAll(",", "\\.") + ", CoutMO = "
								+ devis.getjCout().getText().replaceAll(",", "\\.") + ", HeureSite = "
								+ devis.getjHeureSite().getText().replaceAll(",", "\\.") + ", HeureAtelier = "
								+ devis.getjHeureAtelier().getText().replaceAll(",", "\\.") + ", Prefabrication = "
								+ devis.getjPrefabrication().getText().replaceAll(",", "\\.") + ", MatierePrevu = "
								+ devis.getjPrevu().getText().replaceAll(",", "\\.") + ", MatiereCommande = "
								+ devis.getjCommande().getText().replaceAll(",", "\\.") + ", CodeDevise = " + re[0],
						"numDevis = " + devis.getjNumDevis().getText());
				JOptionPane.showMessageDialog(null, "Devis valid� !");
				devis.dispose();
				devis.getFenetre().setEnabled(true);
				devis.getFenetre().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Num�ro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void validerClient() {
		Client client = (Client) classe;
		ArrayList<String> valeur = client.getModes().modesSelect();
		if (!client.getjNumClient().getText().equals("") && client.getDonnees().exist("Clients", "NumClient",
				"NumClient = " + client.getjNumClient().getText())) {
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
									client.getBase().update("clients", query,
											"numClient = " + client.getjNumClient().getText());
									for (Entry<String, Object[]> entry : client.getVal().entrySet()) {
										if (((boolean) entry.getValue()[1])
												&& !valeur.contains((String) entry.getValue()[0])) {
											client.getBase().delete("paiementclient",
													"numclient = " + client.getjNumClient().getText()
															+ " && modepaiement = " + (String) (entry.getValue()[0]));
										} else if (!((boolean) entry.getValue()[1])
												&& valeur.contains((String) entry.getValue()[0])) {
											client.getBase().insert("paiementclient", client.getjNumClient().getText()
													+ ", " + (String) (entry.getValue()[0]));
										}
									}
									JOptionPane.showMessageDialog(null, "Client mis � jour !");
									client.dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Erreur : Adresse vide ou incompl�te",
											"ATTENTION", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Erreur : Aucun mode de paiment selectionn�",
										"ATTENTION", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Erreur : Nombre Exemplaire et/ou Delais vide ou incorrecte", "ATTENTION",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erreur : Jour du mois suivant vide ou incorrecte",
								"ATTENTION", JOptionPane.WARNING_MESSAGE);
					}
				} else {
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
	
	private void validerCommandes(){
		Commandes commandes = (Commandes)classe;
		if(commandes.getDonnees().exist("Commandes", "NumCommande", "NumCommande = " + commandes.getjNumCommande().getText())){
			if(!commandes.getNumClient().getText().equals("") && commandes.getDonnees().exist("Clients", "NumClient", "NumClient = " + commandes.getNumClient().getText())){
				String [] re = commandes.getValeurDevises().get(commandes.getDevises().getSelectedItem());
				commandes.getBase().update("Commandes", "numClient = " + commandes.getNumClient().getText() + ", CdeComClient = '" + commandes.getjNumCommandeClient().getText() + "', Lblcommandes = '" + commandes.getjLibelle().getText()
				+  "', MntFour = " + commandes.getjFournitures().getText().replaceAll(",", "\\.")
				+ ", CoutMO = " + commandes.getjCout().getText().replaceAll(",", "\\.")
				+ ", HeureSite = " + commandes.getjHeureSite().getText().replaceAll(",", "\\.")
				+ ", HeureAtelier = " + commandes.getjHeureAtelier().getText().replaceAll(",", "\\.")
				+ ", Corem = " + commandes.getCheck().isSelected()
				+ ", Prefabrication = " + commandes.getjPrefabrication().getText().replaceAll(",", "\\.")
				+ ", MatierePrevu = " + commandes.getjPrevu().getText().replaceAll(",", "\\.")
				+ ", MatiereCommande = " + commandes.getjCommande().getText().replaceAll(",", "\\.")
				+ ", CodeDevise = " + re[0], "numCommandes = " + commandes.getjNumCommande().getText());
				comparaisonDevis(commandes);
				JOptionPane.showMessageDialog(null, "commandes valid� !");
				commandes.dispose();
				commandes.getFenetre().setEnabled(true);
				commandes.getFenetre().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Num�ro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	private void validerTermes(){
		Termes termes = (Termes)classe;
		if(termes.getDonnees().exist("Termes", "NumCommande, NumIndice", "NumCommande = " + termes.getNumeroCommande() + " AND NumIndice = " + termes.getNumeroIndice())){
			if(!termes.getNumIndice().getText().equals("") && termes.getDonnees().exist("Commandes", "NumCommande", "NumCommande = " + termes.getNumeroCommande())){
				termes.getBase().update("Termes", "Lblterme = '" + termes.getjLibelle().getText()
				+  "', MntFour = " + termes.getjFournitures().getText().replaceAll(",", "\\.")
				+ ", CoutMO = " + termes.getjCout().getText().replaceAll(",", "\\.")
				+ ", Prefabrication = " + termes.getjPrefabrication().getText().replaceAll(",", "\\.")
				, "numCommande = " + termes.getNumeroCommande() + " AND numIndice = " + termes.getNumeroIndice());
				JOptionPane.showMessageDialog(null, "Terme modifi� !");
				termes.dispose();
				termes.getFenetre().setEnabled(true);
				termes.getFenetre().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Num�ro de Commande inconnu", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Num�ro de Termes inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void comparaisonDevis(Commandes commandes){
		String [] ancienNum = new String [commandes.getDevisdelaCommande().length];
		String [] num = new String[commandes.getListDevis().size()];
		for (int i = 0; i < commandes.getListDevis().size(); i++) {
			commandes.getBase().update("Devis", "numcommande = " + commandes.getjNumCommande().getText(), "numdevis = " + (String)commandes.getListDevis().get(i)[0]);
			num[i] = (String)commandes.getListDevis().get(i)[0];
		}
		for (int i = 0; i < commandes.getDevisdelaCommande().length; i++) {
			ancienNum[i] = (String)commandes.getDevisdelaCommande()[i][0];
		}
		for(int i = 0; i < ancienNum.length; i++){
			boolean ok = false;
			for(int j = 0; j < num.length; j++){
				if(ancienNum[i].equals(num[j])){
					ok = true;
					break;
				}
			}
			if(!ok){
				commandes.getBase().update("Devis", "numcommande = null", "numdevis = " + ancienNum[i]);
			}
		}
	}

	private boolean mail(String mail) {
		boolean resultat = false;
		if (mail.contains("@")) {
			String[] tmp = mail.split("@");
			System.out.println(tmp[0]);
			System.out.println(tmp[1]);
			if (tmp[1].contains(".")) {
				resultat = true;
			}
		}
		return resultat;
	}
}