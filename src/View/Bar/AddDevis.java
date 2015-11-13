package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Identification;
import View.Devis.NewDevis;

public class AddDevis{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private JFrame frame;
	private Base bdd;
	
	public AddDevis(JFrame f, Base base, String typeCompte){
		this.bdd = base;
		this.frame = f;
		menu = new JMenu("Devis");
		menu.getAccessibleContext().setAccessibleDescription("Devis");
		ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/bouton-devis.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		
		if(typeCompte.equals("Admin")){
			//Nouveau Devis
			menuItem = new JMenuItem("Nouveau Devis");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new NewDevis(bdd);
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un nouveau devis");
			menu.add(menuItem);
		
			//Modifier Devis
			menuItem = new JMenuItem("Modifier Devis");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Modifie un devis existant");
			menu.add(menuItem);
		
		
		
			//Supprimer Devis
			menuItem = new JMenuItem("Supprimer Devis");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//new SupprDevis();
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Supprime un devis existant");
			menu.add(menuItem);
		
		
		
			menu.addSeparator();
		}
		
		//Devis en cours
		menuItem = new JMenuItem("Devis en cours");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Liste des devis en cours");
		menu.add(menuItem);
		
		// Liste Devis
		menuItem = new JMenuItem("Liste Devis");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Liste Devis");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Déconnexion
		
		menuItem = new JMenuItem("Déconnexion");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				new Identification(bdd);
			}
		});	
		menuItem.getAccessibleContext().setAccessibleDescription("Déconnexion de l'utilisateur");
		menu.add(menuItem);
		
		//Quitter
		menuItem = new JMenuItem("Quitter");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Quitte l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

