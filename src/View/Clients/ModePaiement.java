package View.Clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
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

	public ModePaiement(Donnees donnees, JFrame frame, LinkedHashMap<String, Object[]> valeurs) {
		super(frame, null, true);
		this.donnees = donnees;
		if (valeurs == null) {
			valeur = donnees.modesPaiements();
		}
		else{
			valeur = valeurs;
		}
	}

	public void afficher() {
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
						valeur.get(check.getText())[1] = true;
					} else {
						valeur.get(check.getText())[1] = false;
					}
				}

			});
		}
		this.add(scrollPane2, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public ArrayList<String> modesSelect() {
		ArrayList<String> list = new ArrayList<String>();
		for (Entry<String, Object[]> entry : valeur.entrySet()) {
			if ((boolean) valeur.get(entry.getKey())[1]) {
				list.add((String) valeur.get(entry.getKey())[0]);
			}
		}
		return list;
	}
}
