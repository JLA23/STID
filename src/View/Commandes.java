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

public class Commandes extends JFrame{
    private JButton calcul1, valider, fermer, calcul2, calcul3, calcul4, calcul5, calcul6, calcul7, nouveau, search;
    private JComboBox<String> devises;
    private JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalDevis, hrsAtelier, prevu, hrsSite, hrs1, euro5;
    private JLabel hrs2, hrs3, totalHeures, commande, euro6, resteCommande, euro7, deviselabel, libelle, numClientLabel, fournitures;
    private JLabel coutMO, totalDevisdevise, devise, nameClient, numCommandeClient;
    private JPanel jPanel1, jPanel2, jPanel3, JPanelTemps, jPanel6;
    private JSeparator jSeparator1, jSeparator2;
    private JFormattedTextField jTotalDevisDevise, jHeureSite, jFournitures, jPrefabrication, jCout, jTotalDevis, jPrevu, jHeureAtelier, jTotalHeure, jCommande, jResteCommande, jNumDevis, jNumCommandeClient;
    private JTextField jLibelle;
    private JButton jDevis;
    private JCheckBox check;
    
    private double valeurDevise;
   // private Donnees donnees;
    private AutoComplete numClient;
   // private Base base;
    private HashMap <String, String []> valeurDevises;
    private Object [][] listClient;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Commandes(){
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouvelle Commande)");
		screenSize.width = 800;
		screenSize.height = 600;
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
        
        jDevis = new JButton("Devis");
        AutoCompleteModel model = new AutoCompleteModel();
        numClient = new AutoComplete(model);
	    
        numero = new JLabel("N� de Commande");
        deviselabel = new JLabel("Devise");
        libelle = new JLabel("Libell�");
        numClientLabel = new JLabel("N� Client");
        hrsAtelier = new JLabel("Heures d'Atelier");
        hrsSite = new JLabel("Heures sur Site");
        hrs1 = new JLabel("Hrs");
        hrs2 = new JLabel("Hrs");
        hrs3 = new JLabel("Hrs");
        totalHeures = new JLabel("Total des Heures");
        fournitures = new JLabel("Fournitures");
        coutMO = new JLabel("Co�t MO");
        prefabrication = new JLabel("Pr�fabrication");
        euro1 = new JLabel("EUR");
        euro2 = new JLabel("EUR");
        euro3 = new JLabel("EUR");
        euro4 = new JLabel("EUR");
        euro5 = new JLabel("EUR");
        euro6 = new JLabel("EUR");
        euro7 = new JLabel("EUR");
        totalDevis = new JLabel("Total Devis");
        prevu = new JLabel("Pr�vu");
        commande = new JLabel("Command�");
        resteCommande = new JLabel("Reste � commander");
        totalDevisdevise = new JLabel("Total (Devise)");
		devise = new JLabel();
		nameClient = new JLabel ("Client : ");
		numCommandeClient = new JLabel("N� C/Client");
		jNumCommandeClient = new JFormattedTextField();
		check = new JCheckBox("Assujettie COREM", false);
		
		jNumDevis = new JFormattedTextField(num);
        jNumDevis.setText("");
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
        
        devises = new JComboBox<>();
        
        calcul4 = new JButton();
        calcul6 = new JButton();
        calcul1 = new JButton();
        calcul2 = new JButton();
        calcul3 = new JButton();
        calcul5 = new JButton();
        calcul7 = new JButton();
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");
        search = new JButton("Chercher Client");
        
        jSeparator2 = new JSeparator();
        jSeparator1 = new JSeparator();
        
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        //InsertDevises();
        //valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
        //devises.addItemListener(new ItemChange(this));
        JPanelTemps.setBorder(BorderFactory.createTitledBorder("Temps"));
        JPanelTemps.setPreferredSize(new Dimension(350, 200));

        jTotalHeure.setBackground(new Color(204, 204, 204));
        jTotalDevis.setBackground(new Color(204, 204, 204));
        jResteCommande.setBackground(new Color(204, 204, 204));
        jTotalDevisDevise.setBackground(new Color(204, 204, 204));
        
        jPanel6.setBorder(BorderFactory.createTitledBorder("Montants"));
        jPanel6.setPreferredSize(new Dimension(350, 200));
        jPanel3.setBorder(BorderFactory.createTitledBorder("Achat Mati�res"));
        
       // devise.setText(devises.getSelectedItem().toString());
        
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void initPanel1(){
		jPanel1.setLayout(null);
		numero.setBounds(10, 11, numero.getPreferredSize().width, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 20;
		jNumDevis.setPreferredSize(new Dimension(80,20));
		jNumDevis.setBounds(l, 10, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
		jPanel1.add(jNumDevis);
		l += jNumDevis.getPreferredSize().width + 145;

		jDevis.setPreferredSize(new Dimension(80,25));
		jDevis.setBounds(l, 8, jDevis.getPreferredSize().width, jDevis.getPreferredSize().height);
		jPanel1.add(jDevis);
		l += jDevis.getPreferredSize().width + 180;
		deviselabel.setBounds(l, 11, deviselabel.getPreferredSize().width, deviselabel.getPreferredSize().height);
		jPanel1.add(deviselabel);
		l += deviselabel.getPreferredSize().width + 10;
		devises.setPreferredSize(new Dimension(100, 25));
		devises.setBounds(l, 7, devises.getPreferredSize().width, devises.getPreferredSize().height);
		jPanel1.add(devises);
		
	}
	
	private void initPanel2(){
		jPanel2.setLayout(null);
		jLibelle.setPreferredSize(new Dimension(500, 20));
		jLibelle.setBounds(70, 10, jLibelle.getPreferredSize().width, jLibelle.getPreferredSize().height);
		jPanel2.add(jLibelle);
		libelle.setBounds(60 - libelle.getPreferredSize().width, 11, libelle.getPreferredSize().width, libelle.getPreferredSize().height);
		jPanel2.add(libelle);
		check.setBounds(90 + jLibelle.getPreferredSize().width, 10, check.getPreferredSize().width, check.getPreferredSize().height);
		jPanel2.add(check);
		numClient.setPreferredSize(new Dimension(80, 20));
		numClient.setBounds(70, 40, numClient.getPreferredSize().width, numClient.getPreferredSize().height);
		jPanel2.add(numClient);
		numClientLabel.setBounds(60 - numClientLabel.getPreferredSize().width, 41, numClientLabel.getPreferredSize().width, numClientLabel.getPreferredSize().height);
		jPanel2.add(numClientLabel);
		search.setPreferredSize(new Dimension(125, 25));
		search.setBounds(80 + numClient.getPreferredSize().width, 37, search.getPreferredSize().width, search.getPreferredSize().height);
		jPanel2.add(search);
		nameClient.setBounds(90 + search.getPreferredSize().width + numClient.getPreferredSize().width, 41, nameClient.getPreferredSize().width, nameClient.getPreferredSize().height);
		jPanel2.add(nameClient);
		numCommandeClient.setBounds(520, 41, numCommandeClient.getPreferredSize().width, numCommandeClient.getPreferredSize().height);
		jPanel2.add(numCommandeClient);
		jNumCommandeClient.setPreferredSize(new Dimension(150, 20));
		jNumCommandeClient.setBounds(530 + numCommandeClient.getPreferredSize().width, 40, jNumCommandeClient.getPreferredSize().width, jNumCommandeClient.getPreferredSize().height);
		jPanel2.add(jNumCommandeClient);
		
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
	
	private void initPanelTemps(){
		JPanelTemps.setLayout(null);
		jHeureAtelier.setPreferredSize(new Dimension(100, 20));
		jHeureAtelier.setBounds(125, 50, jHeureAtelier.getPreferredSize().width, jHeureAtelier.getPreferredSize().height);
		JPanelTemps.add(jHeureAtelier);
		hrsAtelier.setBounds(115 - hrsAtelier.getPreferredSize().width, 51, hrsAtelier.getPreferredSize().width, hrsAtelier.getPreferredSize().height);
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
		hrsSite.setBounds(115 - hrsSite.getPreferredSize().width, 91, hrsSite.getPreferredSize().width, hrsSite.getPreferredSize().height);
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
		totalHeures.setBounds(115 - totalHeures.getPreferredSize().width, 156, totalHeures.getPreferredSize().width, totalHeures.getPreferredSize().height);
		JPanelTemps.add(totalHeures);
		l = 135 + jTotalHeure.getPreferredSize().width;
		hrs3.setBounds(l, 156, hrs3.getPreferredSize().width, hrs3.getPreferredSize().height);
		JPanelTemps.add(hrs3);
	}
	
	private void initAchats(){
		jPanel3.setLayout(null);
		jPrevu.setPreferredSize(new Dimension(100, 20));
		jPrevu.setBounds(125, 30, jPrevu.getPreferredSize().width, jPrevu.getPreferredSize().height);
		jPanel3.add(jPrevu);
		prevu.setBounds(115 - prevu.getPreferredSize().width, 31, prevu.getPreferredSize().width, prevu.getPreferredSize().height);
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
		commande.setBounds(485 - commande.getPreferredSize().width, 31, commande.getPreferredSize().width, commande.getPreferredSize().height);
		jPanel3.add(commande);
		l = 505 + jCommande.getPreferredSize().width;
		euro6.setBounds(l, 31, euro6.getPreferredSize().width, euro6.getPreferredSize().height);
		jPanel3.add(euro6);
		l += euro6.getPreferredSize().width + 20;
		calcul7.setPreferredSize(new Dimension(19, 20));
		calcul7.setBounds(l, 30, calcul7.getPreferredSize().width, calcul7.getPreferredSize().height);
		jPanel3.add(calcul7);
		
		jResteCommande.setPreferredSize(new Dimension(100, 20));
		jResteCommande.setBounds(310, 80, jResteCommande.getPreferredSize().width, jResteCommande.getPreferredSize().height);
		jPanel3.add(jResteCommande);
		resteCommande.setBounds(300 - resteCommande.getPreferredSize().width, 81, resteCommande.getPreferredSize().width, resteCommande.getPreferredSize().height);
		jPanel3.add(resteCommande);
		l = 320 + jResteCommande.getPreferredSize().width;
		euro7.setBounds(l, 81, euro7.getPreferredSize().width, euro7.getPreferredSize().height);
		jPanel3.add(euro7);
	
	}
	
	private static void createAndShowGUI() {
        new Commandes();
    }
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}