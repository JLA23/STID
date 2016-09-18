package View.Pointage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import BDD.Base;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Controller.LimiteCaratere;
import Model.Donnees;

public class HeuresProductives extends JFrame{

	private static final long serialVersionUID = 1L;
	
	protected Base base;
	protected JFrame frame;
	protected Dimension screenSize = new Dimension();
	protected JFormattedTextField semaine, annee, hNorm, hSupp, hTotal;
	protected DecimalFormat nf;
	
	public HeuresProductives(Base bdd, JFrame fenetre){
		this.base = bdd;
		this.frame = fenetre;
		fenetre.setEnabled(false);
		this.addWindowListener(new ActionFermer(this, frame));
		this.setLayout(null);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.setTitle("Heures Productives");
		screenSize.setSize(435, 345);
		this.setPreferredSize(screenSize);
		
		JPanel parametres = new JPanel();
		parametres.setBorder(BorderFactory.createTitledBorder("Paramètres"));
		parametres.setBounds(15, 10, 400, 90);
		parametres.setLayout(null);
		
		JLabel label1 = new JLabel("A la fin de la semaine N° ");
		label1.setBounds(15, 35, label1.getPreferredSize().width, label1.getPreferredSize().height);
		parametres.add(label1);
		
		nf = new DecimalFormat("#0.00");
		nf.setGroupingUsed(false);
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		
		semaine = new JFormattedTextField(num);
		semaine.setBounds(20 + label1.getPreferredSize().width, 35, 50, 20);
		semaine.addKeyListener(new EcouteAction(semaine, false));
		semaine.addKeyListener(new LimiteCaratere(semaine,2));
		semaine.addKeyListener(new verifSemaine());
		parametres.add(semaine);
		int semaineEnCours = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		semaine.setText(semaineEnCours + "");

		
		JLabel label2 = new JLabel("de l'année ");
		label2.setBounds(35 + label1.getPreferredSize().width + 50, 35, label2.getPreferredSize().width, label2.getPreferredSize().height);
		parametres.add(label2);
		
		annee = new JFormattedTextField(num);
		annee.setBounds(40 + label1.getPreferredSize().width +label2.getPreferredSize().width+ 50, 35, 80, 20);
		annee.addKeyListener(new EcouteAction(annee, false));
		annee.addKeyListener(new LimiteCaratere(annee,4));
		parametres.add(annee);
		int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
		annee.setText(anneeEnCours + "");
		this.add(parametres);
		
		JPanel resultats = new JPanel();
		resultats.setBorder(BorderFactory.createTitledBorder("Résultats"));
		resultats.setBounds(15, 110, 400, 180);
		resultats.setLayout(null);
		
		JLabel label3 = new JLabel("Total des heures imputées (Norm)");
		JLabel label4 = new JLabel("Total des heures imputées (Supp)");
		JLabel label5 = new JLabel("Total des heures productives");
		JSeparator jSeparator = new JSeparator();
		
		hNorm = new JFormattedTextField(nf);
		hSupp = new JFormattedTextField(nf);
		hTotal = new JFormattedTextField(nf);
		hNorm.setEditable(false);
		hSupp.setEditable(false);
		hTotal.setEditable(false);
		
		hNorm.setBounds(300, 25, 80, 20);
		label3.setBounds(295 - label3.getPreferredSize().width, 25, label3.getPreferredSize().width, label3.getPreferredSize().height);
		hSupp.setBounds(300, 50, 80, 20);
		label4.setBounds(295 - label3.getPreferredSize().width, 50, label4.getPreferredSize().width, label4.getPreferredSize().height);
		jSeparator.setBounds(280, 77, 110, 2);
		hTotal.setBounds(300, 85, 80, 20);
		label5.setBounds(295 - label5.getPreferredSize().width, 85, label5.getPreferredSize().width, label5.getPreferredSize().height);
		
		hNorm.setHorizontalAlignment(JTextField.RIGHT);
		hSupp.setHorizontalAlignment(JTextField.RIGHT);
		hTotal.setHorizontalAlignment(JTextField.RIGHT);
		
		resultats.add(hNorm);
		resultats.add(label3);
		resultats.add(hSupp);
		resultats.add(label4);
		resultats.add(hTotal);
		resultats.add(label5);
		resultats.add(jSeparator);
		
		JButton executer = new JButton("Executer");
		JButton fermer = new JButton("Fermer");
		executer.setBounds(115, 130, executer.getPreferredSize().width, executer.getPreferredSize().height);
		fermer.setBounds(120 + executer.getPreferredSize().width, 130, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		resultats.add(executer);
		resultats.add(fermer);
		fermer.addActionListener(new ActionFermer(this, frame));
		executer.addActionListener(new ExecuterCalcul());
		
		this.add(resultats);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
	
	public class ExecuterCalcul implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!semaine.getText().isEmpty() && ! annee.getText().isEmpty()){
				Donnees donnees = new Donnees(base);
				String [] res = donnees.fiche("Sum(HrPassNormal), Sum(HrPassSup125 + HrPassSup150 + HrPassSup200), Sum(HrPassNormal + HrPassSup125 + HrPassSup150 + HrPassSup200)", "pointage", "numsem <= "+ semaine.getText() + " and annee = " + annee.getText());
				if(res[0] == null){
					hNorm.setText("0,00");
				}
				else{
					hNorm.setText(nf.format(Double.parseDouble(res[0])));
				}
				if(res[1] == null){
					hSupp.setText("0,00");
				}
				else{
					hSupp.setText(nf.format(Double.parseDouble(res[1])));
				}
				if(res[2] == null){
					hTotal.setText("0,00");
				}
				else{
					hTotal.setText(nf.format(Double.parseDouble(res[2])));
				}
			}
			
		}
		
	}
	
	public class verifSemaine implements KeyListener{

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
			try{
				if(Integer.parseInt(semaine.getText()) > 53){
					JOptionPane.showMessageDialog(null, "La valeur doit être inférieur ou ègal à 53", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
					int semaineEnCours = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
					semaine.setText(semaineEnCours + "");
				}
			}
			catch(Exception e1){
				
			}
			
		}
		
	}
}
