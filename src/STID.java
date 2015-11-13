import javax.swing.JOptionPane;

import BDD.Base;
import View.Identification;

public class STID {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Base bdd = new Base("test.db");
	        	if(bdd.isConnecte()){
	        		new Identification(bdd);
	        	}
	        	else{
	        		JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données");
	        	}
	        }
	        	
	    });
	}
}
