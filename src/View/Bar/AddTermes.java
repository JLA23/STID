package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;

public class AddTermes{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddTermes(Base bdd, String typeCompte){
		menu = new JMenu("Termes");
		menu.getAccessibleContext().setAccessibleDescription("Termes");		
		
		
		//Nouveau Termes
		menuItem = new JMenuItem("Nouveau Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un terme");
		menu.add(menuItem);
		
		//Modifier Termes
		menuItem = new JMenuItem("Modifier Terme");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifie terme");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

