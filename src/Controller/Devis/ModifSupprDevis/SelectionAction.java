package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import BDD.Base;
import View.Devis.ModifDevis;
import View.Devis.SupprDevis;

public class SelectionAction implements ActionListener {
	private JDialog dialog;
	private JTable tables;
	private Object[][] datas;
	private Base bdd;
	private String f;

	public SelectionAction(JDialog dialog, JTable table, Object[][] datas, Base base, String fonction) {
		this.dialog = dialog;
		this.tables = table;
		this.datas = datas;
		this.bdd = base;
		this.f = fonction;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Valider")) {
			int ligne = tables.getSelectedRow();
			if (ligne != -1) {
				String numero = datas[ligne][0].toString();
				dialog.dispose();
				try {
					if(f.equals("Modif")){
						new ModifDevis(bdd, numero);
					}
					else if(f.equals("Suppr")){
						new SupprDevis(bdd, numero);
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aucune ligne de s�lectionn�", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}