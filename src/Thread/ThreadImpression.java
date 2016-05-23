package Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

import BDD.Base;
import Model.Apercu;
import Model.GeneratePDF;
import Model.Imprimer;

public class ThreadImpression extends Thread{

	protected JWindow frame;
	protected JFrame fenetre;
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
		ImageIcon loading = new ImageIcon("ajax-loader.gif");
		frame.add(new JLabel("", loading, JLabel.CENTER));
		frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
        if(mode.equals("Imprimer")){
    		frame.setVisible(true);
    		frame.setAlwaysOnTop(true);
        	new Imprimer(facture, bdd, valeur, this);
        }
        else if(mode.equals("PDF")){
        	new GeneratePDF(facture, bdd, valeur, this);
        }
        else if(mode.equals("Apercu")){
    		frame.setVisible(true);
    		frame.setAlwaysOnTop(true);
        	new Apercu(facture, bdd, valeur, this);
        }
        while(frame.isVisible()){}
        fenetre.setEnabled(true);
	}

	public JWindow getFrame() {
		return frame;
	}

	public void setFrame(JWindow frame) {
		this.frame = frame;
	}
	
	
	
	
}
