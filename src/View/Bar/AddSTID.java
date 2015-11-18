package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Identification;
import View.ModifUser.AjoutUser;
import View.ModifUser.ModifPseudo;

public class AddSTID{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private JFrame frame;
	private Base base;
	
	public AddSTID(Base bdd, JFrame f){
		this.frame = f;
		this.base = bdd;
		menu = new JMenu("STID");
		menu.getAccessibleContext().setAccessibleDescription("Clients");		
		
		
		//Modifier Mot de passe
		menuItem = new JMenuItem("Modifier MDP");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier le Mot de Passe");
		menu.add(menuItem);
		
		//Modifier Pseudo
		menuItem = new JMenuItem("Modifier Pseudo");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifPseudo(base, frame);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier le pseudo");
		menu.add(menuItem);
		
		if(bdd.getTypeCompte().equals("Admin")){
		menu.addSeparator();
		
		//Ajouter Utilisateur
		menuItem = new JMenuItem("Ajouter Utilisateur");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AjoutUser(base);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Ajouter un Utilisateur");
		menu.add(menuItem);
		
		//Suppression Utilisateur
		
		menuItem = new JMenuItem("Supprimer Utilisateur");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*frame.dispose();
				String addr = base.getAdresse();
				String basebdd = base.getBase();
				base.close();
				new Identification(addr, basebdd);*/
			}
		});	
		menuItem.getAccessibleContext().setAccessibleDescription("Suppresion d'un utilisateur");
		menu.add(menuItem);
		
		//Modifier Utilisateur
		
		menuItem = new JMenuItem("Modifier Utilisateur");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*frame.dispose();
				String addr = base.getAdresse();
				String basebdd = base.getBase();
				base.close();
				new Identification(addr, basebdd);*/
			}
		});	
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier utilisateur");
		menu.add(menuItem);
		
		}
		
		menu.addSeparator();
		
		//Déconnexion
		
		menuItem = new JMenuItem("Déconnexion");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				String addr = base.getAdresse();
				String basebdd = base.getBase();
				base.close();
				new Identification(addr, basebdd);
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
				base.close();
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Quitte l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}


