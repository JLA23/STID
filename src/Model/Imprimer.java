package Model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
	protected String numfacture, valeurtext;
	protected ThreadImpression thread;
		
    public Imprimer(String numFacture, Base base, String valeurtext, ThreadImpression thread) {
    	this.bdd = base;
    	this.numfacture = numFacture;
    	this.valeurtext = valeurtext;
    	this.thread = thread;
    	init();
    }
    
    @SuppressWarnings("unchecked")
	private void init() {
        try {
        	
            // - Chargement et compilation du rapport
        	//JOptionPane.showMessageDialog(null, "Impression en cours !");
            org.apache.log4j.BasicConfigurator.configure();
            JasperDesign jasperDesign = JRXmlLoader.load("lib/modeles/STID.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Paramètres à envoyer au rapport
            @SuppressWarnings("rawtypes")
			Map parameters = new HashMap();
            parameters.put("NumFacture", Integer.parseInt(numfacture));
            parameters.put("ValeurText", valeurtext);
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