package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Impression.Pointage.EtatsHeureSpePointage;
import View.Pointage.HeureSpe;
import View.Pointage.HeuresProductives;
import View.Pointage.SaisiePointage;

public class AddPointages{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private Base base;
	private JFrame fenetre;
	
	public AddPointages(Base bdd, JFrame frame){
		menu = new JMenu("Pointages");
		menu.getAccessibleContext().setAccessibleDescription("Pointages");		
		this.base = bdd;
		this.fenetre = frame;
		ImageIcon icon = new ImageIcon(
				new ImageIcon("lib/images/pointage.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		
		//Saisir des heures spéciales
		menuItem = new JMenuItem("Saisir des heures spéciales");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new HeureSpe(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Saisir des heures spéciales");
		menu.add(menuItem);
		
		//Etat Hrs Spéciales
		menuItem = new JMenuItem("Etat Hrs Spéciales");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new EtatsHeureSpePointage(base, fenetre, 0);
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
				new SaisiePointage(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Saisir des pointages");
		menu.add(menuItem);
		
		
		//Etat pointage
		menuItem = new JMenuItem("Etat pointage");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsHeureSpePointage(base, fenetre, 1);
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
				new HeuresProductives(base, fenetre);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Heures productives");
		menu.add(menuItem);					
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

