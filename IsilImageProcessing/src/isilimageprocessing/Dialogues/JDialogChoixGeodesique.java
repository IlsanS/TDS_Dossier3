package isilimageprocessing.Dialogues;

import CImage.CImageNG;
import CImage.Exceptions.CImageNGException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class JDialogChoixGeodesique extends javax.swing.JDialog {

    private int tailleMasque;
    private int nbIter;
    private int[][] masque;
    
    public JDialogChoixGeodesique(java.awt.Frame parent, boolean modal, 
            int t, int n, int[][] m)
    {
        super(parent, modal);
        tailleMasque = t;
        nbIter = n;
        masque = m;
        
        initComponents();
        jSpinnerTailleMasque.setValue(tailleMasque);
        if(nbIter == -1)
        {
            jLabelItereations.setEnabled(false);
            jSpinnerIterations.setEnabled(false);
        }
        else
            jSpinnerIterations.setValue(nbIter);
        if(masque != null)
        {
            try
            {
                jScrollPaneImage.setViewportView(new JLabel(new ImageIcon(
                        new CImageNG(masque).getImage())));
            }
            catch(CImageNGException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinnerTailleMasque = new javax.swing.JSpinner();
        jButtonOk = new javax.swing.JButton();
        jButtonMasqueGeo = new javax.swing.JButton();
        jScrollPaneImage = new javax.swing.JScrollPane();
        jSpinnerIterations = new javax.swing.JSpinner();
        jLabelElemStruct = new javax.swing.JLabel();
        jLabelItereations = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix des paramètres géodésiques");

        jSpinnerTailleMasque.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(2)));

        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonMasqueGeo.setText("Masque géodésique");
        jButtonMasqueGeo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMasqueGeoActionPerformed(evt);
            }
        });

        jSpinnerIterations.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        jLabelElemStruct.setText("Taille élement structurant :");

        jLabelItereations.setText("Nombre itérations :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneImage)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelElemStruct)
                            .addComponent(jLabelItereations))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerTailleMasque, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(jSpinnerIterations))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonMasqueGeo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelElemStruct)
                    .addComponent(jSpinnerTailleMasque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMasqueGeo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOk)
                    .addComponent(jLabelItereations))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneImage, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonOk.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleName("Choix des paramètres géodésiques");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        tailleMasque = (Integer) jSpinnerTailleMasque.getValue();
        nbIter = (Integer) jSpinnerIterations.getValue();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonMasqueGeoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMasqueGeoActionPerformed
        JFileChooser jfc = new JFileChooser(".");
        jfc.setMultiSelectionEnabled(false);
        File f;
        
        jfc.showOpenDialog(this);
        f = jfc.getSelectedFile();
        try
        {
            CImageNG image = new CImageNG(f);
            masque = image.getMatrice();
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        } 
        catch (CImageNGException ex) 
        {
            System.err.println(ex.getMessage());
        }        
        jScrollPaneImage.setViewportView(new JLabel(new ImageIcon(f.getPath())));
    }//GEN-LAST:event_jButtonMasqueGeoActionPerformed

    public int getTailleMasque()
    {
        return tailleMasque;
    }
    
    public int getNombreIterations()
    {
        return nbIter;
    }
    
    public int[][] getMasqueGeodesique()
    {
        return masque;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMasqueGeo;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabelElemStruct;
    private javax.swing.JLabel jLabelItereations;
    private javax.swing.JScrollPane jScrollPaneImage;
    private javax.swing.JSpinner jSpinnerIterations;
    private javax.swing.JSpinner jSpinnerTailleMasque;
    // End of variables declaration//GEN-END:variables
}
