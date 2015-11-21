package View.Clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import BDD.Base;
import Controller.Donnees;
import View.Options.ClickDroit;

public class SearchClient extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension(500, 500);
	protected Base bdd;
	protected JComboBox<String> box;
	
	public SearchClient(Base bdd, JComboBox<String> j, JFrame frame, boolean modale){
		super(frame, null, modale);
		this.bdd = bdd;
		this.box = j;
		//this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("serial")
	public void searchClientNum(){
		Donnees donnees = new Donnees(bdd);
		Object[][] data = donnees.listeClient();
		this.setPreferredSize(screenSize);
		this.setTitle("STID Gestion 2.0 (Chercher Client)");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
        JPanel layerPanel = new JPanel();
        layerPanel.setPreferredSize(screenSize);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.weightx = 1; c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;

        String[] columns = { "Numèro Client", "Nom", "Adresse" };

        // Construct our table to hold our list of layers
        JTable layerTable = new JTable(data, columns){
        	public boolean isCellEditable(int row, int col) {
        		return false;
        	}
        };
        layerTable.getColumnModel().getColumn(0).setPreferredWidth(1);
        layerTable.getColumnModel().getColumn(1).setPreferredWidth(1);
        layerTable.getColumnModel().getColumn(2).setPreferredWidth(170);
        centrerTable(layerTable);
        //JPanel layerPanel2 = new JPanel(new GridBagLayout());
        layerPanel.add(new JScrollPane(layerTable), c);
        // layerPanel.add(layerPanel2);
        layerPanel.add(new JButton("Valider"), BorderLayout.SOUTH);
        this.add(layerPanel);
	  //  this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.pack();
	    this.setVisible(true);

	}
	
	private void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<table.getColumnCount() ; i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	   }

}
