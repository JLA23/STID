package Model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import BDD.Base;
import Thread.ThreadImpression;

public class Apercu {
	
	protected Base bdd;
		
    @SuppressWarnings("unchecked")
	public Apercu(String numFacture, Base base, String valeurtext, ThreadImpression thread) {
    	this.bdd = base;
        org.apache.log4j.BasicConfigurator.configure();
        try {
            // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("lib/modeles/STID.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Paramètres à envoyer au rapport
            @SuppressWarnings("rawtypes")
			Map parameters = new HashMap();
            parameters.put("NumFacture", Integer.parseInt(numFacture));
            parameters.put("ValeurText", valeurtext);
            String path = new File("lib/images/STID.png").getAbsolutePath();
            parameters.put("Logo", path);

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd.getCon());
            JasperViewer jReportsViewer = new JasperViewer(jasperPrint, "Eric", 3);
            thread.getFrame().setVisible(false);
            jReportsViewer.setVisible(true);
        } catch (JRException e) {

            e.printStackTrace();
        } 

    }
    
    
}