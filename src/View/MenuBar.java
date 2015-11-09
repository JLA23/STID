package View;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import View.Bar.AddClients;
import View.Bar.AddCommandes;
import View.Bar.AddDevis;
import View.Bar.AddFacturation;
import View.Bar.AddParametres;
import View.Bar.AddPointages;
import View.Bar.AddPropos;
import View.Bar.AddTermes;
	
	public class MenuBar extends JMenuBar{
	
		private static final long serialVersionUID = 1L;

		/**
		 * Cree une MBar
		 * @param frame La frame sur laquelle mettre la MBar
		 * @param i l'Item3D sur lequel affecter la MBar
		 * @param ifo 
		 */
		public MenuBar(JFrame frame) {
			//this.setLayout(new GridLayout());
			this.add(new AddDevis(frame).getMenu());
			this.add(new AddCommandes().getMenu());
			this.add(new AddClients().getMenu());
			this.add(new AddTermes().getMenu());
			this.add(new AddFacturation().getMenu());
			this.add(new AddPointages().getMenu());
			this.add(new AddParametres().getMenu());
			this.add(new AddPropos().getMenu());

		}
		
}
