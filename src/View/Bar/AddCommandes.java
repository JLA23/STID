package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AddCommandes{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddCommandes(){
		menu = new JMenu("Commandes");
		menu.getAccessibleContext().setAccessibleDescription("Devis");		
		
		
		//Nouvelle Commande
		menuItem = new JMenuItem("Nouvelle Commande");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'une nouvelle commande");
		menu.add(menuItem);
		
		//Modifier Commande
		menuItem = new JMenuItem("Modifier Commande");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifie commande");
		menu.add(menuItem);
		
		
		
		//Recherche sur CDe commande
		menuItem = new JMenuItem("Recherche sur le CDe Commande Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Recherche");
		menu.add(menuItem);
		
		
		
		menu.addSeparator();
		
		
		//Temps par commande
		menuItem = new JMenuItem("Temps / Commandes");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Temp");
		menu.add(menuItem);
		
		// Cahier de Commandes
		menuItem = new JMenuItem("Cahier de Commandes");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Cahier");
		menu.add(menuItem);
		
		// Etat COREM
		menuItem = new JMenuItem("Etat COREM");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Listing des affaires COREM");
		menu.add(menuItem);
						
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

