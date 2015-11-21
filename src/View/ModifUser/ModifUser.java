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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BDD.Base;

public class ModifUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public ModifUser(Base base){
	    JComboBox<String> box = new JComboBox<>();
	    ResultSet res = base.Select("pseudo", "users", null);
	    int i = 0;
	    try {
			while(res.next()){
				String temp = res.getString(1);
				if(!temp.equals(base.getPseudo()) || !temp.equals("root")){
					box.addItem(temp);
					i ++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    if(i != 0){
			this.setLayout(new GridLayout(3, 1));
			this.setTitle("STID Gestion 2.0 (Modification User)");
			screenSize.setSize(300, 150);
			this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		    this.setPreferredSize(screenSize);
		    
		    JPanel pane = new JPanel();
		    pane.setPreferredSize(new Dimension(100, 300));
		    JLabel label = new JLabel("Utilisateur");
		    pane.add(label);
	    	box.setPreferredSize(new Dimension(100, 25));
		    pane.add(box);
		    

		    JPanel pane2 = new JPanel();
		    pane2.setPreferredSize(new Dimension(100, 300));;
		    JLabel label2 = new JLabel("Type de Compte");
		    pane2.add(label2);
		    JComboBox<String> box2 = new JComboBox<>();
		    res = base.Select("type", "compte", null);
		    try {
				while(res.next()){
					box2.addItem(res.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    box2.setPreferredSize(new Dimension(100, 25));
		    pane2.add(box2);
	    
	    	JPanel pane3 = new JPanel();
	    	pane3.setPreferredSize(new Dimension(100, 300));
	    	this.setLocationRelativeTo(null);
	    	JButton bouton = new JButton("Valider");
	    	bouton.setMnemonic(KeyEvent.VK_ENTER);
	    	pane3.add(bouton);
	    
	    	bouton.addActionListener(new ActionValider(base, box, box2, this));
	    
	    
	    	this.add(pane);
	    	this.add(pane2);
	    	this.add(pane3);
	    	this.pack();
	    	this.setResizable(false);
	    	this.setVisible(true);
	    	this.setLocationRelativeTo(null);
	    }
	    
	    else{
	    	JOptionPane.showMessageDialog(null, "Attention !\nAucun utilisateur ne peut être modifié !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	private class ActionValider implements ActionListener {
	private JDialog dialog;
	private Base base;
	private JComboBox<String> pseudo;
	private JComboBox<String> typeCompte;
	
	ActionValider(Base bdd, JComboBox<String> user, JComboBox<String> compte, JDialog dialog){
		this.pseudo = user;
		this.dialog = dialog;
		this.base = bdd;
		this.typeCompte = compte;
	}
		public void actionPerformed(ActionEvent e) {
			String res = base.modifTypeCompte(pseudo.getSelectedItem().toString(), typeCompte.getSelectedItem().toString());
			dialog.dispose();
			if(res.equals("Compte utilisateur modifié !")){
				dialog.dispose();
				JOptionPane.showMessageDialog(null, res);
			}
			else{
				JOptionPane.showMessageDialog(null, "Attention !\nUne erreur s'est produite,\n Veuillez réessayer ultérieurement", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}		
		}
	}
}