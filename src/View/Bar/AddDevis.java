package View.Bar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class AddDevis{
	
	private JMenu menu;
	private JMenuItem menuItem;
	private JFrame frame;
	
	public AddDevis(JFrame f){
		this.frame = f;
		menu = new JMenu("Devis");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("Devis");		
		
		
		//Nouveau Devis
		menuItem = new JMenuItem("Nouveau Devis", KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new Devis();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Creation d'un nouveau devis");
		menu.add(menuItem);
		
		//Modifier Devis
		menuItem = new JMenuItem("Modifier Devis", KeyEvent.VK_M);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//new ModifDevis();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Modifie un devis existant");
		menu.add(menuItem);
		
		
		
		//Supprimer Devis
		menuItem = new JMenuItem("Supprimer Devis", KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SupprDevis();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Supprime un devis existant");
		menu.add(menuItem);
		
		
		
		menu.addSeparator();
		
		
		//Devis en cours
		menuItem = new JMenuItem("Devis en cours", KeyEvent.VK_D);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new DevisEnCours();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Liste des devis en cours");
		menu.add(menuItem);
		
		// Liste Devis
		menuItem = new JMenuItem("Liste Devis", KeyEvent.VK_L);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ListDevis();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Liste Devis");
		menu.add(menuItem);
		
		menu.addSeparator();
			
		
		//Quitter
		menuItem = new JMenuItem("Quitter", KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Quitte l'application");
		menu.add(menuItem);
	}
	
	public JMenu getMenu(){
		return menu;
	}
		
}

