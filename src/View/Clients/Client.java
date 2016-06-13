package View.Clients;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import BDD.Base;
import Controller.LimiteCaratere;
import Controller.EcouteAction;
import Controller.FocusJText;
import Controller.ItemChange;
import Model.Donnees;
import View.Options.ClickDroit;

public class Client extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JTextField jName, jMail, jnumTVA;
	protected JTextField[] jAdresses;
	protected JLabel numClient, nameClient, moisSuivant, raisonAdresse, TVA, taux, delais, nbex, numTVA, fr, mail, actif;
	protected JFormattedTextField jNumClient;
	protected JFormattedTextField jJourSuivant;
	protected JFormattedTextField jDelais;
	protected JFormattedTextField jNbExemplaire;
	protected JPanel panelClient, panelPaiement;
	protected JRadioButton br1, br2;
	protected JCheckBox check;
	protected JComboBox<String> boxTva;
	protected JButton jmodePaiement, valider, fermer, nouveau, gauche, droite, feuille;
	protected Base base;
	protected Donnees donnees;
	protected NumberFormat num;
	protected LinkedHashMap<String, String[]> valeurTaux;
	protected LinkedHashMap<String, Object[]> vals;
	protected ModePaiement modes;
	protected JFrame frame;

	public Client(Base bdd, JFrame frame) {
		super(frame, null, true);
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouveau Client)");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.frame = frame;
		this.base = bdd;
		this.donnees = new Donnees(base);
		num = NumberFormat.getIntegerInstance();
		jAdresses = new JTextField[7];
		jName = new JTextField();
		numClient = new JLabel("N° Client");
		nameClient = new JLabel("Nom");
		jNumClient = new JFormattedTextField(num);
		jNumClient.setText(donnees.newNum("clients", "NumClient", null) + "");
		panelClient = new JPanel();
		panelClient.setBorder(BorderFactory.createTitledBorder("Client"));
		valider = new JButton("Valider");
		fermer = new JButton("Fermer");
		nouveau = new JButton("Nouveau");
		initClient();
		panelClient.add(panelPaiement);
		panelClient.setPreferredSize(new Dimension(610, 305));
		Dimension size = panelClient.getPreferredSize();
		panelClient.setBounds(10, 10, size.width, size.height);
		this.getContentPane().add(panelClient);
		panelPaiement.setPreferredSize(new Dimension(200, 75));
		size = panelPaiement.getPreferredSize();
		panelPaiement.setBounds(400, 10, size.width, size.height);
		valider.setBounds(290, 325, 80, 25);
		fermer.setBounds(380, 325, 80, 25);
		nouveau.setBounds(175, 325, 105, 25);
		this.getContentPane().add(valider);
		this.getContentPane().add(fermer);
		this.getContentPane().add(nouveau);
		insertTaux();
		Insets insets = this.getInsets();
		this.setSize(640 + insets.left + insets.right, 400 + insets.top + insets.bottom);
	}

	@SuppressWarnings("unchecked")
	private void initClient() {
		panelClient.setLayout(null);
		panelClient.add(numClient);
		panelClient.add(jNumClient);
		panelClient.add(nameClient);
		panelClient.add(jName);
		Insets insets = panelClient.getInsets();
		Dimension size = numClient.getPreferredSize();
		int l = 20 + insets.left;
		numClient.setBounds(l, 20 + insets.top, size.width, size.height);
		l = l + 10 + size.width;
		jNumClient.setPreferredSize(new Dimension(45, 20));
		size = jNumClient.getPreferredSize();
		jNumClient.setBounds(l, 20 + insets.top, size.width, size.height);
		l = l + 20 + size.width;
		size = nameClient.getPreferredSize();
		nameClient.setBounds(l, 20 + insets.top, size.width, size.height);
		l = l + 10 + size.width;
		jName.setPreferredSize(new Dimension(200, 20));
		jName.addKeyListener(new LimiteCaratere(jName, 30));
		size = jName.getPreferredSize();
		jName.setBounds(l, 20 + insets.top, size.width, size.height);
		raisonAdresse = new JLabel("Raison social et Adresse");
		size = raisonAdresse.getPreferredSize();
		raisonAdresse.setBounds(110 + insets.left, 60 + insets.top, size.width, size.height);
		int k = 70 + size.height + insets.top;
		panelClient.add(raisonAdresse);
		for (int j = 0; j < jAdresses.length; j++) {
			JTextField tmp = new JTextField();
			tmp.setPreferredSize(new Dimension(300, 20));
			size = tmp.getPreferredSize();
			tmp.setBounds(30, k, size.width, size.height);
			k += size.height;
			jAdresses[j] = tmp;
			jAdresses[j].addKeyListener(new LimiteCaratere(jAdresses[j], 30));
			panelClient.add(tmp);
		}
		k += 15;
		TVA = new JLabel("Type TVA");
		size = TVA.getPreferredSize();
		TVA.setBounds(40, k, size.width, size.height);
		panelClient.add(TVA);

		boxTva = new JComboBox<String>();
		int o = 50 + size.width;
		boxTva.setPreferredSize(new Dimension(140, 20));
		size = boxTva.getPreferredSize();
		boxTva.setBounds(o, k - 2, size.width, size.height);
		panelClient.add(boxTva);
		boxTva.addItemListener(new ItemChange(this, "Client"));
		
		check = new JCheckBox();
		actif = new JLabel("Actif");
		actif.setBounds(o - 10 - actif.getPreferredSize().width, k + 23, actif.getPreferredSize().width, actif.getPreferredSize().height);
		panelClient.add(actif);
		check.setBounds(o, k + 22, check.getPreferredSize().width, check.getPreferredSize().height);
		panelClient.add(check);
		
		taux = new JLabel("Taux TVA : ");
		o = o + 10 + size.width;
		size = taux.getPreferredSize();
		taux.setBounds(o, k, 100, size.height);
		panelClient.add(taux);

		jmodePaiement = new JButton("Modes Paiements");
		jmodePaiement.setPreferredSize(new Dimension(140, 25));
		size = jmodePaiement.getPreferredSize();
		jmodePaiement.setBounds(400, 100, size.width, size.height);
		modes = new ModePaiement(donnees, null, null);
		jmodePaiement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				modes.afficher();
			}
		});
		panelClient.add(jmodePaiement);
		o = 100 + size.height + 15;

		delais = new JLabel("Dèlais Paiement");
		size = delais.getPreferredSize();
		delais.setBounds(395, o, size.width, size.height);
		panelClient.add(delais);

		jDelais = new JFormattedTextField(num);
		jDelais.addKeyListener(new LimiteCaratere(jDelais, 2));
		jDelais.setPreferredSize(new Dimension(50, 20));
		int p = size.width + 405;
		size = jDelais.getPreferredSize();
		jDelais.setBounds(p, o, size.width, size.height);
		panelClient.add(jDelais);

		o = o + size.height + 15;
		nbex = new JLabel("Nb Exemplaire Facture");
		size = nbex.getPreferredSize();
		nbex.setBounds(360, o, size.width, size.height);
		panelClient.add(nbex);

		jNbExemplaire = new JFormattedTextField(num);
		jNbExemplaire.setPreferredSize(new Dimension(50, 20));
		p = size.width + 370;
		size = jNbExemplaire.getPreferredSize();
		jNbExemplaire.setBounds(p, o, size.width, size.height);
		panelClient.add(jNbExemplaire);
		o = o + size.height + 15;
		mail = new JLabel("E-mail");
		mail.setBounds(p - 50, o, mail.getPreferredSize().width, mail.getPreferredSize().height);
		panelClient.add(mail);
		jMail = new JTextField();
		o = o + mail.getPreferredSize().height + 5;
		jMail.setBounds(p - 150, o, 250, 20);
		panelClient.add(jMail);
		o = o + jMail.getPreferredSize().height + 5;
		numTVA = new JLabel("Numéro TVA");
		fr = new JLabel("FR");
		jnumTVA = new JTextField();
		size = numTVA.getPreferredSize();
		numTVA.setBounds(p - numTVA.getPreferredSize().width, o, size.width, size.height);
		panelClient.add(numTVA);
		o = o + numTVA.getPreferredSize().height + 5;
		jnumTVA.setPreferredSize(new Dimension(120, 20));
		size = jnumTVA.getPreferredSize();
		jnumTVA.setBounds(p - 93, o, size.width, size.height);
		panelClient.add(jnumTVA);

		panelPaiement = new JPanel();
		panelPaiement.setBorder(BorderFactory.createTitledBorder("Paiement"));
		panelPaiement.setLayout(null);
		ButtonGroup bg = new ButtonGroup();
		br1 = new JRadioButton("Le", true);
		br2 = new JRadioButton("Fin de mois", false);
		bg.add(br1);
		bg.add(br2);
		jJourSuivant = new JFormattedTextField(num);
		moisSuivant = new JLabel("du mois suivant");
		panelPaiement.add(br1);
		panelPaiement.add(br2);
		panelPaiement.add(jJourSuivant);
		panelPaiement.add(moisSuivant);

		insets = panelPaiement.getInsets();
		size = br1.getPreferredSize();
		l = 20;
		br1.setBounds(10, l, size.width, size.height);
		jJourSuivant.setPreferredSize(new Dimension(30, 20));
		jJourSuivant.addKeyListener(new LimiteCaratere(jJourSuivant, 2));
		l += 2;
		int i = 10 + insets.left + size.width;
		size = jJourSuivant.getPreferredSize();
		jJourSuivant.setBounds(i, l, size.width, size.height);
		i += size.width + 8;
		l += 2;
		size = moisSuivant.getPreferredSize();
		moisSuivant.setBounds(i, l, size.width, size.height);
		l = l + size.height + 2;
		size = br2.getPreferredSize();
		br2.setBounds(10, l, size.width, size.height);
		
		panelClient.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		panelPaiement.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		
		jNumClient.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNumClient.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jName.requestFocus();
			}
		});
		
		new ClickDroit(jName, true, true);
		jName.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jName.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jJourSuivant.requestFocus();
			}
		});
		
		new ClickDroit(jJourSuivant, true, true);
		jJourSuivant.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jJourSuivant.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[0].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[0], true, true);
		jAdresses[0].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[0].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[1].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[1], true, true);
		jAdresses[1].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[1].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[2].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[2], true, true);
		jAdresses[2].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[2].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[3].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[3], true, true);
		jAdresses[3].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[3].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[4].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[4], true, true);
		jAdresses[4].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[4].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[5].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[5], true, true);
		jAdresses[5].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[5].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jAdresses[6].requestFocus();
			}
		});
		
		new ClickDroit(jAdresses[6], true, true);
		jAdresses[6].getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jAdresses[6].getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jDelais.requestFocus();
			}
		});
		
		new ClickDroit(jDelais, true, true);
		jDelais.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jDelais.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jNbExemplaire.requestFocus();
			}
		});
		
		new ClickDroit(jNbExemplaire, true, true);
		jNbExemplaire.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jNbExemplaire.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jMail.requestFocus();
			}
		});
		
		new ClickDroit(jMail, true, true);
		jMail.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jMail.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jnumTVA.requestFocus();
			}
		});
		
		new ClickDroit(jnumTVA, true, true);
		jnumTVA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		jnumTVA.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				valider.requestFocus();
			}
		});
		
		valider.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		valider.getActionMap().put("tab", new AbstractAction() {
			protected static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				jNumClient.requestFocus();
			}
		});
		jJourSuivant.addKeyListener(new EcouteAction(jJourSuivant));
		jDelais.addKeyListener(new EcouteAction(jDelais));
		jNbExemplaire.addKeyListener(new EcouteAction(jNbExemplaire));
	}
	
	public JRadioButton getBr2() {
		return br2;
	}

	public void setBr2(JRadioButton br2) {
		this.br2 = br2;
	}

	protected void insertTaux() {
		valeurTaux = donnees.taux();
		for (Entry<String, String[]> entry : valeurTaux.entrySet()) {
			boxTva.addItem(entry.getKey());
		}
		boxTva.setSelectedItem("Normal");
	}
	
	public void initModif(String [] res) throws ParseException{
		jNumClient.setText(res[0]);
		jName.setText(res[1]);
		for (int j = 0; j < jAdresses.length; j++) {
			jAdresses[j].setText(res[2 + j]);
		}
		for (Entry<String, String[]> entry : valeurTaux.entrySet()) {
			if(((String)entry.getValue()[0]).equals(res[14])){
				boxTva.setSelectedItem(entry.getKey());
				break;
			}
		}
		jDelais.setText(res[10]);
		jNbExemplaire.setText(res[12]);
		jMail.setText(res[9]);
		jnumTVA.setText(res[15]);
		if(res[11].equals("1")){
			br1.setSelected(true);
		}
		else if(res[11].equals("2")){
			br2.setSelected(true);
		}
		if(res[16].equals("1")){
			check.setSelected(true);
		}
		jJourSuivant.setText(res[13]);
		LinkedHashMap<String, Object[]> val = donnees.modesPaiements();
		val = verifPaiement(val, res[0]);
		modes = new ModePaiement(donnees, null, val);
		vals = donnees.modesPaiements();
		vals = verifPaiement(vals, res[0]);
		new FocusJText(this, "Client").name();
	}
	
	private LinkedHashMap<String, Object[]> verifPaiement(LinkedHashMap<String, Object[]> val, String numero){
		String [] result = donnees.modeClient(numero);
		for (Entry<String, Object[]> entry : val.entrySet()) {
			for(int i = 0; i < result.length; i++){
				if(result[i].equals((String)entry.getValue()[0])){
					val.get(entry.getKey())[1] = true;
				}
			}
		}
		return val;
	}

	public JLabel getTVA() {
		return TVA;
	}

	public void setTVA(JLabel tVA) {
		TVA = tVA;
	}

	public JLabel getTaux() {
		return taux;
	}

	public void setTaux(JLabel taux) {
		this.taux = taux;
	}

	public JComboBox<String> getBoxTva() {
		return boxTva;
	}

	public void setBoxTva(JComboBox<String> boxTva) {
		this.boxTva = boxTva;
	}

	public LinkedHashMap<String, String[]> getValeurTaux() {
		return valeurTaux;
	}

	public void setValeurTaux(LinkedHashMap<String, String[]> valeurTaux) {
		this.valeurTaux = valeurTaux;
	}

	public JTextField getjName() {
		return jName;
	}

	public void setjName(JTextField jName) {
		this.jName = jName;
	}

	public JTextField getjMail() {
		return jMail;
	}

	public void setjMail(JTextField jMail) {
		this.jMail = jMail;
	}

	public JTextField getJnumTVA() {
		return jnumTVA;
	}

	public void setJnumTVA(JTextField jnumTVA) {
		this.jnumTVA = jnumTVA;
	}

	public JTextField[] getjAdresses() {
		return jAdresses;
	}

	public void setjAdresses(JTextField[] jAdresses) {
		this.jAdresses = jAdresses;
	}

	public JFormattedTextField getjNumClient() {
		return jNumClient;
	}

	public void setjNumClient(JFormattedTextField jNumClient) {
		this.jNumClient = jNumClient;
	}

	public JFormattedTextField getjJourSuivant() {
		return jJourSuivant;
	}

	public void setjJourSuivant(JFormattedTextField jJourSuivant) {
		this.jJourSuivant = jJourSuivant;
	}

	public JFormattedTextField getjDelais() {
		return jDelais;
	}

	public void setjDelais(JFormattedTextField jDelais) {
		this.jDelais = jDelais;
	}

	public JFormattedTextField getjNbExemplaire() {
		return jNbExemplaire;
	}

	public void setjNbExemplaire(JFormattedTextField jNbExemplaire) {
		this.jNbExemplaire = jNbExemplaire;
	}

	public JButton getJmodePaiement() {
		return jmodePaiement;
	}

	public void setJmodePaiement(JButton jmodePaiement) {
		this.jmodePaiement = jmodePaiement;
	}
	
	public JRadioButton getBr1() {
		return br1;
	}

	public void setBr1(JRadioButton br1) {
		this.br1 = br1;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}
	

	public JButton getValider() {
		return valider;
	}

	public void setValider(JButton valider) {
		this.valider = valider;
	}

	public JButton getFermer() {
		return fermer;
	}

	public void setFermer(JButton fermer) {
		this.fermer = fermer;
	}

	public JButton getNouveau() {
		return nouveau;
	}

	public void setNouveau(JButton nouveau) {
		this.nouveau = nouveau;
	}

	public ModePaiement getModes() {
		return modes;
	}

	public void setModes(ModePaiement modes) {
		this.modes = modes;
	}
	
	public LinkedHashMap<String, Object[]> getVal() {
		return vals;
	}

	public void setVal(LinkedHashMap<String, Object[]> val) {
		this.vals = val;
	}

	public JCheckBox getCheck() {
		return check;
	}

	public void setCheck(JCheckBox check) {
		this.check = check;
	}

	public JButton getGauche() {
		return gauche;
	}

	public void setGauche(JButton gauche) {
		this.gauche = gauche;
	}

	public JButton getDroite() {
		return droite;
	}

	public void setDroite(JButton droite) {
		this.droite = droite;
	}

	public JButton getFeuille() {
		return feuille;
	}

	public void setFeuille(JButton feuille) {
		this.feuille = feuille;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}