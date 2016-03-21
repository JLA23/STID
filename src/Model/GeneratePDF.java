package Model;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import BDD.Base;

public class GeneratePDF {
	
	protected Base bdd;

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public GeneratePDF(String numfacture, Base base, String valeurtext){

    	this.bdd = base;
        org.apache.log4j.BasicConfigurator.configure();
        try {

            // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("lib/modeles/STID.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Paramètres à envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("NumFacture", Integer.parseInt(numfacture));
            parameters.put("ValeurText", valeurtext);
            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd.getCon());
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            
            MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName(); 
            printRequestAttributeSet.add(mediaSizeName);  
            printRequestAttributeSet.add(new Copies(1));
            JRPrintServiceExporter exporter = new JRPrintServiceExporter();

            JasperExportManager.exportReportToPdfFile(jasperPrint, "factureTest.pdf");
            
        } catch (JRException e) {

            e.printStackTrace();
        }

    }
    
    
}