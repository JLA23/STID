package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.Devis.Devis;
import View.Devis.SearchDevis;

public class ActionRechercher implements ActionListener {

	private Devis devis;
	private String f;
	private JFrame fenetre;

	public ActionRechercher(Devis d, JFrame frame, String fonction) {
		this.devis = d;
		this.f = fonction;
		this.fenetre = frame;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (f.equals("Modif")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications ?",
					"Quitter devis", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionValider(devis).valider();
			}
			if (option != JOptionPane.CANCEL_OPTION) {
				devis.dispose();
				new SearchDevis(devis.getBase(), fenetre, true, f);
			}
		} else if (f.equals("Suppr")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le devis ?", "Quitter devis",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				new ActionSuppr(devis).suppr();
			}
			if (option != JOptionPane.CANCEL_OPTION) {
				devis.dispose();
				new SearchDevis(devis.getBase(), fenetre, true, f);
			}
		}

		else if (f.equals("Recherche")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le devis ?", "Quitter devis",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				devis.dispose();
				new SearchDevis(devis.getBase(), fenetre, true, f);
			}
		}
	}
}
