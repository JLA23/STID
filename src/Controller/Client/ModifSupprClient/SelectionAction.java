package Controller.Client.ModifSupprClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import BDD.Base;
import View.Clients.ModifClient;
import View.Clients.SupprClient;

public class SelectionAction implements ActionListener {
	private JDialog dialog;
	private JTable tables;
	private Object[][] datass;
	private Base bdd;
	private String f;
	
	public SelectionAction(JDialog dialog, JTable table, Object [][] datas, Base base, String fonction){
		this.dialog = dialog;
		this.tables = table;
		this.datass = datas;
		this.bdd = base;
		this.f = fonction;
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Valider")) {
			int ligne = tables.getSelectedRow();
			if(ligne != -1){
				String numero = datass[ligne][0].toString();
				dialog.dispose();
				if(f.equals("Modif")){
					new ModifClient(bdd, numero);
				}
				else if(f.equals("Suppr")){
					new SupprClient(bdd, numero);
				}
				else if(f.equals("Recherche")){
					//new LookClient(bdd, numero);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
