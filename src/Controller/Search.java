package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class Search implements KeyListener{
	
	private JFormattedTextField jtextfield;
	private Object[][] data;
	private JTable table;
	
	public Search(JFormattedTextField jtext, Object[][] datas, JTable layerTable){
		this.jtextfield = jtext;
		this.data = datas;
		this.table = layerTable;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int i = 0;
		boolean trouve = false;
		for(; i < data.length; i++){
			if(((String)data[i][0]).length() >= jtextfield.getText().length()){
				if(jtextfield.getText().equals(((String)data[i][0]).substring(0, jtextfield.getText().length()))){
					trouve = true;
					break;
				}
			}
		}
		if(trouve){
			for(int j = 0; j < table.getRowCount(); j ++){
				if(((String)table.getModel().getValueAt(j, 0)).equals((String)data[i][0])){
					table.setRowSelectionInterval(j, j);
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
