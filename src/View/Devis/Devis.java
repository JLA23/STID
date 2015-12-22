package View.Devis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import com.toedter.calendar.JDateChooser;
import BDD.Base;
import Controller.ActionCalculatrice;
import Controller.EcouteAction;
import Controller.Devis.ActionSearch;
import Controller.Devis.ExecuteClick;
import Controller.Devis.FocusClient;
import Controller.Devis.FocusPosition;
import Controller.Devis.ItemChange;
import Model.Donnees;
import View.Options.ClickDroit;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class Devis extends JDialog {
	protected JButton calcul1, valider, fermer, newClient, calcul2, calcul3, calcul4, calcul5, calcul6, calcul7,
			nouveau, search;
	protected JComboBox<String> devises;
	protected JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalDevis, hrsAtelier, prevu, hrsSite, hrs1,
			dateLabel, euro5;
	protected JLabel hrs2, hrs3, totalHeures, commande, euro6, resteCommande, euro7, deviselabel, libelle,
			numClientLabel, fournitures;
	protected JLabel coutMO, totalDevisdevise, devise, nameClient;
	protected JPanel jPanel1, jPanel2, jPanel3, JPanelTemps, jPanel6;
	protected JSeparator jSeparator1, jSeparator2;
	protected JFormattedTextField jTotalDevisDevise, jHeureSite, jFournitures, jPrefabrication, jCout, jTotalDevis,
			jPrevu, jHeureAtelier, jTotalHeure, jCommande, jResteCommande, jNumDevis;
	protected JTextField jLibelle;
	protected JDateChooser jDate;
	protected double valeurDevise;
	protected Donnees donnees;
	protected AutoComplete numClient;
	protected Base base;
	protected HashMap<String, String[]> valeurDevises;
	protected Object[][] listClient;
	protected static final long serialVersionUID = 1L;
	protected Dimension screenSize = new Dimension();

	@SuppressWarnings("unchecked")
	public Devis(Base bdd, JFrame frame) {
		super(frame, null, true);
		this.base = bdd;
		this.donnees = new Donnees(base);
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouveau Devis)");
		screenSize.width = 785;
		screenSize.height = 600;
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setSize(screenSize);

		NumberFormat num = NumberFormat.getIntegerInstance();
		DecimalFormat nf = new DecimalFormat("#0.00");
		nf.setGroupingUsed(false);

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		JPanelTemps = new JPanel();
		jPanel6 = new JPanel();
		jPanel3 = new JPanel();

		jDate = new JDateChooser();
		int nbDevis = donnees.newNumDevis();
		AutoCompleteModel model = new AutoCompleteModel();
		model.addAll(listClient());
		numClient = new AutoComplete(model);

		numero = new JLabel("Numéro");
		dateLabel = new JLabel("Date");
		deviselabel = new JLabel("Devise");
		libelle = new JLabel("Libellé");
		numClientLabel = new JLabel("N° Client");
		hrsAtelier = new JLabel("Heures d'Atelier");
		hrsSite = new JLabel("Heures sur Site");
		hrs1 = new JLabel("Hrs");
		hrs2 = new JLabel("Hrs");
		hrs3 = new JLabel("Hrs");
		totalHeures = new JLabel("Total des Heures");
		fournitures = new JLabel("Fournitures");
		coutMO = new JLabel("Coût MO");
		prefabrication = new JLabel("Préfabrication");
		euro1 = new JLabel("EUR");
		euro2 = new JLabel("EUR");
		euro3 = new JLabel("EUR");
		euro4 = new JLabel("EUR");
		euro5 = new JLabel("EUR");
		euro6 = new JLabel("EUR");
		euro7 = new JLabel("EUR");
		totalDevis = new JLabel("Total Devis");
		prevu = new JLabel("Prévu");
		commande = new JLabel("Commandé");
		resteCommande = new JLabel("Reste à commander");
		totalDevisdevise = new JLabel("Total (Devise)");
		devise = new JLabel();
		nameClient = new JLabel("Client : ");

		jNumDevis = new JFormattedTextField(num);
		jNumDevis.setText(nbDevis + "");
		jLibelle = new JTextField();
		jFournitures = new JFormattedTextField(nf);
		jCout = new JFormattedTextField(nf);
		jPrefabrication = new JFormattedTextField(nf);
		jTotalDevis = new JFormattedTextField(nf);
		jHeureSite = new JFormattedTextField(nf);
		jHeureAtelier = new JFormattedTextField(nf);
		jTotalHeure = new JFormattedTextField(nf);
		jPrevu = new JFormattedTextField(nf);
		jCommande = new JFormattedTextField(nf);
		jResteCommande = new JFormattedTextField(nf);

		jFournitures.setText("0,00");
		jCout.setText("0,00");
		jPrefabrication.setText("0,00");
		jTotalDevis.setText("0,00");
		jHeureSite.setText("0,00");
		jHeureAtelier.setText("0,00");
		jTotalHeure.setText("0,00");
		jResteCommande.setText("0,00");
		jPrevu.setText("0,00");
		jCommande.setText("0,00");

		jTotalDevisDevise = new JFormattedTextField(nf);
		jTotalDevisDevise.setText("0,00");

		jTotalDevis.setEditable(false);
		jTotalHeure.setEditable(false);
		jResteCommande.setEditable(false);
		jTotalDevisDevise.setEditable(false);

		devises = new JComboBox<>();

		ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
		newClient = new JButton("Crèer Client");
		calcul4 = new JButton(icon);
		calcul6 = new JButton(icon);
		calcul1 = new JButton(icon);
		calcul2 = new JButton(icon);
		calcul3 = new JButton(icon);
		calcul5 = new JButton(icon);
		calcul7 = new JButton(icon);
		nouveau = new JButton("Nouveau");
		valider = new JButton("Valider");
		fermer = new JButton("Fermer");
		search = new JButton("Chercher Client");

		jSeparator2 = new JSeparator();
		jSeparator1 = new JSeparator();

		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jDate.setDateFormatString("dd/MM/yyyy");
		jDate.setDate(new Date());

		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		InsertDevises();
		valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
		devises.addItemListener(new ItemChange(this));
		JPanelTemps.setBorder(BorderFactory.createTitledBorder("Temps"));
		JPanelTemps.setPreferredSize(new Dimension(350, 200));

		jTotalHeure.setBackground(new Color(204, 204, 204));
		jTotalDevis.setBackground(new Color(204, 204, 204));
		jResteCommande.setBackground(new Color(204, 204, 204));
		jTotalDevisDevise.setBackground(new Color(204, 204, 204));

		jPanel6.setBorder(BorderFactory.createTitledBorder("Montants"));
		jPanel6.setPreferredSize(new Dimension(350, 200));
		jPanel3.setBorder(BorderFactory.createTitledBorder("Achat Matières"));

		devise.setText(devises.getSelectedItem().toString());
		
		jPanel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jPanel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		JPanelTemps.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jPanel6.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jPanel3.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

		search.addActionListener(new ActionSearch(this));
		
		ExecuteClick click = new ExecuteClick(this);
		
		jFournitures.addMouseListener(new FocusPosition(this, jFournitures, 1, click));
		jCout.addMouseListener(new FocusPosition(this, jCout, 1, click));
		jPrefabrication.addMouseListener(new FocusPosition(this, jPrefabrication, 1, click));
		jHeureSite.addMouseListener(new FocusPosition(this, jHeureSite, 2, click));
		jHeureAtelier.addMouseListener(new FocusPosition(this,jHeureAtelier, 2, click));
		jPrevu.addMouseListener(new FocusPosition(this, jPrevu, 3, click));
		jCommande.addMouseListener(new FocusPosition(this, jCommande, 3, click));
		
		new ClickDroit(jTotalDevis, true, false);
		new ClickDroit(jTotalDevisDevise, true, false);
		new ClickDroit(jTotalHeure, true, false);
		new ClickDroit(jResteCommande, true, false);

		jFournitures.addFocusListener(new FocusPosition(this, jFournitures, 1,click));
		jCout.addFocusListener(new FocusPosition(this, jCout, 1, click));
		jPrefabrication.addFocusListener(new FocusPosition(this, jPrefabrication, 1, click));
		jHeureSite.addFocusListener(new FocusPosition(this, jHeureSite, 2, click));
		jHeureAtelier.addFocusListener(new FocusPosition(this,jHeureAtelier, 2, click));
		jPrevu.addFocusListener(new FocusPosition(this, jPrevu, 3, click));
		jCommande.addFocusListener(new FocusPosition(this, jCommande, 3, click));
		
		jFournitures.addKeyListener(new EcouteAction(jFournitures));
		jCout.addKeyListener(new EcouteAction(jCout));
		jPrefabrication.addKeyListener(new EcouteAction(jPrefabrication));
		jHeureSite.addKeyListener(new EcouteAction(jHeureSite));
		jHeureAtelier.addKeyListener(new EcouteAction(jHeureAtelier));
		jPrevu.addKeyListener(new EcouteAction(jPrevu));
		jCommande.addKeyListener(new EcouteAction(jCommande));

		this.getContentPane().add(jPanel1);
		jPanel1.setPreferredSize(new Dimension(760, 40));
		jPanel1.setBounds(10, 10, jPanel1.getPreferredSize().width, jPanel1.getPreferredSize().height);
		this.getContentPane().add(jPanel2);
		jPanel2.setPreferredSize(new Dimension(760, 430));
		jPanel2.setBounds(10, 60, jPanel2.getPreferredSize().width, jPanel2.getPreferredSize().height);
		initPanel1();
		initPanel2();
		valider.setPreferredSize(new Dimension(80, 25));
		valider.setBounds(670, 495, valider.getPreferredSize().width, valider.getPreferredSize().height);
		this.getContentPane().add(valider);
		fermer.setPreferredSize(new Dimension(80, 25));
		fermer.setBounds(670, 530, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		this.getContentPane().add(fermer);
		nouveau.setPreferredSize(new Dimension(90, 25));
		nouveau.setBounds(20, 510, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
		this.getContentPane().add(nouveau);
		
		new ClickDroit(jNumDevis, true, true);
		jNumDevis.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNumDevis.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jDate.requestFocus();
			}
		});
		
		jDate.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jDate.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jLibelle.requestFocus();
			}
		});
		
		new ClickDroit(jLibelle, true, true);
		jLibelle.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jLibelle.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				numClient.getZoneTexte().requestFocus();
			}
		});
		
		new ClickDroit(numClient.getZoneTexte(), true, true);
		numClient.getZoneTexte().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		numClient.getZoneTexte().getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jFournitures.requestFocus();
			}
		});

		jFournitures.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jFournitures.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jCout.requestFocus();
			}
		});
		jCout.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jCout.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jPrefabrication.requestFocus();
			}
		});
		jPrefabrication.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jPrefabrication.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jHeureAtelier.requestFocus();
			}
		});
		jHeureAtelier.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jHeureAtelier.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jHeureSite.requestFocus();
			}
		});

		jHeureSite.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jHeureSite.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jPrevu.requestFocus();
			}
		});

		jPrevu.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jPrevu.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jCommande.requestFocus();
			}
		});

		jCommande.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jCommande.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				valider.requestFocus();
			}
		});

		calcul1.addActionListener(new ActionCalculatrice(jFournitures));
		calcul2.addActionListener(new ActionCalculatrice(jCout));
		calcul3.addActionListener(new ActionCalculatrice(jPrefabrication));
		calcul4.addActionListener(new ActionCalculatrice(jHeureAtelier));
		calcul5.addActionListener(new ActionCalculatrice(jPrevu));
		calcul6.addActionListener(new ActionCalculatrice(jHeureSite));
		calcul7.addActionListener(new ActionCalculatrice(jCommande));
		
		numClient.getZoneTexte().addFocusListener(new FocusClient(this));

	}

	protected void initPanel1() {
		jPanel1.setLayout(null);
		numero.setBounds(10, 11, numero.getPreferredSize().width, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 20;
		jNumDevis.setPreferredSize(new Dimension(80, 20));
		jNumDevis.setBounds(l, 10, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
		jPanel1.add(jNumDevis);
		l += jNumDevis.getPreferredSize().width + 160;
		dateLabel.setBounds(l, 11, dateLabel.getPreferredSize().width, dateLabel.getPreferredSize().height);
		jPanel1.add(dateLabel);
		l += dateLabel.getPreferredSize().width + 10;
		jDate.setPreferredSize(new Dimension(100, 20));
		jDate.setBounds(l, 10, jDate.getPreferredSize().width, jDate.getPreferredSize().height);
		jPanel1.add(jDate);
		l += jDate.getPreferredSize().width + 160;
		deviselabel.setBounds(l, 11, deviselabel.getPreferredSize().width, deviselabel.getPreferredSize().height);
		jPanel1.add(deviselabel);
		l += deviselabel.getPreferredSize().width + 10;
		devises.setPreferredSize(new Dimension(100, 25));
		devises.setBounds(l, 7, devises.getPreferredSize().width, devises.getPreferredSize().height);
		jPanel1.add(devises);
	}

	protected void initPanel2() {
		jPanel2.setLayout(null);
		jLibelle.setPreferredSize(new Dimension(500, 20));
		jLibelle.setBounds(70, 10, jLibelle.getPreferredSize().width, jLibelle.getPreferredSize().height);
		jPanel2.add(jLibelle);
		libelle.setBounds(60 - libelle.getPreferredSize().width, 11, libelle.getPreferredSize().width,
				libelle.getPreferredSize().height);
		jPanel2.add(libelle);
		numClient.setPreferredSize(new Dimension(80, 20));
		numClient.setBounds(70, 40, numClient.getPreferredSize().width, numClient.getPreferredSize().height);
		jPanel2.add(numClient);
		numClientLabel.setBounds(60 - numClientLabel.getPreferredSize().width, 41,
				numClientLabel.getPreferredSize().width, numClientLabel.getPreferredSize().height);
		jPanel2.add(numClientLabel);
		search.setPreferredSize(new Dimension(125, 25));
		search.setBounds(80 + numClient.getPreferredSize().width, 37, search.getPreferredSize().width,
				search.getPreferredSize().height);
		jPanel2.add(search);
		newClient.setPreferredSize(new Dimension(105, 25));
		newClient.setBounds(90 + search.getPreferredSize().width + numClient.getPreferredSize().width, 37,
				newClient.getPreferredSize().width, newClient.getPreferredSize().height);
		jPanel2.add(newClient);
		nameClient.setBounds(
				120 + newClient.getPreferredSize().width + search.getPreferredSize().width
						+ numClient.getPreferredSize().width,
				41, nameClient.getPreferredSize().width, nameClient.getPreferredSize().height);
		jPanel2.add(nameClient);
		jPanel6.setPreferredSize(new Dimension(350, 230));
		jPanel6.setBounds(20, 70, jPanel6.getPreferredSize().width, jPanel6.getPreferredSize().height);
		jPanel2.add(jPanel6);
		initPanelMontant();
		JPanelTemps.setPreferredSize(new Dimension(350, 230));
		JPanelTemps.setBounds(390, 70, JPanelTemps.getPreferredSize().width, JPanelTemps.getPreferredSize().height);
		jPanel2.add(JPanelTemps);
		initPanelTemps();
		jPanel3.setPreferredSize(new Dimension(720, 120));
		jPanel3.setBounds(20, 300, jPanel3.getPreferredSize().width, jPanel3.getPreferredSize().height);
		jPanel2.add(jPanel3);
		initAchats();
	}

	protected void initPanelMontant() {
		jPanel6.setLayout(null);
		jFournitures.setPreferredSize(new Dimension(100, 20));
		jFournitures.setBounds(125, 40, jFournitures.getPreferredSize().width, jFournitures.getPreferredSize().height);
		jPanel6.add(jFournitures);
		fournitures.setBounds(115 - fournitures.getPreferredSize().width, 41, fournitures.getPreferredSize().width,
				fournitures.getPreferredSize().height);
		jPanel6.add(fournitures);
		int l = 135 + jFournitures.getPreferredSize().width;
		euro1.setBounds(l, 41, euro1.getPreferredSize().width, euro1.getPreferredSize().height);
		jPanel6.add(euro1);
		l += euro1.getPreferredSize().width + 20;
		calcul1.setPreferredSize(new Dimension(19, 20));
		calcul1.setBounds(l, 40, calcul1.getPreferredSize().width, calcul1.getPreferredSize().height);
		jPanel6.add(calcul1);

		jCout.setPreferredSize(new Dimension(100, 20));
		jCout.setBounds(125, 70, jCout.getPreferredSize().width, jCout.getPreferredSize().height);
		jPanel6.add(jCout);
		coutMO.setBounds(115 - coutMO.getPreferredSize().width, 71, coutMO.getPreferredSize().width,
				coutMO.getPreferredSize().height);
		jPanel6.add(coutMO);
		l = 135 + jCout.getPreferredSize().width;
		euro2.setBounds(l, 71, euro2.getPreferredSize().width, euro2.getPreferredSize().height);
		jPanel6.add(euro2);
		l += euro2.getPreferredSize().width + 20;
		calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(l, 70, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
		jPanel6.add(calcul2);

		jPrefabrication.setPreferredSize(new Dimension(100, 20));
		jPrefabrication.setBounds(125, 100, jPrefabrication.getPreferredSize().width,
				jPrefabrication.getPreferredSize().height);
		jPanel6.add(jPrefabrication);
		prefabrication.setBounds(115 - prefabrication.getPreferredSize().width, 101,
				prefabrication.getPreferredSize().width, prefabrication.getPreferredSize().height);
		jPanel6.add(prefabrication);
		l = 135 + jPrefabrication.getPreferredSize().width;
		euro3.setBounds(l, 101, euro3.getPreferredSize().width, euro3.getPreferredSize().height);
		jPanel6.add(euro3);
		l += euro3.getPreferredSize().width + 20;
		calcul3.setPreferredSize(new Dimension(19, 20));
		calcul3.setBounds(l, 100, calcul3.getPreferredSize().width, calcul3.getPreferredSize().height);
		jPanel6.add(calcul3);

		jSeparator1.setPreferredSize(new Dimension(340, 2));
		jSeparator1.setBounds(5, 130, jSeparator1.getPreferredSize().width, jSeparator1.getPreferredSize().height);
		jPanel6.add(jSeparator1);

		jTotalDevis.setPreferredSize(new Dimension(100, 20));
		jTotalDevis.setBounds(125, 140, jTotalDevis.getPreferredSize().width, jTotalDevis.getPreferredSize().height);
		jPanel6.add(jTotalDevis);
		totalDevis.setBounds(115 - totalDevis.getPreferredSize().width, 141, totalDevis.getPreferredSize().width,
				totalDevis.getPreferredSize().height);
		jPanel6.add(totalDevis);
		l = 135 + jTotalDevis.getPreferredSize().width;
		euro4.setBounds(l, 141, euro4.getPreferredSize().width, euro4.getPreferredSize().height);
		jPanel6.add(euro4);

		jTotalDevisDevise.setPreferredSize(new Dimension(100, 20));
		jTotalDevisDevise.setBounds(125, 170, jTotalDevisDevise.getPreferredSize().width,
				jTotalDevisDevise.getPreferredSize().height);
		jPanel6.add(jTotalDevisDevise);
		totalDevisdevise.setBounds(115 - totalDevisdevise.getPreferredSize().width, 171,
				totalDevisdevise.getPreferredSize().width, totalDevis.getPreferredSize().height);
		jPanel6.add(totalDevisdevise);
		l = 135 + jTotalDevisDevise.getPreferredSize().width;
		devise.setBounds(l, 171, devise.getPreferredSize().width, devise.getPreferredSize().height);
		jPanel6.add(devise);

	}

	protected void initPanelTemps() {
		JPanelTemps.setLayout(null);
		jHeureAtelier.setPreferredSize(new Dimension(100, 20));
		jHeureAtelier.setBounds(125, 50, jHeureAtelier.getPreferredSize().width,
				jHeureAtelier.getPreferredSize().height);
		JPanelTemps.add(jHeureAtelier);
		hrsAtelier.setBounds(115 - hrsAtelier.getPreferredSize().width, 51, hrsAtelier.getPreferredSize().width,
				hrsAtelier.getPreferredSize().height);
		JPanelTemps.add(hrsAtelier);
		int l = 135 + jHeureAtelier.getPreferredSize().width;
		hrs1.setBounds(l, 51, hrs1.getPreferredSize().width, hrs1.getPreferredSize().height);
		JPanelTemps.add(hrs1);
		l += hrs1.getPreferredSize().width + 20;
		calcul4.setPreferredSize(new Dimension(19, 20));
		calcul4.setBounds(l, 50, calcul4.getPreferredSize().width, calcul4.getPreferredSize().height);
		JPanelTemps.add(calcul4);

		jHeureSite.setPreferredSize(new Dimension(100, 20));
		jHeureSite.setBounds(125, 90, jHeureSite.getPreferredSize().width, jHeureSite.getPreferredSize().height);
		JPanelTemps.add(jHeureSite);
		hrsSite.setBounds(115 - hrsSite.getPreferredSize().width, 91, hrsSite.getPreferredSize().width,
				hrsSite.getPreferredSize().height);
		JPanelTemps.add(hrsSite);
		l = 135 + jHeureSite.getPreferredSize().width;
		hrs2.setBounds(l, 91, hrs2.getPreferredSize().width, hrs2.getPreferredSize().height);
		JPanelTemps.add(hrs2);
		l += hrs2.getPreferredSize().width + 20;
		calcul5.setPreferredSize(new Dimension(19, 20));
		calcul5.setBounds(l, 90, calcul5.getPreferredSize().width, calcul5.getPreferredSize().height);
		JPanelTemps.add(calcul5);

		jSeparator2.setPreferredSize(new Dimension(340, 2));
		jSeparator2.setBounds(5, 130, jSeparator2.getPreferredSize().width, jSeparator2.getPreferredSize().height);
		JPanelTemps.add(jSeparator2);

		jTotalHeure.setPreferredSize(new Dimension(100, 20));
		jTotalHeure.setBounds(125, 155, jTotalHeure.getPreferredSize().width, jTotalHeure.getPreferredSize().height);
		JPanelTemps.add(jTotalHeure);
		totalHeures.setBounds(115 - totalHeures.getPreferredSize().width, 156, totalHeures.getPreferredSize().width,
				totalHeures.getPreferredSize().height);
		JPanelTemps.add(totalHeures);
		l = 135 + jTotalHeure.getPreferredSize().width;
		hrs3.setBounds(l, 156, hrs3.getPreferredSize().width, hrs3.getPreferredSize().height);
		JPanelTemps.add(hrs3);
	}

	protected void initAchats() {
		jPanel3.setLayout(null);
		jPrevu.setPreferredSize(new Dimension(100, 20));
		jPrevu.setBounds(125, 30, jPrevu.getPreferredSize().width, jPrevu.getPreferredSize().height);
		jPanel3.add(jPrevu);
		prevu.setBounds(115 - prevu.getPreferredSize().width, 31, prevu.getPreferredSize().width,
				prevu.getPreferredSize().height);
		jPanel3.add(prevu);
		int l = 135 + jPrevu.getPreferredSize().width;
		euro5.setBounds(l, 31, euro5.getPreferredSize().width, euro5.getPreferredSize().height);
		jPanel3.add(euro5);
		l += euro5.getPreferredSize().width + 20;
		calcul6.setPreferredSize(new Dimension(19, 20));
		calcul6.setBounds(l, 30, calcul6.getPreferredSize().width, calcul6.getPreferredSize().height);
		jPanel3.add(calcul6);

		jCommande.setPreferredSize(new Dimension(100, 20));
		jCommande.setBounds(495, 30, jCommande.getPreferredSize().width, jCommande.getPreferredSize().height);
		jPanel3.add(jCommande);
		commande.setBounds(485 - commande.getPreferredSize().width, 31, commande.getPreferredSize().width,
				commande.getPreferredSize().height);
		jPanel3.add(commande);
		l = 505 + jCommande.getPreferredSize().width;
		euro6.setBounds(l, 31, euro6.getPreferredSize().width, euro6.getPreferredSize().height);
		jPanel3.add(euro6);
		l += euro6.getPreferredSize().width + 20;
		calcul7.setPreferredSize(new Dimension(19, 20));
		calcul7.setBounds(l, 30, calcul7.getPreferredSize().width, calcul7.getPreferredSize().height);
		jPanel3.add(calcul7);

		jResteCommande.setPreferredSize(new Dimension(100, 20));
		jResteCommande.setBounds(310, 80, jResteCommande.getPreferredSize().width,
				jResteCommande.getPreferredSize().height);
		jPanel3.add(jResteCommande);
		resteCommande.setBounds(300 - resteCommande.getPreferredSize().width, 81,
				resteCommande.getPreferredSize().width, resteCommande.getPreferredSize().height);
		jPanel3.add(resteCommande);
		l = 320 + jResteCommande.getPreferredSize().width;
		euro7.setBounds(l, 81, euro7.getPreferredSize().width, euro7.getPreferredSize().height);
		jPanel3.add(euro7);
	}

	protected void InsertDevises() {
		valeurDevises = donnees.devises();
		for (Entry<String, String[]> entry : valeurDevises.entrySet()) {
			devises.addItem(entry.getKey());
		}
		devises.setSelectedItem("EUR");
	}

	protected ArrayList<String> listClient() {
		listClient = donnees.listeClient();
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listClient.length; i++) {
			res.add(listClient[i][0].toString());
		}
		return res;
	}

	public JLabel getDevise() {
		return devise;
	}

	public void setDevise(JLabel devise) {
		this.devise = devise;
	}

	public HashMap<String, String[]> getValeurDevises() {
		return valeurDevises;
	}

	public void setValeurDevises(HashMap<String, String[]> valeurDevises) {
		this.valeurDevises = valeurDevises;
	}

	public JComboBox<String> getDevises() {
		return devises;
	}

	public void setDevises(JComboBox<String> devises) {
		this.devises = devises;
	}

	public double getValeurDevise() {
		return valeurDevise;
	}

	public void setValeurDevise(double valeurDevise) {
		this.valeurDevise = valeurDevise;
	}

	public JButton getCalcul1() {
		return calcul1;
	}

	public void setCalcul1(JButton calcul1) {
		this.calcul1 = calcul1;
	}

	public JButton getValider() {
		return valider;
	}

	public void setValider(JButton valider) {
		this.valider = valider;
	}

	public JButton getFermer() {
		return fermer;
	}

	public void setFermer(JButton fermer) {
		this.fermer = fermer;
	}

	public JButton getNewClient() {
		return newClient;
	}

	public void setNewClient(JButton newClient) {
		this.newClient = newClient;
	}

	public JButton getCalcul2() {
		return calcul2;
	}

	public void setCalcul2(JButton calcul2) {
		this.calcul2 = calcul2;
	}

	public JButton getCalcul3() {
		return calcul3;
	}

	public void setCalcul3(JButton calcul3) {
		this.calcul3 = calcul3;
	}

	public JButton getCalcul4() {
		return calcul4;
	}

	public void setCalcul4(JButton calcul4) {
		this.calcul4 = calcul4;
	}

	public JButton getCalcul5() {
		return calcul5;
	}

	public void setCalcul5(JButton calcul5) {
		this.calcul5 = calcul5;
	}

	public JButton getCalcul6() {
		return calcul6;
	}

	public void setCalcul6(JButton calcul6) {
		this.calcul6 = calcul6;
	}

	public JButton getCalcul7() {
		return calcul7;
	}

	public void setCalcul7(JButton calcul7) {
		this.calcul7 = calcul7;
	}

	public JButton getNouveau() {
		return nouveau;
	}

	public void setNouveau(JButton nouveau) {
		this.nouveau = nouveau;
	}

	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	public JLabel getNumero() {
		return numero;
	}

	public void setNumero(JLabel numero) {
		this.numero = numero;
	}

	public JLabel getPrefabrication() {
		return prefabrication;
	}

	public void setPrefabrication(JLabel prefabrication) {
		this.prefabrication = prefabrication;
	}

	public JLabel getEuro1() {
		return euro1;
	}

	public void setEuro1(JLabel euro1) {
		this.euro1 = euro1;
	}

	public JLabel getEuro2() {
		return euro2;
	}

	public void setEuro2(JLabel euro2) {
		this.euro2 = euro2;
	}

	public JLabel getEuro3() {
		return euro3;
	}

	public void setEuro3(JLabel euro3) {
		this.euro3 = euro3;
	}

	public JLabel getEuro4() {
		return euro4;
	}

	public void setEuro4(JLabel euro4) {
		this.euro4 = euro4;
	}

	public JLabel getTotalDevis() {
		return totalDevis;
	}

	public void setTotalDevis(JLabel totalDevis) {
		this.totalDevis = totalDevis;
	}

	public JLabel getHrsAtelier() {
		return hrsAtelier;
	}

	public void setHrsAtelier(JLabel hrsAtelier) {
		this.hrsAtelier = hrsAtelier;
	}

	public JLabel getPrevu() {
		return prevu;
	}

	public void setPrevu(JLabel prevu) {
		this.prevu = prevu;
	}

	public JLabel getHrsSite() {
		return hrsSite;
	}

	public void setHrsSite(JLabel hrsSite) {
		this.hrsSite = hrsSite;
	}

	public JLabel getHrs1() {
		return hrs1;
	}

	public void setHrs1(JLabel hrs1) {
		this.hrs1 = hrs1;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	public JLabel getEuro5() {
		return euro5;
	}

	public void setEuro5(JLabel euro5) {
		this.euro5 = euro5;
	}

	public JLabel getHrs2() {
		return hrs2;
	}

	public void setHrs2(JLabel hrs2) {
		this.hrs2 = hrs2;
	}

	public JLabel getHrs3() {
		return hrs3;
	}

	public void setHrs3(JLabel hrs3) {
		this.hrs3 = hrs3;
	}

	public JLabel getTotalHeures() {
		return totalHeures;
	}

	public void setTotalHeures(JLabel totalHeures) {
		this.totalHeures = totalHeures;
	}

	public JLabel getCommande() {
		return commande;
	}

	public void setCommande(JLabel commande) {
		this.commande = commande;
	}

	public JLabel getEuro6() {
		return euro6;
	}

	public void setEuro6(JLabel euro6) {
		this.euro6 = euro6;
	}

	public JLabel getResteCommande() {
		return resteCommande;
	}

	public void setResteCommande(JLabel resteCommande) {
		this.resteCommande = resteCommande;
	}

	public JLabel getEuro7() {
		return euro7;
	}

	public void setEuro7(JLabel euro7) {
		this.euro7 = euro7;
	}

	public JLabel getDeviselabel() {
		return deviselabel;
	}

	public void setDeviselabel(JLabel deviselabel) {
		this.deviselabel = deviselabel;
	}

	public JLabel getLibelle() {
		return libelle;
	}

	public void setLibelle(JLabel libelle) {
		this.libelle = libelle;
	}

	public JLabel getNumClientLabel() {
		return numClientLabel;
	}

	public void setNumClientLabel(JLabel numClientLabel) {
		this.numClientLabel = numClientLabel;
	}

	public JLabel getFournitures() {
		return fournitures;
	}

	public void setFournitures(JLabel fournitures) {
		this.fournitures = fournitures;
	}

	public JLabel getCoutMO() {
		return coutMO;
	}

	public void setCoutMO(JLabel coutMO) {
		this.coutMO = coutMO;
	}

	public JLabel getTotalDevisdevise() {
		return totalDevisdevise;
	}

	public void setTotalDevisdevise(JLabel totalDevisdevise) {
		this.totalDevisdevise = totalDevisdevise;
	}

	public JLabel getNameClient() {
		return nameClient;
	}

	public void setNameClient(JLabel nameClient) {
		this.nameClient = nameClient;
	}

	public JPanel getjPanel1() {
		return jPanel1;
	}

	public void setjPanel1(JPanel jPanel1) {
		this.jPanel1 = jPanel1;
	}

	public JPanel getjPanel2() {
		return jPanel2;
	}

	public void setjPanel2(JPanel jPanel2) {
		this.jPanel2 = jPanel2;
	}

	public JPanel getjPanel3() {
		return jPanel3;
	}

	public void setjPanel3(JPanel jPanel3) {
		this.jPanel3 = jPanel3;
	}

	public JPanel getJPanelTemps() {
		return JPanelTemps;
	}

	public void setJPanelTemps(JPanel jPanelTemps) {
		JPanelTemps = jPanelTemps;
	}

	public JPanel getjPanel6() {
		return jPanel6;
	}

	public void setjPanel6(JPanel jPanel6) {
		this.jPanel6 = jPanel6;
	}

	public JSeparator getjSeparator1() {
		return jSeparator1;
	}

	public void setjSeparator1(JSeparator jSeparator1) {
		this.jSeparator1 = jSeparator1;
	}

	public JSeparator getjSeparator2() {
		return jSeparator2;
	}

	public void setjSeparator2(JSeparator jSeparator2) {
		this.jSeparator2 = jSeparator2;
	}

	public JFormattedTextField getjTotalDevisDevise() {
		return jTotalDevisDevise;
	}

	public void setjTotalDevisDevise(JFormattedTextField jTotalDevisDevise) {
		this.jTotalDevisDevise = jTotalDevisDevise;
	}

	public JFormattedTextField getjHeureSite() {
		return jHeureSite;
	}

	public void setjHeureSite(JFormattedTextField jHeureSite) {
		this.jHeureSite = jHeureSite;
	}

	public JFormattedTextField getjFournitures() {
		return jFournitures;
	}

	public void setjFournitures(JFormattedTextField jFournitures) {
		this.jFournitures = jFournitures;
	}

	public JFormattedTextField getjPrefabrication() {
		return jPrefabrication;
	}

	public void setjPrefabrication(JFormattedTextField jPrefabrication) {
		this.jPrefabrication = jPrefabrication;
	}

	public JFormattedTextField getjCout() {
		return jCout;
	}

	public void setjCout(JFormattedTextField jCout) {
		this.jCout = jCout;
	}

	public JFormattedTextField getjTotalDevis() {
		return jTotalDevis;
	}

	public void setjTotalDevis(JFormattedTextField jTotalDevis) {
		this.jTotalDevis = jTotalDevis;
	}

	public JFormattedTextField getjPrevu() {
		return jPrevu;
	}

	public void setjPrevu(JFormattedTextField jPrevu) {
		this.jPrevu = jPrevu;
	}

	public JFormattedTextField getjHeureAtelier() {
		return jHeureAtelier;
	}

	public void setjHeureAtelier(JFormattedTextField jHeureAtelier) {
		this.jHeureAtelier = jHeureAtelier;
	}

	public JFormattedTextField getjTotalHeure() {
		return jTotalHeure;
	}

	public void setjTotalHeure(JFormattedTextField jTotalHeure) {
		this.jTotalHeure = jTotalHeure;
	}

	public JFormattedTextField getjCommande() {
		return jCommande;
	}

	public void setjCommande(JFormattedTextField jCommande) {
		this.jCommande = jCommande;
	}

	public JFormattedTextField getjResteCommande() {
		return jResteCommande;
	}

	public void setjResteCommande(JFormattedTextField jResteCommande) {
		this.jResteCommande = jResteCommande;
	}

	public JFormattedTextField getjNumDevis() {
		return jNumDevis;
	}

	public void setjNumDevis(JFormattedTextField jNumDevis) {
		this.jNumDevis = jNumDevis;
	}

	public JTextField getjLibelle() {
		return jLibelle;
	}

	public void setjLibelle(JTextField jLibelle) {
		this.jLibelle = jLibelle;
	}

	public JDateChooser getjDate() {
		return jDate;
	}

	public void setjDate(JDateChooser jDate) {
		this.jDate = jDate;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public AutoComplete getNumClient() {
		return numClient;
	}

	public void setNumClient(AutoComplete numClient) {
		this.numClient = numClient;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Object[][] getListClient() {
		return listClient;
	}

	public void setListClient(Object[][] listClient) {
		this.listClient = listClient;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}


}