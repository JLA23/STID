package Model;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

import BDD.Base;

/**
 * A simple demonstration application showing how to create a pie chart using data from a
 * {@link DefaultPieDataset}.
 *
 */
@SuppressWarnings("serial")
public class PieChart extends JFrame {
	
	protected Base base;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PieChart(String annee, Base bdd) {

        super("CA/Client");
        
        this.base = bdd;
		this.setIconImage(new ImageIcon("lib/images/icone.png").getImage());
        // create a dataset...
        final PieDataset dataset = createSampleDataset(annee);
        
        // create the chart...
        final JFreeChart chart = createChart(dataset);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setLayout(null);
        chartPanel.setBounds(0,0, 500, 270);
        setContentPane(chartPanel);
        
        this.setPreferredSize(new Dimension(800, 800));
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
 
    }
    
    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    private PieDataset createSampleDataset(String annee) {
        
    	Donnees donnees = new Donnees(base);
    	
    	Object[][] res = donnees.liste("Round(Sum(t.Prefabrication + t.coutMo + t.mntFour),2), cl.NomClient", "factures as f, clients as cl, termes as t, commandes as c", "date_format(f.DateEmission, '%Y') = " + annee + " and date_format(f.DateEmission, '%m') <= 12 and f.numfacture = t.numfacture and t.numcommande = c.numcommande and c.numclient = cl.numclient group by cl.numclient");
    	
        final DefaultPieDataset result = new DefaultPieDataset();
        
        for(int i = 0; i<res.length; i++){
        	result.setValue((String)res[i][1] + " : " + new java.text.DecimalFormat("#,##0.00").format(Double.parseDouble((String)res[i][0])), Double.parseDouble((String)res[i][0]));
        }
        return result;
        
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "CA/Client",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelGenerator(null);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
        
    }
}