package Controller;

import java.util.List;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import View.Parameters.Categorie;
import View.Pointage.HeureSpe;
import View.SearchList.SearchList;

public class RowListener implements RowSorterListener {

	private SearchList list;
	private HeureSpe spe;
	private Categorie cat;

	public RowListener(SearchList search) {
		this.list = search;
	}
	public RowListener(HeureSpe search) {
		this.spe = search;
	}
	public RowListener(Categorie search) {
		this.cat = search;
	}
	
	
	@Override
	public void sorterChanged(RowSorterEvent arg0) {
		if (arg0.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
			@SuppressWarnings("unchecked")
			List<SortKey> keys = arg0.getSource().getSortKeys();
			if(spe == null && list != null && cat == null){
				list.getModel().fireTableDataChanged();
				list.getSearch().removeKeyListener(list.getActionSearch());
				list.setActionSearch(new Search(list, keys.get(0).getColumn()));;
				list.getSearch().addKeyListener(list.getActionSearch());
			}
			else if(spe != null && list == null){
				spe.getSaisie().fireTableDataChanged();
				spe.getSearch().removeKeyListener(spe.getActionSearch());
				spe.getSearch().addKeyListener(new Search(spe, keys.get(0).getColumn()));
				spe.getSearch().addKeyListener(spe.getActionSearch());
			}
			else if(spe == null && list == null && cat != null){
				cat.getModel().fireTableDataChanged();
				cat.getSearch().removeKeyListener(spe.getActionSearch());
				cat.getSearch().addKeyListener(new Search(spe, keys.get(0).getColumn()));
				cat.getSearch().addKeyListener(spe.getActionSearch());
			}
		}
	}
	

	
}
