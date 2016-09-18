package Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

import Model.Mail;

public class ThreadMail extends Thread{
	
	protected JWindow frame;
	protected String [] fichierText;
	protected String file, mailClient, objet, message;
	protected JFrame fenetre;
	
	public ThreadMail(String [] fichierText, String file, String mailClient, String objet, String message, JFrame fenetre){
		this.fichierText = fichierText;
		this.file = file;
		this.mailClient = mailClient;
		this.objet = objet;
		this.message = message;
		this.fenetre = fenetre;
	}
	
	public void run(){
		frame = new JWindow();
		ImageIcon loading = new ImageIcon("lib/images/ajax-loader.gif");
		frame.add(new JLabel("", loading, JLabel.CENTER));
		frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
        Mail.envoyerMail(fichierText, file, mailClient, objet, message, fenetre, this);
        while(frame.isVisible()){}
	}

	public JWindow getFrame() {
		return frame;
	}

	public void setFrame(JWindow frame) {
		this.frame = frame;
	}	
	

}
