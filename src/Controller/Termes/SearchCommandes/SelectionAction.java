package Controller.Termes.SearchCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import BDD.Base;
import View.Termes.NewTerme;


public class SelectionAction implements ActionListener {
	private JDialog dialog;
	private JTable tables;
	private Object[][] datas;
	private Base bdd;
	private JFrame fenetre;

	public SelectionAction(JDialog dialog, JTable table, Object[][] datas, Base base, JFrame frame) {
		this.dialog = dialog;
		this.tables = table;
		this.datas = datas;
		this.bdd = base;
		this.fenetre = frame;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Valider")) {
			int ligne = tables.getSelectedRow();
			if (ligne != -1) {
				String numero = datas[ligne][0].toString();
				dialog.dispose();
				new NewTerme(bdd, fenetre, numero);
			} else {
				JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
