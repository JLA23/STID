package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Dimension screenSize;
	
	public Interface(){
		this.setTitle("STID Gestion 2.0");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.width -= 120;
		screenSize.height -= 120;
		this.setIconImage(new ImageIcon("icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    this.setLocation(20, 20);
	    MenuBar menubar = new MenuBar(this);
	    JPanel pane = new JPanel();
	    JLabel image = new JLabel(new ImageIcon("STID.png"));
	    pane.add(image, BorderLayout.CENTER);
	    this.add(pane);
		this.setJMenuBar(menubar);
		this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	
}
