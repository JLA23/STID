package View.Factures;

import Model.Calcul;

public class NewAvoir {
	
	protected Factures factures;

	public NewAvoir(Factures facture){
		this.factures = facture;
		factures.getjCout().setText(((Double.parseDouble(factures.getjCout().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjPrefabrication().setText(((Double.parseDouble(factures.getjPrefabrication().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjFournitures().setText(((Double.parseDouble(factures.getjFournitures().getText().replaceAll(",", "\\.")) * -1) + "").replaceAll("\\.", ","));
		factures.getjCout().setEditable(true);
		factures.getjPrefabrication().setEditable(true);
		factures.getjFournitures().setEditable(true);
		factures.getCalcul1().setVisible(true);
		factures.getCalcul2().setVisible(true);
		factures.getCalcul3().setVisible(true);
		new Calcul().calculerMontantTTC(factures.getjFournitures(), factures.getjCout(), factures.getjPrefabrication(), factures.getjTotalHT(), factures.getjTotalTTC(), factures.getjTotalDevise(),
				factures.getValeurDevise(), Double.parseDouble(factures.getjTVA().getText().replaceAll(",", "\\.")), factures);
		
	}
	
}
