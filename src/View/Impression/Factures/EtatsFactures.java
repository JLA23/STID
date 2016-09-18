package View.Impression.Factures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import BDD.Base;
import Controller.ActionSearch;
import Controller.EcouteAction;
import Controller.FocusJText;
import Controller.KeyAutocomplete;
import Controller.LimiteCaratere;
import Model.Donnees;
import View.Impression.InterfaceListing;
import fr.julien.autocomplete.model.AutoCompleteModel;
import fr.julien.autocomplete.view.AutoComplete;

public class EtatsFactures extends InterfaceListing {

	private static final long serialVersionUID = 1L;
	
	protected Object[][] listClients;
	protected AutoComplete jNumClient;
	protected JFormattedTextField jmois, jannee, jCAHT, jCAHTCumul;
	protected JLabel labelClient, labelDate;
	protected JPanel listTVA, jdv;
	protected JDateChooser jDate;
	protected JRadioButton br1, br2;
	protected DecimalFormat nf;

	public EtatsFactures(Base base, JFrame frame, int visible) {
		super(base, frame);
		this.setTitle("Etats Factures");
		listTVA = new JPanel();
		listTVA.setLayout(null);
		listTVA.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 40, jtp.getPreferredSize().height - 40));
		jdv = new JPanel();
		jdv.setLayout(null);
		jdv.setPreferredSize(new Dimension(jtp.getPreferredSize().width - 10, jtp.getPreferredSize().height - 10));
		jtp.add("Listing TVA", listTVA);
		jtp.add("Journal des ventes", jdv);
		initlistTVA();
		initjdv();
		jtp.setSelectedIndex(visible);
		imprimer.addActionListener(new ActionImpression(this, "EtatsFactures") );
		afficher();
	}
	
	private void initlistTVA(){
		NumberFormat num = NumberFormat.getIntegerInstance();
		num.setGroupingUsed(false);
		JLabel listingtemps = new JLabel("Listing TVA");
		listingtemps.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.DEFAULTSIZE, 16);
		listingtemps.setFont(font);
		listingtemps.setBounds(30, 30, listingtemps.getPreferredSize().width, listingtemps.getPreferredSize().height);
		listTVA.add(listingtemps);
		JLabel informations = new JLabel(
				"Edition de la liste des factures et des montants de la TVA.");
		informations.setBounds(40, 60, informations.getPreferredSize().width, informations.getPreferredSize().height);
		listTVA.add(informations);
		JLabel parametrage = new JLabel("Paramétrage de l'édition :");
		parametrage.setForeground(Color.BLUE);
		parametrage.setFont(font);
		parametrage.setBounds(30, 90, parametrage.getPreferredSize().width, parametrage.getPreferredSize().height);
		listTVA.add(parametrage);

		JPanel restriction = new JPanel();
		restriction.setBorder(BorderFactory.createTitledBorder("Restriction"));
		restriction.setBounds(30, 120, 500, 110);
		restriction.setLayout(null);
		listTVA.add(restriction);
		
		JLabel mois = new JLabel("Mois de valeur");
		jmois = new JFormattedTextField(num);
		jmois.addKeyListener(new EcouteAction(jmois, false));
		jmois.addKeyListener(new LimiteCaratere(jmois,2));
		mois.setBounds(100 - mois.getPreferredSize().width, 50, mois.getPreferredSize().width,
				mois.getPreferredSize().height);
		restriction.add(mois);
		jmois.setPreferredSize(new Dimension(50, 25));
		jmois.setBounds(110, 48, jmois.getPreferredSize().width, jmois.getPreferredSize().height);
		restriction.add(jmois);
		
		JLabel annee = new JLabel("Mois de valeur");
		jannee = new JFormattedTextField(num);
		jannee.addKeyListener(new EcouteAction(jannee, false));
		jannee.addKeyListener(new LimiteCaratere(jannee,4));
		annee.setBounds(140 + jmois.getPreferredSize().width, 50, annee.getPreferredSize().width,
				annee.getPreferredSize().height);
		restriction.add(annee);
		jannee.setPreferredSize(new Dimension(80, 25));
		jannee.setBounds(150 + jmois.getPreferredSize().width + annee.getPreferredSize().width, 48, jannee.getPreferredSize().width, jannee.getPreferredSize().height);
		restriction.add(jannee);
		
		int anneeEnCours = Calendar.getInstance().get(Calendar.YEAR);
		int moisEnCours = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		jmois.setText(moisEnCours + "");
		jannee.setText(anneeEnCours + "");
		
	}
	
	private void initjdv(){
		
		Donnees donnees = new Donnees(bdd);
		JPanel parametres = new JPanel();
		parametres.setBorder(BorderFactory.createTitledBorder("Paramètres"));
		parametres.setBounds(15, 10, 420, 110);
		parametres.setLayout(null);
		jdv.add(parametres);
		
		JLabel date = new JLabel("Mois/Année");
		jDate = new JDateChooser();
		jDate.setDateFormatString("MM/yyyy");
		jDate.setDate(new Date());
		date.setBounds(100 - date.getPreferredSize().width, 25, date.getPreferredSize().width, date.getPreferredSize().height);
		jDate.setBounds(110, 23, 100, 25);
		parametres.add(date);
		parametres.add(jDate);
		
		ImageIcon icon2 = new ImageIcon("lib/images/loupe.png");
		JLabel client = new JLabel("Client");
		AutoCompleteModel modelDevis2 = new AutoCompleteModel();
		modelDevis2.addAll(listClients());
		jNumClient = new AutoComplete(modelDevis2);
		jNumClient.getZoneTexte().addKeyListener(new EcouteAction(jNumClient.getZoneTexte(), false));
		client.setBounds(100 - client.getPreferredSize().width, 65, client.getPreferredSize().width,
				client.getPreferredSize().height);
		parametres.add(client);
		jNumClient.setPreferredSize(new Dimension(80, 25));
		jNumClient.setBounds(110, 63, jNumClient.getPreferredSize().width, jNumClient.getPreferredSize().height);
		jNumClient.getZoneTexte().addFocusListener(new FocusJText(this, "EtatsFactures"));
		jNumClient.getZoneTexte().addKeyListener(new KeyAutocomplete(this, "EtatsFactures"));
		parametres.add(jNumClient);
		JButton list3 = new JButton(icon2);
		list3.setPreferredSize(new Dimension(19, 20));
		list3.setBounds(115 + jNumClient.getPreferredSize().width, 65, list3.getPreferredSize().width,
				list3.getPreferredSize().height);
		list3.addActionListener(new ActionSearch(this, "EtatsFactures", "NumClient", jNumClient));
		parametres.add(list3);
		labelClient = new JLabel();
		labelClient.setBounds(130 + jNumClient.getPreferredSize().width + list3.getPreferredSize().width, 65, 0, 0);
		labelClient.setForeground(Color.BLUE);
		parametres.add(labelClient);
		
		//Résultats
		JPanel resultats = new JPanel();
		resultats.setBorder(BorderFactory.createTitledBorder("Résultats"));
		resultats.setBounds(15, 130, 530, 100);
		resultats.setLayout(null);
		jdv.add(resultats);
		
		nf = new DecimalFormat("#0.00");
		nf.setGroupingUsed(false);
		
		JLabel ca = new JLabel("CA HT pour le mois");
		jCAHT = new JFormattedTextField(nf);
		ca.setBounds(125 - ca.getPreferredSize().width, 25, ca.getPreferredSize().width, ca.getPreferredSize().height);
		jCAHT.setBounds(135 , 23, 130, 25);
		String [] res = donnees.fiche("symbole", "devises", "pardefaut = 1");
		JLabel symbole1 = new JLabel();
		symbole1.setText(res[0]);
		symbole1.setBounds(140 + 130, 25, symbole1.getPreferredSize().width, symbole1.getPreferredSize().height);
		
		resultats.add(ca);
		resultats.add(jCAHT);
		resultats.add(symbole1);
		
		JLabel cacumul = new JLabel("CA HT cumulé");
		jCAHTCumul = new JFormattedTextField(nf);
		cacumul.setBounds(125 - cacumul.getPreferredSize().width, 55, cacumul.getPreferredSize().width, cacumul.getPreferredSize().height);
		jCAHTCumul.setBounds(135 , 53, 130, 25);
		JLabel symbole2 = new JLabel();
		symbole2.setText(res[0]);
		symbole2.setBounds(140 + 130, 55, symbole2.getPreferredSize().width, symbole2.getPreferredSize().height);
		
		resultats.add(cacumul);
		resultats.add(jCAHTCumul);
		resultats.add(symbole2);
		
		jCAHT.setEditable(false);
		jCAHTCumul.setEditable(false);
		
		labelDate = new JLabel();
		labelDate.setForeground(Color.BLUE);
		Font font = new Font("Arial", com.lowagie.text.Font.ITALIC, 12);
		labelDate.setFont(font);
		labelDate.setBounds(155 + 130 + symbole1.getPreferredSize().width, 25, labelDate.getPreferredSize().width, labelDate.getPreferredSize().height);
		resultats.add(labelDate);
		
		JButton executer = new JButton("Executer");
		executer.setBounds(145 + 130 + symbole2.getPreferredSize().width + 10 , 53, executer.getPreferredSize().width, executer.getPreferredSize().height);
		resultats.add(executer);
		executer.addActionListener(new ExecuterCA());
		
		
		//Résultats
		JPanel rupture = new JPanel();
		rupture.setBorder(BorderFactory.createTitledBorder("Rupture"));
		rupture.setBounds(440, 10, 120, 110);
		rupture.setLayout(null);
		jdv.add(rupture);
		
		ButtonGroup bg = new ButtonGroup();
		br1 = new JRadioButton("Sans Rupture", true);
		br2 = new JRadioButton("Par Client", false);
		br1.setBounds(12, 25, br1.getPreferredSize().width, br1.getPreferredSize().height);
		br2.setBounds(12, 60, br2.getPreferredSize().width, br2.getPreferredSize().height);
		bg.add(br1);
		bg.add(br2);
		rupture.add(br1);
		rupture.add(br2);
		
	}
	
	protected ArrayList<String> listClients() {
		listClients = donnees.liste("NumClient, NomClient", "clients", null);
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < listClients.length; i++) {
			res.add(listClients[i][0].toString());
		}
		return res;
	}

	public JFormattedTextField getjmois() {
		return jmois;
	}

	public void setjmois(JFormattedTextField jmois) {
		this.jmois = jmois;
	}

	public Object[][] getListClients() {
		return listClients;
	}

	public void setListClients(Object[][] listClients) {
		this.listClients = listClients;
	}

	public AutoComplete getjNumClient() {
		return jNumClient;
	}

	public void setjNumClient(AutoComplete jNumClient) {
		this.jNumClient = jNumClient;
	}

	public JLabel getLabelClient() {
		return labelClient;
	}

	public void setLabelClient(JLabel labelClient) {
		this.labelClient = labelClient;
	}

	public JDateChooser getjDate() {
		return jDate;
	}

	public void setjDate(JDateChooser jDate) {
		this.jDate = jDate;
	}

	public JRadioButton getBr1() {
		return br1;
	}

	public void setBr1(JRadioButton br1) {
		this.br1 = br1;
	}

	public JRadioButton getBr2() {
		return br2;
	}

	public void setBr2(JRadioButton br2) {
		this.br2 = br2;
	}

	
	public class ExecuterCA implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = new SimpleDateFormat("MM/yyyy").format(jDate.getDate());
			if(DateValide(s)){
				if(jNumClient.getZoneTexte().getText().isEmpty()){
					String [] res = donnees.fiche("Sum(t.prefabrication + t.coutmo + t.mntfour)", "termes as t, factures as f", "t.numfacture = f.numfacture and date_format(f.DateEmission, '%m') = "+ new SimpleDateFormat("MM").format(jDate.getDate()) + " and date_format(f.DateEmission, '%Y') = " + new SimpleDateFormat("yyyy").format(jDate.getDate()));
					String [] res2 = donnees.fiche("Sum(t.prefabrication + t.coutmo + t.mntfour)", "termes as t, factures as f", "t.numfacture = f.numfacture and date_format(f.DateEmission, '%m') <= "+ new SimpleDateFormat("MM").format(jDate.getDate()) + " and date_format(f.DateEmission, '%Y') = " + new SimpleDateFormat("yyyy").format(jDate.getDate()));
					if(res[0] == null){
						jCAHT.setText("0,00");
					}
					else{
						jCAHT.setText(nf.format(Double.parseDouble(res[0])));
					}
					if(res2[0] == null){
						jCAHTCumul.setText("0,00");
					}
					else{
						jCAHTCumul.setText(nf.format(Double.parseDouble(res2[0])));
					}
				}
				else{
					String [] res = donnees.fiche("Sum(t.prefabrication + t.coutmo + t.mntfour)", "termes as t, factures as f, commandes as c", "t.numcommande = c.numcommande and c.numclient = " + jNumClient.getText() + " and t.numfacture = f.numfacture and date_format(f.DateEmission, '%m') = "+ new SimpleDateFormat("MM").format(jDate.getDate()) + " and date_format(f.DateEmission, '%Y') = " + new SimpleDateFormat("yyyy").format(jDate.getDate()));
					String [] res2 = donnees.fiche("Sum(t.prefabrication + t.coutmo + t.mntfour)", "termes as t, factures as f, commandes as c", "t.numcommande = c.numcommande and c.numclient = " + jNumClient.getText() + " and t.numfacture = f.numfacture and date_format(f.DateEmission, '%m') <= "+ new SimpleDateFormat("MM").format(jDate.getDate()) + " and date_format(f.DateEmission, '%Y') = " + new SimpleDateFormat("yyyy").format(jDate.getDate()));
					if(res[0] == null){
						jCAHT.setText("0,00");
					}
					else{
						jCAHT.setText(nf.format(Double.parseDouble(res[0])));
					}
					if(res2[0] == null){
						jCAHTCumul.setText("0,00");
					}
					else{
						jCAHTCumul.setText(nf.format(Double.parseDouble(res2[0])));
					}
				}
				
				labelDate.setText("Du 01/01" + new SimpleDateFormat("yyyy").format(jDate.getDate()) + " au 31/" + new SimpleDateFormat("MM").format(jDate.getDate()) + "/" + new SimpleDateFormat("yyyy").format(jDate.getDate()));
				labelDate.setBounds(labelDate.getX(),
						labelDate.getY(),
						labelDate.getPreferredSize().width,
						labelDate.getPreferredSize().height);
			}
			else {
				
			}
			
		}
	}
	
	public boolean DateValide (String s){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Date d = new Date();
        try {
            d = sdf.parse(s);
            String t = sdf.format(d);
            if(t.compareTo(s) !=  0)
                return false;
            else
                return true;
        } catch (Exception e) {
                return false;
        }
	}

	public JFormattedTextField getJmois() {
		return jmois;
	}

	public void setJmois(JFormattedTextField jmois) {
		this.jmois = jmois;
	}

	public JFormattedTextField getJannee() {
		return jannee;
	}

	public void setJannee(JFormattedTextField jannee) {
		this.jannee = jannee;
	}

	public JFormattedTextField getjCAHT() {
		return jCAHT;
	}

	public void setjCAHT(JFormattedTextField jCAHT) {
		this.jCAHT = jCAHT;
	}

	public JFormattedTextField getjCAHTCumul() {
		return jCAHTCumul;
	}

	public void setjCAHTCumul(JFormattedTextField jCAHTCumul) {
		this.jCAHTCumul = jCAHTCumul;
	}
}
