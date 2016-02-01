package View.SearchList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import BDD.Base;
import Model.Donnees;
import fr.julien.autocomplete.view.AutoComplete;

public class SearchList extends JDialog{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = new Dimension(700, 480);
	protected Base bdd;
	protected AutoComplete auto;
	protected Object[][] data;
	protected JTable layerTable;
	protected JFrame frame;
	protected Donnees donnees;
	protected JPanel layerPanel;
	protected GridBagConstraints c;
	protected JFormattedTextField search;
	
	public SearchList(Base bdd, JFrame frame){
		super(frame, null, true);
		this.bdd = bdd;
		this.frame = frame;
		donnees = new Donnees(bdd);
		this.setPreferredSize(screenSize);
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
        layerPanel = new JPanel();
        c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.weightx = 1; c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        NumberFormat num =  NumberFormat.getIntegerInstance();
        search = new JFormattedTextField(num);
        search.setPreferredSize(new Dimension(110, 27));     
	}
	
	public JTable getLayerTable() {
		return layerTable;
	}

	public void setLayerTable(JTable layerTable) {
		this.layerTable = layerTable;
	}
	
	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public Base getBdd() {
		return bdd;
	}

	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}

	public AutoComplete getAuto() {
		return auto;
	}

	public void setAuto(AutoComplete auto) {
		this.auto = auto;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	protected void centrerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<table.getColumnCount() ; i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	   }	
}
