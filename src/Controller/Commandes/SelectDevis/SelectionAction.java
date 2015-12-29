package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import View.Commandes.SelectDevis;

public class SelectionAction implements ActionListener {
	private JDialog dialog;
	private JTable tables;
	private Object[][] datas;
	private SelectDevis select;

	public SelectionAction(JDialog dialog, JTable table, Object[][] datas, SelectDevis sd) {
		this.dialog = dialog;
		this.tables = table;
		this.datas = datas;
		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		int ligne = tables.getSelectedRow();
		if (ligne != -1) {
			if (!select.containt((String) datas[ligne][0])) {
				
				select.getModel().addRow(datas[ligne]);
				dialog.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Vous l'avez déjà ajoutè", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
