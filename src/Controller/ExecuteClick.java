package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

import View.Options.ClickDroit;

public class ExecuteClick {
	
	private boolean click;
	private Object classe;
	private String type, fonction;
	
	public ExecuteClick(Object d, String typeClasse, String fonction){
		this.click = false;
		this.classe = d;
		this.type = typeClasse;
		this.fonction = fonction;
	}
	
	public void execute(MouseEvent m, FocusEvent f, JFormattedTextField jtext, int met){
		if(m != null && f == null){
			click = true;
			if(fonction.equals("Suppr") || fonction.equals("Look")){
				new ClickDroit(jtext, true, false);
			}
			else{
				new ClickDroit(jtext, true, true);
			}
		}
		else if(m == null && f != null){
			if(click == false){
				new TestContenu(classe, jtext, met, type);
			}
			else{
				click = false;
			}
		}
		
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

}
