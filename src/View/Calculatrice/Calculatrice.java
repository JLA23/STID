package View.Calculatrice;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JDialog {
	private static final long serialVersionUID = 1L;
	private String valeur;
	private JPanel container = new JPanel();
	// Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/" };
	// Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JLabel ecran;
	private Dimension dim = new Dimension(50, 40);
	private Dimension dim2 = new Dimension(50, 31);
	private double chiffre1;
	private boolean clicOperateur = false, update = false;
	private String operateur = "";

	public Calculatrice(JFrame frame, String titre, boolean modale, Double val) {
		super(frame, titre, modale);
		this.setSize(240, 260);
		this.setTitle("Calculette");
		if(val != 0){
			this.ecran = new JLabel(val+"");
			update = true;
			clicOperateur = false;
		}
		else{
			this.ecran = new JLabel("0");
		}
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				valeur = ecran.getText();
				valeur = ((double) Math.round((Double.parseDouble(valeur)) * 100) / 100) + "";
				valeur = valeur.replaceAll("\\.", ",");
			}
		});
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// On initialise le conteneur avec tous les composants
		initComposant();
		// On ajoute le conteneur
		this.setContentPane(container);
		// this.addKeyListener(new TouchesListener());
		Toolkit.getDefaultToolkit().addAWTEventListener(new TouchesListener(), AWTEvent.KEY_EVENT_MASK);
		this.setVisible(true);
	}

	private void initComposant() {
		// On d�finit la police d'�criture � utiliser
		Font police = new Font("Arial", Font.BOLD, 20);
		//ecran = new JLabel("0");
		ecran.setFont(police);
		// On aligne les informations � droite dans le JLabel
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(220, 20));
		JPanel operateur = new JPanel();
		operateur.setPreferredSize(new Dimension(55, 225));
		JPanel chiffre = new JPanel();
		chiffre.setPreferredSize(new Dimension(165, 225));
		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(220, 30));

		// On parcourt le tableau initialis�
		// afin de cr�er nos boutons
		for (int i = 0; i < tab_string.length; i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			switch (i) {
			// Pour chaque �l�ment situ� � la fin du tableau
			// et qui n'est pas un chiffre
			// on d�finit le comportement � avoir gr�ce � un listener
			case 11:
				tab_button[i].addActionListener(new EgalListener());
				chiffre.add(tab_button[i]);
				break;
			case 12:
				tab_button[i].setForeground(Color.red);
				tab_button[i].addActionListener(new ResetListener());
				operateur.add(tab_button[i]);
				break;
			case 13:
				tab_button[i].addActionListener(new PlusListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 14:
				tab_button[i].addActionListener(new MoinsListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 15:
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 16:
				tab_button[i].addActionListener(new DivListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			default:
				// Par d�faut, ce sont les premiers �l�ments du tableau
				// donc des chiffres, on affecte alors le bon listener
				chiffre.add(tab_button[i]);
				tab_button[i].addActionListener(new ChiffreListener());
				break;
			}
		}
		panEcran.add(ecran);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(panEcran, BorderLayout.NORTH);
		container.add(chiffre, BorderLayout.CENTER);
		container.add(operateur, BorderLayout.EAST);
	}

	// M�thode permettant d'effectuer un calcul selon l'op�rateur s�lectionn�
	private void calcul() {
		if (operateur.equals("+")) {
			chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if (operateur.equals("-")) {
			chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if (operateur.equals("*")) {
			chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if (operateur.equals("/")) {
			try {
				chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre1));
			} catch (ArithmeticException e) {
				ecran.setText("0");
			}
		}
	}

	// Listener utilis� pour les chiffres
	// Permet de stocker les chiffres et de les afficher
	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// On affiche le chiffre additionnel dans le label
			String str = ((JButton) e.getSource()).getText();
			if (update) {
				update = false;
			} else {
				if (!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}

	// Listener affect� au bouton =
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	// Listener affect� au bouton +
	class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			} else {
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}

	// Listener affect� au bouton -
	class MoinsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			} else {
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}

	// Listener affect� au bouton *
	class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			} else {
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}

	// Listener affect� au bouton /
	class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			} else {
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	// Listener affect� au bouton de remise � z�ro
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("0");
		}
	}

	public String getValeur() {
		return valeur;
	}

	class TouchesListener implements AWTEventListener {
		public void eventDispatched(AWTEvent event) {
			KeyEvent arg0 = (KeyEvent) event;
			String res = "1234567890.";
			if (arg0.getID() == KeyEvent.KEY_PRESSED) {
				if ((arg0.getKeyChar() + "").equals("+")) {
					if (clicOperateur) {
						calcul();
						ecran.setText(String.valueOf(chiffre1));
					} else {
						chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
						clicOperateur = true;
					}
					operateur = "+";
					update = true;
				} else if ((arg0.getKeyChar() + "").equals("-")) {
					if (clicOperateur) {
						calcul();
						ecran.setText(String.valueOf(chiffre1));
					} else {
						chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
						clicOperateur = true;
					}
					operateur = "-";
					update = true;
				} else if ((arg0.getKeyChar() + "").equals("*")) {
					if (clicOperateur) {
						calcul();
						ecran.setText(String.valueOf(chiffre1));
					} else {
						chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
						clicOperateur = true;
					}
					operateur = "*";
					update = true;
				} else if ((arg0.getKeyChar() + "").equals("/")) {
					if (clicOperateur) {
						calcul();
						ecran.setText(String.valueOf(chiffre1));
					} else {
						chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
						clicOperateur = true;
					}
					operateur = "/";
					update = true;
				} else if (arg0.getKeyCode() == 10) {
					calcul();
					update = true;
					clicOperateur = false;
				} else if (arg0.getKeyCode() == 8 || arg0.getKeyCode() == 127){
					String str = ecran.getText();
					if (!ecran.getText().equals("0"))
						if(ecran.getText().length() > 1){
							str = str.substring(0, ecran.getText().length()-1);
						}
						else if (ecran.getText().length() == 1){
						str = "0";
					}
					ecran.setText(str);
				} else if (res.contains(arg0.getKeyChar() + "")) {
					String str = arg0.getKeyChar() + "";
					if (update) {
						update = false;
					} else {
						if (!ecran.getText().equals("0"))
							str = ecran.getText() + str;
					}
					ecran.setText(str);
				}				
			}
		}
	}
}
