package View.Termes;
import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import BDD.Base;
import Controller.ActionFermer;
import Controller.ActionRechercher;
import Controller.TestContenu;
import Controller.ValiderSuppr;
import Model.Calcul;
import Model.Donnees;
import View.Options.ClickDroit;

public class SupprTerme extends Termes {

	private static final long serialVersionUID = 1L;

	public SupprTerme(Base bdd, JFrame frame, String num, String indice, String fact) throws ParseException {
		super(bdd, frame);
		this.setTitle("STID Gestion 2.0 (Supprimer Terme)");
		this.base= bdd;
		valider.setText("Supprimer");
		valider.setBounds(460, 395, 100, 25);
		nouveau.setText("Recherche");
		nouveau.setBounds(20, 410, 100, 25);
		donnees = new Donnees(base);
		numero.setText(numero.getText() + num);
		numeroCommande = num;
		String nbindice = indice;
		numeroIndice = indice;
		new ClickDroit(jNumIndice, true, false);
		new ClickDroit(jLibelle, true, false);
		click.setFonction("Suppr");
		String[] res = null;
		if(fact == null){
		res = donnees.fiche(
				"t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient, t.numfacture",
				"commandes as c, clients as cl, termes as t", "t.numCommande = " + num + " and t.numIndice = "
						+ nbindice + " and t.numfacture is null and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		}
		else{
			res = donnees.fiche(
					"t.lblTerme, cl.nomclient, t.MntFour, t.CoutMo, t.Prefabrication, c.CodeDevise, c.numClient, t.numfacture",
					"commandes as c, clients as cl, termes as t", "t.numCommande = " + num + " and t.numIndice = "
							+ nbindice + " and t.numfacture = " + fact + " and t.numcommande = c.numCommande and c.numclient = cl.numclient");
		}
		jNumIndice.setText(nbindice);
		jLibelle.setText(res[0]);
		nameClient.setText(nameClient.getText() + res[1]);
		nameClient.setBounds(nameClient.getX(), nameClient.getY(), nameClient.getPreferredSize().width,
				nameClient.getPreferredSize().height);
		jFournitures.setText(res[2].replaceAll("\\.", ","));
		jCout.setText(res[3].replaceAll("\\.", ","));
		jPrefabrication.setText(res[4].replaceAll("\\.", ","));
		devises.setSelectedIndex(Integer.parseInt(res[5]) - 1);
		valeurDevise = Double.parseDouble((valeurDevises.get(devises.getSelectedItem().toString()))[2]);
		new Calcul().calculerMontant(jFournitures, jCout, jPrefabrication, jTotalDevis, jTotalDevisDevise,
				valeurDevise);
		new TestContenu(this, jFournitures, 1, "Termes");
		new TestContenu(this, jCout, 1, "Termes");
		new TestContenu(this, jPrefabrication, 1, "Termes");
		new TestContenu(this, jTotalDevis, 0, "Termes");
		new TestContenu(this, jTotalDevisDevise, 0, "Termes");
		jNumIndice.requestFocus();
		jFournitures.setEditable(false);
		jFournitures.setBackground(new Color(204, 204, 204));
		jCout.setEditable(false);
		jCout.setBackground(new Color(204, 204, 204));
		jPrefabrication.setEditable(false);
		jPrefabrication.setBackground(new Color(204, 204, 204));
		
		devises.setEnabled(false);
		jLibelle.setEditable(false);
		calcul1.setVisible(false);
		calcul2.setVisible(false);
		calcul3.setVisible(false);
		jNumIndice.setEditable(false);
		
		jLibelle.setBackground(new Color(204, 204, 204));
		jNumIndice.setBackground(new Color(204, 204, 204));
		valider.addActionListener(new ValiderSuppr(this, "Termes"));
		fermer.addActionListener(new ActionFermer(this, frame));
		nouveau.addActionListener(new ActionRechercher(this, "Termes", "Suppr"));
		if(res[7] != null){
			numFacture = res[7];
			jPanel6.setBounds(10, 65, jPanel6.getPreferredSize().width, jPanel6.getPreferredSize().height);
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.setBorder(BorderFactory.createTitledBorder("Facture"));
	        jPanel7.setPreferredSize(new Dimension(190, 230));
	        jPanel7.setBounds(12 + jPanel6.getPreferredSize().width , 65, jPanel7.getPreferredSize().width, jPanel7.getPreferredSize().height);
	        facture = new JLabel("N° Facture : " + res[7]);
	        String [] date = donnees.fiche("DATE_FORMAT(DateEmission, '%d/%m/%Y')", "factures", "numfacture = " + res[7]);
	        dateEmission = new JLabel("Emise le : " + date[0]);
	        facture.setBounds(30, 80, facture.getPreferredSize().width, facture.getPreferredSize().height);
	        dateEmission.setBounds(30, 110, dateEmission.getPreferredSize().width, dateEmission.getPreferredSize().height);
	        jfacture = new JButton("Voir facture");
	        jfacture.setBounds(50, 150, jfacture.getPreferredSize().width, jfacture.getPreferredSize().height);
	        jPanel7.add(facture);
	        jPanel7.add(dateEmission);
	        jPanel7.add(jfacture);
	        jPanel2.add(jPanel7);
		}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
}
