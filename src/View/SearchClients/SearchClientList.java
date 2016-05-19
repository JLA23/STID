package View.SearchClients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import View.SearchList.SearchList;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchClientList extends SearchList {
	
	private static final long serialVersionUID = 1L;

	public SearchClientList(Base bdd, JFrame frame, String fonction, AutoComplete auto){
		super(bdd, frame);
		System.out.println(auto == null);
		this.auto = auto;
		if(fonction.equals("Suppr")){
			data = donnees.liste("c.NumClient, c.NomClient, CONCAT(c.Adresse2, ' ', c.Adresse3, ' ', c.Adresse4, ' ', c.Adresse5, ' ' , c.Adresse6, ' ', c.Adresse7)",
					"clients as c", "not exists (select d.numClient from devis as d where d.numclient = c.numclient)");
		}
		else{
			data = donnees.liste("NumClient, NomClient, CONCAT(Adresse2, ' ', Adresse3, ' ', Adresse4, ' ', Adresse5, ' ' ,Adresse6, ' ', Adresse7)",
					"clients", null);
		}
		this.setTitle("STID Gestion 2.0 (Chercher Client)");
        model = new DefaultTableModel();
        model.addColumn("N° Client");
        model.addColumn("Nom");
        model.addColumn("Adresse");
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
		layerTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		layerTable.getColumnModel().getColumn(2).setPreferredWidth(320);
		layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centrerTable(layerTable);
		layerTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(650, 400));
		layerPanel.add(scroll, c);
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton retour = new JButton("Retour");
        layerPanel.add(search, BorderLayout.EAST);
        layerPanel.add(retour, BorderLayout.SOUTH);
        layerPanel.add(valider, BorderLayout.SOUTH);
        layerPanel.add(annuler, BorderLayout.SOUTH);
        valider.addActionListener(new SelectionAction(this, "Client", fonction));
        annuler.addActionListener(new ActionFermer(this));
        retour.addActionListener(new RetourAction(this, "Client", fonction));
        search.addKeyListener(new Search(this, 0));
        sorter.addRowSorterListener(new RowListener(this));
        this.add(layerPanel);
        this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);

	}
	
	

	


}
