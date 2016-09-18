package View.Impression.Pointage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import BDD.Base;
import Controller.ActionSearch;
import Controller.EcouteAction;
import Controller.FocusJText;
import View.Impression.InterfaceListing;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;


public class EtatsHeureSpePointage extends InterfaceListing {

	private static final long serialVersionUID = 1L;
	
	protected Object[][] listDevis, listClients, listPersonnel, listCategorie;
	protected JFormattedTextField jAnnee, jAnnee2, intervalle1, intervalle2;
	protected JLabel labelClient, labelClient2, de, a, annee;
	protected JPanel heureSpe, pointage;
	protected JDateChooser jDate;
	protected JRadioButton bp1, bp2, bp3, bs1, bs2, br1, br2, br3;
	protected AutoComplete jNumPersonnel, jNumCategorie, jNumDevis, jNumCommande;
	protected JCheckBox cb1, cb2;
	protected JButton list1, list2, list3, list4;

	public EtatsHeureSpePointage(Base base, JFrame frame, int visible) {
		super(base, frame);
		this.setTitle("Etats Heures Spéciales & Pointages");
		heureSpe = new JPanel();
		heureSpe.setLayout(null);
		heureSpe.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 40, jtp.getPreferredSize().height - 40));
		pointage = new JPanel();
		pointage.setLayout(null);
		pointage.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		jtp.add("Heures Spéciales", heureSpe);
		jtp.add("Pointages", pointage);
		initHeureSpe();
		initPointage();
		jtp.setSelectedIndex(visible);
		imprimer.addActionListener(new ActionImpression(this, "EHSP") );
		afficher();
	}
	
	private void initHeureSpe(){
		JLabel jHeureSpe = new JLabel("Heures Spéciales :");
		jHeureSpe.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		jHeureSpe.setFont(font);
		jHeureSpe.setBounds(30, 30, jHeureSpe.getPreferredSize().width, jHeureSpe.getPreferredSize().height);
		heureSpe.add(jHeureSpe);
		JLabel informations = new JLabel(
				"Edition de la liste des heures spéciales de l'année N.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		heureSpe.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		heureSpe.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Option"));
		restriction.setBounds(30, 120, 250, 110);
		restriction.setLayout(null);
		
		JLabel Annee = new JLabel("Annee");
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		jAnnee = new JFormattedTextField(num);
		jAnnee.addKeyListener(new EcouteAction(jAnnee, false));
		Annee.setBounds(40, 50, Annee.getPreferredSize().width, Annee.getPreferredSize().height);
		jAnnee.setBounds(50 + Annee.getPreferredSize().width, 49, 80, 20);
		int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
		jAnnee.setText(anneeEnCours +"");
		restriction.add(Annee);
		restriction.add(jAnnee);
		heureSpe.add(restriction);
	}
	
	private void initPointage(){
		JLabel labelListeDevis = new JLabel("Liste des pointages");
		labelListeDevis.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		labelListeDevis.setFont(font);
		labelListeDevis.setBounds(30, 15, labelListeDevis.getPreferredSize().width, labelListeDevis.getPreferredSize().height);
		pointage.add(labelListeDevis);
		JLabel informations = new JLabel(
				"Listing des pointages pour l'année N sur une période donnée");
		informations.setBounds(40, 45, informations.getPreferredSize().width, informations.getPreferredSize().height);
		pointage.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 75, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		pointage.add(parametrage);
		
		JPanel personnel = new JPanel();
		personnel.setBorder(BorderFactory.createTitledBorder("Personnel"));
		personnel.setBounds(10, 95, 135, 142);
		personnel.setLayout(null);
		
		ButtonGroup bg = new ButtonGroup();
		bp1 = new JRadioButton("Tous", true);
		bp2 = new JRadioButton("Un seul", false);
		bp3 = new JRadioButton("Une catégorie", false);
		bp1.setBounds(10, 15, bp1.getPreferredSize().width, bp1.getPreferredSize().height);
		bp2.setBounds(10, 37, bp2.getPreferredSize().width, bp2.getPreferredSize().height);
		AutoCompleteModel modelPersonnel = new AutoCompleteModel();
		modelPersonnel.addAll(listPersonnel());
		jNumPersonnel = new AutoComplete(modelPersonnel);
		jNumPersonnel.getZoneTexte().addKeyListener(new EcouteAction(jNumPersonnel.getZoneTexte(), false));
		jNumPersonnel.setBounds(30, 62, 60, 20);
		jNumPersonnel.getZoneTexte().addFocusListener(new FocusJText(this, "EHSP"));
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		list1 = new JButton(icon2);
		list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(95, 62, list1.getPreferredSize().width,
				list1.getPreferredSize().height);
		list1.addActionListener(new ActionSearch(this, "EHSP", "NumPersonnel", jNumPersonnel));
		personnel.add(list1);
		bp3.setBounds(10, 82, bp3.getPreferredSize().width, bp3.getPreferredSize().height);
		AutoCompleteModel modelCategorie= new AutoCompleteModel();
		modelCategorie.addAll(listCategorie());
		jNumCategorie = new AutoComplete(modelCategorie);
		jNumCategorie.setBounds(30, 107, 60, 20);
		jNumCategorie.getZoneTexte().addKeyListener(new EcouteAction(jNumCategorie.getZoneTexte(), false));
		jNumCategorie.getZoneTexte().addFocusListener(new FocusJText(this, "EHSP"));
		list2 = new JButton(icon2);
		list2.setPreferredSize(new Dimension(19, 20));
		list2.setBounds(95, 107, list2.getPreferredSize().width,
				list2.getPreferredSize().height);
		list2.addActionListener(new ActionSearch(this, "EHSP", "NumCategorie", jNumCategorie));
		personnel.add(list2);
		bg.add(bp1);
		bg.add(bp2);
		bg.add(bp3);
		bp1.addActionListener(new ActionPersonnel());
		bp2.addActionListener(new ActionPersonnel());
		bp3.addActionListener(new ActionPersonnel());
		personnel.add(bp1);
		personnel.add(jNumPersonnel);
		personnel.add(bp2);
		personnel.add(jNumCategorie);
		personnel.add(bp3);
		
		list1.setVisible(false);
		jNumPersonnel.setVisible(false);
		list2.setVisible(false);
		jNumCategorie.setVisible(false);
		pointage.add(personnel);
		
		JPanel Semaine = new JPanel();
		Semaine.setBorder(BorderFactory.createTitledBorder("Semaine"));
		Semaine.setBounds(145, 95, 130, 142);
		Semaine.setLayout(null);
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		ButtonGroup bg2 = new ButtonGroup();
		bs1 = new JRadioButton("Toutes", true);
		bs2 = new JRadioButton("Intervales", false);
		bs1.setBounds(10, 15, bs1.getPreferredSize().width, bs1.getPreferredSize().height);
		bs2.setBounds(10, 49, bs2.getPreferredSize().width, bs2.getPreferredSize().height);
		de = new JLabel("de");
		de.setBounds(10, 85, de.getPreferredSize().width, de.getPreferredSize().height);
		a = new JLabel("à");
		intervalle1 = new JFormattedTextField(num);
		intervalle1.setBounds(12 + de.getPreferredSize().width, 85, 40, 20);
		intervalle1.addKeyListener(new EcouteAction(intervalle1, false));
		a.setBounds(55 + de.getPreferredSize().width, 85, a.getPreferredSize().width, a.getPreferredSize().height);
		intervalle2 = new JFormattedTextField(num);
		intervalle2.setBounds(58 + de.getPreferredSize().width + a.getPreferredSize().width, 85, 40, 20);
		intervalle2.addKeyListener(new EcouteAction(intervalle2, false));
		bg2.add(bs1);
		bg2.add(bs2);
		bs1.addActionListener(new ActionSemaine());
		bs2.addActionListener(new ActionSemaine());
		intervalle1.setText("1");
		intervalle2.setText("1");
		Semaine.add(bs1);
		Semaine.add(bs2);
		Semaine.add(de);
		Semaine.add(a);
		Semaine.add(intervalle1);
		Semaine.add(intervalle2);
		
		de.setVisible(false);
		a.setVisible(false);
		intervalle1.setVisible(false);
		intervalle2.setVisible(false);
	
		
		pointage.add(Semaine);
		
		JPanel options = new JPanel();
		options.setBorder(BorderFactory.createTitledBorder("Options"));
		options.setBounds(277, 95, 315, 142);
		options.setLayout(null);
		cb1 = new JCheckBox("Devis", true);
		cb2 = new JCheckBox("Commandes", true);
		cb1.setBounds(10,15, cb1.getPreferredSize().width, cb1.getPreferredSize().height);
		cb2.setBounds(10,45, cb2.getPreferredSize().width, cb2.getPreferredSize().height);
		options.add(cb1);
		options.add(cb2);
		
		AutoCompleteModel modelDevis1 = new AutoCompleteModel();
		modelDevis1.addAll(listDevis());
		jNumDevis = new AutoComplete(modelDevis1);
		jNumDevis.getZoneTexte().addKeyListener(new EcouteAction(jNumDevis.getZoneTexte(), false));
		jNumDevis.setPreferredSize(new Dimension(60, 20));
		jNumDevis.setBounds(110, 18, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
		jNumDevis.getZoneTexte().addFocusListener(new FocusJText(this, "EHSP"));
		options.add(jNumDevis);
		list3 = new JButton(icon2);
		list3.setPreferredSize(new Dimension(19, 20));
		list3.setBounds(115 + jNumDevis.getPreferredSize().width, 18, list3.getPreferredSize().width,
				list3.getPreferredSize().height);
		list3.addActionListener(new ActionSearch(this, "EHSP", "NumDevis", jNumDevis));
		options.add(list3);
		jNumCommande = new AutoComplete(modelDevis1);
		jNumCommande.getZoneTexte().addKeyListener(new EcouteAction(jNumCommande.getZoneTexte(), false));
		jNumCommande.setPreferredSize(new Dimension(60, 20));
		jNumCommande.setBounds(110, 48, jNumCommande.getPreferredSize().width, jNumCommande.getPreferredSize().height);
		jNumCommande.getZoneTexte().addFocusListener(new FocusJText(this, "EHSP"));
		options.add(jNumCommande);
		list4 = new JButton(icon2);
		list4.setPreferredSize(new Dimension(19, 20));
		list4.setBounds(115 + jNumCommande.getPreferredSize().width, 48, list4.getPreferredSize().width,
				list4.getPreferredSize().height);
		list4.addActionListener(new ActionSearch(this, "EHSP", "NumCommande", jNumCommande));
		options.add(list4);
		
		annee = new JLabel("Pour l'année : ");
		annee.setBounds(30, 90, annee.getPreferredSize().width, annee.getPreferredSize().height);
		jAnnee2 = new JFormattedTextField(num);
		jAnnee2.setBounds(35 + annee.getPreferredSize().width, 90, 60, 20);
		int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
		jAnnee2.setText(anneeEnCours +"");
		jAnnee2.addKeyListener(new EcouteAction(jAnnee2, false));
		options.add(annee);
		options.add(jAnnee2);
		

		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Rupture"));
		rupture.setBounds(120 + jNumCommande.getPreferredSize().width + list4.getPreferredSize().width, 10, 110, 125);
		rupture.setLayout(null);
		
		options.add(rupture);
		
		ButtonGroup bg3 = new ButtonGroup();
		br1 = new JRadioButton("Semaine", true);
		br2 = new JRadioButton("Personnel", false);
		br3 = new JRadioButton("Clients", false);
		br1.setBounds(8, 25, br1.getPreferredSize().width, br1.getPreferredSize().height);
		br2.setBounds(8, 55, br2.getPreferredSize().width, br2.getPreferredSize().height);
		br3.setBounds(8,  85,  br3.getPreferredSize().width, br3.getPreferredSize().height);
		
		bg3.add(br1);
		bg3.add(br2);
		bg3.add(br3);
		rupture.add(br1);
		rupture.add(br2);
		rupture.add(br3);
		pointage.add(options);		
	}
	
	protected ArrayList<String> listDevis() {
		listDevis = donnees.liste("NumDevis, LblDevis", "devis", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listDevis.length; i++) {
			res.add(listDevis[i][0].toString());
		}
		return res;
	}
	
	protected ArrayList<String> listClients() {
		listClients = donnees.liste("NumClient, NomClient", "clients", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listClients.length; i++) {
			res.add(listClients[i][0].toString());
		}
		return res;
	}
	protected ArrayList<String> listPersonnel() {
		listPersonnel = donnees.liste("NumPersonnel", "personne", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listPersonnel.length; i++) {
			res.add(listPersonnel[i][0].toString());
		}
		return res;
	}
	protected ArrayList<String> listCategorie() {
		listCategorie = donnees.liste("CodeTypePersonne", "typepers", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listCategorie.length; i++) {
			res.add(listCategorie[i][0].toString());
		}
		return res;
	}

	public Object[][] getListDevis() {
		return listDevis;
	}

	public void setListDevis(Object[][] listDevis) {
		this.listDevis = listDevis;
	}

	public Object[][] getListClients() {
		return listClients;
	}

	public void setListClients(Object[][] listClients) {
		this.listClients = listClients;
	}

	public JLabel getLabelClient() {
		return labelClient;
	}

	public void setLabelClient(JLabel labelClient) {
		this.labelClient = labelClient;
	}

	public JLabel getLabelClient2() {
		return labelClient2;
	}

	public void setLabelClient2(JLabel labelClient2) {
		this.labelClient2 = labelClient2;
	}

	public JDateChooser getjDate() {
		return jDate;
	}

	public void setjDate(JDateChooser jDate) {
		this.jDate = jDate;
	}

	public JRadioButton getBr1() {
		return br1;
	}

	public void setBr1(JRadioButton br1) {
		this.br1 = br1;
	}

	public JRadioButton getBr2() {
		return br2;
	}

	public void setBr2(JRadioButton br2) {
		this.br2 = br2;
	}

	public JRadioButton getBr3() {
		return br3;
	}

	public void setBr3(JRadioButton br3) {
		this.br3 = br3;
	}

	public JFormattedTextField getjAnnee() {
		return jAnnee;
	}

	public void setjAnnee(JFormattedTextField jAnnee) {
		this.jAnnee = jAnnee;
	}

	public JFormattedTextField getjAnnee2() {
		return jAnnee2;
	}

	public void setjAnnee2(JFormattedTextField jAnnee2) {
		this.jAnnee2 = jAnnee2;
	}

	public JFormattedTextField getIntervalle1() {
		return intervalle1;
	}

	public void setIntervalle1(JFormattedTextField intervalle1) {
		this.intervalle1 = intervalle1;
	}

	public JFormattedTextField getIntervalle2() {
		return intervalle2;
	}

	public void setIntervalle2(JFormattedTextField intervalle2) {
		this.intervalle2 = intervalle2;
	}

	public JPanel getPointage() {
		return pointage;
	}

	public void setPointage(JPanel pointage) {
		this.pointage = pointage;
	}

	public JRadioButton getBp1() {
		return bp1;
	}

	public void setBp1(JRadioButton bp1) {
		this.bp1 = bp1;
	}

	public JRadioButton getBp2() {
		return bp2;
	}

	public void setBp2(JRadioButton bp2) {
		this.bp2 = bp2;
	}

	public JRadioButton getBp3() {
		return bp3;
	}

	public void setBp3(JRadioButton bp3) {
		this.bp3 = bp3;
	}

	public JRadioButton getBs1() {
		return bs1;
	}

	public void setBs1(JRadioButton bs1) {
		this.bs1 = bs1;
	}

	public JRadioButton getBs2() {
		return bs2;
	}

	public void setBs2(JRadioButton bs2) {
		this.bs2 = bs2;
	}

	public AutoComplete getjNumPersonnel() {
		return jNumPersonnel;
	}

	public void setjNumPersonnel(AutoComplete jNumPersonnel) {
		this.jNumPersonnel = jNumPersonnel;
	}

	public AutoComplete getjNumCategorie() {
		return jNumCategorie;
	}

	public void setjNumCategorie(AutoComplete jNumCategorie) {
		this.jNumCategorie = jNumCategorie;
	}

	public AutoComplete getjNumDevis() {
		return jNumDevis;
	}

	public void setjNumDevis(AutoComplete jNumDevis) {
		this.jNumDevis = jNumDevis;
	}

	public AutoComplete getjNumCommande() {
		return jNumCommande;
	}

	public void setjNumCommande(AutoComplete jNumCommande) {
		this.jNumCommande = jNumCommande;
	}

	public JCheckBox getCb1() {
		return cb1;
	}

	public void setCb1(JCheckBox cb1) {
		this.cb1 = cb1;
	}

	public JCheckBox getCb2() {
		return cb2;
	}

	public void setCb2(JCheckBox cb2) {
		this.cb2 = cb2;
	}
		
	class ActionPersonnel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton radio = (JRadioButton)e.getSource();
			if(radio == bp1){
				list1.setVisible(false);
				jNumPersonnel.setVisible(false);
				list2.setVisible(false);
				jNumCategorie.setVisible(false);
			}
			else if(radio == bp2){
				list1.setVisible(true);
				jNumPersonnel.setVisible(true);
				list2.setVisible(false);
				jNumCategorie.setVisible(false);
			}
			else if(radio == bp3){
				list1.setVisible(false);
				jNumPersonnel.setVisible(false);
				list2.setVisible(true);
				jNumCategorie.setVisible(true);
			}
			
		}
		
	}
	
	class ActionSemaine implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton radio = (JRadioButton)e.getSource();
			if(radio == bs1){
				de.setVisible(false);
				a.setVisible(false);
				intervalle1.setVisible(false);
				intervalle2.setVisible(false);
			}
			else if(radio == bs2){
				de.setVisible(true);
				a.setVisible(true);
				intervalle1.setVisible(true);
				intervalle2.setVisible(true);
			}
			
		}
		
	}
}
