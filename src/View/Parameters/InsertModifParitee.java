package View.Parameters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import BDD.Base;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Controller.LimiteCaratere;
import Model.Donnees;
import View.Options.ClickDroit;

public class InsertModifParitee extends JFrame {

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JFrame frame, ici;
	private JFormattedTextField code, valeur;
	private JTextField libelle, symbole;
	private JButton bouton, bouton2, bouton3;
	private Base bdd;
	private Donnees donnees;
	private JLabel devise, equivaut ;
	boolean modif = false;
	String numero;

	public InsertModifParitee(JFrame frame, Base base) {
		this.bdd = base;
		donnees = new Donnees(base);
		this.frame = frame;
		init();
		int nb = donnees.newNum("devises", "CodeDevise", null);
		code.setText(nb + "");
		this.setVisible(true);
	}

	public InsertModifParitee(JFrame frame, Base base, String num) {
		this.bdd = base;
		donnees = new Donnees(base);
		this.frame = frame;
		init();
		code.setText(num);
		code.setEditable(false);
		code.setBackground(new Color(204, 204, 204));
		this.numero = num;
		String[] fiche = donnees.fiche("CodeDevise, LibDevise, Symbole, Valeur", "devises", "CodeDevise = " + num);
		libelle.setText(fiche[1]);
		devise.setText(fiche[1]);
		devise.setBounds(25 + 150 + equivaut.getPreferredSize().width, 30, devise.getPreferredSize().width, devise.getPreferredSize().height);
		symbole.setText(fiche[2]);
		valeur.setText(fiche[3].replaceAll("\\.", ","));
		if(!valeur.getText().contains(",")){
			valeur.setText(valeur.getText() + ",000000");
		}
		modif = true;
		this.setVisible(true);

	}

	private void init() {
		this.setLayout(null);
		frame.setEnabled(false);
		this.ici = this;
		this.setTitle("STID Gestion 2.0 (Paritée)");
		screenSize.setSize(500, 220);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setPreferredSize(screenSize);
		NumberFormat num = NumberFormat.getIntegerInstance();

		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBounds(0, 0, 500, 220);
		JLabel label = new JLabel("Code devise : ");
		label.setBounds(15, 15, label.getPreferredSize().width, label.getPreferredSize().height);
		pane.add(label);
		code = new JFormattedTextField(num);
		code.setBounds(20 + label.getPreferredSize().width, 15, 50, 20);
		new ClickDroit(code, true, true);
		pane.add(code);

		JLabel label2 = new JLabel("Symbole : ");
		label2.setBounds(35 + 50 + label.getPreferredSize().width, 15, label2.getPreferredSize().width, label2.getPreferredSize().height);
		pane.add(label2);
		symbole = new JTextField();
		symbole.addKeyListener(new LimiteCaratere(symbole, 3));
		symbole.setPreferredSize(new Dimension(100, 25));
		symbole.setBounds(40 + 50 + label.getPreferredSize().width + label2.getPreferredSize().width, 15, 50, 20);
		new ClickDroit(symbole, true, true);
		pane.add(symbole);
		
		JLabel label3 = new JLabel("Libellé Devise : ");
		label3.setBounds(15, 45, label3.getPreferredSize().width, label3.getPreferredSize().height);
		pane.add(label3);
		libelle = new JTextField();
		libelle.setBounds(20 + label3.getPreferredSize().width, 45, 350, 20);
		new ClickDroit(libelle, true, true);
		pane.add(libelle);
		
		JPanel parite = new JPanel();
		parite.setBorder(BorderFactory.createTitledBorder("Paritée"));
		parite.setBounds(15, 70, 450, 60);
		pane.add(parite);
		parite.setLayout(null);
		
		DecimalFormat nf = new DecimalFormat("#0.000000");
		valeur = new JFormattedTextField(nf);
		
		String [] res = donnees.fiche("libdevise", "devises", "pardefaut = 1");
		
		equivaut = new JLabel("1,00 " + res[0] + " équivaut à ");
		devise = new JLabel();
		equivaut.setBounds(15,30, equivaut.getPreferredSize().width, equivaut.getPreferredSize().height);
		valeur.setBounds(20 + equivaut.getPreferredSize().width, 29, 150, 20);
		valeur.setText("1,000000");
		valeur.addKeyListener(new EcouteAction(valeur, true));
		devise.setBounds(25 + 150 + equivaut.getPreferredSize().width, 30, devise.getPreferredSize().width, devise.getPreferredSize().height);
		
		libelle.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				devise.setText(libelle.getText());
				devise.setBounds(25 + 150 + equivaut.getPreferredSize().width, 30, devise.getPreferredSize().width, devise.getPreferredSize().height);
			}
			
		});
		
		parite.add(equivaut);
		parite.add(devise);
		parite.add(valeur);
		
		bouton = new JButton("Valider");
		bouton.setMnemonic(KeyEvent.VK_ENTER);
		this.getRootPane().setDefaultButton(bouton);
		bouton2 = new JButton("Fermer");
		bouton3 = new JButton("Retour");
		
		bouton.setBounds(125, 150, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
		bouton2.setBounds(135 + bouton.getPreferredSize().width , 150, bouton2.getPreferredSize().width, bouton2.getPreferredSize().height);
		bouton3.setBounds(145 + bouton.getPreferredSize().width + bouton2.getPreferredSize().width, 150, bouton3.getPreferredSize().width, bouton3.getPreferredSize().height);
		
		pane.add(bouton3);
		pane.add(bouton);
		pane.add(bouton2);

		bouton.addActionListener(new ActionValider());
		bouton2.addActionListener(new ActionFermer(this, frame));
		bouton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter sans enregistrer ?", "Quitter ",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					ici.dispose();
					frame.setEnabled(true);
					new Paritee(bdd, frame);
				}
			}

		});
		
		
		code.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		code.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				symbole.requestFocus();
			}
		});
		symbole.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		symbole.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				libelle.requestFocus();
			}
		});
		libelle.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		libelle.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				valeur.requestFocus();
			}
		});
		valeur.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		valeur.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				code.requestFocus();
			}
		});
		new ClickDroit(code, true, false);
		new ClickDroit(symbole, true, false);
		new ClickDroit(libelle, true, true);
		new ClickDroit(valeur, true, true);
		
		this.addWindowListener(new ActionFermer(this, frame));
		this.add(pane);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}

	private class ActionValider implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Valider")) {
				if (!code.getText().isEmpty() && !libelle.getText().isEmpty() && !valeur.getText().isEmpty() && !symbole.getText().isEmpty()) {
					if (modif) {
						if (donnees.exist("devises", "CodeDevise", "CodeDevise = " + numero)) {
							bdd.update("devises", "CodeDevise = " + code.getText() +", LibDevise = '" + libelle.getText() +"', Symbole = '" + symbole.getText() +"', Valeur = " + valeur.getText().replaceAll(",", "\\."), "CodeDevise = " + numero);
							JOptionPane.showMessageDialog(null, "Catégorie modifiée !");
							ici.dispose();
							frame.setEnabled(true);
							new Paritee(bdd, frame);
						}
					} else {
						if (!donnees.exist("devises", "CodeDevise", "CodeDevise = " + numero)) {
							bdd.insert("devises", code.getText() + ", '" + libelle.getText() + "','" + symbole.getText() + "'," + valeur.getText().replaceAll(",", "\\.") + ", 0" );
							JOptionPane.showMessageDialog(null, "Catégorie enregistrée !");
							ici.dispose();
							frame.setEnabled(true);
							new Paritee(bdd, frame);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : champs vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		}
	}
}