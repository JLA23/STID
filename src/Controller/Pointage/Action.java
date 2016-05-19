package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Pointage.HeureSpe;
import View.Pointage.SaisieHeureSpe;

public class Action implements ActionListener{
	
	protected HeureSpe spe;
	protected String event;
	
	public Action (HeureSpe hs, String action){
		this.spe = hs;
		this.event = action;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(event.equals("Ajouter")){
			new SaisieHeureSpe(spe);
		}
		else{
			int ligne = spe.getLayerTable().getSelectedRow();
			if (ligne != -1) {
				String numero = spe.getData()[spe.getLayerTable().convertRowIndexToModel(ligne)][4].toString();
				new SaisieHeureSpe(spe, numero);
			}
		}
		
	}

}
