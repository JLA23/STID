package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import View.Calculatrice.Calculatrice;

public class ActionCalculatrice implements ActionListener {
	protected JTextField casse;
	protected JFrame f;

	public ActionCalculatrice(JTextField field, JFrame frame) {
		this.casse = field;
		this.f = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Calculatrice c = new Calculatrice(f, "Calculatrice", true);
		casse.setText(c.getValeur());
	}
}