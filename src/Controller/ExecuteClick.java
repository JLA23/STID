package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

import View.Options.ClickDroit;

public class ExecuteClick {
	
	private boolean click;
	private Object classe;
	private String type;
	
	public ExecuteClick(Object d, String typeClasse){
		this.click = false;
		this.classe = d;
		this.type = typeClasse;
	}
	
	public void execute(MouseEvent m, FocusEvent f, JFormattedTextField jtext, int met){
		if(m != null && f == null){
			click = true;
			new ClickDroit(jtext, true, true);
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

}
