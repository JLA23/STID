package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Pointage.HeureSpe;
import View.Pointage.SaisieHeureSpe;

public class AjoutSaisie implements ActionListener{
	
	protected SaisieHeureSpe spe;
	protected HeureSpe hr;
	
	public AjoutSaisie(SaisieHeureSpe spe, HeureSpe hr){
		this.spe = spe;
		this.hr = hr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String res = insert();
		if(res.equals("ok")){
			spe.dispose();
			new HeureSpe(hr.getBase(), hr.getFrame());
		}
	}
	
	public String insert(){
		String res = null;
		if(!hr.getDonnees().exist("heurespe", "Annee, NumHeureSpe", "Annee = " + spe.getjAnnee().getText() + " and NumHeureSpe = " + spe.getjNumero().getText())){
			if(!spe.getjLibelle().getText().isEmpty()){
				if(!spe.getJhPerdue().getText().equals("0,00") || !spe.getJhRecup().getText().equals("0,00")){
					hr.getBase().insert("heurespe", spe.getjAnnee().getText() + ", " + spe.getjNumero().getText() + ", '" + spe.getjLibelle().getText() + "', " + spe.getJhRecup().getText().replaceAll(",", "\\.") + ", " + spe.getJhPerdue().getText().replaceAll(",", "\\."));
					JOptionPane.showMessageDialog(null, "Heure Spe enregistré !");
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
