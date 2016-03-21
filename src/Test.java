import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Test {

	  public static void main(String[] a) {
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setSize(560-130, 250);
	    f.setLayout(null);
	    ImageIcon icon = new ImageIcon(new ImageIcon("imprimante.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    JLabel text = new JLabel("Choissisez le mode d'impression");
	    Font font = new Font("Calibri", Font.ITALIC, 28);
	    text.setFont(font);
	    text.setBounds((((560-140)- text.getPreferredSize().width)/2), 10, text.getPreferredSize().width, text.getPreferredSize().height);
	    f.add(text);
	    JButton bouton = new JButton(icon);
	    bouton.setPreferredSize(new Dimension(120, 120));
	    bouton.setBounds(20,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    f.add(bouton);
	    ImageIcon icon2 = new ImageIcon(new ImageIcon("loupe.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    JButton bouton2 = new JButton(icon2);
	    bouton2.setPreferredSize(new Dimension(120, 120));
	    bouton2.setBounds(25 + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    f.add(bouton2);
	    ImageIcon icon3 = new ImageIcon(new ImageIcon("pdf.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    JButton bouton3 = new JButton(icon3);
	    bouton3.setPreferredSize(new Dimension(120, 120));
	    bouton3.setBounds(30 + bouton.getPreferredSize().width + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    f.add(bouton3);
	    ImageIcon icon4 = new ImageIcon(new ImageIcon("mail.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    JButton bouton4 = new JButton(icon4);
	    bouton4.setPreferredSize(new Dimension(120, 120));
	    bouton4.setBounds(35 + bouton.getPreferredSize().width + bouton.getPreferredSize().width + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    f.add(bouton4);
	    f.setVisible(true);
	  }
	}
