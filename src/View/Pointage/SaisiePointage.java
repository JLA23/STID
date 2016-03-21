package View.Pointage;

import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

public class SaisiePointage extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JLabel HeuresNormal, numDevis, HeuresSuppCoef125, HeuresSuppCoef15, HeuresSuppCoef2, codeOuvrier, semAnnee;
	private JFormattedTextField jNumDevis, jHN, jHSC125, jHSC15, jHSC2, jCode;
	private JButton calcul1, calcul2, calcul3, calcul4, list1, list2, nouveau, fermer;
	private JDateChooser jDate;
	
	public SaisiePointage(){
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Nouvelle Facture)");
		screenSize.width = 620;
		screenSize.height = 350;
		ImageIcon icon = new ImageIcon("lib/images/1447428838_calculate_16x16.gif");
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		this.setIconImage(new ImageIcon("lib/images/e.png").getImage());
	    this.setSize(screenSize);
	    
	    numDevis = new JLabel("N° Devis");
	    NumberFormat num =  NumberFormat.getIntegerInstance();
	    jNumDevis = new JFormattedTextField(num);	    
	    numDevis.setBounds(130 - numDevis.getPreferredSize().width ,20, numDevis.getPreferredSize().width, numDevis.getPreferredSize().height);
	    this.add(numDevis);
	    jNumDevis.setPreferredSize(new Dimension(100, 25));
	    jNumDevis.setBounds(135, 18, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
	    this.add(jNumDevis);
	    list1 = new JButton(icon2);
	    list1.setPreferredSize(new Dimension(19, 20));
		list1.setBounds(140 + jNumDevis.getPreferredSize().width, 20, list1.getPreferredSize().width, list1.getPreferredSize().height);
	    this.add(list1);
	    
	    HeuresNormal = new JLabel("Heures Normales");
	    jHN = new JFormattedTextField(num);	    
	    HeuresNormal.setBounds(130 - HeuresNormal.getPreferredSize().width ,60, HeuresNormal.getPreferredSize().width, HeuresNormal.getPreferredSize().height);
	    this.add(HeuresNormal);
	    jHN.setPreferredSize(new Dimension(100, 25));
	    jHN.setBounds(135, 58, jNumDevis.getPreferredSize().width, jNumDevis.getPreferredSize().height);
	    this.add(jHN);
	    calcul1 = new JButton(icon);
	    calcul1.setPreferredSize(new Dimension(19, 20));
		calcul1.setBounds(140 + jNumDevis.getPreferredSize().width, 60, calcul1.getPreferredSize().width, calcul1.getPreferredSize().height);
	    this.add(calcul1);
	    
	    HeuresSuppCoef125 = new JLabel("Heures Supp Coef : 1.25");
	    jHSC125 = new JFormattedTextField(num);	    
	    HeuresSuppCoef125.setBounds(450 - HeuresSuppCoef125.getPreferredSize().width ,60, HeuresSuppCoef125.getPreferredSize().width, HeuresSuppCoef125.getPreferredSize().height);
	    this.add(HeuresSuppCoef125);
	    jHSC125.setPreferredSize(new Dimension(100, 25));
	    jHSC125.setBounds(455, 58, jHSC125.getPreferredSize().width, jHSC125.getPreferredSize().height);
	    this.add(jHSC125);
	    calcul2 = new JButton(icon);
	    calcul2.setPreferredSize(new Dimension(19, 20));
		calcul2.setBounds(460 + jHSC125.getPreferredSize().width, 60, calcul2.getPreferredSize().width, calcul2.getPreferredSize().height);
	    this.add(calcul2);
	    
	    HeuresSuppCoef15 = new JLabel("Coef : 1.50");
	    jHSC15 = new JFormattedTextField(num);	    
	    HeuresSuppCoef15.setBounds(450 - HeuresSuppCoef15.getPreferredSize().width ,100, HeuresSuppCoef15.getPreferredSize().width, HeuresSuppCoef15.getPreferredSize().height);
	    this.add(HeuresSuppCoef15);
	    jHSC15.setPreferredSize(new Dimension(100, 25));
	    jHSC15.setBounds(455, 98, jHSC15.getPreferredSize().width, jHSC15.getPreferredSize().height);
	    this.add(jHSC15);
	    calcul3 = new JButton(icon);
	    calcul3.setPreferredSize(new Dimension(19, 20));
		calcul3.setBounds(460 + jHSC15.getPreferredSize().width, 100, calcul3.getPreferredSize().width, calcul3.getPreferredSize().height);
	    this.add(calcul3);
	    
	    HeuresSuppCoef2 = new JLabel("Coef : 2.00");
	    jHSC2 = new JFormattedTextField(num);	    
	    HeuresSuppCoef2.setBounds(450 - HeuresSuppCoef2.getPreferredSize().width ,140, HeuresSuppCoef2.getPreferredSize().width, HeuresSuppCoef2.getPreferredSize().height);
	    this.add(HeuresSuppCoef2);
	    jHSC2.setPreferredSize(new Dimension(100, 25));
	    jHSC2.setBounds(455, 138, jHSC2.getPreferredSize().width, jHSC2.getPreferredSize().height);
	    this.add(jHSC2);
	    calcul4 = new JButton(icon);
	    calcul4.setPreferredSize(new Dimension(19, 20));
		calcul4.setBounds(460 + jHSC2.getPreferredSize().width, 140, calcul4.getPreferredSize().width, calcul4.getPreferredSize().height);
	    this.add(calcul4);
	    
	    codeOuvrier = new JLabel("Code Ouvrier");
	    jCode = new JFormattedTextField(num);	    
	    codeOuvrier.setBounds(130 - codeOuvrier.getPreferredSize().width ,180, codeOuvrier.getPreferredSize().width, codeOuvrier.getPreferredSize().height);
	    this.add(codeOuvrier);
	    jCode.setPreferredSize(new Dimension(100, 25));
	    jCode.setBounds(135, 178, jCode.getPreferredSize().width, jCode.getPreferredSize().height);
	    this.add(jCode);
	    list2 = new JButton(icon2);
	    list2.setPreferredSize(new Dimension(19, 20));
		list2.setBounds(140 + jCode.getPreferredSize().width, 180, list2.getPreferredSize().width, list2.getPreferredSize().height);
	    this.add(list2);
	    
	    jDate = new JDateChooser();
	    jDate.setDateFormatString("ww/yyyy");
		jDate.setDate(new Date());
		semAnnee = new JLabel("Sem/Année");	    
	    semAnnee.setBounds(130 - semAnnee.getPreferredSize().width ,220, semAnnee.getPreferredSize().width, semAnnee.getPreferredSize().height);
	    this.add(semAnnee);
	    jDate.setPreferredSize(new Dimension(125, 25));
	    jDate.setBounds(135, 218, jDate.getPreferredSize().width, jDate.getPreferredSize().height);
	    this.add(jDate);
	    
	    nouveau = new JButton("Nouveau");
	    fermer = new JButton("Fermer");
	    
	    nouveau.setBounds(10, 285, nouveau.getPreferredSize().width, nouveau.getPreferredSize().height);
	    this.add(nouveau);
	    
	    fermer.setBounds(530, 285, fermer.getPreferredSize().width, fermer.getPreferredSize().height);
	    this.add(fermer);  
	    
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
		
	}

}
