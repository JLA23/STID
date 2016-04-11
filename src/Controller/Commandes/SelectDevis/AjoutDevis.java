package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Commandes.SelectDevis;

public class AjoutDevis implements ActionListener {
	private SelectDevis select;

	public AjoutDevis(SelectDevis sd) {
		this.select = sd;
	}

	public void actionPerformed(ActionEvent e) {
		if (!select.getjNumDevis().getText().isEmpty()
				&& select.getDonnees().exist("devis", "NumDevis", "NumDevis = " + select.getjNumDevis().getText())) {
			if (!select.containt(select.getjNumDevis().getText())) {
				if (!select.getDonnees().lier("numCommande", "devis", "numDevis = " + select.getjNumDevis().getText())) {
					String[] devis = select.getDonnees().fiche("*, c.nomclient", "devis as d, clients as c", "d.numclient = c.numclient and d.numDevis = " +select.getjNumDevis().getText());
					String[] res = new String[4];
					res[0] = devis[0];
					res[1] = devis[1];
					res[2] = devis[devis.length - 1];
					res[3] = devis[4];
					boolean valide = numClientIdentique(res[1]);
					if (!valide) {
						int option = JOptionPane.showConfirmDialog(null,
								"Les numèros de client sont diffèrents.\n Voulez-vous le rajouter à la liste ?",
								"Numéro Client différent", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (option == JOptionPane.YES_OPTION) {
							select.getModel().addRow(res);
							select.getjNumDevis().setText("");
						}
					} else {
						select.getModel().addRow(res);
						select.getjNumDevis().setText("");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Devis est lié à une autre commande", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vous l'avez déjà ajoutè", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Le Numèro de Devis est vide ou incorrect", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean numClientIdentique(String num) {
		int ligne = select.getModel().getRowCount();
		boolean valide = true;
		if (ligne > 0) {
			for (int i = 0; i < ligne; i++) {
				if (!num.equals((String) select.getModel().getValueAt(i, 1))) {
					valide = false;
					break;
				}
			}
		}
		return valide;

	}

}
