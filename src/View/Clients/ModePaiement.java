package View.Clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Donnees;

public class ModePaiement extends JDialog {

	private static final long serialVersionUID = 1L;
	protected LinkedHashMap<String, Object[]> valeur;
	protected Donnees donnees;

	public ModePaiement(Donnees donnees, JFrame frame) {
		super(frame, null, true);
		this.donnees = donnees;
	}

	public void afficher() {
		valeur = donnees.modesPaiements();
		this.setTitle("Modes Paiements");
		this.setLayout(new BorderLayout());
		this.setSize(170, 200);
		JPanel panelFromFile = new JPanel();
		panelFromFile.setLayout(new java.awt.GridLayout(0, 1));
		JScrollPane scrollPane2 = new JScrollPane(panelFromFile);
		scrollPane2.setMaximumSize(new Dimension(300, 180));
		for (Entry<String, Object[]> entry : valeur.entrySet()) {
			JCheckBox check = new JCheckBox(entry.getKey());
			boolean condition = (boolean) valeur.get(entry.getKey())[1];
			if (condition) {
				check.setSelected(true);
			}
			panelFromFile.add(check);
			check.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					JCheckBox check = (JCheckBox) arg0.getSource();
					boolean condition = check.isSelected();
					if (condition) {
						valeur.get(check.getText())[1] = false;
					} else {
						valeur.get(check.getText())[1] = true;
					}
				}

			});
		}
		JButton ok = new JButton("Valider");
		this.add(scrollPane2, BorderLayout.CENTER);
		this.add(ok, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}
