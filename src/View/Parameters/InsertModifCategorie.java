package View.Parameters;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BDD.Base;
import Controller.ActionFermer;
import Model.Donnees;
import View.Options.ClickDroit;

public class InsertModifCategorie extends JFrame {

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JFrame frame, ici;
	private JFormattedTextField code;
	private JTextField libelle;
	private JButton bouton, bouton2, bouton3;
	private Base bdd;
	private Donnees donnees;
	boolean modif = false;
	String numero;

	public InsertModifCategorie(JFrame frame, Base base) {
		this.bdd = base;
		donnees = new Donnees(base);
		this.frame = frame;
		init();
		int nb = donnees.newNum("typepers", "CodeTypePersonne", null);
		code.setText(nb + "");
		this.setVisible(true);
	}

	public InsertModifCategorie(JFrame frame, Base base, String num) {
		this.bdd = base;
		donnees = new Donnees(base);
		this.frame = frame;
		init();
		code.setText(num);
		this.numero = num;
		String[] fiche = donnees.fiche("CodeTypePersonne, LibelleTypePersonne", "typepers", "CodeTypePersonne = " + num);
		libelle.setText(fiche[1]);
		modif = true;
		this.setVisible(true);

	}

	private void init() {
		this.setLayout(new GridLayout(3, 1));
		frame.setEnabled(false);
		this.ici = this;
		this.setTitle("STID Gestion 2.0");
		screenSize.setSize(300, 150);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setPreferredSize(screenSize);
		NumberFormat num = NumberFormat.getIntegerInstance();

		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(100, 300));
		JLabel label = new JLabel("Code");
		pane.add(label);
		code = new JFormattedTextField(num);
		code.setPreferredSize(new Dimension(100, 25));
		new ClickDroit(code, true, true);
		pane.add(code);

		JPanel pane2 = new JPanel();
		pane2.setPreferredSize(new Dimension(100, 300));
		;
		JLabel label2 = new JLabel("Libellé");
		pane2.add(label2);
		libelle = new JTextField();
		libelle.setPreferredSize(new Dimension(100, 25));
		new ClickDroit(libelle, true, true);
		pane2.add(libelle);

		JPanel pane3 = new JPanel();
		pane3.setPreferredSize(new Dimension(100, 300));
		this.setLocationRelativeTo(null);
		bouton = new JButton("Valider");
		bouton.setMnemonic(KeyEvent.VK_ENTER);
		this.getRootPane().setDefaultButton(bouton);
		bouton2 = new JButton("Fermer");
		bouton3 = new JButton("Retour");
		pane3.add(bouton3);
		pane3.add(bouton);
		pane3.add(bouton2);

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
					new Categorie(bdd, frame);
				}
			}

		});
		this.addWindowListener(new ActionFermer(this, frame));
		this.add(pane);
		this.add(pane2);
		this.add(pane3);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}

	private class ActionValider implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Valider")) {
				if (!code.getText().isEmpty() && !libelle.getText().isEmpty()) {
					if (modif) {
						if (donnees.exist("typepers", "CodeTypePersonne", "CodeTypePersonne = " + numero)) {
							bdd.update("typepers", "CodeTypePersonne = " + code.getText() +", LibelleTypePersonne = '" + libelle.getText() +"'", "CodeTypePersonne = " + numero);
							JOptionPane.showMessageDialog(null, "Catégorie modifiée !");
							ici.dispose();
							frame.setEnabled(true);
							new Categorie(bdd, frame);
						}
					} else {
						if (!donnees.exist("typepers", "CodeTypePersonne", "CodeTypePersonne = " + code.getText())) {
							bdd.insert("typepers", code.getText() + ", '" + libelle.getText() + "'");
							JOptionPane.showMessageDialog(null, "Catégorie enregistrée !");
							ici.dispose();
							frame.setEnabled(true);
							new Categorie(bdd, frame);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur : Code ou Libellé vide", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		}
	}
}