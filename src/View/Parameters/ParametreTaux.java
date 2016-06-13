package View.Parameters;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BDD.Base;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Controller.TestContenu;
import Controller.ParametreTaux.ActionTaux;
import Controller.ParametreTaux.ActionValider;
import Model.Donnees;

public class ParametreTaux extends JDialog{

	private static final long serialVersionUID = 1L;
	private Base base;
	private JFormattedTextField jTaux;
	private JLabel taux, typeTaux, pourcent;
	private JComboBox<String> jTypeTaux;
	private JButton valider, annuler;
	private Donnees donnees;
	private LinkedHashMap<String, String[]> valeur;
	
	public ParametreTaux(Base bdd, JFrame frame){
		super(frame, null, true);
		this.base = bdd;
		donnees = new Donnees(base);
		this.setLayout(null);
		this.setSize(new Dimension(300, 160));
		this.setTitle("STID Gestion 2.0 (Paramètres Taux)");
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
		taux = new JLabel("Taux");
		typeTaux = new JLabel("Type Taux");
		typeTaux.setBounds(100 - typeTaux.getPreferredSize().width, 12, typeTaux.getPreferredSize().width, typeTaux.getPreferredSize().height);
		this.getContentPane().add(typeTaux);
		jTypeTaux = new JComboBox<String>();
		jTypeTaux.setPreferredSize(new Dimension(150, 25));
		jTypeTaux.setBounds(110, 10, jTypeTaux.getPreferredSize().width, jTypeTaux.getPreferredSize().height);
		this.getContentPane().add(jTypeTaux);
		taux.setBounds(100 - taux.getPreferredSize().width, 53, taux.getPreferredSize().width, taux.getPreferredSize().height);
		this.getContentPane().add(taux);
		DecimalFormat nf = new DecimalFormat("#0.00");
		jTaux = new JFormattedTextField(nf);
		jTaux.addKeyListener(new EcouteAction(jTaux));
		jTaux.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				new TestContenu(null, jTaux, 0, null);
			}
		});
		initTaux();
		jTypeTaux.addItemListener(new ActionTaux(jTypeTaux, jTaux, valeur));
		jTaux.setPreferredSize(new Dimension(70, 25));
		jTaux.setBounds(110, 50, jTaux.getPreferredSize().width, jTaux.getPreferredSize().height);
		this.getContentPane().add(jTaux);
		pourcent = new JLabel("<html><body><b /><i />%</body></html>");
		pourcent.setBounds(115 + jTaux.getPreferredSize().width, 53, pourcent.getPreferredSize().width, pourcent.getPreferredSize().height);
		this.getContentPane().add(pourcent);
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		valider.setPreferredSize(new Dimension(80, 25));
		annuler.setPreferredSize(new Dimension(80, 25));
		valider.setBounds(60, 90, valider.getPreferredSize().width, valider.getPreferredSize().height);
		annuler.setBounds(150, 90, annuler.getPreferredSize().width, annuler.getPreferredSize().height);
		valider.addActionListener(new ActionValider(this));
		annuler.addActionListener(new ActionFermer(this));
		this.getContentPane().add(valider);
		this.getContentPane().add(annuler);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public JFormattedTextField getjTaux() {
		return jTaux;
	}

	public void setjTaux(JFormattedTextField jTaux) {
		this.jTaux = jTaux;
	}

	public JComboBox<String> getjTypeTaux() {
		return jTypeTaux;
	}

	public void setjTypeTaux(JComboBox<String> jTypeTaux) {
		this.jTypeTaux = jTypeTaux;
	}

	private void initTaux(){
		valeur = donnees.taux();
		for(Entry<String, String[]> entry : valeur.entrySet()){
			jTypeTaux.addItem(entry.getKey());
		}
		jTypeTaux.setSelectedItem("Normal");
		jTaux.setText(valeur.get(jTypeTaux.getSelectedItem().toString())[1].replaceAll("\\.", ","));
		new TestContenu(null, jTaux, 0, null);
	}
	
	

}
