package View.Parameters;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import BDD.Base;

public class ParametreApplication extends JDialog{

	private static final long serialVersionUID = 1L;
	private Base base;
	private JFormattedTextField jTaux;
	private JLabel taux, typeTaux;
	private JComboBox<String> jTypeTaux;
	private JButton valider, annuler;
	
	public ParametreApplication(Base bdd){
		this.base = bdd;
		this.setLayout(null);
		this.setSize(new Dimension(300, 160));
		this.setTitle("STID Gestion 2.0 (Paramètres Application)");
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
		taux = new JLabel("Taux");
		typeTaux = new JLabel("Type Taux");
		typeTaux.setBounds(100 - typeTaux.getPreferredSize().width, 12, typeTaux.getPreferredSize().width, typeTaux.getPreferredSize().height);
		this.getContentPane().add(typeTaux);
		jTypeTaux = new JComboBox<String>();
		jTypeTaux.setPreferredSize(new Dimension(150, 25));
		jTypeTaux.setBounds(110, 10, jTypeTaux.getPreferredSize().width, jTypeTaux.getPreferredSize().height);
		this.getContentPane().add(jTypeTaux);
		taux.setBounds(100 - taux.getPreferredSize().width, 52, taux.getPreferredSize().width, taux.getPreferredSize().height);
		this.getContentPane().add(taux);
		jTaux = new JFormattedTextField();
		jTaux.setPreferredSize(new Dimension(70, 25));
		jTaux.setBounds(110, 50, jTaux.getPreferredSize().width, jTaux.getPreferredSize().height);
		this.getContentPane().add(jTaux);
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		valider.setPreferredSize(new Dimension(80, 25));
		annuler.setPreferredSize(new Dimension(80, 25));
		valider.setBounds(60, 90, valider.getPreferredSize().width, valider.getPreferredSize().height);
		annuler.setBounds(150, 90, annuler.getPreferredSize().width, annuler.getPreferredSize().height);
		this.getContentPane().add(valider);
		this.getContentPane().add(annuler);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	

}
