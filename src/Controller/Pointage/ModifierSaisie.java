package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Pointage.HeureSpe;
import View.Pointage.SaisieHeureSpe;

public class ModifierSaisie implements ActionListener{
	
	protected SaisieHeureSpe spe;
	protected HeureSpe hr;
	
	public ModifierSaisie(SaisieHeureSpe spe, HeureSpe hr){
		this.spe = spe;
		this.hr = hr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String res = modif();
		if(res.equals("ok")){
			spe.dispose();
			new HeureSpe(hr.getBase(), hr.getFrame());
		}
	}
	
	public String modif(){
		String res = null;
		if(hr.getDonnees().exist("heurespe", "Annee, NumHeureSpe", "Annee = " + spe.getjAnnee().getText() + " and NumHeureSpe = " + spe.getjNumero().getText())){
			if(!spe.getjLibelle().getText().isEmpty()){
				if(!spe.getJhPerdue().getText().equals("0,00") || !spe.getJhRecup().getText().equals("0,00")){
					hr.getBase().update("heurespe", "LblHeureSpeciale = '" + spe.getjLibelle().getText() + "', HrPrev = " + spe.getJhRecup().getText().replaceAll(",", "\\.") + ", HrPass = " + spe.getJhPerdue().getText().replaceAll(",", "\\."), "Annee = " + spe.getjAnnee().getText() + " AND NumHeureSpe = " + spe.getjNumero().getText());
					JOptionPane.showMessageDialog(null, "Heure Spe modifié !");
					res = "ok";
				}
				else{
					JOptionPane.showMessageDialog(null, "Erreur : Heures Recupéré et Perdues sont à 0 ", "ATTENTION", JOptionPane.WARNING_MESSAGE);
					res = "error";
				}
			}
			else {
			JOptionPane.showMessageDialog(null, "Erreur : Aucun Libellé ", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			res = "error";
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Erreur : les références existent déjà ", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			res = "error";
		}
		return res;
	}

}
