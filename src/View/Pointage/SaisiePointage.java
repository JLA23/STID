package View.Pointage;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import BDD.Base;
import Controller.ActionCalculatrice;
import Controller.ActionFermer;
import Controller.ActionNouveau;
import Controller.ActionSearch;
import Controller.EcouteAction;
import Controller.ExecuteClick;
import Controller.FocusJText;
import Controller.FocusPosition;
import Model.Donnees;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class SaisiePointage extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JLabel HeuresNormal, numDevis, HeuresSuppCoef125, HeuresSuppCoef15, HeuresSuppCoef2, codeOuvrier, semAnnee;
	private JFormattedTextField jHN, jHSC125, jHSC15, jHSC2;
	private JButton calcul1, calcul2, calcul3, calcul4, list1, list2, nouveau, fermer;
	private AutoComplete jNumDevis, jCode;
	protected Object[][] listDevis, listCode;
	private JDateChooser jDate;
	private Donnees donnees;
	private Base base;
	private JFrame fenetre;
	
	public SaisiePointage(Base bdd, JFrame frame){
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Saisie Pointage)");
		this.fenetre = frame;
		fenetre.setEnabled(false);
		screenSize.width = 620;
		screenSize.height = 350;
		ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    
		this.base = bdd;
		this.donnees = new Donnees(base);
	    
		ExecuteClick click = new ExecuteClick(this, null);
		
	    numDevis = new JLabel("N° Devis");
		DecimalFormat num = new DecimalFormat("#0.00");
		num.setGroupingUsed(false);
	    AutoCompleteModel model = new AutoCompleteModel();
		model.addAll(listDevis());
	    jNumDevis = new AutoComplete(model);
	    numDevis.setBounds(130 - numDevis.getPreferredSize().width ,20, numDevis.getPreferredSize().width, numDevis.getPreferredSize().height);
	    this.add(numDevis);
	    jNumDevis.setPreferredSize(new Dimension(100, 25));
	    jNumDevis.setBounds(135, 18, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
	    this.add(jNumDevis);
	    jNumDevis.getZoneTexte().addFocusListener(new FocusJText(this, "Pointage"));
	    list1 = new JButton(icon2);
	    list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(140 + jNumDevis.getPreferredSize().width, 20, list1.getPreferredSize().width, list1.getPreferredSize().height);
	    this.add(list1);
	    list1.addActionListener(new ActionSearch(this, "Pointage", "NumDevis") );
	    
	    HeuresNormal = new JLabel("Heures Normales");
	    jHN = new JFormattedTextField(num);	    
	    HeuresNormal.setBounds(130 - HeuresNormal.getPreferredSize().width ,60, HeuresNormal.getPreferredSize().width, HeuresNormal.getPreferredSize().height);
	    this.add(HeuresNormal);
	    jHN.setPreferredSize(new Dimension(100, 25));
	    jHN.setBounds(135, 58, jHN.getPreferredSize().width, jHN.getPreferredSize().height);
	    this.add(jHN);
	    jHN.setText("0,00");
	    jHN.addKeyListener(new EcouteAction(jHN));
	    jHN.addMouseListener(new FocusPosition(jHN, 0, click));
	    jHN.addFocusListener(new FocusPosition(jHN, 0, click));
	    calcul1 = new JButton(icon);
	    calcul1.setPreferredSize(new Dimension(19, 20));
		calcul1.setBounds(140 + jNumDevis.getPreferredSize().width, 60, calcul1.getPreferredSize().width, calcul1.getPreferredSize().height);
	    this.add(calcul1);
	    calcul1.addActionListener(new ActionCalculatrice(this, jHN, 0, null));
	    
	    HeuresSuppCoef125 = new JLabel("Heures Supp Coef : 1.25");
	    jHSC125 = new JFormattedTextField(num);	    
	    HeuresSuppCoef125.setBounds(450 - HeuresSuppCoef125.getPreferredSize().width ,60, HeuresSuppCoef125.getPreferredSize().width, HeuresSuppCoef125.getPreferredSize().height);
	    this.add(HeuresSuppCoef125);
	    jHSC125.setPreferredSize(new Dimension(100, 25));
	    jHSC125.setBounds(455, 58, jHSC125.getPreferredSize().width, jHSC125.getPreferredSize().height);
	    this.add(jHSC125);
	    jHSC125.setText("0,00");
	    jHSC125.addKeyListener(new EcouteAction(jHSC125));
	    jHSC125.addMouseListener(new FocusPosition(jHSC125, 0, click));
	    jHSC125.addFocusListener(new FocusPosition(jHSC125, 0, click));
	    calcul2 = new JButton(icon);
	    calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(460 + jHSC125.getPreferredSize().width, 60, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
	    this.add(calcul2);
	    calcul2.addActionListener(new ActionCalculatrice(this, jHSC125, 0, null));
	    
	    HeuresSuppCoef15 = new JLabel("Coef : 1.50");
	    jHSC15 = new JFormattedTextField(num);	    
	    HeuresSuppCoef15.setBounds(450 - HeuresSuppCoef15.getPreferredSize().width ,100, HeuresSuppCoef15.getPreferredSize().width, HeuresSuppCoef15.getPreferredSize().height);
	    this.add(HeuresSuppCoef15);
	    jHSC15.setPreferredSize(new Dimension(100, 25));
	    jHSC15.setBounds(455, 98, jHSC15.getPreferredSize().width, jHSC15.getPreferredSize().height);
	    this.add(jHSC15);
	    jHSC15.setText("0,00");
	    jHSC15.addKeyListener(new EcouteAction(jHSC15));
	    jHSC15.addMouseListener(new FocusPosition(jHSC15, 0, click));
	    jHSC15.addFocusListener(new FocusPosition(jHSC15, 0, click));
	    calcul3 = new JButton(icon);
	    calcul3.setPreferredSize(new Dimension(19, 20));
		calcul3.setBounds(460 + jHSC15.getPreferredSize().width, 100, calcul3.getPreferredSize().width, calcul3.getPreferredSize().height);
	    this.add(calcul3);
	    calcul3.addActionListener(new ActionCalculatrice(this, jHSC15, 0, null));
	    
	    HeuresSuppCoef2 = new JLabel("Coef : 2.00");
	    jHSC2 = new JFormattedTextField(num);
	    jHSC2.setText("0,00");
	    HeuresSuppCoef2.setBounds(450 - HeuresSuppCoef2.getPreferredSize().width ,140, HeuresSuppCoef2.getPreferredSize().width, HeuresSuppCoef2.getPreferredSize().height);
	    this.add(HeuresSuppCoef2);
	    jHSC2.setPreferredSize(new Dimension(100, 25));
	    jHSC2.setBounds(455, 138, jHSC2.getPreferredSize().width, jHSC2.getPreferredSize().height);
	    this.add(jHSC2);
	    jHSC2.addKeyListener(new EcouteAction(jHSC2));
	    jHSC2.addMouseListener(new FocusPosition(jHSC2, 0, click));
	    jHSC2.addFocusListener(new FocusPosition(jHSC2, 0, click));
	    calcul4 = new JButton(icon);
	    calcul4.setPreferredSize(new Dimension(19, 20));
		calcul4.setBounds(460 + jHSC2.getPreferredSize().width, 140, calcul4.getPreferredSize().width, calcul4.getPreferredSize().height);
	    this.add(calcul4);
	    calcul4.addActionListener(new ActionCalculatrice(this, jHSC2, 0, null));
	    
	    codeOuvrier = new JLabel("Code Ouvrier");
	    AutoCompleteModel model2 = new AutoCompleteModel();
		model2.addAll(listPersonne());
		jCode = new AutoComplete(model2);	    
	    codeOuvrier.setBounds(130 - codeOuvrier.getPreferredSize().width ,180, codeOuvrier.getPreferredSize().width, codeOuvrier.getPreferredSize().height);
	    this.add(codeOuvrier);
	    jCode.setPreferredSize(new Dimension(100, 25));
	    jCode.setBounds(135, 178, jCode.getPreferredSize().width, jCode.getPreferredSize().height);
	    this.add(jCode);
	    jCode.getZoneTexte().addFocusListener(new FocusJText(this, "Pointage"));
	    list2 = new JButton(icon2);
	    list2.setPreferredSize(new Dimension(19, 20));
		list2.setBounds(140 + jCode.getPreferredSize().width, 180, list2.getPreferredSize().width, list2.getPreferredSize().height);
	    this.add(list2);
	    list2.addActionListener(new ActionSearch(this, "Pointage", "NumCode"));
	    
	    jDate = new JDateChooser();
	    jDate.setDateFormatString("ww/yyyy");
		jDate.setDate(new Date());
		semAnnee = new JLabel("Sem/Année");	    
	    semAnnee.setBounds(130 - semAnnee.getPreferredSize().width ,220, semAnnee.getPreferredSize().width, semAnnee.getPreferredSize().height);
	    this.add(semAnnee);
	    jDate.setPreferredSize(new Dimension(125, 25));
	    jDate.setBounds(135, 218, jDate.getPreferredSize().width, jDate.getPreferredSize().height);
	    this.add(jDate);
	    jDate.getDateEditor().addPropertyChangeListener(new ChangeDate(this));
	    
	    nouveau = new JButton("Nouveau");
	    fermer = new JButton("Fermer");
	    
	    nouveau.setBounds(10, 285, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
	    this.add(nouveau);
	    nouveau.addActionListener(new ActionNouveau(this, "Pointage"));
	    
	    fermer.setBounds(530, 285, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
	    this.add(fermer); 
	    fermer.addActionListener(new ActionFermer(this, fenetre));
	    
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
		
	}
	
	protected ArrayList<String> listDevis() {
		listDevis = donnees.liste("NumDevis, LblDevis", "Devis", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listDevis.length; i++) {
			res.add(listDevis[i][0].toString());
		}
		return res;
	}
	
	protected ArrayList<String> listPersonne() {
		listCode = donnees.liste("NumPersonnel, Nom, Prenom", "personne", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listCode.length; i++) {
			res.add(listCode[i][0].toString());
		}
		return res;
	}

	public JFormattedTextField getjHN() {
		return jHN;
	}

	public void setjHN(JFormattedTextField jHN) {
		this.jHN = jHN;
	}

	public JFormattedTextField getjHSC125() {
		return jHSC125;
	}

	public void setjHSC125(JFormattedTextField jHSC125) {
		this.jHSC125 = jHSC125;
	}

	public JFormattedTextField getjHSC15() {
		return jHSC15;
	}

	public void setjHSC15(JFormattedTextField jHSC15) {
		this.jHSC15 = jHSC15;
	}

	public JFormattedTextField getjHSC2() {
		return jHSC2;
	}

	public void setjHSC2(JFormattedTextField jHSC2) {
		this.jHSC2 = jHSC2;
	}

	public AutoComplete getjNumDevis() {
		return jNumDevis;
	}

	public void setjNumDevis(AutoComplete jNumDevis) {
		this.jNumDevis = jNumDevis;
	}

	public AutoComplete getjCode() {
		return jCode;
	}

	public void setjCode(AutoComplete jCode) {
		this.jCode = jCode;
	}

	public JDateChooser getjDate() {
		return jDate;
	}

	public void setjDate(JDateChooser jDate) {
		this.jDate = jDate;
	}

	public Donnees getDonnees() {
		return donnees;
	}

	public void setDonnees(Donnees donnees) {
		this.donnees = donnees;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Object[][] getListDevis() {
		return listDevis;
	}

	public void setListDevis(Object[][] listDevis) {
		this.listDevis = listDevis;
	}

	public Object[][] getListCode() {
		return listCode;
	}

	public void setListCode(Object[][] listCode) {
		this.listCode = listCode;
	}
	
	private class ChangeDate implements PropertyChangeListener {
		
		private SaisiePointage saisi;
		
        public ChangeDate (SaisiePointage saisie){
        	this.saisi = saisie;
        }
        public void propertyChange(PropertyChangeEvent e) {
          new FocusJText(saisi, "Pointage").name();
        }
    }
	
	

}
