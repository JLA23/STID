package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;

public class AddParametres{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddParametres(Base bdd){
		menu = new JMenu("Paramètres");
		menu.getAccessibleContext().setAccessibleDescription("Clients");		
		
		
		//Categorie de personne
		menuItem = new JMenuItem("Catégorie de personne");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Définition des catégories de personnes");
		menu.add(menuItem);
		
		//Nouveau Salarié
		menuItem = new JMenuItem("Nouveau Salarié");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("ajouter un salarié");
		menu.add(menuItem);
		
		//Modifier salarié
		menuItem = new JMenuItem("Modifier un salarié");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier un salarié");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Paritées
		menuItem = new JMenuItem("Paritées");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Définition des devises");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		// Paramètres de l'application
		menuItem = new JMenuItem("Paramètres de l'application");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Paramètres de l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

