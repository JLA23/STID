package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ActionFermer implements ActionListener {
		
	private JFrame fenetre;
	private JDialog dialog;
	
	public ActionFermer(JFrame frame){
		this.fenetre = frame;
	}
	
	public ActionFermer(JDialog dia){
		this.dialog = dia;
	}
	public void actionPerformed(ActionEvent arg0) {
		if(fenetre != null && dialog == null){
			fenetre.dispose();
		}
		else if(fenetre == null && dialog != null){
			dialog.dispose();
		}
	}
}
