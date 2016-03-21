package View.SearchFactures;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BDD.Base;
import Controller.ActionList;
import Controller.ActionValiderVerif;
import Controller.EcouteAction;
import View.Options.ClickDroit;

public class SearchFacture extends JDialog{
	
	private JFormattedTextField numCom, numIndice, numFacture;
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private String f;
	private JFrame fr;
	private JDialog ici;
	private Base bdd;
	private JTabbedPane jtp;
	private JButton bouton, bouton2;
	
	public SearchFacture(Base base, JFrame frame, String fonction){
		super(frame, null, true);
		this.ici = this;
		this.f = fonction;
		this.fr = frame;
		this.bdd = base;
		this.setTitle("STID Gestion 2.0 (Recherche Facture)");
		screenSize.setSize(260, 160);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    jtp = new JTabbedPane();
	    jtp.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(jtp.getSelectedIndex() == 0){
					bouton.setMnemonic(KeyEvent.VK_ENTER);
					ici.getRootPane().setDefaultButton(bouton);
					numCom.requestFocus();
				}
				else if(jtp.getSelectedIndex() == 1){
					bouton2.setMnemonic(KeyEvent.VK_ENTER);
					ici.getRootPane().setDefaultButton(bouton2);
					numFacture.requestFocus();
				}
			}
	    	
	    });
	    JPanel pane = new JPanel();
	    JPanel pane2 = new JPanel();
	    pane2.setLayout(null);
	    pane.setPreferredSize(new Dimension(200, 130));
	    JLabel label = new JLabel("Numéro de Commande");
	    pane.add(label);
	   // NumberFormat num =  NumberFormat.getIntegerInstance();
	    numCom = new JFormattedTextField();
	    numCom.addKeyListener(new EcouteAction(numCom, false));
	    numCom.setPreferredSize(new Dimension(100, 25));
	    numCom.addKeyListener(new KeyList(this));
	    new ClickDroit(numCom, true, true);
	    pane.add(numCom);
	    JLabel labelIndice = new JLabel("Numéro Indice");
	    pane.add(labelIndice);
	    numIndice = new JFormattedTextField();
	    numIndice.addKeyListener(new EcouteAction(numIndice, false));
	    numIndice.setPreferredSize(new Dimension(100, 25));
	    numIndice.addKeyListener(new KeyList(this));
	    new ClickDroit(numIndice, true, true);
	    pane.add(numIndice);
	    JLabel labelFacture = new JLabel("Numéro Facture");
	    pane2.add(labelFacture);
	    numFacture = new JFormattedTextField();
	    numFacture.addKeyListener(new EcouteAction(numFacture, false));
	    numFacture.setPreferredSize(new Dimension(100, 25));
	    numFacture.addKeyListener(new KeyList(this));
	    new ClickDroit(numFacture, true, true);
	    pane2.add(numFacture);
	    labelFacture.setBounds(20, 23, labelFacture.getPreferredSize().width, labelFacture.getPreferredSize().height);
	    numFacture.setBounds(25 + labelFacture.getPreferredSize().width, 20, numFacture.getPreferredSize().width, numFacture.getPreferredSize().height);
	    bouton = new JButton("Valider");
	    JButton list = new JButton("Liste");
	    bouton2 = new JButton("Valider");
	    bouton2.setBounds(127 - bouton2.getPreferredSize().width, 70, bouton2.getPreferredSize().width, bouton2.getPreferredSize().height);
	    JButton list2 = new JButton("Liste");
	    list2.setBounds(133, 70, list.getPreferredSize().width, list.getPreferredSize().height);
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton);
	   	pane.add(bouton);
	    pane.add(list);
	    pane2.add(bouton2);
	    pane2.add(list2);
	    bouton.addActionListener(new ActionValiderVerif(this, "Factures"));
	    list.addActionListener(new ActionList(this, base, frame, fonction, "Factures"));
	    bouton2.addActionListener(new ActionValiderVerif(this, "Factures"));
	    list2.addActionListener(new ActionList(this, base, frame, fonction, "Factures"));
	    jtp.addTab("Terme", pane);
	    jtp.addTab("Facture", pane2);
	    this.add(jtp);
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

	public JFormattedTextField getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(JFormattedTextField numFacture) {
		this.numFacture = numFacture;
	}

	public JTabbedPane getJtp() {
		return jtp;
	}

	public void setJtp(JTabbedPane jtp) {
		this.jtp = jtp;
	}
	
	public class KeyList implements KeyListener{
		
		private SearchFacture search;
		
		public KeyList(SearchFacture search){
			this.search = search;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			if((int)arg0.getKeyChar() == 10){
				new ActionValiderVerif(search, "Factures").verif();
			}
			
		}
		
	}
	
}