package View.SearchCommandes;

import java.awt.Dimension;
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
import Controller.EcouteAction;
import Controller.KeyEntrerSearch;
import View.Options.ClickDroit;

public class SearchCommande extends JDialog{
	
	private JFormattedTextField numCom;
	private JTextField numComClient;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String f;
	private JFrame fr;
	private Base bdd;
	
	public SearchCommande(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.f = fonction;
		this.fr = frame;
		this.bdd = base;
		this.setTitle("STID Gestion 2.0 (Recherche Commande)");
		screenSize.setSize(300, 130);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(200, 130));
	    JLabel label = new JLabel("Numéro de Commande");
	    pane.add(label);
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    num.setGroupingUsed(false);
	    numCom = new JFormattedTextField(num);
	    numCom.setPreferredSize(new Dimension(100, 25));
	    numCom.addKeyListener(new EcouteAction(numCom, false));
	    numCom.addKeyListener(new KeyEntrerSearch(this, "Commandes"));
	    new ClickDroit(numCom, true, true);
	    pane.add(numCom);
	    JLabel label2 = new JLabel("Numéro de Commande Client");
	    pane.add(label2);
	    numComClient = new JTextField();
	    numComClient.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(numComClient, true, true);
	    pane.add(numComClient);
	   
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    pane.add(bouton);
	    pane.add(list);
	    bouton.addActionListener(new ActionValiderVerif(this, "Commandes")); 
	    list.addActionListener(new ActionList(this, base, frame, fonction, "Commandes"));
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
}