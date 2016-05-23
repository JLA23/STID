package Model;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

import BDD.Base;
import Thread.ThreadImpression;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class Imprimer {
	
	protected Base bdd;
	protected String numfacture;
	protected Double valeur;
	protected ThreadImpression thread;
		
    public Imprimer(String numFacture, Base base, Double valeur, ThreadImpression thread) {
    	this.bdd = base;
    	this.numfacture = numFacture;
    	this.valeur = valeur;
    	this.thread = thread;
    	init();
    }
    
    @SuppressWarnings("unchecked")
	private void init() {
    	Double val = valeur;
		if(val < 0){
			val = val * -1;
		}
		int i = new Double(val).intValue(); //recuperer la partie entiere
		double decimale = ((val*100)-(new Double(i).doubleValue() * 100))/100;
		decimale = decimale * 100;
		int d = new Double(decimale).intValue();
	
		NumberFormat formatter = new RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT);
		String result = formatter.format(new Double(i).doubleValue());
		result += " euros " + formatter.format(new Double(d).doubleValue()) + " cts";
        try {
        	
            // - Chargement et compilation du rapport
        	//JOptionPane.showMessageDialog(null, "Impression en cours !");
            org.apache.log4j.BasicConfigurator.configure();
            JasperDesign jasperDesign;
            if(valeur >= 0){
				jasperDesign = JRXmlLoader.load("lib/modeles/Factures.jrxml");
			}
			else{
				jasperDesign = JRXmlLoader.load("lib/modeles/Avoir.jrxml");
			}
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Param�tres � envoyer au rapport
            @SuppressWarnings("rawtypes")
			Map parameters = new HashMap();
            parameters.put("NumFacture", Integer.parseInt(numfacture));
            parameters.put("ValeurText", result);
            String path = new File("lib/images/STID.png").getAbsolutePath();
            parameters.put("Logo", path);
   
            // - Execution du rapport
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd.getCon());
            thread.getFrame().setVisible(false);
            new JRViewer(jasperPrint).printManager("Test", 3);
        } catch (JRException e) {

            e.printStackTrace();
        } 

    }    
}