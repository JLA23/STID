package Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import BDD.Base;
import Model.Imprimer;

public class ThreadImpression extends Thread{

	protected JWindow frame;
	protected JFrame fenetre, interfaceMail;
	protected String facture, mode;
	protected Double valeur;
	protected Base bdd;
	protected boolean fini = false;
	
	public ThreadImpression(String numFacture, Base base, Double valeurtext, String mode, JFrame fenetre){
		this.facture = numFacture;
		this.valeur = valeurtext;
		this.mode = mode;
		this.fenetre = fenetre;
		this.bdd = base;
	}
	
	public void run(){
		frame = new JWindow();
		ImageIcon loading = new ImageIcon("lib/images/ajax-loader.gif");
		frame.add(new JLabel("", loading, JLabel.CENTER));
		frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
       if(mode.equals("Imprimer") || mode.equals("Apercu") || mode.equals("Mail")){
    		frame.setVisible(true);
    		frame.setAlwaysOnTop(true);
        	new Imprimer(facture, bdd, valeur, this, mode);
        }
        else if(mode.equals("PDF")){
        	new Imprimer(facture, bdd, valeur, this, mode);
        }
        while(frame.isVisible()){}
        if(interfaceMail != null){
        	while(interfaceMail.isVisible()){}
        }
        	fenetre.setEnabled(true);
        	fenetre.setVisible(true);
	}

	public JWindow getFrame() {
		return frame;
	}

	public void setFrame(JWindow frame) {
		this.frame = frame;
	}

	public JFrame getInterfaceMail() {
		return interfaceMail;
	}

	public void setInterfaceMail(JFrame interfaceMail) {
		this.interfaceMail = interfaceMail;
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}
	
	
	
}
