package View.Devis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;
import BDD.Base;
import Controller.Donnees;
import View.Calculatrice.Calculatrice;
import View.Clients.SearchClient;
import View.Options.ClickDroit;


public class NewDevis extends JFrame{
	
    private JButton jButton1, valider, fermer, jButton2, jButton3, jButton4, jButton5, calcul6, jButton7, jButton8, nouveau, search;
    private JComboBox<String> numClient, devises;
    private JLabel jLabel1, prefabrication, euro1, euro2, euro3, euro4, totalDevis, hrsAtelier, prevu, hrsSite, hrs1, dateLabel, euro5;
    private JLabel hrs2;
    private JLabel hrs3;
    private JLabel totalHeures;
    private JLabel commande;
    private JLabel euro6;
    private JLabel resteCommande;
    private JLabel euro7;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel8;
    private JLabel coutMO;
    private JLabel totalDevisdevise;
    private JLabel devise;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel JPanelTemps;
    private JPanel jPanel6;
    private JSeparator jSeparator1, jSeparator2;
    private JFormattedTextField jTextFieldTotal, jTextField11, jTextField7, jTextField8, jTextField9, jTextField10, jPrevu, jTextField13, jTextField14, jCommande, jTextField16;
    private JTextField jTextField1, jTextField4;
    private JDateChooser jDate;

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public NewDevis(Base bdd){
		
		Donnees donnees = new Donnees(bdd);
		int nbDevis = donnees.newNumDevis() + 1;
		
		this.setTitle("Nouveau Devis");
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
	    
        jLabel1 = new JLabel("Num�ro");
	    dateLabel = new JLabel("Date");
        jLabel3 = new JLabel("Devise");
        jLabel4 = new JLabel("Libell�");
        jLabel5 = new JLabel("N� Client");
        hrsAtelier = new JLabel("Heures d'Atelier");
        hrsSite = new JLabel("Heures sur Site");
        hrs1 = new JLabel("Hrs");
        hrs2 = new JLabel("Hrs");
        hrs3 = new JLabel("Hrs");
        totalHeures = new JLabel("Total des Heures");
        jLabel8 = new JLabel("Fournitures");
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
        
        jTextField1 = new JTextField();
        jTextField1.setText(nbDevis + "");
        jTextField4 = new JTextField();

        NumberFormat nf = new DecimalFormat("#0.00");
        nf.setGroupingUsed(false);
        jTextField7 = new JFormattedTextField(nf);;
        jTextField8 = new JFormattedTextField(nf);
        jTextField9 = new JFormattedTextField(nf);
        jTextField10 = new JFormattedTextField(nf);
        jTextField11 = new JFormattedTextField(nf);
        jTextField13 = new JFormattedTextField(nf);
        jTextField14 = new JFormattedTextField(nf);
        jPrevu = new JFormattedTextField(nf);
        jCommande = new JFormattedTextField(nf);
        jTextField16 = new JFormattedTextField(nf);
        
        jTextField7.setText("0,00");
        jTextField8.setText("0,00");
        jTextField9.setText("0,00");
        jTextField10.setText("0,00");
        jTextField11.setText("0,00");
        jTextField13.setText("0,00");
        jTextField14.setText("0,00");
        jTextField16.setText("0,00");
        jPrevu.setText("0,00");
        jCommande.setText("0,00");
        
		jTextFieldTotal = new JFormattedTextField(nf);
        jTextFieldTotal.setText("0,00");
        
        new ClickDroit(jTextField7, true, true);
        new ClickDroit(jTextField8, true, true);
        new ClickDroit(jTextField9, true, true);
        new ClickDroit(jTextField10, true, false);
        new ClickDroit(jTextField11, true, true);
        new ClickDroit(jTextField13, true, true);
        new ClickDroit(jTextField14, true, false);
        new ClickDroit(jTextField16, true, false);
        new ClickDroit(jTextFieldTotal, true, false);
        new ClickDroit(jPrevu, true, true);
        new ClickDroit(jCommande, true, true);
        
        jTextField10.setEditable(false);
        jTextField14.setEditable(false);
        jTextField16.setEditable(false);
        jTextFieldTotal.setEditable(false);
        
        numClient = new JComboBox<>();
        devises = new JComboBox<>();
        
        jButton2 = new JButton("Cr�er Client");
        jButton5 = new JButton(icon);
        jButton7 = new JButton(icon);
        jButton1 = new JButton(icon);
        jButton3 = new JButton(icon);
        jButton4 = new JButton(icon);
        calcul6 = new JButton(icon);
        jButton8 = new JButton(icon);
        nouveau = new JButton("Nouveau");
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");
        search = new JButton("Chercher Client");
        
        search.addActionListener(new ActionSearch(bdd, numClient, this));
        
        jTextField7.getDocument().addDocumentListener(new EcouteKey(jTextField7));
        jTextField8.getDocument().addDocumentListener(new EcouteKey(jTextField8));
        
        
        jButton1.addActionListener(new ActionCalculatrice(jTextField7, this));
        jButton3.addActionListener(new ActionCalculatrice(jTextField8, this));
        jButton4.addActionListener(new ActionCalculatrice(jTextField9, this));
        jButton5.addActionListener(new ActionCalculatrice(jTextField13, this));
        calcul6.addActionListener(new ActionCalculatrice(jPrevu, this));
        jButton7.addActionListener(new ActionCalculatrice(jTextField11, this));
        jButton8.addActionListener(new ActionCalculatrice(jCommande, this));
        
        jSeparator2 = new JSeparator();
        jSeparator1 = new JSeparator();
  
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jDate.setDateFormatString("dd/MM/yyyy");
        jDate.setDate(new Date());
  
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        numClient.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3255666", "3255665" }));
        devises.setModel(new DefaultComboBoxModel<>(new String[] { "EUR", "FR" }));
        JPanelTemps.setBorder(BorderFactory.createTitledBorder("Temps"));
        JPanelTemps.setPreferredSize(new java.awt.Dimension(350, 200));

        jTextField14.setBackground(new java.awt.Color(204, 204, 204));
        jTextField10.setBackground(new java.awt.Color(204, 204, 204));
        jTextField16.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldTotal.setBackground(new java.awt.Color(204, 204, 204));
        
        jPanel6.setBorder(BorderFactory.createTitledBorder("Montants"));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 200));
        jPanel3.setBorder(BorderFactory.createTitledBorder("Achat Mati�res"));
        
        devise.setText(devises.getSelectedItem().toString());

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202)
                .addComponent(dateLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDate, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(devises, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(jTextField11, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hrs1))
                                    .addGroup(JPanelTempsLayout.createSequentialGroup()
                                        .addComponent(jTextField13, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hrs2)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7 , GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JPanelTempsLayout.createSequentialGroup()
                            	.addGap(10, 10, 10)
                            	.addComponent(totalHeures)
                                .addGap(16, 16, 16)
                                .addComponent(jTextField14, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jTextField13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsAtelier)
                    .addComponent(hrs2))
                .addGap(44, 44, 44)
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsSite)
                    .addComponent(hrs1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelTempsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jLabel8)
                            .addComponent(coutMO)
                            .addComponent(prefabrication))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(euro3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(euro1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(euro2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(totalDevis)
                .addGap(20, 20, 20)
                .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(euro4)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(totalDevisdevise)
                    .addGap(20, 20, 20)
                    .addComponent(jTextFieldTotal, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(devise)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(euro1))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(coutMO)
                    .addComponent(euro2))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefabrication)
                    .addComponent(euro3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(euro4)
                    .addComponent(totalDevis))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jTextField16, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(euro7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    	.addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                    	.addComponent(prevu)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    	.addGap(17, 17, 17)
                        .addComponent(jPrevu, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED
                        .addComponent(euro5)
                        .addGap(10, 10, 10)
                      //  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcul6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(commande)
                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        .addComponent(jCommande, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        //.addGap(20, 20, 20)
                        .addComponent(euro6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                        )));
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPrevu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(calcul6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevu)
                    .addComponent(euro5)
                    .addComponent(jCommande, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(commande)
                    .addComponent(euro6))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jLabel5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(numClient, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search)
                                .addGap(10, 10, 10)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            /*.addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(AteUnit)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6))*/))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField4)))
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
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(numClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addComponent(search).addComponent(jButton2))
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
	    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		private JComboBox<String> casse;
		private Base bdd;
		private JFrame frame;
		
		ActionSearch(Base bdd, JComboBox<String> j, JFrame fr){
			this.casse = j;
			this.bdd = bdd;
			this.frame = fr;

		}
		public void actionPerformed(ActionEvent e) {
			new SearchClient(bdd, casse, frame, true).searchClientNum();
		}
	}
	
	private void calculer(){
		double calcule = Double.parseDouble((jTextField7.getText().toString().replaceAll(" ", "")).replaceAll(",", "\\."));
		calcule = calcule + Double.parseDouble(jTextField8.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		calcule = calcule + Double.parseDouble(jTextField9.getText().toString().replaceAll(",", "\\.").replaceAll(" ", ""));
		jTextField10.setText((calcule +"").replaceAll("\\.", ","));
	}
	
	private void testContenu(JFormattedTextField jtext){
		if(jtext.getText().toString().isEmpty() || jtext.getText().toString().equals("")){
			jtext.setText("0,00");
			calculer();
		}
		/*else if(!jtext.getText().toString().isEmpty()){
			jtext.setText(",00");
			calculer();
		}*/
		
	}
	
	private class EcouteKey implements DocumentListener{
		private JFormattedTextField jtext;
		EcouteKey(JFormattedTextField j){
			this.jtext = j;
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			/*SwingUtilities.invokeLater(new Runnable(){
		    	   public void run(){testContenu(jtext);}
			 });*/
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
		    	   public void run(){testContenu(jtext);}
			 });
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
		    	   public void run(){testContenu(jtext);}
			 });
			
		}
   	
	}
}


