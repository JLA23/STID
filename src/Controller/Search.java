package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import View.SearchList.SearchList;

public class Search implements KeyListener {

	private int col;
	private SearchList search;

	public Search(SearchList list, int colonne) {
		this.col = colonne;
		this.search = list;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//boolean trouve = false;
		int j = 0;
		for (; j < search.getLayerTable().getRowCount(); j++) {
			if (((String) search.getLayerTable().getValueAt(j, col)).length() >= search.getSearch().getText().length()) {
				if (search.getSearch().getText().equals(((String) search.getLayerTable().getValueAt(j, col)).substring(0, search.getSearch().getText().length()))){
					search.getLayerTable().setRowSelectionInterval(j, j);
					break;
				}
			}
		}
		/*for (; i < search.getData().length; i++) {
			if (((String) search.getData()[i][col]).length() >= search.getSearch().getText().length()) {
				if (search.getSearch().getText().equals(
						((String) search.getData()[i][col]).substring(0, search.getSearch().getText().length()))) {
					trouve = true;
					break;
				}
			}
		}
		if (trouve) {
	
			if (acs == -1 || acs == 0) {
				search.getLayerTable().setRowSelectionInterval(j, j);
			} else if (acs == 1) {
				System.out.println(search.getLayerTable().getRowCount() + " " + j);
				search.getLayerTable().setRowSelectionInterval(search.getLayerTable().getRowCount() - j - 1, search.getLayerTable().getRowCount() - j - 1);
			}
		}*/

	}
		
	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
