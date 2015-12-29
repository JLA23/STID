package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Commandes.SelectDevis;

public class ActionSupprimer implements ActionListener{
private SelectDevis select;
	
	public ActionSupprimer(SelectDevis sd) {
		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		int ligne = select.getLayerTable().getSelectedRow();
		select.getModel().removeRow(ligne);

	}

}
