package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

import View.Commandes.Commandes;
import View.Devis.Devis;
import View.Pointage.SaisiePointage;
import View.Impression.Devis.EtatsDevis;
import View.Impression.Commandes.EtatsCommandes;
import View.Impression.Pointage.EtatsHeureSpePointage;
import View.Impression.Clients.EtatsClients;
import View.Impression.Factures.EtatsFactures;

public class FocusJText implements FocusListener {

	private Object devis;
	private String classe;

	public FocusJText(Object d, String typeClasse) {
		this.devis = d;
		this.classe = typeClasse;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(true);
		} 
		
		else if (classe.equals("EtatsDevis")) {
			if (((JTextField) arg0.getSource()) == ((EtatsDevis) devis).getjNumDevis1().getZoneTexte()
					&& !((EtatsDevis) devis).getjNumDevis1().getZoneTexte().getText().isEmpty()) {
				((EtatsDevis) devis).getjNumDevis1().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsDevis) devis).getjNumDevis2().getZoneTexte()
					&& !((EtatsDevis) devis).getjNumDevis2().getZoneTexte().getText().isEmpty()) {
				((EtatsDevis) devis).getjNumDevis2().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsDevis) devis).getjNumClient().getZoneTexte()
					&& !((EtatsDevis) devis).getjNumClient().getZoneTexte().getText().isEmpty()) {
				((EtatsDevis) devis).getjNumClient().getFenetreRecherche().setVisible(true);
			}
		}
		
		else if (classe.equals("EtatsCommandes")) {
			if (((JTextField) arg0.getSource()) == ((EtatsCommandes) devis).getjNumCommandes1().getZoneTexte()
					&& !((EtatsCommandes) devis).getjNumCommandes1().getZoneTexte().getText().isEmpty()) {
				((EtatsCommandes) devis).getjNumCommandes1().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsCommandes) devis).getjNumCommandes2().getZoneTexte()
					&& !((EtatsCommandes) devis).getjNumCommandes2().getZoneTexte().getText().isEmpty()) {
				((EtatsCommandes) devis).getjNumCommandes2().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsCommandes) devis).getjNumClient().getZoneTexte()
					&& !((EtatsCommandes) devis).getjNumClient().getZoneTexte().getText().isEmpty()) {
				((EtatsCommandes) devis).getjNumClient().getFenetreRecherche().setVisible(true);
			}
		}
		
		else if (classe.equals("EtatsClients")) {
			if (((JTextField) arg0.getSource()) == ((EtatsClients) devis).getjNumClient().getZoneTexte()
					&& !((EtatsClients) devis).getjNumClient().getZoneTexte().getText().isEmpty()) {
				((EtatsClients) devis).getjNumClient().getFenetreRecherche().setVisible(true);
			} 
		}
		else if (classe.equals("EtatsFactures")) {
			if (((JTextField) arg0.getSource()) == ((EtatsFactures) devis).getjNumClient().getZoneTexte()
					&& !((EtatsFactures) devis).getjNumClient().getZoneTexte().getText().isEmpty()) {
				((EtatsFactures) devis).getjNumClient().getFenetreRecherche().setVisible(true);
			} 
		}

		else if (classe.equals("EHSP")) {
			if (((JTextField) arg0.getSource()) == ((EtatsHeureSpePointage) devis).getjNumDevis().getZoneTexte()
					&& !((EtatsHeureSpePointage) devis).getjNumDevis().getZoneTexte().getText().isEmpty()) {
				((EtatsHeureSpePointage) devis).getjNumDevis().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsHeureSpePointage) devis).getjNumCommande()
					.getZoneTexte()
					&& !((EtatsHeureSpePointage) devis).getjNumCommande().getZoneTexte().getText().isEmpty()) {
				((EtatsHeureSpePointage) devis).getjNumCommande().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsHeureSpePointage) devis).getjNumPersonnel()
					.getZoneTexte()
					&& !((EtatsHeureSpePointage) devis).getjNumPersonnel().getZoneTexte().getText().isEmpty()) {
				((EtatsHeureSpePointage) devis).getjNumPersonnel().getFenetreRecherche().setVisible(true);
			} else if (((JTextField) arg0.getSource()) == ((EtatsHeureSpePointage) devis).getjNumCategorie()
					.getZoneTexte()
					&& !((EtatsHeureSpePointage) devis).getjNumCategorie().getZoneTexte().getText().isEmpty()) {
				((EtatsHeureSpePointage) devis).getjNumCategorie().getFenetreRecherche().setVisible(true);
			}
		}

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (classe.equals("Devis")) {
			((Devis) devis).getNumClient().getFenetreRecherche().setVisible(false);
		} else if (classe.equals("Pointage")) {
			if (((SaisiePointage) devis).getjNumDevis().getFenetreRecherche().isVisible()) {
				((SaisiePointage) devis).getjNumDevis().getFenetreRecherche().setVisible(false);
			} else if (((SaisiePointage) devis).getjCode().getFenetreRecherche().isVisible()) {
				((SaisiePointage) devis).getjCode().getFenetreRecherche().setVisible(false);
			}
		} else if (classe.equals("EtatsDevis")) {
			if (((EtatsDevis) devis).getjNumDevis1().getFenetreRecherche().isVisible()) {
				((EtatsDevis) devis).getjNumDevis1().getFenetreRecherche().setVisible(false);
			} else if (((EtatsDevis) devis).getjNumDevis2().getFenetreRecherche().isVisible()) {
				((EtatsDevis) devis).getjNumDevis2().getFenetreRecherche().setVisible(false);
			} else if (((EtatsDevis) devis).getjNumClient().getFenetreRecherche().isVisible()) {
				((EtatsDevis) devis).getjNumClient().getFenetreRecherche().setVisible(false);
			}
		}
		
		else if (classe.equals("EtatsCommandes")) {
			if (((EtatsCommandes) devis).getjNumCommandes1().getFenetreRecherche().isVisible()) {
				((EtatsCommandes) devis).getjNumCommandes1().getFenetreRecherche().setVisible(false);
			} else if (((EtatsCommandes) devis).getjNumCommandes2().getFenetreRecherche().isVisible()) {
				((EtatsCommandes) devis).getjNumCommandes2().getFenetreRecherche().setVisible(false);
			} else if (((EtatsCommandes) devis).getjNumClient().getFenetreRecherche().isVisible()) {
				((EtatsCommandes) devis).getjNumClient().getFenetreRecherche().setVisible(false);
			}
		}
		
		else if (classe.equals("EtatsClients")) {
			if (((EtatsClients) devis).getjNumClient().getFenetreRecherche().isVisible()) {
				((EtatsClients) devis).getjNumClient().getFenetreRecherche().setVisible(false);
			} 
		}
		else if (classe.equals("EtatsFactures")) {
			if (((EtatsFactures) devis).getjNumClient().getFenetreRecherche().isVisible()) {
				((EtatsFactures) devis).getjNumClient().getFenetreRecherche().setVisible(false);
			} 
		}

		else if (classe.equals("EHSP")) {
			if (((EtatsHeureSpePointage) devis).getjNumDevis().getFenetreRecherche().isVisible()) {
				((EtatsHeureSpePointage) devis).getjNumDevis().getFenetreRecherche().setVisible(false);
			} else if (((EtatsHeureSpePointage) devis).getjNumCommande().getFenetreRecherche().isVisible()) {
				((EtatsHeureSpePointage) devis).getjNumCommande().getFenetreRecherche().setVisible(false);
			} else if (((EtatsHeureSpePointage) devis).getjNumPersonnel().getFenetreRecherche().isVisible()) {
				((EtatsHeureSpePointage) devis).getjNumPersonnel().getFenetreRecherche().setVisible(false);
			} else if (((EtatsHeureSpePointage) devis).getjNumCategorie().getFenetreRecherche().isVisible()) {
				((EtatsHeureSpePointage) devis).getjNumCategorie().getFenetreRecherche().setVisible(false);
			}
		}
		name();
	}

	public void name() {
		if (classe.equals("Devis")) {
			if (!((Devis) devis).getNumClient().getText().equals("")
					&& !((Devis) devis).getNumClient().getText().equals("(vide)") && ((Devis) devis).getDonnees()
							.exist("clients", "NumClient", "NumClient = " + ((Devis) devis).getNumClient().getText())) {
				int i = 0;
				while (!((Devis) devis).getListClient()[i][0].toString()
						.equals(((Devis) devis).getNumClient().getText())
						&& i < ((Devis) devis).getListClient().length) {
					i++;
				}
				if (i < ((Devis) devis).getListClient().length) {
					((Devis) devis).getNameClient().setText("Client : " + ((Devis) devis).getListClient()[i][1]);
					((Devis) devis).getNameClient().setBounds(((Devis) devis).getNameClient().getX(),
							((Devis) devis).getNameClient().getY(),
							((Devis) devis).getNameClient().getPreferredSize().width,
							((Devis) devis).getNameClient().getPreferredSize().height);
				} else {
					((Devis) devis).getNameClient().setText("Error");
					((Devis) devis).getNameClient().setBounds(((Devis) devis).getNameClient().getX(),
							((Devis) devis).getNameClient().getY(),
							((Devis) devis).getNameClient().getPreferredSize().width,
							((Devis) devis).getNameClient().getPreferredSize().height);
				}
				((Devis) devis).repaint();
			} else {
				((Devis) devis).getNumClient().setText("");
				((Devis) devis).getNameClient().setText("");
				((Devis) devis).repaint();
			}
		}

		else if (classe.equals("Commandes")) {
			if (!((Commandes) devis).getNumClient().getText().equals("")
					&& !((Commandes) devis).getNumClient().getText().equals("(vide)")
					&& ((Commandes) devis).getDonnees().exist("clients", "NumClient",
							"NumClient = " + ((Commandes) devis).getNumClient().getText())) {
				int i = 0;
				while (!((Commandes) devis).getListClient()[i][0].toString()
						.equals(((Commandes) devis).getNumClient().getText())
						&& i < ((Commandes) devis).getListClient().length) {
					i++;
				}
				if (i < ((Commandes) devis).getListClient().length) {
					((Commandes) devis).getNameClient()
							.setText("Client : " + ((Commandes) devis).getListClient()[i][1]);
					((Commandes) devis).getNameClient().setBounds(((Commandes) devis).getNameClient().getX(),
							((Commandes) devis).getNameClient().getY(),
							((Commandes) devis).getNameClient().getPreferredSize().width,
							((Commandes) devis).getNameClient().getPreferredSize().height);
				} else {
					((Commandes) devis).getNameClient().setText("Error");
					((Commandes) devis).getNameClient().setBounds(((Commandes) devis).getNameClient().getX(),
							((Commandes) devis).getNameClient().getY(),
							((Commandes) devis).getNameClient().getPreferredSize().width,
							((Commandes) devis).getNameClient().getPreferredSize().height);
				}
				((Commandes) devis).repaint();
			}
		}

		else if (classe.equals("Pointage")) {
			if (!((SaisiePointage) devis).getjNumDevis().getZoneTexte().getText().equals("")
					&& !((SaisiePointage) devis).getjNumDevis().getZoneTexte().getText().equals("(vide)")
					&& ((SaisiePointage) devis).getDonnees().exist("devis", "NumDevis",
							"NumDevis = " + ((SaisiePointage) devis).getjNumDevis().getZoneTexte().getText())
					&& !((SaisiePointage) devis).getjCode().getZoneTexte().getText().equals("")
					&& !((SaisiePointage) devis).getjCode().getZoneTexte().getText().equals("(vide)")
					&& ((SaisiePointage) devis).getDonnees().exist("personne", "NumPersonnel",
							"NumPersonnel = " + ((SaisiePointage) devis).getjCode().getZoneTexte().getText())) {
				int i = 0;
				while (!((SaisiePointage) devis).getListDevis()[i][0].toString()
						.equals(((SaisiePointage) devis).getjNumDevis().getText())
						&& i < ((SaisiePointage) devis).getListDevis().length) {
					i++;
				}
				if(((SaisiePointage) devis).getjDate().getDate() != null){
					if (((SaisiePointage) devis).getDonnees().exist("pointage", "NumPersonnel, NumDevis, NumSem, Annee",
						"NumPersonnel = " + ((SaisiePointage) devis).getjCode().getText() + " AND NumDevis = "
								+ ((SaisiePointage) devis).getjNumDevis().getText() + " AND NumSem = "
								+ new SimpleDateFormat("ww").format(((SaisiePointage) devis).getjDate().getDate())
								+ " AND Annee = "
								+ new SimpleDateFormat("yyyy").format(((SaisiePointage) devis).getjDate().getDate()))) {
					String[] res = ((SaisiePointage) devis).getDonnees().fiche(
							"Round(HrPassNormal, 2), Round(HrPassSup125,2), Round(HrPassSup150,2), Round(HrPassSup200,2)",
							"pointage",
							"NumPersonnel = " + ((SaisiePointage) devis).getjCode().getText() + " AND NumDevis = "
									+ ((SaisiePointage) devis).getjNumDevis().getText() + " AND NumSem = "
									+ new SimpleDateFormat("ww").format(((SaisiePointage) devis).getjDate().getDate())
									+ " AND Annee = " + new SimpleDateFormat("yyyy")
											.format(((SaisiePointage) devis).getjDate().getDate()));
					((SaisiePointage) devis).getjHN().setText(res[0].replaceAll("\\.", ","));
					((SaisiePointage) devis).getjHSC125().setText(res[1].replaceAll("\\.", ","));
					((SaisiePointage) devis).getjHSC15().setText(res[2].replaceAll("\\.", ","));
					((SaisiePointage) devis).getjHSC2().setText(res[3].replaceAll("\\.", ","));
					}
				}
				((SaisiePointage) devis).repaint();
			}
		}

		else if (classe.equals("EtatsDevis")) {
			if (((EtatsDevis) devis).getJtp().getSelectedIndex() == 0) {
				if (!((EtatsDevis) devis).getjNumClient().getText().equals("")
						&& !((EtatsDevis) devis).getjNumClient().getText().equals("(vide)")
						&& ((EtatsDevis) devis).getDonnees().exist("clients", "NumClient",
								"NumClient = " + ((EtatsDevis) devis).getjNumClient().getText())) {
					int i = 0;
					while (!((EtatsDevis) devis).getListClients()[i][0].toString()
							.equals(((EtatsDevis) devis).getjNumClient().getText())
							&& i < ((EtatsDevis) devis).getListClients().length) {
						i++;
					}

					if (i < ((EtatsDevis) devis).getListClients().length) {
						((EtatsDevis) devis).getLabelClient().setText(((EtatsDevis) devis).getListClients()[i][1] + "");
						((EtatsDevis) devis).getLabelClient().setBounds(((EtatsDevis) devis).getLabelClient().getX(),
								((EtatsDevis) devis).getLabelClient().getY(),
								((EtatsDevis) devis).getLabelClient().getPreferredSize().width,
								((EtatsDevis) devis).getLabelClient().getPreferredSize().height);
					} else {
						((EtatsDevis) devis).getLabelClient().setText("Error");
						((EtatsDevis) devis).getLabelClient().setBounds(((EtatsDevis) devis).getLabelClient().getX(),
								((EtatsDevis) devis).getLabelClient().getY(),
								((EtatsDevis) devis).getLabelClient().getPreferredSize().width,
								((EtatsDevis) devis).getLabelClient().getPreferredSize().height);
					}
					((EtatsDevis) devis).repaint();
				} else {
					((EtatsDevis) devis).getjNumClient().setText("");
					((EtatsDevis) devis).getLabelClient().setText("");
					((EtatsDevis) devis).repaint();
				}
			} else if (((EtatsDevis) devis).getJtp().getSelectedIndex() == 1) {
				if (!((EtatsDevis) devis).getjNumClient2().getText().equals("")
						&& !((EtatsDevis) devis).getjNumClient2().getText().equals("(vide)")
						&& ((EtatsDevis) devis).getDonnees().exist("clients", "NumClient",
								"NumClient = " + ((EtatsDevis) devis).getjNumClient2().getText())) {
					int i = 0;
					while (!((EtatsDevis) devis).getListClients()[i][0].toString()
							.equals(((EtatsDevis) devis).getjNumClient2().getText())
							&& i < ((EtatsDevis) devis).getListClients().length) {
						i++;
					}

					if (i < ((EtatsDevis) devis).getListClients().length) {
						((EtatsDevis) devis).getLabelClient2()
								.setText(((EtatsDevis) devis).getListClients()[i][1] + "");
						((EtatsDevis) devis).getLabelClient2().setBounds(((EtatsDevis) devis).getLabelClient2().getX(),
								((EtatsDevis) devis).getLabelClient2().getY(),
								((EtatsDevis) devis).getLabelClient2().getPreferredSize().width,
								((EtatsDevis) devis).getLabelClient2().getPreferredSize().height);
					} else {
						((EtatsDevis) devis).getLabelClient2().setText("Error");
						((EtatsDevis) devis).getLabelClient2().setBounds(((EtatsDevis) devis).getLabelClient2().getX(),
								((EtatsDevis) devis).getLabelClient2().getY(),
								((EtatsDevis) devis).getLabelClient2().getPreferredSize().width,
								((EtatsDevis) devis).getLabelClient2().getPreferredSize().height);
					}
					((EtatsDevis) devis).repaint();
				} else {
					((EtatsDevis) devis).getjNumClient2().setText("");
					((EtatsDevis) devis).getLabelClient2().setText("");
					((EtatsDevis) devis).repaint();
				}
			}
		}
		
		else if (classe.equals("EtatsCommandes")) {
			if (((EtatsCommandes) devis).getJtp().getSelectedIndex() == 0) {
				if (!((EtatsCommandes) devis).getjNumClient().getText().equals("")
						&& !((EtatsCommandes) devis).getjNumClient().getText().equals("(vide)")
						&& ((EtatsCommandes) devis).getDonnees().exist("clients", "NumClient",
								"NumClient = " + ((EtatsCommandes) devis).getjNumClient().getText())) {
					int i = 0;
					while (!((EtatsCommandes) devis).getListClients()[i][0].toString()
							.equals(((EtatsCommandes) devis).getjNumClient().getText())
							&& i < ((EtatsCommandes) devis).getListClients().length) {
						i++;
					}

					if (i < ((EtatsCommandes) devis).getListClients().length) {
						((EtatsCommandes) devis).getLabelClient().setText(((EtatsCommandes) devis).getListClients()[i][1] + "");
						((EtatsCommandes) devis).getLabelClient().setBounds(((EtatsCommandes) devis).getLabelClient().getX(),
								((EtatsCommandes) devis).getLabelClient().getY(),
								((EtatsCommandes) devis).getLabelClient().getPreferredSize().width,
								((EtatsCommandes) devis).getLabelClient().getPreferredSize().height);
					} else {
						((EtatsCommandes) devis).getLabelClient().setText("Error");
						((EtatsCommandes) devis).getLabelClient().setBounds(((EtatsCommandes) devis).getLabelClient().getX(),
								((EtatsCommandes) devis).getLabelClient().getY(),
								((EtatsCommandes) devis).getLabelClient().getPreferredSize().width,
								((EtatsCommandes) devis).getLabelClient().getPreferredSize().height);
					}
					((EtatsCommandes) devis).repaint();
				} else {
					((EtatsCommandes) devis).getjNumClient().setText("");
					((EtatsCommandes) devis).getLabelClient().setText("");
					((EtatsCommandes) devis).repaint();
				}
			} 
		}
		
		else if (classe.equals("EtatsClients")) {
			if (((EtatsClients) devis).getJtp().getSelectedIndex() == 3) {
				if (!((EtatsClients) devis).getjNumClient().getText().equals("")
						&& !((EtatsClients) devis).getjNumClient().getText().equals("(vide)")
						&& ((EtatsClients) devis).getDonnees().exist("clients", "NumClient",
								"NumClient = " + ((EtatsClients) devis).getjNumClient().getText())) {
					int i = 0;
					while (!((EtatsClients) devis).getListClients()[i][0].toString()
							.equals(((EtatsClients) devis).getjNumClient().getText())
							&& i < ((EtatsClients) devis).getListClients().length) {
						i++;
					}

					if (i < ((EtatsClients) devis).getListClients().length) {
						((EtatsClients) devis).getLabelClient().setText(((EtatsClients) devis).getListClients()[i][1] + "");
						((EtatsClients) devis).getLabelClient().setBounds(((EtatsClients) devis).getLabelClient().getX(),
								((EtatsClients) devis).getLabelClient().getY(),
								((EtatsClients) devis).getLabelClient().getPreferredSize().width,
								((EtatsClients) devis).getLabelClient().getPreferredSize().height);
					} else {
						((EtatsClients) devis).getLabelClient().setText("Error");
						((EtatsClients) devis).getLabelClient().setBounds(((EtatsClients) devis).getLabelClient().getX(),
								((EtatsClients) devis).getLabelClient().getY(),
								((EtatsClients) devis).getLabelClient().getPreferredSize().width,
								((EtatsClients) devis).getLabelClient().getPreferredSize().height);
					}
					((EtatsClients) devis).repaint();
				} else {
					((EtatsClients) devis).getjNumClient().setText("");
					((EtatsClients) devis).getLabelClient().setText("");
					((EtatsClients) devis).repaint();
				}
			} 
		}
		else if (classe.equals("EtatsFactures")) {
			if (((EtatsFactures) devis).getJtp().getSelectedIndex() == 1) {
				if (!((EtatsFactures) devis).getjNumClient().getText().equals("")
						&& !((EtatsFactures) devis).getjNumClient().getText().equals("(vide)")
						&& ((EtatsFactures) devis).getDonnees().exist("clients", "NumClient",
								"NumClient = " + ((EtatsFactures) devis).getjNumClient().getText())) {
					int i = 0;
					while (!((EtatsFactures) devis).getListClients()[i][0].toString()
							.equals(((EtatsFactures) devis).getjNumClient().getText())
							&& i < ((EtatsFactures) devis).getListClients().length) {
						i++;
					}

					if (i < ((EtatsFactures) devis).getListClients().length) {
						((EtatsFactures) devis).getLabelClient().setText(((EtatsFactures) devis).getListClients()[i][1] + "");
						((EtatsFactures) devis).getLabelClient().setBounds(((EtatsFactures) devis).getLabelClient().getX(),
								((EtatsFactures) devis).getLabelClient().getY(),
								((EtatsFactures) devis).getLabelClient().getPreferredSize().width,
								((EtatsFactures) devis).getLabelClient().getPreferredSize().height);
					} else {
						((EtatsFactures) devis).getLabelClient().setText("Error");
						((EtatsFactures) devis).getLabelClient().setBounds(((EtatsFactures) devis).getLabelClient().getX(),
								((EtatsFactures) devis).getLabelClient().getY(),
								((EtatsFactures) devis).getLabelClient().getPreferredSize().width,
								((EtatsFactures) devis).getLabelClient().getPreferredSize().height);
					}
					((EtatsFactures) devis).repaint();
				} else {
					((EtatsFactures) devis).getjNumClient().setText("");
					((EtatsFactures) devis).getLabelClient().setText("");
					((EtatsFactures) devis).repaint();
				}
			} 
		}

	}

}