package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;


import View.Calculatrice.Calculatrice;

public class ActionCalculatrice implements ActionListener {
	
	protected JFormattedTextField casse;
	protected int met;
	protected String classe;
	protected Object frame;

	public ActionCalculatrice(Object d, JFormattedTextField field, int methode, String typeClasse) {
		this.casse = field;
		this.frame = d;
		this.met = methode;
		this.classe = typeClasse;
	}

	public void actionPerformed(ActionEvent e) {
		Calculatrice c = new Calculatrice(null, "Calculatrice", true);
		casse.setText(c.getValeur());
		new TestContenu(frame, casse, met, classe);
	}
}