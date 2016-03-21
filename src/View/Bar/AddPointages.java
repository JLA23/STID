package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Pointage.SaisiePointage;

public class AddPointages{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddPointages(Base bdd, String typeCompte){
		menu = new JMenu("Pointages");
		menu.getAccessibleContext().setAccessibleDescription("Pointages");		
		
		
		//Saisir des heures spéciales
		menuItem = new JMenuItem("Saisir des heures spéciales");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Saisir des heures spéciales");
		menu.add(menuItem);
		
		//Etat Hrs Spéciales
		menuItem = new JMenuItem("Etat Hrs Spéciales");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Etat Hrs Spéciales");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Saisir des pointages
		menuItem = new JMenuItem("Saisir des pointages");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaisiePointage();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Saisir des pointages");
		menu.add(menuItem);
		
		
		//Etat pointage
		menuItem = new JMenuItem("Etat pointage");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Etat pointage");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Heures productives
		menuItem = new JMenuItem("Heures productives");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Heures productives");
		menu.add(menuItem);					
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

