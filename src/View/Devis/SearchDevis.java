package View.Devis;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import BDD.Base;
import Controller.Donnees;
import View.Options.ClickDroit;

public class SearchDevis extends JDialog{
	
	private Base bdd;
	private JFormattedTextField numDevis;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JFrame fenetre;
	
	public SearchDevis(Base base, JFrame frame, boolean modale){
		super(frame, null, modale);
		this.bdd = base;
		this.fenetre = frame;
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("STID Gestion 2.0 (Recherche Devis)");
		screenSize.setSize(250, 110);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JPanel pane = new JPanel();
	    pane.setPreferredSize(new Dimension(100, 300));
	    JLabel label = new JLabel("Numèro de Devis");
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
	    
	    bouton.addActionListener(new ActionValider(this));
	    list.addActionListener(new ActionList(this));
	    
	    this.add(pane);
	    this.add(pane3);
	    this.pack();
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	private class ActionValider implements ActionListener {
		private JDialog frame;
		
		ActionValider(JDialog frame){
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e) {
			Donnees donnees = new Donnees(bdd);
			if(donnees.existNumDevis(numDevis.getText())){
				frame.dispose();
				try {
					new ModifDevis(bdd, numDevis.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Aucun Devis avec ce numèro !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private class ActionList implements ActionListener {
		private JDialog frame;
		
		ActionList(JDialog dialog){
			this.frame = dialog;
		}
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new SearchDevisList(bdd, fenetre, true).searchDevisNum();	
		}
	}
	

}