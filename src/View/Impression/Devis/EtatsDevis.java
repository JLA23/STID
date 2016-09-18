package View.Impression.Devis;

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
import View.Impression.InterfaceListing;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class EtatsDevis extends InterfaceListing {

	private static final long serialVersionUID = 1L;
	
	protected Object[][] listDevis, listClients;
	protected AutoComplete jNumDevis1, jNumDevis2, jNumDevis3, jNumClient, jNumClient2;
	protected JLabel labelClient, labelClient2;
	protected JPanel tempsDevis, listeDevis;
	protected JDateChooser jDate;
	protected JRadioButton br1, br2, br3;

	public EtatsDevis(Base base, JFrame frame, int visible) {
		super(base, frame);
		this.setTitle("Etats Devis");
		tempsDevis = new JPanel();
		tempsDevis.setLayout(null);
		tempsDevis.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 40, jtp.getPreferredSize().height - 40));
		listeDevis = new JPanel();
		listeDevis.setLayout(null);
		listeDevis.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		jtp.add("Temps / Devis", tempsDevis);
		jtp.add("Liste des Devis", listeDevis);
		initTempsDevis();
		initListeDevis();
		jtp.setSelectedIndex(visible);
		imprimer.addActionListener(new ActionImpression(this, "EtatsDevis") );
		afficher();
	}
	
	private void initTempsDevis(){
		JLabel listingtemps = new JLabel("Listing des temps par devis");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		tempsDevis.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des devis en cours de réalisations avec les temps passés.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		tempsDevis.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		tempsDevis.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restriction"));
		restriction.setBounds(30, 120, 500, 110);
		restriction.setLayout(null);
		
		JLabel devis = new JLabel("Du devis n°");
		AutoCompleteModel modelDevis1 = new AutoCompleteModel();
		modelDevis1.addAll(listDevis());
		jNumDevis1 = new AutoComplete(modelDevis1);
		jNumDevis1.getZoneTexte().addKeyListener(new EcouteAction(jNumDevis1.getZoneTexte(), false));
		devis.setBounds(150 - devis.getPreferredSize().width, 30, devis.getPreferredSize().width,
				devis.getPreferredSize().height);
		restriction.add(devis);
		jNumDevis1.setPreferredSize(new Dimension(80, 25));
		jNumDevis1.setBounds(160, 28, jNumDevis1.getPreferredSize().width, jNumDevis1.getPreferredSize().height);
		jNumDevis1.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsDevis"));
		restriction.add(jNumDevis1);
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JButton list1 = new JButton(icon2);
		list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(170 + jNumDevis1.getPreferredSize().width, 30, list1.getPreferredSize().width,
				list1.getPreferredSize().height);
		list1.addActionListener(new ActionSearch(this, "EtatsDevis", "NumDevis", jNumDevis1));
		restriction.add(list1);
		JLabel a = new JLabel("à");
		jNumDevis2 = new AutoComplete(modelDevis1);
		jNumDevis2.getZoneTexte().addKeyListener(new EcouteAction(jNumDevis2.getZoneTexte(), false));
		a.setBounds(180 + list1.getPreferredSize().width + jNumDevis1.getPreferredSize().width , 30, a.getPreferredSize().width,
				a.getPreferredSize().height);
		restriction.add(a);
		jNumDevis2.setPreferredSize(new Dimension(80, 25));
		jNumDevis2.setBounds(190 + a.getPreferredSize().width + list1.getPreferredSize().width + jNumDevis1.getPreferredSize().width, 28, jNumDevis2.getPreferredSize().width, jNumDevis2.getPreferredSize().height);
		jNumDevis2.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsDevis"));
		restriction.add(jNumDevis2);
		JButton list2 = new JButton(icon2);
		list2.setPreferredSize(new Dimension(19, 20));
		list2.setBounds(200 + jNumDevis2.getPreferredSize().width + a.getPreferredSize().width + list1.getPreferredSize().width + jNumDevis1.getPreferredSize().width, 30, list2.getPreferredSize().width,
				list2.getPreferredSize().height);
		list2.addActionListener(new ActionSearch(this, "EtatsDevis", "NumDevis", jNumDevis2));
		restriction.add(list2);
		tempsDevis.add(restriction);
		JLabel client = new JLabel("Pour le client n°");
		AutoCompleteModel modelDevis2 = new AutoCompleteModel();
		modelDevis2.addAll(listClients());
		jNumClient = new AutoComplete(modelDevis2);
		jNumClient.getZoneTexte().addKeyListener(new EcouteAction(jNumClient.getZoneTexte(), false));
		client.setBounds(150 - client.getPreferredSize().width, 60, client.getPreferredSize().width,
				client.getPreferredSize().height);
		restriction.add(client);
		jNumClient.setPreferredSize(new Dimension(80, 25));
		jNumClient.setBounds(160, 58, jNumClient.getPreferredSize().width, jNumClient.getPreferredSize().height);
		jNumClient.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsDevis"));
		restriction.add(jNumClient);
		JButton list3 = new JButton(icon2);
		list3.setPreferredSize(new Dimension(19, 20));
		list3.setBounds(170 + jNumClient.getPreferredSize().width, 58, list3.getPreferredSize().width,
				list3.getPreferredSize().height);
		list3.addActionListener(new ActionSearch(this, "EtatsDevis", "NumClient", jNumClient));
		restriction.add(list3);
		labelClient = new JLabel();
		labelClient.setBounds(180 + jNumClient.getPreferredSize().width + list3.getPreferredSize().width, 60, 0, 0);
		labelClient.setForeground(Color.BLUE);
		restriction.add(labelClient);
		
	}
	
	private void initListeDevis(){
		JLabel labelListeDevis = new JLabel("Liste des devis");
		labelListeDevis.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		labelListeDevis.setFont(font);
		labelListeDevis.setBounds(30, 30, labelListeDevis.getPreferredSize().width, labelListeDevis.getPreferredSize().height);
		listeDevis.add(labelListeDevis);
		JLabel informations = new JLabel(
				"Edition de la liste des devis");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		listeDevis.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		listeDevis.add(parametrage);
		
		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restriction"));
		restriction.setBounds(30, 120, 400, 110);
		restriction.setLayout(null);
		
		JLabel devis = new JLabel("Du devis n°");
		AutoCompleteModel modelDevis1 = new AutoCompleteModel();
		modelDevis1.addAll(listDevis());
		jNumDevis3 = new AutoComplete(modelDevis1);
		jNumDevis3.getZoneTexte().addKeyListener(new EcouteAction(jNumDevis3.getZoneTexte(), false));
		devis.setBounds(150 - devis.getPreferredSize().width, 20, devis.getPreferredSize().width,
				devis.getPreferredSize().height);
		restriction.add(devis);
		jNumDevis3.setPreferredSize(new Dimension(80, 25));
		jNumDevis3.setBounds(160, 18, jNumDevis3.getPreferredSize().width, jNumDevis3.getPreferredSize().height);
		jNumDevis3.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsDevis"));
		restriction.add(jNumDevis3);
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JButton list1 = new JButton(icon2);
		list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(170 + jNumDevis3.getPreferredSize().width, 20, list1.getPreferredSize().width,
				list1.getPreferredSize().height);
		list1.addActionListener(new ActionSearch(this, "EtatsDevis", "NumDevis", jNumDevis3));
		restriction.add(list1);
		JLabel client = new JLabel("Pour le client n°");
		AutoCompleteModel modelDevis2 = new AutoCompleteModel();
		modelDevis2.addAll(listClients());
		jNumClient2 = new AutoComplete(modelDevis2);
		jNumClient2.getZoneTexte().addKeyListener(new EcouteAction(jNumClient2.getZoneTexte(), false));
		client.setBounds(150 - client.getPreferredSize().width, 50, client.getPreferredSize().width,
				client.getPreferredSize().height);
		restriction.add(client);
		jNumClient2.setPreferredSize(new Dimension(80, 25));
		jNumClient2.setBounds(160, 48, jNumClient2.getPreferredSize().width, jNumClient2.getPreferredSize().height);
		jNumClient2.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsDevis"));
		restriction.add(jNumClient2);
		JButton list3 = new JButton(icon2);
		list3.setPreferredSize(new Dimension(19, 20));
		list3.setBounds(170 + jNumClient2.getPreferredSize().width, 48, list3.getPreferredSize().width,
				list3.getPreferredSize().height);
		list3.addActionListener(new ActionSearch(this, "EtatsDevis", "NumClient", jNumClient2));
		restriction.add(list3);
		labelClient2 = new JLabel();
		labelClient2.setBounds(180 + jNumClient2.getPreferredSize().width + list3.getPreferredSize().width, 50, 0, 0);
		labelClient2.setForeground(Color.BLUE);
		restriction.add(labelClient2);
		listeDevis.add(restriction);
		JLabel dateLabel = new JLabel("A partir du");
		dateLabel.setBounds(150 - dateLabel.getPreferredSize().width, 80, dateLabel.getPreferredSize().width,
				dateLabel.getPreferredSize().height);
		restriction.add(dateLabel);
		jDate = new JDateChooser();
		jDate.setDateFormatString("dd/MM/yyyy");
		jDate.setPreferredSize(new Dimension(120, 25));
		jDate.setBounds(160, 78, jDate.getPreferredSize().width,
				jDate.getPreferredSize().height);
		restriction.add(jDate);
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Paiement"));
		rupture.setLayout(null);
		ButtonGroup bg = new ButtonGroup();
		br1 = new JRadioButton("Pas de Rupture", true);
		br2 = new JRadioButton("Par Client", false);
		br3 = new JRadioButton("Par Date", false);
		bg.add(br1);
		bg.add(br2);
		bg.add(br3);
		rupture.add(br1);
		rupture.add(br2);
		rupture.add(br3);
		rupture.setBounds(440, 120, 140, 110);
		listeDevis.add(rupture);
		br1.setBounds(10, 20, br1.getPreferredSize().width, br1.getPreferredSize().height);
		br2.setBounds(10, 50, br2.getPreferredSize().width, br2.getPreferredSize().height);
		br3.setBounds(10, 80, br3.getPreferredSize().width, br3.getPreferredSize().height);
		
	}
	
	protected ArrayList<String> listDevis() {
		listDevis = donnees.liste("NumDevis, LblDevis", "devis", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listDevis.length; i++) {
			res.add(listDevis[i][0].toString());
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

	public AutoComplete getjNumDevis1() {
		return jNumDevis1;
	}

	public void setjNumDevis1(AutoComplete jNumDevis1) {
		this.jNumDevis1 = jNumDevis1;
	}

	public AutoComplete getjNumDevis2() {
		return jNumDevis2;
	}

	public void setjNumDevis2(AutoComplete jNumDevis2) {
		this.jNumDevis2 = jNumDevis2;
	}

	public Object[][] getListDevis() {
		return listDevis;
	}

	public void setListDevis(Object[][] listDevis) {
		this.listDevis = listDevis;
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

	public AutoComplete getjNumDevis3() {
		return jNumDevis3;
	}

	public void setjNumDevis3(AutoComplete jNumDevis3) {
		this.jNumDevis3 = jNumDevis3;
	}

	public AutoComplete getjNumClient2() {
		return jNumClient2;
	}

	public void setjNumClient2(AutoComplete jNumClient2) {
		this.jNumClient2 = jNumClient2;
	}

	public JLabel getLabelClient2() {
		return labelClient2;
	}

	public void setLabelClient2(JLabel labelClient2) {
		this.labelClient2 = labelClient2;
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

	public JRadioButton getBr3() {
		return br3;
	}

	public void setBr3(JRadioButton br3) {
		this.br3 = br3;
	}
		

}
