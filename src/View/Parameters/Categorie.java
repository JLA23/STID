package View.Parameters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import Controller.RowListener;
import Controller.Search;
import View.SearchList.SearchList;

public class Categorie extends SearchList {

	private static final long serialVersionUID = 1L;
	private SearchList list;

	public Categorie(Base base, JFrame fenetre) {
		super(base, fenetre);
		this.setPreferredSize(new Dimension(500, 480));
		this.list = this;
		data = null;
		data = donnees.liste("t.CodeTypePersonne, t.LibelleTypePersonne", "typepers as t", null);
		if (data != null) {
			this.bdd = base;
			// this.sd = select;
			this.frame = fenetre;
			this.setTitle("STID Gestion 2.0 (Liste des Catégories de Personne)");
			model = new DefaultTableModel();
			model.addColumn("Code");
			model.addColumn("Libellé");
			for (int m = 0; m < data.length; m++) {
				model.addRow(data[m]);
			}
			RowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

			// Construct our table to hold our list of layers
			layerTable = new JTable(model) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};
			layerTable.setRowSorter(sorter);
			layerTable.getTableHeader().setReorderingAllowed(false);
			layerTable.getColumnModel().getColumn(0).setPreferredWidth(300);
			layerTable.getColumnModel().getColumn(1).setPreferredWidth(300);
			layerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			centrerTable(layerTable);
			JScrollPane scroll = new JScrollPane(layerTable);
			scroll.setPreferredSize(new Dimension(480, 400));
			layerPanel.add(scroll, c);
			JButton inserer = new JButton("Insérer");
			JButton modifier = new JButton("Modifier");
			JButton fermer = new JButton("fermer");
			layerPanel.add(search, BorderLayout.EAST);
			layerPanel.add(inserer, BorderLayout.SOUTH);
			layerPanel.add(modifier, BorderLayout.SOUTH);
			layerPanel.add(fermer, BorderLayout.SOUTH);
			inserer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					list.dispose();
					new InsertModifCategorie(frame, bdd);

				}

			});
			modifier.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int ligne = layerTable.getSelectedRow();
					String numero = data[layerTable.convertRowIndexToModel(ligne)][0].toString();
					list.dispose();
					new InsertModifCategorie(frame, bdd, numero);
					
				}
				
			});
			fermer.addActionListener(new ActionFermer(this));
			// retour.addActionListener(new RetourAction(this, "Devis",
			// fonction));
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

	/*
	 * public SelectDevis getSd() { return sd; }
	 * 
	 * public void setSd(SelectDevis sd) { this.sd = sd; }
	 */

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
