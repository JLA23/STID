package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    this.setLocation(20, 20);
	    MenuBar menubar = new MenuBar(this, bdd, typeCompte);
	    JPanel pane = new JPanel();
	    JLabel image = new JLabel(new ImageIcon("lib/images/STID.png"));
	    pane.add(image, BorderLayout.CENTER);
	    this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				bdd.close();
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    this.add(pane);
	    this.setResizable(false);
		this.setJMenuBar(menubar);
		this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
}
