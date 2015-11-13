package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;

public class AddClients{
	
	private JMenu menu;
	private JMenuItem menuItem;
	
	public AddClients(Base bdd, String typeCompte){
		menu = new JMenu("Clients");
		menu.getAccessibleContext().setAccessibleDescription("Clients");
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/Client.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		
		
		//Nouveau client
		menuItem = new JMenuItem("Nouveau Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un nouveau client");
		menu.add(menuItem);
		
		//Modifier client
		menuItem = new JMenuItem("Modifier Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifie client");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Liste des Clients
		menuItem = new JMenuItem("Liste des Clients");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Liste des clients");
		menu.add(menuItem);
		
		
		//Retard de paiement
		menuItem = new JMenuItem("Retard de paiement");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Retard");
		menu.add(menuItem);
		
		// Factures à venir
		menuItem = new JMenuItem("Factures à venir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Factures à venir");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		// %CA / Client
		menuItem = new JMenuItem("% CA / Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Resultat");
		menu.add(menuItem);
						
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

