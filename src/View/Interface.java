package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Interface{
	
	private MenuBar menuBar;
	private JFrame f;
	private Dimension screenSize;
	
	public Interface(){
		f = new JFrame("STID");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.width -= 42;
		screenSize.height -= 42;
		f.setIconImage(new ImageIcon("icone.png").getImage());
	    f.setPreferredSize(screenSize);
	    f.setLocation(20, 20);
		menuBar = new MenuBar(f);
		f.setJMenuBar(menuBar);
		f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	}
	
}
