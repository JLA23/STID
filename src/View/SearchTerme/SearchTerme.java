package View.SearchTerme;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import BDD.Base;
import Controller.ActionList;
import Controller.ActionValiderVerif;
import View.Options.ClickDroit;

public class SearchTerme extends JDialog{
	
	private JFormattedTextField numCom, numIndice;
	private JTextField numComClient;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String f;
	private JFrame fr;
	private Base bdd;
	
	public SearchTerme(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.f = fonction;
		this.fr = frame;
		this.bdd = base;
		this.setTitle("STID Gestion 2.0 (Recherche Terme)");
		screenSize.setSize(260, 130);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(200, 130));
	    JLabel label = new JLabel("Numéro de Commande");
	    pane.add(label);
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    numCom = new JFormattedTextField(num);
	    numCom.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(numCom, true, true);
	    pane.add(numCom);
	    JLabel labelIndice = new JLabel("Numéro Indice");
	    pane.add(labelIndice);
	    numIndice = new JFormattedTextField(num);
	    numIndice.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(numIndice, true, true);
	    pane.add(numIndice);
	   
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton); 
	    pane.add(bouton);
	    pane.add(list);
	    bouton.addActionListener(new ActionValiderVerif(this, "Termes"));
	    list.addActionListener(new ActionList(this, base, frame, fonction, "Termes"));
	    this.add(pane);
	    this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	public JFormattedTextField getNumCom() {
		return numCom;
	}

	public void setNumCom(JFormattedTextField numCom) {
		this.numCom = numCom;
	}

	public JTextField getNumComClient() {
		return numComClient;
	}

	public void setNumComClient(JTextField numComClient) {
		this.numComClient = numComClient;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public JFrame getFr() {
		return fr;
	}

	public void setFr(JFrame fr) {
		this.fr = fr;
	}

	public Base getBdd() {
		return bdd;
	}

	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}

	public JFormattedTextField getNumIndice() {
		return numIndice;
	}

	public void setNumIndice(JFormattedTextField numIndice) {
		this.numIndice = numIndice;
	}
}