package View.SearchCommandes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import BDD.Base;
import Controller.ActionFermer;
import Controller.KeyEntrerSearchList;
import Controller.PositionComparator;
import Controller.Search;
import Controller.RetourAction;
import Controller.RowListener;
import Controller.SelectionAction;
import View.SearchList.SearchList;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchCommandeList extends SearchList {

	private static final long serialVersionUID = 1L;
	protected String fonction, numComClient;
	protected JButton valider, retour, annuler;
	protected Object saisie;

	public SearchCommandeList(Base bdd, JFrame frame, String fonction, String numComClient) {
		super(bdd, frame);
		data = null;
		this.numComClient = numComClient;
		this.fonction = fonction;
		init();
		layerTable.addKeyListener(new KeyEntrerSearchList(this, "Commandes", fonction));
		layerTable.addMouseListener(new SelectionAction(this, "Commandes", fonction));
		valider.addActionListener(new SelectionAction(this, "Commandes", fonction));
		retour.addActionListener(new RetourAction(this, "Commandes", fonction));
		annuler.addActionListener(new ActionFermer(this));
		actionSearch = new Search(this, 0);
        search.addKeyListener(actionSearch);
		this.add(layerPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public SearchCommandeList(Base bdd, Object frame, String fonction, AutoComplete complete) {
		super(bdd, (JFrame)frame);
		this.saisie = frame;
		data = null;
		this.fonction = fonction;
		this.auto = complete;
		init();
		layerTable.addKeyListener(new KeyEntrerSearchList(this, "Commandes", fonction));
		layerTable.addMouseListener(new SelectionAction(this, "Commandes", fonction));
		valider.addActionListener(new SelectionAction(this, "Commandes", fonction));
		retour.addActionListener(new RetourAction(this, "Commandes", fonction));
		annuler.addActionListener(new ActionFermer(this));
		actionSearch = new Search(this, 0);
        search.addKeyListener(actionSearch);
		this.add(layerPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getNumComClient() {
		return numComClient;
	}
	public void setNumComClient(String numComClient) {
		this.numComClient = numComClient;
	}
	private void init(){
		if(numComClient != null){
			data = donnees.liste("d.numCommande, d.numClient, c.nomclient, d.lblCommande", "commandes as d, clients as c", "d.numClient = c.numClient and d.CdeComClient = '" + numComClient + "'");
		}
		else{
			data = donnees.liste("d.numCommande, d.numClient, c.nomclient, d.lblCommande", "commandes as d, clients as c", "d.numClient = c.numClient");
		}
		this.setPreferredSize(new Dimension(800, 500));
		this.setTitle("STID Gestion 2.0 (Chercher Commande)");

        model = new DefaultTableModel();
        model.addColumn("N° Commande");
        model.addColumn("N° Client");
        model.addColumn("Nom Client");
        model.addColumn("Libellé");
    	for(int m = 0; m< data.length ; m++){
			model.addRow(data[m]);
		}
    	sorter = new TableRowSorter<>(model);
    	sorter.setComparator(0, new PositionComparator("int"));
    	sorter.setComparator(1, new PositionComparator("int"));
        // Construct our table to hold our list of layers
        layerTable = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
        		return false;
        	}
        };
        layerTable.setRowSorter(sorter);
		layerTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		layerTable.getColumnModel().getColumn(1).setPreferredWidth(1);
		layerTable.getColumnModel().getColumn(2).setPreferredWidth(220);
		layerTable.getColumnModel().getColumn(3).setPreferredWidth(330);
		layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centrerTable(layerTable);
		layerTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll = new JScrollPane(layerTable);
		scroll.setPreferredSize(new Dimension(750, 400));
		layerPanel.add(scroll, c);
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		retour = new JButton("Retour");
		layerPanel.add(search, BorderLayout.EAST);
		layerPanel.add(retour, BorderLayout.SOUTH);
		layerPanel.add(valider, BorderLayout.SOUTH);
		layerPanel.add(annuler, BorderLayout.SOUTH);
        sorter.addRowSorterListener(new RowListener(this));

	}
	public Object getSaisie() {
		return saisie;
	}
	public void setSaisie(Object saisie) {
		this.saisie = saisie;
	}
}
