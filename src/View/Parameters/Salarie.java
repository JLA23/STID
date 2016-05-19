package View.Parameters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import BDD.Base;
import Controller.ActionDroite;
import Controller.ActionFermer;
import Controller.ActionGauche;
import Model.Donnees;
import View.Options.ClickDroit;

public class Salarie extends JFrame {

	private Dimension screenSize = new Dimension();
	private JFrame frame, ici;
	private JFormattedTextField code;
	private JTextField nom, prenom;
	private JButton bouton, bouton2, bouton3, gauche, droite, feuille;
	private Base bdd;
	private Donnees donnees;
	private String numero, fonction;
	private LinkedHashMap<String, String[]> valeur;
	private JComboBox<String> jType;
	
	private static final long serialVersionUID = 1L;

	public Salarie(JFrame frame, Base base) {
		this.bdd = base;
		donnees = new Donnees(base);
		this.frame = frame;
		init();
		int nb = donnees.newNum("personne", "NumPersonnel", null);
		code.setText(nb + "");
		this.setVisible(true);
	}

	public Salarie(JFrame frame, Base base, String num, String fonction) throws ParseException {
		this.bdd = base;
		this.donnees = new Donnees(base);
		this.frame = frame;
		this.fonction = fonction;
		init();
		code.setText(num);
		code.setEditable(false);
		this.numero = num;
		String[] fiche = donnees.fiche("*", "personne",
				"NumPersonnel = " + num);
		nom.setText(fiche[1]);
		prenom.setText(fiche[2]);
		jType.setSelectedIndex(Integer.parseInt(fiche[3]) - 1);
		this.fonction = fonction;
		bouton3.setVisible(false);
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Fleche gauche bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		gauche = new JButton(icon);
		gauche.setBounds(20, 150, 25, 25);
		gauche.addActionListener(new ActionGauche(this, "Salarie", null));
		this.add(gauche);
		ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/Fleche droite bleue.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		droite = new JButton(icon2);
		droite.setBounds(80, 150, 25, 25);
		droite.addActionListener(new ActionDroite(this, "Salarie", null));
		this.add(droite);
		if(fiche[0].equals("1")){
			gauche.setVisible(false);
		}
		if(fiche[0].equals(donnees.max("Numpersonnel", "personne"))){
			droite.setVisible(false);
		}
		ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/feuille.png").getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT));
		feuille = new JButton(icon3);	
		feuille.setBounds(50, 150, 25, 25);
		code.setBackground(new Color(204, 204, 204));
		if(fonction.equals("Suppr")){
			nom.setEditable(false);
			prenom.setEditable(false);
			nom.setBackground(new Color(204, 204, 204));
			prenom.setBackground(new Color(204, 204, 204));
			jType.setEnabled(false);
			
		}
		this.add(feuille);
		this.setVisible(true);

	}

	private void init() {
		this.setLayout(new GridLayout(3, 1));
		frame.setEnabled(false);
		this.ici = this;
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 220);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setPreferredSize(screenSize);
		this.setLayout(null);
		NumberFormat num = NumberFormat.getIntegerInstance();

		JLabel label = new JLabel("Code");
		label.setBounds(100 - label.getPreferredSize().width, 10, label.getPreferredSize().width, label.getPreferredSize().height); 
		this.add(label);
		code = new JFormattedTextField(num);
		code.setPreferredSize(new Dimension(100, 25));
		code.setBounds(110, 8, code.getPreferredSize().width, code.getPreferredSize().height);
		new ClickDroit(code, true, true);
		this.add(code);
		JLabel label2 = new JLabel("Nom");
		label2.setBounds(100 - label2.getPreferredSize().width, 40, label2.getPreferredSize().width, label2.getPreferredSize().height);
		this.add(label2);
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(150, 25));
		nom.setBounds(110, 38, nom.getPreferredSize().width, nom.getPreferredSize().height);
		new ClickDroit(nom, true, true);
		this.add(nom);
		JLabel label3 = new JLabel("Prénom");
		label3.setBounds(100 - label3.getPreferredSize().width, 70, label3.getPreferredSize().width, label3.getPreferredSize().height);
		this.add(label3);
		prenom = new JTextField();
		prenom.setPreferredSize(new Dimension(150, 25));
		prenom.setBounds(110, 68, prenom.getPreferredSize().width, prenom.getPreferredSize().height);
		new ClickDroit(prenom, true, true);
		this.add(prenom);
		JLabel label4 = new JLabel("Catégorie");
		label4.setBounds(100 - label4.getPreferredSize().width, 100, label4.getPreferredSize().width, label4.getPreferredSize().height);
		this.add(label4);
		jType = new JComboBox<String>();
		initType();
		jType.setPreferredSize(new Dimension(125, 25));
		jType.setBounds(110, 98, jType.getPreferredSize().width, jType.getPreferredSize().height);
		this.add(jType);
		bouton = new JButton("Valider");
		bouton.setMnemonic(KeyEvent.VK_ENTER);
		this.getRootPane().setDefaultButton(bouton);
		bouton2 = new JButton("Fermer");
		bouton3 = new JButton("Nouveau");
		bouton3.setBounds(5, 150, bouton3.getPreferredSize().width, bouton3.getPreferredSize().height);
		bouton.setBounds(115, 150, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
		bouton2.setBounds(215, 150, bouton2.getPreferredSize().width, bouton2.getPreferredSize().height);
		this.add(bouton3);
		this.add(bouton);
		this.add(bouton2);

		bouton.addActionListener(new ActionValider());
		bouton2.addActionListener(new ActionFermer(this, frame));
		bouton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter sans enregistrer ?", "Quitter ",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					ici.dispose();
				}
			}

		});
		this.addWindowListener(new ActionFermer(this, frame));
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	

	private void initType(){
		valeur = donnees.type();
		for(Entry<String, String[]> entry : valeur.entrySet()){
			jType.addItem(entry.getKey());
		}
		jType.setSelectedItem("Personnel STID");
	}
	
	private class ActionValider implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Valider")) {
				if (!code.getText().isEmpty() && !nom.getText().isEmpty() && !prenom.getText().isEmpty()) {
					String [] re = valeur.get(jType.getSelectedItem());
					if(fonction != null){
						if (fonction.equals("Modif")) {
							if (donnees.exist("personne", "NumPersonnel", "numpersonnel = " + numero)) {
								bdd.update("personne", "Nom = '" + nom.getText() + "', Prenom = '" + prenom.getText() + "', CodeTypePersonne = " + re[0], "Numpersonnel = " + numero);
								JOptionPane.showMessageDialog(null, "Personnel modifié !");
								ici.dispose();
								frame.setEnabled(true);
								frame.setVisible(true);
							}
						} 	else if (fonction.equals("Suppr")) {
							if (donnees.exist("personne", "NumPersonnel", "numpersonnel = " + numero)){
								if(!donnees.lier("numpersonnel", "pointage", "NumPersonnel = " + numero)) {
									bdd.update("personne", "Nom = '" + nom.getText() + "', Prenom = '" + prenom.getText() + "', CodeTypePersonne = " + re[0], "Numpersonnel = " + numero);
									JOptionPane.showMessageDialog(null, "Personnel modifié !");
									ici.dispose();
									frame.setEnabled(true);
									frame.setVisible(true);
								}
								else{
									JOptionPane.showMessageDialog(null, "Erreur : impossible de supprimer le salarié", "ATTENTION",
										JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					} else {
						if (!donnees.exist("personne", "NumPersonnel", "numpersonnel = " + code.getText())) {
							bdd.insert("personne", code.getText() + ", '" + nom.getText() + "', '" + prenom.getText() + "', " + re[0]);
							JOptionPane.showMessageDialog(null, "Salarié rajouté !");
							ici.dispose();
							frame.setEnabled(true);
							frame.setVisible(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Un champs est vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getIci() {
		return ici;
	}

	public void setIci(JFrame ici) {
		this.ici = ici;
	}

	public JFormattedTextField getCode() {
		return code;
	}

	public void setCode(JFormattedTextField code) {
		this.code = code;
	}

	public JTextField getNom() {
		return nom;
	}

	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	public JTextField getPrenom() {
		return prenom;
	}

	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}

	public JButton getBouton() {
		return bouton;
	}

	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}

	public JButton getBouton2() {
		return bouton2;
	}

	public void setBouton2(JButton bouton2) {
		this.bouton2 = bouton2;
	}

	public JButton getBouton3() {
		return bouton3;
	}

	public void setBouton3(JButton bouton3) {
		this.bouton3 = bouton3;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LinkedHashMap<String, String[]> getValeur() {
		return valeur;
	}

	public void setValeur(LinkedHashMap<String, String[]> valeur) {
		this.valeur = valeur;
	}

	public JComboBox<String> getjType() {
		return jType;
	}

	public void setjType(JComboBox<String> jType) {
		this.jType = jType;
	}

	public Base getBdd() {
		return bdd;
	}

	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}
	
	public void initModif(String [] res) throws ParseException{
		code.setText(res[0]);
		code.setEditable(false);
		code.setBackground(new Color(204, 204, 204));
		nom.setText(res[1]);
		prenom.setText(res[2]);
		jType.setSelectedIndex(Integer.parseInt(res[3]) - 1);
	}
}
