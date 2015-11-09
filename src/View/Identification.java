package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Identification extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public Identification(){
		this.setLayout(new GridLayout(3, 1));
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    this.setLocation(20, 20);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JLabel label = new JLabel("Pseudo");
	    pane.add(label);
	    JTextField jtf = new JTextField();
	    jtf.setPreferredSize(new Dimension(100, 25));
	    pane.add(jtf);
	    
	    JPanel pane2 = new JPanel();
	    pane2.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JLabel label2 = new JLabel("Mot de Passe");
	    pane2.add(label2);
	    JPasswordField jtf2 = new JPasswordField();
	    jtf2.setPreferredSize(new Dimension(100, 25));
	    pane2.add(jtf2);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    pane3.add(bouton);
	    
	    
	    this.add(pane);
	    this.add(pane2);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}

}
