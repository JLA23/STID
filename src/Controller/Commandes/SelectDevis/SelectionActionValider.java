package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.FocusJText;
import Controller.TestContenu;
import View.Commandes.Commandes;
import View.Commandes.SelectDevis;

public class SelectionActionValider implements ActionListener {

	protected SelectDevis select;
	protected Commandes commandes;

	public SelectionActionValider(SelectDevis sd, Commandes com) {
		this.select = sd;
		this.commandes = com;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		action();
	}
	public void action(){
		if (select.getModel().getRowCount() > 0) {
			String[] listnum = new String[select.getModel().getRowCount()];
			commandes.getjLibelle().setText((String)select.getModel().getValueAt(0, 3));
			for (int i = 0; i < listnum.length; i++) {
				listnum[i] = (String) (select.getModel().getValueAt(i, 0));
			}
			double MntFour = select.getDonnees().somme("MntFour", "devis", listnum);
			double coutmo = select.getDonnees().somme("CoutMo", "devis", listnum);
			double prefabrication = select.getDonnees().somme("prefabrication", "devis", listnum);
			double hrsite = select.getDonnees().somme("heuresite", "devis", listnum);
			double hratelier = select.getDonnees().somme("heureatelier", "devis", listnum);
			double matiereprevu = select.getDonnees().somme("Matiereprevu", "devis", listnum);
			double matierecommande = select.getDonnees().somme("MatiereCommande", "devis", listnum);
			commandes.getjFournitures().setText(new Double(MntFour).toString().replaceAll("\\.", ","));
			commandes.getjCout().setText(new Double(coutmo).toString().replaceAll("\\.", ","));
			commandes.getjPrefabrication().setText(new Double(prefabrication).toString().replaceAll("\\.", ","));
			commandes.getjHeureSite().setText(new Double(hrsite).toString().replaceAll("\\.", ","));
			commandes.getjHeureAtelier().setText(new Double(hratelier).toString().replaceAll("\\.", ","));
			commandes.getjPrevu().setText(new Double(matiereprevu).toString().replaceAll("\\.", ","));
			commandes.getjCommande().setText(new Double(matierecommande).toString().replaceAll("\\.", ","));
			new TestContenu(commandes, commandes.getjFournitures(), 1, "Commandes");
			new TestContenu(commandes, commandes.getjCout(), 1, "Commandes");
			new TestContenu(commandes, commandes.getjPrefabrication(), 1, "Commandes");
			new TestContenu(commandes, commandes.getjHeureSite(), 2, "Commandes");
			new TestContenu(commandes, commandes.getjHeureAtelier(), 2, "Commandes");
			new TestContenu(commandes, commandes.getjPrevu(), 3, "Commandes");
			new TestContenu(commandes, commandes.getjCommande(), 3, "Commandes");
			numClientIdentique();
			commandes.setListDevis(new ArrayList<Object[]>());
			
			for (int i = 0; i < select.getModel().getRowCount(); i++) {
				Object[] val = new Object[select.getModel().getColumnCount()];
				for (int j = 0; j < select.getModel().getColumnCount(); j++) {
					val[j] = select.getModel().getValueAt(i, j);
				}
				commandes.getListDevis().add(val);
			}
			select.dispose();
			commandes.setEnabled(true);
			commandes.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Aucun Devis n'a été sélectioné", "ATTENTION",
					JOptionPane.WARNING_MESSAGE);
		}

	}
	
	private void numClientIdentique(){
		int ligne = select.getModel().getRowCount();
		System.out.println(ligne);
		String numclient = (String)select.getModel().getValueAt(0, 1);
		boolean valide = true;
		if(ligne > 1){
			for(int  i = 1; i < ligne; i++){
				if(!numclient.equals((String)select.getModel().getValueAt(i, 1))){
					valide = false;
					break;
				}
			}
		}
		if (valide){
			commandes.getNumClient().getZoneTexte().setText(numclient);
			new FocusJText(commandes, "Commandes").name();
		}
	}

}
