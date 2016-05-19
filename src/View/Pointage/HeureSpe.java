package View.Pointage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
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
import Controller.ActionFermer;
import Controller.RowListener;
import Controller.Search;
import Controller.TestContenu;
import Controller.Pointage.Action;
import Controller.Pointage.SupprimerSaisie;
import Controller.Pointage.TableauSaisie;
import Model.Donnees;

public class HeureSpe extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected JButton ajouter, modifier, supprimer, fermer;
	protected JFormattedTextField jAnnee, perdue, recup, solde, search;
	protected JLabel annee;
	protected GridBagConstraints c;
	protected  DefaultTableModel  model;
	protected JTable layerTable;
	protected JPanel layerPanel;
	protected Object [][] data;
	protected JFrame frame;
	protected Base base;
	protected Donnees donnees;
	protected RowSorter<TableauSaisie> sorter;
	protected JScrollPane scroll;
	protected TableauSaisie saisie;
	
	public HeureSpe(Base bdd, JFrame frame) {
	    this.setSize(600, 380);
	    this.setTitle("STID Gestion 2.0 (Heure Spe)");
	    this.setLayout(null);
	    this.frame = frame;
	    this.base = bdd;
	    this.donnees = new Donnees(bdd);
	    init();	    
	}
	
	public void init(){
		frame.setEnabled(false);
	    annee = new JLabel("Année");
	    annee.setBounds(10, 10, annee.getPreferredSize().width, annee.getPreferredSize().height);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		
	    NumberFormat num = NumberFormat.getIntegerInstance();
		DecimalFormat nf = new DecimalFormat("#0.00");
		num.setGroupingUsed(false);
		nf.setGroupingUsed(false);
	    
		jAnnee = new JFormattedTextField(num);
	    jAnnee.setPreferredSize(new Dimension(50,20));
	    jAnnee.setBounds(20 + annee.getPreferredSize().width, 8, jAnnee.getPreferredSize().width, jAnnee.getPreferredSize().height);
	    Calendar calendrier = Calendar.getInstance();
	    jAnnee.setText(calendrier.get(Calendar.YEAR) + ""); 
	    data = donnees.liste("LblHeureSpeciale, HrPrev, HrPass, Round(HrPrev - HrPass, 2), NumHeureSpe", "heurespe", "Annee = " + calendrier.get(Calendar.YEAR));
	    this.add(annee);
	    this.add(jAnnee);
	    layerPanel = new JPanel();
	    c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.weightx = 1; c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        search = new JFormattedTextField();
        search.setPreferredSize(new Dimension(110, 27));
        saisie = new TableauSaisie(data);
        sorter = new TableRowSorter<>(saisie);
        layerTable = new JTable(saisie){
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
		centrerTable(layerTable);
		scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(550, 200));

		layerPanel.add(scroll, c);

        search.addKeyListener(new Search(this, 0));
        sorter.addRowSorterListener(new RowListener(this));
		layerPanel.setPreferredSize(new Dimension(550, 220));
		layerPanel.setBounds(15, 30, layerPanel.getPreferredSize().width, layerPanel.getPreferredSize().height);
	    this.add(layerPanel);
		int h = 30 + layerPanel.getPreferredSize().height;
		
		recup = new JFormattedTextField(nf);
		perdue = new JFormattedTextField(nf);
		solde = new JFormattedTextField(nf);
		
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
		
		ajouter = new JButton("Ajouter");
		modifier = new JButton("Modifier");
		supprimer = new JButton("Supprimer");
		fermer = new JButton("Fermer");

		ajouter.addActionListener(new Action(this, "Ajouter"));
	 	modifier.addActionListener(new Action(this, "Modifier"));
        supprimer.addActionListener(new SupprimerSaisie(this));
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
		sommeSolde = ((sommeRecup * 100) - (sommePerdue * 100))/100;
		recup.setText((sommeRecup+"").replaceAll("\\.", ","));
		perdue.setText((sommePerdue+"").replaceAll("\\.", ","));
		solde.setText((sommeSolde+"").replaceAll("\\.", ","));
		recup.setEditable(false);
		perdue.setEditable(false);
		solde.setEditable(false);
		couleur();
		new TestContenu(null, recup, -1, null);
		new TestContenu(null, perdue, -1, null);
		new TestContenu(null, solde, -1, null);
		this.add(search, BorderLayout.EAST);
		this.add(ajouter, BorderLayout.SOUTH);
		this.add(modifier, BorderLayout.SOUTH);
		this.add(supprimer, BorderLayout.SOUTH);
		this.add(fermer, BorderLayout.SOUTH);
		jAnnee.addKeyListener(new EcouteAnnee());
	    fermer.addActionListener(new ActionFermer(this, frame));
	    this.addWindowListener(new ActionFermer(this, frame));
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	  }
	
	protected void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<table.getColumnCount() ; i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	   }

	public JFormattedTextField getjAnnee() {
		return jAnnee;
	}

	public void setjAnnee(JFormattedTextField jAnnee) {
		this.jAnnee = jAnnee;
	}

	public JFormattedTextField getPerdue() {
		return perdue;
	}

	public void setPerdue(JFormattedTextField perdue) {
		this.perdue = perdue;
	}

	public JFormattedTextField getRecup() {
		return recup;
	}

	public void setRecup(JFormattedTextField recup) {
		this.recup = recup;
	}

	public JFormattedTextField getSolde() {
		return solde;
	}

	public void setSolde(JFormattedTextField solde) {
		this.solde = solde;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getLayerTable() {
		return layerTable;
	}

	public void setLayerTable(JTable layerTable) {
		this.layerTable = layerTable;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JFormattedTextField getSearch() {
		return search;
	}

	public void setSearch(JFormattedTextField search) {
		this.search = search;
	}

	public TableauSaisie getSaisie() {
		return saisie;
	}

	public void setSaisie(TableauSaisie saisie) {
		this.saisie = saisie;
	}

	public class EcouteAnnee implements KeyListener{
			
		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			action();
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
		public void action(){
			data = donnees.liste("LblHeureSpeciale, HrPrev, HrPass, Round(HrPrev - HrPass, 2), NumHeureSpe", "heurespe", "Annee = " + jAnnee.getText());
			double sommeRecup = 0.00;
			double sommePerdue = 0.00;
			double sommeSolde = 0.00;
			for(int i = 0; i < data.length; i++){
				sommeRecup += Double.parseDouble(data[i][1].toString());
				sommePerdue += Double.parseDouble(data[i][2].toString());
			}
			sommeSolde = ((sommeRecup * 100) - (sommePerdue * 100))/100;
			System.out.println(recup == null);
			recup.setText((sommeRecup+"").replaceAll("\\.", ","));
			perdue.setText((sommePerdue+"").replaceAll("\\.", ","));
			solde.setText((sommeSolde+"").replaceAll("\\.", ","));
			couleur();
			new TestContenu(null, recup, -1, null);
			new TestContenu(null, perdue, -1, null);
			new TestContenu(null, solde, -1, null);
			saisie.actualiser(data);
		}			
	}
	
	public void couleur(){
		if(Double.parseDouble(solde.getText().replaceAll(",", "\\.")) < 0){
			solde.setBackground(Color.red);
		}
		else if (Double.parseDouble(solde.getText().replaceAll(",", "\\.")) > 0){
			solde.setBackground(Color.green);
		}
		else{
			solde.setBackground(Color.white);
		}
	}
}