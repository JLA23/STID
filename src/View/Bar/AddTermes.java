package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import BDD.Base;
import View.SearchCommandes.SearchCommande;
import View.SearchTerme.SearchTerme;;

public class AddTermes{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private JFrame fenetre;
	private Base bdd;
	
	public AddTermes(Base base, String typeCompte, JFrame frame){
		menu = new JMenu("Termes");
		menu.getAccessibleContext().setAccessibleDescription("Termes");		
		this.bdd = base;
		this.fenetre = frame;
		
		
		//Nouveau Termes
		menuItem = new JMenuItem("Nouveau Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new SearchCommande(bdd, fenetre, "NewTerme");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un terme");
		menu.add(menuItem);
		
		//Modifier Termes
		menuItem = new JMenuItem("Modifier Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new SearchTerme(bdd, fenetre, "Modif");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifie terme");
		menu.add(menuItem);
		
		//Supprimer Termes
		menuItem = new JMenuItem("Supprimer Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new SearchTerme(bdd, fenetre, "Suppr");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Supprime terme");
		menu.add(menuItem);
		
		//Rechercher Termes
		menuItem = new JMenuItem("Rechercher Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new SearchTerme(bdd, fenetre, "Look");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Afficher terme");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

