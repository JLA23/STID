package View.Options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.JTextComponent;

public class ClickDroit extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	private JTextComponent text;
	private boolean pasteCutAnnuler, copy;
	private JMenuItem cutMenuItem, copyMenuItem, pasteMenuItem, supprMenuItem;

	public ClickDroit(JTextComponent comp, boolean copy, boolean pasteCut) {
		this.text = comp;
		this.copy = copy;
		this.pasteCutAnnuler = pasteCut;
		init();
		text.setComponentPopupMenu(this);
	}
	
	private void init(){
		// Copy
		if (copy) {
			copyMenuItem = new JMenuItem("Copier");
			this.add(copyMenuItem);
			copyMenuItem.addActionListener(new Action(text));
		}

		// Cut
		if (pasteCutAnnuler) {
			cutMenuItem = new JMenuItem("Couper");
			this.add(cutMenuItem);
			cutMenuItem.addActionListener(new Action(text));

			// Paste
			pasteMenuItem = new JMenuItem("Coller");
			// pasteMenuItem.setEnabled(false);
			this.add(pasteMenuItem);
			pasteMenuItem.addActionListener(new Action(text));

			// Separator
			this.addSeparator();

			// Supprimer
			supprMenuItem = new JMenuItem("Supprimer");
			// supprMenuItem.setEnabled(false);
			this.add(supprMenuItem);
			supprMenuItem.addActionListener(new Action(text));
		}
	}
	
	private class Action implements ActionListener {

		private JTextComponent compo;

		public Action(JTextComponent j) {
			this.compo = j;
		}

		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getActionCommand().equals("Copier")) {
				compo.copy();
			} else if (actionEvent.getActionCommand().equals("Couper")) {
				compo.cut();
			} else if (actionEvent.getActionCommand().equals("Coller")) {
				compo.paste();
			} else if (actionEvent.getActionCommand().equals("Supprimer")) {
				compo.replaceSelection("");
			}
		}
	}

	public boolean isPasteCutAnnuler() {
		return pasteCutAnnuler;
	}

	public void setPasteCutAnnuler(boolean pasteCutAnnuler) {
		this.pasteCutAnnuler = pasteCutAnnuler;
		copyMenuItem.setVisible(false);
		init();
		text.setComponentPopupMenu(this);
	}
		
}
