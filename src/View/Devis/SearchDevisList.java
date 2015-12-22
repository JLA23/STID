package View.Devis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import BDD.Base;
import Controller.ActionFermer;
import Controller.Devis.ModifSupprDevis.RetourAction;
import Controller.Devis.ModifSupprDevis.SelectionAction;
import Model.Donnees;

public class SearchDevisList extends JDialog {

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension(500, 500);
	protected Base bdd;

	public SearchDevisList(Base bdd, JFrame frame, boolean modale, String fonction) {
		super(frame, null, modale);
		this.bdd = bdd;
		Donnees donnees = new Donnees(bdd);
		Object[][] data = donnees.listeDevis();
		this.setPreferredSize(screenSize);
		this.setTitle("STID Gestion 2.0 (Chercher Devis)");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		JPanel layerPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;

		String[] columns = { "Numèro Devis", "Numèro CLient", "Libelle" };

		// Construct our table to hold our list of layers
		JTable layerTable = new JTable(data, columns) {

			private static final long serialVersionUID = 1L;

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
		JButton retour = new JButton("Retour");
		layerPanel.add(retour, BorderLayout.SOUTH);
		layerPanel.add(valider, BorderLayout.SOUTH);
		layerPanel.add(annuler, BorderLayout.SOUTH);
		valider.addActionListener(new SelectionAction(this, layerTable, data, bdd, fonction));
		retour.addActionListener(new RetourAction(this, bdd, fonction));
		annuler.addActionListener(new ActionFermer(this));
		this.add(layerPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(custom);
	}
}
