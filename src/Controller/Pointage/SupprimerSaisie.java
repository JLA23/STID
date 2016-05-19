package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Pointage.HeureSpe;


public class SupprimerSaisie implements ActionListener{
	
	protected HeureSpe hr;
	
	public SupprimerSaisie(HeureSpe hr){
		this.hr = hr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String res = suppr();
		if(res.equals("ok")){
			Object [][] data = hr.getDonnees().liste("LblHeureSpeciale, HrPrev, HrPass, Round(HrPrev - HrPass, 2), NumHeureSpe", "heurespe", "Annee = " + hr.getjAnnee().getText());
			hr.getSaisie().actualiser(data);
		}
	}
	
	public String suppr(){
		String res = null;
		int ligne = hr.getLayerTable().getSelectedRow();
		if (ligne != -1) {
			String numero = hr.getData()[hr.getLayerTable().convertRowIndexToModel(ligne)][4].toString();
			if(hr.getDonnees().exist("heurespe", "Annee, NumHeureSpe", "Annee = " + hr.getjAnnee().getText() + " AND NumHeureSpe = " + numero)){
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer la saisie ?",
						"Supprimer Saisie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					hr.getBase().delete("heurespe", "Annee = " + hr.getjAnnee().getText() + " AND NumHeureSpe = " + numero);
					JOptionPane.showMessageDialog(null, "Heure Spe supprimé !");
					res = "ok";
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Erreur : Aucune Données correspondante", "ATTENTION", JOptionPane.WARNING_MESSAGE);
				res = "error";
			}
		}
			
		return res;
	}
		
}
