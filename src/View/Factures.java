package View;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class Factures extends JFrame{
    private JButton calcul1, valider, fermer, calcul2, calcul3, nouveau;
    private JComboBox<String> devises, boxModePaiement;
    private JTextArea jPrecision;
    private JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalHT, euro5;
    private JLabel libelle, fournitures, precision, modePaiement, dateEmission, dateEcheance, valeur, anneeValeur;
    private JLabel coutMO, totalDevise, devise, nameClient, facture, totalTTC, client, libelle2, labelNumCommandeClient, numCommandeClient;
    private JPanel jPanel1, jPanel2, jPanel3;
    private JFormattedTextField jTotalDevise, jTotalTTC, jFournitures, jPrefabrication, jCout, jTotalHT, jNumComIndice, jNumFacture, jValeur, jAnneeValeur;
    private JDateChooser jDateEmission, jDateEcheance;
    private double valeurDevise;
   // private Donnees donnees;
    private AutoComplete numClient;
   // private Base base;
    private HashMap <String, String []> valeurDevises;
    private Object [][] listClient;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Factures(){
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouvelle Facture)");
		screenSize.width = 800;
		screenSize.height = 530;
		//this.setImage(new Image("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    
		NumberFormat num =  NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
        DecimalFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
	    
	    jPanel1 = new JPanel();
	    jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        
        jDateEmission = new JDateChooser();
        jDateEcheance = new JDateChooser();
        AutoCompleteModel model = new AutoCompleteModel();
        numClient = new AutoComplete(model);
	    
        numero = new JLabel("N° Commande / Indice");
        facture = new JLabel("N° Facture");
        libelle = new JLabel("Libellé");
        nameClient = new JLabel("N° Client");
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
		nameClient = new JLabel ("Client");
		precision = new JLabel("Précisions sur Lettre");
		dateEmission = new JLabel("Date Emission");
		dateEcheance = new JLabel("Date Echéance");
		modePaiement = new JLabel("Mode de Paiement");
		valeur = new JLabel("Valeur");
		anneeValeur = new JLabel("Année Valeur");
		labelNumCommandeClient = new JLabel("N° Commande Client");
		client = new JLabel("");
		libelle2 = new JLabel("");
		numCommandeClient = new JLabel("");
		
		client.setForeground(new Color(0,0,255));
		libelle2.setForeground(new Color(0,0,255));
		numCommandeClient.setForeground(new Color(0,0,255));
		
		jNumComIndice = new JFormattedTextField(num);
        jNumComIndice.setText("");
        jNumFacture = new JFormattedTextField(num);
        jNumFacture.setText("");
        jFournitures = new JFormattedTextField(nf);;
        jCout = new JFormattedTextField(nf);
        jPrefabrication = new JFormattedTextField(nf);
        jTotalHT = new JFormattedTextField(nf);
        jTotalTTC = new JFormattedTextField(nf);
        jValeur = new JFormattedTextField(num);
        jAnneeValeur = new JFormattedTextField(num);

        jPrecision = new JTextArea();
        
        jFournitures.setText("0,00");
        jCout.setText("0,00");
        jPrefabrication.setText("0,00");
        jTotalHT.setText("0,00");
        jTotalTTC.setText("0,00");
        
		jTotalDevise = new JFormattedTextField(nf);
        jTotalDevise.setText("0,00");
        
        jTotalHT.setEditable(false);
        jTotalTTC.setEditable(false);
        jTotalDevise.setEditable(false);
        
        devises = new JComboBox<>();
        boxModePaiement = new JComboBox<>();
        
        calcul1 = new JButton();
        calcul2 = new JButton();
        calcul3 = new JButton();
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");
        
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jDateEmission.setDateFormatString("dd/MM/yyyy");
        jDateEmission.setDate(new Date());
        jDateEcheance.setDateFormatString("dd/MM/yyyy");
        jDateEcheance.setDate(new Date());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        //InsertDevises();
        //valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
        //devises.addItemListener(new ItemChange(this));

        jTotalHT.setBackground(new Color(204, 204, 204));
        jTotalTTC.setBackground(new Color(204, 204, 204));
        jTotalDevise.setBackground(new Color(204, 204, 204));
        
        jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
        
       // devise.setText(devises.getSelectedItem().toString());
        
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
        valider.setPreferredSize(new Dimension(80, 25));
        valider.setBounds(670, 425, valider.getPreferredSize().width, valider.getPreferredSize().height);
        this.getContentPane().add(valider);
        fermer.setPreferredSize(new Dimension(80, 25));
        fermer.setBounds(670, 460, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
        this.getContentPane().add(fermer);
        nouveau.setPreferredSize(new Dimension(90, 25));
        nouveau.setBounds(20, 440, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
        this.getContentPane().add(nouveau);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void initPanel1(){
		jPanel1.setLayout(null);
		numero.setBounds(120, 11, numero.getPreferredSize().width, numero.getPreferredSize().height);
		jPanel1.add(numero);
		int l = numero.getPreferredSize().width + 130;
		jNumComIndice.setPreferredSize(new Dimension(80,20));
		jNumComIndice.setBounds(l, 10, jNumComIndice.getPreferredSize().width, jNumComIndice.getPreferredSize().height);
		jPanel1.add(jNumComIndice);
		l += jNumComIndice.getPreferredSize().width + 100;
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
		nameClient.setBounds(60 - nameClient.getPreferredSize().width, 41, nameClient.getPreferredSize().width, nameClient.getPreferredSize().height);
		jPanel2.add(nameClient);
		client.setBounds(65, 41, 300, client.getPreferredSize().height);
		jPanel2.add(client);
		labelNumCommandeClient.setBounds(452, 41, labelNumCommandeClient.getPreferredSize().width, labelNumCommandeClient.getPreferredSize().height);
		jPanel2.add(labelNumCommandeClient);
		numCommandeClient.setBounds(462 + labelNumCommandeClient.getPreferredSize().width, 41, 100, numCommandeClient.getPreferredSize().height);
		jPanel2.add(numCommandeClient);
		jFournitures.setPreferredSize(new Dimension(100, 20));
		jFournitures.setBounds(80, 70, jFournitures.getPreferredSize().width, jFournitures.getPreferredSize().height);
		jPanel2.add(jFournitures);
		fournitures.setBounds(75 - fournitures.getPreferredSize().width, 71, fournitures.getPreferredSize().width, fournitures.getPreferredSize().height);
		jPanel2.add(fournitures);
		int l = 85 + jFournitures.getPreferredSize().width;
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
		l += valeur.getPreferredSize().width + 10;
		jValeur.setPreferredSize(new Dimension(30, 20));
		jValeur.setBounds(l, 141, jValeur.getPreferredSize().width, jValeur.getPreferredSize().height);
		jPanel3.add(jValeur);
		
		l += jValeur.getPreferredSize().width + 50;
		anneeValeur.setBounds(l, 141, anneeValeur.getPreferredSize().width, anneeValeur.getPreferredSize().height);
		jPanel3.add(anneeValeur);
		l += anneeValeur.getPreferredSize().width + 10;
		jAnneeValeur.setPreferredSize(new Dimension(50, 20));
		jAnneeValeur.setBounds(l, 141, jAnneeValeur.getPreferredSize().width, jAnneeValeur.getPreferredSize().height);
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
	
	
	private static void createAndShowGUI() {
        new Factures();
    }
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
