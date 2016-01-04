package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

import View.Devis.Devis;

public class ActionValider implements ActionListener {
		
		private Devis devis;
	
		public ActionValider(Devis frame){
			this.devis = frame;
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}
	
	public void valider(){
		if(devis.getDonnees().existNumDevis(devis.getjNumDevis().getText())){
			if(!devis.getNumClient().getText().equals("") && devis.getDonnees().existClient(devis.getNumClient().getText())){
				String [] re = devis.getValeurDevises().get(devis.getDevises().getSelectedItem());
				System.out.println(re.length);
				devis.getBase().update("Devis", "numClient = " + devis.getNumClient().getText() + ", DateDevis = '" +new SimpleDateFormat("yyyy/MM/dd").format(devis.getjDate().getDate()) + "', LblDevis = '" + devis.getjLibelle().getText()
				+  "', MntFour = " + devis.getjFournitures().getText().replaceAll(",", "\\.")
				+ ", CoutMO = " + devis.getjCout().getText().replaceAll(",", "\\.")
				+ ", HeureSite = " + devis.getjHeureSite().getText().replaceAll(",", "\\.")
				+ ", HeureAtelier = " + devis.getjHeureAtelier().getText().replaceAll(",", "\\.")
				+ ", Prefabrication = " + devis.getjPrefabrication().getText().replaceAll(",", "\\.")
				+ ", MatierePrevu = " + devis.getjPrevu().getText().replaceAll(",", "\\.")
				+ ", MatiereCommande = " + devis.getjCommande().getText().replaceAll(",", "\\.")
				+ ", CodeDevise = " + re[0], "numDevis = " + devis.getjNumDevis().getText());
				JOptionPane.showMessageDialog(null, "Devis validé !");
				devis.dispose();
				devis.getFenetre().setEnabled(true);
				devis.getFenetre().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Numéro de Devis inexistant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}	
	}
}
