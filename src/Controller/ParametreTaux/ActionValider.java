package Controller.ParametreTaux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Parameters.ParametreTaux;

public class ActionValider implements ActionListener {

	private ParametreTaux taux;

	public ActionValider(ParametreTaux t) {
		this.taux = t;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}

	public void valider() {
		if (!taux.getjTaux().getText().isEmpty()) {
			taux.getBase().update("taux", "TauxChange = " + taux.getjTaux().getText().replaceAll(",", "\\."), "NomTaux = '" + taux.getjTypeTaux().getSelectedItem().toString() + "'");
			JOptionPane.showMessageDialog(null, "Taux modifié !");
			taux.dispose();
		}
		else{
			JOptionPane.showMessageDialog(null, "Erreur : Valeur vide ou incorrect", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}

	}
}