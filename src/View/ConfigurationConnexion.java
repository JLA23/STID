package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BDD.Base;
import components.ConfigurationFichier;
import components.Decryptage;


public class ConfigurationConnexion extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();

	public ConfigurationConnexion(){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0 (Configuration)");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Adresse BDD");
	    pane.add(label);
	    JTextField adresse = new JTextField();
	    adresse.setPreferredSize(new Dimension(100, 25));
	    pane.add(adresse);
	    
	    JPanel pane2 = new JPanel();
	    pane2.setPreferredSize(new Dimension(100, 300));;
	    JLabel label2 = new JLabel("Nom BDD");
	    pane2.add(label2);
	    JTextField bdd = new JTextField();
	    bdd.setPreferredSize(new Dimension(100, 25));
	    pane2.add(bdd);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    pane3.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(adresse, bdd, this));
	    
	    
	    this.add(pane);
	    this.add(pane2);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	private class ActionValider implements ActionListener {
		private JTextField bdd;
		private JTextField adresse;
		private JFrame frame;
		
		ActionValider(JTextField adresse,JTextField bdd, JFrame frame){
			this.bdd = bdd;
			this.adresse = adresse;
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Valider")) {
				frame.dispose();
				new ConfigurationFichier(adresse.getText(), bdd.getText());
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
	}
}
