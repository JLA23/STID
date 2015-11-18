package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;

public class AddPropos{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddPropos(Base bdd){
		menu = new JMenu("A propos");
		menu.getAccessibleContext().setAccessibleDescription("Description");		
		
		
		//Crèdits
		menuItem = new JMenuItem("Crèdits");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Information sur le logiciel");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Aide
		menuItem = new JMenuItem("Aide");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Aide d'utilisation");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

