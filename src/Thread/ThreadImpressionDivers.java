package Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import Model.Imprimer;

public class ThreadImpressionDivers extends Thread {
	
	protected JWindow frame;
	protected Object objet;
	protected String typeimpr, typeClass;
	protected JFrame fenetre;
	
	public ThreadImpressionDivers(Object objet, String typeClasse, String typeImpression, JFrame fenetre){
		this.objet = objet;
		this.typeClass = typeClasse;
		this.typeimpr = typeImpression;
		this.fenetre = fenetre;
	}
	
	public void run(){
		frame = new JWindow();
		ImageIcon loading = new ImageIcon("lib/images/ajax-loader.gif");
		frame.add(new JLabel("", loading, JLabel.CENTER));
		frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
        if(typeimpr.equals("Imprimer") || typeimpr.equals("Apercu")){
    		frame.setVisible(true);
    		frame.setAlwaysOnTop(true);
        	new Imprimer(objet, this, typeClass, typeimpr);
        }
        else if(typeimpr.equals("PDF")){
        	new Imprimer(objet, this, typeClass, typeimpr);
        }
       while(frame.isVisible()){}
       fenetre.setEnabled(true);
       fenetre.setVisible(true);
	}

	public JWindow getFrame() {
		return frame;
	}

	public void setFrame(JWindow frame) {
		this.frame = frame;
	}
	
	
	
	
}