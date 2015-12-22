package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import View.Calculatrice.Calculatrice;

public class ActionCalculatrice implements ActionListener {
	protected JTextField casse;

	public ActionCalculatrice(JTextField field) {
		this.casse = field;
	}

	public void actionPerformed(ActionEvent e) {
		Calculatrice c = new Calculatrice(null, "Calculatrice", true);
		casse.setText(c.getValeur());
	}
}