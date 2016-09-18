package View.Impression;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import BDD.Base;
import Controller.ActionFermer;
import Model.Donnees;

public class InterfaceListing extends JFrame {

	private static final long serialVersionUID = 1L;
	protected Dimension screenSize = new Dimension();
	protected Base bdd;
	protected Donnees donnees;
	protected JTabbedPane jtp;
	protected JFrame frame;
	protected JButton imprimer, exit;

	public InterfaceListing(Base base, JFrame frame) {
		this.frame = frame;
		frame.setEnabled(false);
		this.bdd = base;
		this.setLayout(null);
		donnees = new Donnees(bdd);
		screenSize.setSize(615, 450);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setPreferredSize(screenSize);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 610, 280);
		jtp = new JTabbedPane();
		jtp.setPreferredSize(new Dimension(600, 270));
		panel.add(jtp);
		panel.setBorder( new EtchedBorder(EtchedBorder.RAISED));
		this.add(panel);
		ImageIcon iconimprimer = new ImageIcon(new ImageIcon("lib/images/imprimante.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		imprimer = new JButton("Imprimer", iconimprimer);
		imprimer.setVerticalTextPosition(SwingConstants.BOTTOM); 
		imprimer.setHorizontalTextPosition(SwingConstants.CENTER); 
		imprimer.setBounds(20, 300, 100, 100);
		ImageIcon iconexit = new ImageIcon(new ImageIcon("lib/images/exit.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		exit = new JButton("Sortir", iconexit);
		exit.setVerticalTextPosition(SwingConstants.BOTTOM); 
		exit.setHorizontalTextPosition(SwingConstants.CENTER); 
		exit.addActionListener(new ActionFermer(this, frame));
		exit.setBounds(480, 300, 100, 100);
		this.add(imprimer);
		this.add(exit);
		this.addWindowListener(new ActionFermer(this, frame));
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public JTabbedPane getJtp() {
		return jtp;
	}

	public void setJtp(JTabbedPane jtp) {
		this.jtp = jtp;
	}

	public Base getBdd() {
		return bdd;
	}

	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}
	
	protected void afficher(){
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}
	
	public class ActionImpression implements ActionListener {
		
		protected Object objet;
		protected String typeClasse;
		
		public ActionImpression(Object objet, String typeClasse){
			this.objet = objet;
			this.typeClasse = typeClasse;
		}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Impression(objet, typeClasse);
			}
	}
	
	
}
