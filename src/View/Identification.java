package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BDD.Base;

public class Identification extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Identification(Base bdd){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Pseudo");
	    pane.add(label);
	    JTextField pseudo = new JTextField();
	    pseudo.setPreferredSize(new Dimension(100, 25));
	    pane.add(pseudo);
	    
	    JPanel pane2 = new JPanel();
	    pane2.setPreferredSize(new Dimension(100, 300));;
	    JLabel label2 = new JLabel("Mot de Passe");
	    pane2.add(label2);
	    JPasswordField mdp = new JPasswordField();
	    mdp.setPreferredSize(new Dimension(100, 25));
	    pane2.add(mdp);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    pane3.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(bdd, pseudo, mdp, this));
	    
	    
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
		private Base bdd;
		private JTextField pseudo;
		private JPasswordField mdp;
		private JFrame frame;
		private String motdepasse;
		
		ActionValider(Base bdd, JTextField pseudo,JPasswordField mdp, JFrame frame){
			this.bdd = bdd;
			this.pseudo = pseudo;
			this.mdp = mdp;
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Valider")) {
		      String [] res = bdd.connection(pseudo.getText());
		      motdepasse = new String(mdp.getPassword());
		      if(res.length == 1 && res[0].equals("Error")){
		    	  JOptionPane.showMessageDialog(null, "Attention ! Erreur\nVeuillez réesayer plutard");
		      }
		      else if(res[0].equals("vide")){
		    	  JOptionPane.showMessageDialog(null, "Erreur d'Utilisateur / Mots de passe");
		      }
		      else {
		    	  if(!res[0].equals(pseudo.getText()) || !res[1].equals(motdepasse)){
		    		  JOptionPane.showMessageDialog(null, "Erreur d'Utilisateur / Mots de passe");
		    	  }
		    	  
		    	  else if (res[0].equals(pseudo.getText()) && res[1].equals(motdepasse)){
		    		  frame.dispose();
		    		  new Interface(bdd, res[2]);
		    	  }
		      }
			}
		}
	}
}
