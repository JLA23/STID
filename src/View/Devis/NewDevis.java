package View.Devis;
import BDD.Base;
import Controller.Devis.ActionFermer;
import Controller.Devis.NewDevis.ActionNouveau;
import Controller.Devis.NewDevis.ActionValider;

public class NewDevis extends Devis{
	
	private static final long serialVersionUID = 1L;

	public NewDevis(Base bdd){
		super(bdd);
		valider.addActionListener(new ActionValider(this));
		fermer.addActionListener(new ActionFermer(this));
		nouveau.addActionListener(new ActionNouveau(this));
	}

}


