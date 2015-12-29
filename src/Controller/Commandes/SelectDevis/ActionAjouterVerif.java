package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Commandes.SelectDevis;

public class ActionAjouterVerif implements ActionListener {
	private SelectDevis select;
	
	public ActionAjouterVerif(SelectDevis sd) {
		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		if(!select.getjNumDevis().getText().isEmpty() && select.getDonnees().existNumDevis(select.getjNumDevis().getText())){
			if(!select.containt(select.getjNumDevis().getText())){
			String [] devis = select.getDonnees().devis(select.getjNumDevis().getText());
			String [] res = new String [3];
			res[0] = devis[0];
			res[1] = devis[1];
			res[2] = devis[4];
			select.getModel().addRow(res);
			select.getjNumDevis().setText("");
			}
			else {
				JOptionPane.showMessageDialog(null, "Vous l'avez déjà ajoutè", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Le Numèro de Devis est vide ou incorrect", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
