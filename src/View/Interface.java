package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Interface{
	
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
	    MenuBar menubar = new MenuBar(f);

		f.setJMenuBar(menubar);
		f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	}
	
}
