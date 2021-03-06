package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import BDD.Base;
import View.Impression.Factures.EtatsFactures;
import View.SearchFactures.SearchFacture;
import View.SearchTerme.SearchTerme;

public class AddFacturation {

	private JMenu menu;
	private JFrame fenetre;
	private JMenuItem menuItem;
	private Base bdd;

	public AddFacturation(Base base, String typeCompte, JFrame frame) {
		menu = new JMenu("Facturation");
		menu.getAccessibleContext().setAccessibleDescription("Facturation");
		this.bdd = base;
		this.fenetre = frame;
		ImageIcon icon = new ImageIcon(
				new ImageIcon("lib/images/factures.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		if (typeCompte.equals("Admin")) {
			// Creer une facture
			menuItem = new JMenuItem("Cr�er une facture");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SearchTerme(bdd, fenetre, "NewFacture");
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Cr�er une facture");
			menu.add(menuItem);

			// Modifier Facture ou avoir ou cr�er avoir
			menuItem = new JMenuItem("Modifier une facture / cr�er un avoir");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SearchFacture(bdd, fenetre, "Modif");
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou cr�er un avoir");
			menu.add(menuItem);

			// Supprimer Facture ou avoir
			menuItem = new JMenuItem("Supprimer une facture / avoir");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SearchFacture(bdd, fenetre, "Suppr");
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou cr�er un avoir");
			menu.add(menuItem);
		}
		// Afficher facture ou avoir
		menuItem = new JMenuItem("Afficher facture / avoir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchFacture(bdd, fenetre, "Recherche");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Modifier une facture ou cr�er un avoir");
		menu.add(menuItem);

		menu.addSeparator();

		// Listing TVA
		menuItem = new JMenuItem("Listing TVA");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsFactures(bdd, fenetre, 0);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Listing TVA");
		menu.add(menuItem);

		// Journal des ventes
		menuItem = new JMenuItem("Journal des ventes");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsFactures(bdd, fenetre, 1);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Calcul du CA HT sur un ou plusieur mois");
		menu.add(menuItem);
	}

	public JMenu getMenu() {
		return menu;
	}

}
