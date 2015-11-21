package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BDD.Base;
import View.Options.ClickDroit;

public class Identification extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Identification(String adresse, String base){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Pseudo");
	    pane.add(label);
	    JTextField pseudo = new JTextField();
	    pseudo.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(pseudo, true, true);
	    pane.add(pseudo);
	    
	    JPanel pane2 = new JPanel();
	    pane2.setPreferredSize(new Dimension(100, 300));;
	    JLabel label2 = new JLabel("Mot de Passe");
	    pane2.add(label2);
	    JPasswordField mdp = new JPasswordField();
	    mdp.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(mdp, true, true);
	    pane2.add(mdp);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton); 
	    pane3.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(adresse, base, pseudo, mdp, this));
	    
	    
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
		private JTextField pseudo;
		private JPasswordField mdp;
		private JFrame frame;
		private String motdepasse, adresse, base;
		
		ActionValider(String adresse, String base, JTextField pseudo,JPasswordField mdp, JFrame frame){
			this.pseudo = pseudo;
			this.mdp = mdp;
			this.frame = frame;
			this.adresse = adresse;
			this.base = base;
		}
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Valider")) {
				motdepasse = new String(mdp.getPassword());
				if(!pseudo.getText().equals("") && !motdepasse.equals("")){
					Base bdd = new Base(this.adresse, this.base, this.pseudo.getText(), this.motdepasse);
					String reponse = bdd.connect();
					if(reponse.equals("Connexion ètablie")){
						String typeCompte = bdd.typeCompte(pseudo.getText());
						if(typeCompte.equals("Admin") || typeCompte.equals("User")){
							frame.dispose();
							new Interface(bdd, typeCompte);
						}
						else{
							JOptionPane.showMessageDialog(null, "Erreur : Aucune donnée trouver\n Appeler Technicien", "ATTENTION", JOptionPane.WARNING_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Erreur : Utilisateur / Mot de passe", "ATTENTION", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Attention !\nL'un des champs est vide", "ATTENTION", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
