import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import BDD.Base;
import View.ConfigurationConnexion;
import View.Identification;
import components.Decryptage;

public class STID {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	File fichier = new File("Configuration.conf");
	        	if(!fichier.exists()){
	        		new ConfigurationConnexion();
	        	}
	        	else{			
	        		String [] line = new String [4];
	        		try{
	        			BufferedReader buff = new BufferedReader(new FileReader("Configuration.conf"));
	        			
	        			try {
	        				for(int i = 0; i < line.length; i ++){
	        					line[i] = buff.readLine();
	        				}
	        			} finally {
							
							buff.close();
						}
	        		} catch (IOException ioe) {
						JOptionPane.showMessageDialog(null, "Erreur --" + ioe.toString());
	        		}
	        		String base = new Decryptage().decryptage(line[2]);
					String motdepasse = new Decryptage().decryptage(line[3]);
					Base bdd = new Base(line[0], line[1], base, motdepasse);
	        		if(bdd.isConnecte()){
	        			new Identification(bdd);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données");
	        			new ConfigurationConnexion();
	        		}
	        	}
	        }
	        	
	    });
	}
}
