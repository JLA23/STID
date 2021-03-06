package View.Bar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BDD.Base;
import Model.Donnees;
import View.Clients.CA;
import View.Clients.NewClient;
import View.Impression.Clients.EtatsClients;
import View.SearchClients.SearchClient;

public class AddClients {

	private JMenu menu;
	private JMenuItem menuItem;
	private Base base;
	private Donnees donnees;
	private JFrame frame;

	public AddClients(Base bdd, JFrame fenetre,String typeCompte) {
		menu = new JMenu("Clients");
		this.base = bdd;
		this.frame = fenetre;
		donnees = new Donnees(base);
		menu.getAccessibleContext().setAccessibleDescription("Clients");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("lib/images/Client.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		menu.setIcon(icon);
		if (typeCompte.equals("Admin")) {
			// Nouveau client
			menuItem = new JMenuItem("Nouveau Client");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new NewClient(base);
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un nouveau client");
			menu.add(menuItem);

			// Modifier client
			menuItem = new JMenuItem("Modifier Client");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SearchClient(base, null, "Modif");
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Modifie client");
			menu.add(menuItem);

			// Supprimer Client
			menuItem = new JMenuItem("Supprimer Client");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (donnees.noLier("c.numClient", "clients as c",
							"not exists (select * from devis as d where d.numclient = c.numclient)")) {
						new SearchClient(base, null, "Suppr");
					} else {
						JOptionPane.showMessageDialog(null, "Aucun client ne peut �tre supprim�", "ATTENTION",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			menuItem.getAccessibleContext().setAccessibleDescription("Supprime un client");
			menu.add(menuItem);
		}
		// Rechercher Client
		menuItem = new JMenuItem("Rechercher Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchClient(base, null, "Recherche");
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Rechercher un client");
		menu.add(menuItem);

		menu.addSeparator();

		// Liste des Clients
		menuItem = new JMenuItem("Liste des Clients");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsClients(base, frame, 0);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Liste des clients");
		menu.add(menuItem);

		// Retard de paiement
		menuItem = new JMenuItem("Retard de paiement");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsClients(base, frame, 1);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Retard");
		menu.add(menuItem);
		
		// Impay�
		menuItem = new JMenuItem("Impay�");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsClients(base, frame, 2);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Impay�");
		menu.add(menuItem);

		// Factures � venir
		menuItem = new JMenuItem("Factures � venir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EtatsClients(base, frame, 3);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Factures � venir");
		menu.add(menuItem);

		menu.addSeparator();

		// %CA / Client
		menuItem = new JMenuItem("% CA / Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CA(base, frame);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Resultat");
		menu.add(menuItem);

	}

	public JMenu getMenu() {
		return menu;
	}

}
