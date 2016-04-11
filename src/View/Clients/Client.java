package View.Clients;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import BDD.Base;
import Controller.LimiteCaratere;
import Controller.ItemChange;
import Model.Donnees;

public class Client extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JTextField jName, jMail, jnumTVA;
	protected JTextField[] jAdresses;
	protected JLabel numClient, nameClient, moisSuivant, raisonAdresse, TVA, taux, delais, nbex, numTVA, fr, mail;
	protected JFormattedTextField jNumClient;
	protected JFormattedTextField jJourSuivant;
	protected JFormattedTextField jDelais;
	protected JFormattedTextField jNbExemplaire;
	protected JPanel panelClient, panelPaiement, panelDetails;
	protected JRadioButton br1, br2;
	protected JComboBox<String> boxTva;
	protected JButton jmodePaiement;
	protected JButton valider;
	protected JButton fermer;
	protected JButton nouveau;
	protected Base base;
	protected Donnees donnees;
	protected NumberFormat num;
	protected LinkedHashMap<String, String[]> valeurTaux;
	protected LinkedHashMap<String, Object[]> vals;
	protected ModePaiement modes;

	public Client(Base bdd, JFrame frame) {
		super(frame, null, true);
		this.setLayout(null);
		this.setTitle("Nouveau Client");
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
		initClient();
		panelClient.add(panelPaiement);
		panelClient.setPreferredSize(new Dimension(610, 305));
		Dimension size = panelClient.getPreferredSize();
		panelClient.setBounds(10, 10, size.width, size.height);
		this.getContentPane().add(panelClient);
		panelPaiement.setPreferredSize(new Dimension(200, 75));
		size = panelPaiement.getPreferredSize();
		panelPaiement.setBounds(400, 10, size.width, size.height);
		valider = new JButton("Valider");
		fermer = new JButton("Fermer");
		nouveau = new JButton("Nouveau");
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

}