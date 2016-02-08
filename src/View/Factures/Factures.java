package View.Factures;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;

import BDD.Base;
import Controller.ActionCalculatrice;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Controller.ExecuteClick;
import Controller.FocusPosition;
import Controller.ItemChange;
import Model.Donnees;
import View.Options.ClickDroit;


public class Factures extends JFrame{
    protected JButton calcul1, valider, fermer, calcul2, calcul3, nouveau;
    protected JComboBox<String> devises, boxModePaiement;
    protected JTextArea jPrecision;
    protected JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalHT, euro5, tva;
    protected JLabel libelle, fournitures, precision, modePaiement, dateEmission, dateEcheance, valeur, anneeValeur;
    protected JLabel coutMO, totalDevise, devise, nameClient, facture, totalTTC, client, libelle2, labelNumCommandeClient, numCommandeClient;
    protected JPanel jPanel1, jPanel2, jPanel3;
    protected JFormattedTextField jTotalDevise, jTotalTTC, jFournitures, jPrefabrication, jCout, jTotalHT, jNumFacture, jAnneeValeur, jTVA;
    protected JCheckBox jValeur;
    protected JDateChooser jDateEmission, jDateEcheance;
    protected double valeurDevise, valeurTVA;
    protected Donnees donnees;
    protected Base base;
    protected HashMap <String, String []> valeurDevises, modespaiements;
    protected Object [][] listClient;
    protected JFrame fenetre;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Factures(Base bdd, JFrame frame){
		this.fenetre = frame;
		fenetre.setEnabled(false);
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouvelle Facture)");
		screenSize.width = 800;
		screenSize.height = 530;
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    this.base = bdd;
	    donnees = new Donnees(base);
	    
		NumberFormat num =  NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
        DecimalFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
	    
	    jPanel1 = new JPanel();
	    jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        
        jDateEmission = new JDateChooser();
        jDateEcheance = new JDateChooser();
	    
        numero = new JLabel("N° Commande / Indice : ");
        facture = new JLabel("N° Facture");
        libelle = new JLabel("Libellé :");
        fournitures = new JLabel("Fournitures");
        coutMO = new JLabel("Coût MO");
        prefabrication = new JLabel("Préfabrication");
        euro1 = new JLabel("EUR");
        euro2 = new JLabel("EUR");
        euro3 = new JLabel("EUR");
        euro4 = new JLabel("EUR");
        euro5 = new JLabel("EUR");
        totalHT = new JLabel("Montant HT");
        totalTTC = new JLabel("Montant TTC");
        totalDevise = new JLabel("Total (Devise)");
		devise = new JLabel();
		nameClient = new JLabel ("Client :");
		precision = new JLabel("Précisions sur Lettre");
		dateEmission = new JLabel("Date Emission");
		dateEcheance = new JLabel("Date Echéance");
		modePaiement = new JLabel("Mode de Paiement");
		valeur = new JLabel("Valeur");
		anneeValeur = new JLabel("Année Valeur");
		labelNumCommandeClient = new JLabel("N° Commande Client :");
		client = new JLabel("1");
		libelle2 = new JLabel("1");
		numCommandeClient = new JLabel("1");
		tva = new JLabel("TVA");

		client.setForeground(new Color(0,0,255));
		libelle2.setForeground(new Color(0,0,255));
		numCommandeClient.setForeground(new Color(0,0,255));
		
        jNumFacture = new JFormattedTextField(num);
        jNumFacture.setText("");
        jFournitures = new JFormattedTextField(nf);;
        jCout = new JFormattedTextField(nf);
        jPrefabrication = new JFormattedTextField(nf);
        jTotalHT = new JFormattedTextField(nf);
        jTotalTTC = new JFormattedTextField(nf);
        jValeur = new JCheckBox();
        jAnneeValeur = new JFormattedTextField(num);
        jTVA = new JFormattedTextField(nf);

        jPrecision = new JTextArea();
        
        jFournitures.setText("0,00");
        jCout.setText("0,00");
        jPrefabrication.setText("0,00");
        jTotalHT.setText("0,00");
        jTotalTTC.setText("0,00");
        
		jTotalDevise = new JFormattedTextField(nf);
        jTotalDevise.setText("0,00");
        jTotalDevise.setText("0,00");
        jTVA.setText("0,00");
        
        jTotalHT.setEditable(false);
        jTotalTTC.setEditable(false);
        jTotalDevise.setEditable(false);

        devises = new JComboBox<>();
        boxModePaiement = new JComboBox<>();
        ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
        calcul1 = new JButton(icon);
		calcul2 = new JButton(icon);
		calcul3 = new JButton(icon);
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");
        
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jDateEmission.setDateFormatString("dd/MM/yyyy");
        jDateEmission.setDate(new Date());
        jDateEcheance.setDateFormatString("dd/MM/yyyy");
        jDateEcheance.setDate(new Date());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        InsertDevises();
        valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
        devises.addItemListener(new ItemChange(this, "Factures"));

        jTotalHT.setBackground(new Color(204, 204, 204));
        jTotalTTC.setBackground(new Color(204, 204, 204));
        jTotalDevise.setBackground(new Color(204, 204, 204));
        
        jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
        
        devise.setText(devises.getSelectedItem().toString());
        
        this.getContentPane().add(jPanel1);
        jPanel1.setPreferredSize(new Dimension(760, 40));
        jPanel1.setBounds(10, 10, jPanel1.getPreferredSize().width, jPanel1.getPreferredSize().height);
        this.getContentPane().add(jPanel2);
        jPanel2.setPreferredSize(new Dimension(760, 140));
        jPanel2.setBounds(10, 60, jPanel2.getPreferredSize().width, jPanel2.getPreferredSize().height);
        this.getContentPane().add(jPanel3);
        jPanel3.setPreferredSize(new Dimension(760, 210));
        jPanel3.setBounds(10, 210, jPanel3.getPreferredSize().width, jPanel3.getPreferredSize().height);
        initPanel1();
        initPanel2();
        initPanel3();
        
        jPanel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke> emptySet());
		jPanel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke> emptySet());
		jPanel3.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke> emptySet());
		this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke> emptySet());
        ExecuteClick click = new ExecuteClick(this, "Factures");
        new ClickDroit(jNumFacture, true, true);
        new ClickDroit(jFournitures, true, true);
        new ClickDroit(jCout, true, true);
        new ClickDroit(jPrefabrication, true, true);
        new ClickDroit(jTotalHT, true, false);
        new ClickDroit(jTotalTTC, true, false);
        new ClickDroit(jTotalDevise, true, false);
        new ClickDroit(jPrecision, true, true);
        new ClickDroit(jAnneeValeur, true, true);
        new ClickDroit(jTVA, true, true);
		jNumFacture.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNumFacture.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jTVA.requestFocus();
			}
		});
		
		jTVA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jTVA.getActionMap().put("tab", new AbstractAction() {
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
				jAnneeValeur.requestFocus();
			}
		});
		
		jAnneeValeur.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAnneeValeur.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jDateEmission.requestFocus();
			}
		});
		
		jDateEmission.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jDateEmission.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jDateEcheance.requestFocus();
			}
		});
		
		jDateEcheance.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jDateEcheance.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				valider.requestFocus();
			}
		});
		
		valider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		valider.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jNumFacture.requestFocus();
			}
		});
		
		jFournitures.addFocusListener(new FocusPosition(jFournitures, 1,click));
		jCout.addFocusListener(new FocusPosition(jCout, 1, click));
		jPrefabrication.addFocusListener(new FocusPosition(jPrefabrication, 1, click));
		jTVA.addFocusListener(new FocusPosition(jTVA,  0, click));
		jFournitures.addKeyListener(new EcouteAction(jFournitures));
		jCout.addKeyListener(new EcouteAction(jCout));
		jPrefabrication.addKeyListener(new EcouteAction(jPrefabrication));
		jTVA.addKeyListener(new EcouteAction(jTVA));
        valider.setPreferredSize(new Dimension(80, 25));
        valider.setBounds(670, 425, valider.getPreferredSize().width, valider.getPreferredSize().height);
        this.getContentPane().add(valider);
        fermer.setPreferredSize(new Dimension(80, 25));
        fermer.setBounds(670, 460, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
        this.getContentPane().add(fermer);
        nouveau.setPreferredSize(new Dimension(90, 25));
        nouveau.setBounds(20, 440, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
        this.getContentPane().add(nouveau);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ActionFermer(this, fenetre));

	}
	
	private void initPanel1(){
		jPanel1.setLayout(null);
		numero.setPreferredSize(new Dimension(numero.getPreferredSize().width + 180, numero.getPreferredSize().height));
		numero.setBounds(120, 11,  numero.getPreferredSize().width, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 130;
		facture.setBounds(l, 11, facture.getPreferredSize().width, facture.getPreferredSize().height);
		jPanel1.add(facture);
		l += facture.getPreferredSize().width + 20;
		jNumFacture.setPreferredSize(new Dimension(80, 20));
		jNumFacture.setBounds(l, 10, jNumFacture.getPreferredSize().width, jNumFacture.getPreferredSize().height);
		jPanel1.add(jNumFacture);		
	}
	
	private void initPanel2(){
		jPanel2.setLayout(null);
		libelle.setBounds(60 - libelle.getPreferredSize().width, 11, libelle.getPreferredSize().width, libelle.getPreferredSize().height);
		jPanel2.add(libelle);
		libelle2.setBounds(65, 11, 300, libelle2.getPreferredSize().height);
		jPanel2.add(libelle2);
		int l = 535 + libelle2.getPreferredSize().width;
		tva.setBounds(l, 41, tva.getPreferredSize().width, tva.getPreferredSize().height);
		jPanel2.add(tva);
		jTVA.setPreferredSize(new Dimension(100, 20));
		jTVA.setBounds(l + 10 + tva.getPreferredSize().width, 41, jTVA.getPreferredSize().width, jTVA.getPreferredSize().height);
		jPanel2.add(jTVA);
		nameClient.setBounds(60 - nameClient.getPreferredSize().width, 41, nameClient.getPreferredSize().width, nameClient.getPreferredSize().height);
		jPanel2.add(nameClient);
		client.setBounds(65, 41, 300, client.getPreferredSize().height);
		jPanel2.add(client);
		labelNumCommandeClient.setBounds(452, 11, labelNumCommandeClient.getPreferredSize().width, labelNumCommandeClient.getPreferredSize().height);
		jPanel2.add(labelNumCommandeClient);
		numCommandeClient.setBounds(462 + labelNumCommandeClient.getPreferredSize().width, 11, 100, numCommandeClient.getPreferredSize().height);
		jPanel2.add(numCommandeClient);
		jFournitures.setPreferredSize(new Dimension(100, 20));
		jFournitures.setBounds(80, 70, jFournitures.getPreferredSize().width, jFournitures.getPreferredSize().height);
		jPanel2.add(jFournitures);
		fournitures.setBounds(75 - fournitures.getPreferredSize().width, 71, fournitures.getPreferredSize().width, fournitures.getPreferredSize().height);
		jPanel2.add(fournitures);
		l = 85 + jFournitures.getPreferredSize().width;
		euro1.setBounds(l, 71, euro1.getPreferredSize().width, euro1.getPreferredSize().height);
		jPanel2.add(euro1);
		l += euro1.getPreferredSize().width + 5;
		calcul1.setPreferredSize(new Dimension(19, 20));
		calcul1.setBounds(l, 70, calcul1.getPreferredSize().width, calcul1.getPreferredSize().height);
		jPanel2.add(calcul1);
		l += calcul1.getPreferredSize().width + 25;
		coutMO.setBounds(l , 71, coutMO.getPreferredSize().width, coutMO.getPreferredSize().height);
		jPanel2.add(coutMO);
		l += 5 + coutMO.getPreferredSize().width;
		jCout.setPreferredSize(new Dimension(100, 20));
		jCout.setBounds(l, 70, jCout.getPreferredSize().width, jCout.getPreferredSize().height);
		jPanel2.add(jCout);
		l += 5 + jCout.getPreferredSize().width;
		euro2.setBounds(l, 71, euro2.getPreferredSize().width, euro2.getPreferredSize().height);
		jPanel2.add(euro2);
		l += euro2.getPreferredSize().width + 5;
		calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(l, 70, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
		jPanel2.add(calcul2);
		l += calcul2.getPreferredSize().width + 25;
		prefabrication.setBounds(l, 71, prefabrication.getPreferredSize().width, prefabrication.getPreferredSize().height);
		jPanel2.add(prefabrication);
		l += 5 + prefabrication.getPreferredSize().width;
		jPrefabrication.setPreferredSize(new Dimension(100, 20));
		jPrefabrication.setBounds(l, 70, jPrefabrication.getPreferredSize().width, jPrefabrication.getPreferredSize().height);
		jPanel2.add(jPrefabrication);
		l += 5 + jPrefabrication.getPreferredSize().width;
		euro3.setBounds(l, 71, euro3.getPreferredSize().width, euro3.getPreferredSize().height);
		jPanel2.add(euro3);
		l += euro3.getPreferredSize().width + 5;
		calcul3.setPreferredSize(new Dimension(19, 20));
		calcul3.setBounds(l, 70, calcul3.getPreferredSize().width, calcul3.getPreferredSize().height);
		jPanel2.add(calcul3);
		
		jTotalHT.setPreferredSize(new Dimension(100, 20));
		jTotalHT.setBounds(80, 100, jTotalHT.getPreferredSize().width, jTotalHT.getPreferredSize().height);
		jPanel2.add(jTotalHT);
		totalHT.setBounds(75 - totalHT.getPreferredSize().width, 101, totalHT.getPreferredSize().width, totalHT.getPreferredSize().height);
		jPanel2.add(totalHT);
		l = 85 + jTotalHT.getPreferredSize().width;
		euro4.setBounds(l, 101, euro4.getPreferredSize().width, euro4.getPreferredSize().height);
		jPanel2.add(euro4);
		l += euro1.getPreferredSize().width + 25;
		totalTTC.setBounds(l , 101, totalTTC.getPreferredSize().width, totalTTC.getPreferredSize().height);
		jPanel2.add(totalTTC);
		l += 5 + totalTTC.getPreferredSize().width;
		jTotalTTC.setPreferredSize(new Dimension(100, 20));
		jTotalTTC.setBounds(l, 100, jTotalTTC.getPreferredSize().width, jTotalTTC.getPreferredSize().height);
		jPanel2.add(jTotalTTC);
		l += 5 + jTotalTTC.getPreferredSize().width;
		euro5.setBounds(l, 101, euro5.getPreferredSize().width, euro5.getPreferredSize().height);
		jPanel2.add(euro5);
		l += euro2.getPreferredSize().width + 54;
		totalDevise.setBounds(l, 101, totalDevise.getPreferredSize().width, totalDevise.getPreferredSize().height);
		jPanel2.add(totalDevise);
		l += 5 + totalDevise.getPreferredSize().width;
		jTotalDevise.setPreferredSize(new Dimension(100, 20));
		jTotalDevise.setBounds(l, 100, jTotalDevise.getPreferredSize().width, jTotalDevise.getPreferredSize().height);
		jPanel2.add(jTotalDevise);
		l += 5 + jTotalDevise.getPreferredSize().width;
		devise.setBounds(l, 101, devise.getPreferredSize().width, devise.getPreferredSize().height);
		jPanel2.add(devise);
		calcul1.addActionListener(new ActionCalculatrice(this, jFournitures, 1, "Factures"));
		calcul2.addActionListener(new ActionCalculatrice(this, jCout, 1, "Factures"));
		calcul3.addActionListener(new ActionCalculatrice(this, jPrefabrication, 1, "Factures"));
	}
	
	private void initPanel3(){
		jPanel3.setLayout(null);
		precision.setBounds(10, 11, precision.getPreferredSize().width, precision.getPreferredSize().height);
		jPanel3.add(precision);
		jPrecision.setPreferredSize(new Dimension(740, 90));
		jPrecision.setBounds(10, 30, jPrecision.getPreferredSize().width, jPrecision.getPreferredSize().height);
		jPrecision.setBorder(BorderFactory.createEtchedBorder());
		jPrecision.setLineWrap(true);
		jPrecision.setWrapStyleWord(true);
		jPanel3.add(jPrecision);
		
		modePaiement.setBounds(10, 141, modePaiement.getPreferredSize().width, modePaiement.getPreferredSize().height);
		jPanel3.add(modePaiement);
		boxModePaiement.setPreferredSize(new Dimension(180, 20));
		int l = modePaiement.getPreferredSize().width + 20;
		boxModePaiement.setBounds(l, 140, boxModePaiement.getPreferredSize().width, boxModePaiement.getPreferredSize().height);
		jPanel3.add(boxModePaiement);
		l += boxModePaiement.getPreferredSize().width + 50;
		valeur.setBounds(l, 141, valeur.getPreferredSize().width, valeur.getPreferredSize().height);
		jPanel3.add(valeur);
		l += valeur.getPreferredSize().width + 3;
		jValeur.setBounds(l, 141, jValeur.getPreferredSize().width, jValeur.getPreferredSize().height);
		jPanel3.add(jValeur);
		
		l += jValeur.getPreferredSize().width + 90;
		anneeValeur.setBounds(l, 141, anneeValeur.getPreferredSize().width, anneeValeur.getPreferredSize().height);
		jPanel3.add(anneeValeur);
		l += anneeValeur.getPreferredSize().width + 10;
		jAnneeValeur.setPreferredSize(new Dimension(50, 20));
		jAnneeValeur.setBounds(l, 141, jAnneeValeur.getPreferredSize().width, jAnneeValeur.getPreferredSize().height);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        jAnneeValeur.setText(year + "");
		jPanel3.add(jAnneeValeur);
		
		dateEmission.setBounds(32, 171, dateEmission.getPreferredSize().width, dateEmission.getPreferredSize().height);
		jPanel3.add(dateEmission);
		jDateEmission.setPreferredSize(new Dimension(100, 20));
		l = dateEmission.getPreferredSize().width + 42;
		jDateEmission.setBounds(l, 170, jDateEmission.getPreferredSize().width, jDateEmission.getPreferredSize().height);
		jPanel3.add(jDateEmission);
		l += jDateEmission.getPreferredSize().width + 82;
		dateEcheance.setBounds(l, 171, dateEcheance.getPreferredSize().width, dateEcheance.getPreferredSize().height);
		jPanel3.add(dateEcheance);
		l += dateEcheance.getPreferredSize().width + 10;
		jDateEcheance.setPreferredSize(new Dimension(100, 20));
		jDateEcheance.setBounds(l, 171, jDateEcheance.getPreferredSize().width, jDateEcheance.getPreferredSize().height);
		jPanel3.add(jDateEcheance);

		
	}
	
	protected void InsertDevises() {
		valeurDevises = donnees.devises();
		for (Entry<String, String[]> entry : valeurDevises.entrySet()) {
			devises.addItem(entry.getKey());
		}
		devises.setSelectedItem("EUR");
	}
	
	protected void InsertModesPaiements(String numClient) {
		modespaiements = donnees.modesPaiements(numClient);
		for (Entry<String, String[]> entry : modespaiements.entrySet()) {
			boxModePaiement.addItem(entry.getKey());
		}
	}
	public JComboBox<String> getDevises() {
		return devises;
	}

	public void setDevises(JComboBox<String> devises) {
		this.devises = devises;
	}

	public JComboBox<String> getBoxModePaiement() {
		return boxModePaiement;
	}

	public void setBoxModePaiement(JComboBox<String> boxModePaiement) {
		this.boxModePaiement = boxModePaiement;
	}

	public JTextArea getjPrecision() {
		return jPrecision;
	}

	public void setjPrecision(JTextArea jPrecision) {
		this.jPrecision = jPrecision;
	}

	public JLabel getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(JLabel modePaiement) {
		this.modePaiement = modePaiement;
	}

	public JLabel getAnneeValeur() {
		return anneeValeur;
	}

	public void setAnneeValeur(JLabel anneeValeur) {
		this.anneeValeur = anneeValeur;
	}

	public JFormattedTextField getjTotalDevise() {
		return jTotalDevise;
	}

	public void setjTotalDevise(JFormattedTextField jTotalDevise) {
		this.jTotalDevise = jTotalDevise;
	}

	public JFormattedTextField getjTotalTTC() {
		return jTotalTTC;
	}

	public void setjTotalTTC(JFormattedTextField jTotalTTC) {
		this.jTotalTTC = jTotalTTC;
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

	public JFormattedTextField getjTotalHT() {
		return jTotalHT;
	}

	public void setjTotalHT(JFormattedTextField jTotalHT) {
		this.jTotalHT = jTotalHT;
	}

	public JFormattedTextField getjNumFacture() {
		return jNumFacture;
	}

	public void setjNumFacture(JFormattedTextField jNumFacture) {
		this.jNumFacture = jNumFacture;
	}

	public JFormattedTextField getjAnneeValeur() {
		return jAnneeValeur;
	}

	public void setjAnneeValeur(JFormattedTextField jAnneeValeur) {
		this.jAnneeValeur = jAnneeValeur;
	}

	public JFormattedTextField getjTVA() {
		return jTVA;
	}

	public void setjTVA(JFormattedTextField jTVA) {
		this.jTVA = jTVA;
	}

	public JCheckBox getjValeur() {
		return jValeur;
	}

	public void setjValeur(JCheckBox jValeur) {
		this.jValeur = jValeur;
	}

	public JDateChooser getjDateEmission() {
		return jDateEmission;
	}

	public void setjDateEmission(JDateChooser jDateEmission) {
		this.jDateEmission = jDateEmission;
	}

	public JDateChooser getjDateEcheance() {
		return jDateEcheance;
	}

	public void setjDateEcheance(JDateChooser jDateEcheance) {
		this.jDateEcheance = jDateEcheance;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public HashMap<String, String[]> getModespaiements() {
		return modespaiements;
	}

	public void setModespaiements(HashMap<String, String[]> modespaiements) {
		this.modespaiements = modespaiements;
	}

	public double getValeurDevise() {
		return valeurDevise;
	}

	public void setValeurDevise(double valeurDevise) {
		this.valeurDevise = valeurDevise;
	}

	public HashMap<String, String[]> getValeurDevises() {
		return valeurDevises;
	}

	public void setValeurDevises(HashMap<String, String[]> valeurDevises) {
		this.valeurDevises = valeurDevises;
	}

	public String toString() {
		return valeurDevises.toString();
	}

	public Collection<String[]> values() {
		return valeurDevises.values();
	}

	public double getValeurTVA() {
		return valeurTVA;
	}

	public void setValeurTVA(double valeurTVA) {
		this.valeurTVA = valeurTVA;
	}
}