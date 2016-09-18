package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Donnees;

public class VerifNum implements FocusListener{
	
	protected JTextField field;
	protected Donnees donnees;
	protected String classe, com;
	
	public VerifNum(JTextField text, Donnees donnees, String classe){
		this.field = text;
		this.donnees = donnees;
		this.classe = classe;
	}
	public VerifNum(JTextField text, String text2, Donnees donnees, String classe){
		this.field = text;
		this.com = text2;
		this.donnees = donnees;
		this.classe = classe;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(classe.equals("devis")){
			if(donnees.exist(classe, "numdevis" , "numdevis = " + field.getText())){
				JOptionPane.showMessageDialog(null, "Le numéro existe déjà", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				field.requestFocus();
			}
		}
		else if(classe.equals("commandes")){
			if(donnees.exist(classe, "numcommande" , "numcommande = " + field.getText())){
				JOptionPane.showMessageDialog(null, "Le numéro existe déjà", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				field.requestFocus();
			}
		}
		else if(classe.equals("termes")){
			if(donnees.exist(classe, "numcommande, numtermes" , "numcommande = " + com + " and numindice = " + field.getText())){
				JOptionPane.showMessageDialog(null, "Le numéro existe déjà", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				field.requestFocus();
			}
		}
		else if(classe.equals("factures")){
			if(donnees.exist(classe, "numfacture" , "numfacture = " + field.getText())){
				JOptionPane.showMessageDialog(null, "Le numéro existe déjà", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				field.requestFocus();
			}
		}
		else if(classe.equals("clients")){
			if(donnees.exist(classe, "numclient" , "numclient = " + field.getText())){
				JOptionPane.showMessageDialog(null, "Le numéro existe déjà", "ATTENTION",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				field.requestFocus();
			}
		}

		
	}

}
