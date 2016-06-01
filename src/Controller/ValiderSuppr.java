package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Factures.Factures;
import View.Termes.Termes;

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
		} else if (type.equals("Termes")) {
			supprTermes();
		}else if (type.equals("Factures")) {
			supprFacture();
		}
	}

	private void supprDevis() {
		Devis devis = (Devis) classe;
		if (devis.getDonnees().exist("devis", "NumDevis", "NumDevis = " + devis.getjNumDevis().getText())) {
			if (!devis.getNumClient().getText().equals("") && devis.getDonnees().exist("clients", "NumClient",
					"NumClient = " + devis.getNumClient().getText())) {
				if (!devis.getDonnees().lier("numCommande", "devis", "numDevis = " + devis.getjNumDevis().getText())) {
					System.out.println(devis.getBase().delete("devis", "numDevis = " + devis.getjNumDevis().getText()));
					JOptionPane.showMessageDialog(null, "Devis supprimé !");
					devis.dispose();
					devis.getFenetre().setEnabled(true);
					devis.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Une commande est liée au devis", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void supprClient() {
		Client client = (Client) classe;
		if (client.getDonnees().exist("clients", "NumClient", "NumClient = " + client.getjNumClient().getText())) {
			if (!client.getDonnees().lier("numClient", "devis", "numClient = " + client.getjNumClient().getText())) {
				client.getBase().delete("paiementclient", "NumClient = " + client.getjNumClient().getText());
				client.getBase().delete("clients", "numclient = " + client.getjNumClient().getText());
				JOptionPane.showMessageDialog(null, "Client supprimé !");
				client.dispose();
			} else {
				JOptionPane.showMessageDialog(null,
						"Impossible de supprimer le Client.\nUn devis est liée à ce client.", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Client inexistant", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void supprCommandes() {
		Commandes commande = (Commandes) classe;
		if (commande.getDonnees().exist("commandes", "NumCommande",
				"NumCommande = " + commande.getjNumCommande().getText())) {
			if (!commande.getNumClient().getText().equals("") && commande.getDonnees().exist("clients", "NumClient",
					"NumClient = " + commande.getNumClient().getText())) {
				if (!commande.getDonnees().lier("numCommande", "termes", "numCommande = " + commande.getjNumCommande().getText())) {
					for (int i = 0; i < commande.getListDevis().size(); i++) {
						commande.getBase().update("devis", "numcommande = null",
								"numdevis = " + (String) commande.getListDevis().get(i)[0]);
					}
					commande.getBase().delete("commandes", "numCommande = " + commande.getjNumCommande().getText());
					JOptionPane.showMessageDialog(null, "Commande supprimé !");
					commande.dispose();
					commande.getFenetre().setEnabled(true);
					commande.getFenetre().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Un terme est liée à la commande", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Numéro de Commande inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void supprTermes() {
		Termes termes = (Termes) classe;
		boolean exist = termes.getDonnees().exist("termes", "NumCommande, NumIndice, NumFacture", "NumCommande = " + termes.getNumeroCommande() + " AND NumIndice = " + termes.getNumeroIndice() + " AND NumFacture is null");
		if(termes.getNumFacture() == null){
			if(exist){
			if (!termes.getNumIndice().getText().equals("") && termes.getDonnees().exist("commandes", "Numcommande",
					"NumCommande = " + termes.getNumeroCommande())) {
					termes.getBase().delete("termes", "numCommande = " + termes.getNumeroCommande() + " AND NumIndice = " + termes.getNumeroIndice() + " AND NumFacture is null");
					JOptionPane.showMessageDialog(null, "Terme supprimé !");
					termes.dispose();
					termes.getFenetre().setEnabled(true);
					termes.getFenetre().setVisible(true);
				
			} else {
				JOptionPane.showMessageDialog(null, "Erreur : Numéro d'indice ou de commande incorrecte", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
			} else {
			JOptionPane.showMessageDialog(null, "Terme inexistant ", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Erreur : Une facture est liée au Terme", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}


private void supprFacture() {
	Factures facture = (Factures) classe;
	if (facture.getDonnees().exist("factures", "NumFacture",
			"NumFacture = " + facture.getjNumFacture().getText())) {
		if (facture.getDonnees().exist("termes", "Numcommande, NumIndice",
				"NumCommande = " + facture.getNumeroCommande() + " AND NumIndice = " + facture.getNumeroIndice())) {
				facture.getBase().update("termes", "numfacture = null", "numcommande = " + facture.getNumeroCommande() + " AND numindice = " + facture.getNumeroIndice() + " AND numfacture = " + facture.getjNumFacture().getText());
				facture.getBase().delete("factures", "numfacture = " + facture.getjNumFacture().getText());
				JOptionPane.showMessageDialog(null, "Facture supprimé !");
				facture.dispose();
				facture.getFenetre().setEnabled(true);
				facture.getFenetre().setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "Erreur : Numéro d'indice ou de commande incorrecte", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	} else {
		JOptionPane.showMessageDialog(null, "Terme inexistant ", "ATTENTION", JOptionPane.WARNING_MESSAGE);
	}
}
}
