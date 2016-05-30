package View.SearchParameters;

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
import Controller.KeyEntrerSearchList;
import Controller.Search;
import Controller.SelectionAction;
import Controller.RetourAction;
import Controller.RowListener;
import View.Pointage.SaisiePointage;
import View.SearchList.SearchList;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchSalarieList extends SearchList {

	private static final long serialVersionUID = 1L;
	protected String f;
	protected AutoComplete auto;
	protected SaisiePointage saisie;
	
	public SearchSalarieList(Base bdd, JFrame frame, String fonction) {
		super(bdd, frame);
		this.setPreferredSize(new Dimension(800, 480));
		this.bdd = bdd;
		this.frame = frame;
		this.f = fonction;
		init();
	}
	
	public SearchSalarieList(Base bdd, SaisiePointage frame, String fonction, AutoComplete auto) {
		super(bdd, frame);
		this.setPreferredSize(new Dimension(800, 480));
		this.bdd = bdd;
		this.frame = frame;
		this.saisie = frame;
		this.f = fonction;
		this.auto = auto;
		init();
	}
	private void init(){
		data = null;
		data = donnees.liste("p.NumPersonnel, p.Nom, p.Prenom", "personne as p", null);
		if (data != null) {
			this.setTitle("STID Gestion 2.0 (Chercher Salarié)");
	        model = new DefaultTableModel();
	        model.addColumn("N°");
	        model.addColumn("Nom");
	        model.addColumn("Prénom");
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
			layerTable.getColumnModel().getColumn(0).setPreferredWidth(30);
			layerTable.getColumnModel().getColumn(1).setPreferredWidth(30);
			layerTable.getColumnModel().getColumn(2).setPreferredWidth(100);
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
		    layerTable.addKeyListener(new KeyEntrerSearchList(this, "Salarie", f));
			valider.addActionListener(new SelectionAction(this, "Salarie", f));
		 	annuler.addActionListener(new ActionFermer(this));
	        retour.addActionListener(new RetourAction(this, "Salarie", f));
	        search.addKeyListener(new Search(this, 0));
	        sorter.addRowSorterListener(new RowListener(this));
			this.add(layerPanel);
			this.pack();
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "Erreur : Aucun personnel", "ATTENTION",
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

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public AutoComplete getAuto() {
		return auto;
	}

	public void setAuto(AutoComplete auto) {
		this.auto = auto;
	}

	public SaisiePointage getSaisie() {
		return saisie;
	}

	public void setSaisie(SaisiePointage saisie) {
		this.saisie = saisie;
	}
	
	
	
}