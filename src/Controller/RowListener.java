package Controller;

import java.util.List;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import View.SearchList.SearchList;

public class RowListener implements RowSorterListener {

	private SearchList list;

	public RowListener(SearchList search) {
		this.list = search;
	}
	
	@Override
	public void sorterChanged(RowSorterEvent arg0) {
		if (arg0.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
			@SuppressWarnings("unchecked")
			List<SortKey> keys = arg0.getSource().getSortKeys();
			list.getModel().fireTableDataChanged();
			list.getSearch().addKeyListener(new Search(list, keys.get(0).getColumn()));
		}
	}
}
