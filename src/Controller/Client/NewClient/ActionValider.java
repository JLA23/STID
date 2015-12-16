package Controller.Client.NewClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import View.Clients.Client;

public class ActionValider implements ActionListener {
	
	private Client client;
	
	public ActionValider(Client d){
		this.client = d;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}
	public void valider(){
		if(!client.getDonnees().existNumClient(client.getjNumClient().getText())){
			if(mail(client.getjMail().getText())){
				if(!client.getjAdresses()[0].getText().isEmpty() && !client.getjAdresses()[1].getText().isEmpty() && !client.getjAdresses()[2].getText().isEmpty()){
					String [] re = client.getValeurTaux().get(client.getBoxTva().getSelectedItem());
					String query = client.getjNumClient().getText() + ", '" + client.getjName() +"', '";
					for(int i = 0; i< client.getjAdresses().length; i++){
						query += client.getjAdresses()[i].getText() + "', '";
					}
					query += client.getjMail() + "', " + client.getjDelais().getText() + ", " + client.getjDelais().getText() + ", " + client.getjJourSuivant().getText() + ", ";
					if(client.getBr1().isSelected()){
						query += 1 + ", ";
					}
					else if(client.getBr2().isSelected()){
						query += 2 + ", ";
					}
					query += client.getjNbExemplaire().getText() + ", " + client.getjJourSuivant().getText() + ", " + re[0] + ", " + client.getJnumTVA().getText();
					client.getBase().insert("client", query);
					JOptionPane.showMessageDialog(null, "client enregistré !");
					client.dispose();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Numéro de client existant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	private boolean mail(String mail){
		boolean resultat = false;
		if(mail.contains("@")){
			String [] tmp = mail.split("@");
			if(tmp[1].contains("\\.")){
				resultat = true;
			}
		}
		return resultat;
	}
}