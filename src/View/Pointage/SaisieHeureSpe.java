package View.Pointage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Controller.ActionCalculatrice;
import Controller.EcouteAction;
import Controller.TestContenu;
import Controller.Pointage.AjoutSaisie;
import Controller.Pointage.FermerSaisie;
import Controller.Pointage.ModifierSaisie;
import Controller.Pointage.NouveauSaisie;
import View.Options.ClickDroit;

public class SaisieHeureSpe extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected HeureSpe spe;
	protected String event;
	protected JLabel anneeLabel, libelle, hRecup, hPerdue, numero;
	protected JFormattedTextField jAnnee, jNumero, jhRecup, jhPerdue;
	protected JTextField jLibelle;
	protected JButton fermer, ajouter, nouveau, calcul1, calcul2;

	public SaisieHeureSpe(HeureSpe hs){
		this.spe = hs;
		init();
		ajouter.addActionListener(new AjoutSaisie(this, spe));
		fermer.addActionListener(new FermerSaisie(this, spe));
		nouveau.addActionListener(new NouveauSaisie(this, spe));
		this.addWindowListener(new FermerSaisie(this, spe));
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setTitle("STID Gestion 2.0 (Saisie Heure Spe)");
		this.setVisible(true);
	}
	
	public SaisieHeureSpe(HeureSpe hs, String numero){
		this.spe = hs;
		init();
		String [] res = spe.getDonnees().fiche("Annee, NumHeureSpe, LblHeureSpeciale, HrPrev, HrPass", "heurespe", "Annee = " + spe.getjAnnee().getText() + " AND NumHeureSpe = " + numero);
		jAnnee.setText(res[0]);
		jNumero.setText(res[1]);
		jLibelle.setText(res[2]);
		jhRecup.setText(res[3].replaceAll("\\.", ","));
		jhPerdue.setText(res[4].replaceAll("\\.", ","));
		new TestContenu(null, jhRecup, -1, null);
		new TestContenu(null, jhPerdue, -1, null);
		jAnnee.setEditable(false);
		jNumero.setEditable(false);
		jAnnee.setBackground(Color.gray);
		jNumero.setBackground(Color.gray);
		ajouter.addActionListener(new ModifierSaisie(this, spe));
		fermer.addActionListener(new FermerSaisie(this, spe));
		nouveau.setVisible(false);
		this.addWindowListener(new FermerSaisie(this, spe));
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setTitle("STID Gestion 2.0 (Modif Heure Spe)");
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void init(){
		String annee = spe.getjAnnee().getText();
		int num = spe.getDonnees().newNum("heurespe", "NumHeureSpe", "Annee = " + annee);
		this.setSize(500, 240);
		this.setLayout(null);
		NumberFormat nume = NumberFormat.getIntegerInstance();
		nume.setGroupingUsed(false);
		DecimalFormat nf = new DecimalFormat("#0.00");
		nf.setGroupingUsed(false);
		spe.dispose();
		
		anneeLabel = new JLabel("Annee");
		jAnnee = new JFormattedTextField(nume);
		jAnnee.setPreferredSize(new Dimension(50,20));
		jAnnee.setBounds(150, 8, jAnnee.getPreferredSize().width, jAnnee.getPreferredSize().height);
		anneeLabel.setBounds(140 - anneeLabel.getPreferredSize().width, 10, anneeLabel.getPreferredSize().width, anneeLabel.getPreferredSize().height);
		jAnnee.setText(annee);
		jAnnee.addKeyListener(new EcouteAnnee());
		this.add(jAnnee);
		this.add(anneeLabel);
		
		numero = new JLabel("Numéro");
		jNumero = new JFormattedTextField(nume);
		jNumero.setPreferredSize(new Dimension(50,20));
		jNumero.setBounds(350, 8, jNumero.getPreferredSize().width, jNumero.getPreferredSize().height);
		numero.setBounds(340 - numero.getPreferredSize().width, 10, numero.getPreferredSize().width, numero.getPreferredSize().height);
		jNumero.setText(num + "");
		this.add(jNumero);
		this.add(numero);
		
		libelle = new JLabel("Libellé");
		jLibelle = new JTextField();
		jLibelle.setPreferredSize(new Dimension(300, 20));
		jLibelle.setBounds(150, 48, jLibelle.getPreferredSize().width, jLibelle.getPreferredSize().height);
		libelle.setBounds(140 - libelle.getPreferredSize().width, 50, libelle.getPreferredSize().width, libelle.getPreferredSize().height);
		this.add(jLibelle);
		this.add(libelle);
		
		ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
		
		hRecup = new JLabel("Heures Récupérées");
		jhRecup = new JFormattedTextField(nf);
		jhRecup.setPreferredSize(new Dimension(80, 20));
		jhRecup.setBounds(150, 88, jhRecup.getPreferredSize().width, jhRecup.getPreferredSize().height);
		hRecup.setBounds(140 - hRecup.getPreferredSize().width, 90, hRecup.getPreferredSize().width, hRecup.getPreferredSize().height);
		this.add(jhRecup);
		this.add(hRecup);
		jhRecup.setText("0,00");
		
		calcul1 = new JButton(icon);
		calcul1.setPreferredSize(new Dimension(19, 20));
		calcul1.setBounds(160 + jhRecup.getPreferredSize().width, 88, calcul1.getPreferredSize().width, calcul1.getPreferredSize().height);
		this.add(calcul1);
		calcul1.addActionListener(new ActionCalculatrice(this, jhRecup, -1, null));
		
		hPerdue = new JLabel("Heures Perdue");
		jhPerdue = new JFormattedTextField(nf);
		jhPerdue.setPreferredSize(new Dimension(80, 20));
		jhPerdue.setBounds(150, 128, jhPerdue.getPreferredSize().width, jhPerdue.getPreferredSize().height);
		hPerdue.setBounds(140 - hPerdue.getPreferredSize().width, 130, hPerdue.getPreferredSize().width, hPerdue.getPreferredSize().height);
		this.add(jhPerdue);
		this.add(hPerdue);
		jhPerdue.setText("0,00");
		
		calcul2 = new JButton(icon);
		calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(160 + jhPerdue.getPreferredSize().width, 128, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
		this.add(calcul2);
		calcul2.addActionListener(new ActionCalculatrice(this, jhPerdue, -1, null));
		
		nouveau = new JButton("Nouveau");
		nouveau.setBounds(20, 170, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
		this.add(nouveau);
		
		ajouter = new JButton("Ajouter");
		ajouter.setBounds(300, 170, ajouter.getPreferredSize().width, ajouter.getPreferredSize().height);
		this.add(ajouter);

		
		fermer = new JButton("Fermer");
		fermer.setBounds(390, 170, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		this.add(fermer);
		
		new ClickDroit(jAnnee, true, true);
		new ClickDroit(jNumero, true, true);
		new ClickDroit(jLibelle, true, true);
		new ClickDroit(jhRecup, true, true);
		new ClickDroit(jhPerdue, true, true);
		
		jAnnee.addKeyListener(new EcouteAction(jAnnee));
		jNumero.addKeyListener(new EcouteAction(jNumero));
		jhRecup.addKeyListener(new EcouteAction(jhRecup));
		jhPerdue.addKeyListener(new EcouteAction(jhPerdue));
		

	    this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		jAnnee.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAnnee.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jNumero.requestFocus();
			}
		});
		jNumero.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNumero.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jLibelle.requestFocus();
			}
		});
		
		jLibelle.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jLibelle.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jhRecup.requestFocus();
			}
		});
		
		jhRecup.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jhRecup.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jhPerdue.requestFocus();
			}
		});
		
		jhPerdue.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jhPerdue.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAnnee.requestFocus();
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
	}

	public JFormattedTextField getjAnnee() {
		return jAnnee;
	}

	public void setjAnnee(JFormattedTextField jAnnee) {
		this.jAnnee = jAnnee;
	}

	public JFormattedTextField getjNumero() {
		return jNumero;
	}

	public void setjNumero(JFormattedTextField jNumero) {
		this.jNumero = jNumero;
	}

	public JTextField getjLibelle() {
		return jLibelle;
	}

	public void setjLibelle(JTextField jLibelle) {
		this.jLibelle = jLibelle;
	}

	public JFormattedTextField getJhRecup() {
		return jhRecup;
	}

	public void setJhRecup(JFormattedTextField jhRecup) {
		this.jhRecup = jhRecup;
	}

	public JFormattedTextField getJhPerdue() {
		return jhPerdue;
	}

	public void setJhPerdue(JFormattedTextField jhPerdue) {
		this.jhPerdue = jhPerdue;
	}
	
	public class EcouteAnnee implements KeyListener{
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			//System.out.println("ecoute 1");
			//action();
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			action();
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("ecoute 3");
			//action();
			
		}
		
		public void action(){
			int newNum = spe.getDonnees().newNum("heurespe", "NumHeureSpe", "Annee = " + jAnnee.getText());
			jNumero.setText(newNum + "");
		}			
	
	}
}
