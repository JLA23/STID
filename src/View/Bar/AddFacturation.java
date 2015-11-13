package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;

public class AddFacturation{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddFacturation(Base bdd, String typeCompte){
		menu = new JMenu("Facturation");
		menu.getAccessibleContext().setAccessibleDescription("Facturation");		
		
		
		//Creation de Bdl
		menuItem = new JMenuItem("Création de Bdl");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un borderau de livraison");
		menu.add(menuItem);
		
		//Creer une facture
		menuItem = new JMenuItem("Créer une facture");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Créer une facture");
		menu.add(menuItem);
		
		//Liste des Clients
		menuItem = new JMenuItem("Modifier une facture / créer un avoir");
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

