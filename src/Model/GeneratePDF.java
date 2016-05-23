package Model;

import java.awt.HeadlessException;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

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
import Thread.ThreadImpression;

public class GeneratePDF {

	protected Base bdd;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public GeneratePDF(String numfacture, Base base, Double valeur, ThreadImpression thread) {
		this.bdd = base;
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

			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("." + File.separator));
			int reponse = chooser.showDialog(chooser, "Enregistrer sous");
			if (reponse == JFileChooser.APPROVE_OPTION) {
				String fichier = chooser.getSelectedFile().toString();
				String extension = fichier.substring(fichier.length()-4, fichier.length());
				System.out.println(fichier + " : " + extension);
				if(!extension.equals(".pdf")){
					fichier += "."+"pdf";
				}
				thread.getFrame().setVisible(true);
				thread.getFrame().setAlwaysOnTop(true);
				try {
					// - Chargement et compilation du rapport
					org.apache.log4j.BasicConfigurator.configure();
					JasperDesign jasperDesign; 
					if(valeur >= 0){
						jasperDesign = JRXmlLoader.load("lib/modeles/Factures.jrxml");
					}
					else{
						jasperDesign = JRXmlLoader.load("lib/modeles/Avoir.jrxml");
					}
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

					// - Paramètres à envoyer au rapport
					Map parameters = new HashMap();
					parameters.put("NumFacture", Integer.parseInt(numfacture));
					parameters.put("ValeurText", result);
		            String path = new File("lib/images/STID.png").getAbsolutePath();
		            parameters.put("Logo", path);
					// - Execution du rapport
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, bdd.getCon());
					PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

					MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName();
					printRequestAttributeSet.add(mediaSizeName);
					printRequestAttributeSet.add(new Copies(1));
					JRPrintServiceExporter exporter = new JRPrintServiceExporter();

					JasperExportManager.exportReportToPdfFile(jasperPrint, fichier);
					thread.getFrame().setVisible(false);
					JOptionPane.showMessageDialog(null, "PDF généré !");
					
					
				} catch (JRException e) {

					e.printStackTrace();
				}
			}
		} catch (HeadlessException he) {
			he.printStackTrace();
		}
		org.apache.log4j.BasicConfigurator.configure();

	}

}