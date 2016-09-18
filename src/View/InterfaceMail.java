package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BDD.Base;
import CryptageDecryptage.Decryptage;
import Model.Donnees;
import Model.Mail;
import Thread.ThreadImpression;
import Thread.ThreadMail;


public class InterfaceMail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel client, mail, pieceJointe, objet, message;
	protected JTextField jObjet;
	protected JTextArea jMessage;
	protected JButton envoyer, annuler;
	protected Base base;
	protected String file, numfacture, mailClient;
	protected ThreadImpression th;
	protected String[] fichierText;

	public InterfaceMail(String numfacture, String filePDF, Base bdd, ThreadImpression thread) {
		this.setPreferredSize(new Dimension(550, 750));
		this.setLayout(null);
		this.base = bdd;
		this.file = filePDF;
		this.numfacture = numfacture;
		this.th = thread;
		this.setTitle("STID Gestion 2.0 (Envoi Mail)");
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());

	}

	public void init() throws IOException {
		if (new File("ConfigurationMail.conf").exists()) {
			BufferedReader buff;
			String[] line = new String[6];
			try {
				buff = new BufferedReader(new FileReader("ConfigurationMail.conf"));
				for (int i = 0; i < line.length; i++) {
					line[i] = buff.readLine();
				}
				buff.close();
				boolean test = new Mail().testConnexion(Integer.parseInt(line[3]), line[2], line[0],
						new Decryptage().decrypt(line[1]), line[4], line[5]);
				th.getFrame().setVisible(false);
				if (test) {
					fichierText = line;
					Donnees donnees = new Donnees(base);
					String[] res = donnees.fiche(
							"c.NomClient, c.mail, Sum(t.prefabrication + t.mntfour + t.coutmo + f.montanttaxe), d.symbole",
							"clients as c, factures as f, termes as t, commandes as co, devises as d",
							"f.NumFacture = " + numfacture
									+ " and t.numfacture = f.numfacture and t.numcommande = co.numcommande and co.numclient = c.numclient and d.pardefaut = 1");
					if (res[1] != null && !res[1].equals("null")) {
						client = new JLabel("Client : " + res[0]);
						this.mailClient = res[1];
						mail = new JLabel("E-Mail : " + res[1]);
						pieceJointe = new JLabel("Pièce Jointe : " + file);
						objet = new JLabel("Objet :");
						message = new JLabel("Message :");

						jObjet = new JTextField();

						jMessage = new JTextArea();
						jMessage.setLineWrap(true);
						jMessage.setWrapStyleWord(true);
						java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
						double somme = Double.parseDouble(res[2]);
						BufferedReader buff1;
						File text = new File("MailPresentation.txt");
						if (text.exists()) {
							String mes = "";
							String tmp = "";
							buff1 = new BufferedReader(new FileReader(text));
							String mesObjet = buff1.readLine();
							while ((tmp = buff1.readLine()) != null) {
								if (!tmp.equals("Message:")) {
									mes += tmp + "\n";
								}
							}
							mesObjet = mesObjet.replaceAll(Pattern.quote("${NumFacture}"), numfacture);
							mesObjet = mesObjet.replaceAll(Pattern.quote("Objet:"), "");
							mes = mes.replaceAll(Pattern.quote("${NumFacture}"), numfacture);
							mes = mes.replaceAll(Pattern.quote("${Montant}"), df.format(somme));
							mes = mes.replaceAll(Pattern.quote("${Devise}"), res[3]);

							jObjet.setText(mesObjet);
							jMessage.setText(mes);

							client.setBounds(12, 10, client.getPreferredSize().width, client.getPreferredSize().height);
							mail.setBounds(12, 40, mail.getPreferredSize().width, mail.getPreferredSize().height);
							pieceJointe.setBounds(12, 70, pieceJointe.getPreferredSize().width,
									pieceJointe.getPreferredSize().height);
							objet.setBounds(12, 100, objet.getPreferredSize().width, objet.getPreferredSize().height);
							message.setBounds(12, 130, message.getPreferredSize().width,
									message.getPreferredSize().height);

							jObjet.setBounds(17 + objet.getPreferredSize().width, 98, 457, 24);

							jMessage.setBounds(12, 155, 500, 500);
							jMessage.setBorder(BorderFactory.createEtchedBorder());

							envoyer = new JButton("Envoyer");
							annuler = new JButton("Annuler");

							envoyer.setBounds(170, 670, envoyer.getPreferredSize().width,
									envoyer.getPreferredSize().height);
							annuler.setBounds(175 + envoyer.getPreferredSize().width, 670,
									annuler.getPreferredSize().width, annuler.getPreferredSize().height);
							
							envoyer.addActionListener(new ActionEnvoyerMail(this));
							annuler.addActionListener(new ActionFermer(this));

							this.add(client);
							this.add(mail);
							this.add(pieceJointe);
							this.add(objet);
							this.add(message);
							this.add(envoyer);
							this.add(annuler);
							this.addWindowListener(new ActionFermer(this));
							this.add(jObjet);
							this.add(jMessage);
							this.pack();
							this.setVisible(true);
							this.setLocationRelativeTo(null);
						}
						else{
							new File(file).delete();
							JOptionPane.showMessageDialog(null, "Aucun Fichier de présentation de Mail.\nVeuillez rèinstaller le programme.", "ERREUR", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(null,
								"Le client " + res[0]
										+ " n'a pas d'adresse mail.\nVeuillez en mettre à jour la ficher client",
								"Erreur", JOptionPane.ERROR_MESSAGE);
						new File(file).delete();
					}
				}
				else{
					th.getFrame().setVisible(false);
					JOptionPane.showMessageDialog(null, "Erreur de connexion.\nVeuillez aller dans paramètres configurer une adresse mail.", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				th.getFrame().setVisible(false);
				new File(file).delete();
				JOptionPane.showMessageDialog(null, e.getMessage() + "\n Le programe va quitter", "ATTENTION",
						JOptionPane.ERROR_MESSAGE);
				base.close();
				System.exit(1);
			}
		} else {
			th.getFrame().setVisible(false);
			new File(file).delete();
			JOptionPane.showMessageDialog(null, "Aucune Configuration d'adresse mail détecté.\nVeuillez aller dans paramètres configurer une adresse mail.", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class ActionFermer implements ActionListener, WindowListener{
		
		protected InterfaceMail intermail;
		
		public ActionFermer(InterfaceMail im){
			this.intermail = im;
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			fermer();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			fermer();
		}
		
		private void fermer(){
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment annuler l'envoi du mail ?",
                    "Veuillez confirmer votre choix",
                    JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				intermail.dispose();
				th.getFenetre().setEnabled(true);
				th.getFenetre().setVisible(true);
				new File(file).delete();
			}
		}
		
	}
	
	private class ActionEnvoyerMail implements ActionListener{
		
	protected InterfaceMail intermail;
		
		public ActionEnvoyerMail(InterfaceMail im){
			this.intermail = im;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			intermail.setVisible(false);
			intermail.dispose();
			ThreadMail thm = new ThreadMail(fichierText, file, mailClient, jObjet.getText(), jMessage.getText(), th.getFenetre());
			thm.start();
			
		}
		
	}

}
