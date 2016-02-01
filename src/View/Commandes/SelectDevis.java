package View.Commandes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import BDD.Base;
import Controller.Commandes.ActionListDevis;
import Controller.Commandes.SelectDevis.ActionAfficherDevis;
import Controller.Commandes.SelectDevis.AjoutDevis;
import Controller.Commandes.SelectDevis.ActionFermerListDevis;
import Controller.Commandes.SelectDevis.ActionSupprimerListDevis;
import Controller.Commandes.SelectDevis.SelectionActionValider;
import Model.Donnees;

public class SelectDevis extends JFrame {

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension(800, 550);
	protected JFormattedTextField jNumDevis;
	protected JLabel numDevis;
	protected JButton ajouter;
	protected Base base;
	protected Commandes commande;
	protected DefaultTableModel model;
	protected JTable layerTable;
	protected Donnees donnees;

	public SelectDevis(Base bdd, Commandes com, String numCommande) {
		this.base = bdd;
		this.commande = com;
		this.donnees = new Donnees(base);
		model = new DefaultTableModel();
		this.setTitle("STID Gestion 2.0 (Selection Devis)");
		this.setPreferredSize(screenSize);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setLayout(null);
		model.addColumn("N° Devis");
		model.addColumn("N° Client");
		model.addColumn("Nom Client");
		model.addColumn("Libelle");
		if(numCommande != null){
			System.out.println("entre");
			Object[][] liees = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numcommande = " + numCommande);
			for(int m = 0; m< liees.length ; m++){
				model.addRow(liees[m]);
			}
		}
		JPanel layerPanel = new JPanel();
		layerPanel.setPreferredSize(new Dimension(470, 470));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;

		layerTable = new JTable(model) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		layerTable.getTableHeader().setReorderingAllowed(false);
		layerTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		layerTable.getColumnModel().getColumn(1).setPreferredWidth(1);
		layerTable.getColumnModel().getColumn(2).setPreferredWidth(220);
		layerTable.getColumnModel().getColumn(3).setPreferredWidth(330);
		centrerTable(layerTable);
		JScrollPane scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(765, 420));
		layerPanel.add(scroll, c);
		JButton valider = new JButton("Valider");
		JButton fermer = new JButton("Fermer");
		JButton consulter = new JButton("Consulter");
		JButton supprimer = new JButton("Supprimer");
		JButton liste = new JButton("Liste");
		numDevis = new JLabel("N° Devis");
		jNumDevis = new JFormattedTextField();
		numDevis.setBounds(295, 448, numDevis.getPreferredSize().width, numDevis.getPreferredSize().height);
		jNumDevis.setPreferredSize(new Dimension(100, 25));
		jNumDevis.setBounds(300 + numDevis.getPreferredSize().width, 445, jNumDevis.getPreferredSize().width,
				jNumDevis.getPreferredSize().height);
		this.getContentPane().add(numDevis);
		this.getContentPane().add(jNumDevis);
		ajouter = new JButton("Ajouter");
		ajouter.setBounds(310 + numDevis.getPreferredSize().width + jNumDevis.getPreferredSize().width, 445,
				ajouter.getPreferredSize().width, ajouter.getPreferredSize().height);
		this.getContentPane().add(ajouter);
		consulter.setBounds(190, 480, consulter.getPreferredSize().width, consulter.getPreferredSize().height);
		liste.setBounds(195 + consulter.getPreferredSize().width, 480, liste.getPreferredSize().width,
				liste.getPreferredSize().height);
		supprimer.setBounds(200 + consulter.getPreferredSize().width + liste.getPreferredSize().width, 480,
				supprimer.getPreferredSize().width, supprimer.getPreferredSize().height);
		valider.setBounds(
				205 + consulter.getPreferredSize().width + liste.getPreferredSize().width
						+ supprimer.getPreferredSize().width,
				480, valider.getPreferredSize().width, valider.getPreferredSize().height);
		fermer.setBounds(
				210 + consulter.getPreferredSize().width + liste.getPreferredSize().width
						+ supprimer.getPreferredSize().width + valider.getPreferredSize().width,
				480, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		this.getContentPane().add(consulter);
		this.getContentPane().add(liste);
		this.getContentPane().add(supprimer);
		this.getContentPane().add(valider);
		this.getContentPane().add(fermer);
		layerPanel.setBounds(10, 10, 770, 430);
		liste.addActionListener(new ActionListDevis(base, this, numCommande));
		valider.addActionListener(new SelectionActionValider(this, com)); 
		fermer.addActionListener(new ActionFermerListDevis(this, com)); 
		ajouter.addActionListener(new AjoutDevis(this));
		supprimer.addActionListener(new ActionSupprimerListDevis(this));
		consulter.addActionListener(new ActionAfficherDevis(this));
		this.addWindowListener(new ActionFermerListDevis(this, com));
		this.add(layerPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JFormattedTextField getjNumDevis() {
		return jNumDevis;
	}

	public void setjNumDevis(JFormattedTextField jNumDevis) {
		this.jNumDevis = jNumDevis;
	}

	public void afficher(){
		commande.setEnabled(false);
		this.setVisible(true);

	}

	private void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(custom);
	}
	
	public boolean containt(String numDevis){
		boolean resultat = false;
		int lignes = model.getRowCount();
		for(int i = 0; i < lignes; i++){
			if(((String)model.getValueAt(i, 0)).equals(numDevis)){
				resultat = true;
				break;
			}
		}
		return resultat;
	}

	public JTable getLayerTable() {
		return layerTable;
	}

	public void setLayerTable(JTable layerTable) {
		this.layerTable = layerTable;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

}
