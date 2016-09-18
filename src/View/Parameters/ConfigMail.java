package View.Parameters;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BDD.Base;
import Controller.ActionFermer;
import Controller.EcouteAction;
import CryptageDecryptage.Cryptage;
import CryptageDecryptage.Decryptage;
import View.Options.ClickDroit;

public class ConfigMail extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension();
	private JTextField mail, jSmtp, jPort;
	private JCheckBox b1, b2;
	private JPasswordField mdp;
	private JFrame fenetre;
	private File fichier;
	private Base bdd;
	
	public ConfigMail(JFrame frame, Base base) throws FileNotFoundException{
		this.fenetre = frame;
		fenetre.setEnabled(false);
		this.bdd = base;
		this.fichier = new File("ConfigurationMail.conf");
		this.addWindowListener(new ActionFermer(this, fenetre));
		this.setLayout(null);
		this.setTitle("STID Gestion 2.0 (Mail)");
		screenSize.setSize(355, 240);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
	    this.setPreferredSize(screenSize);
	    
	    JLabel label = new JLabel("Adresse mail");
	    label.setBounds(100 - label.getPreferredSize().width,16, label.getPreferredSize().width, label.getPreferredSize().height);
	    this.add(label);
	    mail = new JTextField();
	    mail.setPreferredSize(new Dimension(200, 25));
	    mail.setBounds(105 ,14, mail.getPreferredSize().width, mail.getPreferredSize().height);
	    new ClickDroit(mail, true, true);
	    this.add(mail);
	    
	    JLabel label2 = new JLabel("Mot de Passe");
	    label2.setBounds(100 - label2.getPreferredSize().width,46, label2.getPreferredSize().width, label2.getPreferredSize().height);
	    this.add(label2);
	    mdp = new JPasswordField();
	    mdp.setPreferredSize(new Dimension(200, 25));
	    mdp.setBounds(105 ,44, mdp.getPreferredSize().width, mdp.getPreferredSize().height);
	    new ClickDroit(mdp, true, true);
	    this.add(mdp);
	    
	    JLabel label3 = new JLabel("SMTP");
	    label3.setBounds(100 - label3.getPreferredSize().width,76, label3.getPreferredSize().width, label3.getPreferredSize().height);
	    this.add(label3);
	    jSmtp = new JTextField();
	    jSmtp.setPreferredSize(new Dimension(200, 25));
	    jSmtp.setBounds(105,74, jSmtp.getPreferredSize().width, jSmtp.getPreferredSize().height);
	    new ClickDroit(jSmtp, true, true);
	    this.add(jSmtp);
	    
	    JLabel label4 = new JLabel("Port");
	    label4.setBounds(100 - label4.getPreferredSize().width,106, label4.getPreferredSize().width, label4.getPreferredSize().height);
	    this.add(label4);
	    jPort = new JTextField();
	    jPort.setPreferredSize(new Dimension(60, 25));
	    jPort.addKeyListener(new EcouteAction(jPort, false));
	    jPort.setBounds(105,104, jPort.getPreferredSize().width, jPort.getPreferredSize().height);
	    new ClickDroit(jPort, true, true);
	    this.add(jPort);
	    
	    b1 = new JCheckBox("SSL");
	    b2 = new JCheckBox("TLS");
	    
	    b1.setBounds(120, 135, b1.getPreferredSize().width, b1.getPreferredSize().height);
	    b2.setBounds(125 + b1.getPreferredSize().width, 135, b2.getPreferredSize().width, b2.getPreferredSize().height);
	    
	    b1.addActionListener(new ActionVerifButton());
	    b2.addActionListener(new ActionVerifButton());
	    
	    this.add(b1);
	    this.add(b2);
	    
	    this.setLocationRelativeTo(null);
	    JButton bouton = new JButton("Valider");
	    bouton.setBounds(130, 170, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    bouton.setMnemonic(KeyEvent.VK_ENTER);
	    this.getRootPane().setDefaultButton(bouton); 
	    this.add(bouton);
	    
	    bouton.addActionListener(new ActionValider(this));
	    
	    if(fichier.exists()){
			BufferedReader buff;
			String [] line = new String [6];
			try {
				buff = new BufferedReader(new FileReader("ConfigurationMail.conf"));
				for(int i = 0; i < line.length; i ++){
					line[i] = buff.readLine();
				}
				mail.setText(line[0]);
				mdp.setText(new Decryptage().decrypt(line[1]));
				jSmtp.setText(line[2]);
				jPort.setText(line[3]);
				if(line[4] != null && line[4].equals("true")){
					b1.setSelected(true);
				}
				if(line[5] != null && line[5].equals("true")){
					b2.setSelected(true);
				}
				buff.close();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage() + "\n Le programe va quitter", "ATTENTION",JOptionPane.ERROR_MESSAGE);
				bdd.close();
				System.exit(1);
			}
	    }
	    
	    this.pack();
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	private class ActionVerifButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox tmp = (JCheckBox)e.getSource();
			if(tmp == b1){
				if(b2.isSelected()){
					b2.setSelected(false);
				}
			}
			else if(tmp == b2){
				if(b1.isSelected()){
					b1.setSelected(false);
				}
			}
			
		}
		
	}
	private class ActionValider implements ActionListener {
		
		protected ConfigMail config;
		
		public ActionValider(ConfigMail cm){
			this.config = cm;
		}
		
		public void actionPerformed(ActionEvent e) {
			String ssl = "false", tls = "false";
			if(b1.isSelected()){
				ssl = "true";
			}
			if(tls.equals("true")){
				tls = "false";
			}
			String motdepasse = new String(mdp.getPassword());
			if(!mail.getText().isEmpty() && mail(mail.getText())){
				if(!motdepasse.isEmpty() && !motdepasse.equals("")){
					if(!jSmtp.getText().isEmpty() && smtp(jSmtp.getText())){
						if(!jPort.getText().isEmpty()){
							if(connexion(jSmtp.getText(), Integer.parseInt(jPort.getText()), mail.getText(), motdepasse, ssl, tls)){
								try {
									fichier.createNewFile();
									FileWriter f = new FileWriter(fichier);
									f.write(mail.getText() +"\n");
									f.write(new Cryptage().encrypt(motdepasse) + "\n");
									f.write(jSmtp.getText() + "\n");
									f.write(jPort.getText() + "\n");
									if(b1.isSelected()){
										f.write("true\n");
									}
									else{
										f.write("false\n");
									}
									if(b2.isSelected()){
										f.write("true\n");
									}
									else{
										f.write("false\n");
									}
									f.close();
									JOptionPane.showMessageDialog(null, "Connexion établit !", "Succès", JOptionPane.INFORMATION_MESSAGE);
									config.dispose();
									fenetre.setEnabled(true);
									fenetre.setVisible(true);
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Port vide", "Erreur",JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "SMPT incorrect \n(Attention pas de majuscule)", "Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Mot de passe vide", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Adresse mail vide ou incorrect", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean mail(String mail) {
		boolean resultat = false;
		if (mail.contains("@")) {
			String[] tmp = mail.split("@");
			if (tmp[1].contains(".")) {
				resultat = true;
			}
		}
		return resultat;
	}
	
	private boolean smtp(String smtp) {
		System.out.println(smtp);
		boolean resultat = false;
		if (smtp.contains("smtp") || smtp.contains("smtps")) {
				resultat = true;
		}
		return resultat;
	}

	
	private boolean connexion(String host, int port, String user, String pwd, String ssl, String tls){
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
