package Controller.Devis.NewDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import View.Devis.Devis;

public class ActionValider implements ActionListener {
	
	private Devis devis;
	
	public ActionValider(Devis d){
		this.devis = d;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		valider();
	}
	public void valider(){
		if(!devis.getDonnees().existNumDevis(devis.getjNumDevis().getText())){
			if(!devis.getNumClient().getText().equals("") && devis.getDonnees().existClient(devis.getNumClient().getText())){
				String [] re = devis.getValeurDevises().get(devis.getDevises().getSelectedItem());
				devis.getBase().insert("Devis", devis.getjNumDevis().getText() + ", " + devis.getNumClient().getText() + ", null, '" +new SimpleDateFormat("yyyy/MM/dd").format(devis.getjDate().getDate()) + "', '" + devis.getjLibelle().getText()
				+  "', " + devis.getjFournitures().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjCout().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjHeureAtelier().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjHeureSite().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjPrefabrication().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjPrevu().getText().replaceAll(",", "\\.")
				+ ", " + devis.getjCommande().getText().replaceAll(",", "\\.")
				+ ", " + re[0] + ", (Select tauxchange from taux where id in (Select typetva as id from clients where numclient = " + devis.getNumClient().getText() + "))");
				JOptionPane.showMessageDialog(null, "Devis enregistré !");
				devis.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Numéro de Client inconnu ou vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Numéro de Devis existant", "ATTENTION", JOptionPane.WARNING_MESSAGE);
		}	
    }
}