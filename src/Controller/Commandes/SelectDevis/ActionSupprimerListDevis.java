package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Commandes.SelectDevis;

public class ActionSupprimerListDevis implements ActionListener{
private SelectDevis select;
	
	public ActionSupprimerListDevis(SelectDevis sd) {
		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		int ligne = select.getLayerTable().getSelectedRow();
		if(ligne != -1){
			select.getModel().removeRow(ligne);
		} else {
			JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
