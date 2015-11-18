package View.ModifUser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BDD.Base;
import Controller.PasswordCreate;
import View.Identification;

public class AjoutUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public AjoutUser(Base base){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0 (New User)");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
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
	    JLabel label2 = new JLabel("Type de Compte");
	    pane2.add(label2);
	    JComboBox<String> box = new JComboBox<>();
	    ResultSet res = base.Select("type", "compte", null);
	    try {
			while(res.next()){
				box.addItem(res.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    box.setPreferredSize(new Dimension(100, 25));
	    pane2.add(box);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    pane3.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(base, pseudo, box.getSelectedItem().toString(), this));
	    
	    
	    this.add(pane);
	    this.add(pane2);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	private class ActionValider implements ActionListener {
	private JTextField pseudo;
	private JFrame frame;
	private JDialog dialog;
	private Base base;
	private String typeComptes;
	
	ActionValider(Base bdd, JTextField pseudo, String typeCompte, JDialog dialog){
		this.pseudo = pseudo;
		this.dialog = dialog;
		this.base = bdd;
		this.typeComptes = typeCompte;
	}
		public void actionPerformed(ActionEvent e) {
			String mdp = new PasswordCreate().create();
			String res = base.newUtilisateur(pseudo.getText(), mdp, typeComptes);
			dialog.dispose();
			if(res.equals("Utilisateur ajouté avec succés !")){
				dialog.dispose();
				JTextArea text = new JTextArea(res + "\n\nPseudo : " + pseudo.getText() +"\n\nMot de passe : " + mdp);
				JOptionPane.showMessageDialog(null, text);
			}
			else{
				JOptionPane.showMessageDialog(null, "Attention !\nUne erreur s'est produite,\n Veuillez réessayer ultérieurement", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		
		}
	}
}

