package View.Devis;

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
import Controller.Devis.ModifSupprDevis.ActionList;
import Controller.Devis.ModifSupprDevis.ActionValiderVerif;
import View.Options.ClickDroit;

public class SearchDevis extends JDialog{
	
	private JFormattedTextField numDevis;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	
	public SearchDevis(Base base, JFrame frame, boolean modale, String fonction){
		super(frame, null, modale);
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
	    numDevis = new JFormattedTextField(num);
	    numDevis.setPreferredSize(new Dimension(100, 25));
	    new ClickDroit(numDevis, true, true);
	    pane.add(numDevis);
	    
	    JPanel pane3 = new JPanel();
	    pane3.setPreferredSize(new Dimension(100, 300));
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton); 
	    pane3.add(bouton);
	    pane3.add(list);
	    
	    bouton.addActionListener(new ActionValiderVerif(this, base, numDevis, frame, fonction));
	    list.addActionListener(new ActionList(this, base, frame, fonction));
	    
	    this.add(pane);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
}