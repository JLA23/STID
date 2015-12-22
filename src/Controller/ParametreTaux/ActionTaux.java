package Controller.ParametreTaux;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import Controller.Devis.TestContenu;

public class ActionTaux implements ItemListener{
	
	protected JComboBox<String> b;
	protected JFormattedTextField jtext1;
	protected LinkedHashMap<String, String[]> valeurs;
	
	public ActionTaux(JComboBox<String> box, JFormattedTextField jtext, LinkedHashMap<String, String[]> valeur){
		this.b = box;
		this.jtext1 = jtext;
		this.valeurs = valeur;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		jtext1.setText(valeurs.get(b.getSelectedItem().toString())[1].replaceAll("\\.", ","));
		new TestContenu(null, jtext1, 0);
	}

}
