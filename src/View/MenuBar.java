package View;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import View.Bar.AddCommandes;
import View.Bar.AddDevis;
	
	public class MenuBar extends JMenuBar{
	
		private static final long serialVersionUID = 1L;

		/**
		 * Cree une MBar
		 * @param frame La frame sur laquelle mettre la MBar
		 * @param i l'Item3D sur lequel affecter la MBar
		 * @param ifo 
		 */
		public MenuBar(JFrame frame) {

			this.add(new AddDevis(frame).getMenu());
			this.add(new AddCommandes().getMenu());
			/*addClients();
			addTermes();
			addFacturation();
			addPointages();
			addParametres();*/

		}
		
}
