package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Parameters.Categorie;
import View.Parameters.ParametreTaux;

public class AddParametres{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private Base base;
	private JFrame fenetre;
	
	public AddParametres(Base bdd, String typeCompte, JFrame frame){
		base = bdd;
		fenetre = frame;
		menu = new JMenu("Param�tres");
		menu.getAccessibleContext().setAccessibleDescription("Clients");		
		
		
		//Categorie de personne
		menuItem = new JMenuItem("Cat�gorie de personne");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new Categorie(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("D�finition des cat�gories de personnes");
		menu.add(menuItem);
		
		//Nouveau Salari�
		menuItem = new JMenuItem("Nouveau Salari�");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("ajouter un salari�");
		menu.add(menuItem);
		
		//Modifier salari�
		menuItem = new JMenuItem("Modifier un salari�");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier un salari�");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Parit�es
		menuItem = new JMenuItem("Parit�es");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("D�finition des devises");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		// Param�tres de l'application
		menuItem = new JMenuItem("Param�tres Taux");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ParametreTaux(base);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Param�tres de l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

