package Controller.Pointage;

import javax.swing.table.AbstractTableModel;

public class TableauSaisie extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Object[][] donnees;
 
    private String[] entetes = {"<html><body><b>Libellé</b></body></html>", "<html><body><b>Recup</b></body></html>", "<html><body><b>Perdues</b></body></html>", "<html><body><b>Solde</b></body></html>"};
 
    public TableauSaisie(Object [][] donnees) {
        super();
 
        this.donnees = donnees;
    }
 
    public int getRowCount() {
        return donnees.length;
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[rowIndex][columnIndex];
    }
    
    public void actualiser(Object [][] data){
    	this.donnees = data;
    	this.fireTableDataChanged();	
    }
    
    public void tableModifier(){
    	this.fireTableDataChanged();
    }
}