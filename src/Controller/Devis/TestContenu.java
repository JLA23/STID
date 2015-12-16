package Controller.Devis;

import javax.swing.JFormattedTextField;

import Model.Calcul;
import View.Devis.Devis;

public class TestContenu {
	public TestContenu(Devis devis, JFormattedTextField jtext, int methode) {
		if (jtext.getText().toString().isEmpty() || jtext.getText().toString().equals("")) {
			jtext.setText("0,00");
		} else if (!jtext.getText().contains(",")) {
			jtext.setText(jtext.getText() + ",00");
		} else {
			jtext.setText((Math
					.round(((Double.parseDouble(jtext.getText().replaceAll(",", "\\."))) + 0.004) * Math.pow(10, 2))
					/ Math.pow(10, 2) + "").replaceAll("\\.", ","));
		}
		if (methode == 1) {
			new Calcul().calculerDevis(devis);
		} else if (methode == 2) {
			new Calcul().calculerHeures(devis);
		} else if (methode == 3) {
			new Calcul().calculerResteCommande(devis);
		}
	}
}
