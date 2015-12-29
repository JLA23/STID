package Controller.Commandes;

import javax.swing.JFormattedTextField;
import Model.Calcul;
import View.Commandes.Commandes;

public class TestContenu {
	public TestContenu(Commandes commandes, JFormattedTextField jtext, int methode) {
		if (jtext.getText().toString().isEmpty() || jtext.getText().toString().equals("")) {
			jtext.setText("0,00");
		} else if (!jtext.getText().contains(",")) {
			jtext.setText(jtext.getText() + ",00");
		} else if(jtext.getText().split(",")[1].length() == 1){
			jtext.setText(jtext.getText() + "0");
		}
		else {
			jtext.setText((Math
					.round(((Double.parseDouble(jtext.getText().replaceAll(",", "\\."))) + 0.004) * Math.pow(10, 2))
					/ Math.pow(10, 2) + "").replaceAll("\\.", ","));
		}
		if (methode == 1) {
			new Calcul().calculerCommande(commandes);
		} else if (methode == 2) {
			new Calcul().calculerHeures(commandes);
		} else if (methode == 3) {
			new Calcul().calculerResteCommande(commandes);
		}
	}
}
