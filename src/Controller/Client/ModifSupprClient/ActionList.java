package Controller.Client.ModifSupprClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import BDD.Base;
import View.Clients.SearchClientList;

public class ActionList implements ActionListener {
	private JDialog frame;
	private Base bdd;
	private String fonction;
	
	public ActionList(JDialog dialog, Base base, String f){
		this.frame = dialog;
		this.bdd = base;
		this.fonction = f;
	}
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new SearchClientList(bdd, null, true, fonction);	
	}
}
