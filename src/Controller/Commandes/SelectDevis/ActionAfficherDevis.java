package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;

import View.Commandes.SelectDevis;
import View.Devis.LookDevis;

public class ActionAfficherDevis implements ActionListener {

	private SelectDevis select;

	public ActionAfficherDevis(SelectDevis sd) {

		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		int ligne = select.getLayerTable().getSelectedRow();
		if (ligne != -1) {
			try {
				new LookDevis(select.getBase(), (String)select.getModel().getValueAt(ligne, 0), select);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucune ligne de sélectionné", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
