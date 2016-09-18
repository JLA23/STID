package View;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import BDD.Base;
import View.Bar.AddClients;
import View.Bar.AddCommandes;
import View.Bar.AddDevis;
import View.Bar.AddFacturation;
import View.Bar.AddParametres;
import View.Bar.AddPointages;
import View.Bar.AddPropos;
import View.Bar.AddSTID;
import View.Bar.AddTermes;
	
	public class MenuBar extends JMenuBar{
	
		private static final long serialVersionUID = 1L;

		public MenuBar(JFrame frame, Base bdd, String typeCompte) {
			this.add(new AddSTID(bdd, frame).getMenu());
			this.add(new AddDevis(bdd, typeCompte, frame).getMenu());
			this.add(new AddCommandes(bdd, typeCompte,frame).getMenu());
			this.add(new AddClients(bdd, frame, typeCompte).getMenu());
			this.add(new AddTermes(bdd, typeCompte, frame).getMenu());
			this.add(new AddFacturation(bdd, typeCompte, frame).getMenu());
			if(typeCompte.equals("Admin")){
			this.add(new AddPointages(bdd, frame).getMenu());
			this.add(new AddParametres(bdd, frame).getMenu());
			}
			this.add(new AddPropos(bdd).getMenu());

		}
		
}
