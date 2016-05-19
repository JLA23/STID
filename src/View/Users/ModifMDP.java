package View.Users;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import BDD.Base;
import View.Identification;

public class ModifMDP extends JDialog{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public ModifMDP(Base base, JFrame frame){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 160);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Ancien Mot de passe : ");
	    JPasswordField oldPassword = new JPasswordField();
	    oldPassword.setPreferredSize(new Dimension(100, 25));
	    pane.add(label);
	    pane.add(oldPassword);
	    
	    JPanel pane2 = new JPanel();
	    pane2.setPreferredSize(new Dimension(100, 300));;
	    JLabel label2 = new JLabel("Nouveau Mot de Passe :");
	    pane2.add(label2);
	    JPasswordField newPassword = new JPasswordField();
	    newPassword.setPreferredSize(new Dimension(100, 25));
	    pane2.add(newPassword);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    JButton bouton = new JButton("Valider");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    pane3.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(base, oldPassword, newPassword, frame, this));
	    
	    this.add(pane);
	    this.add(pane2);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	private class ActionValider implements ActionListener {
	private JPasswordField oldpw, newpw;
	private JFrame frame;
	private JDialog dialog;
	private Base base;
	
	ActionValider(Base bdd, JPasswordField old, JPasswordField newp, JFrame frame, JDialog dialog){
		this.oldpw = old;
		this.newpw = newp;
		this.dialog = dialog;
		this.frame = frame;
		this.base = bdd;
	}
		public void actionPerformed(ActionEvent e) {
			String oldmdp = new String(oldpw.getPassword());
			String newmdp = new String(newpw.getPassword());
			if(oldmdp.equals(base.getMdp())){
				String res = base.modifierMDP(newmdp);
				dialog.dispose();
				if(res.equals("Mot de passe modifié avec succés !")){
					dialog.dispose();
					JOptionPane.showMessageDialog(null, res + "\nVeuillez vous reconnecter !");
					frame.dispose();
					String adresse = base.getAdresse();
					String based = base.getBase();
					base.close();
					new Identification(adresse, based);
				}
				else{
					JOptionPane.showMessageDialog(null, "Attention !\nUne erreur s'est produite,\n Veuillez réessayer ultérieurement", "ATTENTION", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Attention !\nAncien mot de passe incorrect", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}