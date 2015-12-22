package Controller.Client.ModifSupprClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Clients.Client;
import View.Clients.SearchClient;

public class ActionRechercher implements ActionListener {
	
	private Client client;
	private String f;

	public ActionRechercher(Client d, String fonction){
		this.client = d;
		this.f = fonction;
	}
	public void actionPerformed(ActionEvent arg0) {
		if(f.equals("Modif")){
			int option = JOptionPane.showConfirmDialog(
				  null,
				  "Voulez-vous enregistrer les modifications ?", 
				  "Quitter devis",
				  JOptionPane.YES_NO_CANCEL_OPTION,
				  JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.YES_OPTION){
				new ActionValider(client).valider();
			}
			if(option != JOptionPane.CANCEL_OPTION){
				client.dispose();
				new SearchClient(client.getBase(), null, f);
			}
		}
		else if(f.equals("Suppr")){
			int option = JOptionPane.showConfirmDialog(
					  null,
					  "Voulez-vous supprimer le devis ?", 
					  "Quitter devis",
					  JOptionPane.YES_NO_CANCEL_OPTION,
					  JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.YES_OPTION){
					new ActionSuppr(client).suppr();
				}
				if(option != JOptionPane.CANCEL_OPTION){
					client.dispose();
					new SearchClient(client.getBase(), null, f);
			}
		}
	}
}
