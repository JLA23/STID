package Controller.Commandes.ModifSupprCommandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Commandes.Commandes;

public class ActionValider implements ActionListener {
		
		private Commandes commandes;
	
		public ActionValider(Commandes frame){
			this.commandes = frame;
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}
	
	public void valider(){
		if(commandes.getDonnees().existNumCommande(commandes.getjNumCommande().getText())){
			if(!commandes.getNumClient().getText().equals("") && commandes.getDonnees().existClient(commandes.getNumClient().getText())){
				String [] re = commandes.getValeurDevises().get(commandes.getDevises().getSelectedItem());
				commandes.getBase().update("Commandes", "numClient = " + commandes.getNumClient().getText() + ", CdeComClient = '" + commandes.getjNumCommandeClient().getText() + "', Lblcommandes = '" + commandes.getjLibelle().getText()
				+  "', MntFour = " + commandes.getjFournitures().getText().replaceAll(",", "\\.")
				+ ", CoutMO = " + commandes.getjCout().getText().replaceAll(",", "\\.")
				+ ", HeureSite = " + commandes.getjHeureSite().getText().replaceAll(",", "\\.")
				+ ", HeureAtelier = " + commandes.getjHeureAtelier().getText().replaceAll(",", "\\.")
				+ ", Corem = " + commandes.getCheck().isSelected()
				+ ", Prefabrication = " + commandes.getjPrefabrication().getText().replaceAll(",", "\\.")
				+ ", MatierePrevu = " + commandes.getjPrevu().getText().replaceAll(",", "\\.")
				+ ", MatiereCommande = " + commandes.getjCommande().getText().replaceAll(",", "\\.")
				+ ", CodeDevise = " + re[0], "numCommandes = " + commandes.getjNumCommande().getText());
				comparaisonDevis();
				JOptionPane.showMessageDialog(null, "commandes validé !");
				commandes.dispose();
				commandes.getFenetre().setEnabled(true);
				commandes.getFenetre().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	public void comparaisonDevis(){
		String [] ancienNum = new String [commandes.getDevisdelaCommande().length];
		String [] num = new String[commandes.getListDevis().size()];
		for (int i = 0; i < commandes.getListDevis().size(); i++) {
			commandes.getBase().update("Devis", "numcommande = " + commandes.getjNumCommande().getText(), "numdevis = " + (String)commandes.getListDevis().get(i)[0]);
			num[i] = (String)commandes.getListDevis().get(i)[0];
		}
		for (int i = 0; i < commandes.getDevisdelaCommande().length; i++) {
			ancienNum[i] = (String)commandes.getDevisdelaCommande()[i][0];
		}
		for(int i = 0; i < ancienNum.length; i++){
			boolean ok = false;
			for(int j = 0; j < num.length; j++){
				if(ancienNum[i].equals(num[j])){
					ok = true;
					break;
				}
			}
			if(!ok){
				commandes.getBase().update("Devis", "numcommande = null", "numdevis = " + ancienNum[i]);
			}
		}
		
	}
}
