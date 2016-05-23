package View.SearchTerme;
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
import Controller.RetourAction;
import Controller.RowListener;
import Controller.SelectionAction;
import View.SearchList.SearchList;

public class SearchTermeList extends SearchList {

	private static final long serialVersionUID = 1L;

	public SearchTermeList(Base bdd, JFrame frame, String fonction, String numCom, String numindice) {
		super(bdd, frame);
		this.bdd = bdd;
		this.frame = frame;
		data = null;
		if(fonction.equals("NewFacture") && numCom == null && numindice == null){
			data = donnees.liste("t.numCommande, t.numindice, t.numfacture, co.numClient, c.nomclient, t.lblTerme", "termes as t, commandes as co, clients as c", "co.numCommande = t.numCommande and co.numClient = c.numClient and t.numfacture is null");
		}
		else if(fonction.equals("NewFacture") && numCom != null && numindice == null){
			data = donnees.liste("t.numCommande, t.numindice, t.numfacture, co.numClient, c.nomclient, t.lblTerme", "termes as t, commandes as co, clients as c", "co.numCommande = t.numCommande and co.numClient = c.numClient and t.numfacture is null and t.numCommande = " + numCom);
		}
		else if(numCom != null && !fonction.equals("NewFacture") && numindice == null){
			data = donnees.liste("t.numCommande, t.numindice, t.numfacture, co.numClient, c.nomclient, t.lblTerme", "termes as t, commandes as co, clients as c", "co.numCommande = t.numCommande and co.numClient = c.numClient and t.numCommande = " + numCom);
		}
		else if(numCom != null && !fonction.equals("NewFacture") && numindice != null){
			data = donnees.liste("t.numCommande, t.numindice, t.numfacture, co.numClient, c.nomclient, t.lblTerme", "termes as t, commandes as co, clients as c", "co.numCommande = t.numCommande and co.numClient = c.numClient and t.numCommande = " + numCom + " and t.numindice = " + numindice);
		}
		else{
			data = donnees.liste("t.numCommande, t.numindice, t.numfacture, co.numClient, c.nomclient, t.lblTerme", "termes as t, commandes as co, clients as c", "co.numCommande = t.numCommande and co.numClient = c.numClient");
		}
		this.setPreferredSize(new Dimension(1000, 500));
		this.setTitle("STID Gestion 2.0 (Chercher Termes)");

        model = new DefaultTableModel();
        model.addColumn("N° Commande");
        model.addColumn("N° Indice");
        model.addColumn("N° Facture");
        model.addColumn("N° Client");
        model.addColumn("Nom Client");
        model.addColumn("Libellé");
    	for(int m = 0; m < data.length ; m++){
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
		layerTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		layerTable.getColumnModel().getColumn(1).setPreferredWidth(10);
		layerTable.getColumnModel().getColumn(2).setPreferredWidth(10);
		layerTable.getColumnModel().getColumn(3).setPreferredWidth(10);
		layerTable.getColumnModel().getColumn(4).setPreferredWidth(220);
		layerTable.getColumnModel().getColumn(5).setPreferredWidth(330);
		layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centrerTable(layerTable);
		layerTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(950, 400));
		layerPanel.add(scroll, c);
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		JButton retour = new JButton("Retour");
		layerPanel.add(search, BorderLayout.EAST);
		layerPanel.add(retour, BorderLayout.SOUTH);
		layerPanel.add(valider, BorderLayout.SOUTH);
		layerPanel.add(annuler, BorderLayout.SOUTH);
		valider.addActionListener(new SelectionAction(this, "Termes", fonction));
		retour.addActionListener(new RetourAction(this, "Termes", fonction));
		annuler.addActionListener(new ActionFermer(this));
        search.addKeyListener(new Search(this, 0));
        sorter.addRowSorterListener(new RowListener(this));
		this.add(layerPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
}
