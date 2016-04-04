package View.SearchParameters;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
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
import View.Options.ClickDroit;

public class SearchSalarie extends JDialog{
	
	private JFormattedTextField numPersonnel;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JFrame fr;
	private Base bdd;
	private String f;

	public SearchSalarie(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.fr = frame;
		this.bdd = base;
		this.f = fonction;
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("STID Gestion 2.0 (Recherche Personnel)");
		screenSize.setSize(250, 110);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Numéro Personnel");
	    pane.add(label);
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    numPersonnel = new JFormattedTextField(num);
	    numPersonnel.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(numPersonnel, true, true);
	    pane.add(numPersonnel);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton); 
	    pane3.add(bouton);
	    pane3.add(list);
	    
	    bouton.addActionListener(new ActionValiderVerif(this, "Salarie"));
	    list.addActionListener(new ActionList(this, base, frame, f, "Salarie"));
	    
	    this.add(pane);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	public JFormattedTextField getNumPersonnel() {
		return numPersonnel;
	}

	public void setnumPersonnel(JFormattedTextField numPersonnel) {
		this.numPersonnel = numPersonnel;
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

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}
}