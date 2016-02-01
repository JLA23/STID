package View.Termes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import BDD.Base;
import Controller.ActionCalculatrice;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Controller.FocusPosition;
import Controller.ItemChange;
import Controller.ExecuteClick;
import Model.Donnees;
import View.Options.ClickDroit;


public class Termes extends JFrame{
    protected JButton calcul1, valider, fermer, calcul2, calcul3, nouveau;
    protected JFormattedTextField jNumIndice;
    protected JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalDevis;
    protected JLabel numIndice, libelle, fournitures;
    protected JLabel coutMO, totalDevisdevise, devise, nameClient, deviselabel;
    protected JPanel jPanel1, jPanel2, jPanel6;
    protected JSeparator jSeparator1;
    protected JFormattedTextField jTotalDevisDevise, jFournitures, jPrefabrication, jCout, jTotalDevis, jNumCommande, jNumCommandeClient;
    protected JTextField jLibelle;
    protected JComboBox<String> devises;
    protected double valeurDevise;
    protected HashMap <String, String []> valeurDevises;
    protected Donnees donnees;
    protected Base base;
    protected HashMap <String, String []> valeurjNumIndice;
    protected Object [][] listClient;
    protected JFrame fenetre;
    protected String numeroIndice, numeroClient, numeroCommande;

	private static final long serialVersionUID = 1L;
	protected Dimension screenSize = new Dimension();
	
	@SuppressWarnings("unchecked")
	public Termes(Base bdd, JFrame frame){
		this.fenetre = frame;
		fenetre.setEnabled(false);
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouveau Terme)");
		screenSize.width = 600;
		screenSize.height = 500;
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    this.base = bdd;
	    donnees = new Donnees(base);
        DecimalFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
	    
	    jPanel1 = new JPanel();
	    jPanel2 = new JPanel();
	    jPanel6 = new JPanel();
        	    
        numero = new JLabel("N° de Commande : ");
        numIndice = new JLabel("N° Indice");
        libelle = new JLabel("Libellé");
        fournitures = new JLabel("Fournitures");
        coutMO = new JLabel("Coût MO");
        prefabrication = new JLabel("Préfabrication");
        euro1 = new JLabel("EUR");
        euro2 = new JLabel("EUR");
        euro3 = new JLabel("EUR");
        euro4 = new JLabel("EUR");
        totalDevis = new JLabel("Total Devis");
        totalDevisdevise = new JLabel("Total (Devise)");
		devise = new JLabel();
		nameClient = new JLabel ("Client : ");
		deviselabel = new JLabel("Devise");
		devises = new JComboBox<String>();
		
        jLibelle = new JTextField();
        jFournitures = new JFormattedTextField(nf);;
        jCout = new JFormattedTextField(nf);
        jPrefabrication = new JFormattedTextField(nf);
        jTotalDevis = new JFormattedTextField(nf);
        
        jFournitures.setText("0,00");
        jCout.setText("0,00");
        jPrefabrication.setText("0,00");
        jTotalDevis.setText("0,00");
        
		jTotalDevisDevise = new JFormattedTextField(nf);
        jTotalDevisDevise.setText("0,00");
        
        jTotalDevis.setEditable(false);
        jTotalDevisDevise.setEditable(false);
        
        jNumIndice = new JFormattedTextField();
        ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
        calcul1 = new JButton(icon);
		calcul2 = new JButton(icon);
		calcul3 = new JButton(icon);
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");

        jSeparator1 = new JSeparator();
        
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        InsertDevises();
        valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);

        jTotalDevis.setBackground(new Color(204, 204, 204));
        jTotalDevisDevise.setBackground(new Color(204, 204, 204));
        
        jPanel6.setBorder(BorderFactory.createTitledBorder("Montants"));
        jPanel6.setPreferredSize(new Dimension(350, 200));
    	
        jPanel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jPanel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jPanel6.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		ExecuteClick click = new ExecuteClick(this, "Termes");
		
		jFournitures.addMouseListener(new FocusPosition(jFournitures, 1, click));
		jCout.addMouseListener(new FocusPosition(jCout, 1, click));
		jPrefabrication.addMouseListener(new FocusPosition(jPrefabrication, 1, click));
		jFournitures.addKeyListener(new EcouteAction(jFournitures));
		jCout.addKeyListener(new EcouteAction(jCout));
		jPrefabrication.addKeyListener(new EcouteAction(jPrefabrication));
        
        devise.setText(devises.getSelectedItem().toString());
        devises.addItemListener(new ItemChange(this, "Termes"));
        
    	new ClickDroit(jLibelle, true, true);
		jNumIndice.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNumIndice.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jLibelle.requestFocus();
			}
		});
		jLibelle.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jLibelle.getActionMap().put("tab", new AbstractAction() {
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
				valider.requestFocus();
			}
		});
		valider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		valider.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jNumIndice.requestFocus();
			}
		});
		
		jFournitures.addFocusListener(new FocusPosition(jFournitures, 1,click));
		jCout.addFocusListener(new FocusPosition(jCout, 1, click));
		jPrefabrication.addFocusListener(new FocusPosition(jPrefabrication, 1, click));
        
        this.getContentPane().add(jPanel1);
        jPanel1.setPreferredSize(new Dimension(560, 40));
        jPanel1.setBounds(10, 10, jPanel1.getPreferredSize().width, jPanel1.getPreferredSize().height);
        this.getContentPane().add(jPanel2);
        jPanel2.setPreferredSize(new Dimension(560, 330));
        jPanel2.setBounds(10, 60, jPanel2.getPreferredSize().width, jPanel2.getPreferredSize().height);
        initPanel1();
        initPanel2();
        valider.setPreferredSize(new Dimension(80, 25));
        valider.setBounds(470, 395, valider.getPreferredSize().width, valider.getPreferredSize().height);
        this.getContentPane().add(valider);
        fermer.setPreferredSize(new Dimension(80, 25));
        fermer.setBounds(470, 430, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
        this.getContentPane().add(fermer);
        nouveau.setPreferredSize(new Dimension(90, 25));
        nouveau.setBounds(20, 410, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
        this.getContentPane().add(nouveau);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ActionFermer(this, fenetre));
	}
	
	private void initPanel1(){
		jPanel1.setLayout(null);
		numero.setBounds(10, 11, numero.getPreferredSize().width + 60, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 70;

		numIndice.setBounds(l, 11, numIndice.getPreferredSize().width, numIndice.getPreferredSize().height);
		jPanel1.add(numIndice);
		l += numIndice.getPreferredSize().width + 10;
		jNumIndice.setPreferredSize(new Dimension(100, 20));
		jNumIndice.setBounds(l, 10, jNumIndice.getPreferredSize().width, jNumIndice.getPreferredSize().height);
		jPanel1.add(jNumIndice);
		
		l += jNumIndice.getPreferredSize().width + 15;
		deviselabel.setBounds(l, 11, deviselabel.getPreferredSize().width, deviselabel.getPreferredSize().height);
		jPanel1.add(deviselabel);
		l += deviselabel.getPreferredSize().width + 10;
		devises.setPreferredSize(new Dimension(100, 25));
		devises.setBounds(l, 7, devises.getPreferredSize().width, devises.getPreferredSize().height);
		jPanel1.add(devises);
		
	}
	
	private void initPanel2(){
		jPanel2.setLayout(null);
		jLibelle.setPreferredSize(new Dimension(400, 20));
		jLibelle.setBounds(70, 10, jLibelle.getPreferredSize().width, jLibelle.getPreferredSize().height);
		jPanel2.add(jLibelle);
		libelle.setBounds(60 - libelle.getPreferredSize().width, 11, libelle.getPreferredSize().width, libelle.getPreferredSize().height);
		jPanel2.add(libelle);
		nameClient.setBounds(25, 41, nameClient.getPreferredSize().width, nameClient.getPreferredSize().height);
		jPanel2.add(nameClient);
		
		jPanel6.setPreferredSize(new Dimension(350, 230));
		jPanel6.setBounds(100, 65, jPanel6.getPreferredSize().width, jPanel6.getPreferredSize().height);
		jPanel2.add(jPanel6);
		initPanelMontant();
	}
	
	private void initPanelMontant(){
		jPanel6.setLayout(null);
		jFournitures.setPreferredSize(new Dimension(100, 20));
		jFournitures.setBounds(125, 40, jFournitures.getPreferredSize().width, jFournitures.getPreferredSize().height);
		jPanel6.add(jFournitures);
		fournitures.setBounds(115 - fournitures.getPreferredSize().width, 41, fournitures.getPreferredSize().width, fournitures.getPreferredSize().height);
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
		coutMO.setBounds(115 - coutMO.getPreferredSize().width, 71, coutMO.getPreferredSize().width, coutMO.getPreferredSize().height);
		jPanel6.add(coutMO);
		l = 135 + jCout.getPreferredSize().width;
		euro2.setBounds(l, 71, euro2.getPreferredSize().width, euro2.getPreferredSize().height);
		jPanel6.add(euro2);
		l += euro2.getPreferredSize().width + 20;
		calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(l, 70, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
		jPanel6.add(calcul2);
		
		jPrefabrication.setPreferredSize(new Dimension(100, 20));
		jPrefabrication.setBounds(125, 100, jPrefabrication.getPreferredSize().width, jPrefabrication.getPreferredSize().height);
		jPanel6.add(jPrefabrication);
		prefabrication.setBounds(115 - prefabrication.getPreferredSize().width, 101, prefabrication.getPreferredSize().width, prefabrication.getPreferredSize().height);
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
		totalDevis.setBounds(115 - totalDevis.getPreferredSize().width, 141, totalDevis.getPreferredSize().width, totalDevis.getPreferredSize().height);
		jPanel6.add(totalDevis);
		l = 135 + jTotalDevis.getPreferredSize().width;
		euro4.setBounds(l, 141, euro4.getPreferredSize().width, euro4.getPreferredSize().height);
		jPanel6.add(euro4);
		
		jTotalDevisDevise.setPreferredSize(new Dimension(100, 20));
		jTotalDevisDevise.setBounds(125, 170, jTotalDevisDevise.getPreferredSize().width, jTotalDevisDevise.getPreferredSize().height);
		jPanel6.add(jTotalDevisDevise);
		totalDevisdevise.setBounds(115 - totalDevisdevise.getPreferredSize().width, 171, totalDevisdevise.getPreferredSize().width, totalDevis.getPreferredSize().height);
		jPanel6.add(totalDevisdevise);
		l = 135 + jTotalDevisDevise.getPreferredSize().width;
		devise.setBounds(l, 171, devise.getPreferredSize().width, devise.getPreferredSize().height);
		jPanel6.add(devise);
		
		calcul1.addActionListener(new ActionCalculatrice(jFournitures));
		calcul2.addActionListener(new ActionCalculatrice(jCout));
		calcul3.addActionListener(new ActionCalculatrice(jPrefabrication));
		
		jFournitures.addKeyListener(new EcouteAction(jFournitures));
		jCout.addKeyListener(new EcouteAction(jCout));
		jPrefabrication.addKeyListener(new EcouteAction(jPrefabrication));

	}
	
	protected void InsertDevises() {
		valeurDevises = donnees.devises();
		for (Entry<String, String[]> entry : valeurDevises.entrySet()) {
			devises.addItem(entry.getKey());
		}
		devises.setSelectedItem("EUR");
	}
	
	public double getValeurDevise() {
		return valeurDevise;
	}

	public void setValeurDevise(double valeurDevise) {
		this.valeurDevise = valeurDevise;
	}

	public JFormattedTextField getjNumIndice() {
		return jNumIndice;
	}

	public void setjNumIndice(JFormattedTextField jNumIndice) {
		this.jNumIndice = jNumIndice;
	}

	public JFormattedTextField getjTotalDevisDevise() {
		return jTotalDevisDevise;
	}

	public void setjTotalDevisDevise(JFormattedTextField jTotalDevisDevise) {
		this.jTotalDevisDevise = jTotalDevisDevise;
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

	public JFormattedTextField getjNumCommande() {
		return jNumCommande;
	}

	public void setjNumCommande(JFormattedTextField jNumCommande) {
		this.jNumCommande = jNumCommande;
	}

	public JLabel getDevise() {
		return devise;
	}

	public void setDevise(JLabel devise) {
		this.devise = devise;
	}

	public JComboBox<String> getDevises() {
		return devises;
	}

	public void setDevises(JComboBox<String> devises) {
		this.devises = devises;
	}

	public HashMap<String, String[]> getValeurDevises() {
		return valeurDevises;
	}

	public void setValeurDevises(HashMap<String, String[]> valeurDevises) {
		this.valeurDevises = valeurDevises;
	}

	public JTextField getjLibelle() {
		return jLibelle;
	}

	public void setjLibelle(JTextField jLibelle) {
		this.jLibelle = jLibelle;
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

	public Object[][] getListClient() {
		return listClient;
	}

	public void setListClient(Object[][] listClient) {
		this.listClient = listClient;
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public JLabel getNumIndice() {
		return numIndice;
	}

	public void setNumIndice(JLabel numIndice) {
		this.numIndice = numIndice;
	}

	public String getNumeroClient() {
		return numeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	public String getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public String getNumeroIndice() {
		return numeroIndice;
	}

	public void setNumeroIndice(String numeroIndice) {
		this.numeroIndice = numeroIndice;
	}
	
}
