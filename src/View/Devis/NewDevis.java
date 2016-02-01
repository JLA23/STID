package View.Devis;
import javax.swing.JFrame;
import BDD.Base;
import Controller.ActionFermer;
import Controller.Devis.NewDevis.ActionCreateClient;
import Controller.ActionNouveau;
import Controller.Devis.NewDevis.ActionValiderDevis;

public class NewDevis extends Devis{
	
	private static final long serialVersionUID = 1L;

	public NewDevis(Base bdd, JFrame frame){
		super(bdd, frame);
		valider.addActionListener(new ActionValiderDevis(this));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionNouveau(this, "Devis"));
		newClient.addActionListener(new ActionCreateClient(this));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}


