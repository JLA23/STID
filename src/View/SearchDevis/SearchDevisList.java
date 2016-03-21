package View.SearchDevis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import BDD.Base;
import Controller.ActionFermer;
import Controller.Search;
import Controller.SelectionAction;
import Controller.RetourAction;
import Controller.RowListener;
import View.Commandes.SelectDevis;
import View.SearchList.SearchList;

public class SearchDevisList extends SearchList {

	private static final long serialVersionUID = 1L;
	protected SelectDevis sd;
	
	public SearchDevisList(Base bdd, JFrame frame, SelectDevis select, String numCommande, String fonction) {
		super(bdd, frame);
		this.setPreferredSize(new Dimension(800, 480));
		data = null;
		if(fonction.equals("Commandes")){
			if(numCommande != null){
				data = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "Devis as d, Clients as c",
					"d.numclient = c.numclient and (d.numcommande is null || d.numCommande = " + numCommande + ")");
			}
			else{
				data = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "Devis as d, Clients as c", "d.numclient = c.numclient and d.numcommande is null");
			}
		}
		else{
			data = donnees.liste("d.numDevis, d.numClient, c.nomclient, d.lblDevis", "Devis as d, Clients as c", "d.numClient = c.numClient");
		}
		if (data != null) {
			this.bdd = bdd;
			this.sd = select;
			this.frame = frame;
			this.setTitle("STID Gestion 2.0 (Chercher Devis)");
	        model = new DefaultTableModel();
	        model.addColumn("N° Devis");
	        model.addColumn("N° Client");
	        model.addColumn("Nom Client");
	        model.addColumn("Libelle");
	    	for(int m = 0; m< data.length ; m++){
				model.addRow(data[m]);
			}
	    	RowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	       
	        // Construct our table to hold our list of layers
	        layerTable = new JTable(model){
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int col) {
	        		return false;
	        	}
	        };
	        layerTable.setRowSorter(sorter);
			layerTable.getTableHeader().setReorderingAllowed(false);
			layerTable.getColumnModel().getColumn(0).setPreferredWidth(1);
			layerTable.getColumnModel().getColumn(1).setPreferredWidth(1);
			layerTable.getColumnModel().getColumn(2).setPreferredWidth(220);
			layerTable.getColumnModel().getColumn(3).setPreferredWidth(330);
			layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			centrerTable(layerTable);
			JScrollPane scroll = new JScrollPane(layerTable);
			scroll.setPreferredSize(new Dimension(750, 400));
			layerPanel.add(scroll, c);
			JButton valider = new JButton("Valider");
			JButton annuler = new JButton("Annuler");
			JButton retour = new JButton("Retour");
		    layerPanel.add(search, BorderLayout.EAST);
		    layerPanel.add(retour, BorderLayout.SOUTH);
		    layerPanel.add(valider, BorderLayout.SOUTH);
		    layerPanel.add(annuler, BorderLayout.SOUTH);
			valider.addActionListener(new SelectionAction(this, "Devis", fonction));
		 	annuler.addActionListener(new ActionFermer(this));
	        retour.addActionListener(new RetourAction(this, "Devis", fonction));
	        search.addKeyListener(new Search(this, 0));
	        sorter.addRowSorterListener(new RowListener(this));
			this.add(layerPanel);
			this.pack();
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "Erreur : Aucun devis en attente", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public Base getBdd() {
		return bdd;
	}


	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}


	public SelectDevis getSd() {
		return sd;
	}


	public void setSd(SelectDevis sd) {
		this.sd = sd;
	}


	public Object[][] getData() {
		return data;
	}


	public void setData(Object[][] data) {
		this.data = data;
	}


	public JTable getLayerTable() {
		return layerTable;
	}


	public void setLayerTable(JTable layerTable) {
		this.layerTable = layerTable;
	}

	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
