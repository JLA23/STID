package View.Clients;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BDD.Base;
import Controller.ActionFermer;
import Controller.EcouteAction;
import Model.Donnees;
import Model.PieChart;

@SuppressWarnings("serial")
public class CA extends JFrame{
	
	protected Base base;
	protected JFrame frame;
	protected JTextField jannee;
	protected JButton executer, fermer;
	protected PieChart chart;
	
	public CA(Base bdd, JFrame fenetre){
		this.base = bdd;
		this.frame = fenetre;
		frame.setEnabled(false);
		this.setTitle("CA");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
		this.addWindowListener(new ActionFermer(this, frame));
		this.setPreferredSize(new Dimension(200, 115));
		JPanel pane = new JPanel();
		pane.setBounds(0, 0, 200, 115);
		pane.setLayout(null);
		JLabel annee = new JLabel("Année : ");
		annee.setBounds(35, 15, annee.getPreferredSize().width, annee.getPreferredSize().height);
		pane.add(annee);
		
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		jannee = new JTextField();
		jannee.addKeyListener(new EcouteAction(jannee, false));
		jannee.setBounds(40 + annee.getPreferredSize().width, 15, 60, 20);
		pane.add(jannee);
		
		executer = new JButton("Executer");
		executer.setBounds(12, 50, executer.getPreferredSize().width, executer.getPreferredSize().height);
		executer.addActionListener(new validerCA());
		pane.add(executer);
		
		fermer = new JButton("Fermer");
		fermer.setBounds(22 + executer.getPreferredSize().width, 50, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
		fermer.addActionListener(new fermerCA(this));
		pane.add(fermer);
		
		executer.setMnemonic(KeyEvent.VK_ENTER);
		this.getRootPane().setDefaultButton(executer);
		this.add(pane);
		this.setLayout(null);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public class validerCA implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Donnees donnees = new Donnees(base);
			if(!jannee.getText().isEmpty() && !jannee.getText().equals("")){
				if(donnees.exist("factures", "Date_Format(dateemission, '%Y')", "Date_Format(dateemission, '%Y') = " + jannee.getText())){
					if(chart != null){
						chart.dispose();
					}
					chart = new PieChart(jannee.getText(), base);
				}
				else{
					JOptionPane.showMessageDialog(null, "Aucun CA pour cette année", "ATTENTION",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Il faut une année", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
			}
		
		}
	}
	
	public class fermerCA implements ActionListener{
		
		protected JFrame ca;
		
		public fermerCA(JFrame ca){
			this.ca = ca;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(chart != null){
				chart.dispose();
			}
			new ActionFermer(ca, frame).fermer();
		}
	}

}
