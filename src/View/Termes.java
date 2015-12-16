package View;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class Termes extends JFrame{
    private JButton calcul1, valider, fermer, calcul2, calcul3, nouveau;
    private JFormattedTextField jNumIndice;
    private JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalDevis;
    private JLabel numIndice, libelle, fournitures;
    private JLabel coutMO, totalDevisdevise, devise, nameClient, deviselabel;
    private JPanel jPanel1, jPanel2, jPanel3, JPanelTemps, jPanel6;
    private JSeparator jSeparator1;
    private JFormattedTextField jTotalDevisDevise, jHeureSite, jFournitures, jPrefabrication, jCout, jTotalDevis, jPrevu, jHeureAtelier, jTotalHeure, jCommande, jResteCommande, jNumCommande, jNumCommandeClient;
    private JTextField jLibelle;
    private JComboBox<String> devises;
    private double valeurDevise;
   // private Donnees donnees;
   // private Base base;
    private HashMap <String, String []> valeurjNumIndice;
    private Object [][] listClient;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Termes(){
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouveau Terme)");
		screenSize.width = 600;
		screenSize.height = 500;
		//this.setImage(new Image("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    
		NumberFormat num =  NumberFormat.getIntegerInstance();
        DecimalFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
	    
	    jPanel1 = new JPanel();
	    jPanel2 = new JPanel();
	    JPanelTemps = new JPanel();
	    jPanel6 = new JPanel();
        jPanel3 = new JPanel();
        	    
        numero = new JLabel("N° de Commande");
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
		jNumCommandeClient = new JFormattedTextField();
		
		jNumCommande = new JFormattedTextField(num);
        jNumCommande.setText("");
        jLibelle = new JTextField();
        jFournitures = new JFormattedTextField(nf);;
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
        
        jNumIndice = new JFormattedTextField();
        calcul1 = new JButton();
        calcul2 = new JButton();
        calcul3 = new JButton();
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");

        jSeparator1 = new JSeparator();
        
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        //InsertjNumIndice();
        //valeurDevise = Double.parseDouble((valeurjNumIndice.get(jNumIndice.getSelectedItem().toString()))[2]);
        //jNumIndice.addItemListener(new ItemChange(this));
        JPanelTemps.setBorder(BorderFactory.createTitledBorder("Temps"));
        JPanelTemps.setPreferredSize(new Dimension(350, 200));

        jTotalHeure.setBackground(new Color(204, 204, 204));
        jTotalDevis.setBackground(new Color(204, 204, 204));
        jResteCommande.setBackground(new Color(204, 204, 204));
        jTotalDevisDevise.setBackground(new Color(204, 204, 204));
        
        jPanel6.setBorder(BorderFactory.createTitledBorder("Montants"));
        jPanel6.setPreferredSize(new Dimension(350, 200));
        jPanel3.setBorder(BorderFactory.createTitledBorder("Achat Matières"));
        
       // devise.setText(jNumIndice.getSelectedItem().toString());
        
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void initPanel1(){
		jPanel1.setLayout(null);
		numero.setBounds(10, 11, numero.getPreferredSize().width, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 20;
		jNumCommande.setPreferredSize(new Dimension(80,20));
		jNumCommande.setBounds(l, 10, jNumCommande.getPreferredSize().width, jNumCommande.getPreferredSize().height);
		jPanel1.add(jNumCommande);
		l += jNumCommande.getPreferredSize().width + 15;

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

	}
	
	
	private static void createAndShowGUI() {
        new Termes();
    }
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
