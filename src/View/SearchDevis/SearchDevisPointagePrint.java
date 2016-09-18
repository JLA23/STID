package View.SearchDevis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import BDD.Base;
import Controller.ActionFermer;
import Controller.KeyEntrerSearchList;
import Controller.PositionComparator;
import Controller.RetourAction;
import Controller.RowListener;
import Controller.Search;
import Controller.SelectionAction;
import View.SearchList.SearchList;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchDevisPointagePrint extends SearchList {

	private static final long serialVersionUID = 1L;
	protected AutoComplete auto;
	protected Object saisie;
	
	public SearchDevisPointagePrint(Base bdd, Object frame, String fonction, AutoComplete auto) {
		super(bdd, ((JFrame)frame));
		this.setPreferredSize(new Dimension(900, 480));
		data = null;
		data = donnees.liste("d.numDevis, d.numcommande, d.numClient, c.nomclient, d.lblDevis", "devis as d, clients as c", "d.numClient = c.numClient");
		if (data != null) {
			this.saisie = frame;
			this.auto = auto;
			this.setTitle("STID Gestion 2.0 (Recherche Devis)");
	        model = new DefaultTableModel();
	        model.addColumn("N° Devis");
	        model.addColumn("N° Commande");
	        model.addColumn("N° Client");
	        model.addColumn("Nom Client");
	        model.addColumn("Libelle");
	    	for(int m = 0; m< data.length ; m++){
				model.addRow(data[m]);
			}
	    	sorter = new TableRowSorter<>(model);
	    	sorter.setComparator(0, new PositionComparator("int"));
	    	sorter.setComparator(1, new PositionComparator("int"));
	    	sorter.setComparator(2, new PositionComparator("int"));
	        // Construct our table to hold our list of layers
	        layerTable = new JTable(model){
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int col) {
	        		return false;
	        	}
	        };
	        layerTable.setRowSorter(sorter);
			layerTable.getTableHeader().setReorderingAllowed(false);
			layerTable.getColumnModel().getColumn(0).setPreferredWidth(20);
			layerTable.getColumnModel().getColumn(1).setPreferredWidth(60);
			layerTable.getColumnModel().getColumn(2).setPreferredWidth(30);
			layerTable.getColumnModel().getColumn(3).setPreferredWidth(240);
			layerTable.getColumnModel().getColumn(4).setPreferredWidth(280);
			layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			centrerTable(layerTable);
			JScrollPane scroll = new JScrollPane(layerTable);
			scroll.setPreferredSize(new Dimension(850, 400));
			layerPanel.add(scroll, c);
			JButton valider = new JButton("Valider");
			JButton annuler = new JButton("Annuler");
			JButton retour = new JButton("Retour");
		    layerPanel.add(search, BorderLayout.EAST);
		    layerPanel.add(retour, BorderLayout.SOUTH);
		    layerPanel.add(valider, BorderLayout.SOUTH);
		    layerPanel.add(annuler, BorderLayout.SOUTH);
		    layerTable.addKeyListener(new KeyEntrerSearchList(this, null, fonction));
			valider.addActionListener(new SelectionAction(this, null, fonction));
		 	annuler.addActionListener(new ActionFermer(this));
	        retour.addActionListener(new RetourAction(this, null, fonction));
			actionSearch = new Search(this, 0);
	        search.addKeyListener(actionSearch);
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

	public AutoComplete getAuto() {
		return auto;
	}

	public void setAuto(AutoComplete auto) {
		this.auto = auto;
	}

	public Object getSaisie() {
		return saisie;
	}

	public void setSaisie(Object saisie) {
		this.saisie = saisie;
	}
	
	
}
