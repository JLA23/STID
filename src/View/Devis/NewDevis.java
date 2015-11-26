package View.Devis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
import BDD.Base;
import Controller.Donnees;
import View.Calculatrice.Calculatrice;
import View.Clients.SearchClient;
import View.Options.ClickDroit;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class NewDevis extends JFrame{
	
    private JButton calcul1, valider, fermer, newClient, calcul2, calcul3, calcul4, calcul5, calcul6, calcul7, nouveau, search;
    private JComboBox<String> devises;
    private JLabel numero, prefabrication, euro1, euro2, euro3, euro4, totalDevis, hrsAtelier, prevu, hrsSite, hrs1, dateLabel, euro5;
    private JLabel hrs2, hrs3, totalHeures, commande, euro6, resteCommande, euro7, deviselabel, libelle, numClientLabel, fournitures;
    private JLabel coutMO, totalDevisdevise, devise, nameClient;
    private JPanel jPanel1, jPanel2, jPanel3, JPanelTemps, jPanel6;
    private JSeparator jSeparator1, jSeparator2;
    private JFormattedTextField jTotalDevisDevise, jHeureSite, jFournitures, jCout, jPrefabrication, jTotalDevis, jPrevu, jHeureAtelier, jTotalHeure, jCommande, jResteCommande, jNumDevis;
    private JTextField jLibelle;
    private JDateChooser jDate;
    private double valeurDevise;
    private Donnees donnees;
    private AutoComplete numClient;
    private Base base;
    private HashMap <String, String []> valeurDevises;
    private Object [][] listClient;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public NewDevis(Base bdd){
		
		this.base= bdd;
		NumberFormat num =  NumberFormat.getIntegerInstance();
        NumberFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
		donnees = new Donnees(bdd);
		int nbDevis = donnees.newNumDevis() + 1;
        AutoCompleteModel model = new AutoCompleteModel();
        model.addAll(listClient());
        numClient = new AutoComplete(model);
		
		this.setTitle("STID Gestion 2.0 (Nouveau Devis)");
		screenSize.width = 800;
		screenSize.height = 600;
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
		
	    ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
	    jPanel1 = new JPanel();
	    jPanel2 = new JPanel();
	    JPanelTemps = new JPanel();
	    jPanel6 = new JPanel();
        jPanel3 = new JPanel();
	    
	    jDate = new JDateChooser();
	    
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
		nameClient = new JLabel ("Client : ");
        
        jNumDevis = new JFormattedTextField(num);
        jNumDevis.setText(nbDevis + "");
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
        
        new ClickDroit(jFournitures, true, true);
        new ClickDroit(jCout, true, true);
        new ClickDroit(jPrefabrication, true, true);
        new ClickDroit(jTotalDevis, true, false);
        new ClickDroit(jHeureSite, true, true);
        new ClickDroit(jHeureAtelier, true, true);
        new ClickDroit(jTotalHeure, true, false);
        new ClickDroit(jResteCommande, true, false);
        new ClickDroit(jTotalDevisDevise, true, false);
        new ClickDroit(jPrevu, true, true);
        new ClickDroit(jCommande, true, true);
        
        jTotalDevis.setEditable(false);
        jTotalHeure.setEditable(false);
        jResteCommande.setEditable(false);
        jTotalDevisDevise.setEditable(false);
        
        devises = new JComboBox<>();
        
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
        
        valider.addActionListener(new ActionValider(this));
        
        search.addActionListener(new ActionSearch(bdd, numClient, this));
        
        numClient.getZoneTexte().addFocusListener(new FocusClient(this));
        
        jFournitures.addFocusListener(new FocusPosition(jFournitures, 1));
        jCout.addFocusListener(new FocusPosition(jCout, 1));
        jPrefabrication.addFocusListener(new FocusPosition(jPrefabrication, 1));
        jHeureSite.addFocusListener(new FocusPosition(jHeureSite, 2));
        jHeureAtelier.addFocusListener(new FocusPosition(jHeureAtelier, 2));
        jPrevu.addFocusListener(new FocusPosition(jPrevu, 3));
        jCommande.addFocusListener(new FocusPosition(jCommande, 3));
        
        jFournitures.addKeyListener(new EcouteAction());
        jCout.addKeyListener(new EcouteAction());
        jPrefabrication.addKeyListener(new EcouteAction());
        jHeureSite.addKeyListener(new EcouteAction());
        jHeureAtelier.addKeyListener(new EcouteAction());
        jPrevu.addKeyListener(new EcouteAction());
        jCommande.addKeyListener(new EcouteAction());
        jFournitures.addKeyListener(new EcouteAction());
        
        calcul1.addActionListener(new ActionCalculatrice(jFournitures, this));
        calcul2.addActionListener(new ActionCalculatrice(jCout, this));
        calcul3.addActionListener(new ActionCalculatrice(jPrefabrication, this));
        calcul4.addActionListener(new ActionCalculatrice(jHeureAtelier, this));
        calcul5.addActionListener(new ActionCalculatrice(jPrevu, this));
        calcul6.addActionListener(new ActionCalculatrice(jHeureSite, this));
        calcul7.addActionListener(new ActionCalculatrice(jCommande, this));
        
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

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(numero)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNumDevis, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(dateLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDate, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deviselabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(devises, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(numero, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addComponent(jNumDevis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deviselabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(devises, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout JPanelTempsLayout = new GroupLayout(JPanelTemps);
        JPanelTemps.setLayout(JPanelTempsLayout);
        JPanelTempsLayout.setHorizontalGroup(
            JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(JPanelTempsLayout.createSequentialGroup()
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(JPanelTempsLayout.createSequentialGroup()
                        .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelTempsLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(hrsAtelier)
                                    .addComponent(hrsSite))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(JPanelTempsLayout.createSequentialGroup()
                                        .addComponent(jHeureSite, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hrs1))
                                    .addGroup(JPanelTempsLayout.createSequentialGroup()
                                        .addComponent(jHeureAtelier, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hrs2)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(calcul6 , GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calcul4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JPanelTempsLayout.createSequentialGroup()
                            	.addGap(10, 10, 10)
                            	.addComponent(totalHeures)
                                .addGap(16, 16, 16)
                                .addComponent(jTotalHeure, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hrs3)))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPanelTempsLayout.setVerticalGroup(
            JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(JPanelTempsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jHeureAtelier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsAtelier)
                    .addComponent(hrs2))
                .addGap(44, 44, 44)
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jHeureSite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsSite)
                    .addComponent(hrs1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTotalHeure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrs3)
                    .addComponent(totalHeures))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(fournitures)
                            .addComponent(coutMO)
                            .addComponent(prefabrication))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPrefabrication, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(euro3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jFournitures, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(euro1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jCout, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(euro2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(calcul3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                            .addComponent(calcul1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                            .addComponent(calcul2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(totalDevis)
                .addGap(20, 20, 20)
                .addComponent(jTotalDevis, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(euro4)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(totalDevisdevise)
                    .addGap(20, 20, 20)
                    .addComponent(jTotalDevisDevise, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(devise)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jFournitures, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fournitures)
                    .addComponent(euro1))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(coutMO)
                    .addComponent(euro2))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPrefabrication, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefabrication)
                    .addComponent(euro3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTotalDevis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(euro4)
                    .addComponent(totalDevis))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jTotalDevisDevise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(devise)
                        .addComponent(totalDevisdevise))));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                    	.addGap(180, 180, 180)
                        .addComponent(resteCommande)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(jResteCommande, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(euro7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    	.addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                    	.addComponent(prevu)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    	.addGap(17, 17, 17)
                        .addComponent(jPrevu, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED
                        .addComponent(euro5)
                        .addGap(10, 10, 10)
                      //  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcul5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(commande)
                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        .addComponent(jCommande, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        //.addGap(20, 20, 20)
                        .addComponent(euro6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcul7, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        )
                        )));
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPrevu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevu)
                    .addComponent(euro5)
                    .addComponent(jCommande, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(commande)
                    .addComponent(euro6))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jResteCommande, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(euro7)
                    .addComponent(resteCommande))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            /*.addComponent(service)*/
                            .addComponent(numClientLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(numClient, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search)
                                .addGap(10, 10, 10)
                                .addComponent(newClient)
                                .addGap(30, 30, 30)
                                .addComponent(nameClient)
                                .addGap(0, 0, Short.MAX_VALUE))
                            /*.addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(AteUnit)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6))*/))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(libelle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(4, 4, 4)
                        .addComponent(jLibelle)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(JPanelTemps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(libelle)
                        .addComponent(jLibelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(numClientLabel)
                            .addComponent(numClient)                    
                        .addComponent(search).addComponent(newClient).addComponent(nameClient)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    /*.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(service)
                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(AteUnit)
                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))*/
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(JPanelTemps, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nouveau)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(valider, GroupLayout.Alignment.TRAILING)
                            .addComponent(fermer, GroupLayout.Alignment.TRAILING))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(valider)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fermer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(nouveau)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        this.setResizable(false);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
		}
	

	private class ActionCalculatrice implements ActionListener {
		private JTextField casse;
		private JFrame f;
		
		ActionCalculatrice(JTextField field, JFrame frame){
			this.casse = field;
			this.f = frame;
		}
		public void actionPerformed(ActionEvent e) {
			Calculatrice c = new Calculatrice(f, "Calculatrice", true);
			casse.setText(c.getValeur());
			//f.revalidate();
		}
	}
	
	private class ActionSearch implements ActionListener {
		private JFrame frame;
		ActionSearch(Base bdd, AutoComplete lo, JFrame fr){
			this.frame = fr;
		}
		public void actionPerformed(ActionEvent e) {
			new SearchClient(base, numClient, frame, true).searchClientNum(listClient);
		}
	}
	
    private void InsertDevises(){
    	valeurDevises = donnees.devises();
    	for (HashMap.Entry<String, String []> entry : valeurDevises.entrySet())
    	{
    	   devises.addItem(entry.getKey());
    	}
    	devises.setSelectedItem("EUR");
    }
    
    private ArrayList<String> listClient(){
    	listClient = donnees.listeClient();
    	ArrayList<String> res = new ArrayList<String>();
    	for(int i = 0; i< listClient.length; i ++){
    		res.add(listClient[i][0].toString());
    	}
    	return res;
    }
	
	private void calculerDevis(){
		double calcule = Double.parseDouble(jFournitures.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jCout.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jPrefabrication.getText().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10,2)) / Math.pow(10,2);
		jTotalDevis.setText((calcule +"").replaceAll("\\.", ","));
		calcule =  Math.round((calcule*valeurDevise) * Math.pow(10,2)) / Math.pow(10,2);
		jTotalDevisDevise.setText((calcule + "").replaceAll("\\.", ","));
	}
	
	private void calculerHeures(){
		double calcule = Double.parseDouble(jHeureSite.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jHeureAtelier.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = Math.round((calcule) * Math.pow(10,2)) / Math.pow(10,2);
		jTotalHeure.setText((calcule +"").replaceAll("\\.", ","));
	}
	
	private void calculerResteCommande(){
		double calcule = Double.parseDouble(jPrevu.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule - Double.parseDouble(jCommande.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule =  Math.round((calcule) * Math.pow(10,2)) / Math.pow(10,2);
		jResteCommande.setText((calcule +"").replaceAll("\\.", ","));
	}
	
	private void testContenu(JFormattedTextField jtext, int methode){
		if(jtext.getText().toString().isEmpty() || jtext.getText().toString().equals("")){
			jtext.setText("0,00");
		}
		else if(!jtext.getText().contains(",")){
			jtext.setText(jtext.getText()+",00");
		}
		if(methode == 1){
			calculerDevis();
		}
		else if(methode == 2){
			calculerHeures();
		}
		else if(methode == 3){
			calculerResteCommande();
		}
	}
	
	private class FocusPosition implements FocusListener{
		private JFormattedTextField jtext;
		private int met;
		FocusPosition(JFormattedTextField j, int methode){
			this.jtext = j;
			this.met = methode;
		}
		@Override
		public void focusGained(FocusEvent arg0) {
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
		    	   public void run(){testContenu(jtext, met);}
			 });	
		}
	}
	
	private class FocusClient implements FocusListener{
		
		JFrame fenetre;
		
		FocusClient(JFrame frame){
			this.fenetre = frame;
		}

		@Override
		public void focusGained(FocusEvent arg0) {
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			int i = 0;
			while(!listClient[i][0].toString().equals(numClient.getText()) && i < listClient.length){
				i++;
			}
			if(i < listClient.length){
				nameClient.setText("Client : " + listClient[i][1]);
			}
			else{
				nameClient.setText("Error");
			}
			fenetre.repaint();
			
		}
		
	}
	
	private class EcouteAction implements KeyListener{
		EcouteAction(){
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '.'){
				e.setKeyChar(',');
				System.out.println(e.getKeyChar());
			}
		}
	}
	
	private class ItemChange implements ItemListener{

		private JFrame fenetre;
	
		ItemChange(JFrame frame){
			this.fenetre = frame;
		}

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
			devise.setText(devises.getSelectedItem().toString());
			fenetre.repaint();
			calculerDevis();
			
		}
	}
	
    private	class ActionValider implements ActionListener {
    		
    		private JFrame fenetre;
    	
    		public ActionValider(JFrame frame){
    			this.fenetre = frame;
    		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!donnees.existNumDevis(jNumDevis.getText())){
				if(!numClient.getText().equals("") && donnees.existClient(numClient.getText())){
					String [] re = valeurDevises.get(devises.getSelectedItem());
					System.out.println(re.length);
					base.insert("Devis", "'"  + jNumDevis.getText() + "', '" + jNumDevis.getText() + "', null, '" +new SimpleDateFormat("yyyy/MM/dd").format(jDate.getDate()) + "', '" + jLibelle.getText()
					+  "', " + jFournitures.getText().replaceAll(",", "\\.")
					+ ", " + jCout.getText().replaceAll(",", "\\.")
					+ ", " + jHeureAtelier.getText().replaceAll(",", "\\.")
					+ ", " + jHeureSite.getText().replaceAll(",", "\\.")
					+ ", " + jPrefabrication.getText().replaceAll(",", "\\.")
					+ ", " + jPrevu.getText().replaceAll(",", "\\.")
					+ ", " + jCommande.getText().replaceAll(",", "\\.")
					+ ", " + re[0]);
					JOptionPane.showMessageDialog(null, "Devis validé !");
					fenetre.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Numéro de Devis existant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}	
		}
    }
	
}


