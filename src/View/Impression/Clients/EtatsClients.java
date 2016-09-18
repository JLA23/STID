package View.Impression.Clients;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import BDD.Base;
import Controller.ActionSearch;
import Controller.EcouteAction;
import Controller.FocusJText;
import Controller.KeyAutocomplete;
import View.Impression.InterfaceListing;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class EtatsClients extends InterfaceListing {

	private static final long serialVersionUID = 1L;
	
	protected Object[][] listCommandes, listClients;
	protected AutoComplete jNumClient;
	protected JLabel labelClient;
	protected JPanel listeClients, retard, avenir, impaye;
	protected JDateChooser jDate;
	protected JRadioButton br1, br2, bo1, bo2;

	public EtatsClients(Base base, JFrame frame, int visible) {
		super(base, frame);
		this.setTitle("Etats Commandes");
		listeClients = new JPanel();
		listeClients.setLayout(null);
		listeClients.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 40, jtp.getPreferredSize().height - 40));
		retard = new JPanel();
		retard.setLayout(null);
		retard.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		avenir = new JPanel();
		avenir.setLayout(null);
		avenir.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		impaye = new JPanel();
		impaye.setLayout(null);
		impaye.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		jtp.add("Liste Clients", listeClients);
		jtp.add("Retards de Paiement", retard);
		jtp.add("Impayé", impaye);
		jtp.add("Factures à venir", avenir);
		initlisteClients();
		initretard();
		initimpaye();
		initAvenir();
		jtp.setSelectedIndex(visible);
		imprimer.addActionListener(new ActionImpression(this, "EtatsClients") );
		afficher();
	}
	
	private void initlisteClients(){
		JLabel listingtemps = new JLabel("Liste des Clients :");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		listeClients.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des clients de la société.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		listeClients.add(informations);
		JLabel parametrage = new JLabel("Format de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		listeClients.add(parametrage);
		
		JLabel edition = new JLabel("Numéro de client, nom du client");
		edition.setBounds(40, 120, edition.getPreferredSize().width, edition.getPreferredSize().height);
		listeClients.add(edition);
		
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Ordre"));
		rupture.setBounds(40, 150, 180, 80);
		rupture.setLayout(null);
		
		listeClients.add(rupture);
		
		ButtonGroup bg = new ButtonGroup();
		bo1 = new JRadioButton("Par Nom", true);
		bo2 = new JRadioButton("Par Numéro", false);
		bo1.setBounds(8, 20, bo1.getPreferredSize().width, bo1.getPreferredSize().height);
		bo2.setBounds(8, 45, bo2.getPreferredSize().width, bo2.getPreferredSize().height);
		
		bg.add(bo1);
		bg.add(bo2);
		rupture.add(bo1);
		rupture.add(bo2);
	}
	
	public JRadioButton getBo1() {
		return bo1;
	}

	public void setBo1(JRadioButton bo1) {
		this.bo1 = bo1;
	}

	public JRadioButton getBo2() {
		return bo2;
	}

	public void setBo2(JRadioButton bo2) {
		this.bo2 = bo2;
	}

	private void initretard(){
		JLabel listingtemps = new JLabel("Etat des retard des paiements :");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		retard.add(listingtemps);
		JLabel informations = new JLabel(
				"Listing des impayés des clients actifs, classé par date avec rupture par client.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		retard.add(informations);
	}
	
	private void initimpaye(){
		JLabel listingtemps = new JLabel("Etat des impayés :");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		impaye.add(listingtemps);
		JLabel informations = new JLabel(
				"Listing des impayés des clients inactifs, classé par date avec rupture par client.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		impaye.add(informations);
	}
	
	private void initAvenir(){
		JLabel listingtemps = new JLabel("Etats des factures à venir");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		avenir.add(listingtemps);
		JLabel informations = new JLabel(
				"Listing des factures non payées.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		avenir.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		avenir.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restrictions"));
		restriction.setBounds(15, 120, 300, 110);
		restriction.setLayout(null);
		
		avenir.add(restriction);
		JLabel client = new JLabel("Pour le client n°");
		AutoCompleteModel modelDevis2 = new AutoCompleteModel();
		modelDevis2.addAll(listClients());
		jNumClient = new AutoComplete(modelDevis2);
		jNumClient.getZoneTexte().addKeyListener(new EcouteAction(jNumClient.getZoneTexte(), false));
		client.setBounds(120 - client.getPreferredSize().width, 30, client.getPreferredSize().width,
				client.getPreferredSize().height);
		restriction.add(client);
		jNumClient.setPreferredSize(new Dimension(80, 25));
		jNumClient.setBounds(125, 28, jNumClient.getPreferredSize().width, jNumClient.getPreferredSize().height);
		jNumClient.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsClients"));
		jNumClient.getZoneTexte().addKeyListener(new KeyAutocomplete(this, "EtatsClients"));
		restriction.add(jNumClient);
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JButton list = new JButton(icon2);
		list.setPreferredSize(new Dimension(19, 20));
		list.setBounds(130 + jNumClient.getPreferredSize().width, 30, list.getPreferredSize().width,
				list.getPreferredSize().height);
		list.addActionListener(new ActionSearch(this, "EtatsClients", "NumClient", jNumClient));
		restriction.add(list);
		labelClient = new JLabel();
		labelClient.setBounds(100, 70, labelClient.getPreferredSize().width, labelClient.getPreferredSize().height);
		labelClient.setForeground(Color.BLUE);
		restriction.add(labelClient);
		
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Rupture"));
		rupture.setBounds(330, 120, 180, 110);
		rupture.setLayout(null);
		
		avenir.add(rupture);
		
		ButtonGroup bg = new ButtonGroup();
		br1 = new JRadioButton("Par Client", true);
		br2 = new JRadioButton("Par date d'échéance", false);
		br1.setBounds(8, 25, br1.getPreferredSize().width, br1.getPreferredSize().height);
		br2.setBounds(8, 65, br2.getPreferredSize().width, br2.getPreferredSize().height);
		
		bg.add(br1);
		bg.add(br2);
		rupture.add(br1);
		rupture.add(br2);
		
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

	public JPanel getlisteClients() {
		return listeClients;
	}

	public void setlisteClients(JPanel listeClients) {
		this.listeClients = listeClients;
	}

	public JPanel getretard() {
		return retard;
	}

	public void setretard(JPanel retard) {
		this.retard = retard;
	}

}
