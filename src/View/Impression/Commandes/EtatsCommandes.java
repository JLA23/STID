package View.Impression.Commandes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import BDD.Base;
import Controller.ActionSearch;
import Controller.EcouteAction;
import Controller.FocusJText;
import View.Impression.InterfaceListing;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class EtatsCommandes extends InterfaceListing {

	private static final long serialVersionUID = 1L;
	
	protected Object[][] listCommandes, listClients;
	protected AutoComplete jNumCommandes1, jNumCommandes2, jNumCommandes3, jNumClient;
	protected JLabel labelClient;
	protected JPanel tempsCommandes, listeCommandes, etatCorem;
	protected JDateChooser jDate;
	protected JRadioButton br1, br2, bc1, bc2, bs1, bs2;
	protected JFormattedTextField jAnnee;

	public EtatsCommandes(Base base, JFrame frame, int visible) {
		super(base, frame);
		this.setTitle("Etats Commandes");
		tempsCommandes = new JPanel();
		tempsCommandes.setLayout(null);
		tempsCommandes.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 40, jtp.getPreferredSize().height - 40));
		listeCommandes = new JPanel();
		listeCommandes.setLayout(null);
		listeCommandes.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		etatCorem = new JPanel();
		etatCorem.setLayout(null);
		etatCorem.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		jtp.add("Temps / Commandes", tempsCommandes);
		jtp.add("Cahier de Commandes", listeCommandes);
		jtp.add("Etat Corem", etatCorem);
		initTempsCommandes();
		initlisteCommandes();
		initCorem();
		jtp.setSelectedIndex(visible);
		imprimer.addActionListener(new ActionImpression(this, "EtatsCommandes") );
		afficher();
	}
	
	private void initTempsCommandes(){
		JLabel listingtemps = new JLabel("Listing des temps par commandes");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		tempsCommandes.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des affaires en cours de réalisations ou terminées avec les temps passés.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		tempsCommandes.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		tempsCommandes.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restrictions"));
		restriction.setBounds(15, 120, 400, 110);
		restriction.setLayout(null);
		
		JLabel devis = new JLabel("De la commande n°");
		AutoCompleteModel modelDevis1 = new AutoCompleteModel();
		modelDevis1.addAll(listCommandes());
		jNumCommandes1 = new AutoComplete(modelDevis1);
		jNumCommandes1.getZoneTexte().addKeyListener(new EcouteAction(jNumCommandes1.getZoneTexte(), false));
		devis.setBounds(120 - devis.getPreferredSize().width, 30, devis.getPreferredSize().width,
				devis.getPreferredSize().height);
		restriction.add(devis);
		jNumCommandes1.setPreferredSize(new Dimension(80, 25));
		jNumCommandes1.setBounds(125, 28, jNumCommandes1.getPreferredSize().width, jNumCommandes1.getPreferredSize().height);
		jNumCommandes1.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsCommandes"));
		restriction.add(jNumCommandes1);
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JButton list1 = new JButton(icon2);
		list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(130 + jNumCommandes1.getPreferredSize().width, 30, list1.getPreferredSize().width,
				list1.getPreferredSize().height);
		list1.addActionListener(new ActionSearch(this, "EtatsCommandes", "NumCommandes", jNumCommandes1));
		restriction.add(list1);
		JLabel a = new JLabel("à");
		jNumCommandes2 = new AutoComplete(modelDevis1);
		jNumCommandes2.getZoneTexte().addKeyListener(new EcouteAction(jNumCommandes2.getZoneTexte(), false));
		a.setBounds(135 + list1.getPreferredSize().width + jNumCommandes1.getPreferredSize().width , 30, a.getPreferredSize().width,
				a.getPreferredSize().height);
		restriction.add(a);
		jNumCommandes2.setPreferredSize(new Dimension(80, 25));
		jNumCommandes2.setBounds(140 + a.getPreferredSize().width + list1.getPreferredSize().width + jNumCommandes1.getPreferredSize().width, 28, jNumCommandes2.getPreferredSize().width, jNumCommandes2.getPreferredSize().height);
		jNumCommandes2.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsCommandes"));
		restriction.add(jNumCommandes2);
		JButton list2 = new JButton(icon2);
		list2.setPreferredSize(new Dimension(19, 20));
		list2.setBounds(145+ jNumCommandes2.getPreferredSize().width + a.getPreferredSize().width + list1.getPreferredSize().width + jNumCommandes1.getPreferredSize().width, 30, list2.getPreferredSize().width,
				list2.getPreferredSize().height);
		list2.addActionListener(new ActionSearch(this, "EtatsCommandes", "NumCommandes", jNumCommandes2));
		restriction.add(list2);
		tempsCommandes.add(restriction);
		JLabel client = new JLabel("Pour le client n°");
		AutoCompleteModel modelDevis2 = new AutoCompleteModel();
		modelDevis2.addAll(listClients());
		jNumClient = new AutoComplete(modelDevis2);
		jNumClient.getZoneTexte().addKeyListener(new EcouteAction(jNumClient.getZoneTexte(), false));
		client.setBounds(120 - client.getPreferredSize().width, 60, client.getPreferredSize().width,
				client.getPreferredSize().height);
		restriction.add(client);
		jNumClient.setPreferredSize(new Dimension(80, 25));
		jNumClient.setBounds(125, 58, jNumClient.getPreferredSize().width, jNumClient.getPreferredSize().height);
		jNumClient.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsCommandes"));
		restriction.add(jNumClient);
		JButton list3 = new JButton(icon2);
		list3.setPreferredSize(new Dimension(19, 20));
		list3.setBounds(130 + jNumClient.getPreferredSize().width, 60, list3.getPreferredSize().width,
				list3.getPreferredSize().height);
		list3.addActionListener(new ActionSearch(this, "EtatsCommandes", "NumClient", jNumClient));
		restriction.add(list3);
		labelClient = new JLabel();
		labelClient.setBounds(135 + jNumClient.getPreferredSize().width + list3.getPreferredSize().width, 60, 0, 0);
		labelClient.setForeground(Color.BLUE);
		restriction.add(labelClient);
		
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Voir les commandes"));
		rupture.setBounds(430, 120, 150, 110);
		rupture.setLayout(null);
		
		tempsCommandes.add(rupture);
		
		ButtonGroup bg = new ButtonGroup();
		br1 = new JRadioButton("Terminées", true);
		br2 = new JRadioButton("En-cours", false);
		br1.setBounds(8, 25, br1.getPreferredSize().width, br1.getPreferredSize().height);
		br2.setBounds(8, 65, br2.getPreferredSize().width, br2.getPreferredSize().height);
		
		bg.add(br1);
		bg.add(br2);
		rupture.add(br1);
		rupture.add(br2);
		
	}
	
	private void initlisteCommandes(){
		JLabel listingtemps = new JLabel("Editions du cahier de commandes ");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		listeCommandes.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des affaires avec n° de facture et montant.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		listeCommandes.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		listeCommandes.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restrictions"));
		restriction.setBounds(15, 120, 320, 110);
		restriction.setLayout(null);
		listeCommandes.add(restriction);
		
		JLabel devis = new JLabel("N° de commande supérieur à ");
		AutoCompleteModel modelDevis1 = new AutoCompleteModel();
		modelDevis1.addAll(listCommandes());
		jNumCommandes3 = new AutoComplete(modelDevis1);
		jNumCommandes3.getZoneTexte().addKeyListener(new EcouteAction(jNumCommandes3.getZoneTexte(), false));
		devis.setBounds(185 - devis.getPreferredSize().width, 50, devis.getPreferredSize().width,
				devis.getPreferredSize().height);
		restriction.add(devis);
		jNumCommandes3.setPreferredSize(new Dimension(80, 25));
		jNumCommandes3.setBounds(190, 48, jNumCommandes3.getPreferredSize().width, jNumCommandes3.getPreferredSize().height);
		jNumCommandes3.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsCommandes"));
		restriction.add(jNumCommandes3);
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JButton list1 = new JButton(icon2);
		list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(195 + jNumCommandes3.getPreferredSize().width, 50, list1.getPreferredSize().width,
				list1.getPreferredSize().height);
		list1.addActionListener(new ActionSearch(this, "EtatsCommandes", "NumCommandes", jNumCommandes3));
		restriction.add(list1);
	}
	
	private void initCorem(){
		JLabel listingtemps = new JLabel("Editions des commandes COREM");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		etatCorem.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des affaires COREM.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		etatCorem.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		etatCorem.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restrictions"));
		restriction.setBounds(15, 120, 160, 110);
		restriction.setLayout(null);
		etatCorem.add(restriction);
		
		JLabel annee = new JLabel("Année ");
		
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		jAnnee = new JFormattedTextField(num);
		jAnnee.addKeyListener(new EcouteAction(jAnnee, false));
		annee.setBounds(60 - annee.getPreferredSize().width, 50, annee.getPreferredSize().width,
				annee.getPreferredSize().height);
		restriction.add(annee);
		int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
		jAnnee.setBounds(65, 48, 60, 20);
		jAnnee.setText(anneeEnCours + "");
		restriction.add(jAnnee);
		
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Commandes"));
		rupture.setBounds(185, 120, 155, 110);
		rupture.setLayout(null);
		
		etatCorem.add(rupture);
		
		ButtonGroup bg = new ButtonGroup();
		bc1 = new JRadioButton("Assujeti COREM", true);
		bc2 = new JRadioButton("Non assujeti COREM", false);
		bc1.setBounds(8, 25, bc1.getPreferredSize().width, bc1.getPreferredSize().height);
		bc2.setBounds(8, 65, bc2.getPreferredSize().width, bc2.getPreferredSize().height);
		
		bg.add(bc1);
		bg.add(bc2);
		rupture.add(bc1);
		rupture.add(bc2);
		
		JPanel rupture2 = new JPanel();
		rupture2.setBorder(BorderFactory.createTitledBorder("Semestre"));
		rupture2.setBounds(350, 120, 150, 110);
		rupture2.setLayout(null);
		
		etatCorem.add(rupture2);
		
		ButtonGroup bg2 = new ButtonGroup();
		bs1 = new JRadioButton("1er Semestre", true);
		bs2 = new JRadioButton("2ème Semestre", false);
		bs1.setBounds(8, 25, bs1.getPreferredSize().width, bs1.getPreferredSize().height);
		bs2.setBounds(8, 65, bs2.getPreferredSize().width, bs2.getPreferredSize().height);
		
		bg2.add(bs1);
		bg2.add(bs2);
		rupture2.add(bs1);
		rupture2.add(bs2);
		
	}
	
	protected ArrayList<String> listCommandes() {
		listCommandes = donnees.liste("NumCommande, LblCommande", "commandes", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listCommandes.length; i++) {
			res.add(listCommandes[i][0].toString());
		}
		return res;
	}
	
	protected ArrayList<String> listClients() {
		listClients = donnees.liste("NumClient, NomClient", "clients", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listClients.length; i++) {
			res.add(listClients[i][0].toString());
		}
		return res;
	}

	public AutoComplete getjNumCommandes1() {
		return jNumCommandes1;
	}

	public void setjNumCommandes1(AutoComplete jNumCommandes1) {
		this.jNumCommandes1 = jNumCommandes1;
	}

	public AutoComplete getjNumCommandes2() {
		return jNumCommandes2;
	}

	public void setjNumCommandes2(AutoComplete jNumCommandes2) {
		this.jNumCommandes2 = jNumCommandes2;
	}

	public Object[][] getListCommandes() {
		return listCommandes;
	}

	public void setListCommandes(Object[][] listDevis) {
		this.listCommandes = listDevis;
	}

	public Object[][] getListClients() {
		return listClients;
	}

	public void setListClients(Object[][] listClients) {
		this.listClients = listClients;
	}

	public AutoComplete getjNumClient() {
		return jNumClient;
	}

	public void setjNumClient(AutoComplete jNumClient) {
		this.jNumClient = jNumClient;
	}

	public JLabel getLabelClient() {
		return labelClient;
	}

	public void setLabelClient(JLabel labelClient) {
		this.labelClient = labelClient;
	}

	public AutoComplete getjNumCommandes3() {
		return jNumCommandes3;
	}

	public void setjNumCommandes3(AutoComplete jNumCommandes3) {
		this.jNumCommandes3 = jNumCommandes3;
	}

	public JDateChooser getjDate() {
		return jDate;
	}

	public void setjDate(JDateChooser jDate) {
		this.jDate = jDate;
	}

	public JRadioButton getBr1() {
		return br1;
	}

	public void setBr1(JRadioButton br1) {
		this.br1 = br1;
	}

	public JRadioButton getBr2() {
		return br2;
	}

	public void setBr2(JRadioButton br2) {
		this.br2 = br2;
	}

	public JPanel getTempsCommandes() {
		return tempsCommandes;
	}

	public void setTempsCommandes(JPanel tempsCommandes) {
		this.tempsCommandes = tempsCommandes;
	}

	public JPanel getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(JPanel listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public JRadioButton getBc1() {
		return bc1;
	}

	public void setBc1(JRadioButton bc1) {
		this.bc1 = bc1;
	}

	public JRadioButton getBc2() {
		return bc2;
	}

	public void setBc2(JRadioButton bc2) {
		this.bc2 = bc2;
	}

	public JRadioButton getBs1() {
		return bs1;
	}

	public void setBs1(JRadioButton bs1) {
		this.bs1 = bs1;
	}

	public JRadioButton getBs2() {
		return bs2;
	}

	public void setBs2(JRadioButton bs2) {
		this.bs2 = bs2;
	}

	public JFormattedTextField getjAnnee() {
		return jAnnee;
	}

	public void setjAnnee(JFormattedTextField jAnnee) {
		this.jAnnee = jAnnee;
	}
	

}
