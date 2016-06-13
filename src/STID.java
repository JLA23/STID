import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import BDD.Base;
import View.ConfigurationConnexion;
import View.Identification;

public class STID {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	File fichier = new File("Configuration.conf");
	        	if(!fichier.exists()){
	        		new ConfigurationConnexion();
	        	}
	        	else{			
	        		String [] line = new String [2];
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
	        		Base bdd = new Base(line[0],line[1], "test", "test");
					String message = bdd.connect();
					System.out.println(message);
					if(message.equals("Connexion ètablie")){
						bdd.close();
						new Identification(line[0],line[1]);
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
