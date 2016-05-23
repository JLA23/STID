package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.SearchFactures.SearchFacture;
import View.SearchTerme.SearchTerme;

public class AddFacturation{
	
	private JMenu menu;
	private JFrame fenetre;
	private JMenuItem menuItem;
	private Base bdd;
	
	public AddFacturation(Base base, String typeCompte, JFrame frame){
		menu = new JMenu("Facturation");
		menu.getAccessibleContext().setAccessibleDescription("Facturation");
		this.bdd = base;
		this.fenetre = frame;		
		
		//Creer une facture
		menuItem = new JMenuItem("Créer une facture");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new SearchTerme(bdd, fenetre, "NewFacture");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Créer une facture");
		menu.add(menuItem);
		
		//Modifier Facture ou avoir ou crèer avoir
		menuItem = new JMenuItem("Modifier une facture / créer un avoir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchFacture(bdd, fenetre, "Modif");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou créer un avoir");
		menu.add(menuItem);
		
		//Supprimer Facture ou avoir 
		menuItem = new JMenuItem("Supprimer une facture / avoir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchFacture(bdd, fenetre, "Suppr");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou créer un avoir");
		menu.add(menuItem);
		
		//Afficher facture ou avoir
		menuItem = new JMenuItem("Afficher facture / avoir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou créer un avoir");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		
		//Listing TVA
		menuItem = new JMenuItem("Listing TVA");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Listing TVA");
		menu.add(menuItem);
		
		// Journal des ventes
		menuItem = new JMenuItem("Journal des ventes");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Calcul du CA HT sur un ou plusieur mois");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

