package View.SearchClients;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import BDD.Base;
import Controller.ActionList;
import Controller.ActionValiderVerif;
import Controller.EcouteAction;
import Controller.KeyEntrerSearch;
import View.Options.ClickDroit;

public class SearchClient extends JDialog {
	
	private JFormattedTextField numClient;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String f;
	private JFrame fr;
	private Base bdd;
	
	public SearchClient(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.f = fonction;
		this.fr = frame;
		this.bdd = base;
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("STID Gestion 2.0 (Recherche Client)");
		screenSize.setSize(250, 110);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Numéro du Client");
	    pane.add(label);
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    numClient = new JFormattedTextField(num);
	    numClient.setPreferredSize(new Dimension(100, 25));
	    numClient.addKeyListener(new EcouteAction(numClient, false));
	    numClient.addKeyListener(new KeyEntrerSearch(this, "Client"));
	    new ClickDroit(numClient, true, true);
	    pane.add(numClient);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    pane3.add(bouton);
	    pane3.add(list);
	    
	    bouton.addActionListener(new ActionValiderVerif(this, "Client"));
	    list.addActionListener(new ActionList(this, base, null, fonction,"Client"));
	    
	    this.add(pane);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	public JFormattedTextField getNumClient() {
		return numClient;
	}

	public void setNumClient(JFormattedTextField numClient) {
		this.numClient = numClient;
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
}
