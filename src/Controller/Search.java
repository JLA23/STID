package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.Pointage.HeureSpe;
import View.SearchList.SearchList;

public class Search implements KeyListener {

	private int col;
	private SearchList search;
	private HeureSpe spe;

	public Search(SearchList list, int colonne) {
		this.col = colonne;
		this.search = list;

	}

	public Search(HeureSpe list, int colonne) {
		this.col = colonne;
		this.spe = list;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// boolean trouve = false;
		if (spe == null && search != null) {
			int j = 0;
			for (; j < search.getLayerTable().getRowCount(); j++) {
				if (((String) search.getLayerTable().getValueAt(j, col)).length() >= search.getSearch().getText()
						.length()) {
					if (search.getSearch().getText().equals(((String) search.getLayerTable().getValueAt(j, col))
							.substring(0, search.getSearch().getText().length()))) {
						search.getLayerTable().setRowSelectionInterval(j, j);
						break;
					}
				}
			}
		}
		else if (spe != null && search == null){
			int j = 0;
			for (; j < spe.getLayerTable().getRowCount(); j++) {
				if (((String) spe.getLayerTable().getValueAt(j, col)).length() >= spe.getSearch().getText()
						.length()) {
					if (spe.getSearch().getText().equals(((String) spe.getLayerTable().getValueAt(j, col))
							.substring(0, spe.getSearch().getText().length()))) {
						spe.getLayerTable().setRowSelectionInterval(j, j);
						break;
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
