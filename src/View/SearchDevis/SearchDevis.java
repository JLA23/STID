package View.SearchDevis;

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

public class SearchDevis extends JDialog{
	
	private JFormattedTextField numDevis;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String f;
	private JFrame fr;
	private Base bdd;

	public SearchDevis(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.f = fonction;
		this.fr = frame;
		this.bdd = base;
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("STID Gestion 2.0 (Recherche Devis)");
		screenSize.setSize(250, 110);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Numéro de Devis");
	    pane.add(label);
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    num.setGroupingUsed(false);
	    numDevis = new JFormattedTextField(num);
	    numDevis.setPreferredSize(new Dimension(100, 25));
	    numDevis.addKeyListener(new EcouteAction(numDevis, false));
	    numDevis.addKeyListener(new KeyEntrerSearch(this, "Devis"));
	    new ClickDroit(numDevis, true, true);
	    pane.add(numDevis);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    pane3.add(bouton);
	    pane3.add(list);
	    
	    bouton.addActionListener(new ActionValiderVerif(this, "Devis"));
	    list.addActionListener(new ActionList(this, base, frame, fonction, "Devis"));
	    
	    this.add(pane);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	public JFormattedTextField getNumDevis() {
		return numDevis;
	}

	public void setNumDevis(JFormattedTextField numDevis) {
		this.numDevis = numDevis;
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