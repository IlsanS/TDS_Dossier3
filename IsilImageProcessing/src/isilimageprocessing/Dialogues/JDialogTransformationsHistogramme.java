package isilimageprocessing.Dialogues;

import CImage.CImageNG;
import CImage.Exceptions.CImageNGException;
import ImageProcessing.Histogramme.Events.GammaValueChangedEvent;
import ImageProcessing.Histogramme.Events.HistoValueChangedEventListener;
import ImageProcessing.Histogramme.Events.LinSatValueChangedEvent;
import ImageProcessing.Histogramme.Histogramme;
import isilimageprocessing.Panels.JPanelTransfoGammaParametres;
import isilimageprocessing.Panels.JPanelTransfoLinSatParametres;
import java.awt.Color;
import java.awt.Dimension;
import java.util.EventObject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JDialogTransformationsHistogramme extends javax.swing.JDialog
    implements HistoValueChangedEventListener
{
    public static int LIN = 0;
    public static int LIN_SAT = 1;
    public static int GAMMA = 2;
    public static int EGALI = 3;
    public static int NEGATIF = 4;
    
    private int[][] imageBefore;
    private int[][] imageAfter;
    private int[] courbeTonale;
    private int transfoType;
    
    private int min = 0;
    private int max = 255;
    private double gamma = 1;

    public JDialogTransformationsHistogramme(java.awt.Frame parent, boolean modal, 
            int[][] imageBefore, int transfoType) throws CImageNGException 
    {
        super(parent, modal);
        initComponents();
        
        //initialisation
        courbeTonale = new int[256];        
        this.imageBefore = new int[imageBefore.length][imageBefore[0].length];
        this.imageAfter = new int[imageBefore.length][imageBefore[0].length];
        
        //layout des panels
        this.jPanelImageBefore.setLayout(new java.awt.GridLayout(1, 1));
        this.jPanelImageAfter.setLayout(new java.awt.GridLayout(1, 1));
        this.jPanelSelectParametres.setLayout(new java.awt.GridLayout(1, 1));
        this.jPanelHistoBefore.setLayout(new java.awt.GridLayout(1, 1));
        this.jPanelHistoAfter.setLayout(new java.awt.GridLayout(1, 1));
        
        //affichage des images
        this.imageBefore = imageBefore;
        CImageNG cing = new CImageNG(this.imageBefore);
        jPanelImageBefore.add(new JLabel(new ImageIcon(cing.getImage())));
        
        //affichage des histogrammes
        showHistoBefore();
        showHistoAfter();
        
        //int correspondant au type de transformation
        this.transfoType = transfoType;
        
        this.setTitle(makeTitle());        
        
        setParamPanelForTransfoType();
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    //afficher le panel des paramètres en fonction du type de modification
    private void setParamPanelForTransfoType()
    {
        switch(transfoType)
        {
            case 0:
                createImageAfter();
                break;
            case 1:
                JPanelTransfoLinSatParametres jptlp = new JPanelTransfoLinSatParametres();
                jptlp.addEventListener(this);
                this.jPanelSelectParametres.add(jptlp);
                createImageAfter();
                break;
            case 2:
                JPanelTransfoGammaParametres jptgp = new JPanelTransfoGammaParametres();
                jptgp.addEventListener(this);
                this.jPanelSelectParametres.add(jptgp);
                createImageAfter();
                break;
            case 3:
                createImageAfter();
                break;
            case 4:
                createImageAfter();
                break;
        }
    }
    
    //générer l'image transformée et l'afficher
    private void createImageAfter()
    {
        switch(transfoType)
        {
            case 0:
                min = Histogramme.minimum(imageBefore);
                max = Histogramme.maximum(imageBefore);
                courbeTonale = 
                        Histogramme.creeCourbeTonaleLineaireSaturation(min, max);
                break;
            case 1:
                courbeTonale = 
                        Histogramme.creeCourbeTonaleLineaireSaturation(min, max);
                break;
            case 2:
                courbeTonale = 
                        Histogramme.creeCourbeTonaleGamma(gamma);
                break;
            case 3:
                courbeTonale = 
                        Histogramme.creeCourbeTonaleEgalisation(imageBefore);
                break;
            case 4:
                courbeTonale = Histogramme.creeCourbeTonaleNegatif();
                break;
        }
        
        try
        {
            imageAfter = Histogramme.rehaussement(imageBefore, courbeTonale);
            CImageNG cing = new CImageNG(this.imageAfter);
            jPanelImageAfter.removeAll();
            jPanelImageAfter.add(new JLabel(new ImageIcon(cing.getImage())));
            
            showHistoAfter();
        }
        catch(CImageNGException ex)
        {
            System.err.println("Erreur : " + ex.getMessage());
        }
    }
    
    public int[][] getImageAfter()
    {
        return this.imageAfter;
    }
    
    private String makeTitle()
    { 
        String title = "Transformation d'histogramme : ";
        switch(transfoType)
        {
            case 0:
                title += "Linéaire";
                break;
            case 1:
                title += "Linéaire avec saturation";
                break;
            case 2:
                title += "Gamma";
                break;
            case 3:
                title += "Égalisation";
                break;
            case 4:
                title += "Négatif";
                break;            
        }
        
        return title;
    }
    
    private ChartPanel getHistogramme(int[][] image)
    {
        int histo[];
        
        histo = Histogramme.Histogramme256(image);
        
        // Création du dataset
        XYSeries serie = new XYSeries("Histo");
        for(int i=0 ; i<256 ; i++) serie.add(i,histo[i]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);
        
        // Creation du chart
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogramme","Niveaux de gris","Nombre de pixels",
                dataset,PlotOrientation.VERTICAL,false,false,false);

        XYPlot plot = (XYPlot)chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0,255);
        plot.setDomainAxis(axeX);
        
        // creation d'un panel
        ChartPanel cp = new ChartPanel(chart);
        
        return cp;
    }
    
    private void showHistoBefore()
    {
        ChartPanel histo = getHistogramme(imageBefore);
        
        jPanelHistoBefore.removeAll();
        jPanelHistoBefore.add(histo);
        jPanelHistoBefore.setPreferredSize(new Dimension(325, 224));
        
        jPanelHistoBefore.revalidate();
    }
    
    private void showHistoAfter()
    {
        ChartPanel histo = getHistogramme(imageAfter);
        
        jPanelHistoAfter.removeAll();
        jPanelHistoAfter.add(histo);
        jPanelHistoAfter.setPreferredSize(new Dimension(325, 224));
        
        jPanelHistoAfter.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelImageBefore = new javax.swing.JPanel();
        jPanelImageAfter = new javax.swing.JPanel();
        jPanelSelectParametres = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanelHistoBefore = new javax.swing.JPanel();
        jPanelHistoAfter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout jPanelImageBeforeLayout = new javax.swing.GroupLayout(jPanelImageBefore);
        jPanelImageBefore.setLayout(jPanelImageBeforeLayout);
        jPanelImageBeforeLayout.setHorizontalGroup(
            jPanelImageBeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        jPanelImageBeforeLayout.setVerticalGroup(
            jPanelImageBeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelImageAfterLayout = new javax.swing.GroupLayout(jPanelImageAfter);
        jPanelImageAfter.setLayout(jPanelImageAfterLayout);
        jPanelImageAfterLayout.setHorizontalGroup(
            jPanelImageAfterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        jPanelImageAfterLayout.setVerticalGroup(
            jPanelImageAfterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelSelectParametresLayout = new javax.swing.GroupLayout(jPanelSelectParametres);
        jPanelSelectParametres.setLayout(jPanelSelectParametresLayout);
        jPanelSelectParametresLayout.setHorizontalGroup(
            jPanelSelectParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelSelectParametresLayout.setVerticalGroup(
            jPanelSelectParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanelHistoBefore.setMinimumSize(new java.awt.Dimension(325, 224));
        jPanelHistoBefore.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelHistoBeforeLayout = new javax.swing.GroupLayout(jPanelHistoBefore);
        jPanelHistoBefore.setLayout(jPanelHistoBeforeLayout);
        jPanelHistoBeforeLayout.setHorizontalGroup(
            jPanelHistoBeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        jPanelHistoBeforeLayout.setVerticalGroup(
            jPanelHistoBeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        jPanelHistoAfter.setMinimumSize(new java.awt.Dimension(325, 224));
        jPanelHistoAfter.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelHistoAfterLayout = new javax.swing.GroupLayout(jPanelHistoAfter);
        jPanelHistoAfter.setLayout(jPanelHistoAfterLayout);
        jPanelHistoAfterLayout.setHorizontalGroup(
            jPanelHistoAfterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        jPanelHistoAfterLayout.setVerticalGroup(
            jPanelHistoAfterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelImageBefore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelSelectParametres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelImageAfter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelHistoBefore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelHistoAfter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSelectParametres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelImageAfter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelImageBefore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelHistoAfter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelHistoBefore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOK)
                    .addComponent(jButtonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JPanel jPanelHistoAfter;
    private javax.swing.JPanel jPanelHistoBefore;
    private javax.swing.JPanel jPanelImageAfter;
    private javax.swing.JPanel jPanelImageBefore;
    private javax.swing.JPanel jPanelSelectParametres;
    // End of variables declaration//GEN-END:variables

    @Override
    public void handlerHistoValueChanged(EventObject e) 
    {
        switch(transfoType)
        {
            case 1:
                LinSatValueChangedEvent lsvce = (LinSatValueChangedEvent) e;
                min = lsvce.getMin();
                max = lsvce.getMax();
                break;
            case 2:
                GammaValueChangedEvent gvce = (GammaValueChangedEvent) e;
                gamma = gvce.getGamma();
                break;
            default:
                break;
        }
        
        createImageAfter();
    }
}
