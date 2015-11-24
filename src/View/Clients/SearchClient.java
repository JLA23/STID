package View.Clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import BDD.Base;
import Controller.Donnees;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchClient extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension(500, 500);
	protected Base bdd;
	protected AutoComplete auto;
	
	public SearchClient(Base bdd, AutoComplete auto, JFrame frame, boolean modale){
		super(frame, null, modale);
		this.bdd = bdd;
		this.auto = auto;
	}
	
	@SuppressWarnings("serial")
	public void searchClientNum(){
		Donnees donnees = new Donnees(bdd);
		Object[][] data = donnees.listeClient();
		this.setPreferredSize(screenSize);
		this.setTitle("STID Gestion 2.0 (Chercher Client)");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
        JPanel layerPanel = new JPanel();
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
        layerPanel.add(new JScrollPane(layerTable), c);
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        layerPanel.add(valider, BorderLayout.SOUTH);
        layerPanel.add(annuler, BorderLayout.SOUTH);
        valider.addActionListener(new SelectionAction(this, layerTable, data));
        this.add(layerPanel);
        this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);

	}
	
	private void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<table.getColumnCount() ; i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	   }
	
	private class SelectionAction implements ActionListener {
		private JDialog dialog;
		private JTable tables;
		private Object[][] datas;
		
		SelectionAction(JDialog dialog, JTable table, Object [][] datas){
			this.dialog = dialog;
			this.tables = table;
			this.datas = datas;
		}
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Valider")) {
				int ligne = tables.getSelectedRow();
				if(ligne != -1){
					String numero = datas[ligne][0].toString();
					auto.setText(numero);
					dialog.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

}
