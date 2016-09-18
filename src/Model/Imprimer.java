package Model;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.toedter.calendar.JTextFieldDateEditor;

import BDD.Base;
import Thread.ThreadImpression;
import Thread.ThreadImpressionDivers;
import View.InterfaceMail;
import View.Impression.Clients.EtatsClients;
import View.Impression.Commandes.EtatsCommandes;
import View.Impression.Devis.EtatsDevis;
import View.Impression.Factures.EtatsFactures;
import View.Impression.Pointage.EtatsHeureSpePointage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Imprimer {

	protected Base bdd;
	protected String numfacture, typeClasse, impression;
	protected Double valeur;
	protected ThreadImpression thread;
	protected ThreadImpressionDivers thread2;
	protected Object objet;
	
	public Imprimer(String numFacture, Base base, Double valeur, ThreadImpression thread, String impression) {
		this.bdd = base;
		this.numfacture = numFacture;
		this.valeur = valeur;
		this.thread = thread;
		this.impression = impression;
		initFacture();
	}
	
	public Imprimer(Object objet, ThreadImpressionDivers thread, String typeClasse, String impression) {
		this.thread2 = thread;
		this.typeClasse = typeClasse;
		this.objet = objet;
		this.impression = impression;
		if(typeClasse.equals("EtatsDevis")){
			initEtatsDevis();
		}
		else if(typeClasse.equals("EtatsCommandes")){
			initEtatCommandes();
		}
		else if(typeClasse.equals("EtatsClients")){
			initEtatsClients();
		}
		else if(typeClasse.equals("EHSP")){
			initEHSP();
		}
		else if(typeClasse.equals("EtatsFactures")){
			initEtatsFactures();
		}
		
	}

	@SuppressWarnings("unchecked")
	private void initFacture() {
    	Double val = valeur;
		if(val < 0){
			val = val * -1;
		}
		
		Donnees donnees = new Donnees(bdd);
		String [] res = donnees.fiche("c.NomClient, c.NbEx", "clients as c, factures as f, termes as t, commandes as co", "f.NumFacture = " + numfacture + " and t.numfacture = f.numfacture and t.numcommande = co.numcommande and co.numclient = c.numclient");		
		int i = new Double(val).intValue(); //recuperer la partie entiere
		double decimale = ((val*100)-(new Double(i).doubleValue() * 100))/100;
		decimale = decimale * 100;
		int d = new Double(decimale).intValue();
	
		NumberFormat formatter = new RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT);
		String result = formatter.format(new Double(i).doubleValue());
		result += " euros " + formatter.format(new Double(d).doubleValue()) + " cts";
        String path1;
        if(valeur >= 0){
			path1 = "lib/modeles/Factures.jasper";
		}
		else{
			path1 = "lib/modeles/Avoir.jasper";
		}
        @SuppressWarnings("rawtypes")
		Map parameters = new HashMap();
        parameters.put("NumFacture", Integer.parseInt(numfacture));
        parameters.put("ValeurText", result);
        String path = new File("lib/images/STID.png").getAbsolutePath();
        parameters.put("Logo", path);

        // - Execution du rapport
        if(impression.equals("Imprimer")){
        	traitementImprimer(path1, parameters, bdd.getCon(), "Facture n°" + numfacture + " - " + res[0], Integer.parseInt(res[1]));
        }
        else if(impression.equals("PDF")){
        	 traitementPDF(path1, parameters, bdd.getCon(), "Facture n°" + numfacture + " - " + res[0]);
        }
        else if(impression.equals("Apercu")){
        	traitementApercu(path1, parameters, bdd.getCon(), "Facture n°" + numfacture + " - " + res[0], Integer.parseInt(res[1]));
       	}
        else if(impression.equals("Mail")){
        	try {
				traitementMail(path1, parameters, bdd.getCon(), "Facture n°" + numfacture + " - " + res[0], numfacture);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initEtatsDevis() {
		boolean erreur = false;
		EtatsDevis etat = ((EtatsDevis)objet);
		this.bdd = etat.getBdd();
		Donnees donnees = etat.getDonnees();
		String path1;
		Map parameters = new HashMap();
		String conditions = "";
		String text = "";
		String nomFichier;
		if(etat.getJtp().getSelectedIndex() == 0){
			path1 = "lib/modeles/ListeDevisEnCours.jasper";
			nomFichier = "Liste Devis en-cours";
			if(!etat.getjNumDevis1().getText().isEmpty() && !etat.getjNumDevis1().getText().equals("(vide)")){
				if(!donnees.exist("devis", "NumDevis", "NumDevis = " + etat.getjNumDevis1().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de devis du début est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
						conditions = "d.numdevis >= " + etat.getjNumDevis1().getZoneTexte().getText();
						text = "A Partir du devis n° : " + etat.getjNumDevis1().getZoneTexte().getText() + " " +donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis1().getZoneTexte().getText())[0];
				}
			}
			if(!etat.getjNumDevis2().getZoneTexte().getText().isEmpty() && !etat.getjNumDevis2().getZoneTexte().getText().equals("(vide)")){
				System.out.println(etat.getjNumDevis2().getZoneTexte().getText() + " - " + etat.getjNumDevis1().getZoneTexte().getText());
				if(!etat.getjNumDevis1().getZoneTexte().getText().isEmpty() && !etat.getjNumDevis1().getZoneTexte().getText().equals("(vide)")){
					if(!donnees.exist("devis", "NumDevis", "NumDevis = " + etat.getjNumDevis2().getZoneTexte().getText())){
						thread2.getFrame().setVisible(false);
						erreur = true;
						JOptionPane.showMessageDialog(null, "Le Numéro de devis de fin est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
					}
					else{
						if(!conditions.equals("")){
							conditions += " and ";
							text += "\n";
							conditions += "d.numdevis <= " + etat.getjNumDevis2().getZoneTexte().getText();
							text += "Jusqu'au devis n° : " + etat.getjNumDevis2().getZoneTexte().getText() + " " + donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis2().getZoneTexte().getText())[0];
						}
					}
				}
				else{
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Erreur d'intervalle", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				
			}
			if(!etat.getjNumClient().getZoneTexte().getText().isEmpty() && !etat.getjNumClient().getZoneTexte().getText().equals("(vide)")){
				if(!donnees.exist("clients", "NumClient", "NumClient = " + etat.getjNumClient().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de client est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(!conditions.equals("")){
						conditions += " and ";
						text += "\n";
					}
					conditions += "c.numclient = " + etat.getjNumClient().getZoneTexte().getText();
					text += "Pour le client n° : " + etat.getjNumClient().getZoneTexte().getText() + " " + donnees.fiche("nomclient", "clients", "numclient = " + etat.getjNumClient().getZoneTexte().getText())[0];
				}
			}
			
			if(conditions.equals("")){
				conditions = "d.numdevis is not null";
			}
	        parameters.put("Conditions", conditions);
	        parameters.put("textDevis", text);
	        if(erreur == false){
	        	if(impression.equals("Imprimer")){
	        		traitementImprimer(path1, parameters, bdd.getCon(), "Liste des Devis en-cours", 1);
	        	}
	        	else if(impression.equals("PDF")){
	        		 traitementPDF(path1, parameters, bdd.getCon(), "Liste des Devis en-cours");
	        	}
	        	else if(impression.equals("Apercu")){
	        		traitementApercu(path1, parameters, bdd.getCon(), "Liste des Devis en-cours", 1);
	        	}
	        }

		}
		else if(etat.getJtp().getSelectedIndex() == 1){
			System.out.println("entrer");
			path1 = "";
			nomFichier = "Liste Devis";
			if(etat.getBr1().isSelected()){
				path1 = "lib/modeles/ListeDevis.jasper";
			}
			else if (etat.getBr2().isSelected()){
				path1 = "lib/modeles/ListeDevisClient.jasper";
			}
			else if (etat.getBr3().isSelected()){
				path1 = "lib/modeles/ListeDevisDate.jasper";
			}
			if(!etat.getjNumDevis3().getText().isEmpty() && !etat.getjNumDevis3().getText().equals("(vide)")){
				if(!donnees.exist("devis", "NumDevis", "NumDevis = " + etat.getjNumDevis3().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de devis du début est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
						conditions = "d.numdevis >= " + etat.getjNumDevis3().getZoneTexte().getText();
						text = "A Partir du devis n° : " + etat.getjNumDevis3().getZoneTexte().getText() + " " +donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis3().getZoneTexte().getText())[0];
				}
			}
			if(!etat.getjNumClient2().getZoneTexte().getText().isEmpty() && !etat.getjNumClient2().getZoneTexte().getText().equals("(vide)")){
				if(!donnees.exist("clients", "NumClient", "NumClient = " + etat.getjNumClient2().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de client est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(!conditions.equals("")){
						conditions += " and ";
						text += "\n";
					}
					conditions += "d.numclient = " + etat.getjNumClient2().getZoneTexte().getText();
					text += "Pour le client n° : " + etat.getjNumClient2().getZoneTexte().getText() + " " + donnees.fiche("nomclient", "clients", "numclient = " + etat.getjNumClient2().getZoneTexte().getText())[0];
				}
			}
			if(!((JTextFieldDateEditor)etat.getjDate().getDateEditor()).getText().isEmpty() && !etat.getjNumClient().getZoneTexte().getText().equals("(vide)")){
				if(!isValid(((JTextFieldDateEditor)etat.getjDate().getDateEditor()).getText(), etat.getjDate().getDateFormatString())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le format de la date est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(!conditions.equals("")){
						conditions += " and ";
						text += "\n";
					}
					conditions += "d.datedevis = " + new SimpleDateFormat("yyyy/MM/dd").format(etat.getjDate().getDate());
					text += "A partir du : " + ((JTextFieldDateEditor)etat.getjDate().getDateEditor()).getText();
				}
			}
			
			if(conditions.equals("")){
				conditions = "d.numdevis is not null";
			}
	        parameters.put("Conditions", conditions);
	        parameters.put("textDevis", text);
	        if(erreur == false){
	        	if(impression.equals("Imprimer")){
	        		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
	        	}
	        	else if(impression.equals("PDF")){
	        		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
	        	}
	        	else if(impression.equals("Apercu")){
	        		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
	        	}
	        }
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initEtatCommandes(){
		boolean erreur = false;
		EtatsCommandes etat = ((EtatsCommandes)objet);
		this.bdd = etat.getBdd();
		Donnees donnees = etat.getDonnees();
		String path1;
		Map parameters = new HashMap();
		String conditions = "";
		String text = "";
		String nomFichier;
		if(etat.getJtp().getSelectedIndex() == 0){
			path1 = "lib/modeles/ListeCommandes.jasper";
			nomFichier = "Liste Commandes";
			if(!etat.getjNumCommandes1().getText().isEmpty() && !etat.getjNumCommandes1().getText().equals("(vide)")){
				if(!donnees.exist("commandes", "NumCommande", "NumCommande = " + etat.getjNumCommandes1().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de commande du début est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
						conditions = "c.numcommande >= " + etat.getjNumCommandes1().getZoneTexte().getText();
						text = "A Partir de la commande n° : " + etat.getjNumCommandes1().getZoneTexte().getText() + " " +donnees.fiche("lblcommande", "commandes", "numcommande = " + etat.getjNumCommandes1().getZoneTexte().getText())[0];
				}
			}
			if(!etat.getjNumCommandes2().getZoneTexte().getText().isEmpty() && !etat.getjNumCommandes2().getZoneTexte().getText().equals("(vide)")){
				if(!etat.getjNumCommandes1().getZoneTexte().getText().isEmpty() && !etat.getjNumCommandes1().getZoneTexte().getText().equals("(vide)")){
					if(!donnees.exist("commandes", "NumCommande", "NumCommande = " + etat.getjNumCommandes2().getZoneTexte().getText())){
						thread2.getFrame().setVisible(false);
						erreur = true;
						JOptionPane.showMessageDialog(null, "Le Numéro de commandes de fin est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
					}
					else{
						if(!conditions.equals("")){
							conditions += " and ";
							text += "\n";
							conditions += "c.numcommande <= " + etat.getjNumCommandes2().getZoneTexte().getText();
							text += "Jusqu'à la commande n° : " + etat.getjNumCommandes2().getZoneTexte().getText() + " " + donnees.fiche("lblcommande", "commandes", "numcommande = " + etat.getjNumCommandes2().getZoneTexte().getText())[0];
						}
					}
				}
				else{
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Erreur d'intervalle", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				
			}
			if(!etat.getjNumClient().getZoneTexte().getText().isEmpty() && !etat.getjNumClient().getZoneTexte().getText().equals("(vide)")){
				if(!donnees.exist("clients", "NumClient", "NumClient = " + etat.getjNumClient().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de client est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(!conditions.equals("")){
						conditions += " and ";
						text += "\n";
					}
					conditions += "cl.numclient = " + etat.getjNumClient().getZoneTexte().getText();
					text += "Pour le client n° : " + etat.getjNumClient().getZoneTexte().getText() + " " + donnees.fiche("nomclient", "clients", "numclient = " + etat.getjNumClient().getZoneTexte().getText())[0];
				}
			}
			if(conditions.equals("")){
				conditions = "c.numcommande is not null";
			}
			if(etat.getBr1().isSelected()){
				conditions += " and c.travauxfini = 1";
				parameters.put("statue", "Terminées");
				nomFichier += " Terminées";
			}
			else if(etat.getBr2().isSelected()){
				conditions += " and c.travauxfini = 0";
				parameters.put("statue", "Non Terminées");
				nomFichier += " Non Terminées";
			}
	        parameters.put("Conditions", conditions);
	        parameters.put("textDevis", text);
	        if(erreur == false){
	        	if(impression.equals("Imprimer")){
	        		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
	        	}
	        	else if(impression.equals("PDF")){
	        		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
	        	}
	        	else if(impression.equals("Apercu")){
	        		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
	        	}
	        }

		}
		else if(etat.getJtp().getSelectedIndex() == 1){
			path1 = "lib/modeles/CahierdeCommandes.jasper";
			nomFichier = "Cahier de Commandes";
			if(!etat.getjNumCommandes3().getText().isEmpty() && !etat.getjNumCommandes3().getText().equals("(vide)")){
				if(!donnees.exist("commandes", "NumCommande", "NumCommande = " + etat.getjNumCommandes3().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de commande est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
						conditions = etat.getjNumCommandes3().getZoneTexte().getText();
						text = "A Partir de la commande n° : " + etat.getjNumCommandes3().getZoneTexte().getText() + " " +donnees.fiche("lblcommande", "commandes", "numcommande = " + etat.getjNumCommandes3().getZoneTexte().getText())[0];
				}
			}
			else {
				conditions = 0 + "";
				text = "";
			}
			parameters.put("Conditions", conditions);
		    parameters.put("textDevis", text);
		    if(erreur == false){
		       	if(impression.equals("Imprimer")){
		       		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
		       	}
		       	else if(impression.equals("PDF")){
		       		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
		       	}
		       	else if(impression.equals("Apercu")){
		       		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
		       	}
		    }
		}
		else if(etat.getJtp().getSelectedIndex() == 2){
			path1 = "lib/modeles/EtatCorem.jasper";
			nomFichier = "Etat Corem";
			if(!etat.getjAnnee().getText().isEmpty() && !etat.getjAnnee().getText().equals("")){
				int an = Integer.parseInt(etat.getjAnnee().getText());
				int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
				int mois = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				if(an >= 1990 && an <= anneeEnCours){
					parameters.put("Annee", etat.getjAnnee().getText());
					if(etat.getBc1().isSelected()){
						parameters.put("Corem", "1");
						text = "Liste des factures assujeti COREM \n";
					}
					else if (etat.getBc2().isSelected()){
						parameters.put("Corem", "0");
						text = "Liste des factures non assujeti COREM \n";
					}
					if(etat.getBs1().isSelected()){
						parameters.put("semestre", "Date_Format(f.DateEmission, '%m') <= 6");
						text += "Pour le premier semestre de l'année " + an;
					}
					else if(etat.getBs2().isSelected()){
						if(an == anneeEnCours){
							if(mois <= 6){
								thread2.getFrame().setVisible(false);
								erreur = true;
								JOptionPane.showMessageDialog(null, "2ème semestre non disponible pour l'année en cours", "ATTENTION",
									JOptionPane.WARNING_MESSAGE);
							}
							else{
								parameters.put("semestre", "Date_Format(f.DateEmission, '%m') >= 7");
								text += "Pour le deuxième semestre de l'année " + an;
							}
						}
						else {
							parameters.put("semestre", "Date_Format(f.DateEmission, '%m') >= 7");
							text += "Pour le deuxième semestre de l'année " + an;
						}
					}
				    parameters.put("Text", text);
				    if(erreur == false){				    	
				       	if(impression.equals("Imprimer")){
				       		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
				       	}
				       	else if(impression.equals("PDF")){
				       		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
				       	}
				       	else if(impression.equals("Apercu")){
				       		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
				       	}
				    }
				}
				else {
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Année incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				thread2.getFrame().setVisible(false);
				erreur = true;
				JOptionPane.showMessageDialog(null, "Il faut une année", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initEtatsFactures(){
		boolean erreur = false;
		EtatsFactures etat = ((EtatsFactures)objet);
		this.bdd = etat.getBdd();
		Donnees donnees = etat.getDonnees();
		String path1 = "", text = "";
		String [] mois = new String [] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
		Map parameters = new HashMap();
		String nomFichier = "";
		if(etat.getJtp().getSelectedIndex() == 0){
			path1 = "lib/modeles/ListingTVA.jasper";
			nomFichier = "Listing TVA";
			if(!etat.getjmois().getText().isEmpty()){
				if(!etat.getJannee().getText().isEmpty()){
					if(donnees.exist("factures", "valeur, AnneeValeur", "valeur = " + etat.getjmois().getText() + " and anneeValeur = " + etat.getJannee().getText())){
						parameters.put("valeur", etat.getjmois().getText());
						parameters.put("AnneeValeur", etat.getJannee().getText());
					}
					else{
						thread2.getFrame().setVisible(false);
						erreur = true;
						JOptionPane.showMessageDialog(null, "Aucune donnée", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "L'année est vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				thread2.getFrame().setVisible(false);
				erreur = true;
				JOptionPane.showMessageDialog(null, "La case mois est vide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(etat.getJtp().getSelectedIndex() == 1){
			nomFichier = "Journal des Ventes";
			if(etat.getBr1().isSelected()){
				path1 = "lib/modeles/JournaldesVentes.jasper";
			}
			else if(etat.getBr2().isSelected()){
				path1 = "lib/modeles/JournaldesVentesParClient.jasper";
			}
			if(etat.getjDate().getDate() != null){
				if(donnees.exist("factures", "numfacture", "date_format(DateEmission, '%m') = "+ new SimpleDateFormat("MM").format(etat.getjDate().getDate()) + " and date_format(DateEmission, '%Y') = " + new SimpleDateFormat("yyyy").format(etat.getjDate().getDate()))){
					parameters.put("Mois", Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())));
					parameters.put("Annee", Integer.parseInt(new SimpleDateFormat("yyyy").format(etat.getjDate().getDate())));
					text = "Pour " + mois[Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())) - 1] + " " + new SimpleDateFormat("yyyy").format(etat.getjDate().getDate());
					parameters.put("MoisText", mois[Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())) - 1]);
					if(Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())) - 1 == 0){
						parameters.put("MoisAvant", mois[Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())) - 1]);
					}
					else{
						parameters.put("MoisAvant", mois[Integer.parseInt(new SimpleDateFormat("MM").format(etat.getjDate().getDate())) - 2]);
					}
				}
				else{
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Aucune donnée", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				thread2.getFrame().setVisible(false);
				erreur = true;
				JOptionPane.showMessageDialog(null, "Date invalide", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
			if(!etat.getjNumClient().getZoneTexte().getText().isEmpty()){
				if(donnees.exist("clients", "numclient", "numclient = " + etat.getjNumClient().getZoneTexte().getText())){
					parameters.put("ConditionsClient", "c.numclient = " + etat.getjNumClient().getZoneTexte().getText());
					String [] res = donnees.fiche("nomclient", "clients", "numclient = " + etat.getjNumClient().getZoneTexte().getText());
					text += "\nClient n°" + etat.getjNumClient().getZoneTexte().getText() + " " + res[0];
					parameters.put("Text", text);
				}
				else{
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "N° Client invalide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				parameters.put("ConditionsClient", "c.numclient is not null");
				parameters.put("Text", text);
			}			
		}
	    if(erreur == false){    	
	       	if(impression.equals("Imprimer")){
	       		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
	       	}
	       	else if(impression.equals("PDF")){
	       		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
	       	}
	       	else if(impression.equals("Apercu")){
	       		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
	       	}
	    }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initEtatsClients(){
		boolean erreur = false;
		EtatsClients etat = ((EtatsClients)objet);
		this.bdd = etat.getBdd();
		Donnees donnees = etat.getDonnees();
		String path1 = "";
		Map parameters = new HashMap();
		String nomFichier = "";
		if(etat.getJtp().getSelectedIndex() == 0){
			path1 = "lib/modeles/ListeClient.jasper";
			nomFichier = "Liste Clients";
			if(etat.getBo1().isSelected()){
				parameters.put("Ordre", "NomClient");
			}
			else if(etat.getBo2().isSelected()){
				parameters.put("Ordre", "NumClient");
			}
		}
		else if(etat.getJtp().getSelectedIndex() == 1){
			path1 = "lib/modeles/RetardPaiement.jasper";
			nomFichier = "Retard Paiement";
		}
		else if(etat.getJtp().getSelectedIndex() == 2){
			path1 = "lib/modeles/Impaye.jasper";
			nomFichier = "Impayé";
		}
		else if(etat.getJtp().getSelectedIndex() == 3){
			nomFichier = "Factures à venir";
			if(!etat.getjNumClient().getZoneTexte().getText().isEmpty() && !etat.getjNumClient().getZoneTexte().getText().equals("(vide)")){
				if(!donnees.exist("clients", "NumClient", "NumClient = " + etat.getjNumClient().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le Numéro de client est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else{
					parameters.put("Conditions", "c.numclient = " + etat.getjNumClient().getZoneTexte().getText());
				}
			}
			else{
				parameters.put("Conditions", "c.numclient = " + etat.getjNumClient().getZoneTexte().getText());
			}
			if(etat.getBr1().isSelected()){
				path1 = "lib/modeles/FacturesavenirClients.jasper";
				
			}
			else if(etat.getBr2().isSelected()){
				path1 = "lib/modeles/FacturesavenirDate.jasper";
			}
		}
		
		 if(erreur == false){
		    	
		       	if(impression.equals("Imprimer")){
		       		traitementImprimer(path1, parameters, bdd.getCon(), nomFichier, 1);
		       	}
		       	else if(impression.equals("PDF")){
		       		 traitementPDF(path1, parameters, bdd.getCon(), nomFichier);
		       	}
		       	else if(impression.equals("Apercu")){
		       		traitementApercu(path1, parameters, bdd.getCon(), nomFichier, 1);
		       	}
		    }
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initEHSP(){
		boolean erreur = false;
		EtatsHeureSpePointage etat = ((EtatsHeureSpePointage)objet);
		this.bdd = etat.getBdd();
		Donnees donnees = etat.getDonnees();
		String path1 = "";
		Map parameters = new HashMap();
		String text = "", text2 = "", CO = "";
		if(etat.getJtp().getSelectedIndex() == 0){
			path1 = "lib/modeles/ListeHeureSpe.jasper";
			if(!donnees.exist("heurespe", "annee", "annee = " + etat.getjAnnee().getText())){
				thread2.getFrame().setVisible(false);
				erreur = true;
				JOptionPane.showMessageDialog(null, "L'année est incorrect", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
			}
			parameters.put("Annee", etat.getjAnnee().getText());
	        
	        if(erreur == false){
	        	if(impression.equals("Imprimer")){
	        		traitementImprimer(path1, parameters, bdd.getCon(), "Liste des Heures Speciales", 1);
	        	}
	        	else if(impression.equals("PDF")){
	        		 traitementPDF(path1, parameters, bdd.getCon(), "Liste des Heures Speciales");
	        	}
	        	else if(impression.equals("Apercu")){
	        		traitementApercu(path1, parameters, bdd.getCon(), "Liste des Heures Speciales", 1);
	        	}
	        }
		}
		else if(etat.getJtp().getSelectedIndex() == 1){
			if(etat.getBr1().isSelected()){
				path1 = "lib/modeles/ListePointagesSem.jasper";
			}
			else if(etat.getBr2().isSelected()){
				path1 = "lib/modeles/ListePointagesPersonnel.jasper";
			}
			else if(etat.getBr3().isSelected()){
				path1 = "lib/modeles/ListePointagesClients.jasper";
			}
			if(etat.getBp1().isSelected()){
				parameters.put("ConditionsPersonnel", "pe.numpersonnel >= 0");
				text += "Pour tous le personnel\n";
			}
			else if(etat.getBp2().isSelected()){
				if(!donnees.exist("personne", "numpersonnel", "numpersonnel = " + etat.getjNumPersonnel().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le numéro du personnel est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else {
					parameters.put("ConditionsPersonnel", "pe.numpersonnel = " + etat.getjNumPersonnel().getZoneTexte().getText());
					String [] res = donnees.fiche("nom", "personne", "numpersonnel = " + etat.getjNumPersonnel().getZoneTexte().getText());
					text += "Pour la personne : " + res[0] +"\n";
				}
			}
			else if(etat.getBp3().isSelected()){
				if(!donnees.exist("typepers", "CodeTypePersonne", "CodeTypePersonne = " + etat.getjNumCategorie().getZoneTexte().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "Le catégorie du personnel est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}
				else {
					parameters.put("ConditionsPersonnel", "pe.CodeTypePersonne = " + etat.getjNumCategorie().getZoneTexte().getText());
					String [] res = donnees.fiche("LibelleTypePersonne", "typepers", "CodeTypePersonne = " + etat.getjNumCategorie().getZoneTexte().getText());
					text += "Pour la catégorie: " + res[0] + "\n";
				}
			}
			if(etat.getBs1().isSelected()){
					parameters.put("ConditionsSem", "p.numsem is not null");
					text2 += "Pour toute les semaines";
			}
			else if(etat.getBs2().isSelected()){
				if(!etat.getIntervalle1().getText().isEmpty() && !etat.getIntervalle2().getText().isEmpty() && Integer.parseInt(etat.getIntervalle1().getText()) > 0 && Integer.parseInt(etat.getIntervalle1().getText()) < 53 && Integer.parseInt(etat.getIntervalle2().getText()) > 0 && Integer.parseInt(etat.getIntervalle2().getText()) < 53 && Integer.parseInt(etat.getIntervalle1().getText()) <= Integer.parseInt(etat.getIntervalle2().getText())){
					parameters.put("ConditionsSem", "p.numsem >= " + etat.getIntervalle1().getText() + " and p.numsem <= " + etat.getIntervalle2().getText());
					text2 += "Entre la semaine " + etat.getIntervalle1().getText() + " et la semaine " + etat.getIntervalle2().getText();
				}
				else {
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "L'intervalle est incorrect", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				}		
			}
			String temp = "";
			if (etat.getCb2().isSelected()){
				parameters.put("Commandes", true);
				if(!etat.getjNumCommande().getText().isEmpty()){
					if(donnees.exist("devis", "numcommande", "numcommande = " + etat.getjNumCommande().getZoneTexte().getText())){
						CO = "d.numcommande = " + etat.getjNumCommande().getText();
						String [] res = donnees.fiche("lblcommande", "commandes", "numcommande = " + etat.getjNumCommande().getZoneTexte().getText());
						temp = "Ne concerne que la commande n° " + etat.getjNumCommande().getZoneTexte().getText() + " " + res[0] +"\n";
					}
					else{
						thread2.getFrame().setVisible(false);
						erreur = true;
						JOptionPane.showMessageDialog(null, " Le numéro de commmande est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);	
					}
				}
				else{
					temp = "Ne concerne que les commandes\n"; 
				}
			}
			else {
				parameters.put("Commandes", false);
			}
			if(etat.getCb1().isSelected()){
				parameters.put("Devis", true);
				if(!etat.getjNumDevis().getText().isEmpty()){
					if(donnees.exist("devis", "numdevis", "numdevis = " + etat.getjNumDevis().getZoneTexte().getText())){
						if(etat.getCb2().isSelected()){
							if(!etat.getjNumCommande().getText().isEmpty()){
								if(donnees.exist("devis", "numdevis, numcommande", "numdevis = " + etat.getjNumDevis().getZoneTexte().getText() + " and numcommande = " + etat.getjNumCommande().getZoneTexte().getText())){
									CO = "d.numdevis = " + etat.getjNumDevis().getText();
									String [] res = donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis().getZoneTexte().getText());
									temp = "Ne concerne que le devis n° " + etat.getjNumDevis().getZoneTexte().getText() + " " + res[0] +"\n";
								}
								else{
									thread2.getFrame().setVisible(false);
									erreur = true;
									JOptionPane.showMessageDialog(null, " Le numéro de Devis n'est pas associer à ce numéro de commande", "ATTENTION",
											JOptionPane.WARNING_MESSAGE);	
								}
							}
							else{
								CO = "d.numdevis = " + etat.getjNumDevis().getText(); 
								String [] res = donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis().getZoneTexte().getText());
								temp = "Ne concerne que le devis n° " + etat.getjNumDevis().getZoneTexte().getText() + " " + res[0] +"\n";
							}
						}
						else if(!etat.getCb2().isSelected()){
							CO = "d.numdevis = " + etat.getjNumDevis().getText() + " and d.numcommande is null"; 
							String [] res = donnees.fiche("lbldevis", "devis", "numdevis = " + etat.getjNumDevis().getZoneTexte().getText());
							temp = "Ne concerne que le devis n° " + etat.getjNumDevis().getZoneTexte().getText() + " " + res[0] +"\n";
						}
					}
					else{
						thread2.getFrame().setVisible(false);
						erreur = true;
						JOptionPane.showMessageDialog(null, " Le numéro de Devis est incorrect", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);	
					}
				}
				else if(etat.getjNumDevis().getText().isEmpty() && etat.getjNumCommande().getText().isEmpty()){
					temp = "Concerne les devis et les commandes\n"; 
				}
					
			}
			else{
				parameters.put("Devis", false);
			}
			if(!etat.getCb1().isSelected() && !etat.getCb2().isSelected()){
				thread2.getFrame().setVisible(false);
				erreur = true;
				JOptionPane.showMessageDialog(null, " Il faut que devis et/ou commandes soit coché !", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);	
			}

			if(CO.equals("")){
				if((etat.getCb1().isSelected() && etat.getCb2().isSelected()) || (!etat.getCb1().isSelected() && etat.getCb2().isSelected())){
					CO = "d.numdevis is not null";
				}
				else if (etat.getCb1().isSelected() && !etat.getCb2().isSelected()){
					CO = "d.numcommande is null";
				}
			}
			
			parameters.put("ConditionsOrder", CO);
			text += temp;
			if(!etat.getjAnnee2().getText().isEmpty()){
				if(!donnees.exist("pointage", "annee", "annee = " + etat.getjAnnee2().getText())){
					thread2.getFrame().setVisible(false);
					erreur = true;
					JOptionPane.showMessageDialog(null, "L'année est incorrect", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					parameters.put("Annee", false);
					parameters.put("ConditionsAnnee", "p.annee = " +etat.getjAnnee2().getText());
					text2 += " pour l'année " + etat.getjAnnee2().getText();
					text += text2;
					parameters.put("text", text);
				}
			}
			else{
				parameters.put("Annee", true);
				parameters.put("ConditionsAnnee", "p.annee = is not null");
				text2 += " de toutes les années";
				text += text2;
				parameters.put("text", text);
			}
	        if(erreur == false){
	        	if(impression.equals("Imprimer")){
	        		traitementImprimer(path1, parameters, bdd.getCon(), "Liste des Pointages", 1);
	        	}
	        	else if(impression.equals("PDF")){
	        		 traitementPDF(path1, parameters, bdd.getCon(), "Liste des Pointages");
	        	}
	        	else if(impression.equals("Apercu")){
	        		traitementApercu(path1, parameters, bdd.getCon(), "Liste des Pointages", 1);
	        	}
	        }
	        
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void traitementImprimer(String fichier, Map parameters, Connection bdd, String namePrint, int nbcopie){	
		// - Chargement et compilation du rapport
		//JOptionPane.showMessageDialog(null, "Impression en cours !");
         org.apache.log4j.BasicConfigurator.configure();
         // JasperDesign jasperDesign;
		// - Execution du rapport
		File reportFile = new File(fichier);
		try{
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		    @SuppressWarnings("unchecked")
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd);
		    if(thread2 != null && thread == null){
		    	thread2.getFrame().setVisible(false);
		    }
		    else if(thread2 == null && thread != null){
		    	thread.getFrame().setVisible(false);
		    }
		    JRViewer view = new JRViewer(jasperPrint);
		    if(!jasperPrint.getPages().isEmpty()){
		    	view.printManager(namePrint, nbcopie);
		    }
		}
		catch(JRException e){
		    if(thread2 != null && thread == null){
		    	thread2.getFrame().setVisible(false);
		    }
		    else if(thread2 == null && thread != null){
		    	thread.getFrame().setVisible(false);
		    }
		    JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(), "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
		//jasperDesign = JRXmlLoader.load(fichier);
		//JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 

    }
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
    private void traitementApercu(String fichier, Map parameters, Connection bdd, String namePrint, int nbcopie) {
				// - Chargement et compilation du rapport
				org.apache.log4j.BasicConfigurator.configure();
				try {
					File reportFile = new File(fichier);
					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());

					// - Paramètres à envoyer au rapport
					// - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd);
            if (thread2 != null && thread == null) {
				thread2.getFrame().setVisible(false);
			} else if (thread2 == null && thread != null) {
				thread.getFrame().setVisible(false);
			}
			if (!jasperPrint.getPages().isEmpty()) {
	            JasperViewer jReportsViewer = new JasperViewer(jasperPrint, namePrint, nbcopie);
	            jReportsViewer.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Aucune Pages", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);	
			}
			
		} catch (JRException e) {
			if (thread2 != null && thread == null) {
				thread2.getFrame().setVisible(false);
			} else if (thread2 == null && thread != null) {
				thread.getFrame().setVisible(false);
			}
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(), "ATTENTION",
					JOptionPane.WARNING_MESSAGE);

		}

    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void traitementPDF(String fichier, Map parameters, Connection bdd, String namePrint) {

		try {

			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
			chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents")); 
			chooser.setSelectedFile(new File(namePrint+".pdf"));
			//chooser.setCurrentDirectory(new File("." + File.separator));
			chooser.updateUI();
			int reponse = chooser.showDialog(chooser, "Enregistrer sous");
			if (reponse == JFileChooser.APPROVE_OPTION) {
				String file = chooser.getSelectedFile().toString();
				String extension = file.substring(file.length() - 4, file.length());
				if (!extension.equals(".pdf")) {
					file += "." + "pdf";
				}
				if (thread2 != null && thread == null) {
					thread2.getFrame().setVisible(true);
					thread2.getFrame().setAlwaysOnTop(true);
				} else if (thread2 == null && thread != null) {
					thread.getFrame().setVisible(true);
					thread.getFrame().setAlwaysOnTop(true);
				}
				// - Chargement et compilation du rapport
				org.apache.log4j.BasicConfigurator.configure();
				try {
					File reportFile = new File(fichier);
					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());

					// - Paramètres à envoyer au rapport
					// - Execution du rapport
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd);
					PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

					MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName();
					printRequestAttributeSet.add(mediaSizeName);
					printRequestAttributeSet.add(new Copies(1));
					if (thread2 != null && thread == null) {
						thread2.getFrame().setVisible(false);
					} else if (thread2 == null && thread != null) {
						thread.getFrame().setVisible(false);
					}
					if (!jasperPrint.getPages().isEmpty()) {
						JasperExportManager.exportReportToPdfFile(jasperPrint, file);
						JOptionPane.showMessageDialog(null, "PDF généré !");
					}
				} catch (JRException e) {
					if (thread2 != null && thread == null) {
						thread2.getFrame().setVisible(false);
					} else if (thread2 == null && thread != null) {
						thread.getFrame().setVisible(false);
					}
					JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(), "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (HeadlessException he) {
			he.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void traitementMail(String fichier, Map parameters, Connection connect, String namePrint, String numfacture) throws IOException {
				String file = namePrint.replace("\\.", "") + ".pdf";
				// - Chargement et compilation du rapport
				org.apache.log4j.BasicConfigurator.configure();
				try {
					File reportFile = new File(fichier);
					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());

					// - Paramètres à envoyer au rapport
					// - Execution du rapport
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connect);
					PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

					MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName();
					printRequestAttributeSet.add(mediaSizeName);
					printRequestAttributeSet.add(new Copies(1));
					if (!jasperPrint.getPages().isEmpty()) {
						JasperExportManager.exportReportToPdfFile(jasperPrint, file);
						InterfaceMail im = new InterfaceMail(numfacture, file, bdd, thread);
						thread.setInterfaceMail(im);
						im.init();
					}
					else{
						JOptionPane.showMessageDialog(null,  "Erreur : Aucune page", "Erreur", JOptionPane.ERROR);
					}
				} catch (JRException e) {
					thread.getFrame().setVisible(false);
					JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(), "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
		}

	

    public static boolean isValid(String strdate, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            @SuppressWarnings("unused")
			Date date = df.parse(strdate);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}