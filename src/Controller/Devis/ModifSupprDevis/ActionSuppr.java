package Controller.Devis.ModifSupprDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.Devis.Devis;

public class ActionSuppr implements ActionListener {
	
	private Devis devis;

	public ActionSuppr(Devis frame){
		this.devis = frame;
	}
@Override
public void actionPerformed(ActionEvent arg0) {
	suppr();
}

public void suppr(){
	if(devis.getDonnees().existNumDevis(devis.getjNumDevis().getText())){
		if(!devis.getNumClient().getText().equals("") && devis.getDonnees().existClient(devis.getNumClient().getText())){
			if(devis.getDonnees().lieeCommande(devis.getjNumDevis().getText())){
				devis.getBase().delete("devis", "numDevis = "+ devis.getjNumDevis().getText());
				JOptionPane.showMessageDialog(null, "Devis supprimé !");
				devis.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "Erreur : Une commande est liée au devis", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
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
