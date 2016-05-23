package View.Factures;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import Controller.Factures.NewAvoir.ActionValiderAvoir;
import Model.Calcul;

public class NewAvoir {
	
	protected Factures factures;

	public NewAvoir(Factures facture){
		this.factures = facture;
		int nbFacture = factures.getDonnees().newNum("factures","NumFacture", null);
		factures.getjNumFacture().setText(nbFacture + "");
		factures.getjNumFacture().setEditable(true);
		factures.getjNumFacture().setBackground(Color.white);
		factures.getjTVA().setEditable(true);
		factures.getjTVA().setBackground(Color.white);
		factures.getjCout().setText(((Double.parseDouble(factures.getjCout().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjPrefabrication().setText(((Double.parseDouble(factures.getjPrefabrication().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjFournitures().setText(((Double.parseDouble(factures.getjFournitures().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjCout().setEditable(true);
		factures.getjPrefabrication().setEditable(true);
		factures.getjFournitures().setEditable(true);
		factures.getjCout().setBackground(Color.white);
		factures.getjPrefabrication().setBackground(Color.white);
		factures.getjFournitures().setBackground(Color.white);
		factures.getCalcul1().setVisible(true);
		factures.getCalcul2().setVisible(true);
		factures.getCalcul3().setVisible(true);
		new Calcul().calculerMontantTTC(factures.getjFournitures(), factures.getjCout(), factures.getjPrefabrication(), factures.getjTotalHT(), factures.getjTotalTTC(), factures.getjTotalDevise(),
				factures.getValeurDevise(), Double.parseDouble(factures.getjTVA().getText().replaceAll(",", "\\.")), factures);
		factures.getNouveau().setVisible(false);
		factures.getjValeur().setText("0");
		factures.getjDateEmission().setDate(new Date());
		factures.getjDateEcheance().setDate(new Date());
		factures.getJavoir().setVisible(false);
		factures.getJbfacture().setVisible(false);
		    for( ActionListener al : factures.getValider().getActionListeners() ) {
		    	factures.getValider().removeActionListener( al );
		    }
		factures.getValider().addActionListener(new ActionValiderAvoir(factures));
		
	}
	
}
