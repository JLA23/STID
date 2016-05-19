package Controller;

import java.util.List;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import View.Pointage.HeureSpe;
import View.SearchList.SearchList;

public class RowListener implements RowSorterListener {

	private SearchList list;
	private HeureSpe spe;

	public RowListener(SearchList search) {
		this.list = search;
	}
	public RowListener(HeureSpe search) {
		this.spe = search;
	}
	
	@Override
	public void sorterChanged(RowSorterEvent arg0) {
		if (arg0.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
			@SuppressWarnings("unchecked")
			List<SortKey> keys = arg0.getSource().getSortKeys();
			if(spe == null && list != null){
				list.getModel().fireTableDataChanged();
				list.getSearch().addKeyListener(new Search(list, keys.get(0).getColumn()));
			}
			else if(spe != null && list == null){
				spe.getSaisie().fireTableDataChanged();
				spe.getSearch().addKeyListener(new Search(spe, keys.get(0).getColumn()));
				System.out.println(keys.get(0).getColumn());
			}
		}
	}
}
