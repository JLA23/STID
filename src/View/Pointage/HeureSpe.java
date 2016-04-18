package View.Pointage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BDD.Base;
import Model.Donnees;

public class HeureSpe extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected JButton ajouter, modifier, supprimer, fermer;
	protected JFormattedTextField jAnnee, perdue, recup, solde;
	protected JLabel annee;
	protected GridBagConstraints c;
	protected  DefaultTableModel  model;
	protected JTable layerTable;
	protected JPanel layerPanel;
	protected Object [][] data;
	protected JFrame frame;
	protected Base base;
	protected Donnees donnees;
	
	public HeureSpe(Base bdd, JFrame frame) {
	    this.setSize(600, 380);
	    this.setTitle("STID Gestion 2.0 (Heure Spe)");
	    this.setLayout(null);
	    this.frame = frame;
	    this.base = bdd;
	    this.donnees = new Donnees(bdd);
	    annee = new JLabel("Année");
	    annee.setBounds(10, 10, annee.getPreferredSize().width, annee.getPreferredSize().height);
	    
	    NumberFormat num = NumberFormat.getIntegerInstance();
		DecimalFormat nf = new DecimalFormat("#0.00");
		num.setGroupingUsed(false);
		nf.setGroupingUsed(false);
	    
		JFormattedTextField jAnnee = new JFormattedTextField(num);
	    jAnnee.setPreferredSize(new Dimension(50,20));
	    jAnnee.setBounds(20 + annee.getPreferredSize().width, 8, jAnnee.getPreferredSize().width, jAnnee.getPreferredSize().height);
	    Calendar calendrier = Calendar.getInstance();
	    jAnnee.setText(calendrier.get(Calendar.YEAR) + ""); 
	    data = donnees.liste("LblHeureSpeciale, HrPrev, HrPass, Round(HrPrev - HrPass, 2)", "heurespe", "Annee = " + calendrier.get(Calendar.YEAR));
	    this.add(annee);
	    this.add(jAnnee);
	    layerPanel = new JPanel();
	    c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.weightx = 1; c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        JFormattedTextField search = new JFormattedTextField();
        search.setPreferredSize(new Dimension(110, 27));
        model = new DefaultTableModel();
        model.addColumn("Libellé");
        model.addColumn("Recup");
        model.addColumn("Perdues");
        model.addColumn("Solde");
    	for(int m = 0; m < data.length ; m++){
			model.addRow(data[m]);
		}
        RowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        layerTable = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
        		return false;
        	}
        };
        layerTable.setRowSorter(sorter);
		layerTable.getTableHeader().setReorderingAllowed(false);
		layerTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		layerTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		layerTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		layerTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//centrerTable(layerTable);
		JScrollPane scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(550, 200));

		layerPanel.add(scroll, c);
		JButton ajouter = new JButton("Ajouter");
		JButton modifier = new JButton("Modifier");
		JButton supprimer = new JButton("Supprimer");
		JButton fermer = new JButton("Fermer");

		//valider.addActionListener(new SelectionAction(this, "Devis", fonction));
	 	//annuler.addActionListener(new ActionFermer(this));
        //retour.addActionListener(new RetourAction(this, "Devis", fonction));
        //search.addKeyListener(new Search(this, 0));
        //sorter.addRowSorterListener(new RowListener(this));
		layerPanel.setPreferredSize(new Dimension(550, 220));
		layerPanel.setBounds(15, 30, layerPanel.getPreferredSize().width, layerPanel.getPreferredSize().height);
	    this.add(layerPanel);
		int h = 30 + layerPanel.getPreferredSize().height;
		
		JFormattedTextField recup = new JFormattedTextField(nf);
		JFormattedTextField perdue = new JFormattedTextField(nf);
		JFormattedTextField solde = new JFormattedTextField(nf);
		
		recup.setBounds(305, h, 80, 25);
		perdue.setBounds(385, h, 80, 25);
		solde.setBounds(465, h, 100, 25);
		this.add(recup);
		this.add(perdue);
		this.add(solde);
		h += 50;
		int l = 20;
		search.setBounds(l, h, 120, 25);
		l += 205;
		ajouter.setBounds(l, h, ajouter.getPreferredSize().width, ajouter.getPreferredSize().height);
		l += (5 + ajouter.getPreferredSize().width);
		modifier.setBounds(l, h, modifier.getPreferredSize().width, modifier.getPreferredSize().height);
		l += (5 + modifier.getPreferredSize().width);
		supprimer.setBounds(l, h, supprimer.getPreferredSize().width, supprimer.getPreferredSize().height);
		l += (5 + supprimer.getPreferredSize().width);
		fermer.setBounds(l, h, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		double sommeRecup = 0.00;
		double sommePerdue = 0.00;
		double sommeSolde = 0.00;
		for(int i = 0; i < data.length; i++){
			sommeRecup += Double.parseDouble(data[i][1].toString());
			sommePerdue += Double.parseDouble(data[i][2].toString());
		}
		sommeSolde = sommeRecup - sommePerdue;
		recup.setText((sommeRecup+"").replaceAll("\\.", ","));
		perdue.setText((sommePerdue+"").replaceAll("\\.", ","));
		solde.setText((sommeSolde+"").replaceAll("\\.", ","));
		this.add(search, BorderLayout.EAST);
		this.add(ajouter, BorderLayout.SOUTH);
		this.add(modifier, BorderLayout.SOUTH);
		this.add(supprimer, BorderLayout.SOUTH);
		this.add(fermer, BorderLayout.SOUTH);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	  }
	
	protected void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<table.getColumnCount() ; i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	   }

}
