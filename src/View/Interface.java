package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BDD.Base;

public class Interface extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String typeCompte;
	
	public Interface(Base bdd, String typeCompte){
		
		this.typeCompte = typeCompte;
		
		this.setTitle("STID Gestion 2.0 ("+ this.typeCompte +")");
		screenSize.width = 750;
		screenSize.height = 400;
		this.setIconImage(new ImageIcon("icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    this.setLocation(20, 20);
	    MenuBar menubar = new MenuBar(this, bdd, typeCompte);
	    JPanel pane = new JPanel();
	    JLabel image = new JLabel(new ImageIcon("STID.png"));
	    pane.add(image, BorderLayout.CENTER);
	    this.add(pane);
	    this.setResizable(false);
		this.setJMenuBar(menubar);
		this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
}
