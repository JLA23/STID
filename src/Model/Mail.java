package Model;

import java.util.Properties;
import javax.mail.internet.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CryptageDecryptage.Decryptage;
import Thread.ThreadMail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import java.util.*;

public class Mail {
	private final static String MAILER_VERSION = "Java";

	public static void envoyerMail(String [] fichierText, String file, String adress, String objet, String messageMail, JFrame frame, ThreadMail thm) {

		try {
			Properties prop = System.getProperties();
			prop.put("mail.smtp.auth", "true");
	        //SSL
			if(fichierText[4].equals("true")){
	        	prop.put("mail.smtp.socketFactory.port", Integer.parseInt(fichierText[3]));
	    		prop.put("mail.smtp.socketFactory.class",
	    				"javax.net.ssl.SSLSocketFactory");
	        }
	        //TLS
	        if(fichierText[5].equals("true")){
	        	prop.put("mail.smtp.starttls.enable","true");
	        }
			prop.put("mail.smtp.host", fichierText[2]);
			prop.put("mail.smtp.port", Integer.parseInt(fichierText[3]));
			Session session = Session.getDefaultInstance(prop, null);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fichierText[0]));
			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress(adress);
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject(objet);
			// message.setText("test mail");
			message.setHeader("X-Mailer", MAILER_VERSION);
			message.setSentDate(new Date());
			session.setDebug(true);
			// Première partie du message
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(messageMail);

			// Ajout de la première partie du message dans un objet Multipart
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Partie de la pièce jointe
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(file);

			// Ajout de la partie pièce jointe
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			session.setDebug(true);
			// Transport.send(message);

			// ajout des éléments au mail
			// message.setContent(multipart);
			Transport.send(message, fichierText[0], new Decryptage().decrypt(fichierText[1]));
			thm.getFrame().setVisible(false);
			JOptionPane.showMessageDialog(null, "Mail envoyé avec succès", "Mail", JOptionPane.INFORMATION_MESSAGE);
			frame.setEnabled(true);
			frame.setVisible(true);
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'adresse : " + e.getMessage(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Le Message ne s'est pas envoyé : " + e.getMessage(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean testConnexion(int port, String host, String user, String pwd, String ssl, String tls) {
		boolean resultat = false;
	    try {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        if(ssl.equals("true")){
	        	props.put("mail.smtp.socketFactory.port", port);
	    		props.put("mail.smtp.socketFactory.class",
	    				"javax.net.ssl.SSLSocketFactory");
	        }
	        if(tls.equals("true")){
	        	props.put("mail.smtp.starttls.enable","true");
	        }
	        Session session = Session.getInstance(props, null);
			String[] tmp = host.split("\\.");
	        Transport transport = session.getTransport(tmp[0]);
	        transport.connect(host, port, user, pwd);
	        transport.close();
	        resultat = true;
	     } 
	     catch(AuthenticationFailedException e) {
	    	 JOptionPane.showMessageDialog(null, "Echec d'autentification : \n" + e.getMessage(), "ERREUR", JOptionPane.ERROR_MESSAGE);
	     }
	     catch(MessagingException e) {
	    	 JOptionPane.showMessageDialog(null, "Echec : " + e.getMessage(), "ERREUR", JOptionPane.ERROR_MESSAGE);
	     }
	    return resultat;
	}

}
