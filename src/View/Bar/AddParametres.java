package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BDD.Base;
import View.Parameters.Categorie;
import View.Parameters.ConfigMail;
import View.Parameters.ParametreTaux;
import View.Parameters.Paritee;
import View.Parameters.Salarie;
import View.SearchParameters.SearchSalarie;

public class AddParametres{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private Base base;
	private JFrame fenetre;
	
	public AddParametres(Base bdd, JFrame frame){
		base = bdd;
		fenetre = frame;
		menu = new JMenu("Param�tres");
		menu.getAccessibleContext().setAccessibleDescription("Param�tres");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("lib/images/parametrage.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		
		
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
					new Salarie(fenetre, base);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("ajouter un salari�");
		menu.add(menuItem);
		
		//Modifier salari�
		menuItem = new JMenuItem("Modifier un salari�");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchSalarie(base, fenetre, "Modif");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier un salari�");
		menu.add(menuItem);
		
		//Supprimer salari�
		menuItem = new JMenuItem("Supprimer un salari�");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchSalarie(base, fenetre, "Suppr");
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
				new Paritee(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("D�finition des devises");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Mail
		menuItem = new JMenuItem("Configuration Mail");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new ConfigMail(fenetre, base);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() + "\n Le programe va quitter", "ATTENTION",JOptionPane.ERROR_MESSAGE);
					base.close();
					System.exit(1);
				}
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Configurer adresse mail");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		// Param�tres de l'application
		menuItem = new JMenuItem("Param�tres Taux");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ParametreTaux(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Param�tres de l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

