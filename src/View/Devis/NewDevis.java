package View.Devis;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.Devis.NewDevis.ActionCreateClient;
import Controller.Devis.NewDevis.ActionNouveau;
import Controller.Devis.NewDevis.ActionValider;

public class NewDevis extends Devis{
	
	private static final long serialVersionUID = 1L;

	public NewDevis(Base bdd, JFrame frame){
		super(bdd, frame);
		valider.addActionListener(new ActionValider(this));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionNouveau(this));
		newClient.addActionListener(new ActionCreateClient(this));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}


