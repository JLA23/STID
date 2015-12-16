package Controller.Devis.NewDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Devis.Devis;
import View.Devis.NewDevis;

public class ActionNouveau implements ActionListener {
	
	private Devis devis;
	
	public ActionNouveau(Devis d){
		this.devis = d;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new ActionValider(devis).valider();
		if(!devis.isShowing()){
			new NewDevis(devis.getBase());
		}
	}
}
