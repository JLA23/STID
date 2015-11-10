import javax.swing.JOptionPane;

import BDD.Base;
import View.Identification;
import View.Devis.NewDevis;

public class STID {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Base bdd = new Base("test.db");
	        	if(bdd.isConnecte()){
	        		new NewDevis(bdd);
	        	}
	        	else{
	        		JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données");
	        	}
	        }
	        	
	    });
	}
}
