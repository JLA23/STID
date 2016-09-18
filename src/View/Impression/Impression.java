package View.Impression;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import BDD.Base;
import Controller.ActionFermer;
import Thread.ThreadImpression;
import Thread.ThreadImpressionDivers;

public class Impression extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JButton bouton, bouton2, bouton3, bouton4;
	protected String facture, typeClass;
	protected Double valeur;
	protected Base bdd;
	protected JFrame fenetre;
	protected Object objet;

	public Impression(String numfacture, Base base, Double valeurtext, JFrame fenetre){
		this.facture = numfacture;
		this.valeur = valeurtext;
		this.bdd = base;
		this.fenetre = fenetre;
		fenetre.setEnabled(false);
		this.setSize(550, 250);
		init();
		this.setVisible(true);
		this.addWindowListener(new ActionFermer(this, fenetre));

	    ImageIcon icon4 = new ImageIcon(new ImageIcon("lib/images/mail.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    bouton4 = new JButton(icon4);
	    bouton4.setPreferredSize(new Dimension(120, 120));
	    bouton4.setBounds(35 + bouton.getPreferredSize().width + bouton.getPreferredSize().width + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    this.add(bouton4);
	    
		actionFacture();
	  }
	
	public Impression(Object frame, String typeClasse){
		((JFrame)frame).setEnabled(false);
		fenetre = ((JFrame)frame);
		this.setSize(460, 250);
		this.objet = frame;
		this.typeClass = typeClasse;
		init();
		this.setVisible(true);
		this.addWindowListener(new ActionFermer(this, fenetre));
		action();
	}
	  
	  private void init(){
	    this.setLayout(null);
	    this.setResizable(false);
        this.setLocationRelativeTo(null);
	    JLabel text = new JLabel("Choissisez le mode d'impression");
	    Font font = new Font("Calibri", Font.ITALIC, 28);
	    text.setFont(font);
	    text.setBounds((((560-140)- text.getPreferredSize().width)/2), 10, text.getPreferredSize().width, text.getPreferredSize().height);
	    this.add(text);
	    ImageIcon icon = new ImageIcon(new ImageIcon("lib/images/imprimante.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    bouton = new JButton(icon);
	    bouton.setPreferredSize(new Dimension(120, 120));
	    bouton.setBounds(20,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    this.add(bouton);
	    ImageIcon icon2 = new ImageIcon(new ImageIcon("lib/images/loupe2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    bouton2 = new JButton(icon2);
	    bouton2.setPreferredSize(new Dimension(120, 120));
	    bouton2.setBounds(25 + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    this.add(bouton2);
	    ImageIcon icon3 = new ImageIcon(new ImageIcon("lib/images/pdf.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	    bouton3 = new JButton(icon3);
	    bouton3.setPreferredSize(new Dimension(120, 120));
	    bouton3.setBounds(30 + bouton.getPreferredSize().width + bouton.getPreferredSize().width,65, bouton.getPreferredSize().width, bouton.getPreferredSize().height);
	    this.add(bouton3);	    
	  }
	  
	  private void actionFacture(){
		  bouton.addActionListener(new ImprimerFacture(this));
		  bouton2.addActionListener(new ApercuFacture(this));
		  bouton3.addActionListener(new PDFFacture(this));
		  bouton4.addActionListener(new MailFacture(this));
	  }
	  
	  private void action(){
		  bouton.addActionListener(new ActionImprimer(this, "Imprimer"));
		  bouton2.addActionListener(new ActionImprimer(this, "Apercu"));
		  bouton3.addActionListener(new ActionImprimer(this, "PDF"));
	  }
	  
	  private class ImprimerFacture implements ActionListener {
		  
		  protected Impression impr;
		  
		  public ImprimerFacture(Impression impression){
			  this.impr = impression;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			impr.dispose();
            new ThreadImpression(facture, bdd, valeur, "Imprimer", fenetre).start();
			
		}
		  
	  }
	  private class PDFFacture implements ActionListener {
		  
		  protected Impression impr;
		  
		  public PDFFacture(Impression impression){
			  this.impr = impression;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			impr.dispose();
			 new ThreadImpression(facture, bdd, valeur, "PDF", fenetre).start();
		}
		  
	  }
	  
	  private class MailFacture implements ActionListener {
		  
		  protected Impression impr;
		  
		  public MailFacture(Impression impression){
			  this.impr = impression;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			impr.dispose();
			 new ThreadImpression(facture, bdd, valeur, "Mail", fenetre).start();
		}
		  
	  }
	  
	  private class ApercuFacture implements ActionListener {
		  
		  protected Impression impr;
		  
		  public ApercuFacture(Impression impression){
			  this.impr = impression;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			impr.dispose();
			 new ThreadImpression(facture, bdd, valeur, "Apercu", fenetre).start();
		}
		  
	  }
	  
	  private class ActionImprimer implements ActionListener{
		  
		  protected Impression impr;
		  protected String typeimpression;
		  
		  public ActionImprimer(Impression impression, String typeimpr){
			  this.impr = impression;
			  this.typeimpression = typeimpr;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			impr.dispose();
			new ThreadImpressionDivers(objet, typeClass, typeimpression, fenetre).start();		}
	  }
	}
