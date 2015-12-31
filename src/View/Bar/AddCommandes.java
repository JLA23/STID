package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Commandes.NewCommande;
import View.Commandes.SearchCommande;

public class AddCommandes{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private Base base;
	private JFrame fenetre;
	
	public AddCommandes(Base bdd, String typeCompte, JFrame frame){
		this.base = bdd;
		this.fenetre = frame;
		menu = new JMenu("Commandes");
		menu.getAccessibleContext().setAccessibleDescription("Devis");
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/pannier-commande.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		
		if(typeCompte.equals("Admin")){
			//Nouvelle Commande
			menuItem = new JMenuItem("Nouvelle Commande");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new NewCommande(base, fenetre);
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Creation d'une nouvelle commande");
			menu.add(menuItem);
		
			//Modifier Commande
			menuItem = new JMenuItem("Modifier Commande");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SearchCommande(base, fenetre, "Modif");
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Modifie commande");
			menu.add(menuItem);
			
			//Supprime Commande
			menuItem = new JMenuItem("Supprimer Commande");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						//new ModifDevis();
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Supprime une commande");
			menu.add(menuItem);
		}
		
		
		//Recherche une commande
		menuItem = new JMenuItem("Recherche Commande");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Recherche une commande");
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

