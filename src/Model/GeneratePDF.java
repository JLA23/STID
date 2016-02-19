package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.mysql.jdbc.Driver;

public class GeneratePDF {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {

        // - Param�tres de connexion � la base de donn�es
        String url = "jdbc:mysql://localhost/STID";
        String login = "root";
        String password = "root";
        Connection connection = null;
        org.apache.log4j.BasicConfigurator.configure();
        try {
            // - Connexion � la base
            Driver monDriver = new com.mysql.jdbc.Driver();
            //DriverManager.registerDriver(monDriver);
            connection = DriverManager.getConnection(url, login, password);

            // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("ListeClientGroupByNumClient.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Param�tres � envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("NumClient", "NumClient");
            parameters.put("Ordre", "NomClient");
            Date d = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            parameters.put("Date", dateFormat.format(d));
            

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            
            MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName(); 
            printRequestAttributeSet.add(mediaSizeName);  
            printRequestAttributeSet.add(new Copies(1));
            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
           /* exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                   printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                 Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                 Boolean.FALSE);
            exporter.exportReport();*/
            // - Cr�ation du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "test2.pdf");
           // JasperViewer jReportsViewer = new JasperViewer(jasperPrint, "Eric", 3);
            //jReportsViewer.setVisible(true);
            //jReportsViewer.viewReport(jasperPrint);
        } catch (JRException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                 connection.close();
                } catch (SQLException e) {

                        e.printStackTrace();
                }
        }

    }
    
    
}