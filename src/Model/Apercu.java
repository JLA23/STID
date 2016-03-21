package Model;

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

public class Apercu {
	
	protected Base bdd;
		
    @SuppressWarnings("unchecked")
	public Apercu(String numFacture, Base base) {
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

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd.getCon());
            JasperViewer jReportsViewer = new JasperViewer(jasperPrint, "Eric", 3);
            jReportsViewer.setVisible(true);
        } catch (JRException e) {

            e.printStackTrace();
        } 

    }
    
    
}