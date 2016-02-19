package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import com.mysql.jdbc.Driver;

public class PrintPDF {

    public PrintPDF() {

        // - Paramètres de connexion à la base de données
        String url = "jdbc:mysql://localhost/STID";
        String login = "root";
        String password = "root";
        Connection connection = null;
        org.apache.log4j.BasicConfigurator.configure();
        try {
            // - Connexion à la base
            Driver monDriver = new com.mysql.jdbc.Driver();
            //DriverManager.registerDriver(monDriver);
            connection = DriverManager.getConnection(url, login, password);

            // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("STID.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Paramètres à envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("NumClient", 1);

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            /*PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            
            MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName(); 
            printRequestAttributeSet.add(mediaSizeName);  
            printRequestAttributeSet.add(new Copies(1));
           // JRPrintServiceExporter exporter = new JRPrintServiceExporter();
            /*exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                   printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                 Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                 Boolean.FALSE);
            exporter.exportReport();*/
            // - Création du rapport au format PDF
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "test.pdf");
            JasperViewer jReportsViewer = new JasperViewer(jasperPrint, "Eric", 3);
            System.out.println("Generation fini");
            jReportsViewer.setVisible(true);
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