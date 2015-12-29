package Controller.Commandes;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

import View.Commandes.Commandes;
import View.Options.ClickDroit;

public class ExecuteClick {
	
	private boolean click;
	private Commandes devis;
	
	public ExecuteClick(Commandes d){
		click = false;
		this.devis = d;
	}
	
	public void execute(MouseEvent m, FocusEvent f, JFormattedTextField jtext, int met){
		if(m != null && f == null){
			click = true;
			new ClickDroit(jtext, true, true);
		}
		else if(m == null && f != null){
			if(click == false){
				new TestContenu(devis, jtext, met);
			}
			else{
				click = false;
			}
		}
		
	}

}
