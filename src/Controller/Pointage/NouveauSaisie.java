package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Pointage.HeureSpe;
import View.Pointage.SaisieHeureSpe;

public class NouveauSaisie implements ActionListener {
	
	protected SaisieHeureSpe spe;
	protected HeureSpe hr;
	
	public NouveauSaisie(SaisieHeureSpe spe, HeureSpe hr){
		this.spe = spe;
		this.hr = hr;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String res = new AjoutSaisie(spe, hr).insert();
		if(res.equals("ok")){
			spe.getjLibelle().setText("");
			spe.getjNumero().setText(hr.getDonnees().newNum("heurespe", "NumHeureSpe", "Annee = " + spe.getjAnnee().getText()) + "");
			spe.getJhPerdue().setText("0,00");
			spe.getJhRecup().setText("0,00");
		}
		

	}
	

}
